package net.mcreator.klv.procedures;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ParedGloo2Procedure {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;

        BlockPos[] placedBlocks = new BlockPos[15];
        int blockIndex = 0;

        if (entity.getDirection() == Direction.SOUTH) {
            for (int index0 = 0; index0 < 15; index0++) {
                BlockPos pos = getBlockPosForPlacement(x, y, z, index0, Direction.SOUTH);
                if (isReplaceable(world, pos)) {
                    world.setBlock(pos, Blocks.END_STONE.defaultBlockState(), 3);
                    placedBlocks[blockIndex++] = pos;
                }
            }
        } else if (entity.getDirection() == Direction.NORTH) {
            for (int index1 = 0; index1 < 15; index1++) {
                BlockPos pos = getBlockPosForPlacement(x, y, z, index1, Direction.NORTH);
                if (isReplaceable(world, pos)) {
                    world.setBlock(pos, Blocks.END_STONE.defaultBlockState(), 3);
                    placedBlocks[blockIndex++] = pos;
                }
            }
        } else if (entity.getDirection() == Direction.EAST) {
            for (int index2 = 0; index2 < 15; index2++) {
                BlockPos pos = getBlockPosForPlacement(x, y, z, index2, Direction.EAST);
                if (isReplaceable(world, pos)) {
                    world.setBlock(pos, Blocks.END_STONE.defaultBlockState(), 3);
                    placedBlocks[blockIndex++] = pos;
                }
            }
        } else if (entity.getDirection() == Direction.WEST) {
            for (int index3 = 0; index3 < 15; index3++) {
                BlockPos pos = getBlockPosForPlacement(x, y, z, index3, Direction.WEST);
                if (isReplaceable(world, pos)) {
                    world.setBlock(pos, Blocks.END_STONE.defaultBlockState(), 3);
                    placedBlocks[blockIndex++] = pos;
                }
            }
        }

        if (entity instanceof Player _player)
            _player.getCooldowns().addCooldown(itemstack.getItem(), 1400);
        if (world instanceof ServerLevel _level)
            _level.sendParticles(ParticleTypes.DRAGON_BREATH, x, y, z, 195, 3, 3, 3, 0.2);
        if (world instanceof Level _level) {
            if (!_level.isClientSide()) {
                _level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.stone.place")), SoundSource.BLOCKS, 1, 1);
            } else {
                _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.stone.place")), SoundSource.BLOCKS, 1, 1, false);
            }
        }

        // Programar eliminación de bloques después de 30 segundos
        scheduler.schedule(() -> {
            for (BlockPos pos : placedBlocks) {
                if (pos != null && world.getBlockState(pos).getBlock() == Blocks.END_STONE) {
                    if (world instanceof ServerLevel serverWorld) {
                        // Elimina los bloques
                        try {
                            // true si quiere notificar al usuario que los bloques han sido eliminados
                            serverWorld.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                        } catch (Exception e) {
                            // Ignoramos el error.
                        }
                        // Genera particulas de humo
                        serverWorld.sendParticles(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 10, 0.5, 0.5, 0.5, 0.0);
                    }
                }
            }
        }, 30, TimeUnit.SECONDS);
    }

    private static boolean isReplaceable(LevelAccessor world, BlockPos pos) {
        return world.getBlockState(pos).getBlock() == Blocks.AIR ||
               world.getBlockState(pos).getBlock() == Blocks.GRASS ||
               world.getBlockState(pos).getBlock() == Blocks.TALL_GRASS;
    }

    private static BlockPos getBlockPosForPlacement(double x, double y, double z, int index, Direction direction) {
        switch (direction) {
            case SOUTH:
                return switch (index) {
                    case 0 -> new BlockPos(x, y, z + 2);
                    case 1 -> new BlockPos(x - 1, y, z + 2);
                    case 2 -> new BlockPos(x + 1, y, z + 2);
                    case 3 -> new BlockPos(x, y + 1, z + 2);
                    case 4 -> new BlockPos(x + 1, y + 1, z + 2);
                    case 5 -> new BlockPos(x - 1, y + 1, z + 2);
                    case 6 -> new BlockPos(x, y + 2, z + 2);
                    case 7 -> new BlockPos(x + 1, y + 2, z + 2);
                    case 8 -> new BlockPos(x - 1, y + 2, z + 2);
                    case 9 -> new BlockPos(x + 2, y, z + 1);
                    case 10 -> new BlockPos(x - 2, y, z + 1);
                    case 11 -> new BlockPos(x - 2, y + 1, z + 1);
                    case 12 -> new BlockPos(x + 2, y + 1, z + 1);
                    case 13 -> new BlockPos(x + 2, y + 2, z + 1);
                    case 14 -> new BlockPos(x - 2, y + 2, z + 1);
                    default -> new BlockPos(x, y, z);
                };
            case NORTH:
                return switch (index) {
                    case 0 -> new BlockPos(x, y, z - 2);
                    case 1 -> new BlockPos(x + 1, y, z - 2);
                    case 2 -> new BlockPos(x - 1, y, z - 2);
                    case 3 -> new BlockPos(x, y + 1, z - 2);
                    case 4 -> new BlockPos(x + 1, y + 1, z - 2);
                    case 5 -> new BlockPos(x - 1, y + 1, z - 2);
                    case 6 -> new BlockPos(x, y + 2, z - 2);
                    case 7 -> new BlockPos(x + 1, y + 2, z - 2);
                    case 8 -> new BlockPos(x - 1, y + 2, z - 2);
                    case 9 -> new BlockPos(x + 2, y, z - 1);
                    case 10 -> new BlockPos(x - 2, y, z - 1);
                    case 11 -> new BlockPos(x - 2, y + 1, z - 1);
                    case 12 -> new BlockPos(x + 2, y + 1, z - 1);
                    case 13 -> new BlockPos(x + 2, y + 2, z - 1);
                    case 14 -> new BlockPos(x - 2, y + 2, z - 1);
                    default -> new BlockPos(x, y, z);
                };
            case EAST:
                return switch (index) {
                    case 0 -> new BlockPos(x + 2, y, z);
                    case 1 -> new BlockPos(x + 2, y, z + 1);
                    case 2 -> new BlockPos(x + 2, y, z - 1);
                    case 3 -> new BlockPos(x + 2, y + 1, z);
                    case 4 -> new BlockPos(x + 2, y + 1, z + 1);
                    case 5 -> new BlockPos(x + 2, y + 1, z - 1);
                    case 6 -> new BlockPos(x + 2, y + 2, z);
                    case 7 -> new BlockPos(x + 2, y + 2, z + 1);
                    case 8 -> new BlockPos(x + 2, y + 2, z - 1);
                    case 9 -> new BlockPos(x + 1, y, z + 2);
                    case 10 -> new BlockPos(x + 1, y, z - 2);
                    case 11 -> new BlockPos(x + 1, y + 1, z + 2);
                    case 12 -> new BlockPos(x + 1, y + 1, z - 2);
                    case 13 -> new BlockPos(x + 1, y + 2, z + 2);
                    case 14 -> new BlockPos(x + 1, y + 2, z - 2);
                    default -> new BlockPos(x, y, z);
                };
            case WEST:
                return switch (index) {
                    case 0 -> new BlockPos(x - 2, y, z);
                    case 1 -> new BlockPos(x - 2, y, z + 1);
                    case 2 -> new BlockPos(x - 2, y, z - 1);
                    case 3 -> new BlockPos(x - 2, y + 1, z);
                    case 4 -> new BlockPos(x - 2, y + 1, z + 1);
                    case 5 -> new BlockPos(x - 2, y + 1, z - 1);
                    case 6 -> new BlockPos(x - 2, y + 2, z);
                    case 7 -> new BlockPos(x - 2, y + 2, z + 1);
                    case 8 -> new BlockPos(x - 2, y + 2, z - 1);
                    case 9 -> new BlockPos(x - 1, y, z + 2);
                    case 10 -> new BlockPos(x - 1, y, z - 2);
                    case 11 -> new BlockPos(x - 1, y + 1, z + 2);
                    case 12 -> new BlockPos(x - 1, y + 1, z - 2);
                    case 13 -> new BlockPos(x - 1, y + 2, z + 2);
                    case 14 -> new BlockPos(x - 1, y + 2, z - 2);
                    default -> new BlockPos(x, y, z);
                };
            default:
                return new BlockPos(x, y, z);
        }
    }
}
