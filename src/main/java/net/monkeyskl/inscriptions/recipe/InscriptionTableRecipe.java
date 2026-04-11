package net.monkeyskl.inscriptions.recipe;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record InscriptionTableRecipe(Ingredient inputItem, ItemStack output) implements Recipe<InscriptionTableRecipeInput> {

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.inputItem);
        return list;
    }

    @Override
    public boolean matches(InscriptionTableRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(InscriptionTableRecipeInput input) {
        return output.copy();
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
    public RecipeSerializer<? extends Recipe<InscriptionTableRecipeInput>> getSerializer() {
        return ModRecipes.INSCRIPTION_TABLE_RECIPE_SERIALIZER;
    }
    

    @Override
    public RecipeType<? extends Recipe<InscriptionTableRecipeInput>> getType() {
        return ModRecipes.INSCRIPTION_TABLE_RECIPE_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(inputItem);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static final RecipeSerializer<InscriptionTableRecipe> SERIALIZER = new RecipeSerializer<>(
            RecordCodecBuilder.mapCodec(inst -> inst.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(InscriptionTableRecipe::inputItem),
                    ItemStack.CODEC.fieldOf("result").forGetter(InscriptionTableRecipe::output)
            ).apply(inst, InscriptionTableRecipe::new)),

            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, InscriptionTableRecipe::inputItem,
                    ItemStack.STREAM_CODEC, InscriptionTableRecipe::output,
                    InscriptionTableRecipe::new)
    );
}
