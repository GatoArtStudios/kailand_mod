package net.mcreator.klv.procedures;

import net.minecraft.util.datafix.fixes.FurnaceRecipeFix;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.Optional;

public class AutoFundicionProcedureProcedure {
	private static final List<Block> BLACKLIST_BLOCKS = List.of(
			Blocks.REDSTONE_ORE,
			Blocks.DEEPSLATE_REDSTONE_ORE,
			Blocks.DIAMOND_ORE,
			Blocks.DEEPSLATE_DIAMOND_ORE,
			Blocks.LAPIS_ORE,
			Blocks.DEEPSLATE_LAPIS_ORE,
			Blocks.COAL_ORE,
			Blocks.DEEPSLATE_COAL_ORE,
			Blocks.NETHER_GOLD_ORE,
			Blocks.NETHER_QUARTZ_ORE,
			Blocks.EMERALD_ORE,
			Blocks.DEEPSLATE_EMERALD_ORE,
			Blocks.GILDED_BLACKSTONE
	);
	public static void execute(LevelAccessor world, double x, double y, double z, ItemStack itemStack) {
		BlockPos pos = new BlockPos(x, y, z);
		BlockState blockState = world.getBlockState(pos);
		Block block = blockState.getBlock();

		if (BLACKLIST_BLOCKS.contains(block)) {
			return;
		}

		// Verificamos si el bloque es fundible
		if (world instanceof ServerLevel serverLevel) {
			RecipeManager recipeManager = serverLevel.getRecipeManager(); // Obtenemos el RecipeManager del servidor
			ItemStack blockAsItem = new ItemStack(block.asItem()); // Convertimos el bloque en un item
			SimpleContainer container = new SimpleContainer(blockAsItem);
			Optional<SmeltingRecipe> optionalRecipe = recipeManager.getRecipeFor(RecipeType.SMELTING, container, serverLevel); // Obtenemos la receta correspondiente

			// Verificamos si la receta es válida
			if (optionalRecipe.isPresent()) {
				SmeltingRecipe smeltingRecipe = optionalRecipe.get(); // Obtenemos la receta
				ItemStack result = smeltingRecipe.getResultItem(); // Obtenemos el resultado de la receta

				// Obtenemos el nivel de fortuna del item usado para minar
				int fornuteLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, itemStack);
				int cantidadDeItems = 1;
				if (fornuteLevel > 0) {
					cantidadDeItems += world.getRandom().nextInt(fornuteLevel + 1);
				}

				// Reemplazamos el bloque por aire
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				ItemStack drop = ItemStack.EMPTY;
				if (block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE) {
					drop = new ItemStack(Items.IRON_INGOT, cantidadDeItems);
				} else if (block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_GOLD_ORE) {
					drop = new ItemStack(Items.GOLD_INGOT, cantidadDeItems);
				} else if (block == Blocks.COPPER_ORE || block == Blocks.DEEPSLATE_COPPER_ORE) {
					drop = new ItemStack(Items.COPPER_INGOT, cantidadDeItems);
				} else {
					drop = new ItemStack(result.copy().getItemHolder(), 1);
				}

				// Crear un item en la posición
				if (world instanceof Level _level && !_level.isClientSide()) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, drop);
					entityToSpawn.setPickUpDelay(0);
					_level.addFreshEntity(entityToSpawn);
				}

				// Generamos particulas
				if (world instanceof ServerLevel _level) {
					_level.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 10, 0.5, 0.5, 0.5, 0.1);
				}
			}
		}
	}
}
