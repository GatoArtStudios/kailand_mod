package net.mcreator.klv.procedures;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraft.sounds.SoundEvents; // Importar los sonidos
import net.minecraft.sounds.SoundSource; // Importar la fuente de sonido

import net.mcreator.klv.init.KlvModItems;

import java.util.Map;

public class EspadaShulkerProcedureProcedure {
    public static void execute(Map<String, Object> dependencies) {
        if (dependencies.get("entity") == null || dependencies.get("world") == null) {
            return;
        }

        Entity entity = (Entity) dependencies.get("entity");
        Level world = (Level) dependencies.get("world");

        if (entity instanceof LivingEntity _livEnt && _livEnt.getMainHandItem().getItem() == KlvModItems.ESPADA_SHULKER.get()) {
            if (entity instanceof Player _player && !_player.getCooldowns().isOnCooldown(KlvModItems.ESPADA_SHULKER.get())) {
                
                // Crear el proyectil de shulker
                ShulkerBullet shulkerBullet = new ShulkerBullet(EntityType.SHULKER_BULLET, world);

                // Obtener la dirección del jugador y posicionar el proyectil frente a él
                Vec3 lookDirection = _player.getLookAngle(); // Vector de la dirección a la que mira el jugador
                Vec3 spawnPosition = _player.position().add(lookDirection.scale(1.5)); // Posición frente al jugador

                shulkerBullet.moveTo(spawnPosition.x, spawnPosition.y + _player.getEyeHeight(), spawnPosition.z);

                // Ajustar la fuerza y dirección del proyectil
                float velocity = 2.5F; // Velocidad del proyectil
                float inaccuracy = 0.0F; // Precisión del proyectil
                shulkerBullet.shoot(lookDirection.x, lookDirection.y, lookDirection.z, velocity, inaccuracy);

                // Añadir el proyectil al mundo
                world.addFreshEntity(shulkerBullet);

                // Reproducir el sonido cuando el proyectil es lanzado
                world.playSound(null, _player.blockPosition(), SoundEvents.SHULKER_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);

                // Establecer cooldown de 3 segundos
                _player.getCooldowns().addCooldown(KlvModItems.ESPADA_SHULKER.get(), 60); // 3 segundos * 20 ticks/segundo
            }
        }
    }
}
