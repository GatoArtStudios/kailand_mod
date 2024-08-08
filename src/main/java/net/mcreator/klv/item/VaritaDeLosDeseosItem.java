
package net.mcreator.klv.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.klv.procedures.AbrirGUIDeseosProcedure;
import net.mcreator.klv.init.KlvModTabs;

public class VaritaDeLosDeseosItem extends Item {
	public VaritaDeLosDeseosItem() {
		super(new Item.Properties().tab(KlvModTabs.TAB_KL).durability(350).fireResistant().rarity(Rarity.EPIC));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		AbrirGUIDeseosProcedure.execute(world, x, y, z, entity);
		return ar;
	}
}
