
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

import net.mcreator.klv.world.features.ores.MineralMisticoFeature;
import net.mcreator.klv.world.features.ores.BloqueVeloxFeature;
import net.mcreator.klv.KlvMod;

@Mod.EventBusSubscriber
public class KlvModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, KlvMod.MODID);
	public static final RegistryObject<Feature<?>> MINERAL_MISTICO = REGISTRY.register("mineral_mistico", MineralMisticoFeature::feature);
	public static final RegistryObject<Feature<?>> BLOQUE_VELOX = REGISTRY.register("bloque_velox", BloqueVeloxFeature::feature);
}
