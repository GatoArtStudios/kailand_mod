package net.mcreator.klv.procedures;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class BotasWardenProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide()) {
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 70, 0, false, false));
		}
		if (entity.isShiftKeyDown()) {
			entity.clearFire();
		}
	}
}
