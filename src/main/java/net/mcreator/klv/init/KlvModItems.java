
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.klv.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.klv.item.VenasDeWardenItem;
import net.mcreator.klv.item.VaritadelDictadoItem;
import net.mcreator.klv.item.VaritaLunarItem;
import net.mcreator.klv.item.VaritaDeSkullItem;
import net.mcreator.klv.item.VaritaDeLosDeseosItem;
import net.mcreator.klv.item.TsukeItem;
import net.mcreator.klv.item.PrismaItem;
import net.mcreator.klv.item.PicoKailandItem;
import net.mcreator.klv.item.MonedaRubiItem;
import net.mcreator.klv.item.MonedaRotaItem;
import net.mcreator.klv.item.MonedaDeOroItem;
import net.mcreator.klv.item.MonedaAmatistaItem;
import net.mcreator.klv.item.MjolnirItem;
import net.mcreator.klv.item.MazoHeroicoItem;
import net.mcreator.klv.item.MartilloDeGravedadItem;
import net.mcreator.klv.item.KatanaItem;
import net.mcreator.klv.item.HachaNordicaItem;
import net.mcreator.klv.item.HachaLeviatanItem;
import net.mcreator.klv.item.GuadanaItem;
import net.mcreator.klv.item.GemaKailandItem;
import net.mcreator.klv.item.GemaHeladaItem;
import net.mcreator.klv.item.FuriaInfernalItem;
import net.mcreator.klv.item.EspadaVampiricaItem;
import net.mcreator.klv.item.EspadaSonicaItem;
import net.mcreator.klv.item.EspadaShulkerItem;
import net.mcreator.klv.item.EspadaMisticaItem;
import net.mcreator.klv.item.EsenciaDeRagnarokItem;
import net.mcreator.klv.item.CuchillaItem;
import net.mcreator.klv.item.CorazonDelWardenItem;
import net.mcreator.klv.item.CascoDeWardenItem;
import net.mcreator.klv.item.BotasAgilesItem;
import net.mcreator.klv.item.BaculoElementalItem;
import net.mcreator.klv.item.AntidisturbiosItem;
import net.mcreator.klv.KlvMod;

public class KlvModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, KlvMod.MODID);
	public static final RegistryObject<Item> MONEDA_RUBI = REGISTRY.register("moneda_rubi", () -> new MonedaRubiItem());
	public static final RegistryObject<Item> MONEDA_AMATISTA = REGISTRY.register("moneda_amatista", () -> new MonedaAmatistaItem());
	public static final RegistryObject<Item> MONEDA_ROTA = REGISTRY.register("moneda_rota", () -> new MonedaRotaItem());
	public static final RegistryObject<Item> HACHA_NORDICA = REGISTRY.register("hacha_nordica", () -> new HachaNordicaItem());
	public static final RegistryObject<Item> HACHA_LEVIATAN = REGISTRY.register("hacha_leviatan", () -> new HachaLeviatanItem());
	public static final RegistryObject<Item> KATANA = REGISTRY.register("katana", () -> new KatanaItem());
	public static final RegistryObject<Item> GUADANA = REGISTRY.register("guadana", () -> new CuchillaItem());
	public static final RegistryObject<Item> CUCHILLA = REGISTRY.register("cuchilla", () -> new GuadanaItem());
	public static final RegistryObject<Item> ESENCIA_DE_RAGNAROK = REGISTRY.register("esencia_de_ragnarok", () -> new EsenciaDeRagnarokItem());
	public static final RegistryObject<Item> VARITA_DE_LOS_DESEOS = REGISTRY.register("varita_de_los_deseos", () -> new VaritaDeLosDeseosItem());
	public static final RegistryObject<Item> MJOLNIR = REGISTRY.register("mjolnir", () -> new MjolnirItem());
	public static final RegistryObject<Item> ANTIDISTURBIOS = REGISTRY.register("antidisturbios", () -> new AntidisturbiosItem());
	public static final RegistryObject<Item> ESPADA_SONICA = REGISTRY.register("espada_sonica", () -> new EspadaSonicaItem());
	public static final RegistryObject<Item> CASCO_DE_WARDEN_HELMET = REGISTRY.register("casco_de_warden_helmet", () -> new CascoDeWardenItem.Helmet());
	public static final RegistryObject<Item> CASCO_DE_WARDEN_CHESTPLATE = REGISTRY.register("casco_de_warden_chestplate", () -> new CascoDeWardenItem.Chestplate());
	public static final RegistryObject<Item> CASCO_DE_WARDEN_LEGGINGS = REGISTRY.register("casco_de_warden_leggings", () -> new CascoDeWardenItem.Leggings());
	public static final RegistryObject<Item> CASCO_DE_WARDEN_BOOTS = REGISTRY.register("casco_de_warden_boots", () -> new CascoDeWardenItem.Boots());
	public static final RegistryObject<Item> VARITA_DE_SKULL = REGISTRY.register("varita_de_skull", () -> new VaritaDeSkullItem());
	public static final RegistryObject<Item> TSUKE = REGISTRY.register("tsuke", () -> new TsukeItem());
	public static final RegistryObject<Item> CORAZON_DEL_WARDEN = REGISTRY.register("corazon_del_warden", () -> new CorazonDelWardenItem());
	public static final RegistryObject<Item> VENAS_DE_WARDEN = REGISTRY.register("venas_de_warden", () -> new VenasDeWardenItem());
	public static final RegistryObject<Item> MAZO_HEROICO = REGISTRY.register("mazo_heroico", () -> new MazoHeroicoItem());
	public static final RegistryObject<Item> PICO_KAILAND = REGISTRY.register("pico_kailand", () -> new PicoKailandItem());
	public static final RegistryObject<Item> GEMA_KAILAND = REGISTRY.register("gema_kailand", () -> new GemaKailandItem());
	public static final RegistryObject<Item> ESPADA_SHULKER = REGISTRY.register("espada_shulker", () -> new EspadaShulkerItem());
	public static final RegistryObject<Item> BOTAS_AGILES_BOOTS = REGISTRY.register("botas_agiles_boots", () -> new BotasAgilesItem.Boots());
	public static final RegistryObject<Item> MINERAL_MISTICO = block(KlvModBlocks.MINERAL_MISTICO, KlvModTabs.TAB_KL);
	public static final RegistryObject<Item> BLOQUE_VELOX = block(KlvModBlocks.BLOQUE_VELOX, KlvModTabs.TAB_KL);
	public static final RegistryObject<Item> ESPADA_VAMPIRICA = REGISTRY.register("espada_vampirica", () -> new EspadaVampiricaItem());
	public static final RegistryObject<Item> GEMA_HELADA = REGISTRY.register("gema_helada", () -> new GemaHeladaItem());
	public static final RegistryObject<Item> PRISMA = REGISTRY.register("prisma", () -> new PrismaItem());
	public static final RegistryObject<Item> ESPADA_MISTICA = REGISTRY.register("espada_mistica", () -> new EspadaMisticaItem());
	public static final RegistryObject<Item> FURIA_INFERNAL = REGISTRY.register("furia_infernal", () -> new FuriaInfernalItem());
	public static final RegistryObject<Item> VARITADEL_DICTADO = REGISTRY.register("varitadel_dictado", () -> new VaritadelDictadoItem());
	public static final RegistryObject<Item> VARITA_LUNAR = REGISTRY.register("varita_lunar", () -> new VaritaLunarItem());
	public static final RegistryObject<Item> BACULO_DORADO = REGISTRY.register("baculo_dorado", () -> new BaculoElementalItem());
	public static final RegistryObject<Item> MARTILLO_DE_GRAVEDAD = REGISTRY.register("martillo_de_gravedad", () -> new MartilloDeGravedadItem());
	public static final RegistryObject<Item> MONEDA_DE_ORO = REGISTRY.register("moneda_de_oro", () -> new MonedaDeOroItem());

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
