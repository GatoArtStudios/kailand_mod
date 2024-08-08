package net.mcreator.klv.item;

import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.MazoHeroicoAlEntidadSerGolpeadaConLaHerramientaProcedure;
import net.mcreator.klv.procedures.MazoHeroicoCuandoSeHaceClicConElBotonDerechoProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MazoHeroicoItem extends AxeItem {
    public MazoHeroicoItem() {
        super(new 1(), 1.0F, -3.2F, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41486_().m_41497_(Rarity.EPIC));
    }

    public boolean m_7579_(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.m_7579_(itemstack, entity, sourceentity);
        MazoHeroicoAlEntidadSerGolpeadaConLaHerramientaProcedure.execute(entity, sourceentity, itemstack);
        return retval;
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.m_7203_(world, entity, hand);
        MazoHeroicoCuandoSeHaceClicConElBotonDerechoProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity, (ItemStack)ar.m_19095_());
        return ar;
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("\u00a77al usar clic derecho otorga una habilidad que al momento de golpear a una entidad ignorara totalmente su armadura"));
    }
}
