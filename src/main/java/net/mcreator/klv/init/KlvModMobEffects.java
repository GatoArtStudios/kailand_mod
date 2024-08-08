
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.effect.MobEffect;

import net.mcreator.klv.potion.TeleportEffectMobEffect;
import net.mcreator.klv.potion.SensorDeEntidadesMobEffect;
import net.mcreator.klv.potion.RandomDirectionMobEffect;
import net.mcreator.klv.potion.HitArmorMobEffect;
import net.mcreator.klv.potion.CooldownMobEffect;
import net.mcreator.klv.potion.CooldownCambioMobEffect;
import net.mcreator.klv.potion.CooldownCadenasDeLuzMobEffect;
import net.mcreator.klv.potion.CooldownAreaRegenerativaMobEffect;
import net.mcreator.klv.KlvMod;

public class KlvModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, KlvMod.MODID);
	public static final RegistryObject<MobEffect> SENSOR_DE_ENTIDADES = REGISTRY.register("sensor_de_entidades", () -> new SensorDeEntidadesMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN = REGISTRY.register("cooldown", () -> new CooldownMobEffect());
	public static final RegistryObject<MobEffect> HIT_ARMOR = REGISTRY.register("hit_armor", () -> new HitArmorMobEffect());
	public static final RegistryObject<MobEffect> TELEPORT_EFFECT = REGISTRY.register("teleport_effect", () -> new TeleportEffectMobEffect());
	public static final RegistryObject<MobEffect> RANDOM_DIRECTION = REGISTRY.register("random_direction", () -> new RandomDirectionMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN_AREA_REGENERATIVA = REGISTRY.register("cooldown_area_regenerativa", () -> new CooldownAreaRegenerativaMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN_CADENAS_DE_LUZ = REGISTRY.register("cooldown_cadenas_de_luz", () -> new CooldownCadenasDeLuzMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN_CAMBIO = REGISTRY.register("cooldown_cambio", () -> new CooldownCambioMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN_ALIENTO_DE_DRAGON = REGISTRY.register("cooldown_aliento_de_dragon", () -> new CooldownAlientoDeDragonMobEffect());
	public static final RegistryObject<MobEffect> COOLDOWN_CORTE_FUGAZ = REGISTRY.register("cooldown_corte_fugaz", () -> new CooldownCorteFugazMobEffect());
}
