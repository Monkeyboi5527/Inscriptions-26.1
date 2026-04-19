package net.monkeyskl.inscriptions.recipe;

import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record InscriptionTableRecipe(List<Ingredient> ingredients, ItemStack output)
        implements Recipe<InscriptionTableRecipeInput> {

    
    @Override
    public boolean matches(InscriptionTableRecipeInput input, Level level) {
        if (ingredients.size() != 3) return false;

        for (int i = 0; i < 3; i++) {
            if (!ingredients.get(i).test(input.getItem(i))) {
                return false;
            }
        }

        return true;
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
        NonNullList<Ingredient> list = NonNullList.create();
        list.addAll(ingredients);
        return PlacementInfo.create(list);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }


    public static final RecipeSerializer<InscriptionTableRecipe> SERIALIZER = new RecipeSerializer<>(

            RecordCodecBuilder.mapCodec(inst -> inst.group(

                    Ingredient.CODEC.listOf().fieldOf("ingredients")
                            .forGetter(InscriptionTableRecipe::ingredients),

                    ItemStack.CODEC.fieldOf("result")
                            .forGetter(InscriptionTableRecipe::output)

            ).apply(inst, InscriptionTableRecipe::new)),

            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
                    InscriptionTableRecipe::ingredients,

                    ItemStack.STREAM_CODEC,
                    InscriptionTableRecipe::output,

                    InscriptionTableRecipe::new
            )
    );

    private boolean hasSharpnessFive(ItemStack stack, Level level) {

        Holder<Enchantment> sharpness = level.registryAccess()
                .getOrThrow(Registries.ENCHANTMENT)
                .value().getOrThrow(Enchantments.SHARPNESS);

        var enchants = stack.get(DataComponents.ENCHANTMENTS);
        if (enchants != null && enchants.getLevel(sharpness) >= 5) {
            return true;
        }

        var stored = stack.get(DataComponents.STORED_ENCHANTMENTS);
        if (stored != null && stored.getLevel(sharpness) >= 5) {
            return true;
        }

        return false;
    }
}