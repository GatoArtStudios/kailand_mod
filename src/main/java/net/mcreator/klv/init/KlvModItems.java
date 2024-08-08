// Source code is decompiled from a .class file using FernFlower decompiler.
package net.mcreator.klv.init;

import net.mcreator.klv.item.AntidisturbiosItem;
import net.mcreator.klv.item.BaculoElementalItem;
import net.mcreator.klv.item.BotasAgilesItem;
import net.mcreator.klv.item.CascoDeWardenItem;
import net.mcreator.klv.item.CorazonDelWardenItem;
import net.mcreator.klv.item.CuchillaItem;
import net.mcreator.klv.item.EsenciaDeRagnarokItem;
import net.mcreator.klv.item.EspadaMisticaItem;
import net.mcreator.klv.item.EspadaShulkerItem;
import net.mcreator.klv.item.EspadaSonicaItem;
import net.mcreator.klv.item.EspadaVampiricaItem;
import net.mcreator.klv.item.FuriaInfernalItem;
import net.mcreator.klv.item.GemaHeladaItem;
import net.mcreator.klv.item.GemaKailandItem;
import net.mcreator.klv.item.GuadanaItem;
import net.mcreator.klv.item.HachaLeviatanItem;
import net.mcreator.klv.item.HachaNordicaItem;
import net.mcreator.klv.item.KatanaItem;
import net.mcreator.klv.item.MartilloDeGravedadItem;
import net.mcreator.klv.item.MazoHeroicoItem;
import net.mcreator.klv.item.MjolnirItem;
import net.mcreator.klv.item.MonedaAmatistaItem;
import net.mcreator.klv.item.MonedaItem;
import net.mcreator.klv.item.MonedaRotaItem;
import net.mcreator.klv.item.MonedaRubiItem;
import net.mcreator.klv.item.PicoKailandItem;
import net.mcreator.klv.item.PrismaItem;
import net.mcreator.klv.item.TsukeItem;
import net.mcreator.klv.item.VaritaDeLosDeseosItem;
import net.mcreator.klv.item.VaritaDeSkullItem;
import net.mcreator.klv.item.VaritaLunarItem;
import net.mcreator.klv.item.VaritadelDictadoItem;
import net.mcreator.klv.item.VenasDeWardenItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KlvModItems {
    public static final DeferredRegister<Item> REGISTRY;
    public static final RegistryObject<Item> MONEDA_RUBI;
    public static final RegistryObject<Item> MONEDA;
    public static final RegistryObject<Item> MONEDA_AMATISTA;
    public static final RegistryObject<Item> MONEDA_ROTA;
    public static final RegistryObject<Item> HACHA_NORDICA;
    public static final RegistryObject<Item> HACHA_LEVIATAN;
    public static final RegistryObject<Item> KATANA;
    public static final RegistryObject<Item> GUADANA;
    public static final RegistryObject<Item> CUCHILLA;
    public static final RegistryObject<Item> ESENCIA_DE_RAGNAROK;
    public static final RegistryObject<Item> VARITA_DE_LOS_DESEOS;
    public static final RegistryObject<Item> MJOLNIR;
    public static final RegistryObject<Item> ANTIDISTURBIOS;
    public static final RegistryObject<Item> ESPADA_SONICA;
    public static final RegistryObject<Item> CASCO_DE_WARDEN_HELMET;
    public static final RegistryObject<Item> CASCO_DE_WARDEN_CHESTPLATE;
    public static final RegistryObject<Item> CASCO_DE_WARDEN_LEGGINGS;
    public static final RegistryObject<Item> CASCO_DE_WARDEN_BOOTS;
    public static final RegistryObject<Item> VARITA_DE_SKULL;
    public static final RegistryObject<Item> TSUKE;
    public static final RegistryObject<Item> CORAZON_DEL_WARDEN;
    public static final RegistryObject<Item> VENAS_DE_WARDEN;
    public static final RegistryObject<Item> MAZO_HEROICO;
    public static final RegistryObject<Item> PICO_KAILAND;
    public static final RegistryObject<Item> GEMA_KAILAND;
    public static final RegistryObject<Item> ESPADA_SHULKER;
    public static final RegistryObject<Item> BOTAS_AGILES_BOOTS;
    public static final RegistryObject<Item> MINERAL_MISTICO;
    public static final RegistryObject<Item> BLOQUE_VELOX;
    public static final RegistryObject<Item> ESPADA_VAMPIRICA;
    public static final RegistryObject<Item> GEMA_HELADA;
    public static final RegistryObject<Item> PRISMA;
    public static final RegistryObject<Item> ESPADA_MISTICA;
    public static final RegistryObject<Item> FURIA_INFERNAL;
    public static final RegistryObject<Item> VARITADEL_DICTADO;
    public static final RegistryObject<Item> VARITA_LUNAR;
    public static final RegistryObject<Item> BACULO_DORADO;
    public static final RegistryObject<Item> OJO_VIGILANTE_SPAWN_EGG;
    public static final RegistryObject<Item> MARTILLO_DE_GRAVEDAD;

    public KlvModItems() {
    }

    private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
        return REGISTRY.register(block.getId().m_135815_(), () -> {
            return new BlockItem((Block)block.get(), (new Item.Properties()).m_41491_(tab));
        });
    }

    static {
        REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, "klv");
        MONEDA_RUBI = REGISTRY.register("moneda_rubi", () -> {
            return new MonedaRubiItem();
        });
        MONEDA = REGISTRY.register("moneda", () -> {
            return new MonedaItem();
        });
        MONEDA_AMATISTA = REGISTRY.register("moneda_amatista", () -> {
            return new MonedaAmatistaItem();
        });
        MONEDA_ROTA = REGISTRY.register("moneda_rota", () -> {
            return new MonedaRotaItem();
        });
        HACHA_NORDICA = REGISTRY.register("hacha_nordica", () -> {
            return new HachaNordicaItem();
        });
        HACHA_LEVIATAN = REGISTRY.register("hacha_leviatan", () -> {
            return new HachaLeviatanItem();
        });
        KATANA = REGISTRY.register("katana", () -> {
            return new KatanaItem();
        });
        GUADANA = REGISTRY.register("guadana", () -> {
            return new CuchillaItem();
        });
        CUCHILLA = REGISTRY.register("cuchilla", () -> {
            return new GuadanaItem();
        });
        ESENCIA_DE_RAGNAROK = REGISTRY.register("esencia_de_ragnarok", () -> {
            return new EsenciaDeRagnarokItem();
        });
        VARITA_DE_LOS_DESEOS = REGISTRY.register("varita_de_los_deseos", () -> {
            return new VaritaDeLosDeseosItem();
        });
        MJOLNIR = REGISTRY.register("mjolnir", () -> {
            return new MjolnirItem();
        });
        ANTIDISTURBIOS = REGISTRY.register("antidisturbios", () -> {
            return new AntidisturbiosItem();
        });
        ESPADA_SONICA = REGISTRY.register("espada_sonica", () -> {
            return new EspadaSonicaItem();
        });
        CASCO_DE_WARDEN_HELMET = REGISTRY.register("casco_de_warden_helmet", () -> {
            return new CascoDeWardenItem.Helmet();
        });
        CASCO_DE_WARDEN_CHESTPLATE = REGISTRY.register("casco_de_warden_chestplate", () -> {
            return new CascoDeWardenItem.Chestplate();
        });
        CASCO_DE_WARDEN_LEGGINGS = REGISTRY.register("casco_de_warden_leggings", () -> {
            return new CascoDeWardenItem.Leggings();
        });
        CASCO_DE_WARDEN_BOOTS = REGISTRY.register("casco_de_warden_boots", () -> {
            return new CascoDeWardenItem.Boots();
        });
        VARITA_DE_SKULL = REGISTRY.register("varita_de_skull", () -> {
            return new VaritaDeSkullItem();
        });
        TSUKE = REGISTRY.register("tsuke", () -> {
            return new TsukeItem();
        });
        CORAZON_DEL_WARDEN = REGISTRY.register("corazon_del_warden", () -> {
            return new CorazonDelWardenItem();
        });
        VENAS_DE_WARDEN = REGISTRY.register("venas_de_warden", () -> {
            return new VenasDeWardenItem();
        });
        MAZO_HEROICO = REGISTRY.register("mazo_heroico", () -> {
            return new MazoHeroicoItem();
        });
        PICO_KAILAND = REGISTRY.register("pico_kailand", () -> {
            return new PicoKailandItem();
        });
        GEMA_KAILAND = REGISTRY.register("gema_kailand", () -> {
            return new GemaKailandItem();
        });
        ESPADA_SHULKER = REGISTRY.register("espada_shulker", () -> {
            return new EspadaShulkerItem();
        });
        BOTAS_AGILES_BOOTS = REGISTRY.register("botas_agiles_boots", () -> {
            return new BotasAgilesItem.Boots();
        });
        MINERAL_MISTICO = block(KlvModBlocks.MINERAL_MISTICO, KlvModTabs.TAB_KL);
        BLOQUE_VELOX = block(KlvModBlocks.BLOQUE_VELOX, KlvModTabs.TAB_KL);
        ESPADA_VAMPIRICA = REGISTRY.register("espada_vampirica", () -> {
            return new EspadaVampiricaItem();
        });
        GEMA_HELADA = REGISTRY.register("gema_helada", () -> {
            return new GemaHeladaItem();
        });
        PRISMA = REGISTRY.register("prisma", () -> {
            return new PrismaItem();
        });
        ESPADA_MISTICA = REGISTRY.register("espada_mistica", () -> {
            return new EspadaMisticaItem();
        });
        FURIA_INFERNAL = REGISTRY.register("furia_infernal", () -> {
            return new FuriaInfernalItem();
        });
        VARITADEL_DICTADO = REGISTRY.register("varitadel_dictado", () -> {
            return new VaritadelDictadoItem();
        });
        VARITA_LUNAR = REGISTRY.register("varita_lunar", () -> {
            return new VaritaLunarItem();
        });
        BACULO_DORADO = REGISTRY.register("baculo_dorado", () -> {
            return new BaculoElementalItem();
        });
        OJO_VIGILANTE_SPAWN_EGG = REGISTRY.register("ojo_vigilante_spawn_egg", () -> {
            return new ForgeSpawnEggItem(KlvModEntities.OJO_VIGILANTE, -1, -65536, (new Item.Properties()).m_41491_(KlvModTabs.TAB_KL));
        });
        MARTILLO_DE_GRAVEDAD = REGISTRY.register("martillo_de_gravedad", () -> {
            return new MartilloDeGravedadItem();
        });
    }
}
