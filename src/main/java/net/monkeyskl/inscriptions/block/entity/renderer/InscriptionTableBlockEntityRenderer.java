package net.monkeyskl.inscriptions.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.monkeyskl.inscriptions.block.entity.custom.InscriptionTableBlockEntity;

public class InscriptionTableBlockEntityRenderer implements BlockEntityRenderer<InscriptionTableBlockEntity, InscriptionTableBlockEntityRenderState> {

    @Override
    public InscriptionTableBlockEntityRenderState createRenderState() {
        return new InscriptionTableBlockEntityRenderState();
    }

    @Override
    public void submit(InscriptionTableBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        
    }

}
