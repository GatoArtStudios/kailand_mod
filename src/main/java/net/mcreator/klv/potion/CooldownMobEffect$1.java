package net.mcreator.klv.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

class CooldownMobEffect$1 implements IClientMobEffectExtensions {
    CooldownMobEffect$1(CooldownMobEffect this$0) {
        this.this$0 = this$0;
    }

    public boolean isVisibleInGui(MobEffectInstance effect) {
        return false;
    }
}
