package net.monkeyskl.inscriptions.entity.cilent;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.client.renderer.entity.layers.WingsLayer;
import net.minecraft.client.renderer.entity.state.ArmorStandRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.monkeyskl.inscriptions.entity.custom.DummyEntity;
import org.jspecify.annotations.Nullable;

public class DummyRenderer extends LivingEntityRenderer<DummyEntity, ArmorStandRenderState, DummyModel> {
    public static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath("inscriptions", "textures/entity/dummy/dummy.png");


    public DummyRenderer(EntityRendererProvider.Context context) {
        super(context, new DummyModel(context.bakeLayer(DummyModel.LAYER_LOCATION)), 0.0F);
        this.model = this.getModel();

        this.addLayer(new HumanoidArmorLayer<>(
                this,
                new ArmorModelSet<>(
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.HEAD_LAYER), EquipmentSlot.HEAD),
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.CHEST_LAYER), EquipmentSlot.CHEST),
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.LEGS_LAYER), EquipmentSlot.LEGS),
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.FEET_LAYER), EquipmentSlot.FEET)
                ),
                new ArmorModelSet<>(
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.HEAD_LAYER), EquipmentSlot.HEAD),
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.CHEST_LAYER), EquipmentSlot.CHEST),
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.LEGS_LAYER), EquipmentSlot.LEGS),
                        DummyArmorModel.createForSlot(context.bakeLayer(DummyArmorModel.FEET_LAYER), EquipmentSlot.FEET)
                ),
                context.getEquipmentRenderer()
        ));
        this.addLayer(new ItemInHandLayer<>(this));
        this.addLayer(new WingsLayer<>(this, context.getModelSet(), context.getEquipmentRenderer()));
        this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
    }

    @Override
    public Identifier getTextureLocation(ArmorStandRenderState state) {
        return TEXTURE;
    }

    @Override
    public ArmorStandRenderState createRenderState() {
        return new ArmorStandRenderState();
    }

    @Override
    public void extractRenderState(DummyEntity entity, ArmorStandRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        HumanoidMobRenderer.extractHumanoidRenderState(entity, state, partialTicks, this.itemModelResolver);
        state.yRot = Mth.rotLerp(partialTicks, entity.yRotO, entity.getYRot());
        state.isMarker = entity.isMarker();
        state.isSmall = entity.isSmall();
        state.showArms = entity.showArms();
        state.showBasePlate = entity.showBasePlate();
        state.bodyPose = entity.getBodyPose();
        state.headPose = entity.getHeadPose();
        state.leftArmPose = entity.getLeftArmPose();
        state.rightArmPose = entity.getRightArmPose();
        state.leftLegPose = entity.getLeftLegPose();
        state.rightLegPose = entity.getRightLegPose();
        state.wiggle = (float)(entity.level().getGameTime() - entity.lastHit) + partialTicks;
    }

    @Override
    public void submit(ArmorStandRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    protected void setupRotations(ArmorStandRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - bodyRot));
        if (state.wiggle < 5.0F) {
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.sin(state.wiggle / 1.5F * (float) Math.PI) * 3.0F));
        }
    }

    @Nullable
    @Override
    protected RenderType getRenderType(ArmorStandRenderState state, boolean isBodyVisible, boolean forceTransparent, boolean appearGlowing) {
        if (!state.isMarker) {
            return super.getRenderType(state, isBodyVisible, forceTransparent, appearGlowing);
        } else {
            Identifier texture = this.getTextureLocation(state);
            if (forceTransparent) {
                return RenderTypes.entityTranslucent(texture, false);
            } else {
                return isBodyVisible ? RenderTypes.entityCutout(texture, false) : null;
            }
        }
    }
}