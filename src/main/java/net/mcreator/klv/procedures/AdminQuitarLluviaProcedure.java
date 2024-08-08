package net.mcreator.klv.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.klv.KlvMod;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class AdminQuitarLluviaProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity.hasPermissions(3)) {
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, (x + 5), (y + 6), (z - 5), 0, Explosion.BlockInteraction.DESTROY);
			if (world instanceof Level _level && !_level.isClientSide())
				_level.explode(null, (x - 5), (y + 4), (z + 5), 0, Explosion.BlockInteraction.DESTROY);
			Rayos10ProcedureProcedure.execute(world, x, y, z);
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 60, 3, false, false));
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 130, 3, false, false));
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 3, false, false));
			KlvMod.queueServerWork(50, () -> {
				Rayos10ProcedureProcedure.execute(world, x, y, z);
			});
			entity.clearFire();
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
						.collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if (!(entityiterator == entity)) {
						if (entityiterator instanceof LivingEntity _entity && !_entity.level.isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 40, 4, false, false));
					}
				}
			}
		}
	}
}
