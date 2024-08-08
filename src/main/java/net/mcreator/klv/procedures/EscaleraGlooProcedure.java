package net.mcreator.klv.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.Timer;
import java.util.TimerTask;

public class EscaleraGlooProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;

        if (!(entity instanceof Player)) {
            return;
        }

        Player player = (Player) entity;
        Vec3 lookVec = player.getLookAngle();

        // Determinar la dirección en la que está mirando el jugador (west o east)
        boolean facingWest = lookVec.x < 0;

        // Coordenadas iniciales
        double startX = x;
        double startY = y;
        double startZ = z;

        // Ajustar coordenadas según la dirección del jugador
        if (facingWest) {
            startX -= 1; // Mover la escalera un bloque hacia el oeste
        } else {
            startX += 1; // Mover la escalera un bloque hacia el este
        }

        // Crear la escalera de end stone
        for (int i = 0; i < 5; i++) { // Escalera de 5 niveles
            if (world.getBlockState(new BlockPos(startX + i, startY + i, startZ)).getBlock() == Blocks.AIR) {
                world.setBlock(new BlockPos(startX + i, startY + i, startZ), Blocks.END_STONE.defaultBlockState(), 3);
            }
        }

        // Variables finales efectivas para el TimerTask
        final double finalStartX = startX;
        final double finalStartY = startY;
        final double finalStartZ = startZ;

        // Esperar 30 segundos (600 ticks) antes de borrar la escalera
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // Borrar la escalera de end stone
                for (int i = 0; i < 5; i++) { // Escalera de 5 niveles
                    if (world.getBlockState(new BlockPos(finalStartX + i, finalStartY + i, finalStartZ)).getBlock() == Blocks.END_STONE) {
                        world.setBlock(new BlockPos(finalStartX + i, finalStartY + i, finalStartZ), Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }, 30000); // 30000 milisegundos = 30 segundos
    }
}
