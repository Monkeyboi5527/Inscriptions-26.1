package net.monkeyskl.inscriptions.menu.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.monkeyskl.inscriptions.block.ModBlocks;
import net.monkeyskl.inscriptions.menu.ModMenuTypes;
import net.monkeyskl.inscriptions.recipe.ModRecipes;
import net.monkeyskl.inscriptions.recipe.custom.TestCraftingRecipe;
import net.monkeyskl.inscriptions.recipe.custom.TestCraftingRecipeInput;

import java.util.List;
import java.util.Optional;

public class TestCraftingMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess access;
    private final Level level;

    private final Container input = new SimpleContainer(2) {
        @Override
        public void setChanged() {
            super.setChanged();
            TestCraftingMenu.this.slotsChanged(this);
        }
    };

    private final ResultContainer output = new ResultContainer();

    public TestCraftingMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL);
    }

    public TestCraftingMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
        super(ModMenuTypes.TEST_CRAFTING, containerId);
        this.level = inventory.player.level();
        this.access = access;

        this.addSlot(new Slot(input, 0, 26, 34));

        this.addSlot(new Slot(output, 0, 80, 34) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }

            @Override
            public void onTake(Player player, ItemStack stack) {
                TestCraftingMenu.this.onTake(player, stack);
            }
        });

        addStandardInventorySlots(inventory.player.getInventory(), 8, 84);
    }

    @Override
    public void slotsChanged(Container container) {
        if (container == input && level instanceof ServerLevel serverLevel) {

            TestCraftingRecipeInput recipeInput =
                    new TestCraftingRecipeInput(input.getItem(0), input.getItem(1));

            Optional<RecipeHolder<TestCraftingRecipe>> recipe =
                    serverLevel.recipeAccess().getRecipeFor(
                            ModRecipes.TEST_CRAFTING_RECIPE_TYPE,
                            recipeInput,
                            serverLevel
                    );

            if (recipe.isPresent()) {
                output.setItem(0, recipe.get().value().assemble(recipeInput));
                output.setRecipeUsed(recipe.get());
            } else {
                output.clearContent();
                output.setRecipeUsed(null);
            }
        }
    }

    public void onTake(Player player, ItemStack stack) {
        stack.onCraftedBy(player, stack.getCount());

        output.awardUsedRecipes(player, List.of(input.getItem(0)));

        input.removeItem(0, 1);

        slotsChanged(input);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack original = slot.getItem();
            newStack = original.copy();

            if (index == 1) {
                if (!this.moveItemStackTo(original, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(original, newStack);
            }

            else if (index >= 2) {
                if (!this.moveItemStackTo(original, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            }

            else {
                if (!this.moveItemStackTo(original, 2, this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, original);
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.TEST_CRAFTING);
    }
}