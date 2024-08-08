package net.mcreator.klv.procedures;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.mcreator.klv.init.KlvModMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class BastonElementalProcedure {
    private static final Random random = new Random();
    private static final int RADIUS = 5;
    private static final int DAMAGE = 10;
    private static final int TIME_LIMIT = 100;
    private static final Map<UUID, Boolean> playerActions = new ConcurrentHashMap();
    private static final int DURATION_AREA_REGENERACION = 700;
    private static final int DURATION_CADENAS_DE_LUZ = 100;
    private static final int DURATION_CAMBIO = 160;
    private static final String[] habilidades = new String[]{"\u00c1rea de Regeneraci\u00f3n", "Cadenas de Luz", "Cambio"};
    private static final Map<UUID, Integer> currentHabilidad = new ConcurrentHashMap();

    public BastonElementalProcedure() {
    }

    public static void execute(LevelAccessor world, double x, double y, double z, Player player, ItemStack itemstack) {
        if (world instanceof ServerLevel serverWorld) {
            UUID playerUUID = player.m_20148_();
            int habilidadIndex = (Integer)currentHabilidad.getOrDefault(playerUUID, 0);
            if (player.m_6047_()) {
                habilidadIndex = (habilidadIndex + 1) % habilidades.length;
                currentHabilidad.put(playerUUID, habilidadIndex);
                String var10001 = habilidades[habilidadIndex];
                player.m_5661_(Component.m_237113_("Habilidad actual: " + var10001), true);
            } else if (tieneCooldown(player, habilidadIndex)) {
                player.m_5661_(Component.m_237113_("Esta habilidad est\u00e1 en cooldown."), true);
            } else {
                switch (habilidadIndex) {
                    case 0:
                        activarAreaRegeneracion(serverWorld, x, y, z, player, itemstack);
                        break;
                    case 1:
                        activarCadenasDeLuz(world, player, itemstack);
                        break;
                    case 2:
                        activarCambio(world, player, itemstack);
                }

            }
        }
    }

    private static boolean tieneCooldown(Player player, int habilidadIndex) {
        boolean var10000;
        switch (habilidadIndex) {
            case 0:
                var10000 = player.m_21023_((MobEffect)KlvModMobEffects.COOLDOWN_AREA_REGENERATIVA.get());
                break;
            case 1:
                var10000 = player.m_21023_((MobEffect)KlvModMobEffects.COOLDOWN_CADENAS_DE_LUZ.get());
                break;
            case 2:
                var10000 = player.m_21023_((MobEffect)KlvModMobEffects.COOLDOWN_CAMBIO.get());
                break;
            default:
                var10000 = false;
        }

        return var10000;
    }

    private static void activarAreaRegeneracion(ServerLevel world, double x, double y, double z, Player player, ItemStack itemstack) {
        double radius = 5.0;

        for(double theta = 0.0; theta < 6.283185307179586; theta += 0.19634954084936207) {
            double dx = radius * Math.cos(theta);
            double dz = radius * Math.sin(theta);
            world.m_8767_(ParticleTypes.f_123750_, x + dx, y, z + dz, 1, 0.0, 0.0, 0.0, 0.0);
        }

        List<LivingEntity> entities = world.m_45976_(LivingEntity.class, player.m_20191_().m_82400_(5.0));
        Iterator var12 = entities.iterator();

        while(var12.hasNext()) {
            LivingEntity entity = (LivingEntity)var12.next();
            entity.m_7292_(new MobEffectInstance(MobEffects.f_19605_, 140, 1));
        }

        player.m_7292_(new MobEffectInstance(MobEffects.f_19605_, 140, 1));
        player.m_7292_(new MobEffectInstance((MobEffect)KlvModMobEffects.COOLDOWN_AREA_REGENERATIVA.get(), 700, 0));
        world.m_5594_((Player)null, new BlockPos(x, y, z), SoundEvents.f_11911_, player.m_5720_(), 1.0F, 1.0F);
    }

    private static void activarCadenasDeLuz(LevelAccessor world, Player player, ItemStack itemstack) {
        double range = 20.0;
        Vec3 lookVec = player.m_20154_();
        double x = player.m_20185_();
        double y = player.m_20186_() + (double)player.m_20192_();
        double z = player.m_20189_();
        world.m_5594_((Player)null, new BlockPos(x, y, z), SoundEvents.f_11862_, SoundSource.PLAYERS, 1.0F, 1.0F);

        for(int i = 0; (double)i < range; ++i) {
            double dx = x + lookVec.f_82479_ * (double)i;
            double dy = y + lookVec.f_82480_ * (double)i;
            double dz = z + lookVec.f_82481_ * (double)i;
            if (world instanceof ServerLevel) {
                ((ServerLevel)world).m_8767_(ParticleTypes.f_175821_, dx, dy, dz, 1, 0.0, 0.0, 0.0, 0.0);
                AABB area = new AABB(dx - 0.5, dy - 0.5, dz - 0.5, dx + 0.5, dy + 0.5, dz + 0.5);
                List<Entity> entities = world.m_6249_((Entity)null, area, (e) -> {
                    return !e.equals(player);
                });
                Iterator var21 = entities.iterator();

                while(var21.hasNext()) {
                    Entity target = (Entity)var21.next();
                    if (target instanceof LivingEntity) {
                        ((LivingEntity)target).m_7292_(new MobEffectInstance(MobEffects.f_19597_, 40, 80));
                        ((LivingEntity)target).m_7292_(new MobEffectInstance(MobEffects.f_19619_, 40, 0));
                        world.m_5594_((Player)null, target.m_20183_(), SoundEvents.f_11983_, SoundSource.PLAYERS, 1.0F, 1.0F);
                    }
                }
            }
        }

        player.m_7292_(new MobEffectInstance((MobEffect)KlvModMobEffects.COOLDOWN_CADENAS_DE_LUZ.get(), 100, 0));
    }

    private static void activarCambio(LevelAccessor world, Player player, ItemStack itemstack) {
        double range = 40.0;
        Vec3 lookVec = player.m_20154_();
        double x = player.m_20185_();
        double y = player.m_20186_() + (double)player.m_20192_();
        double z = player.m_20189_();
        Entity target = null;

        for(int i = 0; (double)i < range; ++i) {
            double dx = x + lookVec.f_82479_ * (double)i;
            double dy = y + lookVec.f_82480_ * (double)i;
            double dz = z + lookVec.f_82481_ * (double)i;
            AABB area = new AABB(dx - 0.5, dy - 0.5, dz - 0.5, dx + 0.5, dy + 0.5, dz + 0.5);
            List<Entity> entities = world.m_6249_((Entity)null, area, (e) -> {
                return !e.equals(player);
            });
            Iterator var22 = entities.iterator();

            while(var22.hasNext()) {
                Entity entity = (Entity)var22.next();
                if (entity instanceof LivingEntity) {
                    target = entity;
                    break;
                }
            }

            if (target != null) {
                break;
            }
        }

        if (target != null) {
            double targetX = target.m_20185_();
            double targetY = target.m_20186_();
            double targetZ = target.m_20189_();
            double distance = (double)player.m_20270_(target);
            if (distance > range) {
                player.m_5661_(Component.m_237113_("No puedes cambiar de lugar con entidades tan lejanas."), true);
            } else {
                player.m_6021_(targetX, targetY, targetZ);
                target.m_6021_(x, y, z);

                for(int i = 0; i < 32; ++i) {
                    if (world instanceof ServerLevel) {
                        ServerLevel serverWorld = (ServerLevel)world;
                        serverWorld.m_8767_(ParticleTypes.f_123760_, targetX, targetY + random.nextDouble() * 2.0, targetZ, 1, random.nextGaussian(), 0.0, random.nextGaussian(), 0.0);
                        serverWorld.m_8767_(ParticleTypes.f_123760_, x, y + random.nextDouble() * 2.0, z, 1, random.nextGaussian(), 0.0, random.nextGaussian(), 0.0);
                    }
                }

                world.m_5594_((Player)null, new BlockPos(x, y, z), SoundEvents.f_11852_, SoundSource.PLAYERS, 1.0F, 1.0F);
                player.m_7292_(new MobEffectInstance((MobEffect)KlvModMobEffects.COOLDOWN_CAMBIO.get(), 160, 0));
            }
        }

    }
}
