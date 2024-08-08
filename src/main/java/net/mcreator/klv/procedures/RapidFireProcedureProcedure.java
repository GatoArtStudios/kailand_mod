package net.mcreator.klv.procedures;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

public class RapidFireProcedureProcedure {
    public static void execute(Entity entity, ItemStack itemstack) {
        if (entity == null || !(entity instanceof Player))
            return;

        System.out.println("RapidFireProcedureProcedure: Procedimiento ejecutado."); // Mensaje de depuración

        Enchantment recargarapidaEnchantment = ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation("klv:recargarapida"));
        if (recargarapidaEnchantment != null) {
            int level = EnchantmentHelper.getItemEnchantmentLevel(recargarapidaEnchantment, itemstack);
            System.out.println("RapidFireProcedureProcedure: Nivel del encantamiento = " + level); // Mensaje de depuración

            if (level > 0) {
                // Ajustar la duración de uso del arco
                int reducedCooldown = Math.max(1, 20 - (level * 5)); // Ajustar el multiplicador según sea necesario
                System.out.println("RapidFireProcedureProcedure: Cooldown reducido = " + reducedCooldown); // Mensaje de depuración

                // Aplicar el cooldown reducido al arco
                ((Player) entity).getCooldowns().addCooldown(itemstack.getItem(), reducedCooldown);
                System.out.println("RapidFireProcedureProcedure: Cooldown aplicado = " + reducedCooldown); // Mensaje de depuración final
            }
        } else {
            System.out.println("RapidFireProcedureProcedure: Encantamiento 'klv:recargarapida' no encontrado."); // Mensaje de depuración
        }
    }
}
