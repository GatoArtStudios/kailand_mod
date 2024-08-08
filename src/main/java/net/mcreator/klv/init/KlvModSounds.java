
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.klv.KlvMod;

public class KlvModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, KlvMod.MODID);
	public static final RegistryObject<SoundEvent> KATANASOUND = REGISTRY.register("katanasound", () -> new SoundEvent(new ResourceLocation("klv", "katanasound")));
	public static final RegistryObject<SoundEvent> LANZARMJONLIR = REGISTRY.register("lanzarmjonlir", () -> new SoundEvent(new ResourceLocation("klv", "lanzarmjonlir")));
	public static final RegistryObject<SoundEvent> LANZARMARTILLOTHOR = REGISTRY.register("lanzarmartillothor", () -> new SoundEvent(new ResourceLocation("klv", "lanzarmartillothor")));
	public static final RegistryObject<SoundEvent> IMPACTOMJONLIR1 = REGISTRY.register("impactomjonlir1", () -> new SoundEvent(new ResourceLocation("klv", "impactomjonlir1")));
	public static final RegistryObject<SoundEvent> MJONLIRPIERCESOUND1 = REGISTRY.register("mjonlirpiercesound1", () -> new SoundEvent(new ResourceLocation("klv", "mjonlirpiercesound1")));
}
