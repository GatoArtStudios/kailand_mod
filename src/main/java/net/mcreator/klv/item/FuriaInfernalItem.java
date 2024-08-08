package net.mcreator.klv.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.ChatFormatting;
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

import net.mcreator.klv.procedures.FuriaInfernalProcedureProcedure;
import net.mcreator.klv.init.KlvModTabs;

import javax.annotation.Nullable;
import java.util.List;

public class FuriaInfernalItem extends SwordItem {
    public FuriaInfernalItem() {
        super(new Tier() {
            public int getUses() {
                return 2300;
            }

            public float getSpeed() {
                return 4f;
            }

            public float getAttackDamageBonus() {
                return 14.5f;
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
        }, 3, -3f, new Item.Properties().tab(KlvModTabs.TAB_KL).fireResistant());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);

        if (!world.isClientSide) {
            FuriaInfernalProcedureProcedure.execute(world, entity);
            entity.getCooldowns().addCooldown(this, 700); // Añadir cooldown de 700 ticks (35 segundos)

            // Reducir la durabilidad del ítem en 50
            ItemStack itemstack = entity.getItemInHand(hand);
            itemstack.hurtAndBreak(50, entity, (e) -> {
                e.broadcastBreakEvent(hand);
            });
        }

        return ar;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.literal("Al hacer clic derecho, se genera una pared temporal de fuego que empuja a los enemigos").withStyle(ChatFormatting.GRAY));
    }
}
