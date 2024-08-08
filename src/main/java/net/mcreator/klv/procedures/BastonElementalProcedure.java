package net.mcreator.klv.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Random;

import net.mcreator.klv.init.KlvModItems;
import net.mcreator.klv.init.KlvModMobEffects; // Asegúrate de que esta clase esté correctamente importada

public class BastonElementalProcedure {
    private static final Random random = new Random();
    private static final int RADIUS = 5;
    private static final int DAMAGE = 10; // 5 corazones (1 corazón = 2 de daño)
    private static final int TIME_LIMIT = 100; // 5 segundos (20 ticks por segundo)

    private static final Map<UUID, Boolean> playerActions = new ConcurrentHashMap<>();

    private static final int DURATION_AREA_REGENERACION = 700; // 35 segundos
    private static final int DURATION_CADENAS_DE_LUZ = 100; // 5 segundos
    private static final int DURATION_CAMBIO = 160; // 8 segundos

    private static final String[] habilidades = {"Área de Regeneración", "Cadenas de Luz", "Cambio"};
    private static final Map<UUID, Integer> currentHabilidad = new ConcurrentHashMap<>();

    public static void execute(LevelAccessor world, double x, double y, double z, Player player, ItemStack itemstack) {
        if (!(world instanceof ServerLevel serverWorld)) return;

        UUID playerUUID = player.getUUID();
        int habilidadIndex = currentHabilidad.getOrDefault(playerUUID, 0);

        // Verificar si el jugador está agachado y hace clic derecho
        if (player.isCrouching()) {
            habilidadIndex = (habilidadIndex + 1) % habilidades.length;
            currentHabilidad.put(playerUUID, habilidadIndex);
            player.displayClientMessage(Component.literal("Habilidad actual: " + habilidades[habilidadIndex]), true);
            return;
        }

        // Verificar si el jugador tiene el efecto de cooldown correspondiente
        if (tieneCooldown(player, habilidadIndex)) {
            player.displayClientMessage(Component.literal("Esta habilidad está en cooldown."), true);
            return;
        }

        switch (habilidadIndex) {
            case 0 -> activarAreaRegeneracion(serverWorld, x, y, z, player, itemstack);
            case 1 -> activarCadenasDeLuz(world, player, itemstack);
            case 2 -> activarCambio(world, player, itemstack);
        }
    }

    private static boolean tieneCooldown(Player player, int habilidadIndex) {
        return switch (habilidadIndex) {
            case 0 -> player.hasEffect(KlvModMobEffects.COOLDOWN_AREA_REGENERATIVA.get());
            case 1 -> player.hasEffect(KlvModMobEffects.COOLDOWN_CADENAS_DE_LUZ.get());
            case 2 -> player.hasEffect(KlvModMobEffects.COOLDOWN_CAMBIO.get());
            default -> false;
        };
    }

    private static void activarAreaRegeneracion(ServerLevel world, double x, double y, double z, Player player, ItemStack itemstack) {
        // Crear el círculo de partículas y aplicar regeneración
        double radius = 5.0;
        for (double theta = 0; theta < 2 * Math.PI; theta += Math.PI / 16) {
            double dx = radius * Math.cos(theta);
            double dz = radius * Math.sin(theta);
            world.sendParticles(ParticleTypes.HEART, x + dx, y, z + dz, 1, 0, 0, 0, 0);
        }

        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(5));
        for (LivingEntity entity : entities) {
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 140, 1)); // Regeneración II por 7 segundos
        }

        // También curar al portador del bastón
        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 140, 1)); // Regeneración II por 7 segundos
        player.addEffect(new MobEffectInstance(KlvModMobEffects.COOLDOWN_AREA_REGENERATIVA.get(), DURATION_AREA_REGENERACION, 0)); // Cooldown de 35 segundos

        world.playSound(null, new BlockPos(x, y, z), SoundEvents.GENERIC_DRINK, player.getSoundSource(), 1.0F, 1.0F);
    }

    private static void activarCadenasDeLuz(LevelAccessor world, Player player, ItemStack itemstack) {
        // Lanza un rayo de partículas mágicas y congela a la entidad impactada
        double range = 20.0;
        Vec3 lookVec = player.getLookAngle();
        double x = player.getX();
        double y = player.getY() + player.getEyeHeight();
        double z = player.getZ();

        world.playSound(null, new BlockPos(x, y, z), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.0F); // Sonido al lanzar el rayo

        for (int i = 0; i < range; i++) {
            double dx = x + lookVec.x * i;
            double dy = y + lookVec.y * i;
            double dz = z + lookVec.z * i;

            if (world instanceof ServerLevel) {
                ((ServerLevel) world).sendParticles(ParticleTypes.SNOWFLAKE, dx, dy, dz, 1, 0, 0, 0, 0);

                AABB area = new AABB(dx - 0.5, dy - 0.5, dz - 0.5, dx + 0.5, dy + 0.5, dz + 0.5);
                List<Entity> entities = world.getEntities((Entity) null, area, e -> !e.equals(player));
                for (Entity target : entities) {
                    if (target instanceof LivingEntity) {
                        ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 80)); // Lentitud extrema por 2 segundos
                        ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.GLOWING, 40, 0)); // Luminiscencia por 2 segundos
                        world.playSound(null, target.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F); // Sonido de congelamiento
                    }
                }
            }
        }

        player.addEffect(new MobEffectInstance(KlvModMobEffects.COOLDOWN_CADENAS_DE_LUZ.get(), DURATION_CADENAS_DE_LUZ, 0)); // Cooldown de 5 segundos
    }

    private static void activarCambio(LevelAccessor world, Player player, ItemStack itemstack) {
        // Intercambia lugares con la entidad apuntada
        double range = 40.0;
        Vec3 lookVec = player.getLookAngle();
        double x = player.getX();
        double y = player.getY() + player.getEyeHeight();
        double z = player.getZ();

        Entity target = null;

        for (int i = 0; i < range; i++) {
            double dx = x + lookVec.x * i;
            double dy = y + lookVec.y * i;
            double dz = z + lookVec.z * i;

            AABB area = new AABB(dx - 0.5, dy - 0.5, dz - 0.5, dx + 0.5, dy + 0.5, dz + 0.5);
            List<Entity> entities = world.getEntities((Entity) null, area, e -> !e.equals(player));
            for (Entity entity : entities) {
                if (entity instanceof LivingEntity) {
                    target = entity;
                    break;
                }
            }

            if (target != null) break;
        }

        if (target != null) {
            double targetX = target.getX();
            double targetY = target.getY();
            double targetZ = target.getZ();

            double distance = player.distanceTo(target);

            if (distance > range) {
                player.displayClientMessage(Component.literal("No puedes cambiar de lugar con entidades tan lejanas."), true);
            } else {
                player.teleportTo(targetX, targetY, targetZ);
                target.teleportTo(x, y, z);

                for (int i = 0; i < 32; ++i) {
                    if (world instanceof ServerLevel serverWorld) {
                        serverWorld.sendParticles(ParticleTypes.PORTAL, targetX, targetY + random.nextDouble() * 2.0D, targetZ, 1, random.nextGaussian(), 0.0D, random.nextGaussian(), 0.0D);
                        serverWorld.sendParticles(ParticleTypes.PORTAL, x, y + random.nextDouble() * 2.0D, z, 1, random.nextGaussian(), 0.0D, random.nextGaussian(), 0.0D);
                    }
                }

                world.playSound(null, new BlockPos(x, y, z), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);

                player.addEffect(new MobEffectInstance(KlvModMobEffects.COOLDOWN_CAMBIO.get(), DURATION_CAMBIO, 0)); // Cooldown de 8 segundos
            }
        }
    }
}
