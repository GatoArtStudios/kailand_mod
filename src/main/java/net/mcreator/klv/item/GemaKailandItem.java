package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.ParedGloo2Procedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GemaKailandItem extends Item {
    public GemaKailandItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41487_(1).m_41486_().m_41497_(Rarity.EPIC));
    }

    @OnlyIn(Dist.CLIENT)
    public boolean m_5812_(ItemStack itemstack) {
        return true;
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("Esta gema permite al jugador colocar una pared temporal para cubrirse"));
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        ItemStack itemstack = (ItemStack)ar.m_19095_();
        double x = entity.m_20185_();
        double y = entity.m_20186_();
        double z = entity.m_20189_();
        ParedGloo2Procedure.execute(world, x, y, z, entity, itemstack);
        return ar;
    }
}
