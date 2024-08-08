package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.HachaLeviatanProcedureProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class HachaLeviatanItem extends AxeItem {
    public HachaLeviatanItem() {
        super(new 1(), 1.0F, -3.0F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41486_());
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        HachaLeviatanProcedureProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, (ItemStack)ar.m_19095_());
        return ar;
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("\u00a77Esta hacha lanza rayos a enemigos cercanos y otorga efectos positivos al jugador durante unos segundos"));
    }

    @OnlyIn(Dist.CLIENT)
    public boolean m_5812_(ItemStack itemstack) {
        return true;
    }
}
