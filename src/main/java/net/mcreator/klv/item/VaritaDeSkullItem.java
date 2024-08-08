
package net.mcreator.klv.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;

import net.mcreator.klv.procedures.SkullRayoProcedureProcedure;
import net.mcreator.klv.init.KlvModTabs;

import java.util.List;

public class VaritaDeSkullItem extends Item {
	public VaritaDeSkullItem() {
		super(new Item.Properties().tab(KlvModTabs.TAB_KL).stacksTo(1).rarity(Rarity.RARE));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("\u00A77Esta varita lanza un rayo s\u00F3nico a los enemigos"));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		SkullRayoProcedureProcedure.execute(world, x, y, z, entity, itemstack);
		return ar;
	}
}
