package net.mcreator.klv.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Rarity;

import net.mcreator.klv.procedures.MazoHeroicoCuandoSeHaceClicConElBotonDerechoProcedure;
import net.mcreator.klv.procedures.MazoHeroicoAlEntidadSerGolpeadaConLaHerramientaProcedure;
import net.mcreator.klv.init.KlvModTabs;

import java.util.List;

public class MazoHeroicoItem extends AxeItem {
    public MazoHeroicoItem() {
        super(new Tier() {
            public int getUses() {
                return 3200;
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
    public boolean hurtEnemy(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
        boolean retval = super.hurtEnemy(itemstack, entity, sourceentity);
        MazoHeroicoAlEntidadSerGolpeadaConLaHerramientaProcedure.execute(entity, sourceentity, itemstack);
        return retval;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        MazoHeroicoCuandoSeHaceClicConElBotonDerechoProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, ar.getObject());
        return ar;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.literal("§7al usar clic derecho otorga una habilidad que al momento de golpear a una entidad ignorara totalmente su armadura"));
    }
}
