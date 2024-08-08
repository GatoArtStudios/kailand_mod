package net.mcreator.klv.init;

import net.mcreator.klv.potion.CooldownAreaRegenerativaMobEffect;
import net.mcreator.klv.potion.CooldownCadenasDeLuzMobEffect;
import net.mcreator.klv.potion.CooldownCambioMobEffect;
import net.mcreator.klv.potion.CooldownMobEffect;
import net.mcreator.klv.potion.HitArmorMobEffect;
import net.mcreator.klv.potion.RandomDirectionMobEffect;
import net.mcreator.klv.potion.SensorDeEntidadesMobEffect;
import net.mcreator.klv.potion.TeleportEffectMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KlvModMobEffects {
    public static final DeferredRegister<MobEffect> REGISTRY;
    public static final RegistryObject<MobEffect> SENSOR_DE_ENTIDADES;
    public static final RegistryObject<MobEffect> COOLDOWN;
    public static final RegistryObject<MobEffect> HIT_ARMOR;
    public static final RegistryObject<MobEffect> TELEPORT_EFFECT;
    public static final RegistryObject<MobEffect> RANDOM_DIRECTION;
    public static final RegistryObject<MobEffect> COOLDOWN_AREA_REGENERATIVA;
    public static final RegistryObject<MobEffect> COOLDOWN_CADENAS_DE_LUZ;
    public static final RegistryObject<MobEffect> COOLDOWN_CAMBIO;

    public KlvModMobEffects() {
    }

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "klv");
        SENSOR_DE_ENTIDADES = REGISTRY.register("sensor_de_entidades", () -> {
            return new SensorDeEntidadesMobEffect();
        });
        COOLDOWN = REGISTRY.register("cooldown", () -> {
            return new CooldownMobEffect();
        });
        HIT_ARMOR = REGISTRY.register("hit_armor", () -> {
            return new HitArmorMobEffect();
        });
        TELEPORT_EFFECT = REGISTRY.register("teleport_effect", () -> {
            return new TeleportEffectMobEffect();
        });
        RANDOM_DIRECTION = REGISTRY.register("random_direction", () -> {
            return new RandomDirectionMobEffect();
        });
        COOLDOWN_AREA_REGENERATIVA = REGISTRY.register("cooldown_area_regenerativa", () -> {
            return new CooldownAreaRegenerativaMobEffect();
        });
        COOLDOWN_CADENAS_DE_LUZ = REGISTRY.register("cooldown_cadenas_de_luz", () -> {
            return new CooldownCadenasDeLuzMobEffect();
        });
        COOLDOWN_CAMBIO = REGISTRY.register("cooldown_cambio", () -> {
            return new CooldownCambioMobEffect();
        });
    }
}
