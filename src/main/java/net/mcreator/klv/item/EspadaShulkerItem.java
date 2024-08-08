package net.mcreator.klv.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.klv.procedures.EspadaShulkerProcedureProcedure;
import net.mcreator.klv.init.KlvModTabs;

import java.util.HashMap;
import java.util.Map;

public class EspadaShulkerItem extends SwordItem {
    public EspadaShulkerItem() {
        super(new Tier() {
            public int getUses() {
                return 1750;
            }

            public float getSpeed() {
                return 4f;
            }

            public float getAttackDamageBonus() {
                return 8f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 20;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2.6f, new Item.Properties().tab(KlvModTabs.TAB_KL));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

        // Crear el mapa de dependencias
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        Map<String, Object> dependencies = new HashMap<>();
        dependencies.put("entity", entity);
        dependencies.put("x", x);
        dependencies.put("y", y);
        dependencies.put("z", z);
        dependencies.put("world", world);

        // Ejecutar el procedimiento
        EspadaShulkerProcedureProcedure.execute(dependencies);

        return ar;
    }
}
