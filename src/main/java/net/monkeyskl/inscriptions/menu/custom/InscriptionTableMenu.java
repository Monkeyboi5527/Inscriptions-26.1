package net.monkeyskl.inscriptions.menu.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.monkeyskl.inscriptions.block.ModBlocks;
import net.monkeyskl.inscriptions.menu.ModMenuTypes;
import net.monkeyskl.inscriptions.recipe.custom.InscriptionTableRecipeInput;
import net.monkeyskl.inscriptions.recipe.ModRecipes;

public class InscriptionTableMenu extends AbstractContainerMenu {
    private final Inventory inventory;
    private final ContainerLevelAccess access;
    private final Container container;


    public InscriptionTableMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, ContainerLevelAccess.NULL, new SimpleContainer(3));
    }

    public InscriptionTableMenu(int containerId, Inventory inventory, ContainerLevelAccess access, Container container) {
        super(ModMenuTypes.INSCRIPTION_TABLE, containerId);
        this.inventory = inventory;
        this.access = access;
        this.container = new SimpleContainer(3) {
            @Override
            public void setChanged() {
                super.setChanged();
                InscriptionTableMenu.this.slotsChanged(this);
            }
        };

        this.addSlot(new Slot(this.container, 0, 26, 34));
        this.addSlot(new Slot(this.container, 1, 80, 34));
        this.addSlot(new Slot(this.container, 2, 134, 34));

        addStandardInventorySlots(inventory.player.getInventory(), 8, 84);
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        if (!(container instanceof SimpleContainer)) return;

        if (this.access == ContainerLevelAccess.NULL) return;

        this.access.execute((level, pos) -> {

            if (!(level instanceof ServerLevel serverLevel)) return;

            InscriptionTableRecipeInput input =
                    new InscriptionTableRecipeInput(
                            container.getItem(0),
                            container.getItem(1),
                            container.getItem(2)
                    );

            var optional = serverLevel.recipeAccess().getRecipeFor(
                    ModRecipes.INSCRIPTION_TABLE_RECIPE_TYPE,
                    input,
                    serverLevel
            );

            if (optional.isPresent()) {
                var recipe = optional.get();

                ItemStack result = recipe.value().assemble(input);


                container.setItem(2, result);
            } else {
                container.setItem(2, ItemStack.EMPTY);
            }
        });
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);

        if (slot != null && slot.hasItem()) {
            ItemStack original = slot.getItem();
            newStack = original.copy();

            int containerSize = this.container.getContainerSize();

            if (index < containerSize) {
                if (!this.moveItemStackTo(original, containerSize, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            }

            else {
                boolean moved = false;

                for (int i = 0; i < containerSize; i++) {
                    Slot target = this.slots.get(i);

                    if (!target.hasItem()) {
                        ItemStack single = original.copyWithCount(1);
                        target.setByPlayer(single);
                        original.shrink(1);
                        moved = true;
                        break;
                    }
                }

                if (!moved) {
                    return ItemStack.EMPTY;
                }
            }

            if (original.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            slot.onTake(player, original);
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player, ModBlocks.INSCRIPTION_TABLE);
    }

}
