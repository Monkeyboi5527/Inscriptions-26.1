package net.monkeyskl.inscriptions.entity.cilent;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.object.armorstand.ArmorStandArmorModel;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;

public class DummyArmorModel extends ArmorStandArmorModel {

    public static final ModelLayerLocation HEAD_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath("inscriptions", "dummy_armor_head"), "main");
    public static final ModelLayerLocation CHEST_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath("inscriptions", "dummy_armor_chest"), "main");
    public static final ModelLayerLocation LEGS_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath("inscriptions", "dummy_armor_legs"), "main");
    public static final ModelLayerLocation FEET_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath("inscriptions", "dummy_armor_feet"), "main");

    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath("inscriptions", "dummy_armor"), "main");
    public static final ModelLayerLocation SMALL_LAYER_LOCATION =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath("inscriptions", "dummy_armor_small"), "main");

    public DummyArmorModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();
        
        root.addOrReplaceChild("head", CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)),
                PartPose.offset(0.0F, 1.0F, 0.0F));

        root.addOrReplaceChild("hat", CubeListBuilder.create(),
                PartPose.offset(0.0F, 1.0F, 0.0F));

        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.ZERO);

        root.addOrReplaceChild("right_arm", CubeListBuilder.create()
                        .texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.offset(-5.0F, 2.0F, 0.0F));

        root.addOrReplaceChild("left_arm", CubeListBuilder.create()
                        .texOffs(40, 16).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.offset(5.0F, 2.0F, 0.0F));

        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.offset(-1.85F, 12.0F, 0.0F));

        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)),
                PartPose.offset(1.85F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    public static LayerDefinition createLegsLayer() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("head", CubeListBuilder.create(),
                PartPose.offset(0.0F, 1.0F, 0.0F));
        root.addOrReplaceChild("hat", CubeListBuilder.create(),
                PartPose.offset(0.0F, 1.0F, 0.0F));
        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
                PartPose.ZERO);
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(),
                PartPose.offset(-5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(),
                PartPose.offset(5.0F, 2.0F, 0.0F));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
                PartPose.offset(-1.85F, 12.0F, 0.0F));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create()
                        .texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.1F)),
                PartPose.offset(1.85F, 12.0F, 0.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    public static DummyArmorModel createForSlot(ModelPart root, EquipmentSlot slot) {
        DummyArmorModel model = new DummyArmorModel(root);
        model.head.visible = false;
        model.hat.visible = false;
        model.body.visible = false;
        model.leftArm.visible = false;
        model.rightArm.visible = false;
        model.leftLeg.visible = false;
        model.rightLeg.visible = false;

        // Show only relevant parts
        switch (slot) {
            case HEAD -> { model.head.visible = true; model.hat.visible = true; }
            case CHEST -> { model.body.visible = true; model.leftArm.visible = true; model.rightArm.visible = true; }
            case LEGS -> { model.body.visible = true; model.leftLeg.visible = true; model.rightLeg.visible = true; }
            case FEET -> { model.leftLeg.visible = true; model.rightLeg.visible = true; }
        }
        return model;
    }

    public static final ArmorModelSet<ModelLayerLocation> ARMOR_MODEL_SET = new ArmorModelSet<>(
            HEAD_LAYER,
            CHEST_LAYER,
            LEGS_LAYER,
            FEET_LAYER
    );
}
