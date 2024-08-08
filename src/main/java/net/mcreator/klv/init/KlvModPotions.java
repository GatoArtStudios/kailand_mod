
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.klv.KlvMod;

public class KlvModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, KlvMod.MODID);
	public static final RegistryObject<Potion> POCION_DE_REGRESO = REGISTRY.register("pocion_de_regreso", () -> new Potion(new MobEffectInstance(KlvModMobEffects.TELEPORT_EFFECT.get(), 1, 0, true, true)));
}
