package net.monkeyskl.inscriptions.recipe;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.Optional;

public record InscriptionTableRecipe(Ingredient slot1, Ingredient slot2, Ingredient slot3, ItemStack output) implements Recipe<InscriptionTableRecipeInput> {

    @Override
    public boolean matches(InscriptionTableRecipeInput input, Level level) {

        if (slot1 != null && !slot1.test(input.getItem(0))) return false;
        if (slot1 == null && !input.getItem(0).isEmpty()) return false;

        if (slot2 != null && !slot2.test(input.getItem(1))) return false;
        if (slot2 == null && !input.getItem(1).isEmpty()) return false;

        if (slot3 != null && !slot3.test(input.getItem(2))) return false;
        if (slot3 == null && !input.getItem(2).isEmpty()) return false;

        return true;
    }

    @Override
    public ItemStack assemble(InscriptionTableRecipeInput input) {
        // So broken right now
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

        if (slot1 != null) list.add(slot1);
        if (slot2 != null) list.add(slot2);
        if (slot3 != null) list.add(slot3);

        return PlacementInfo.create(list);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static final RecipeSerializer<InscriptionTableRecipe> SERIALIZER = new RecipeSerializer<>(

            RecordCodecBuilder.mapCodec(inst -> inst.group(

                    Ingredient.CODEC.optionalFieldOf("slot1")
                            .forGetter(r -> Optional.ofNullable(r.slot1())),

                    Ingredient.CODEC.optionalFieldOf("slot2")
                            .forGetter(r -> Optional.ofNullable(r.slot2())),

                    Ingredient.CODEC.optionalFieldOf("slot3")
                            .forGetter(r -> Optional.ofNullable(r.slot3())),

                    ItemStack.CODEC.fieldOf("result")
                            .forGetter(InscriptionTableRecipe::output)

            ).apply(inst, (s1, s2, s3, output) ->
                    new InscriptionTableRecipe(
                            s1.orElse(null),
                            s2.orElse(null),
                            s3.orElse(null),
                            output
                    )
            )),

            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    r -> r.slot1 == null ? Ingredient.of(Items.STONE) : r.slot1,

                    Ingredient.CONTENTS_STREAM_CODEC,
                    r -> r.slot2 == null ? Ingredient.of(Items.STONE) : r.slot2,

                    Ingredient.CONTENTS_STREAM_CODEC,
                    r -> r.slot3 == null ? Ingredient.of(Items.STONE) : r.slot3,

                    ItemStack.STREAM_CODEC,
                    r -> r.output,

                    (s1, s2, s3, output) ->
                            new InscriptionTableRecipe(s1, s2, s3, output)
            )
    );
}
