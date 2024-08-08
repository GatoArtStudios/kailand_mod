package net.mcreator.klv.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.Level;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.mcreator.klv.init.KlvModMobEffects; // Asegúrate de importar tu efecto

import java.util.List;

public class MisticaSwordEffectProcedure {
    public static void execute(Player player) {
        Level world = player.level;

        // Reproducir un sonido mágico
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);

        // Invocar partículas de fuegos artificiales en un círculo alrededor del jugador
        double radius = 5.0; // Radio de 5 bloques (diámetro de 10 bloques)
        for (double theta = 0; theta < 2 * Math.PI; theta += Math.PI / 16) {
            double x = player.getX() + radius * Math.cos(theta);
            double y = player.getY() + 1; // Un poco por encima del suelo
            double z = player.getZ() + radius * Math.sin(theta);
            world.addParticle(ParticleTypes.FIREWORK, x, y, z, 0, 0, 0);
        }

        // Generar paredes de partículas de fuegos artificiales alrededor del jugador
        for (int i = -5; i <= 5; i++) {
            for (int j = -5; j <= 5; j++) {
                if (Math.abs(i) == 5 || Math.abs(j) == 5) {
                    for (int k = 0; k <= 3; k++) { // Altura de la pared de partículas
                        double x = player.getX() + i;
                        double y = player.getY() + k;
                        double z = player.getZ() + j;
                        world.addParticle(ParticleTypes.FIREWORK, x, y, z, 0, 0, 0);
                    }
                }
            }
        }

        // Aplicar efecto de "RandomDirection" a las entidades en un radio de 10x10
        AABB area = new AABB(player.getX() - 5, player.getY() - 5, player.getZ() - 5, player.getX() + 5, player.getY() + 5, player.getZ() + 5);
        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, area);

        for (LivingEntity entity : entities) {
            if (entity != player) { // No aplicar el efecto al propio jugador
                entity.addEffect(new MobEffectInstance(KlvModMobEffects.RANDOM_DIRECTION.get(), 60, 0)); // 60 ticks = 3 segundos
            }
        }
    }
}
