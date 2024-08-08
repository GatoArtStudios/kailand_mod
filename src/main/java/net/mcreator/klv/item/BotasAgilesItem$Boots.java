package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.BotasAgilesAlTenerPuestasLasBotasProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class BotasAgilesItem$Boots extends BotasAgilesItem {
    public BotasAgilesItem$Boots() {
        super(EquipmentSlot.FEET, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL));
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("Estas botas otorgan doble salto y aumentan la velocidad del jugador"));
    }

    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return "klv:textures/models/armor/redarmor_layer_1.png";
    }

    public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
        BotasAgilesAlTenerPuestasLasBotasProcedure.execute(entity);
    }
}
