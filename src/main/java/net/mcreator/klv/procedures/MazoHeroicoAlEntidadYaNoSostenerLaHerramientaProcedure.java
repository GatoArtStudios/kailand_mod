package net.mcreator.klv.procedures;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.ItemStack;

import net.mcreator.klv.init.KlvModEnchantments;

import java.util.Map;

public class MazoHeroicoAlEntidadYaNoSostenerLaHerramientaProcedure {
	public static void execute(ItemStack itemstack) {
		if (EnchantmentHelper.getItemEnchantmentLevel(KlvModEnchantments.IGNORACION_DE_ARMADURA.get(), itemstack) != 0) {
			{
				Map<Enchantment, Integer> _enchantments = EnchantmentHelper.getEnchantments(itemstack);
				if (_enchantments.containsKey(KlvModEnchantments.IGNORACION_DE_ARMADURA.get())) {
					_enchantments.remove(KlvModEnchantments.IGNORACION_DE_ARMADURA.get());
					EnchantmentHelper.setEnchantments(_enchantments, itemstack);
				}
			}
		}
	}
}
