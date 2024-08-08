package net.mcreator.klv.item;

import java.util.HashMap;
import java.util.Map;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.EspadaShulkerProcedureProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;

public class EspadaShulkerItem extends SwordItem {
    public EspadaShulkerItem() {
        super(new 1(), 3, -2.6F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL));
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        double x = entity.m_20185_();
        double y = entity.m_20186_();
        double z = entity.m_20189_();
        Map<String, Object> dependencies = new HashMap();
        dependencies.put("entity", entity);
        dependencies.put("x", x);
        dependencies.put("y", y);
        dependencies.put("z", z);
        dependencies.put("world", world);
        EspadaShulkerProcedureProcedure.execute(dependencies);
        return ar;
    }
}
