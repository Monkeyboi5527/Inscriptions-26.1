package net.monkeyskl.inscriptions.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Display;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.monkeyskl.inscriptions.block.entity.custom.InscriptionTableBlockEntity;

public class InscriptionTableBlockEntityRenderState extends BlockEntityRenderState {
    public BlockPos lightPosition;
    public ServerLevel blockEntityServerLevel;
    public float rotation;
    ItemStack itemStack;
    ItemDisplayContext itemTransform;
    
    final Display.ItemDisplay.ItemRenderState itemRenderState = new Display.ItemDisplay.ItemRenderState(itemStack, itemTransform);

}
