package net.monkeyskl.inscriptions.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.monkeyskl.inscriptions.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {


    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, ModBlocks.INSCRIPTION_TABLE)
                        .pattern("xxx")
                        .pattern("yyy")
                        .pattern("zzz")
                        .define('x', Items.GRASS_BLOCK)
                        .define('y', Items.NETHERRACK)
                        .define('z', Items.END_STONE)
                        .unlockedBy("has_end_stone", has(Items.END_STONE))
                        .save(output);
            }
        };
    }

    @Override
    public String getName() {
        return "Inscriptions Recipes";
    }
}
