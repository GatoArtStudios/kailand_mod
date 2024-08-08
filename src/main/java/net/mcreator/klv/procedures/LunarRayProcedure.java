package net.mcreator.klv.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.server.level.ServerLevel;
import net.mcreator.klv.init.KlvModMobEffects; // Importa tu clase de efectos de poción

import java.util.List;
import java.util.Map;

public class LunarRayProcedure {
    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("entity") == null) {
            System.err.println("Failed to load dependency entity for procedure LunarRayProcedure!");
            return;
        }
        if (dependencies.get("world") == null) {
            System.err.println("Failed to load dependency world for procedure LunarRayProcedure!");
            return;
        }

        Entity entity = (Entity) dependencies.get("entity");
        Level world = (Level) dependencies.get("world");

        if (!(entity instanceof Player)) return;
        Player player = (Player) entity;

        if (player.isCrouching()) {
            // Habilidad especial cuando el jugador está agachado
            if (world.isNight()) {
                if (!player.hasEffect(KlvModMobEffects.COOLDOWN.get())) {
                    // Spawnear partículas alrededor del jugador (usando partículas que recuerden a la Luna, como SOUL)
                    for (int i = 0; i < 360; i += 10) {
                        double dx = player.getX() + Math.cos(Math.toRadians(i)) * 2;
                        double dz = player.getZ() + Math.sin(Math.toRadians(i)) * 2;
                        if (world instanceof ServerLevel) {
                            ((ServerLevel) world).sendParticles(ParticleTypes.SOUL, dx, player.getY() + 2, dz, 1, 0, 0, 0, 0.1);
                        }
                    }
                    // Dar efectos al jugador
                    player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 400, 1));
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0));
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 0));
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 600, 0));
                    // Aplicar el efecto de cooldown de 40 segundos
                    player.addEffect(new MobEffectInstance(KlvModMobEffects.COOLDOWN.get(), 800, 0));
                } else {
                    // Mostrar mensaje si la habilidad está en cooldown
                    if (!world.isClientSide()) {
                        player.displayClientMessage(Component.literal("La habilidad secundaria está en cooldown"), true);
                    }
                }
            } else {
                // Mostrar mensaje si es de día
                if (!world.isClientSide()) {
                    player.displayClientMessage(Component.literal("Esta habilidad solo se activa por la noche"), true);
                }
            }
        } else {
            // Lanzar el rayo solo durante la noche
            if (world.isNight()) {
                // Lanzar el rayo en la dirección que el jugador está mirando
                Vec3 lookVec = player.getLookAngle();
                double x = player.getX();
                double y = player.getY() + player.getEyeHeight();
                double z = player.getZ();

                for (int i = 0; i < 20; i++) {
                    double dx = x + lookVec.x * i;
                    double dy = y + lookVec.y * i;
                    double dz = z + lookVec.z * i;

                    if (world instanceof ServerLevel) {
                        ((ServerLevel) world).sendParticles(ParticleTypes.END_ROD, dx, dy, dz, 1, 0, 0, 0, 0);

                        AABB area = new AABB(dx - 0.5, dy - 0.5, dz - 0.5, dx + 0.5, dy + 0.5, dz + 0.5);
                        List<Entity> entities = world.getEntities((Entity) null, area, e -> !e.equals(entity));
                        for (Entity target : entities) {
                            if (target instanceof LivingEntity) {
                                ((LivingEntity) target).hurt(DamageSource.GENERIC, 4.0F); // Incrementar el daño
                                ((LivingEntity) target).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 20, 2, false, true));
                            }
                        }
                    }
                }

                if (!world.isClientSide()) {
                    world.playSound(null, new BlockPos(x, y, z), SoundEvents.EVOKER_CAST_SPELL, SoundSource.PLAYERS, 1.0F, 1.0F); // Sonido místico y corto
                }
                // Establecer cooldown de 3 segundos en el ítem en ambas manos
                player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 60);
                player.getCooldowns().addCooldown(player.getOffhandItem().getItem(), 60);
            } else {
                // Mostrar mensaje si es de día
                if (!world.isClientSide()) {
                    player.displayClientMessage(Component.literal("El rayo solo se puede lanzar por la noche"), true);
                }
            }
        }
    }
}
