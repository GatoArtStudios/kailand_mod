
package net.mcreator.klv.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

import net.mcreator.klv.init.KlvModTabs;

public class MonedaDeOroItem extends Item {
	public MonedaDeOroItem() {
		super(new Item.Properties().tab(KlvModTabs.TAB_KL).stacksTo(64).rarity(Rarity.RARE));
	}
}
