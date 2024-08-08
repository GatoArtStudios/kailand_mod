package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.GemaHeladaCuandoSeHaceClicConElBotonDerechoProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class GemaHeladaItem extends Item {
    public GemaHeladaItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41487_(1).m_41497_(Rarity.EPIC));
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("Esta gema congela a las entidades cercanas"));
        list.add(Component.m_237113_("inmoviliz\u00e1ndolas temporalmente"));
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        ItemStack itemstack = (ItemStack)ar.m_19095_();
        double x = entity.m_20185_();
        double y = entity.m_20186_();
        double z = entity.m_20189_();
        GemaHeladaCuandoSeHaceClicConElBotonDerechoProcedure.execute(world, x, y, z, entity);
        return ar;
    }
}
