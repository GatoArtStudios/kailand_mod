package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.GuadanaAlEntidadSerGolpeadaConLaHerramientaProcedure;
import net.mcreator.klv.procedures.GuadanaProcedureProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class GuadanaItem extends SwordItem {
    public GuadanaItem() {
        super(new 1(), 3, -2.9F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL));
    }

    public boolean m_7579_(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.m_7579_(itemstack, entity, sourceentity);
        GuadanaAlEntidadSerGolpeadaConLaHerramientaProcedure.execute(entity);
        return retval;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        GuadanaProcedureProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, (ItemStack)ar.m_19095_());
        return ar;
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("\u00a77Esta cuchilla hace al jugador invisible durante 45 segundos al activarse"));
    }
}
