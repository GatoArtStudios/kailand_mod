package net.mcreator.klv.procedures;

import net.minecraft.world.entity.Entity;

public class BotasWardenProcedureProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity.isShiftKeyDown()) {
			entity.clearFire();
		}
	}
}
