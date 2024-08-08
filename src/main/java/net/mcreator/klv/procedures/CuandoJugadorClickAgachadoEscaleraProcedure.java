package net.mcreator.klv.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public class CuandoJugadorClickAgachadoEscaleraProcedure {
    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
        if (entity == null)
            return;

        // Verificar si el jugador está agachado
        if (entity.isShiftKeyDown()) {
            // Llamar al procedimiento de la escalera
            EscaleraGlooProcedure.execute(world, x, y, z, entity, itemstack);
        } else {
            // Llamar al procedimiento de la pared
            ParedGloo2Procedure.execute(world, x, y, z, entity, itemstack);
        }
    }
}
