package net.mcreator.klv.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

class CooldownAreaRegenerativaMobEffect$1 implements IClientMobEffectExtensions {
    CooldownAreaRegenerativaMobEffect$1(CooldownAreaRegenerativaMobEffect this$0) {
        this.this$0 = this$0;
    }

    public boolean isVisibleInGui(MobEffectInstance effect) {
        return false;
    }
}
