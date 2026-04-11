package net.monkeyskl.inscriptions.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.monkeyskl.inscriptions.block.entity.custom.InscriptionTableBlockEntity;
import org.jspecify.annotations.Nullable;

public class InscriptionTableBlockEntityRenderer implements BlockEntityRenderer<InscriptionTableBlockEntity, InscriptionTableBlockEntityRenderState> {

    @Override
    public InscriptionTableBlockEntityRenderState createRenderState() {
        return new InscriptionTableBlockEntityRenderState();
    }

    @Override
    public void submit(InscriptionTableBlockEntityRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if (state.itemRenderState == null) return;

        poseStack.pushPose();
        poseStack.translate(0.5f, 0.62f, 0.5f);
        poseStack.scale(0.3f, 0.3f, 0.3f);
        poseStack.mulPose(Axis.XP.rotationDegrees(90));

        state.itemRenderState.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    @Override
    public void extractRenderState(InscriptionTableBlockEntity blockEntity, InscriptionTableBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        Minecraft.getInstance().getItemModelResolver().updateForTopItem(
                state.itemRenderState,
                blockEntity.getRenderStack(),
                ItemDisplayContext.FIXED,
                null,
                null,
                0
        );
    }

}
