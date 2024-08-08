package net.mcreator.klv.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.SimonDiceProcedure;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class VaritadelDictadoItem extends Item {
    public VaritadelDictadoItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41503_(3500).m_41486_().m_41497_(Rarity.EPIC));
    }

    public Multimap<Attribute, AttributeModifier> m_7167_(EquipmentSlot equipmentSlot) {
        if (equipmentSlot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.m_7167_(equipmentSlot));
            builder.put(Attributes.f_22281_, new AttributeModifier(f_41374_, "Item modifier", 4.0, Operation.ADDITION));
            builder.put(Attributes.f_22283_, new AttributeModifier(f_41375_, "Item modifier", -2.4, Operation.ADDITION));
            return builder.build();
        } else {
            return super.m_7167_(equipmentSlot);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public boolean m_5812_(ItemStack itemstack) {
        return true;
    }

    public Component m_7626_(ItemStack stack) {
        return Component.m_237113_("Varita del Dictado");
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        ItemStack itemstack = entity.m_21120_(hand);
        if (!world.f_46443_) {
            List<Player> playersInRadius = world.m_45976_(Player.class, entity.m_20191_().m_82400_(5.0));
            if (playersInRadius.size() > 1) {
                SimonDiceProcedure.execute(world, entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), entity);
                itemstack.m_41622_(25, entity, (e) -> {
                    e.m_21190_(hand);
                });
                entity.m_36335_().m_41524_(this, 900);
            } else {
                entity.m_5661_(Component.m_237113_("No hay jugadores cercanos."), true);
            }
        }

        return InteractionResultHolder.m_19090_(itemstack);
    }

    public void m_7373_(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.m_7373_(stack, world, tooltip, flag);
        tooltip.add(Component.m_237113_("Este \u00edtem da \u00f3rdenes a los jugadores en un radio de 5x5. Si fallan la orden, pierden vida.").m_130940_(ChatFormatting.GRAY));
    }
}
