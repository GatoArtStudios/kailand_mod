package net.mcreator.klv.item;

import java.util.List;
import javax.annotation.Nullable;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.MisticaSwordEffectProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class EspadaMisticaItem extends SwordItem {
    public EspadaMisticaItem() {
        super(new 1(), 3, -2.7F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41486_());
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        if (!world.f_46443_) {
            MisticaSwordEffectProcedure.execute(entity);
            entity.m_36335_().m_41524_(this, 900);
        }

        return ar;
    }

    public void m_7373_(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.m_237113_("Este \u00edtem, cuando se hace clic derecho, gira a los jugadores en un radio de 8x8 bloques."));
    }
}
