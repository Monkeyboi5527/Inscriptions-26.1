package net.monkeyskl.inscriptions.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.ContainerUser;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.monkeyskl.inscriptions.block.entity.ImplementedInventory;
import net.monkeyskl.inscriptions.block.entity.ModBlockEntities;
import net.monkeyskl.inscriptions.menu.custom.InscriptionTableMenu;
import org.jspecify.annotations.Nullable;

public class InscriptionTableBlockEntity extends BlockEntity implements ImplementedInventory, MenuProvider {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);



    public InscriptionTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INSCRIPTION_TABLE_BE, pos, state);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
    }

    @Override
    public void startOpen(ContainerUser containerUser) {
        ImplementedInventory.super.startOpen(containerUser);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("item.inscriptions.inscription_table");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new InscriptionTableMenu(containerId, inventory);
    }

}