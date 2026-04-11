package net.monkeyskl.inscriptions.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class InscriptionTableBlockEntityRenderState extends BlockEntityRenderState {
    public float rotation;
    public ItemStack itemStack = ItemStack.EMPTY;
    public ItemDisplayContext itemTransform = ItemDisplayContext.FIXED;
    public final ItemStackRenderState itemRenderState = new ItemStackRenderState();
}