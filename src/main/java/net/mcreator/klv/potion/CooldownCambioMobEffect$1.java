package net.mcreator.klv.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;

class CooldownCambioMobEffect$1 implements IClientMobEffectExtensions {
    CooldownCambioMobEffect$1(CooldownCambioMobEffect this$0) {
        this.this$0 = this$0;
    }

    public boolean isVisibleInGui(MobEffectInstance effect) {
        return false;
    }
}
