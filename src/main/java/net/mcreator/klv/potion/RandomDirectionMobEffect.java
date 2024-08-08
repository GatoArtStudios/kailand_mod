package net.mcreator.klv.potion;

import net.mcreator.klv.procedures.RandomDirectionChangeProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RandomDirectionMobEffect extends MobEffect {
    public RandomDirectionMobEffect() {
        super(MobEffectCategory.HARMFUL, 5149489);
    }

    public void m_6742_(LivingEntity entity, int amplifier) {
        RandomDirectionChangeProcedure.execute(entity);
    }

    public boolean m_6584_(int duration, int amplifier) {
        return true;
    }
}
