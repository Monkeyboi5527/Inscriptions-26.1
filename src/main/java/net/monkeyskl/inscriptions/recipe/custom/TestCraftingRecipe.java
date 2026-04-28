package net.monkeyskl.inscriptions.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.monkeyskl.inscriptions.recipe.ModRecipes;

public class TestCraftingRecipe implements Recipe<TestCraftingRecipeInput>{
    private final Ingredient baseItem;
    private final Ingredient upgradeItem;
    private final ItemStackTemplate output;

    private TestCraftingRecipe(Ingredient baseItem,Ingredient upgradeItem ,ItemStackTemplate output) {
        this.baseItem = baseItem;
        this.upgradeItem = upgradeItem;
        this.output = output;
    }

    public ItemStackTemplate getOutput() {
        return output;
    }

    public Ingredient getBaseItem() {
        return baseItem;
    }

    public Ingredient getUpgradeItem() {
        return upgradeItem;
    }

    @Override
    public boolean matches(TestCraftingRecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        ItemStack stack = input.baseItem();

        boolean itemMatch = baseItem.test(stack);

        Registry<Enchantment> registry = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
        Holder<Enchantment> SHARPNESS = registry.getOrThrow(Enchantments.SHARPNESS);
        boolean hasSharpness5 = stack.getEnchantments().getLevel(SHARPNESS) == 5;

        return itemMatch && hasSharpness5 && upgradeItem.test(input.upgradeItem());
    }

    @Override
    public ItemStack assemble(TestCraftingRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "test_crafting";
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
        return PlacementInfo.create(baseItem);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static final MapCodec<TestCraftingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("baseItem")
                            .forGetter(TestCraftingRecipe::getBaseItem),

                    Ingredient.CODEC.fieldOf("upgradeItem")
                            .forGetter(TestCraftingRecipe::getUpgradeItem),

                    ItemStackTemplate.CODEC.fieldOf("result")
                            .forGetter(TestCraftingRecipe::getOutput)
            ).apply(instance, TestCraftingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, TestCraftingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    TestCraftingRecipe::getBaseItem,

                    Ingredient.CONTENTS_STREAM_CODEC,
                    TestCraftingRecipe::getUpgradeItem,

                    ItemStackTemplate.STREAM_CODEC,
                    TestCraftingRecipe::getOutput,

                    TestCraftingRecipe::new
            );

}