package net.monkeyskl.inscriptions.block.entity.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.monkeyskl.inscriptions.block.custom.InscriptionTableBlock;
import net.monkeyskl.inscriptions.block.entity.ImplementedInventory;
import org.jspecify.annotations.Nullable;

public class InscriptionTableBlockEntity extends BaseEntityBlock implements ImplementedInventory {
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(2, ItemStack.EMPTY);
    public static final MapCodec<InscriptionTableBlockEntity> CODEC = InscriptionTableBlock.simpleCodec(InscriptionTableBlockEntity::new);

    public InscriptionTableBlockEntity(Properties properties) {
        super(properties);
    }


    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        return null;
    }
}
