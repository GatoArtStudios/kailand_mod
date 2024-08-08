package net.mcreator.klv.item;

import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.CascoDeWardenAlTenerPuestoElCascoProcedure;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CascoDeWardenItem$Helmet extends CascoDeWardenItem {
    public CascoDeWardenItem$Helmet() {
        super(EquipmentSlot.HEAD, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "klv:textures/models/armor/wardengod__layer_1.png";
    }

    public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
        CascoDeWardenAlTenerPuestoElCascoProcedure.execute(entity);
    }
}
