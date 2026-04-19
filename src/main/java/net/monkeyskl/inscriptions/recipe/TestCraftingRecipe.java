package net.monkeyskl.inscriptions.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class TestCraftingRecipe implements Recipe<TestCraftingRecipeInput>{
    private final Ingredient inputItem;
    private final ItemStackTemplate output;

    private TestCraftingRecipe(Ingredient inputItem, ItemStackTemplate output) {
        this.inputItem = inputItem;
        this.output = output;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    public Ingredient getInputItem() {
        return inputItem;
    }

    @Override
    public boolean matches(TestCraftingRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }

        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(TestCraftingRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public RecipeSerializer<? extends Recipe<TestCraftingRecipeInput>> getSerializer() {
        return ModRecipes.TEST_CRAFTING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<TestCraftingRecipeInput>> getType() {
        return ModRecipes.TEST_CRAFTING_RECIPE_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(inputItem);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static final MapCodec<TestCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("input")
                            .forGetter(TestCraftingRecipe::getInputItem),

                    ItemStackTemplate.CODEC.fieldOf("result")
                            .forGetter(TestCraftingRecipe::getOutput)
            ).apply(instance, TestCraftingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, TestCraftingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    TestCraftingRecipe::getInputItem,

                    ItemStackTemplate.STREAM_CODEC,
                    TestCraftingRecipe::getOutput,

                    TestCraftingRecipe::new
            );

}