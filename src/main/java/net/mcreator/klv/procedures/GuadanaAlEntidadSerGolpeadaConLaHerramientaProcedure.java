package net.mcreator.klv.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class GuadanaAlEntidadSerGolpeadaConLaHerramientaProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (Math.random() > 0.2) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 2, false, false));
		}
	}
}
