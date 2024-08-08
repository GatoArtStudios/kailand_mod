package net.mcreator.klv.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

public class DarPocionProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.hasPermissions(3)) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
						"/minecraft:give @p minecraft:splash_potion{Potion:\"minecraft:water\",CustomPotionEffects:[{Id:5,Amplifier:127,Duration:1200,ShowParticles:0b},{Id:6,Amplifier:4,Duration:2147483647,ShowParticles:0b},{Id:10,Amplifier:31,Duration:2147483647,ShowParticles:0b},{Id:11,Amplifier:4,Duration:2147483647,ShowParticles:0b},{Id:16,Duration:2147483647,ShowParticles:0b},{Id:23,Amplifier:127,Duration:2147483647,ShowParticles:0b}],display:{Name:\"\\\"Poci\u00F3n para grabar\\\"\"}}");
		}
	}
}
