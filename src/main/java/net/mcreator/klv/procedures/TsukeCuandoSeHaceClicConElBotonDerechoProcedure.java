package net.mcreator.klv.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

import net.mcreator.klv.KlvMod;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class TsukeCuandoSeHaceClicConElBotonDerechoProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		entity.getPersistentData().putDouble("TotalTarget", 0);
		entity.getPersistentData().putDouble("CurrentTarget", 0);
		entity.getPersistentData().putDouble("Timer", 0);
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (!(entityiterator == entity || entity instanceof ItemEntity) && entity instanceof LivingEntity) {
					entity.getPersistentData().putDouble("TotalTarget", (entity.getPersistentData().getDouble("TotalTarget") + 1));
					entityiterator.getPersistentData().putDouble("TargetQueue", (entity.getPersistentData().getDouble("TotalTarget")));
				}
			}
		}
		for (int index0 = 0; index0 < (int) entity.getPersistentData().getDouble("TotalTarget"); index0++) {
			entity.getPersistentData().putDouble("Timer", (entity.getPersistentData().getDouble("Timer") + 2));
			KlvMod.queueServerWork((int) entity.getPersistentData().getDouble("Timer"), () -> {
				entity.getPersistentData().putDouble("CurrentTarget", (entity.getPersistentData().getDouble("CurrentTarget") + 1));
				if (entity instanceof LivingEntity _entity)
					_entity.swing(InteractionHand.MAIN_HAND, true);
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (entity.getPersistentData().getDouble("CurrentTarget") == entityiterator.getPersistentData().getDouble("TargetQueue")) {
							if (world instanceof ServerLevel _level)
								_level.sendParticles(ParticleTypes.CRIT, x, (y + 1), z, 15, 0, 0, 0, 1.5);
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, new BlockPos(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("klv:katanasound")), SoundSource.NEUTRAL, (float) 0.5, (float) 1.2);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("klv:katanasound")), SoundSource.NEUTRAL, (float) 0.5, (float) 1.2, false);
								}
							}
							entityiterator.hurt(DamageSource.GENERIC, 8);
							world.addParticle(ParticleTypes.SWEEP_ATTACK, x, (y + 1.65), z, 0, 0, 0);
							if (entity instanceof Player _player)
								_player.getCooldowns().addCooldown(itemstack.getItem(), 350);
							{
								ItemStack _ist = itemstack;
								if (_ist.hurt(6, RandomSource.create(), null)) {
									_ist.shrink(1);
									_ist.setDamageValue(0);
								}
							}
						}
					}
				}
			});
		}
	}
}
