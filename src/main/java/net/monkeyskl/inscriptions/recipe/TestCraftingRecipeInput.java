package net.monkeyskl.inscriptions.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record TestCraftingRecipeInput(ItemStack input) implements RecipeInput {

    @Override
    public ItemStack getItem(int index) {
        return switch (index){
            case 0 -> input;
            default -> ItemStack.EMPTY;
        };
    }

    @Override
    public int size() {
        return 1;
    }

}