package net.mcreator.klv.potion;

import net.mcreator.klv.procedures.RandomDirectionChangeProcedure;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class RandomDirectionMobEffect extends MobEffect {
    public RandomDirectionMobEffect() {
        super(MobEffectCategory.HARMFUL, 5149489); // Configura según sea necesario
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        RandomDirectionChangeProcedure.execute(entity);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
