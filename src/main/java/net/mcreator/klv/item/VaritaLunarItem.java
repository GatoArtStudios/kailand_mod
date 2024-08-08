package net.mcreator.klv.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.LunarRayProcedure;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class VaritaLunarItem extends Item {
    public VaritaLunarItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41503_(100).m_41486_().m_41497_(Rarity.RARE));
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        ItemStack itemstack = (ItemStack)ar.m_19095_();
        double x = entity.m_20185_();
        double y = entity.m_20186_();
        double z = entity.m_20189_();
        Map<String, Object> dependencies = new HashMap();
        dependencies.put("entity", entity);
        dependencies.put("world", world);
        LunarRayProcedure.executeProcedure(dependencies);
        return ar;
    }

    public void m_7373_(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.m_7373_(stack, world, tooltip, flag);
        tooltip.add(Component.m_237113_("Esta varita lanza rayos y, cuando te agachas, tiene una habilidad m\u00e1s poderosa, pero solo se puede utilizar por la noche").m_130940_(ChatFormatting.GRAY));
    }
}
