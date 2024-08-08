package net.mcreator.klv.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public abstract class CascoDeWardenItem extends ArmorItem {
    public CascoDeWardenItem(EquipmentSlot slot, Item.Properties properties) {
        super(new 1(), slot, properties);
    }
}
