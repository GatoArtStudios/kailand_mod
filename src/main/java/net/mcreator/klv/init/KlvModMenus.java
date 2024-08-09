
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.mcreator.klv.world.inventory.AdminGuiMenu;
import net.mcreator.klv.world.inventory.GUIDeseosMenu;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import net.mcreator.klv.KlvMod;

public class KlvModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, KlvMod.MODID);
	public static final RegistryObject<MenuType<GUIDeseosMenu>> GUI_DESEOS = REGISTRY.register("gui_deseos", () -> IForgeMenuType.create(GUIDeseosMenu::new));
	public static final RegistryObject<MenuType<AdminGuiMenu>> ADMIN_GUI = REGISTRY.register("admin_gui", () -> IForgeMenuType.create(AdminGuiMenu::new));
}
