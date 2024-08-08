
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class KlvModTabs {
	public static CreativeModeTab TAB_KL;

	public static void load() {
		TAB_KL = new CreativeModeTab("tabkl") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(KlvModItems.MONEDA_ROTA.get());
			}

			@Override
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
