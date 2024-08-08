package net.mcreator.klv.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.klv.network.KlvModVariables;

public class PocionTeleportProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (KlvModVariables.MapVariables.get(world).PlayerSpawnLocationX == 0 && KlvModVariables.MapVariables.get(world).PlayerSpawnLocationY == 0 && KlvModVariables.MapVariables.get(world).PlayerSpawnLocationZ == 0) {
			{
				Entity _ent = entity;
				_ent.teleportTo((world.getLevelData().getXSpawn()), (world.getLevelData().getYSpawn()), (world.getLevelData().getZSpawn()));
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport((world.getLevelData().getXSpawn()), (world.getLevelData().getYSpawn()), (world.getLevelData().getZSpawn()), _ent.getYRot(), _ent.getXRot());
			}
		} else {
			{
				Entity _ent = entity;
				_ent.teleportTo(KlvModVariables.MapVariables.get(world).PlayerSpawnLocationX, KlvModVariables.MapVariables.get(world).PlayerSpawnLocationY, KlvModVariables.MapVariables.get(world).PlayerSpawnLocationZ);
				if (_ent instanceof ServerPlayer _serverPlayer)
					_serverPlayer.connection.teleport(KlvModVariables.MapVariables.get(world).PlayerSpawnLocationX, KlvModVariables.MapVariables.get(world).PlayerSpawnLocationY, KlvModVariables.MapVariables.get(world).PlayerSpawnLocationZ,
							_ent.getYRot(), _ent.getXRot());
			}
		}
	}
}
