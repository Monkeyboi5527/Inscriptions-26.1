// Made with Blockbench 5.1.1
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports

package net.monkeyskl.inscriptions.entity.cilent;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.model.object.armorstand.ArmorStandModel;
import net.minecraft.client.renderer.entity.state.ArmorStandRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.HumanoidArm;

public class DummyModel extends ArmorStandModel {
	private final ModelPart head;
	private final ModelPart Pumpkin;
	private final ModelPart Hat;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;
	private final ModelPart right;
	private final ModelPart left;
	private final ModelPart waist;
	private final ModelPart base;
	public static final ModelLayerLocation LAYER_LOCATION =
			new ModelLayerLocation(Identifier.fromNamespaceAndPath("inscriptions", "dummy"), "main");

	public DummyModel(ModelPart root) {
		super(root);
		this.head = root.getChild("head");
		this.Pumpkin = this.head.getChild("Pumpkin");
		this.Hat = this.head.getChild("Hat");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
		this.right = root.getChild("right_body_stick");
		this.left = root.getChild("left_body_stick");
		this.waist = root.getChild("shoulder_stick");
		this.base = root.getChild("base_plate");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		PartDefinition root = mesh.getRoot();


		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 59)
						.addBox(-1.0F, -7.0F, -1.0F, 2.0F, 7.0F, 2.0F, CubeDeformation.NONE),
				PartPose.offset(0.0F, 1.0F, 0.0F));


		PartDefinition Pumpkin = head.addOrReplaceChild("Pumpkin", CubeListBuilder.create().texOffs(32, 41)
						.addBox(-4.0F, -3.0F, -4.0F, 8.0F, 1.0F, 8.0F, CubeDeformation.NONE),
				PartPose.offset(0.0F, -7.0F, 0.0F));

		Pumpkin.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 50)
						.addBox(-3.0F, -1.0F, -5.0F, 8.0F, 1.0F, 8.0F, CubeDeformation.NONE),
				PartPose.offsetAndRotation(-1.0F, 2.0F, -5.0F, -1.5708F, 0.0F, 0.0F));
		Pumpkin.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 17)
						.addBox(-2.0F, -1.0F, -5.0F, 8.0F, 1.0F, 8.0F, CubeDeformation.NONE),
				PartPose.offsetAndRotation(-2.0F, 2.0F, 4.0F, -1.5708F, 0.0F, 0.0F));
		Pumpkin.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(36, 30)
						.addBox(-3.0F, -1.0F, -6.0F, 8.0F, 1.0F, 10.0F, CubeDeformation.NONE),
				PartPose.offsetAndRotation(4.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.5708F));
		Pumpkin.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 30)
						.addBox(-3.0F, -1.0F, -6.0F, 8.0F, 1.0F, 10.0F, CubeDeformation.NONE),
				PartPose.offsetAndRotation(-5.0F, 0.0F, 1.0F, 0.0F, 0.0F, 1.5708F));

		head.addOrReplaceChild("Hat", CubeListBuilder.create()
						.texOffs(0, 0).addBox(-8.0F, -11.0F, -8.0F, 16.0F, 1.0F, 16.0F, CubeDeformation.NONE)
						.texOffs(0, 41).addBox(-4.0F, -13.0F, -4.0F, 8.0F, 2.0F, 8.0F, CubeDeformation.NONE),
				PartPose.ZERO);


		root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 51)
						.addBox(-6.0F, 0.0F, -1.5F, 12.0F, 3.0F, 3.0F, CubeDeformation.NONE),
				PartPose.ZERO);

		PartDefinition left_arm = root.addOrReplaceChild("left_arm", CubeListBuilder.create(),
				PartPose.offset(5.0F, 2.0F, 0.0F));
		left_arm.addOrReplaceChild("left_arm_r1", CubeListBuilder.create().texOffs(16, 57)
						.addBox(-1.0F, -4.0F, -1.0F, 2.0F, 10.0F, 2.0F, CubeDeformation.NONE),
				PartPose.offsetAndRotation(7.0F, -1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition right_arm = root.addOrReplaceChild("right_arm", CubeListBuilder.create(),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		right_arm.addOrReplaceChild("right_arm_r1", CubeListBuilder.create().texOffs(24, 57)
						.addBox(-1.0F, -6.0F, -1.0F, 2.0F, 10.0F, 2.0F, CubeDeformation.NONE),
				PartPose.offsetAndRotation(-7.0F, -1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 57)
						.addBox(-0.95F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, CubeDeformation.NONE),
				PartPose.offset(1.85F, 12.0F, 0.0F));

		root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(8, 57)
						.addBox(-1.2F, 0.0F, -1.0F, 2.0F, 11.0F, 2.0F, CubeDeformation.NONE),
				PartPose.offset(-1.85F, 12.0F, 0.0F));


		root.addOrReplaceChild("right_body_stick", CubeListBuilder.create().texOffs(40, 59)
						.addBox(1.0F, 3.0F, -1.0F, 2.0F, 7.0F, 2.0F, CubeDeformation.NONE),
				PartPose.ZERO);
		root.addOrReplaceChild("left_body_stick", CubeListBuilder.create().texOffs(48, 59)
						.addBox(-3.0F, 3.0F, -1.0F, 2.0F, 7.0F, 2.0F, CubeDeformation.NONE),
				PartPose.ZERO);
		root.addOrReplaceChild("shoulder_stick", CubeListBuilder.create().texOffs(48, 26)
						.addBox(-4.0F, 10.0F, -1.0F, 8.0F, 2.0F, 2.0F, CubeDeformation.NONE),
				PartPose.ZERO);
		root.addOrReplaceChild("base_plate", CubeListBuilder.create().texOffs(0, 17)
						.addBox(-6.0F, 11.0F, -6.0F, 12.0F, 1.0F, 12.0F, CubeDeformation.NONE),
				PartPose.offset(0.0F, 12.0F, 0.0F));

		return LayerDefinition.create(mesh, 128, 128);
	}

	@Override
	public void setupAnim(ArmorStandRenderState state) {
		super.setupAnim(state);
	}

	@Override
	public void translateToHand(HumanoidRenderState state, HumanoidArm arm, PoseStack poseStack) {
		super.translateToHand(state, arm, poseStack);
	}
}