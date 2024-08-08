package net.mcreator.klv.potion;

import net.mcreator.klv.procedures.PocionTeleportProcedure;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class TeleportEffectMobEffect extends MobEffect {
    public TeleportEffectMobEffect() {
        super(MobEffectCategory.NEUTRAL, -6749953);
    }

    public String m_19481_() {
        return "effect.klv.teleport_effect";
    }

    public void m_6385_(LivingEntity entity, AttributeMap attributeMap, int amplifier) {
        PocionTeleportProcedure.execute(entity.f_19853_, entity);
    }

    public boolean m_6584_(int duration, int amplifier) {
        return true;
    }
}
