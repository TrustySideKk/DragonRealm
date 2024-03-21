package com.trustysidekick.dragonrealm.entity.client;

import com.trustysidekick.dragonrealm.entity.animation.ModAnimations;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class DragonWhelpModel<T extends DragonWhelpEntity> extends SinglePartEntityModel<T> {
	private final ModelPart dragonwhelp;
	private final ModelPart head;

	public DragonWhelpModel(ModelPart root) {
		this.dragonwhelp = root.getChild("root");
		this.head = dragonwhelp.getChild("body").getChild("torso").getChild("neck").getChild("head");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -5.7F, 0.0F));

		ModelPartData body = root.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(0, 57).mirrored().cuboid(-12.325F, -2.75F, -18.75F, 25.0F, 14.0F, 39.0F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 66).mirrored().cuboid(-8.425F, -8.75F, -21.25F, 16.85F, 7.0F, 42.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.825F, 7.45F, 30.9F));

		ModelPartData tail1 = torso.addChild("tail1", ModelPartBuilder.create().uv(0, 99).cuboid(-6.5F, -6.25F, 1.15F, 14.65F, 12.9F, 15.5F, new Dilation(0.0F)), ModelTransform.pivot(-0.825F, -1.45F, 19.1F));

		ModelPartData tail2 = tail1.addChild("tail2", ModelPartBuilder.create().uv(0, 100).cuboid(-5.5F, -5.75F, 0.15F, 12.65F, 11.4F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, 15.5F));

		ModelPartData tail3 = tail2.addChild("tail3", ModelPartBuilder.create().uv(3, 105).cuboid(-4.5F, -4.75F, 0.65F, 10.65F, 9.4F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, 15.0F));

		ModelPartData tail4 = tail3.addChild("tail4", ModelPartBuilder.create().uv(4, 109).cuboid(-3.5F, -2.75F, 0.15F, 8.65F, 6.4F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 12.5F));

		ModelPartData tail5 = tail4.addChild("tail5", ModelPartBuilder.create().uv(0, 112).cuboid(-2.0F, -2.1F, 0.65F, 5.65F, 3.9F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, 11.0F));

		ModelPartData neck = torso.addChild("neck", ModelPartBuilder.create(), ModelTransform.pivot(-0.825F, 10.05F, -18.4F));

		ModelPartData cube_r1 = neck.addChild("cube_r1", ModelPartBuilder.create().uv(0, 58).cuboid(-6.175F, 11.95F, 1.65F, 13.85F, 10.5F, 20.0F, new Dilation(0.0F)), ModelTransform.of(0.075F, -30.25F, -2.5F, -0.6981F, 0.0F, 0.0F));

		ModelPartData cube_r2 = neck.addChild("cube_r2", ModelPartBuilder.create().uv(19, 83).mirrored().cuboid(-5.925F, 11.45F, -8.35F, 13.35F, 10.5F, 30.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.075F, -30.25F, -2.5F, -0.3491F, 0.0F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(0, 103).cuboid(-7.5F, -10.5F, -9.0F, 15.15F, 10.0F, 10.5F, new Dilation(0.0F)), ModelTransform.pivot(0.8F, -15.5F, -15.5F));

		ModelPartData cube_r3 = head.addChild("cube_r3", ModelPartBuilder.create().uv(42, 117).cuboid(-4.3F, -2.275F, -2.25F, 8.6F, 1.55F, 4.5F, new Dilation(0.0F)), ModelTransform.of(0.15F, 7.0281F, -0.6377F, 0.3927F, 0.0F, 0.0F));

		ModelPartData cube_r4 = head.addChild("cube_r4", ModelPartBuilder.create().uv(1, 84).cuboid(-4.425F, 7.55F, -1.4F, 9.0F, 1.55F, 13.0F, new Dilation(0.0F)), ModelTransform.of(0.075F, -4.5979F, -11.8612F, -0.2618F, 0.0F, 0.0F));

		ModelPartData cube_r5 = head.addChild("cube_r5", ModelPartBuilder.create().uv(31, 106).mirrored().cuboid(4.4F, -1.5F, -9.75F, 5.2F, 1.4F, 13.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(6.7633F, -2.8058F, -3.75F, 0.0436F, 0.2618F, 2.0071F));

		ModelPartData cube_r6 = head.addChild("cube_r6", ModelPartBuilder.create().uv(0, 106).mirrored().cuboid(-9.6F, -1.5F, -9.75F, 5.2F, 1.4F, 13.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-6.7633F, -2.8058F, -3.75F, 0.0436F, -0.2618F, -2.0071F));

		ModelPartData cube_r7 = head.addChild("cube_r7", ModelPartBuilder.create().uv(0, 117).cuboid(-2.25F, 1.1F, -2.3F, 3.5F, 2.0F, 4.5F, new Dilation(0.0F)), ModelTransform.of(9.1804F, -0.1557F, -10.6933F, 0.2602F, -0.2975F, 1.4443F));

		ModelPartData cube_r8 = head.addChild("cube_r8", ModelPartBuilder.create().uv(114, 91).mirrored().cuboid(-4.6189F, -3.5428F, -4.5F, 1.6F, 1.25F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-2.7633F, -5.5058F, -9.0F, 0.2864F, 0.0525F, -1.3767F));

		ModelPartData cube_r9 = head.addChild("cube_r9", ModelPartBuilder.create().uv(114, 91).cuboid(3.0189F, -3.5428F, -4.5F, 1.6F, 1.25F, 5.0F, new Dilation(0.0F))
				.uv(114, 91).cuboid(-1.4811F, -4.0428F, -5.0F, 1.6F, 1.25F, 5.0F, new Dilation(0.0F)), ModelTransform.of(2.9133F, -5.5058F, -9.0F, 0.2864F, -0.0525F, 1.3767F));

		ModelPartData cube_r10 = head.addChild("cube_r10", ModelPartBuilder.create().uv(114, 91).mirrored().cuboid(0.3811F, -2.2428F, -5.0F, 1.6F, 1.25F, 5.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-4.5133F, -5.5058F, -9.0F, 0.2864F, 0.0525F, -1.3767F));

		ModelPartData cube_r11 = head.addChild("cube_r11", ModelPartBuilder.create().uv(0, 19).cuboid(-4.1189F, -1.7428F, -6.5F, 7.1F, 1.25F, 8.5F, new Dilation(0.0F)), ModelTransform.of(-4.5133F, -5.5058F, -9.0F, 0.2964F, 0.2615F, -1.3134F));

		ModelPartData cube_r12 = head.addChild("cube_r12", ModelPartBuilder.create().uv(0, 19).mirrored().cuboid(-2.9811F, -1.7428F, -6.5F, 7.1F, 1.25F, 8.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.6633F, -5.5058F, -9.0F, 0.2964F, -0.2615F, 1.3134F));

		ModelPartData cube_r13 = head.addChild("cube_r13", ModelPartBuilder.create().uv(0, 117).mirrored().cuboid(-1.25F, 1.1F, -2.3F, 3.5F, 2.0F, 4.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-9.1804F, -0.1557F, -10.6933F, 0.2602F, 0.2975F, -1.4443F));

		ModelPartData cube_r14 = head.addChild("cube_r14", ModelPartBuilder.create().uv(5, 111).mirrored().cuboid(-3.4F, -1.6F, -5.25F, 3.5F, 2.0F, 10.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-6.7633F, -2.8058F, -3.75F, 0.0F, 0.0F, -1.5708F));

		ModelPartData cube_r15 = head.addChild("cube_r15", ModelPartBuilder.create().uv(2, 108).mirrored().cuboid(-7.3F, -1.6F, -4.25F, 5.55F, 2.0F, 10.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-6.7633F, -2.8058F, -3.75F, 0.0F, -0.2182F, -1.8762F));

		ModelPartData cube_r16 = head.addChild("cube_r16", ModelPartBuilder.create().uv(27, 108).mirrored().cuboid(1.75F, -1.6F, -4.25F, 5.55F, 2.0F, 10.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(6.7633F, -2.8058F, -3.75F, 0.0F, 0.2182F, 1.8762F));

		ModelPartData cube_r17 = head.addChild("cube_r17", ModelPartBuilder.create().uv(29, 111).mirrored().cuboid(-0.1F, -1.6F, -5.25F, 3.5F, 2.0F, 10.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(6.7633F, -2.8058F, -3.75F, 0.0F, 0.0F, 1.5708F));

		ModelPartData cube_r18 = head.addChild("cube_r18", ModelPartBuilder.create().uv(68, 75).cuboid(-4.525F, -4.5F, -9.5F, 9.05F, 8.05F, 14.5F, new Dilation(0.0F)), ModelTransform.of(0.075F, -4.5979F, -11.8612F, 0.1745F, 0.0F, 0.0F));

		ModelPartData cube_r19 = head.addChild("cube_r19", ModelPartBuilder.create().uv(8, 107).cuboid(-7.425F, -3.05F, -6.85F, 14.85F, 7.5F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.075F, -4.25F, 5.5F, -0.5672F, 0.0F, 0.0F));

		ModelPartData horn_left = head.addChild("horn_left", ModelPartBuilder.create(), ModelTransform.pivot(16.5F, 3.5F, -6.0F));

		ModelPartData horn_r1 = horn_left.addChild("horn_r1", ModelPartBuilder.create().uv(98, 56).mirrored().cuboid(-3.624F, 1.7365F, 1.1325F, 3.775F, 2.9445F, 7.9275F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-13.8624F, -13.0702F, 5.5769F, -0.2728F, 0.4203F, -1.6359F));

		ModelPartData horn_r2 = horn_left.addChild("horn_r2", ModelPartBuilder.create().uv(103, 59).cuboid(-1.5855F, -1.3968F, -1.3213F, 3.171F, 2.1895F, 2.6425F, new Dilation(0.0F)), ModelTransform.of(-8.127F, -15.5896F, 14.6234F, -0.1419F, 0.4203F, -1.6359F));

		ModelPartData horn_r3 = horn_left.addChild("horn_r3", ModelPartBuilder.create().uv(104, 60).cuboid(-1.2835F, -1.1703F, 1.3212F, 2.567F, 2.0385F, 2.6425F, new Dilation(0.0F)), ModelTransform.of(-8.127F, -15.5896F, 14.6234F, -0.0546F, 0.4203F, -1.6359F));

		ModelPartData horn_r4 = horn_left.addChild("horn_r4", ModelPartBuilder.create().uv(107, 60).mirrored().cuboid(-0.5285F, -0.1887F, 5.8512F, 1.057F, 0.9815F, 1.8875F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-8.127F, -15.5896F, 14.6234F, 0.1112F, 0.4203F, -1.6359F));

		ModelPartData horn_r5 = horn_left.addChild("horn_r5", ModelPartBuilder.create().uv(104, 60).mirrored().cuboid(-0.906F, -0.8683F, 3.9638F, 1.812F, 1.4345F, 1.8875F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-8.127F, -15.5896F, 14.6234F, 0.0152F, 0.4203F, -1.6359F));

		ModelPartData horn_right = head.addChild("horn_right", ModelPartBuilder.create(), ModelTransform.pivot(-16.35F, 3.5F, -6.0F));

		ModelPartData horn_r6 = horn_right.addChild("horn_r6", ModelPartBuilder.create().uv(98, 55).cuboid(-0.151F, 1.7365F, 1.1325F, 3.775F, 2.9445F, 7.9275F, new Dilation(0.0F)), ModelTransform.of(13.8624F, -13.0702F, 5.5769F, -0.2728F, -0.4203F, 1.6359F));

		ModelPartData horn_r7 = horn_right.addChild("horn_r7", ModelPartBuilder.create().uv(103, 61).cuboid(-1.5855F, -1.3968F, -1.3213F, 3.171F, 2.1895F, 2.6425F, new Dilation(0.0F)), ModelTransform.of(8.127F, -15.5896F, 14.6234F, -0.1419F, -0.4203F, 1.6359F));

		ModelPartData horn_r8 = horn_right.addChild("horn_r8", ModelPartBuilder.create().uv(104, 60).cuboid(-1.2835F, -1.1703F, 1.3212F, 2.567F, 2.0385F, 2.6425F, new Dilation(0.0F)), ModelTransform.of(8.127F, -15.5896F, 14.6234F, -0.0546F, -0.4203F, 1.6359F));

		ModelPartData horn_r9 = horn_right.addChild("horn_r9", ModelPartBuilder.create().uv(106, 60).mirrored().cuboid(-0.5285F, -0.1887F, 5.8512F, 1.057F, 0.9815F, 1.8875F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(8.127F, -15.5896F, 14.6234F, 0.1112F, -0.4203F, 1.6359F));

		ModelPartData horn_r10 = horn_right.addChild("horn_r10", ModelPartBuilder.create().uv(105, 61).cuboid(-0.906F, -0.8683F, 3.9638F, 1.812F, 1.4345F, 1.8875F, new Dilation(0.0F)), ModelTransform.of(8.127F, -15.5896F, 14.6234F, 0.0152F, -0.4203F, 1.6359F));

		ModelPartData jaw_lower = head.addChild("jaw_lower", ModelPartBuilder.create(), ModelTransform.of(0.0F, 1.0F, -10.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData cube_r20 = jaw_lower.addChild("cube_r20", ModelPartBuilder.create().uv(82, 77).mirrored().cuboid(-3.425F, 6.55F, -7.9F, 6.85F, 2.05F, 11.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.075F, -5.9502F, -1.7161F, 0.0873F, 0.0F, 0.0F));

		ModelPartData wing_right = torso.addChild("wing_right", ModelPartBuilder.create(), ModelTransform.pivot(-6.275F, -8.45F, -12.9F));

		ModelPartData wing_right_inner = wing_right.addChild("wing_right_inner", ModelPartBuilder.create(), ModelTransform.of(-0.0884F, 3.3604F, -2.0299F, 0.0F, 0.0F, -0.0436F));

		ModelPartData cube_r21 = wing_right_inner.addChild("cube_r21", ModelPartBuilder.create().uv(86, 42).mirrored().cuboid(-3.675F, -2.61F, 1.75F, 17.35F, 0.01F, 8.0F, new Dilation(0.0F)).mirrored(false)
				.uv(1, 119).mirrored().cuboid(-7.675F, -3.6F, -2.25F, 15.35F, 4.2F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-20.0F, -16.0F, -2.5F, -0.196F, -0.6286F, 1.0675F));

		ModelPartData cube_r22 = wing_right_inner.addChild("cube_r22", ModelPartBuilder.create().uv(86, 26).mirrored().cuboid(-16.675F, -3.01F, -4.25F, 15.35F, 0.01F, 12.5F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 119).mirrored().cuboid(-16.675F, -4.0F, -8.75F, 15.35F, 4.5F, 4.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.9866F, -0.1604F, 7.9299F, 0.0F, 0.0F, 0.7418F));

		ModelPartData cube_r23 = wing_right_inner.addChild("cube_r23", ModelPartBuilder.create().uv(0, 102).mirrored().cuboid(-1.175F, -5.0F, -9.25F, 9.35F, 7.0F, 18.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-5.9866F, -0.1604F, 7.9299F, 0.0F, 0.0F, 0.3054F));

		ModelPartData wing_right_middle = wing_right_inner.addChild("wing_right_middle", ModelPartBuilder.create(), ModelTransform.pivot(-22.0F, -22.0F, -5.0F));

		ModelPartData cube_r24 = wing_right_middle.addChild("cube_r24", ModelPartBuilder.create().uv(70, 10).mirrored().cuboid(-0.175F, -1.41F, -5.25F, 24.35F, 0.01F, 10.0F, new Dilation(0.0F)).mirrored(false)
				.uv(1, 122).mirrored().cuboid(-1.675F, -2.6F, 4.75F, 24.35F, 2.2F, 3.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.0F, 6.0F, 2.5F, -2.4686F, -1.3153F, -2.8474F));

		ModelPartData wing_right_outer = wing_right_middle.addChild("wing_right_outer", ModelPartBuilder.create(), ModelTransform.of(-5.5F, -2.5F, 23.0F, 0.0436F, 0.0436F, 0.0F));

		ModelPartData cube_r25 = wing_right_outer.addChild("cube_r25", ModelPartBuilder.create().uv(0, 124).mirrored().cuboid(-12.175F, -0.7F, -4.35F, 24.35F, 1.4F, 2.2F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-3.016F, -1.0023F, 12.1216F, -1.2097F, -1.4016F, 2.1561F));

		ModelPartData cube_r26 = wing_right_outer.addChild("cube_r26", ModelPartBuilder.create().uv(69, 0).mirrored().cuboid(-12.175F, -0.005F, 2.75F, 25.35F, 0.01F, 9.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(6.9629F, 11.8333F, 10.4885F, -1.2217F, -1.4016F, 2.1561F));

		ModelPartData wing_left = torso.addChild("wing_left", ModelPartBuilder.create(), ModelTransform.pivot(6.275F, -8.45F, -12.9F));

		ModelPartData wing_left_inner = wing_left.addChild("wing_left_inner", ModelPartBuilder.create(), ModelTransform.of(0.0884F, 3.3604F, -2.0299F, 0.0F, 0.0F, 0.0436F));

		ModelPartData cube_r27 = wing_left_inner.addChild("cube_r27", ModelPartBuilder.create().uv(86, 42).cuboid(-13.675F, -2.61F, 1.75F, 17.35F, 0.01F, 8.0F, new Dilation(0.0F))
				.uv(1, 119).cuboid(-7.675F, -3.6F, -2.25F, 15.35F, 4.2F, 4.0F, new Dilation(0.0F)), ModelTransform.of(20.0F, -16.0F, -2.5F, -0.196F, 0.6286F, -1.0675F));

		ModelPartData cube_r28 = wing_left_inner.addChild("cube_r28", ModelPartBuilder.create().uv(86, 26).cuboid(1.325F, -3.01F, -4.25F, 15.35F, 0.01F, 12.5F, new Dilation(0.0F))
				.uv(0, 119).cuboid(1.325F, -4.0F, -8.75F, 15.35F, 4.5F, 4.5F, new Dilation(0.0F)), ModelTransform.of(5.9866F, -0.1604F, 7.9299F, 0.0F, 0.0F, -0.7418F));

		ModelPartData cube_r29 = wing_left_inner.addChild("cube_r29", ModelPartBuilder.create().uv(0, 102).cuboid(-8.175F, -5.0F, -9.25F, 9.35F, 7.0F, 18.5F, new Dilation(0.0F)), ModelTransform.of(5.9866F, -0.1604F, 7.9299F, 0.0F, 0.0F, -0.3054F));

		ModelPartData wing_left_middle = wing_left_inner.addChild("wing_left_middle", ModelPartBuilder.create(), ModelTransform.pivot(22.0F, -22.0F, -5.0F));

		ModelPartData cube_r30 = wing_left_middle.addChild("cube_r30", ModelPartBuilder.create().uv(70, 10).cuboid(-24.175F, -1.41F, -5.25F, 24.35F, 0.01F, 10.0F, new Dilation(0.0F))
				.uv(0, 122).cuboid(-22.675F, -2.6F, 4.75F, 24.35F, 2.2F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 6.0F, 2.5F, -2.4686F, 1.3153F, 2.8474F));

		ModelPartData wing_left_outer = wing_left_middle.addChild("wing_left_outer", ModelPartBuilder.create(), ModelTransform.of(5.5F, -2.5F, 23.0F, 0.0436F, -0.0436F, 0.0F));

		ModelPartData cube_r31 = wing_left_outer.addChild("cube_r31", ModelPartBuilder.create().uv(0, 124).cuboid(-12.175F, -0.7F, -4.35F, 24.35F, 1.4F, 2.2F, new Dilation(0.0F)), ModelTransform.of(3.016F, -1.0023F, 12.1216F, -1.2097F, 1.4016F, -2.1561F));

		ModelPartData cube_r32 = wing_left_outer.addChild("cube_r32", ModelPartBuilder.create().uv(69, 0).cuboid(-13.175F, -0.005F, 2.75F, 25.35F, 0.01F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-6.9629F, 11.8333F, 10.4885F, -1.2217F, 1.4016F, -2.1561F));

		ModelPartData leg_right = body.addChild("leg_right", ModelPartBuilder.create(), ModelTransform.pivot(-9.3F, 13.0F, 45.5F));

		ModelPartData leg_right_upper = leg_right.addChild("leg_right_upper", ModelPartBuilder.create(), ModelTransform.pivot(-3.675F, 0.2F, -0.6F));

		ModelPartData cube_r33 = leg_right_upper.addChild("cube_r33", ModelPartBuilder.create().uv(2, 107).cuboid(-1.425F, -6.5F, -3.75F, 8.85F, 13.5F, 7.5F, new Dilation(0.0F)), ModelTransform.of(0.35F, 0.0F, 0.0F, -0.3521F, 0.1776F, 0.4483F));

		ModelPartData leg_right_lower = leg_right_upper.addChild("leg_right_lower", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, 4.5F, -4.0F));

		ModelPartData cube_r34 = leg_right_lower.addChild("cube_r34", ModelPartBuilder.create().uv(9, 112).mirrored().cuboid(-2.925F, 5.0F, -6.25F, 8.35F, 7.5F, 8.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.35F, -4.5F, 4.0F, 0.0F, 0.0F, 0.1309F));

		ModelPartData leg_right_foot = leg_right_lower.addChild("leg_right_foot", ModelPartBuilder.create().uv(102, 58).cuboid(-1.575F, 2.1F, -13.75F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F))
				.uv(1, 110).cuboid(-5.575F, 0.0F, -9.25F, 10.85F, 4.5F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 7.5F, 4.0F));

		ModelPartData cube_r35 = leg_right_foot.addChild("cube_r35", ModelPartBuilder.create().uv(102, 58).cuboid(-0.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)), ModelTransform.of(3.85F, 3.35F, -11.5F, 0.0F, -0.3491F, 0.0F));

		ModelPartData cube_r36 = leg_right_foot.addChild("cube_r36", ModelPartBuilder.create().uv(102, 58).cuboid(-2.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)), ModelTransform.of(-4.15F, 3.35F, -11.5F, 0.0F, 0.3491F, 0.0F));

		ModelPartData leg_left = body.addChild("leg_left", ModelPartBuilder.create(), ModelTransform.pivot(10.95F, 13.0F, 45.5F));

		ModelPartData leg_left_upper = leg_left.addChild("leg_left_upper", ModelPartBuilder.create(), ModelTransform.pivot(3.675F, 0.2F, -0.6F));

		ModelPartData cube_r37 = leg_left_upper.addChild("cube_r37", ModelPartBuilder.create().uv(2, 107).mirrored().cuboid(-7.425F, -6.5F, -3.75F, 8.85F, 13.5F, 7.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.3521F, -0.1776F, -0.4483F));

		ModelPartData leg_left_lower = leg_left_upper.addChild("leg_left_lower", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, 4.5F, -4.0F));

		ModelPartData cube_r38 = leg_left_lower.addChild("cube_r38", ModelPartBuilder.create().uv(10, 112).cuboid(-5.425F, 5.0F, -6.25F, 8.35F, 7.5F, 8.5F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -4.5F, 4.0F, 0.0F, 0.0F, -0.1309F));

		ModelPartData leg_left_foot = leg_left_lower.addChild("leg_left_foot", ModelPartBuilder.create().uv(102, 58).mirrored().cuboid(-0.925F, 2.1F, -13.75F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 110).mirrored().cuboid(-4.925F, 0.0F, -9.25F, 10.85F, 4.5F, 13.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-3.0F, 7.5F, 4.0F));

		ModelPartData cube_r39 = leg_left_foot.addChild("cube_r39", ModelPartBuilder.create().uv(102, 58).mirrored().cuboid(-2.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-3.5F, 3.35F, -11.5F, 0.0F, 0.3491F, 0.0F));

		ModelPartData cube_r40 = leg_left_foot.addChild("cube_r40", ModelPartBuilder.create().uv(102, 58).mirrored().cuboid(-0.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.5F, 3.35F, -11.5F, 0.0F, -0.3491F, 0.0F));

		ModelPartData arm_left = body.addChild("arm_left", ModelPartBuilder.create(), ModelTransform.pivot(11.0F, 12.5F, 18.9F));

		ModelPartData arm_left_upper = arm_left.addChild("arm_left_upper", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r41 = arm_left_upper.addChild("cube_r41", ModelPartBuilder.create().uv(2, 107).mirrored().cuboid(-7.425F, -7.0F, -3.75F, 8.85F, 13.0F, 7.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(3.675F, 1.2F, 0.4F, 0.4326F, 0.2149F, -0.4326F));

		ModelPartData arm_left_lower = arm_left_upper.addChild("arm_left_lower", ModelPartBuilder.create(), ModelTransform.pivot(4.175F, 5.2F, -1.1F));

		ModelPartData cube_r42 = arm_left_lower.addChild("cube_r42", ModelPartBuilder.create().uv(8, 112).cuboid(-5.425F, 5.0F, -3.25F, 7.85F, 7.5F, 8.5F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, 3.0F, -0.0076F, 0.043F, -0.1747F));

		ModelPartData cube_r43 = arm_left_lower.addChild("cube_r43", ModelPartBuilder.create().uv(2, 111).mirrored().cuboid(-5.425F, 5.0F, -3.75F, 8.35F, 9.5F, 7.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-0.5F, -4.5F, 5.0F, -0.3463F, -0.0447F, -0.1231F));

		ModelPartData arm_left_foot = arm_left_lower.addChild("arm_left_foot", ModelPartBuilder.create().uv(102, 58).mirrored().cuboid(-0.925F, 0.1F, -15.75F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)).mirrored(false)
				.uv(0, 110).mirrored().cuboid(-4.925F, -2.0F, -11.25F, 10.85F, 4.5F, 13.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(-0.5F, 9.5F, 2.5F));

		ModelPartData cube_r44 = arm_left_foot.addChild("cube_r44", ModelPartBuilder.create().uv(102, 58).mirrored().cuboid(-2.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-3.5F, 1.35F, -13.5F, 0.0F, 0.3491F, 0.0F));

		ModelPartData cube_r45 = arm_left_foot.addChild("cube_r45", ModelPartBuilder.create().uv(102, 58).mirrored().cuboid(-0.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(4.5F, 1.35F, -13.5F, 0.0F, -0.3491F, 0.0F));

		ModelPartData arm_right = body.addChild("arm_right", ModelPartBuilder.create(), ModelTransform.pivot(-9.45F, 12.5F, 19.4F));

		ModelPartData arm_right_upper = arm_right.addChild("arm_right_upper", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r46 = arm_right_upper.addChild("cube_r46", ModelPartBuilder.create().uv(6, 107).cuboid(-1.425F, -7.0F, -3.75F, 8.85F, 13.0F, 7.5F, new Dilation(0.0F)), ModelTransform.of(-3.325F, 1.2F, -0.1F, 0.4326F, -0.2149F, 0.4326F));

		ModelPartData arm_right_lower = arm_right_upper.addChild("arm_right_lower", ModelPartBuilder.create(), ModelTransform.pivot(-4.175F, 5.2F, -1.6F));

		ModelPartData cube_r47 = arm_right_lower.addChild("cube_r47", ModelPartBuilder.create().uv(6, 112).cuboid(-2.425F, 5.0F, -3.25F, 7.85F, 7.5F, 8.5F, new Dilation(0.0F)), ModelTransform.of(0.35F, -8.0F, 3.0F, -0.0076F, -0.043F, 0.1747F));

		ModelPartData cube_r48 = arm_right_lower.addChild("cube_r48", ModelPartBuilder.create().uv(7, 111).cuboid(-2.925F, 5.0F, -3.75F, 8.35F, 9.5F, 7.5F, new Dilation(0.0F)), ModelTransform.of(0.85F, -4.5F, 5.0F, -0.3463F, 0.0447F, 0.1231F));

		ModelPartData arm_right_foot = arm_right_lower.addChild("arm_right_foot", ModelPartBuilder.create().uv(102, 59).cuboid(-1.575F, 0.1F, -15.75F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F))
				.uv(5, 110).cuboid(-5.575F, -2.0F, -11.25F, 10.85F, 4.5F, 13.5F, new Dilation(0.0F)), ModelTransform.pivot(0.5F, 9.5F, 2.5F));

		ModelPartData cube_r49 = arm_right_foot.addChild("cube_r49", ModelPartBuilder.create().uv(102, 58).cuboid(-0.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)), ModelTransform.of(3.85F, 1.35F, -13.5F, 0.0F, -0.3491F, 0.0F));

		ModelPartData cube_r50 = arm_right_foot.addChild("cube_r50", ModelPartBuilder.create().uv(102, 58).cuboid(-2.425F, -1.25F, -2.25F, 2.85F, 2.5F, 4.5F, new Dilation(0.0F)), ModelTransform.of(-4.15F, 1.35F, -13.5F, 0.0F, 0.3491F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}

	@Override
	public void setAngles(DragonWhelpEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.DRAGONWHELP_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);

		this.updateAnimation(entity.idleAnimationState, ModAnimations.DRAGONWHELP_IDLE, ageInTicks, 1f);
		//this.updateAnimation(entity.attackAnimationState, ModAnimations.DRAGONWHELP_WALK, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
		headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		dragonwhelp.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return dragonwhelp;
	}
}