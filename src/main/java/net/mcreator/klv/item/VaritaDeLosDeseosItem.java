package net.mcreator.klv.item;

import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.AbrirGUIDeseosProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public class VaritaDeLosDeseosItem extends Item {
    public VaritaDeLosDeseosItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41503_(350).m_41486_().m_41497_(Rarity.EPIC));
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        ItemStack itemstack = (ItemStack)ar.m_19095_();
        double x = entity.m_20185_();
        double y = entity.m_20186_();
        double z = entity.m_20189_();
        AbrirGUIDeseosProcedure.execute(world, x, y, z, entity);
        return ar;
    }
}
