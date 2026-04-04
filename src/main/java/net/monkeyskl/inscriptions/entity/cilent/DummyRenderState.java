package net.monkeyskl.inscriptions.entity.cilent;

import net.minecraft.client.renderer.entity.state.ArmorStandRenderState;
import net.minecraft.core.Rotations;
import net.monkeyskl.inscriptions.entity.custom.DummyEntity;

public class DummyRenderState extends ArmorStandRenderState {
    public float yRot;
    public float wiggle;
    public boolean isMarker;
    public boolean isSmall;
    public boolean showArms;
    public boolean showBasePlate = true;
    public Rotations headPose = DummyEntity.DEFAULT_HEAD_POSE;
    public Rotations bodyPose = DummyEntity.DEFAULT_BODY_POSE;
    public Rotations leftArmPose = DummyEntity.DEFAULT_LEFT_ARM_POSE;
    public Rotations rightArmPose = DummyEntity.DEFAULT_RIGHT_ARM_POSE;
    public Rotations leftLegPose = DummyEntity.DEFAULT_LEFT_LEG_POSE;
    public Rotations rightLegPose = DummyEntity.DEFAULT_RIGHT_LEG_POSE;
}
