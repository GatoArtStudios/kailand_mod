package net.mcreator.klv.item;

import net.mcreator.klv.init.KlvModTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class EsenciaDeRagnarokItem extends Item {
    public EsenciaDeRagnarokItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41487_(1).m_41497_(Rarity.RARE));
    }

    @OnlyIn(Dist.CLIENT)
    public boolean m_5812_(ItemStack itemstack) {
        return true;
    }
}
