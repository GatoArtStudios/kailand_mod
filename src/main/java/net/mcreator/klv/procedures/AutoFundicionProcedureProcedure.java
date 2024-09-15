package net.mcreator.klv.procedures;

import net.minecraft.util.datafix.fixes.FurnaceRecipeFix;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
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

import java.util.Optional;

public class AutoFundicionProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		BlockPos pos = new BlockPos(x, y, z);
		BlockState blockState = world.getBlockState(pos);
		Block block = blockState.getBlock();

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

				// Reemplazamos el bloque por aire
				world.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);

				// Crear un item en la posición
				if (world instanceof Level _level && !_level.isClientSide()) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, result.copy());
					entityToSpawn.setPickUpDelay(0);
					_level.addFreshEntity(entityToSpawn);
				}

				// Generamos particulas
				if (world instanceof ServerLevel _level) {
					_level.sendParticles(ParticleTypes.SMALL_FLAME, x, y, z, 10, 0.5, 0.5, 0.5, 0.1);
				}

				// Damos 1/3 de experiencia que normalmente daria un horno al fundir x bloque
				float experience = smeltingRecipe.getExperience();
				if (world instanceof ServerLevel _serverLevel) {
					_serverLevel.players().forEach(player -> {
						if (player.distanceToSqr(x, y, z) < 64) {
							player.giveExperiencePoints((int) (experience / 3));
						}
					});
				}
			}
		}
	}
}
