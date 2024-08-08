package net.mcreator.klv.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class AdminRayosProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.hasPermissions(3)) {
			Rayos10ProcedureProcedure.execute(world, x, y, z);
		}
	}
}
