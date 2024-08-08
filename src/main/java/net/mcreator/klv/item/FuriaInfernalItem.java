package net.mcreator.klv.item;

import java.util.List;
import javax.annotation.Nullable;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.FuriaInfernalProcedureProcedure;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FuriaInfernalItem extends SwordItem {
    public FuriaInfernalItem() {
        super(new 1(), 3, -3.0F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41486_());
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        if (!world.f_46443_) {
            FuriaInfernalProcedureProcedure.execute(world, entity);
            entity.m_36335_().m_41524_(this, 700);
            ItemStack itemstack = entity.m_21120_(hand);
            itemstack.m_41622_(50, entity, (e) -> {
                e.m_21190_(hand);
            });
        }

        return ar;
    }

    public void m_7373_(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.m_7373_(stack, world, tooltip, flag);
        tooltip.add(Component.m_237113_("Al hacer clic derecho, se genera una pared temporal de fuego que empuja a los enemigos").m_130940_(ChatFormatting.GRAY));
    }
}
