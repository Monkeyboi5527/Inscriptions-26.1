package net.monkeyskl.inscriptions.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.monkeyskl.inscriptions.block.entity.ImplementedInventory;
import net.monkeyskl.inscriptions.block.entity.ModBlockEntities;

public class InscriptionTableBlockEntity extends BlockEntity implements ImplementedInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);

    public InscriptionTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INSCRIPTION_TABLE_BE, pos, state);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }



}