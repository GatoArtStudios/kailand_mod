package net.mcreator.klv.init;

import net.mcreator.klv.block.BloqueVeloxBlock;
import net.mcreator.klv.block.MineralMisticoBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KlvModBlocks {
    public static final DeferredRegister<Block> REGISTRY;
    public static final RegistryObject<Block> MINERAL_MISTICO;
    public static final RegistryObject<Block> BLOQUE_VELOX;

    public KlvModBlocks() {
    }

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, "klv");
        MINERAL_MISTICO = REGISTRY.register("mineral_mistico", () -> {
            return new MineralMisticoBlock();
        });
        BLOQUE_VELOX = REGISTRY.register("bloque_velox", () -> {
            return new BloqueVeloxBlock();
        });
    }
}
