package net.mcreator.klv.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Rarity;

import net.mcreator.klv.procedures.MartilloDeGravedadProcedureProcedure;
import net.mcreator.klv.init.KlvModTabs;

import java.util.List;

public class MartilloDeGravedadItem extends AxeItem {
    private static final int COOLDOWN_PRINCIPAL = 20 * 20; // 20 segundos en ticks
    private static final int COOLDOWN_SECUNDARIO = 20 * 20; // 20 segundos en ticks

    public MartilloDeGravedadItem() {
        super(new Tier() {
            public int getUses() {
                return 2850;
            }

            public float getSpeed() {
                return 4f;
            }

            public float getAttackDamageBonus() {
                return 14f;
            }

            public int getLevel() {
                return 1;
            }

            public int getEnchantmentValue() {
                return 2;
            }

            public Ingredient getRepairIngredient() {
                return Ingredient.of();
            }
        }, 1, -3.2f, new Item.Properties().tab(KlvModTabs.TAB_KL).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        ItemStack itemstack = entity.getItemInHand(hand);
        if (!world.isClientSide) {
            if (entity.isCrouching()) {
                // Habilidad secundaria: verificar cooldown
                if (entity.getCooldowns().isOnCooldown(this)) {
                    return InteractionResultHolder.fail(itemstack);
                }

                // Ejecutar el procedimiento de habilidad secundaria
                MartilloDeGravedadProcedureProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, true);

                // Establecer el cooldown secundario
                entity.getCooldowns().addCooldown(this, COOLDOWN_SECUNDARIO);

                // Retornar resultado
                return InteractionResultHolder.success(itemstack);
            } else {
                // Habilidad principal: verificar cooldown
                if (entity.getCooldowns().isOnCooldown(this)) {
                    return InteractionResultHolder.fail(itemstack);
                }

                // Ejecutar el procedimiento de habilidad principal
                MartilloDeGravedadProcedureProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, false);

                // Establecer el cooldown principal
                entity.getCooldowns().addCooldown(this, COOLDOWN_PRINCIPAL);

                // Retornar resultado
                return InteractionResultHolder.success(itemstack);
            }
        }
        return InteractionResultHolder.pass(itemstack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BLOCK;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.literal("§5Principal: §7Atrae y daña entidades cercanas"));
        tooltip.add(Component.literal("§5Secundaria: §7Empuja a las entidades cercanas"));
    }
}
