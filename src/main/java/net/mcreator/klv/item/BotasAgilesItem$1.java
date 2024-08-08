package net.mcreator.klv.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

class BotasAgilesItem$1 implements ArmorMaterial {
    BotasAgilesItem$1() {
    }

    public int m_7366_(EquipmentSlot slot) {
        return (new int[]{50, 50, 50, 50})[slot.m_20749_()] * 5000;
    }

    public int m_7365_(EquipmentSlot slot) {
        return (new int[]{0, 0, 0, 5})[slot.m_20749_()];
    }

    public int m_6646_() {
        return 9;
    }

    public SoundEvent m_7344_() {
        return (SoundEvent)ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
    }

    public Ingredient m_6230_() {
        return Ingredient.m_151265_();
    }

    public String m_6082_() {
        return "botas_agiles";
    }

    public float m_6651_() {
        return 10.0F;
    }

    public float m_6649_() {
        return 0.1F;
    }
}
