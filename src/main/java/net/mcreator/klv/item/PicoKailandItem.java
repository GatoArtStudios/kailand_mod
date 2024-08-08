package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.AutoFundicionProcedureProcedure;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PicoKailandItem extends PickaxeItem {
    public PicoKailandItem() {
        super(new 1(), 1, -2.8F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41486_());
    }

    public boolean m_6813_(ItemStack itemstack, Level world, BlockState blockstate, BlockPos pos, LivingEntity entity) {
        boolean retval = super.m_6813_(itemstack, world, blockstate, pos, entity);
        AutoFundicionProcedureProcedure.execute(world, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_());
        return retval;
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("\u00a77Este pico funde autom\u00e1ticamente los minerales al romperlos"));
    }

    @OnlyIn(Dist.CLIENT)
    public boolean m_5812_(ItemStack itemstack) {
        return true;
    }
}
