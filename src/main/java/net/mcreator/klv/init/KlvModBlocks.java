
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import net.mcreator.klv.block.MineralMisticoBlock;
import net.mcreator.klv.block.BloqueVeloxBlock;
import net.mcreator.klv.KlvMod;

public class KlvModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, KlvMod.MODID);
	public static final RegistryObject<Block> MINERAL_MISTICO = REGISTRY.register("mineral_mistico", () -> new MineralMisticoBlock());
	public static final RegistryObject<Block> BLOQUE_VELOX = REGISTRY.register("bloque_velox", () -> new BloqueVeloxBlock());
}
