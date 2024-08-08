package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.MartilloDeGravedadProcedureProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class MartilloDeGravedadItem extends AxeItem {
    private static final int COOLDOWN_PRINCIPAL = 400;
    private static final int COOLDOWN_SECUNDARIO = 400;

    public MartilloDeGravedadItem() {
        super(new 1(), 1.0F, -3.2F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41486_().m_41497_(Rarity.EPIC));
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        ItemStack itemstack = entity.m_21120_(hand);
        if (!world.f_46443_) {
            if (entity.m_6047_()) {
                if (entity.m_36335_().m_41519_(this)) {
                    return InteractionResultHolder.m_19100_(itemstack);
                } else {
                    MartilloDeGravedadProcedureProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, true);
                    entity.m_36335_().m_41524_(this, 400);
                    return InteractionResultHolder.m_19090_(itemstack);
                }
            } else if (entity.m_36335_().m_41519_(this)) {
                return InteractionResultHolder.m_19100_(itemstack);
            } else {
                MartilloDeGravedadProcedureProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, false);
                entity.m_36335_().m_41524_(this, 400);
                return InteractionResultHolder.m_19090_(itemstack);
            }
        } else {
            return InteractionResultHolder.m_19098_(itemstack);
        }
    }

    public UseAnim m_6164_(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.m_237113_("\u00a75Principal: \u00a77Atrae y da\u00f1a entidades cercanas"));
        tooltip.add(Component.m_237113_("\u00a75Secundaria: \u00a77Empuja a las entidades cercanas"));
    }
}
