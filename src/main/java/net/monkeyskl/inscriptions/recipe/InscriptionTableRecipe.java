package net.monkeyskl.inscriptions.recipe;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public record InscriptionTableRecipe(Ingredient slot1, Ingredient slot2, Ingredient slot3, ItemStack output) implements Recipe<InscriptionTableRecipeInput> {

//    @Override
//    public boolean matches(InscriptionTableRecipeInput input, Level level) {
//        if (!slot1.test(input.getItem(0))) return false;
//        if (!slot2.test(input.getItem(1))) return false;
//        if (!slot3.test(input.getItem(2))) return false;
//
//        ItemStack stack = input.getItem(0);
//        if (stack.isEmpty()) return false;
//
//        return hasSharpnessFive(stack, level);
//    }

    @Override
    public boolean matches(InscriptionTableRecipeInput input, Level level) {
        return !input.getItem(0).isEmpty();
    }

    // TESTING
    @Override
    public ItemStack assemble(InscriptionTableRecipeInput input) {
        return new ItemStack(Items.DIAMOND);
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

                    Ingredient.CODEC.fieldOf("slot1").forGetter(InscriptionTableRecipe::slot1),
                    Ingredient.CODEC.fieldOf("slot2").forGetter(InscriptionTableRecipe::slot2),
                    Ingredient.CODEC.fieldOf("slot3").forGetter(InscriptionTableRecipe::slot3),
                    ItemStack.CODEC.fieldOf("result").forGetter(InscriptionTableRecipe::output)

            ).apply(inst, InscriptionTableRecipe::new)),

            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, InscriptionTableRecipe::slot1,
                    Ingredient.CONTENTS_STREAM_CODEC, InscriptionTableRecipe::slot2,
                    Ingredient.CONTENTS_STREAM_CODEC, InscriptionTableRecipe::slot3,
                    ItemStack.STREAM_CODEC, InscriptionTableRecipe::output,
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
