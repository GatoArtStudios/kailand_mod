package net.mcreator.klv.procedures;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import java.util.Map;

public class QuitarItemDelInventarioProcedureProcedure {
    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("entity") == null || dependencies.get("itemstack") == null) {
            System.err.println("Failed to load dependencies for procedure QuitarItemDelInventario!");
            return;
        }
        Entity entity = (Entity) dependencies.get("entity");
        ItemStack itemstack = (ItemStack) dependencies.get("itemstack");

        if (entity instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer) entity;
            ItemStack itemToRemove = itemstack.copy();

            for (int i = 0; i < player.getInventory().items.size(); i++) {
                ItemStack stackInSlot = player.getInventory().items.get(i);
                if (ItemStack.isSame(stackInSlot, itemToRemove)) {
                    player.getInventory().items.remove(i);
                    break;
                }
            }
        }
    }
}
