
package net.mcreator.klv.potion;

import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

import net.mcreator.klv.procedures.PocionTeleportProcedure;

public class TeleportEffectMobEffect extends MobEffect {
	public TeleportEffectMobEffect() {
		super(MobEffectCategory.NEUTRAL, -6749953);
	}

	@Override
	public String getDescriptionId() {
		return "effect.klv.teleport_effect";
	}

	@Override
	public void addAttributeModifiers(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
		PocionTeleportProcedure.execute(entity.level, entity);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
