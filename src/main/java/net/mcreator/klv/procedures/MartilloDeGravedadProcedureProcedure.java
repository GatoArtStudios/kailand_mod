package net.mcreator.klv.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.stream.Collectors;

public class MartilloDeGravedadProcedureProcedure {
    public static void execute(Level world, double x, double y, double z, Entity entity, boolean isSecondary) {
        if (entity == null || !(entity instanceof Player)) return;
        Player player = (Player) entity;

        // Definir el radio de atracción/empuje, aumentado en 4 bloques
        double radius = 14.0;

        // Obtener todas las entidades dentro del radio especificado
        List<Entity> entities = world.getEntitiesOfClass(Entity.class, new AABB(
            new BlockPos(x - radius, y - radius, z - radius),
            new BlockPos(x + radius, y + radius, z + radius)
        ));

        // Filtrar la lista de entidades para excluir al jugador
        entities = entities.stream()
            .filter(e -> !e.equals(player))
            .collect(Collectors.toList());

        // Aplicar la fuerza de empuje/atracción, la elevación, el daño y el efecto de caída lenta a cada entidad
        for (Entity target : entities) {
            if (target instanceof LivingEntity livingTarget) {
                Vec3 direction;
                if (isSecondary) {
                    // Empujar lejos
                    direction = new Vec3(target.getX() - x, target.getY() - y, target.getZ() - z).normalize();
                    // Empujar y elevar 3 bloques en el aire
                    double force = 2.5; // Fuerza de empuje
                    livingTarget.setDeltaMovement(livingTarget.getDeltaMovement().add(
                        direction.x * force,
                        3.0,
                        direction.z * force
                    ));
                } else {
                    // Atraer
                    direction = new Vec3(x - target.getX(), y - target.getY(), z - target.getZ()).normalize();
                    // Atraer y elevar 2 bloques en el aire
                    double force = 1.5; // Fuerza de atracción
                    livingTarget.setDeltaMovement(livingTarget.getDeltaMovement().add(
                        direction.x * force,
                        2.0,
                        direction.z * force
                    ));
                }

                // Aplicar el efecto de caída lenta
                livingTarget.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 8 * 20, 0, false, false));

                // Quitar vida (3 corazones = 6 de daño)
                livingTarget.hurt(DamageSource.GENERIC, 6.0F);
            }
        }

        // Efecto visual de explosión
        if (world instanceof ServerLevel) {
            ServerLevel serverWorld = (ServerLevel) world;
            serverWorld.sendParticles(ParticleTypes.EXPLOSION, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
            serverWorld.playSound(null, new BlockPos(x, y, z), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);

            // Efecto de rotura de bloque en el suelo
            for (BlockPos pos : BlockPos.betweenClosed(
                new BlockPos(x - radius, y - 1, z - radius),
                new BlockPos(x + radius, y - 1, z + radius)
            )) {
                serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()), pos.getX(), pos.getY(), pos.getZ(), 1, 0.5, 0.5, 0.5, 0.0);
            }
        }
    }
}
