package net.mcreator.klv.item;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.network.chat.Component;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.klv.procedures.BastonElementalProcedure;  // Asegúrate de que este sea el nombre correcto
import net.mcreator.klv.init.KlvModTabs;

import java.util.List;

public class BaculoElementalItem extends Item {
    public BaculoElementalItem() {
        super(new Item.Properties().tab(KlvModTabs.TAB_KL).stacksTo(1).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        BastonElementalProcedure.execute(world, x, y, z, entity, itemstack);

        return ar;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§7Un báculo mágico que alterna entre tres poderosas habilidades."));
        tooltip.add(Component.literal("§7Pulsa agachado y clic derecho para cambiar de habilidad."));
        tooltip.add(Component.literal("§aÁrea de Regeneración: §7Crea un círculo curativo que otorga regeneración II durante 7 segundos."));
        tooltip.add(Component.literal("§aCadenas de Luz: §7Lanza un rayo que congela a los enemigos durante 2 segundos."));
        tooltip.add(Component.literal("§aCambio: §7Intercambia posiciones con la entidad apuntada dentro de un radio de 15 bloques."));
    }
}
