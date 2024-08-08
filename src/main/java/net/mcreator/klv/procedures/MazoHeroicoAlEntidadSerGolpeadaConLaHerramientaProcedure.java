package net.mcreator.klv.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;

import net.mcreator.klv.init.KlvModMobEffects;

public class MazoHeroicoAlEntidadSerGolpeadaConLaHerramientaProcedure {
    public static void execute(Entity entity, Entity sourceentity, ItemStack itemstack) {
        if (entity == null || sourceentity == null)
            return;

        // Verificar si el jugador que porta el mazo tiene el efecto "HitArmor"
        if (sourceentity instanceof LivingEntity _livEnt && _livEnt.hasEffect(KlvModMobEffects.HIT_ARMOR.get())) {
            // Remover el efecto "HitArmor" del jugador que porta el mazo
            _livEnt.removeEffect(KlvModMobEffects.HIT_ARMOR.get());

            // Si la entidad golpeada es una LivingEntity
            if (entity instanceof LivingEntity _targetEntity) {
                // Reducir directamente la salud de la entidad golpeada en 8 puntos (4 corazones)
                _targetEntity.setHealth(Math.max(0, _targetEntity.getHealth() - 8.0F));
            }

            // Añadir cooldown al jugador
            if (sourceentity instanceof Player _player) {
                _player.getCooldowns().addCooldown(itemstack.getItem(), 1200);
            }

            // Daño al itemstack
            ItemStack _ist = itemstack;
            if (_ist.hurt(1, RandomSource.create(), null)) {
                _ist.shrink(1);
                _ist.setDamageValue(0);
            }
        }
    }
}
