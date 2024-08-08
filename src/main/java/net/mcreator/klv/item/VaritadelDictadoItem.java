package net.mcreator.klv.item;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component; // Importación correcta
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.ChatFormatting; // Importación correcta

import net.mcreator.klv.procedures.SimonDiceProcedure;
import net.mcreator.klv.init.KlvModTabs;
import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;
import java.util.List;

public class VaritadelDictadoItem extends Item {
    public VaritadelDictadoItem() {
        super(new Item.Properties().tab(KlvModTabs.TAB_KL).durability(3500).fireResistant().rarity(Rarity.EPIC));
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.getDefaultAttributeModifiers(equipmentSlot));
            builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Item modifier", 4d, AttributeModifier.Operation.ADDITION));
            builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Item modifier", -2.4, AttributeModifier.Operation.ADDITION));
            return builder.build();
        }
        return super.getDefaultAttributeModifiers(equipmentSlot);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(ItemStack itemstack) {
        return true;
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.literal("Varita del Dictado");
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        ItemStack itemstack = entity.getItemInHand(hand);
        if (!world.isClientSide) {
            List<Player> playersInRadius = world.getEntitiesOfClass(Player.class, entity.getBoundingBox().inflate(5));

            if (playersInRadius.size() > 1) { // Más de 1 para excluir al jugador que usa la varita
                SimonDiceProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity);
                itemstack.hurtAndBreak(25, entity, e -> e.broadcastBreakEvent(hand));
                entity.getCooldowns().addCooldown(this, 900); // Añadir cooldown de 45 segundos (900 ticks)
            } else {
                entity.displayClientMessage(Component.literal("No hay jugadores cercanos."), true);
            }
        }
        return InteractionResultHolder.success(itemstack);
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.literal("Este ítem da órdenes a los jugadores en un radio de 5x5. Si fallan la orden, pierden vida.").withStyle(ChatFormatting.GRAY));
    }
}
