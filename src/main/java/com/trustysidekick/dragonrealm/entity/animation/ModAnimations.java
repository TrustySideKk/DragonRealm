package com.trustysidekick.dragonrealm.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class ModAnimations {
    public static final Animation PORCUPINE_WALK = Animation.Builder.create(2.5f).looping()
            .addBoneAnimation("torso",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, AnimationHelper.createTranslationalVector(0f, 0f, -0.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.9583434f, AnimationHelper.createTranslationalVector(0f, 0f, -0.05f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0.05f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2.125f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_front_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0834333f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.625f, AnimationHelper.createRotationalVector(-11f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2.0834335f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_front_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.20834334f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5416766f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0834333f, AnimationHelper.createRotationalVector(-11f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.2916767f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_back_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.3433333f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createRotationalVector(-11f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2.2916765f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_back_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.875f, AnimationHelper.createRotationalVector(-11f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5834333f, AnimationHelper.createRotationalVector(-7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2.0834335f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation PORCUPINE_IDLE = Animation.Builder.create(2f).looping()
            .addBoneAnimation("torso",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0.35f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.875f, AnimationHelper.createTranslationalVector(0f, 0.4f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0f, 0.4f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(0f, 0.35f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("torso",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("skull",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.3433333f, AnimationHelper.createTranslationalVector(0f, 0.1f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(0f, 0.2f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.9583433f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("skull",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(2f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(2f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(-2f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createRotationalVector(-2f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_front_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.7083434f, AnimationHelper.createTranslationalVector(0f, 0.1f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_front_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.9167666f, AnimationHelper.createTranslationalVector(0f, 0.1f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.7916767f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_back_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.125f, AnimationHelper.createTranslationalVector(0f, 0.1f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.7916767f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_back_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.2916767f, AnimationHelper.createTranslationalVector(0f, 0.1f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.9167667f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
    public static final Animation PORCUPINE_ATTACK = Animation.Builder.create(2f).looping()
            .addBoneAnimation("torso",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.6766666f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createTranslationalVector(0f, 0.5f, -1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0f, 0.5f, -1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5416767f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("torso",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5416767f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.6766667f, AnimationHelper.createRotationalVector(5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("skull",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5416767f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("skull",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.4167667f, AnimationHelper.createRotationalVector(15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createRotationalVector(15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createRotationalVector(15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5416767f, AnimationHelper.createRotationalVector(15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.6766667f, AnimationHelper.createRotationalVector(15f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.875f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("hair",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.25f, 1.25f, 1.25f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.25f, 1.25f, 1.25f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes2",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.25f, 1.25f, 1.25f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.25f, 1.25f, 1.25f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes3",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes4",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes5",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes6",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes7",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("spikes8",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.0416767f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.15f, 1.15f, 1.15f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_front_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -0.25f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, -0.25f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(0f, -0.25f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_front_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_front_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -0.25f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, -0.25f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(0f, -0.25f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_front_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createRotationalVector(7.5f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_back_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("right_back_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_back_leg",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(0f, -0.5f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("left_back_leg",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.5f, AnimationHelper.createRotationalVector(10f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();

    public static final Animation DRAGONWHELP_IDLE = Animation.Builder.create(2.5F).looping()
		.addBoneAnimation("torso", new Transformation(Transformation.Targets.TRANSLATE,
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.875F, AnimationHelper.createTranslationalVector(0.0F, 0.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_left", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("neck", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-0.1494F, -0.0167F, 0.009F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-0.2534F, -0.0301F, 0.0171F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.3612F, -0.0274F, 0.0089F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(-0.1966F, -0.0125F, 0.0024F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(2.2821F, 3.052F, -0.4901F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(3.9944F, 3.6028F, -0.5094F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(5.2307F, 1.5773F, -0.1683F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(2.6383F, 0.7801F, -0.1007F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right_inner", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0014F, -0.0015F, 0.0002F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0103F, -0.0106F, 0.0013F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0003F, -0.0003F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0008F, -0.0008F, 0.0001F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(-0.0046F, 0.0047F, -0.0006F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createRotationalVector(0.0103F, -0.0106F, 0.0013F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right_middle", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-0.0011F, 0.0012F, -0.0001F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-0.0077F, 0.0083F, -0.0009F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-0.0002F, 0.0002F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.0006F, 0.0006F, -0.0001F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0034F, -0.0037F, 0.0004F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createRotationalVector(-0.0077F, 0.0083F, -0.0009F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right_outer", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(1.0064F, -0.529F, 0.1749F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.816F, -0.0491F, 0.0935F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.5919F, -0.0882F, 0.0748F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.7017F, -0.2087F, 0.1021F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.5783F, -0.2228F, 0.091F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.5F, AnimationHelper.createRotationalVector(0.816F, -0.0491F, 0.0935F), Transformation.Interpolations.LINEAR)
            ))
            .build();

    public static final Animation DRAGONWHELP_WALK = Animation.Builder.create(2.0F).looping()
		.addBoneAnimation("torso", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 2.5F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.875F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("torso", new Transformation(Transformation.Targets.TRANSLATE,
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.375F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.8333F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.2917F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.7083F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right_inner", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0021F, -0.0022F, 0.0003F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0021F, -0.0022F, 0.0003F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.0049F, 0.005F, -0.0006F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(0.0033F, -0.0034F, 0.0004F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right_middle", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-0.0016F, 0.0017F, -0.0002F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-0.0016F, 0.0017F, -0.0002F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0036F, -0.0039F, 0.0004F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-0.0025F, 0.0027F, -0.0003F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -2.5F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_left", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 2.5F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(2.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail1", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-12.7666F, 0.03F, -0.0043F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-6.706F, 11.138F, -1.0169F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-12.0321F, 1.4283F, -0.1973F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-4.5101F, -7.321F, 0.5258F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-11.7587F, -1.4862F, 0.2017F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail2", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-0.5483F, 0.0014F, -0.0001F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-2.9078F, 4.8929F, -0.2877F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-0.884F, 0.1112F, -0.0046F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-2.7845F, -4.503F, 0.2599F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-0.9222F, -0.1231F, 0.0051F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail3", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-2.8228F, 0.0021F, -0.0001F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-7.0652F, 8.4235F, -0.521F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-3.3356F, 0.1678F, -0.0049F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-7.262F, -8.3557F, 0.5312F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-3.3949F, -0.186F, 0.0055F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail4", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(1.7455F, 0.002F, -0.0001F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-2.7932F, 8.7388F, -0.6121F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(1.2641F, 0.1575F, -0.0054F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-3.4505F, -9.3154F, 0.7065F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(1.208F, -0.1745F, 0.0061F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail5", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.7921F, 0.0014F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-8.8184F, 5.9059F, -0.1865F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-6.1056F, 0.1061F, -0.0008F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-9.4669F, -6.6238F, 0.2467F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-6.1417F, -0.1174F, 0.001F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("neck", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.9122F, 0.0159F, 0.031F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.2036F, 0.0174F, -0.0068F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.0021F, 0.0001F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-0.0011F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(21.4237F, -0.0428F, 0.2263F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(25.0596F, -1.1091F, 0.2291F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(22.1687F, 3.491F, 0.2566F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(21.3282F, 2.28F, 0.2233F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(21.4265F, 0.1293F, 0.2263F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_right", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-0.324F, 1.6595F, 2.7758F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-0.2077F, 2.5601F, 2.4549F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.0896F, 0.6698F, 0.7996F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-0.0166F, 0.1174F, 0.1414F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_right_upper", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(7.2467F, -0.8648F, -6.9155F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-5.2956F, -1.8367F, 1.8728F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.6378F, -0.577F, -0.1659F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-0.5283F, -0.1386F, 0.2395F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_right_lower", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(22.1975F, -5.607F, -5.2924F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-10.8634F, 5.9452F, -4.5341F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-26.406F, 9.5362F, -0.0031F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-1.9773F, 0.8133F, -0.058F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_right_foot", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-9.8306F, 12.1315F, 3.0692F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-1.2071F, -6.6134F, -1.8232F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(22.3198F, -8.7095F, -5.9001F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(1.8075F, -0.899F, -0.3846F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_left", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-0.2229F, -2.8572F, -2.7267F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0101F, 0.0683F, 0.0847F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.324F, -1.6595F, -2.7758F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-0.1762F, -1.9452F, -1.9236F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_left_upper", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-5.387F, 2.0127F, -1.7571F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0466F, -0.0582F, -0.0306F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(7.2467F, 0.8648F, 6.9155F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-4.6554F, 1.4946F, -1.7741F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_left_lower", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-12.4693F, -6.5515F, 4.8159F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-32.6167F, -11.5566F, -0.0273F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(22.1975F, 5.607F, 5.2924F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-8.6233F, -4.866F, 3.6474F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("leg_left_foot", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.5382F, 7.2574F, 2.2238F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(27.3386F, 10.0478F, 7.5039F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-9.8306F, -12.1315F, -3.0692F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-2.9703F, 5.4004F, 1.2904F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm_left_upper", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(4.9287F, -3.0584F, 5.9285F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.0771F, -0.4356F, -1.7981F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-0.0692F, -0.0971F, -0.7217F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(6.2041F, -4.1342F, 6.246F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(6.8364F, -4.5354F, 7.4898F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm_left_lower", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(5.0702F, 2.9756F, -8.6449F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-28.4846F, -1.4569F, 2.708F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-21.967F, -1.164F, 0.7727F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(9.4777F, 3.7093F, -8.9811F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(5.3265F, 3.8152F, -11.1688F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm_left_foot", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-18.4873F, -1.8795F, -0.3617F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(22.4589F, 2.1824F, 1.2531F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(21.6724F, 1.4557F, 1.0485F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-14.2193F, -1.7225F, -0.3055F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-19.5435F, -2.3014F, -0.3198F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm_right_upper", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-0.1869F, 0.1251F, 0.8917F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(6.3186F, 3.7309F, -5.6624F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(5.3533F, 2.9109F, -5.5986F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-0.1347F, 0.4913F, 1.962F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-0.0396F, 0.0596F, 0.2971F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm_right_lower", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-21.791F, 1.1695F, -0.9675F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(9.9889F, -3.3147F, 7.6923F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(5.0658F, -2.7481F, 7.9244F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(-28.2353F, 1.4526F, -2.9051F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(-25.6458F, 1.297F, -0.0553F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("arm_right_foot", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(20.9789F, -1.5357F, -1.1403F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(-13.1379F, 1.7245F, 0.3525F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(-17.3449F, 1.7929F, 0.4411F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(21.7935F, -2.2915F, -1.3531F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(24.9835F, -1.5692F, -1.3653F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right_outer", new Transformation(Transformation.Targets.ROTATE,
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.638F, -0.1546F, 0.0883F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5F, AnimationHelper.createRotationalVector(0.6885F, -0.1517F, 0.0933F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0F, AnimationHelper.createRotationalVector(0.6885F, -0.1517F, 0.0933F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.5F, AnimationHelper.createRotationalVector(0.5225F, -0.1611F, 0.077F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.9583F, AnimationHelper.createRotationalVector(0.7189F, -0.15F, 0.0963F), Transformation.Interpolations.LINEAR)
            ))
            .build();

    public static final Animation DRAGONWHELP_ATTACKBITE = Animation.Builder.create(1.4167F)
            .addBoneAnimation("torso", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.875F, AnimationHelper.createRotationalVector(5.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("torso", new Transformation(Transformation.Targets.TRANSLATE, 
			new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createTranslationalVector(0.0F, -0.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.0833F, AnimationHelper.createTranslationalVector(0.0F, 0.5F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail1", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-9.5735F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.75F, AnimationHelper.createRotationalVector(9.2381F, -1.0577F, 0.1441F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(-11.8182F, -1.998F, 0.2722F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail2", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-1.3994F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.75F, AnimationHelper.createRotationalVector(11.4586F, -0.0696F, 0.0028F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(-0.7232F, -0.1314F, 0.0052F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail3", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-4.1542F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.75F, AnimationHelper.createRotationalVector(11.4095F, -0.1047F, 0.0028F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(-3.0896F, -0.1977F, 0.0053F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail4", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.4753F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.75F, AnimationHelper.createRotationalVector(18.5153F, -0.0982F, 0.0032F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(1.4953F, -0.1854F, 0.006F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("tail5", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(-6.6138F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.75F, AnimationHelper.createRotationalVector(33.7349F, -0.0666F, 0.0004F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(-5.9551F, -0.1258F, 0.0008F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("neck", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createRotationalVector(-17.6571F, -0.0184F, 0.0103F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.7917F, AnimationHelper.createRotationalVector(9.7882F, -0.0213F, 0.0105F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(-0.3463F, -0.0287F, 0.011F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createRotationalVector(39.9626F, 3.3089F, -0.5261F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.7917F, AnimationHelper.createRotationalVector(-5.0164F, 2.9116F, -0.4393F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(5.0315F, 1.9181F, -0.2223F), Transformation.Interpolations.LINEAR)
            ))

            .addBoneAnimation("jaw_lower", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.25F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.4167F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.75F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.875F, AnimationHelper.createRotationalVector(-7.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 22.5F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -5.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_left", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createRotationalVector(0.0F, 0.0F, -22.5F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 5.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_right_middle", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createRotationalVector(35.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .addBoneAnimation("wing_left_middle", new Transformation(Transformation.Targets.ROTATE, 
			new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(0.5417F, AnimationHelper.createRotationalVector(35.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
            new Keyframe(1.4167F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
            ))
            .build();
}