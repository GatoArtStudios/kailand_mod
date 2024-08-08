
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import net.mcreator.klv.client.gui.GUIDeseosScreen;
import net.mcreator.klv.client.gui.AdminGuiScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KlvModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(KlvModMenus.GUI_DESEOS.get(), GUIDeseosScreen::new);
			MenuScreens.register(KlvModMenus.ADMIN_GUI.get(), AdminGuiScreen::new);
		});
	}
}
