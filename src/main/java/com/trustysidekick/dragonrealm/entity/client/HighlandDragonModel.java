package com.trustysidekick.dragonrealm.entity.client;

import com.trustysidekick.dragonrealm.entity.animation.ModAnimations;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class HighlandDragonModel<T extends HighlandDragonEntity> extends SinglePartEntityModel<T> {
	private final ModelPart highlandDragon;
	private final ModelPart body;
	private final ModelPart head;

	public HighlandDragonModel(ModelPart root) {
		this.highlandDragon = root.getChild("root");
		this.body = root.getChild("body");
		this.head = body.getChild("torso").getChild("neck").getChild("head");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 9.0F, -18.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(155, 51).cuboid(-2.875F, 4.0F, 18.025F, 5.7875F, 5.4F, 3.175F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.0F, 0.0F));

		ModelPartData cube_r1 = torso.addChild("cube_r1", ModelPartBuilder.create().uv(136, 40).cuboid(-1.8438F, -1.125F, -3.8125F, 3.6875F, 1.15F, 5.125F, new Dilation(0.0F)), ModelTransform.of(0.0188F, 5.125F, 10.0125F, -0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r2 = torso.addChild("cube_r2", ModelPartBuilder.create().uv(155, 51).cuboid(-3.3937F, -2.275F, 7.7625F, 6.7875F, 6.1F, 4.675F, new Dilation(0.0F)), ModelTransform.of(0.0188F, 5.325F, 13.2625F, -0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r3 = torso.addChild("cube_r3", ModelPartBuilder.create().uv(116, 1).cuboid(-2.6437F, -0.825F, 1.3125F, 5.2875F, 5.0F, 3.625F, new Dilation(0.0F))
				.uv(125, 33).cuboid(-2.8438F, -1.125F, -2.3125F, 5.6875F, 6.0F, 3.625F, new Dilation(0.0F)), ModelTransform.of(0.0188F, 5.325F, 13.2625F, 0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r4 = torso.addChild("cube_r4", ModelPartBuilder.create().uv(155, 50).cuboid(-3.3438F, -1.125F, -2.7125F, 6.6875F, 5.7F, 4.175F, new Dilation(0.0F)), ModelTransform.of(0.0188F, 6.125F, 10.0125F, -0.0873F, 0.0F, 0.0F));

		ModelPartData neck = torso.addChild("neck", ModelPartBuilder.create().uv(149, 42).cuboid(-1.125F, -7.0F, -11.15F, 2.2875F, 0.75F, 2.925F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.5F, 9.0F, 0.5236F, 0.0F, 0.0F));

		ModelPartData cube_r5 = neck.addChild("cube_r5", ModelPartBuilder.create().uv(156, 59).cuboid(-2.8438F, -1.125F, -2.3125F, 5.6875F, 4.5F, 3.625F, new Dilation(0.0F)), ModelTransform.of(0.0188F, 0.125F, -1.6875F, -0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r6 = neck.addChild("cube_r6", ModelPartBuilder.create().uv(135, 1).cuboid(-0.8437F, -1.625F, -1.3125F, 1.6875F, 0.5F, 4.125F, new Dilation(0.0F))
				.uv(116, 1).cuboid(-2.1938F, -1.125F, -1.3125F, 4.3875F, 3.6F, 4.125F, new Dilation(0.0F)), ModelTransform.of(0.0188F, -1.875F, -3.6875F, -0.9599F, 0.0F, 0.0F));

		ModelPartData cube_r7 = neck.addChild("cube_r7", ModelPartBuilder.create().uv(135, 19).cuboid(-0.6937F, -1.625F, -1.3125F, 1.3875F, 0.5F, 2.625F, new Dilation(0.0F))
				.uv(133, 26).cuboid(-1.8937F, -1.125F, -1.3125F, 3.7875F, 3.05F, 2.625F, new Dilation(0.0F)), ModelTransform.of(0.0188F, -3.875F, -4.6875F, -1.2217F, 0.0F, 0.0F));

		ModelPartData cube_r8 = neck.addChild("cube_r8", ModelPartBuilder.create().uv(28, 154).mirrored().cuboid(-0.6438F, -1.625F, -1.3125F, 1.2875F, 0.55F, 2.625F, new Dilation(0.0F)).mirrored(false)
				.uv(119, 3).cuboid(-1.6937F, -1.125F, -1.3125F, 3.3875F, 2.9F, 2.625F, new Dilation(0.0F)), ModelTransform.of(0.0188F, -5.875F, -5.7375F, -0.9599F, 0.0F, 0.0F));

		ModelPartData cube_r9 = neck.addChild("cube_r9", ModelPartBuilder.create().uv(67, 155).cuboid(-0.4938F, -1.625F, -1.3125F, 0.9875F, 0.55F, 2.625F, new Dilation(0.0F))
				.uv(135, 6).cuboid(-1.4938F, -1.125F, -1.3125F, 2.9875F, 2.25F, 2.625F, new Dilation(0.0F)), ModelTransform.of(0.0188F, -6.875F, -7.2375F, -0.3054F, 0.0F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(127, 65).cuboid(-1.2375F, -1.85F, -2.425F, 2.5163F, 1.375F, 2.915F, new Dilation(0.0F))
				.uv(49, 91).mirrored().cuboid(-0.4675F, -2.125F, -2.425F, 0.9762F, 0.275F, 2.8875F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -7.5F, -8.5F));

		ModelPartData cube_r10 = head.addChild("cube_r10", ModelPartBuilder.create().uv(146, 43).cuboid(1.21F, -0.4125F, -2.6813F, 1.5675F, 0.385F, 3.575F, new Dilation(0.0F)), ModelTransform.of(1.8599F, -1.1091F, -0.9812F, 0.0436F, 0.2618F, 2.0071F));

		ModelPartData cube_r11 = head.addChild("cube_r11", ModelPartBuilder.create().uv(68, 151).cuboid(-2.7775F, -0.4125F, -2.6813F, 1.5675F, 0.385F, 3.575F, new Dilation(0.0F)), ModelTransform.of(-1.8599F, -1.1091F, -0.9812F, 0.0436F, -0.2618F, -2.0071F));

		ModelPartData cube_r12 = head.addChild("cube_r12", ModelPartBuilder.create().uv(70, 147).cuboid(-0.3438F, 0.3025F, -0.6325F, 0.9625F, 0.55F, 1.2375F, new Dilation(0.0F)), ModelTransform.of(-2.5246F, -0.3803F, -2.8907F, 0.2602F, 0.2975F, -1.4443F));

		ModelPartData cube_r13 = head.addChild("cube_r13", ModelPartBuilder.create().uv(61, 155).cuboid(-0.935F, -0.44F, -1.4438F, 0.9625F, 0.55F, 2.8325F, new Dilation(0.0F)), ModelTransform.of(-1.8599F, -1.1091F, -0.9812F, 0.0F, 0.0F, -1.5708F));

		ModelPartData cube_r14 = head.addChild("cube_r14", ModelPartBuilder.create().uv(0, 156).cuboid(-1.2427F, 0.002F, -1.7875F, 1.2375F, 0.55F, 1.7875F, new Dilation(0.0F)), ModelTransform.of(-1.2824F, -1.8516F, -2.425F, 0.3927F, 0.0F, -0.6109F));

		ModelPartData cube_r15 = head.addChild("cube_r15", ModelPartBuilder.create().uv(32, 163).cuboid(-0.3163F, -0.2681F, -0.1375F, 0.7288F, 0.6462F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-1.9147F, -1.2839F, -2.2948F, 0.6063F, -0.134F, -1.2121F));

		ModelPartData cube_r16 = head.addChild("cube_r16", ModelPartBuilder.create().uv(66, 40).cuboid(-0.275F, -0.2337F, -0.825F, 0.55F, 0.55F, 1.7188F, new Dilation(0.0F)), ModelTransform.of(-1.3824F, -1.1299F, -3.0844F, 0.5932F, -0.0505F, -1.1018F));

		ModelPartData cube_r17 = head.addChild("cube_r17", ModelPartBuilder.create().uv(67, 162).mirrored().cuboid(-0.1169F, -0.4125F, -0.7081F, 0.2337F, 0.605F, 1.6225F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.3564F, -1.3283F, -2.7861F, 0.6343F, -0.1666F, -0.777F));

		ModelPartData cube_r18 = head.addChild("cube_r18", ModelPartBuilder.create().uv(51, 135).cuboid(-0.3438F, -0.275F, -1.4438F, 1.2375F, 0.55F, 2.8875F, new Dilation(0.0F)), ModelTransform.of(-1.8599F, -1.1091F, -0.9812F, 0.0F, 0.0F, -0.6109F));

		ModelPartData cube_r19 = head.addChild("cube_r19", ModelPartBuilder.create().uv(39, 153).cuboid(-2.0075F, -0.44F, -1.1687F, 1.5263F, 0.55F, 2.8875F, new Dilation(0.0F)), ModelTransform.of(-1.8599F, -1.1091F, -0.9812F, 0.0F, -0.2182F, -1.8762F));

		ModelPartData cube_r20 = head.addChild("cube_r20", ModelPartBuilder.create().uv(144, 53).cuboid(0.4812F, -0.44F, -1.1687F, 1.5263F, 0.55F, 2.8875F, new Dilation(0.0F)), ModelTransform.of(1.8599F, -1.1091F, -0.9812F, 0.0F, 0.2182F, 1.8762F));

		ModelPartData cube_r21 = head.addChild("cube_r21", ModelPartBuilder.create().uv(114, 33).cuboid(-0.0275F, -0.44F, -1.4438F, 0.9625F, 0.55F, 2.805F, new Dilation(0.0F)), ModelTransform.of(1.8599F, -1.1091F, -0.9812F, 0.0F, 0.0F, 1.5708F));

		ModelPartData cube_r22 = head.addChild("cube_r22", ModelPartBuilder.create().uv(114, 33).cuboid(-0.6187F, 0.3025F, -0.6325F, 0.9625F, 0.55F, 1.2375F, new Dilation(0.0F)), ModelTransform.of(2.5246F, -0.3803F, -2.8907F, 0.2602F, -0.2975F, 1.4443F));

		ModelPartData cube_r23 = head.addChild("cube_r23", ModelPartBuilder.create().uv(114, 33).cuboid(-0.4125F, -0.2681F, -0.1375F, 0.7288F, 0.6462F, 0.275F, new Dilation(0.0F)), ModelTransform.of(1.9147F, -1.2839F, -2.2948F, 0.6063F, 0.134F, 1.2121F));

		ModelPartData cube_r24 = head.addChild("cube_r24", ModelPartBuilder.create().uv(65, 162).mirrored().cuboid(-0.1169F, -0.4125F, -0.7081F, 0.2337F, 0.605F, 1.6225F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.3564F, -1.3283F, -2.7861F, 0.6343F, 0.1666F, 0.777F));

		ModelPartData cube_r25 = head.addChild("cube_r25", ModelPartBuilder.create().uv(114, 40).cuboid(-0.275F, -0.2337F, -0.825F, 0.55F, 0.55F, 1.7188F, new Dilation(0.0F)), ModelTransform.of(1.3824F, -1.1299F, -3.0844F, 0.5932F, 0.0505F, 1.1018F));

		ModelPartData cube_r26 = head.addChild("cube_r26", ModelPartBuilder.create().uv(114, 65).cuboid(0.0052F, 0.002F, -1.7875F, 1.2375F, 0.55F, 1.7875F, new Dilation(0.0F)), ModelTransform.of(1.2824F, -1.8516F, -2.425F, 0.3927F, 0.0F, 0.6109F));

		ModelPartData cube_r27 = head.addChild("cube_r27", ModelPartBuilder.create().uv(67, 154).cuboid(-0.8937F, -0.275F, -1.4438F, 1.2375F, 0.55F, 2.8875F, new Dilation(0.0F)), ModelTransform.of(1.8599F, -1.1091F, -0.9812F, 0.0F, 0.0F, 0.6109F));

		ModelPartData cube_r28 = head.addChild("cube_r28", ModelPartBuilder.create().uv(38, 157).cuboid(-0.4263F, -0.4606F, -0.5431F, 0.8525F, 0.5912F, 2.1862F, new Dilation(0.0F)), ModelTransform.of(-0.7288F, -0.6488F, -6.5563F, -0.2061F, 0.5576F, -0.1102F));

		ModelPartData cube_r29 = head.addChild("cube_r29", ModelPartBuilder.create().uv(114, 33).cuboid(-0.4263F, -0.4606F, -0.5431F, 0.8525F, 0.5912F, 2.1862F, new Dilation(0.0F)), ModelTransform.of(0.7288F, -0.6488F, -6.5563F, -0.2061F, -0.5576F, 0.1102F));

		ModelPartData cube_r30 = head.addChild("cube_r30", ModelPartBuilder.create().uv(44, 155).cuboid(-1.1069F, 0.2888F, -3.7262F, 2.2138F, 0.9075F, 1.2925F, new Dilation(0.0F))
				.uv(68, 114).cuboid(-1.1069F, 0.2888F, -3.7262F, 2.2138F, 0.9075F, 1.2925F, new Dilation(0.0F))
				.uv(132, 65).cuboid(-0.5569F, 0.2613F, -2.4475F, 1.1137F, 0.9213F, 2.0625F, new Dilation(0.0F))
				.uv(156, 74).cuboid(-1.2169F, 0.4263F, -2.4475F, 2.4337F, 0.7288F, 2.0625F, new Dilation(0.0F)), ModelTransform.of(0.0206F, -1.6019F, -3.2118F, 0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r31 = head.addChild("cube_r31", ModelPartBuilder.create().uv(66, 159).cuboid(-1.0381F, 0.9763F, -2.4475F, 0.3437F, 0.715F, 2.75F, new Dilation(0.0F))
				.uv(114, 33).cuboid(0.7356F, 0.9763F, -2.4475F, 0.3712F, 0.715F, 2.75F, new Dilation(0.0F)), ModelTransform.of(-0.0206F, -1.6019F, -3.2118F, -0.1309F, 0.0F, 0.0F));

		ModelPartData cube_r32 = head.addChild("cube_r32", ModelPartBuilder.create().uv(2, 156).cuboid(-0.4331F, -0.22F, -1.65F, 0.8663F, 0.7838F, 2.475F, new Dilation(0.0F))
				.uv(128, 68).cuboid(-1.2581F, 0.0F, -1.65F, 2.5163F, 0.5638F, 2.475F, new Dilation(0.0F)), ModelTransform.of(0.0206F, -1.6019F, -3.2118F, 0.3491F, 0.0F, 0.0F));

		ModelPartData cube_r33 = head.addChild("cube_r33", ModelPartBuilder.create().uv(114, 33).cuboid(-2.4631F, -36.3186F, -4.527F, 0.2612F, 0.385F, 0.275F, new Dilation(0.0F)), ModelTransform.of(0.5039F, 36.4308F, -5.4123F, -0.0201F, 0.7756F, 0.0782F));

		ModelPartData cube_r34 = head.addChild("cube_r34", ModelPartBuilder.create().uv(114, 33).cuboid(-2.3943F, -36.2858F, 0.2806F, 0.1237F, 0.3575F, 0.165F, new Dilation(0.0F)), ModelTransform.of(0.5039F, 36.4308F, -5.4123F, 0.1108F, 0.7756F, 0.0782F));

		ModelPartData cube_r35 = head.addChild("cube_r35", ModelPartBuilder.create().uv(14, 165).mirrored().cuboid(2.2214F, -36.3161F, -4.5461F, 0.2612F, 0.385F, 0.275F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5039F, 36.4308F, -5.4123F, -0.0201F, -0.7756F, -0.0782F));

		ModelPartData cube_r36 = head.addChild("cube_r36", ModelPartBuilder.create().uv(73, 165).cuboid(2.2902F, -36.2858F, 0.2613F, 0.1237F, 0.3575F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-0.5039F, 36.4308F, -5.4123F, 0.1108F, -0.7756F, -0.0782F));

		ModelPartData horns = head.addChild("horns", ModelPartBuilder.create(), ModelTransform.pivot(1.3564F, -1.3283F, -2.7861F));

		ModelPartData cube_r37 = horns.addChild("cube_r37", ModelPartBuilder.create().uv(56, 148).cuboid(-0.3506F, -0.715F, 0.5844F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(0.6875F, 0.0F, 0.0F, -0.0909F, 0.558F, -0.0658F));

		ModelPartData cube_r38 = horns.addChild("cube_r38", ModelPartBuilder.create().uv(53, 145).cuboid(0.2269F, -0.715F, 0.5844F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-3.4004F, 0.0F, 0.0F, -0.0909F, -0.558F, 0.0658F));

		ModelPartData cube_r39 = horns.addChild("cube_r39", ModelPartBuilder.create().uv(66, 142).cuboid(0.2681F, -0.33F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-3.4004F, 0.0F, 0.0F, 0.2461F, -0.5074F, -0.3359F));

		ModelPartData cube_r40 = horns.addChild("cube_r40", ModelPartBuilder.create().uv(72, 163).cuboid(0.1581F, 0.0413F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)), ModelTransform.of(-3.4004F, 0.0F, 0.0F, 0.3482F, -0.3232F, -0.719F));

		ModelPartData cube_r41 = horns.addChild("cube_r41", ModelPartBuilder.create().uv(72, 163).cuboid(0.1581F, -0.4125F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)), ModelTransform.of(-1.1179F, 0.55F, -1.3338F, 0.2706F, -0.7426F, -0.1583F));

		ModelPartData cube_r42 = horns.addChild("cube_r42", ModelPartBuilder.create().uv(49, 141).cuboid(0.2269F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-2.4379F, 0.0F, -0.9625F, -0.0909F, -0.558F, 0.0658F));

		ModelPartData cube_r43 = horns.addChild("cube_r43", ModelPartBuilder.create().uv(64, 138).cuboid(0.1581F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-2.4379F, 0.0F, -0.9625F, 0.04F, -0.558F, 0.0658F));

		ModelPartData cube_r44 = horns.addChild("cube_r44", ModelPartBuilder.create().uv(72, 163).cuboid(0.1581F, -0.4125F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)), ModelTransform.of(-2.4379F, 0.0F, -0.9625F, 0.2311F, -0.5267F, -0.1348F));

		ModelPartData cube_r45 = horns.addChild("cube_r45", ModelPartBuilder.create().uv(45, 137).cuboid(0.2269F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-2.7129F, 0.0F, 0.0F, -0.0909F, -0.558F, 0.0658F));

		ModelPartData cube_r46 = horns.addChild("cube_r46", ModelPartBuilder.create().uv(62, 134).cuboid(0.1581F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-2.7129F, 0.0F, 0.0F, 0.04F, -0.558F, 0.0658F));

		ModelPartData cube_r47 = horns.addChild("cube_r47", ModelPartBuilder.create().uv(16, 163).mirrored().cuboid(0.1581F, -0.4125F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.7129F, 0.0F, 0.0F, 0.2311F, -0.5267F, -0.1348F));

		ModelPartData cube_r48 = horns.addChild("cube_r48", ModelPartBuilder.create().uv(60, 133).cuboid(-0.5294F, -0.33F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(0.6875F, 0.0F, 0.0F, 0.2461F, 0.5074F, 0.3359F));

		ModelPartData cube_r49 = horns.addChild("cube_r49", ModelPartBuilder.create().uv(12, 163).mirrored().cuboid(-0.5294F, 0.0413F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.6875F, 0.0F, 0.0F, 0.3482F, 0.3232F, 0.719F));

		ModelPartData cube_r50 = horns.addChild("cube_r50", ModelPartBuilder.create().uv(72, 163).cuboid(-0.5294F, -0.4125F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.55F, -1.3338F, 0.2706F, 0.7426F, 0.1583F));

		ModelPartData cube_r51 = horns.addChild("cube_r51", ModelPartBuilder.create().uv(5, 96).cuboid(-0.4194F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.4537F, -1.3338F, 0.0201F, 0.7756F, -0.0782F));

		ModelPartData cube_r52 = horns.addChild("cube_r52", ModelPartBuilder.create().uv(6, 97).cuboid(-0.3506F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.4537F, -1.3338F, -0.1108F, 0.7756F, -0.0782F));

		ModelPartData cube_r53 = horns.addChild("cube_r53", ModelPartBuilder.create().uv(5, 96).cuboid(-0.4194F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.8387F, -2.4337F, 0.0201F, 0.7756F, -0.0782F));

		ModelPartData cube_r54 = horns.addChild("cube_r54", ModelPartBuilder.create().uv(6, 97).cuboid(-0.3506F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.8387F, -2.4337F, -0.1108F, 0.7756F, -0.0782F));

		ModelPartData cube_r55 = horns.addChild("cube_r55", ModelPartBuilder.create().uv(72, 163).cuboid(-0.5294F, -0.4125F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.55F, -4.4962F, 0.2706F, 0.7426F, 0.1583F));

		ModelPartData cube_r56 = horns.addChild("cube_r56", ModelPartBuilder.create().uv(57, 164).cuboid(-0.4194F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.55F, -4.4962F, 0.0201F, 0.7756F, -0.0782F));

		ModelPartData cube_r57 = horns.addChild("cube_r57", ModelPartBuilder.create().uv(38, 130).cuboid(-0.3506F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-1.595F, 0.55F, -4.4962F, -0.1108F, 0.7756F, -0.0782F));

		ModelPartData cube_r58 = horns.addChild("cube_r58", ModelPartBuilder.create().uv(35, 127).cuboid(-0.3506F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-0.275F, 0.0F, -0.9625F, -0.0909F, 0.558F, -0.0658F));

		ModelPartData cube_r59 = horns.addChild("cube_r59", ModelPartBuilder.create().uv(54, 164).cuboid(-0.4194F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-0.275F, 0.0F, -0.9625F, 0.04F, 0.558F, -0.0658F));

		ModelPartData cube_r60 = horns.addChild("cube_r60", ModelPartBuilder.create().uv(70, 163).cuboid(-0.5294F, -0.4125F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)), ModelTransform.of(-0.275F, 0.0F, -0.9625F, 0.2311F, 0.5267F, 0.1348F));

		ModelPartData cube_r61 = horns.addChild("cube_r61", ModelPartBuilder.create().uv(73, 164).cuboid(-0.3506F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0909F, 0.558F, -0.0658F));

		ModelPartData cube_r62 = horns.addChild("cube_r62", ModelPartBuilder.create().uv(52, 164).cuboid(-0.4194F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.04F, 0.558F, -0.0658F));

		ModelPartData cube_r63 = horns.addChild("cube_r63", ModelPartBuilder.create().uv(67, 159).cuboid(-0.5294F, -0.4125F, 0.5294F, 0.3712F, 0.33F, 0.385F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.2311F, 0.5267F, 0.1348F));

		ModelPartData horn_left = horns.addChild("horn_left", ModelPartBuilder.create(), ModelTransform.pivot(1.5311F, 1.9533F, -1.2889F));

		ModelPartData horn_r1 = horn_left.addChild("horn_r1", ModelPartBuilder.create().uv(6, 150).mirrored().cuboid(-0.66F, 0.3163F, 0.2063F, 0.6875F, 0.5362F, 1.4438F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.5246F, -2.3803F, 2.3907F, -0.2728F, 0.4203F, -1.6359F));

		ModelPartData horn_r2 = horn_left.addChild("horn_r2", ModelPartBuilder.create().uv(33, 96).cuboid(-0.2887F, -0.2544F, -0.2406F, 0.5775F, 0.3987F, 0.4813F, new Dilation(0.0F)), ModelTransform.of(-1.4801F, -2.8392F, 4.0382F, -0.1419F, 0.4203F, -1.6359F));

		ModelPartData horn_r3 = horn_left.addChild("horn_r3", ModelPartBuilder.create().uv(29, 162).cuboid(-0.2337F, -0.2131F, 0.2406F, 0.4675F, 0.3713F, 0.4813F, new Dilation(0.0F)), ModelTransform.of(-1.4801F, -2.8392F, 4.0382F, -0.0546F, 0.4203F, -1.6359F));

		ModelPartData horn_r4 = horn_left.addChild("horn_r4", ModelPartBuilder.create().uv(36, 127).mirrored().cuboid(-0.0962F, -0.0344F, 1.0656F, 0.1925F, 0.1787F, 0.3438F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.4801F, -2.8392F, 4.0382F, 0.1112F, 0.4203F, -1.6359F));

		ModelPartData horn_r5 = horn_left.addChild("horn_r5", ModelPartBuilder.create().uv(34, 162).cuboid(-0.165F, -0.1581F, 0.7219F, 0.33F, 0.2612F, 0.3438F, new Dilation(0.0F)), ModelTransform.of(-1.4801F, -2.8392F, 4.0382F, 0.0152F, 0.4203F, -1.6359F));

		ModelPartData horn_right = horns.addChild("horn_right", ModelPartBuilder.create(), ModelTransform.pivot(-4.2439F, 1.9533F, -1.4264F));

		ModelPartData horn_r6 = horn_right.addChild("horn_r6", ModelPartBuilder.create().uv(7, 153).cuboid(-0.0275F, 0.3163F, 0.2063F, 0.6875F, 0.5362F, 1.4437F, new Dilation(0.0F)), ModelTransform.of(2.5246F, -2.3803F, 2.3907F, -0.2728F, -0.4203F, 1.6359F));

		ModelPartData horn_r7 = horn_right.addChild("horn_r7", ModelPartBuilder.create().uv(41, 97).cuboid(-0.2887F, -0.2544F, -0.2406F, 0.5775F, 0.3987F, 0.4813F, new Dilation(0.0F)), ModelTransform.of(1.4801F, -2.8392F, 4.0382F, -0.1419F, -0.4203F, 1.6359F));

		ModelPartData horn_r8 = horn_right.addChild("horn_r8", ModelPartBuilder.create().uv(36, 162).cuboid(-0.2337F, -0.2131F, 0.2406F, 0.4675F, 0.3713F, 0.4813F, new Dilation(0.0F)), ModelTransform.of(1.4801F, -2.8392F, 4.0382F, -0.0546F, -0.4203F, 1.6359F));

		ModelPartData horn_r9 = horn_right.addChild("horn_r9", ModelPartBuilder.create().uv(40, 163).cuboid(-0.0962F, -0.0344F, 1.0656F, 0.1925F, 0.1787F, 0.3438F, new Dilation(0.0F)), ModelTransform.of(1.4801F, -2.8392F, 4.0382F, 0.1112F, -0.4203F, 1.6359F));

		ModelPartData horn_r10 = horn_right.addChild("horn_r10", ModelPartBuilder.create().uv(38, 151).cuboid(-0.165F, -0.1581F, 0.7219F, 0.33F, 0.2612F, 0.3438F, new Dilation(0.0F)), ModelTransform.of(1.4801F, -2.8392F, 4.0382F, 0.0152F, -0.4203F, 1.6359F));

		ModelPartData horn_right_big = horns.addChild("horn_right_big", ModelPartBuilder.create(), ModelTransform.pivot(-5.3439F, 1.9533F, -1.4264F));

		ModelPartData horn_r11 = horn_right_big.addChild("horn_r11", ModelPartBuilder.create().uv(73, 164).cuboid(-0.0688F, -0.0206F, -0.1169F, 0.1375F, 0.1513F, 0.2338F, new Dilation(0.0F)), ModelTransform.of(0.8338F, -4.1454F, 5.4805F, -1.0738F, -0.6107F, 1.8431F));

		ModelPartData horn_r12 = horn_right_big.addChild("horn_r12", ModelPartBuilder.create().uv(46, 137).mirrored().cuboid(-0.0963F, -0.4331F, 0.3231F, 0.1925F, 0.2062F, 0.3438F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.9648F, -3.6353F, 4.829F, -0.7248F, -0.6107F, 1.8431F));

		ModelPartData horn_r13 = horn_right_big.addChild("horn_r13", ModelPartBuilder.create().uv(43, 163).cuboid(-0.1238F, -0.2956F, 0.1306F, 0.2475F, 0.2612F, 0.3438F, new Dilation(0.0F)), ModelTransform.of(0.9648F, -3.6353F, 4.829F, -0.3321F, -0.6107F, 1.8431F));

		ModelPartData horn_r14 = horn_right_big.addChild("horn_r14", ModelPartBuilder.create().uv(48, 139).mirrored().cuboid(-0.1513F, -0.2681F, -0.3369F, 0.3025F, 0.3162F, 0.5637F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.9648F, -3.6353F, 4.829F, -0.0266F, -0.6107F, 1.8431F));

		ModelPartData horn_r15 = horn_right_big.addChild("horn_r15", ModelPartBuilder.create().uv(46, 137).mirrored().cuboid(-0.2063F, -0.1856F, 0.3231F, 0.4125F, 0.3712F, 0.5637F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.2073F, -2.9279F, 3.9488F, -0.3757F, -0.6107F, 1.8431F));

		ModelPartData horn_r16 = horn_right_big.addChild("horn_r16", ModelPartBuilder.create().uv(69, 134).mirrored().cuboid(-0.2888F, -0.2956F, -0.5019F, 0.5775F, 0.4262F, 0.8938F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.2073F, -2.9279F, 3.9488F, -0.6375F, -0.6107F, 1.8431F));

		ModelPartData horn_r17 = horn_right_big.addChild("horn_r17", ModelPartBuilder.create().uv(12, 140).mirrored().cuboid(-0.0275F, 0.3162F, 0.2063F, 0.6875F, 0.5362F, 1.4437F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.9646F, -2.1053F, 2.9407F, -0.812F, -0.6107F, 1.8431F));

		ModelPartData horn_left_big = horns.addChild("horn_left_big", ModelPartBuilder.create(), ModelTransform.pivot(2.6311F, 1.9533F, -1.4264F));

		ModelPartData horn_r18 = horn_left_big.addChild("horn_r18", ModelPartBuilder.create().uv(120, 57).cuboid(-0.0688F, -0.0206F, -0.1169F, 0.1375F, 0.1513F, 0.2338F, new Dilation(0.0F)), ModelTransform.of(-0.8338F, -4.1454F, 5.4805F, -1.0738F, 0.6107F, -1.8431F));

		ModelPartData horn_r19 = horn_left_big.addChild("horn_r19", ModelPartBuilder.create().uv(50, 141).mirrored().cuboid(-0.0963F, -0.4331F, 0.3231F, 0.1925F, 0.2062F, 0.3438F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.9648F, -3.6353F, 4.829F, -0.7248F, 0.6107F, -1.8431F));

		ModelPartData horn_r20 = horn_left_big.addChild("horn_r20", ModelPartBuilder.create().uv(48, 139).mirrored().cuboid(-0.1238F, -0.2956F, 0.1306F, 0.2475F, 0.2612F, 0.3438F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.9648F, -3.6353F, 4.829F, -0.3321F, 0.6107F, -1.8431F));

		ModelPartData horn_r21 = horn_left_big.addChild("horn_r21", ModelPartBuilder.create().uv(120, 57).cuboid(-0.1513F, -0.2681F, -0.3369F, 0.3025F, 0.3162F, 0.5637F, new Dilation(0.0F)), ModelTransform.of(-0.9648F, -3.6353F, 4.829F, -0.0266F, 0.6107F, -1.8431F));

		ModelPartData horn_r22 = horn_left_big.addChild("horn_r22", ModelPartBuilder.create().uv(51, 142).mirrored().cuboid(-0.2062F, -0.1856F, 0.3231F, 0.4125F, 0.3712F, 0.5637F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-1.2073F, -2.9279F, 3.9488F, -0.3757F, 0.6107F, -1.8431F));

		ModelPartData horn_r23 = horn_left_big.addChild("horn_r23", ModelPartBuilder.create().uv(120, 57).cuboid(-0.2887F, -0.2956F, -0.5019F, 0.5775F, 0.4262F, 0.8938F, new Dilation(0.0F)), ModelTransform.of(-1.2073F, -2.9279F, 3.9488F, -0.6375F, 0.6107F, -1.8431F));

		ModelPartData horn_r24 = horn_left_big.addChild("horn_r24", ModelPartBuilder.create().uv(13, 159).cuboid(-0.66F, 0.3162F, 0.2063F, 0.6875F, 0.5362F, 1.4437F, new Dilation(0.0F)), ModelTransform.of(-2.9646F, -2.1053F, 2.9407F, -0.812F, 0.6107F, -1.8431F));

		ModelPartData jaw_lower = head.addChild("jaw_lower", ModelPartBuilder.create(), ModelTransform.of(0.0275F, 0.3242F, -2.7162F, 0.1309F, 0.0F, 0.0F));

		ModelPartData cube_r64 = jaw_lower.addChild("cube_r64", ModelPartBuilder.create().uv(142, 95).cuboid(-1.2169F, 2.0763F, -1.3475F, 2.475F, 0.4262F, 2.2F, new Dilation(0.0F)), ModelTransform.of(-0.0069F, -1.9261F, -0.4957F, -0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r65 = jaw_lower.addChild("cube_r65", ModelPartBuilder.create().uv(120, 57).cuboid(-0.1169F, -0.3231F, -0.1925F, 0.2337F, 0.4262F, 0.935F, new Dilation(0.0F)), ModelTransform.of(-0.0069F, 0.7528F, -3.2702F, 0.5236F, 0.0F, 0.0F));

		ModelPartData cube_r66 = jaw_lower.addChild("cube_r66", ModelPartBuilder.create().uv(120, 57).cuboid(-0.6669F, 1.8013F, -3.41F, 1.3337F, 0.4262F, 0.825F, new Dilation(0.0F))
				.uv(149, 65).cuboid(-0.9419F, 1.8013F, -2.585F, 1.8838F, 0.4262F, 3.575F, new Dilation(0.0F)), ModelTransform.of(-0.0069F, -1.9261F, -0.4957F, 0.0873F, 0.0F, 0.0F));

		ModelPartData teeth = jaw_lower.addChild("teeth", ModelPartBuilder.create(), ModelTransform.pivot(0.2289F, 0.9325F, -3.1087F));

		ModelPartData cube_r67 = teeth.addChild("cube_r67", ModelPartBuilder.create().uv(73, 165).cuboid(-0.4194F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F))
				.uv(73, 165).cuboid(-0.3222F, -1.1196F, -0.1352F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.7158F, 0.3135F, 0.8266F));

		ModelPartData cube_r68 = teeth.addChild("cube_r68", ModelPartBuilder.create().uv(74, 165).cuboid(-0.3506F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F))
				.uv(74, 165).cuboid(-0.2534F, -1.2707F, -0.2088F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.5849F, 0.3135F, 0.8266F));

		ModelPartData cube_r69 = teeth.addChild("cube_r69", ModelPartBuilder.create().uv(72, 165).cuboid(0.1297F, -1.2707F, -0.2088F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F))
				.uv(67, 159).cuboid(0.2269F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-0.5129F, 0.0F, 0.0F, 0.5849F, -0.3135F, -0.8266F));

		ModelPartData cube_r70 = teeth.addChild("cube_r70", ModelPartBuilder.create().uv(73, 161).cuboid(0.0609F, -1.1196F, -0.1352F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F))
				.uv(73, 160).cuboid(0.1581F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-0.5129F, 0.0F, 0.0F, 0.7158F, -0.3135F, -0.8266F));

		ModelPartData cube_r71 = teeth.addChild("cube_r71", ModelPartBuilder.create().uv(71, 156).cuboid(0.1581F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(-0.7604F, -0.1925F, 0.4125F, 0.0201F, -0.7756F, 0.0782F));

		ModelPartData cube_r72 = teeth.addChild("cube_r72", ModelPartBuilder.create().uv(63, 155).cuboid(0.2269F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(-0.7604F, -0.1925F, 0.4125F, -0.1108F, -0.7756F, 0.0782F));

		ModelPartData cube_r73 = teeth.addChild("cube_r73", ModelPartBuilder.create().uv(69, 152).cuboid(-0.4194F, -0.825F, 0.5844F, 0.2612F, 0.33F, 0.275F, new Dilation(0.0F)), ModelTransform.of(0.2475F, -0.1925F, 0.4125F, 0.0201F, 0.7756F, -0.0782F));

		ModelPartData cube_r74 = teeth.addChild("cube_r74", ModelPartBuilder.create().uv(59, 151).cuboid(-0.3506F, -1.0725F, 0.5431F, 0.1237F, 0.1925F, 0.165F, new Dilation(0.0F)), ModelTransform.of(0.2475F, -0.1925F, 0.4125F, -0.1108F, 0.7756F, -0.0782F));

		ModelPartData leg_left = torso.addChild("leg_left", ModelPartBuilder.create(), ModelTransform.pivot(3.4813F, 7.2527F, 22.9421F));

		ModelPartData cube_r75 = leg_left.addChild("cube_r75", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-1.4925F, 1.56F, -2.505F, 2.925F, 2.52F, 4.41F, new Dilation(0.0F)).mirrored(false)
				.uv(155, 51).mirrored().cuboid(-1.3725F, 5.82F, 2.895F, 2.505F, 1.92F, 1.41F, new Dilation(0.0F)).mirrored(false)
				.uv(155, 51).mirrored().cuboid(-1.3725F, 4.02F, -1.905F, 2.505F, 1.92F, 6.21F, new Dilation(0.0F)).mirrored(false)
				.uv(155, 51).mirrored().cuboid(-1.6725F, -2.76F, -3.105F, 3.345F, 4.32F, 6.21F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		ModelPartData foot2 = leg_left.addChild("foot2", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-1.5525F, -0.9F, -1.665F, 3.105F, 1.32F, 2.61F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.88F, 8.3501F, 1.4896F));

		ModelPartData toe4 = foot2.addChild("toe4", ModelPartBuilder.create(), ModelTransform.of(0.6586F, -0.6124F, -1.682F, 0.0F, -0.48F, 0.0F));

		ModelPartData cube_r76 = toe4.addChild("cube_r76", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.5225F, -0.05F, -1.565F, 1.045F, 0.7F, 1.33F, new Dilation(-0.2F)).mirrored(false), ModelTransform.of(-0.7109F, -0.15F, -2.4484F, 0.7372F, 0.2595F, -0.035F));

		ModelPartData cube_r77 = toe4.addChild("cube_r77", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.5425F, 0.05F, -0.385F, 1.085F, 0.74F, 1.37F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(-0.7109F, -0.15F, -2.4484F, 0.3009F, 0.2595F, -0.035F));

		ModelPartData cube_r78 = toe4.addChild("cube_r78", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.9525F, -0.6F, -3.705F, 1.125F, 0.78F, 2.01F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.8305F, 0.6124F, 1.7336F, -0.1355F, 0.2595F, -0.035F));

		ModelPartData toe5 = foot2.addChild("toe5", ModelPartBuilder.create(), ModelTransform.pivot(-0.7414F, -0.4624F, -1.682F));

		ModelPartData cube_r79 = toe5.addChild("cube_r79", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.5225F, -0.05F, -1.565F, 1.045F, 0.7F, 1.33F, new Dilation(-0.2F)).mirrored(false), ModelTransform.of(-0.8F, -0.3F, -2.5F, 0.7372F, 0.2595F, -0.035F));

		ModelPartData cube_r80 = toe5.addChild("cube_r80", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.5425F, 0.05F, -0.385F, 1.085F, 0.74F, 1.37F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(-0.8F, -0.3F, -2.5F, 0.3009F, 0.2595F, -0.035F));

		ModelPartData cube_r81 = toe5.addChild("cube_r81", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.9525F, -0.6F, -3.705F, 1.125F, 0.78F, 2.01F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.7414F, 0.4624F, 1.682F, -0.1355F, 0.2595F, -0.035F));

		ModelPartData toe6 = foot2.addChild("toe6", ModelPartBuilder.create(), ModelTransform.of(0.8386F, -0.7624F, -0.622F, 0.0F, -0.9163F, 0.0F));

		ModelPartData cube_r82 = toe6.addChild("cube_r82", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.5225F, -0.05F, -1.565F, 1.045F, 0.7F, 1.33F, new Dilation(-0.2F)).mirrored(false), ModelTransform.of(-0.3692F, 0.0F, -2.8042F, 0.7372F, 0.2595F, -0.035F));

		ModelPartData cube_r83 = toe6.addChild("cube_r83", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.5425F, 0.05F, -0.385F, 1.085F, 0.74F, 1.37F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(-0.3692F, 0.0F, -2.8042F, 0.3009F, 0.2595F, -0.035F));

		ModelPartData cube_r84 = toe6.addChild("cube_r84", ModelPartBuilder.create().uv(155, 51).mirrored().cuboid(-0.9525F, -0.6F, -3.705F, 1.125F, 0.78F, 2.01F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.1722F, 0.7624F, 1.3778F, -0.1355F, 0.2595F, -0.035F));

		ModelPartData leg_right = torso.addChild("leg_right", ModelPartBuilder.create(), ModelTransform.pivot(-3.4813F, 7.2527F, 22.9421F));

		ModelPartData cube_r85 = leg_right.addChild("cube_r85", ModelPartBuilder.create().uv(155, 51).cuboid(-1.4325F, 1.56F, -2.505F, 2.925F, 2.52F, 4.41F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-1.1325F, 5.82F, 2.895F, 2.505F, 1.92F, 1.41F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-1.1325F, 4.02F, -1.905F, 2.505F, 1.92F, 6.21F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-1.6725F, -2.76F, -3.105F, 3.345F, 4.32F, 6.21F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		ModelPartData foot = leg_right.addChild("foot", ModelPartBuilder.create().uv(155, 51).cuboid(-1.5525F, -0.9F, -1.665F, 3.105F, 1.32F, 2.61F, new Dilation(0.0F)), ModelTransform.pivot(-0.88F, 8.3501F, 1.4896F));

		ModelPartData toe = foot.addChild("toe", ModelPartBuilder.create(), ModelTransform.of(-0.6586F, -0.5624F, -1.682F, 0.0F, 0.48F, 0.0F));

		ModelPartData cube_r86 = toe.addChild("cube_r86", ModelPartBuilder.create().uv(155, 51).cuboid(-0.5225F, -0.05F, -1.565F, 1.045F, 0.7F, 1.33F, new Dilation(-0.2F)), ModelTransform.of(0.7109F, -0.2F, -2.4484F, 0.7372F, -0.2595F, 0.035F));

		ModelPartData cube_r87 = toe.addChild("cube_r87", ModelPartBuilder.create().uv(155, 51).cuboid(-0.5425F, 0.05F, -0.385F, 1.085F, 0.74F, 1.37F, new Dilation(-0.1F)), ModelTransform.of(0.7109F, -0.2F, -2.4484F, 0.3009F, -0.2595F, 0.035F));

		ModelPartData cube_r88 = toe.addChild("cube_r88", ModelPartBuilder.create().uv(155, 51).cuboid(-0.1725F, -0.6F, -3.705F, 1.125F, 0.78F, 2.01F, new Dilation(0.0F)), ModelTransform.of(-0.8305F, 0.5624F, 1.7336F, -0.1355F, -0.2595F, 0.035F));

		ModelPartData toe2 = foot.addChild("toe2", ModelPartBuilder.create(), ModelTransform.pivot(0.8914F, -0.5624F, -1.682F));

		ModelPartData cube_r89 = toe2.addChild("cube_r89", ModelPartBuilder.create().uv(155, 51).cuboid(-0.5225F, -0.05F, -1.565F, 1.045F, 0.7F, 1.33F, new Dilation(-0.2F)), ModelTransform.of(0.65F, -0.2F, -2.5F, 0.7372F, -0.2595F, 0.035F));

		ModelPartData cube_r90 = toe2.addChild("cube_r90", ModelPartBuilder.create().uv(155, 51).cuboid(-0.5425F, 0.05F, -0.385F, 1.085F, 0.74F, 1.37F, new Dilation(-0.1F)), ModelTransform.of(0.65F, -0.2F, -2.5F, 0.3009F, -0.2595F, 0.035F));

		ModelPartData cube_r91 = toe2.addChild("cube_r91", ModelPartBuilder.create().uv(155, 51).cuboid(-0.1725F, -0.6F, -3.705F, 1.125F, 0.78F, 2.01F, new Dilation(0.0F)), ModelTransform.of(-0.8914F, 0.5624F, 1.682F, -0.1355F, -0.2595F, 0.035F));

		ModelPartData toe3 = foot.addChild("toe3", ModelPartBuilder.create(), ModelTransform.of(-1.3386F, -0.7624F, -0.622F, 0.0F, 0.9163F, 0.0F));

		ModelPartData cube_r92 = toe3.addChild("cube_r92", ModelPartBuilder.create().uv(155, 51).cuboid(-0.5225F, -0.05F, -1.565F, 1.045F, 0.7F, 1.33F, new Dilation(-0.2F)), ModelTransform.of(0.6736F, 0.0F, -2.4076F, 0.7372F, -0.2595F, 0.035F));

		ModelPartData cube_r93 = toe3.addChild("cube_r93", ModelPartBuilder.create().uv(155, 51).cuboid(-0.5425F, 0.05F, -0.385F, 1.085F, 0.74F, 1.37F, new Dilation(-0.1F)), ModelTransform.of(0.6736F, 0.0F, -2.4076F, 0.3009F, -0.2595F, 0.035F));

		ModelPartData cube_r94 = toe3.addChild("cube_r94", ModelPartBuilder.create().uv(155, 51).cuboid(-0.1725F, -0.6F, -3.705F, 1.125F, 0.78F, 2.01F, new Dilation(0.0F)), ModelTransform.of(-0.8678F, 0.7624F, 1.7744F, -0.1355F, -0.2595F, 0.035F));

		ModelPartData wing_right = torso.addChild("wing_right", ModelPartBuilder.create(), ModelTransform.of(-2.0F, 5.5F, 9.35F, 0.9599F, 0.0F, 0.0F));

		ModelPartData arm_inner = wing_right.addChild("arm_inner", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r95 = arm_inner.addChild("cube_r95", ModelPartBuilder.create().uv(0, 31).cuboid(-4.3937F, 1.1F, -5.3875F, 6.1875F, 0.1F, 9.575F, new Dilation(0.0F)), ModelTransform.of(-5.0763F, 1.3133F, 3.9442F, -1.1474F, -0.6178F, 1.5836F));

		ModelPartData cube_r96 = arm_inner.addChild("cube_r96", ModelPartBuilder.create().uv(5, 110).cuboid(-6.8938F, -0.375F, -2.9125F, 6.6875F, 2.35F, 3.625F, new Dilation(0.0F)), ModelTransform.of(2.0188F, 0.3749F, -0.6382F, -0.0541F, 0.2492F, 0.3767F));

		ModelPartData cube_r97 = arm_inner.addChild("cube_r97", ModelPartBuilder.create().uv(13, 139).cuboid(-13.9937F, -0.125F, -2.4125F, 6.6875F, 1.85F, 2.375F, new Dilation(0.0F)), ModelTransform.of(2.3905F, 0.522F, -0.7399F, -0.0541F, 0.2492F, 0.3767F));

		ModelPartData arm_outer = arm_inner.addChild("arm_outer", ModelPartBuilder.create(), ModelTransform.of(-10.0355F, -4.3596F, 1.4176F, 0.0F, 1.6581F, 0.0F));

		ModelPartData cube_r98 = arm_outer.addChild("cube_r98", ModelPartBuilder.create().uv(77, 154).cuboid(-0.8521F, 0.6808F, -20.2234F, 27.7375F, 0.1F, 21.275F, new Dilation(0.0F)), ModelTransform.of(-2.2151F, 0.5268F, -17.7443F, -3.0572F, 1.1311F, 2.2248F));

		ModelPartData cube_r99 = arm_outer.addChild("cube_r99", ModelPartBuilder.create().uv(0, 156).cuboid(0.536F, 1.4517F, -0.8169F, 11.6875F, 0.1F, 19.425F, new Dilation(0.0F)), ModelTransform.of(-1.4621F, 0.2604F, 0.1533F, -3.067F, 0.0829F, 2.1951F));

		ModelPartData cube_r100 = arm_outer.addChild("cube_r100", ModelPartBuilder.create().uv(159, 43).cuboid(-0.1544F, 0.0048F, -0.1848F, 4.2875F, 0.55F, 0.575F, new Dilation(0.0F)), ModelTransform.of(-1.9621F, 3.2604F, 0.4533F, 3.0917F, -0.0403F, 2.2127F));

		ModelPartData cube_r101 = arm_outer.addChild("cube_r101", ModelPartBuilder.create().uv(157, 45).cuboid(0.2008F, 1.2649F, -0.3905F, 2.4375F, 0.55F, 0.575F, new Dilation(0.0F)), ModelTransform.of(-1.4621F, 0.2604F, 0.4533F, 3.091F, 0.097F, 2.1621F));

		ModelPartData cube_r102 = arm_outer.addChild("cube_r102", ModelPartBuilder.create().uv(120, 98).cuboid(-3.7938F, -0.225F, -2.3875F, 6.6875F, 2.15F, 2.675F, new Dilation(0.0F)), ModelTransform.of(-1.1856F, -0.2456F, -2.7139F, -0.174F, -1.4488F, 0.1248F));

		ModelPartData cube_r103 = arm_outer.addChild("cube_r103", ModelPartBuilder.create().uv(152, 84).cuboid(-10.7938F, -0.125F, -1.9375F, 6.6875F, 1.75F, 1.975F, new Dilation(0.0F)), ModelTransform.of(-1.0174F, -0.1623F, -2.464F, -0.174F, -1.4488F, 0.1248F));

		ModelPartData cube_r104 = arm_outer.addChild("cube_r104", ModelPartBuilder.create().uv(136, 100).cuboid(-16.2938F, 0.025F, -1.3875F, 4.7875F, 1.35F, 1.575F, new Dilation(0.0F)), ModelTransform.of(-0.5297F, -0.172F, -1.8143F, -0.174F, -1.4488F, 0.1248F));

		ModelPartData wing_splines = arm_outer.addChild("wing_splines", ModelPartBuilder.create(), ModelTransform.of(-1.7723F, -0.0586F, -18.2726F, 0.0F, -0.829F, -0.9599F));

		ModelPartData spline_outer = wing_splines.addChild("spline_outer", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

		ModelPartData cube_r105 = spline_outer.addChild("cube_r105", ModelPartBuilder.create().uv(157, 44).cuboid(-0.3776F, -0.4923F, -0.1985F, 7.8875F, 0.55F, 0.725F, new Dilation(0.0F)), ModelTransform.of(-0.4428F, 0.5854F, 0.5283F, 3.1295F, -0.5572F, 3.0966F));

		ModelPartData cube_r106 = spline_outer.addChild("cube_r106", ModelPartBuilder.create().uv(154, 43).cuboid(6.5187F, -0.4923F, 2.8022F, 10.9375F, 0.55F, 0.875F, new Dilation(0.0F)), ModelTransform.of(-0.4428F, 0.5854F, 0.5283F, 3.1228F, -0.9935F, 3.1059F));

		ModelPartData cube_r107 = spline_outer.addChild("cube_r107", ModelPartBuilder.create().uv(152, 44).mirrored().cuboid(15.931F, -0.4832F, 7.235F, 16.6875F, 0.55F, 0.825F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.4428F, 0.5854F, 0.5283F, 3.1085F, -1.2552F, 3.1216F));

		ModelPartData spline_mid = wing_splines.addChild("spline_mid", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.0F));

		ModelPartData cube_r108 = spline_mid.addChild("cube_r108", ModelPartBuilder.create().uv(164, 42).mirrored().cuboid(-0.8865F, -0.2503F, -0.2457F, 13.6875F, 0.55F, 0.575F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.4428F, 0.5854F, 0.5284F, 3.1228F, -0.9935F, 3.1059F));

		ModelPartData cube_r109 = spline_mid.addChild("cube_r109", ModelPartBuilder.create().uv(151, 43).cuboid(12.1993F, -0.6719F, 3.0168F, 11.0875F, 0.55F, 0.575F, new Dilation(0.0F)), ModelTransform.of(-0.4428F, 0.5854F, 0.5284F, -3.0678F, -1.2545F, 3.0092F));

		ModelPartData spline_inner = wing_splines.addChild("spline_inner", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r110 = spline_inner.addChild("cube_r110", ModelPartBuilder.create().uv(156, 43).cuboid(7.9016F, -0.458F, 1.7207F, 6.6875F, 0.55F, 0.75F, new Dilation(0.0F)), ModelTransform.of(0.4772F, 0.6815F, -0.5996F, -2.7889F, -1.4668F, 2.7349F));

		ModelPartData cube_r111 = spline_inner.addChild("cube_r111", ModelPartBuilder.create().uv(154, 44).cuboid(0.021F, -0.1671F, -0.4625F, 8.1875F, 0.55F, 0.8F, new Dilation(0.0F)), ModelTransform.of(0.4772F, 0.6815F, -0.5996F, -3.1384F, -1.2115F, 3.0862F));

		ModelPartData claw_right = arm_outer.addChild("claw_right", ModelPartBuilder.create(), ModelTransform.pivot(-13.2241F, 0.431F, -18.5284F));

		ModelPartData cube_r112 = claw_right.addChild("cube_r112", ModelPartBuilder.create().uv(24, 0).cuboid(0.7597F, -0.33F, -9.0538F, 1.2375F, 0.85F, 0.975F, new Dilation(-0.35F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0045F, -1.0755F, -0.5738F));

		ModelPartData cube_r113 = claw_right.addChild("cube_r113", ModelPartBuilder.create().uv(158, 142).cuboid(1.1979F, -1.0499F, -9.1038F, 1.7875F, 0.85F, 1.075F, new Dilation(-0.3F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6613F, -0.9244F, 0.204F));

		ModelPartData cube_r114 = claw_right.addChild("cube_r114", ModelPartBuilder.create().uv(66, 40).cuboid(1.9749F, -2.1452F, -9.1038F, 2.0375F, 0.85F, 1.075F, new Dilation(-0.2F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.9556F, -0.6031F, 0.6209F));

		ModelPartData cube_r115 = claw_right.addChild("cube_r115", ModelPartBuilder.create().uv(32, 168).cuboid(2.028F, 1.7143F, -11.1783F, 1.2375F, 0.85F, 0.975F, new Dilation(-0.35F)), ModelTransform.of(-1.807F, -5.3436F, -3.5808F, 0.3528F, -1.2899F, -0.5392F));

		ModelPartData cube_r116 = claw_right.addChild("cube_r116", ModelPartBuilder.create().uv(28, 166).cuboid(-1.8387F, -3.5582F, -11.2283F, 1.7875F, 0.85F, 1.075F, new Dilation(-0.3F)), ModelTransform.of(0.0F, -0.3F, -0.2F, -0.8871F, -1.1464F, 0.7589F));

		ModelPartData cube_r117 = claw_right.addChild("cube_r117", ModelPartBuilder.create().uv(24, 165).cuboid(-1.8372F, -3.1352F, -11.2283F, 2.0375F, 0.85F, 1.075F, new Dilation(-0.2F)), ModelTransform.of(0.0F, -0.3F, -0.2F, -1.2027F, -0.7628F, 1.1372F));

		ModelPartData cube_r118 = claw_right.addChild("cube_r118", ModelPartBuilder.create().uv(23, 100).mirrored().cuboid(-4.1182F, -9.6513F, -11.1631F, 1.2375F, 0.85F, 0.975F, new Dilation(-0.35F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 2.2051F, -1.4717F, -1.9092F));

		ModelPartData cube_r119 = claw_right.addChild("cube_r119", ModelPartBuilder.create().uv(20, 166).cuboid(-7.1624F, -7.4364F, -11.2131F, 1.7875F, 0.85F, 1.075F, new Dilation(-0.3F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.7376F, -1.2098F, 2.0206F));

		ModelPartData cube_r120 = claw_right.addChild("cube_r120", ModelPartBuilder.create().uv(18, 129).cuboid(-8.3012F, -4.4002F, -11.2131F, 2.0375F, 0.85F, 1.075F, new Dilation(-0.2F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.6531F, -0.7764F, 1.9221F));

		ModelPartData cube_r121 = claw_right.addChild("cube_r121", ModelPartBuilder.create().uv(14, 164).cuboid(-7.5657F, -1.9843F, -11.2131F, 2.3375F, 0.85F, 1.075F, new Dilation(-0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.6353F, -0.4282F, 1.8912F));

		ModelPartData cube_r122 = claw_right.addChild("cube_r122", ModelPartBuilder.create().uv(69, 164).cuboid(-1.0589F, -3.0064F, -11.2283F, 2.3375F, 0.85F, 1.075F, new Dilation(-0.1F)), ModelTransform.of(0.0F, -0.3F, -0.2F, -1.2803F, -0.4319F, 1.2731F));

		ModelPartData cube_r123 = claw_right.addChild("cube_r123", ModelPartBuilder.create().uv(69, 164).cuboid(2.8619F, -3.3799F, -9.1038F, 2.3375F, 0.85F, 1.075F, new Dilation(-0.1F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.0486F, -0.3078F, 0.8296F));

		ModelPartData cube_r124 = claw_right.addChild("cube_r124", ModelPartBuilder.create().uv(1, 119).mirrored().cuboid(0.1581F, -3.1721F, -11.2283F, 2.6875F, 0.85F, 1.075F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, -0.3F, -0.2F, -1.3009F, -0.2222F, 1.3367F));

		ModelPartData cube_r125 = claw_right.addChild("cube_r125", ModelPartBuilder.create().uv(66, 164).mirrored().cuboid(-5.9732F, -0.7659F, -11.2131F, 2.6875F, 0.85F, 1.075F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.6308F, -0.2105F, 1.8769F));

		ModelPartData cube_r126 = claw_right.addChild("cube_r126", ModelPartBuilder.create().uv(61, 164).cuboid(3.9051F, -4.3854F, -9.1038F, 2.6875F, 0.85F, 1.075F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.0717F, -0.1172F, 0.9385F));

		ModelPartData wing_left = torso.addChild("wing_left", ModelPartBuilder.create(), ModelTransform.of(2.0F, 5.5F, 9.35F, 0.9599F, 0.0F, 0.0F));

		ModelPartData arm_inner2 = wing_left.addChild("arm_inner2", ModelPartBuilder.create(), ModelTransform.pivot(-2.0188F, 0.3749F, -0.6382F));

		ModelPartData cube_r127 = arm_inner2.addChild("cube_r127", ModelPartBuilder.create().uv(0, 31).cuboid(-1.7937F, 1.1F, -5.3875F, 6.1875F, 0.1F, 9.575F, new Dilation(0.0F)), ModelTransform.of(7.095F, 0.9384F, 4.5824F, -1.1474F, 0.6178F, -1.5836F));

		ModelPartData cube_r128 = arm_inner2.addChild("cube_r128", ModelPartBuilder.create().uv(5, 110).mirrored().cuboid(0.2063F, -0.375F, -2.9125F, 6.6875F, 2.35F, 3.625F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.0541F, -0.2492F, -0.3767F));

		ModelPartData cube_r129 = arm_inner2.addChild("cube_r129", ModelPartBuilder.create().uv(13, 139).mirrored().cuboid(7.3063F, -0.125F, -2.4125F, 6.6875F, 1.85F, 2.375F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.3717F, 0.147F, -0.1017F, -0.0541F, -0.2492F, -0.3767F));

		ModelPartData arm_outer2 = arm_inner2.addChild("arm_outer2", ModelPartBuilder.create(), ModelTransform.of(13.3543F, -4.7345F, 2.3557F, 0.0F, -1.6581F, 0.0F));

		ModelPartData cube_r130 = arm_outer2.addChild("cube_r130", ModelPartBuilder.create().uv(77, 154).cuboid(-22.8687F, -0.175F, -17.6375F, 27.7375F, 0.1F, 21.275F, new Dilation(0.0F)), ModelTransform.of(3.5986F, 4.334F, -18.9914F, -3.0572F, -1.1311F, -2.2248F));

		ModelPartData cube_r131 = arm_outer2.addChild("cube_r131", ModelPartBuilder.create().uv(0, 156).cuboid(-12.2235F, 1.4517F, -0.8169F, 11.6875F, 0.1F, 19.425F, new Dilation(0.0F)), ModelTransform.of(1.2765F, 0.2604F, 1.4745F, -3.067F, -0.0829F, -2.1951F));

		ModelPartData cube_r132 = arm_outer2.addChild("cube_r132", ModelPartBuilder.create().uv(159, 43).mirrored().cuboid(-4.1331F, 0.0048F, -0.1848F, 4.2875F, 0.55F, 0.575F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.7765F, 3.2604F, 1.7745F, 3.0917F, 0.0403F, -2.2127F));

		ModelPartData cube_r133 = arm_outer2.addChild("cube_r133", ModelPartBuilder.create().uv(157, 45).mirrored().cuboid(-2.6383F, 1.2649F, -0.3905F, 2.4375F, 0.55F, 0.575F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.2765F, 0.2604F, 1.7745F, 3.091F, -0.097F, -2.1621F));

		ModelPartData cube_r134 = arm_outer2.addChild("cube_r134", ModelPartBuilder.create().uv(120, 98).mirrored().cuboid(-2.8937F, -0.225F, -2.3875F, 6.6875F, 2.15F, 2.675F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.0F, -0.2456F, -1.3927F, -0.174F, 1.4488F, -0.1248F));

		ModelPartData cube_r135 = arm_outer2.addChild("cube_r135", ModelPartBuilder.create().uv(152, 84).mirrored().cuboid(4.1063F, -0.125F, -1.9375F, 6.6875F, 1.75F, 1.975F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.8319F, -0.1623F, -1.1428F, -0.174F, 1.4488F, -0.1248F));

		ModelPartData cube_r136 = arm_outer2.addChild("cube_r136", ModelPartBuilder.create().uv(136, 100).mirrored().cuboid(11.5063F, 0.025F, -1.3875F, 4.7875F, 1.35F, 1.575F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.3441F, -0.172F, -0.4931F, -0.174F, 1.4488F, -0.1248F));

		ModelPartData wing_splines2 = arm_outer2.addChild("wing_splines2", ModelPartBuilder.create(), ModelTransform.of(1.5868F, -0.0586F, -16.9514F, 0.0F, 0.829F, 0.9599F));

		ModelPartData spline_outer2 = wing_splines2.addChild("spline_outer2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.6545F, 0.0F));

		ModelPartData cube_r137 = spline_outer2.addChild("cube_r137", ModelPartBuilder.create().uv(157, 44).mirrored().cuboid(-8.1871F, 0.0749F, -0.3922F, 7.8875F, 0.55F, 0.725F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 3.1295F, 0.5572F, -3.0966F));

		ModelPartData cube_r138 = spline_outer2.addChild("cube_r138", ModelPartBuilder.create().uv(154, 43).mirrored().cuboid(-13.4912F, -0.5811F, 0.2486F, 10.9375F, 0.55F, 0.875F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.7371F, 0.8754F, 2.4583F, 3.1228F, 0.9935F, -3.1059F));

		ModelPartData cube_r139 = spline_outer2.addChild("cube_r139", ModelPartBuilder.create().uv(152, 44).cuboid(-25.726F, 0.0446F, -7.6489F, 16.6875F, 0.55F, 0.825F, new Dilation(0.0F)), ModelTransform.of(16.7371F, 0.8754F, 2.4583F, 3.1085F, 1.2552F, -3.1216F));

		ModelPartData spline_mid2 = wing_splines2.addChild("spline_mid2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.3491F, 0.0F));

		ModelPartData cube_r140 = spline_mid2.addChild("cube_r140", ModelPartBuilder.create().uv(164, 42).cuboid(-11.8396F, -0.422F, 1.8117F, 13.6875F, 0.55F, 0.575F, new Dilation(0.0F)), ModelTransform.of(-0.7629F, 0.6755F, 2.4583F, 3.1228F, 0.9935F, -3.1059F));

		ModelPartData cube_r141 = spline_mid2.addChild("cube_r141", ModelPartBuilder.create().uv(151, 43).mirrored().cuboid(-18.1208F, -0.0463F, -6.6357F, 11.0875F, 0.55F, 0.575F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(11.2371F, 0.6755F, 2.4583F, -3.0678F, 1.2545F, -3.0092F));

		ModelPartData spline_inner2 = wing_splines2.addChild("spline_inner2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r142 = spline_inner2.addChild("cube_r142", ModelPartBuilder.create().uv(156, 43).mirrored().cuboid(-14.5891F, -0.458F, 1.7207F, 6.6875F, 0.55F, 0.75F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.4772F, 0.6815F, -0.5996F, -2.7889F, 1.4668F, -2.7349F));

		ModelPartData cube_r143 = spline_inner2.addChild("cube_r143", ModelPartBuilder.create().uv(154, 44).mirrored().cuboid(-8.2085F, -0.1671F, -0.4625F, 8.1875F, 0.55F, 0.8F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.4772F, 0.6815F, -0.5996F, -3.1384F, 1.2115F, -3.0862F));

		ModelPartData claw_left = arm_outer2.addChild("claw_left", ModelPartBuilder.create(), ModelTransform.pivot(13.0385F, 0.431F, -17.2072F));

		ModelPartData cube_r144 = claw_left.addChild("cube_r144", ModelPartBuilder.create().uv(24, 0).mirrored().cuboid(-1.9972F, -0.33F, -9.0538F, 1.2375F, 0.85F, 0.975F, new Dilation(-0.35F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0045F, 1.0755F, 0.5738F));

		ModelPartData cube_r145 = claw_left.addChild("cube_r145", ModelPartBuilder.create().uv(158, 142).mirrored().cuboid(-2.9854F, -1.0499F, -9.1038F, 1.7875F, 0.85F, 1.075F, new Dilation(-0.3F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.6613F, 0.9244F, -0.204F));

		ModelPartData cube_r146 = claw_left.addChild("cube_r146", ModelPartBuilder.create().uv(66, 40).mirrored().cuboid(-4.0124F, -2.1452F, -9.1038F, 2.0375F, 0.85F, 1.075F, new Dilation(-0.2F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.9556F, 0.6031F, -0.6209F));

		ModelPartData cube_r147 = claw_left.addChild("cube_r147", ModelPartBuilder.create().uv(32, 168).mirrored().cuboid(-3.2655F, 1.7143F, -11.1783F, 1.2375F, 0.85F, 0.975F, new Dilation(-0.35F)).mirrored(false), ModelTransform.of(1.807F, -5.3436F, -3.5808F, 0.3528F, 1.2899F, 0.5392F));

		ModelPartData cube_r148 = claw_left.addChild("cube_r148", ModelPartBuilder.create().uv(28, 166).mirrored().cuboid(0.0512F, -3.5582F, -11.2283F, 1.7875F, 0.85F, 1.075F, new Dilation(-0.3F)).mirrored(false), ModelTransform.of(0.0F, -0.3F, -0.2F, -0.8871F, 1.1464F, -0.7589F));

		ModelPartData cube_r149 = claw_left.addChild("cube_r149", ModelPartBuilder.create().uv(24, 165).mirrored().cuboid(-0.2003F, -3.1352F, -11.2283F, 2.0375F, 0.85F, 1.075F, new Dilation(-0.2F)).mirrored(false), ModelTransform.of(0.0F, -0.3F, -0.2F, -1.2027F, 0.7628F, -1.1372F));

		ModelPartData cube_r150 = claw_left.addChild("cube_r150", ModelPartBuilder.create().uv(23, 100).cuboid(2.8807F, -9.6513F, -11.1631F, 1.2375F, 0.85F, 0.975F, new Dilation(-0.35F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 2.2051F, 1.4717F, 1.9092F));

		ModelPartData cube_r151 = claw_left.addChild("cube_r151", ModelPartBuilder.create().uv(20, 166).mirrored().cuboid(5.3749F, -7.4364F, -11.2131F, 1.7875F, 0.85F, 1.075F, new Dilation(-0.3F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.7376F, 1.2098F, -2.0206F));

		ModelPartData cube_r152 = claw_left.addChild("cube_r152", ModelPartBuilder.create().uv(18, 129).mirrored().cuboid(6.2637F, -4.4002F, -11.2131F, 2.0375F, 0.85F, 1.075F, new Dilation(-0.2F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.6531F, 0.7764F, -1.9221F));

		ModelPartData cube_r153 = claw_left.addChild("cube_r153", ModelPartBuilder.create().uv(14, 164).mirrored().cuboid(5.2282F, -1.9843F, -11.2131F, 2.3375F, 0.85F, 1.075F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.6353F, 0.4282F, -1.8912F));

		ModelPartData cube_r154 = claw_left.addChild("cube_r154", ModelPartBuilder.create().uv(69, 164).mirrored().cuboid(-1.2786F, -3.0064F, -11.2283F, 2.3375F, 0.85F, 1.075F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(0.0F, -0.3F, -0.2F, -1.2803F, 0.4319F, -1.2731F));

		ModelPartData cube_r155 = claw_left.addChild("cube_r155", ModelPartBuilder.create().uv(69, 164).mirrored().cuboid(-5.1994F, -3.3799F, -9.1038F, 2.3375F, 0.85F, 1.075F, new Dilation(-0.1F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.0486F, 0.3078F, -0.8296F));

		ModelPartData cube_r156 = claw_left.addChild("cube_r156", ModelPartBuilder.create().uv(1, 119).cuboid(-2.8456F, -3.1721F, -11.2283F, 2.6875F, 0.85F, 1.075F, new Dilation(0.0F)), ModelTransform.of(0.0F, -0.3F, -0.2F, -1.3009F, 0.2222F, -1.3367F));

		ModelPartData cube_r157 = claw_left.addChild("cube_r157", ModelPartBuilder.create().uv(66, 164).cuboid(3.2857F, -0.7659F, -11.2131F, 2.6875F, 0.85F, 1.075F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.6308F, 0.2105F, -1.8769F));

		ModelPartData cube_r158 = claw_left.addChild("cube_r158", ModelPartBuilder.create().uv(61, 164).mirrored().cuboid(-6.5926F, -4.3854F, -9.1038F, 2.6875F, 0.85F, 1.075F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.0717F, 0.1172F, -0.9385F));

		ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 25.0F));

		ModelPartData cube_r159 = tail.addChild("cube_r159", ModelPartBuilder.create().uv(155, 51).cuboid(-0.3938F, 0.475F, 30.7125F, 0.7875F, 0.6F, 2.675F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-0.8938F, 0.225F, 27.2625F, 1.7875F, 1.1F, 3.675F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-1.3938F, -0.275F, 23.2625F, 2.7875F, 2.1F, 4.175F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-1.8938F, -0.775F, 19.2625F, 3.7875F, 3.1F, 4.175F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-2.3937F, -1.275F, 15.2625F, 4.7875F, 4.1F, 4.175F, new Dilation(0.0F))
				.uv(155, 51).cuboid(-2.8937F, -1.775F, 12.2625F, 5.7875F, 5.1F, 3.175F, new Dilation(0.0F)), ModelTransform.of(0.0188F, -0.675F, -11.7375F, -0.0873F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 176, 176);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return highlandDragon;
	}

	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		//this.setHeadAngles(netHeadYaw, headPitch);

		// TODO
		//this.animateMovement(ModAnimations.DRAGONWHELP_WALK, limbAngle, limbDistance, 2f, 2.5f);

		//this.updateAnimation(entity.idleAnimationState, ModAnimations.DRAGONWHELP_IDLE, ageInTicks, 1f);
		//this.updateAnimation(entity.attackAnimationState, ModAnimations.DRAGONWHELP_ATTACKBITE, ageInTicks, 1f);
	}
}