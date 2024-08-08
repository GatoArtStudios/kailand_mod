package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.RobaVidaProcedureProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class EspadaVampiricaItem extends SwordItem {
    public EspadaVampiricaItem() {
        super(new 1(), 3, -2.5F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41486_());
    }

    public boolean m_7579_(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.m_7579_(itemstack, entity, sourceentity);
        RobaVidaProcedureProcedure.execute(entity.f_19853_, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, sourceentity);
        return retval;
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("\u00a77Esta espada tiene una m\u00ednima posibilidad de otorgar medio coraz\u00f3n de vida al jugador al golpear"));
    }
}
