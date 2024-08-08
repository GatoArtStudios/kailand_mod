package net.mcreator.klv.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.klv.network.KlvModVariables;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class CuandoDasClickCamaProcedure {
	@SubscribeEvent
	public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		if (event.getHand() != event.getEntity().getUsedItemHand())
			return;
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getEntity());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((world.getBlockState(new BlockPos(x, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:beds")))) {
			KlvModVariables.MapVariables.get(world).PlayerSpawnLocationX = entity.getX();
			KlvModVariables.MapVariables.get(world).syncData(world);
			KlvModVariables.MapVariables.get(world).PlayerSpawnLocationY = entity.getY();
			KlvModVariables.MapVariables.get(world).syncData(world);
			KlvModVariables.MapVariables.get(world).PlayerSpawnLocationZ = entity.getZ();
			KlvModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
