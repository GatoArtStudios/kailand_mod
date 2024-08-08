package net.mcreator.klv.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import net.mcreator.klv.entity.MjolnirEntity;
import net.mcreator.klv.init.KlvModTabs;
import net.mcreator.klv.procedures.MjolnirPuedeUsarEsteObjetoProcedure;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MjolnirItem extends Item {
    public MjolnirItem() {
        super((new Item.Properties()).m_41491_(KlvModTabs.TAB_KL).m_41503_(2200));
    }

    public InteractionResultHolder<ItemStack> m_7203_(Level world, Player entity, InteractionHand hand) {
        entity.m_6672_(hand);
        return new InteractionResultHolder(InteractionResult.SUCCESS, entity.m_21120_(hand));
    }

    public void m_7373_(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.m_7373_(itemstack, world, list, flag);
        list.add(Component.m_237113_("\u00a77Este martillo se puede lanzar para generar rayos en el punto de impacto"));
    }

    public UseAnim m_6164_(ItemStack itemstack) {
        return UseAnim.SPEAR;
    }

    public int m_8105_(ItemStack itemstack) {
        return 72000;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean m_5812_(ItemStack itemstack) {
        return true;
    }

    public Multimap<Attribute, AttributeModifier> m_7167_(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
            builder.putAll(super.m_7167_(slot));
            builder.put(Attributes.f_22281_, new AttributeModifier(f_41374_, "Ranged item modifier", 12.0, Operation.ADDITION));
            builder.put(Attributes.f_22283_, new AttributeModifier(f_41375_, "Ranged item modifier", -2.4, Operation.ADDITION));
            return builder.build();
        } else {
            return super.m_7167_(slot);
        }
    }

    public void m_5551_(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (!world.m_5776_() && entityLiving instanceof ServerPlayer entity) {
            double x = entity.m_20185_();
            double y = entity.m_20186_();
            double z = entity.m_20189_();
            MjolnirEntity entityarrow = MjolnirEntity.shoot(world, entity, world.m_213780_(), 1.8F, 3.5, 3);
            itemstack.m_41622_(1, entity, (e) -> {
                e.m_21190_(entity.m_7655_());
            });
            entityarrow.f_36705_ = Pickup.DISALLOWED;
            MjolnirPuedeUsarEsteObjetoProcedure.execute(world, x, y, z, entity, itemstack);
        }

    }
}
