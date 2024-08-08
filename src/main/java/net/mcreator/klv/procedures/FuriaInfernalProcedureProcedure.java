package net.mcreator.klv.procedures;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FuriaInfernalProcedureProcedure {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void execute(Level world, Player player) {
        if (world == null || player == null) return;

        int x = player.getBlockX();
        int y = player.getBlockY();
        int z = player.getBlockZ();
        Direction direction = player.getDirection();
        Set<BlockPos> blockPositions = new HashSet<>();

        // Desplazar la posición inicial 2 bloques más adelante
        x += direction.getStepX() * 2;
        z += direction.getStepZ() * 2;

        // Colocar la pared de magma blocks
        for (int i = -1; i < 4; i++) { // Altura de 4 bloques (más una fila inferior)
            for (int j = -1; j <= 1; j++) { // Ancho de 3 bloques (centrado en el jugador)
                BlockPos blockPos;
                switch (direction) {
                    case NORTH:
                    case SOUTH:
                        blockPos = new BlockPos(x + j, y + i, z);
                        break;
                    case EAST:
                    case WEST:
                        blockPos = new BlockPos(x, y + i, z + j);
                        break;
                    default:
                        blockPos = new BlockPos(x, y + i, z);
                        break;
                }
                // Solo reemplazar bloques de aire, hierba, hierba alta y flores
                if (world.getBlockState(blockPos).getBlock() == Blocks.AIR ||
                    world.getBlockState(blockPos).getBlock() == Blocks.GRASS ||
                    world.getBlockState(blockPos).getBlock() == Blocks.TALL_GRASS ||
                    world.getBlockState(blockPos).getBlock() == Blocks.DANDELION ||
                    world.getBlockState(blockPos).getBlock() == Blocks.POPPY ||
                    world.getBlockState(blockPos).getBlock() == Blocks.BLUE_ORCHID ||
                    world.getBlockState(blockPos).getBlock() == Blocks.ALLIUM ||
                    world.getBlockState(blockPos).getBlock() == Blocks.AZURE_BLUET ||
                    world.getBlockState(blockPos).getBlock() == Blocks.RED_TULIP ||
                    world.getBlockState(blockPos).getBlock() == Blocks.ORANGE_TULIP ||
                    world.getBlockState(blockPos).getBlock() == Blocks.WHITE_TULIP ||
                    world.getBlockState(blockPos).getBlock() == Blocks.PINK_TULIP ||
                    world.getBlockState(blockPos).getBlock() == Blocks.OXEYE_DAISY ||
                    world.getBlockState(blockPos).getBlock() == Blocks.CORNFLOWER ||
                    world.getBlockState(blockPos).getBlock() == Blocks.LILY_OF_THE_VALLEY ||
                    world.getBlockState(blockPos).getBlock() == Blocks.WITHER_ROSE) {
                    world.setBlock(blockPos, Blocks.MAGMA_BLOCK.defaultBlockState(), 3);
                    blockPositions.add(blockPos);
                }
            }
        }

        // Crear una onda expansiva hacia enfrente del jugador
        double explosionX = x + direction.getStepX() * 2;
        double explosionZ = z + direction.getStepZ() * 2;
        world.explode(player, explosionX, y, explosionZ, 4.0F, Explosion.BlockInteraction.NONE);

        // Programar la eliminación de los bloques después de 25 segundos (500 ticks)
        if (world instanceof ServerLevel) {
            scheduler.schedule(() -> {
                for (BlockPos pos : blockPositions) {
                    world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                }
            }, 25, TimeUnit.SECONDS);
        }
    }
}
