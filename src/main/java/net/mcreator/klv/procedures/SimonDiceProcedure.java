package net.mcreator.klv.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent;
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

public class SimonDiceProcedure {
    private static final Random random = new Random();
    private static final int RADIUS = 10;
    private static final int DAMAGE = 10; // 5 corazones (1 corazón = 2 de daño)
    private static final int TIME_LIMIT = 100; // 5 segundos (20 ticks por segundo)

    private static final Map<UUID, Boolean> playerActions = new ConcurrentHashMap<>();

    public static void execute(LevelAccessor world, double x, double y, double z, Player player) {
        if (!(world instanceof ServerLevel serverWorld)) return;

        int order = random.nextInt(7); // Genera un número aleatorio entre 0 y 6
        List<Player> playersInRadius = serverWorld.getEntitiesOfClass(Player.class, player.getBoundingBox().inflate(RADIUS));

        // Enviar orden a jugadores en el radio
        for (Player target : playersInRadius) {
            if (target != player) { // Evitar afectar al jugador que usa la varita
                playerActions.put(target.getUUID(), false);
                switch (order) {
                    case 0 -> sendChatMessage(target, "Tienes que agacharte");
                    case 1 -> sendChatMessage(target, "Tienes que moverte");
                    case 2 -> sendChatMessage(target, "Tienes que pegar al aire (clic izquierdo)");
                    case 3 -> sendChatMessage(target, "Tienes que saltar");
                    case 4 -> sendChatMessage(target, "Tienes que lanzar un ítem");
                    case 5 -> sendChatMessage(target, "Tienes que abrir el inventario");
                    case 6 -> sendChatMessage(target, "Tienes que escribir un mensaje en el chat");
                }
            }
        }

        // Registrar eventos de verificación
        MinecraftForge.EVENT_BUS.register(new Object() {
            private int ticks = 0;
            private final List<Player> playersToCheck = playersInRadius;
            private final BlockPos startPos = new BlockPos(x, y, z);

            @SubscribeEvent
            public void onServerTick(TickEvent.ServerTickEvent event) {
                if (event.phase == TickEvent.Phase.END) {
                    ticks++;
                    int timeLeft = (TIME_LIMIT - ticks) / 20; // tiempo restante en segundos
                    for (Player target : playersToCheck) {
                        if (target != player) { // Evitar afectar al jugador que usa la varita
                            // Mostrar el tiempo restante en la barra de acción
                            ((ServerPlayer) target).displayClientMessage(Component.literal("Tiempo restante: " + timeLeft + "s"), true);

                            if (ticks >= TIME_LIMIT) {
                                if (!playerActions.getOrDefault(target.getUUID(), false)) {
                                    target.hurt(DamageSource.MAGIC, DAMAGE);
                                    player.displayClientMessage(Component.literal("El jugador " + target.getName().getString() + " falló la orden y recibió daño."), true);
                                } else {
                                    player.displayClientMessage(Component.literal("El jugador " + target.getName().getString() + " cumplió la orden perfectamente."), true);
                                }
                                MinecraftForge.EVENT_BUS.unregister(this);
                            }
                        }
                    }
                }
            }

            @SubscribeEvent
            public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
                if (event.getEntity() instanceof Player && playersToCheck.contains(event.getEntity())) {
                    playerActions.put(event.getEntity().getUUID(), true);
                }
            }

            @SubscribeEvent
            public void onPlayerSwing(PlayerInteractEvent.LeftClickEmpty event) {
                if (event.getEntity() instanceof Player && playersToCheck.contains(event.getEntity())) {
                    playerActions.put(event.getEntity().getUUID(), true);
                }
            }

            @SubscribeEvent
            public void onPlayerOpenContainer(PlayerContainerEvent.Open event) {
                if (event.getEntity() instanceof Player && playersToCheck.contains(event.getEntity())) {
                    playerActions.put(event.getEntity().getUUID(), true);
                }
            }

            @SubscribeEvent
            public void onPlayerChat(ServerChatEvent event) {
                if (playersToCheck.contains(event.getPlayer())) {
                    playerActions.put(event.getPlayer().getUUID(), true);
                }
            }
        });

        // Emitir sonido
        player.playSound(SoundEvents.NOTE_BLOCK_PLING, 1.0F, 1.0F);
    }

    private static void sendChatMessage(Player player, String message) {
        player.sendSystemMessage(Component.literal(message));
    }
}
