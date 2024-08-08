package net.mcreator.klv.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class SensorDeEntidadesMobEffect extends MobEffect {
    public SensorDeEntidadesMobEffect() {
        super(MobEffectCategory.BENEFICIAL, -16724788);
    }

    public String m_19481_() {
        return "effect.klv.sensor_de_entidades";
    }

    public boolean m_6584_(int duration, int amplifier) {
        return true;
    }
}
