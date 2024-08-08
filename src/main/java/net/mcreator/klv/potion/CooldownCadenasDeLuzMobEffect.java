package net.mcreator.klv.potion;

import java.util.function.Consumer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

public class CooldownCadenasDeLuzMobEffect extends MobEffect {
    public CooldownCadenasDeLuzMobEffect() {
        super(MobEffectCategory.HARMFUL, -1);
    }

    public String m_19481_() {
        return "effect.klv.cooldown_cadenas_de_luz";
    }

    public boolean m_6584_(int duration, int amplifier) {
        return true;
    }

    public void initializeClient(Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new 1(this));
    }
}
