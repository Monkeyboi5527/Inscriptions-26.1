package net.monkeyskl.inscriptions.recipe;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.monkeyskl.inscriptions.Inscriptions;
import net.monkeyskl.inscriptions.recipe.custom.InscriptionTableRecipe;
import net.monkeyskl.inscriptions.recipe.custom.TestCraftingRecipe;

public class ModRecipes {

    public static final RecipeSerializer<InscriptionTableRecipe> INSCRIPTION_TABLE_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "inscription_table"),
            InscriptionTableRecipe.SERIALIZER);

    public static final RecipeType<InscriptionTableRecipe> INSCRIPTION_TABLE_RECIPE_TYPE =
            Registry.register(BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "inscription_table"),
                    new RecipeType<InscriptionTableRecipe>() {
                        @Override
                        public String toString() {
                            return "inscription_table";
                        }
                    });

    public static final RecipeSerializer<TestCraftingRecipe> TEST_CRAFTING_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "test_crafting"),
            new RecipeSerializer<>(TestCraftingRecipe.CODEC, TestCraftingRecipe.STREAM_CODEC)
    );

    public static final RecipeType<TestCraftingRecipe> TEST_CRAFTING_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            Identifier.fromNamespaceAndPath(Inscriptions.MOD_ID, "test_crafting"),
            new RecipeType<TestCraftingRecipe>() { }
    );


    public static void registerModRecipes() {
        Inscriptions.LOGGER.info("Registering ModRecipes");
    }
}
