package net.mcreator.klv.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public abstract class BotasAgilesItem extends ArmorItem {
    public BotasAgilesItem(EquipmentSlot slot, Item.Properties properties) {
        super(new 1(), slot, properties);
    }
}
