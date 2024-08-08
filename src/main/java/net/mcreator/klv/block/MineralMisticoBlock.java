package net.mcreator.klv.block;

import java.util.Collections;
import java.util.List;
import net.mcreator.klv.init.KlvModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;

public class MineralMisticoBlock extends Block {
    public MineralMisticoBlock() {
        super(Properties.m_60939_(Material.f_76278_).m_60918_(SoundType.f_56742_).m_60913_(18.0F, 35.0F).m_60953_((s) -> {
            return 3;
        }).m_60999_());
    }

    public int m_7753_(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 10;
    }

    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        Item var6 = player.m_150109_().m_36056_().m_41720_();
        if (var6 instanceof PickaxeItem tieredItem) {
            return tieredItem.m_43314_().m_6604_() >= 4;
        } else {
            return false;
        }
    }

    public List<ItemStack> m_7381_(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.m_7381_(state, builder);
        return !dropsOriginal.isEmpty() ? dropsOriginal : Collections.singletonList(new ItemStack((ItemLike)KlvModItems.GEMA_KAILAND.get()));
    }
}
