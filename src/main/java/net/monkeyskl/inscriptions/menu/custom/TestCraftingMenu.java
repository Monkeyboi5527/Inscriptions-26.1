package net.monkeyskl.inscriptions.menu.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.monkeyskl.inscriptions.block.ModBlocks;
import net.monkeyskl.inscriptions.block.entity.custom.TestCraftingBlockEntity;
import net.monkeyskl.inscriptions.enchantment.ModEnchantments;
import net.monkeyskl.inscriptions.menu.ModMenuTypes;
import net.monkeyskl.inscriptions.recipe.ModRecipes;
import net.monkeyskl.inscriptions.recipe.custom.TestCraftingRecipe;
import net.monkeyskl.inscriptions.recipe.custom.TestCraftingRecipeInput;

import java.util.List;
import java.util.Optional;

public class TestCraftingMenu extends AbstractContainerMenu {

    private ContainerLevelAccess access;
    private Level level;

    private Container input;
    private final ResultContainer output = new ResultContainer();

    public TestCraftingMenu(int containerId, Inventory inventory, TestCraftingBlockEntity blockEntity) {
        super(ModMenuTypes.TEST_CRAFTING, containerId);

        this.level = inventory.player.level();
        this.input = blockEntity;
        this.access = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());

        this.addSlot(new Slot(input, 0, 26, 34) {
            @Override
            public void setChanged() {
                super.setChanged();
                TestCraftingMenu.this.slotsChanged(input);
            }
        });

        this.addSlot(new Slot(input, 1, 134, 34) {
            @Override
            public void setChanged() {
                super.setChanged();
                TestCraftingMenu.this.slotsChanged(input);
            }
        });

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

        addStandardInventorySlots(inventory, 8, 84);
    }

    public TestCraftingMenu(int containerId, Inventory inventory) {
        super(ModMenuTypes.TEST_CRAFTING, containerId);

        this.level = inventory.player.level();
        this.access = ContainerLevelAccess.NULL;
        this.input = new SimpleContainer(2);


        this.addSlot(new Slot(input, 0, 26, 34));
        this.addSlot(new Slot(input, 1, 134, 34));

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

        addStandardInventorySlots(inventory, 8, 84);
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);

        if (!level.isClientSide() && container == input) {
            updateResult();
        }
    }

    private void updateResult() {
        if (!(level instanceof ServerLevel serverLevel)) return;

        ItemStack stack = input.getItem(0);
        ItemEnchantments enchantments =
                stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);

        int sharpnessLevel =
                enchantments.getLevel(
                        serverLevel.registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(Enchantments.SHARPNESS)
                );

        boolean sharp = sharpnessLevel >= 5;

        TestCraftingRecipeInput recipeInput =
                new TestCraftingRecipeInput(input.getItem(0), input.getItem(1));

        Optional<RecipeHolder<TestCraftingRecipe>> recipe =
                serverLevel.recipeAccess().getRecipeFor(
                        ModRecipes.TEST_CRAFTING_RECIPE_TYPE,
                        recipeInput,
                        serverLevel
                );

        if (recipe.isPresent()) {
            if (sharp) {
                ItemStack book = new ItemStack(Items.ENCHANTED_BOOK);

                Holder<Enchantment> vorpal =
                        serverLevel.registryAccess()
                                .lookupOrThrow(Registries.ENCHANTMENT)
                                .getOrThrow(ModEnchantments.VORPAL);

                ItemEnchantments.Mutable mutable =
                        new ItemEnchantments.Mutable(ItemEnchantments.EMPTY);

                mutable.set(vorpal, 1);

                book.set(DataComponents.STORED_ENCHANTMENTS, mutable.toImmutable());

                output.setItem(0, book);
            }
            output.setItem(0, recipe.get().value().assemble(recipeInput));
            output.setRecipeUsed(recipe.get());
        } else {
            output.clearContent();
            output.setRecipeUsed(null);
        }

        broadcastChanges();
    }

    public void onTake(Player player, ItemStack stack) {
        stack.onCraftedBy(player, stack.getCount());

        output.awardUsedRecipes(player, List.of(input.getItem(0), input.getItem(1)));

        input.removeItem(0, 1);
        input.removeItem(1, 1);

        slotsChanged(input);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        int containerSize = 3;

        if (slot != null && slot.hasItem()) {
            ItemStack original = slot.getItem();
            newStack = original.copy();

            if (index < containerSize) {
                if (!this.moveItemStackTo(original, containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }

            else {
                if (!this.moveItemStackTo(original, 0, containerSize, false)) {
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