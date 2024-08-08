
package net.mcreator.klv.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.klv.procedures.CascoDeWardenEventoCadaTickDelPetoDeArmaduraProcedure;
import net.mcreator.klv.procedures.CascoDeWardenAlTenerPuestosLosPantalonesProcedure;
import net.mcreator.klv.procedures.CascoDeWardenAlTenerPuestoElCascoProcedure;
import net.mcreator.klv.procedures.BotasWardenProcedureProcedure;
import net.mcreator.klv.init.KlvModTabs;

public abstract class CascoDeWardenItem extends ArmorItem {
	public CascoDeWardenItem(EquipmentSlot slot, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForSlot(EquipmentSlot slot) {
				return new int[]{50, 50, 50, 50}[slot.getIndex()] * -1;
			}

			@Override
			public int getDefenseForSlot(EquipmentSlot slot) {
				return new int[]{7, 9, 11, 8}[slot.getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 35;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.warden.hurt"));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of();
			}

			@Override
			public String getName() {
				return "casco_de_warden";
			}

			@Override
			public float getToughness() {
				return 10f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.6f;
			}
		}, slot, properties);
	}

	public static class Helmet extends CascoDeWardenItem {
		public Helmet() {
			super(EquipmentSlot.HEAD, new Item.Properties().tab(KlvModTabs.TAB_KL));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "klv:textures/models/armor/wardengod__layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			CascoDeWardenAlTenerPuestoElCascoProcedure.execute(entity);
		}
	}

	public static class Chestplate extends CascoDeWardenItem {
		public Chestplate() {
			super(EquipmentSlot.CHEST, new Item.Properties().tab(KlvModTabs.TAB_KL));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "klv:textures/models/armor/wardengod__layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			CascoDeWardenEventoCadaTickDelPetoDeArmaduraProcedure.execute(entity);
		}
	}

	public static class Leggings extends CascoDeWardenItem {
		public Leggings() {
			super(EquipmentSlot.LEGS, new Item.Properties().tab(KlvModTabs.TAB_KL));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "klv:textures/models/armor/wardengod__layer_2.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			CascoDeWardenAlTenerPuestosLosPantalonesProcedure.execute(entity);
		}
	}

	public static class Boots extends CascoDeWardenItem {
		public Boots() {
			super(EquipmentSlot.FEET, new Item.Properties().tab(KlvModTabs.TAB_KL));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "klv:textures/models/armor/wardengod__layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			BotasWardenProcedureProcedure.execute(entity);
		}
	}
}
