
package net.mcreator.klv.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import net.mcreator.klv.init.KlvModTabs;

public class CorazonDelWardenItem extends Item {
	public CorazonDelWardenItem() {
		super(new Item.Properties().tab(KlvModTabs.TAB_KL).stacksTo(1).rarity(Rarity.EPIC));
	}
}
