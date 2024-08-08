package net.mcreator.klv.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import net.mcreator.klv.procedures.MisticaSwordEffectProcedure;
import net.mcreator.klv.init.KlvModTabs;

import javax.annotation.Nullable;
import java.util.List;

public class EspadaMisticaItem extends SwordItem {
    public EspadaMisticaItem() {
        super(new Tier() {
            public int getUses() {
                return 1850;
            }

            public float getSpeed() {
                return 4f;
            }

            public float getAttackDamageBonus() {
                return 9.8f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 25;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 3, -2.7f, new Item.Properties().tab(KlvModTabs.TAB_KL).fireResistant());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

        // Ejecutar el procedimiento cuando se usa la espada
        if (!world.isClientSide) {
            MisticaSwordEffectProcedure.execute(entity); // Pasar el jugador como argumento
            entity.getCooldowns().addCooldown(this, 900); // Añadir cooldown de 900 ticks (45 segundos)
        }

        return ar;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("Este ítem, cuando se hace clic derecho, gira a los jugadores en un radio de 8x8 bloques."));
    }
}
