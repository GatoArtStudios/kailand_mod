package net.mcreator.klv.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

public class CascoDeWardenAlTenerPuestoElCascoProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		double Scaling = 0;
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 65, 1, false, false));
	}
}
