package net.mcreator.klv.procedures;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSetTitlesAnimationPacket;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvents;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class SimonDiceProcedure {
    private static final Random random = new Random();
    private static final int RADIUS = 10;
    private static final int DAMAGE = 10; // 5 corazones (1 corazón = 2 de daño)
    private static final int TIME_LIMIT = 100; // 5 segundos (20 ticks por segundo)

    private static final Map<UUID, Boolean> playerActions = new ConcurrentHashMap<>();
    private static final Map<UUID, Double> lastPositions = new ConcurrentHashMap<>();

    public static void execute(LevelAccessor world, double x, double y, double z, ServerPlayer playerSender) {
        if (!(world instanceof ServerLevel serverWorld)) return;

        int order = random.nextInt(7); // Genera un número aleatorio entre 0 y 6
//        List<Player> playersInRadius = serverWorld.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(RADIUS));
        List<ServerPlayer> playersInRadius = serverWorld.players().stream().filter(player -> player != playerSender).filter(player -> player.distanceToSqr(playerSender) <= Math.pow(RADIUS, 2)).collect(Collectors.toList());

        if (playersInRadius.isEmpty()) return; // Retorna si no hay jugadores en el radio

        // Enviar orden a jugadores en el radio
        for (ServerPlayer target : playersInRadius) {
            if (target != playerSender) { // Evitar afectar al jugador que usa la varita
                playerActions.put(target.getUUID(), false);
                switch (order) {
                    case 0 -> {
                        sendOrderTitle(target, "\u00A7bAgacharte");
                        target.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        sendConfusionParticles(target);
                    }
//                    case 0 -> sendChatMessage(target, "Tienes que agacharte");
                    case 1 -> {
                        sendOrderTitle(target, "\u00A7aMoverte");
                        target.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        sendConfusionParticles(target);
                    }
//                    case 1 -> sendChatMessage(target, "Tienes que moverte");
                    case 2 -> {
                        sendOrderTitle(target, "\u00A79Preciona clic izquierdo en el aire");
                        target.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        sendConfusionParticles(target);
                    }
//                    case 2 -> sendChatMessage(target, "Tienes que pegar al aire (clic izquierdo)");
                    case 3 -> {
                        sendOrderTitle(target, "\u00A7eSaltar");
                        target.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        sendConfusionParticles(target);
                    }
//                    case 3 -> sendChatMessage(target, "Tienes que saltar");
                    case 4 -> {
                        sendOrderTitle(target, "\u00A76Lanzar un ítem");
                        target.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        sendConfusionParticles(target);
                    }
//                    case 4 -> sendChatMessage(target, "Tienes que lanzar un ítem");
                    case 5 -> {
                        sendOrderTitle(target, "\u00A7dAbrir el inventario");
                        target.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        sendConfusionParticles(target);
                    }
//                    case 5 -> sendChatMessage(target, "Tienes que abrir el inventario");
                    case 6 -> {
                        sendOrderTitle(target, "\u00A7cEscribir un mensaje en el chat");
                        target.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        sendConfusionParticles(target);
                    }
//                    case 6 -> sendChatMessage(target, "Tienes que escribir un mensaje en el chat");
                }
                double targetPost = getPositionPlayer(target); // Sumamos las posiciones si el usaurio se a movido, seran otras posiciones
                lastPositions.put(target.getUUID(), targetPost); // Agregamos la posicion inicial del usuario objetivo
            }
        }

        // Registrar eventos de verificación
        MinecraftForge.EVENT_BUS.register(new Object() {
            private int ticks = 0;
            private final List<ServerPlayer> playersToCheck = playersInRadius;

            @SubscribeEvent
            public void onServerTick(TickEvent.ServerTickEvent event) {
                if (event.phase == TickEvent.Phase.END) {
                    ticks++;
                    int timeLeft = (TIME_LIMIT - ticks) / 20; // tiempo restante en segundos
                    for (ServerPlayer target : playersToCheck) {
                        if (target != playerSender) { // Evitar afectar al jugador que usa la varita
                            // Mostrar el tiempo restante en la barra de acción
                            target.displayClientMessage(Component.literal("\u00A7cTiempo restante: " + timeLeft + " segundos"), true);
//                            ((ServerPlayer) target).displayClientMessage(Component.literal("Tiempo restante: " + timeLeft + "s"), true);

                            if (ticks >= TIME_LIMIT) {
                                StringBuilder playerComplete = new StringBuilder();
                                StringBuilder playerOver = new StringBuilder();

                                if (!playerActions.getOrDefault(target.getUUID(), false)) {
                                    target.hurt(DamageSource.MAGIC, DAMAGE);
                                    if (!playerOver.isEmpty()) {
                                        playerOver.append(", ");
                                    }
                                    playerOver.append(target.getName().getString());
//                                    playerSender.displayClientMessage(Component.literal("El jugador " + target.getName().getString() + " falló la orden y recibió daño."), true);
                                } else {
                                    if (!playerComplete.isEmpty()) {
                                        playerComplete.append(", ");
                                    }
                                    playerComplete.append(target.getName().getString());
//                                    playerSender.displayClientMessage(Component.literal("El jugador " + target.getName().getString() + " cumplió la orden perfectamente."), true);
                                }
                                playerSender.displayClientMessage(Component.literal(
                                        "Jugadores que han fallado: " + (!playerOver.isEmpty() ? playerOver.toString() : "Ninguno") +
                                                "\nJugadores que han completado: " + (!playerComplete.isEmpty() ? playerComplete.toString() : "Ninguno")), true);
                                MinecraftForge.EVENT_BUS.unregister(this);
                            }
                        }
                    }
                }
            }

            @SubscribeEvent
            public void onTick(TickEvent.PlayerTickEvent event) {
//                playerSender.displayClientMessage(Component.literal("Evento de entidad"), true);
                if (order == 1 || order == 0 && playersToCheck.contains(event.player)) {
                    if (order == 0) {
                        // Comprovamos si el usuario se agacho
                        boolean isSneaking = event.player.isCrouching();
                        if (isSneaking) {
                            playerActions.put(event.player.getUUID(), true);
                        }

                    } else if (order == 1) {
                        // Comprovamos si el usuario se movio
                        double playerPost = getPositionPlayer(event.player); // Sumamos las posiciones si el usaurio se a movido, seran otras posiciones
                        if (playerPost != lastPositions.getOrDefault(event.player.getUUID(), 0.0)) {
                            playerActions.put(event.player.getUUID(), true);
                        }
                    }
                }
            }

            @SubscribeEvent
            public void onPlayerSwing(PlayerInteractEvent.LeftClickEmpty event) {
//                playerSender.displayClientMessage(Component.literal("Has pegado al aire"), true);
                if (event.getEntity() instanceof Player && playersToCheck.contains(event.getEntity()) && order == 2) {
                    playerActions.put(event.getEntity().getUUID(), true);
                }
            }

            @SubscribeEvent
            public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
//                playerSender.displayClientMessage(Component.literal("Has saltado exitosamente"), true);
                if (event.getEntity() instanceof Player && playersToCheck.contains(event.getEntity()) && order == 3) {
                    playerActions.put(event.getEntity().getUUID(), true);
                }
            }

            @SubscribeEvent
            public void onItemToss(ItemTossEvent event) {
//                playerSender.displayClientMessage(Component.literal("Has tirado un ítem"), true);
                if (order == 4 && playersToCheck.contains(event.getPlayer())) {
                    playerActions.put(event.getPlayer().getUUID(), true);
                }
            }
            @SubscribeEvent
            public void onPlayerOpenContainer(PlayerContainerEvent.Open event) {
//                playerSender.displayClientMessage(Component.literal("Has abierto el inventario"), true);
                if (event.getEntity() instanceof Player && playersToCheck.contains(event.getEntity()) && order == 5) {
                    playerActions.put(event.getEntity().getUUID(), true);
                }
            }
            @SubscribeEvent
            public void onPlayerChat(ServerChatEvent event) {
//                playerSender.displayClientMessage(Component.literal("Has escrito un mensaje en el chat"), true);
                if (playersToCheck.contains(event.getPlayer()) && order == 6) {
                    playerActions.put(event.getPlayer().getUUID(), true);
                }
            }
        });

        // Emitir sonido
        playerSender.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
    }

    private static void sendChatMessage(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }

    private static double getPositionPlayer(Player player) {
        return player.getX() + player.getY() + player.getZ();
    }

    private static void sendOrderTitle(ServerPlayer player, String order) {
        player.connection.send(new ClientboundSetTitleTextPacket(Component.literal(order)));
        player.connection.send(new ClientboundSetTitlesAnimationPacket(10, 100, 10));
    }
    private static void sendConfusionParticles(ServerPlayer player) {
        Vec3 pos = player.position();
        AABB aabb = player.getBoundingBox();
        ((ServerLevel) player.level).sendParticles(
                ParticleTypes.CLOUD,
                pos.x, pos.y + aabb.getYsize() / 2, pos.z,
                20, aabb.getXsize() / 2, aabb.getYsize() / 2, aabb.getZsize() / 2, 0.2
        );
    }
}
