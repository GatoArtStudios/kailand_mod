// Source code is decompiled from a .class file using FernFlower decompiler.
package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.BastonElementalProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BaculoElementalItem extends Item {
    public BaculoElementalItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41487_(1).m_41486_().m_41497_(Rarity.EPIC));
    }

    @OnlyIn(Dist.CLIENT)
    public boolean m_5812_(ItemStack itemstack) {
        return true;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        ItemStack itemstack = (ItemStack)ar.m_19095_();
        double x = entity.m_20185_();
        double y = entity.m_20186_();
        double z = entity.m_20189_();
        BastonElementalProcedure.execute(world, x, y, z, entity, itemstack);
        return ar;
    }

    @OnlyIn(Dist.CLIENT)
    public void m_7373_(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.m_237113_("\u00a77Un b\u00e1culo m\u00e1gico que alterna entre tres poderosas habilidades."));
        tooltip.add(Component.m_237113_("\u00a77Pulsa agachado y clic derecho para cambiar de habilidad."));
        tooltip.add(Component.m_237113_("\u00a7a\u00c1rea de Regeneraci\u00f3n: \u00a77Crea un c\u00edrculo curativo que otorga regeneraci\u00f3n II durante 7 segundos."));
        tooltip.add(Component.m_237113_("\u00a7aCadenas de Luz: \u00a77Lanza un rayo que congela a los enemigos durante 2 segundos."));
        tooltip.add(Component.m_237113_("\u00a7aCambio: \u00a77Intercambia posiciones con la entidad apuntada dentro de un radio de 15 bloques."));
    }
}
