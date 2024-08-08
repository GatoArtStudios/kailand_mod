
package net.mcreator.klv.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;

public class SensorDeEntidadesMobEffect extends MobEffect {
	public SensorDeEntidadesMobEffect() {
		super(MobEffectCategory.BENEFICIAL, -16724788);
	}

	@Override
	public String getDescriptionId() {
		return "effect.klv.sensor_de_entidades";
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		return true;
	}
}
