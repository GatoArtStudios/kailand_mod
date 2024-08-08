
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.enchantment.Enchantment;

import net.mcreator.klv.enchantment.RecargarapidaEnchantment;
import net.mcreator.klv.enchantment.IgnoracionDeArmaduraEnchantment;
import net.mcreator.klv.enchantment.DobleSaltoEncantamientoEnchantment;
import net.mcreator.klv.KlvMod;

public class KlvModEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, KlvMod.MODID);
	public static final RegistryObject<Enchantment> IGNORACION_DE_ARMADURA = REGISTRY.register("ignoracion_de_armadura", () -> new IgnoracionDeArmaduraEnchantment());
	public static final RegistryObject<Enchantment> DOBLE_SALTO_ENCANTAMIENTO = REGISTRY.register("doble_salto_encantamiento", () -> new DobleSaltoEncantamientoEnchantment());
	public static final RegistryObject<Enchantment> RECARGARAPIDA = REGISTRY.register("recargarapida", () -> new RecargarapidaEnchantment());
}
