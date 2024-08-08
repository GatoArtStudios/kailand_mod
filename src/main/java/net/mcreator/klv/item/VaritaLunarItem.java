package net.mcreator.klv.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.ChatFormatting; // Importación correcta

import net.mcreator.klv.procedures.LunarRayProcedure;
import net.mcreator.klv.init.KlvModTabs;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class VaritaLunarItem extends Item {
    public VaritaLunarItem() {
        super(new Item.Properties().tab(KlvModTabs.TAB_KL).durability(100).fireResistant().rarity(Rarity.RARE));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        // Crear un mapa de dependencias
        Map<String, Object> dependencies = new HashMap<>();
        dependencies.put("entity", entity);
        dependencies.put("world", world);

        // Llamar al procedimiento LunarRayProcedure
        LunarRayProcedure.executeProcedure(dependencies);

        return ar;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.literal("Esta varita lanza rayos y, cuando te agachas, tiene una habilidad más poderosa, pero solo se puede utilizar por la noche").withStyle(ChatFormatting.GRAY));
    }
}
