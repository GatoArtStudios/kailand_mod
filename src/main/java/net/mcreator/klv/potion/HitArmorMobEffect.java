package net.mcreator.klv.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class HitArmorMobEffect extends MobEffect {
    public HitArmorMobEffect() {
        super(MobEffectCategory.NEUTRAL, -6750055);
    }

    public String m_19481_() {
        return "effect.klv.hit_armor";
    }

    public boolean m_6584_(int duration, int amplifier) {
        return true;
    }
}
