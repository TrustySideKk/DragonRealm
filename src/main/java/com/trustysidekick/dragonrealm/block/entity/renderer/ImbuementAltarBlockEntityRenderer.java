package com.trustysidekick.dragonrealm.block.entity.renderer;

import com.trustysidekick.dragonrealm.block.entity.ImbuementAltarBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class ImbuementAltarBlockEntityRenderer implements BlockEntityRenderer<ImbuementAltarBlockEntity> {
    //private static final float ROTATION_SPEED = 1.0f; // Rotation speed
    //private float rotationAngle = 0.0f; // Current rotation angle
    private float imbuingSpeed;

    public ImbuementAltarBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        imbuingSpeed = 0.0f;
    }

    @Override
    public void render(ImbuementAltarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack slot0 = entity.getRenderStack(0);
        ItemStack slot1 = entity.getRenderStack(1);


        //matrices.push();
        entity.renderRotationAngle += entity.renderRotationSpeed;
        if (!slot0.isEmpty() && !entity.isImbuing) {
            // Update rotation angle based on time
            //entity.renderSlot0RotationAngle += entity.renderRotationSpeed;

            matrices.push();
            matrices.translate(0.5f, 0.7f, 0.5f);
            // Apply rotation
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.renderRotationAngle));
            matrices.scale(0.35f, 0.35f, 0.35f);
            itemRenderer.renderItem(slot0, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!slot1.isEmpty() && !entity.isImbuing) {
            // Update rotation angle based on time
            //entity.renderSlot1RotationAngle += entity.renderRotationSpeed;

            matrices.push();
            matrices.translate(0.5f, 1.7f, 0.5f);
            // Apply rotation
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.renderRotationAngle));
            matrices.scale(0.35f, 0.35f, 0.35f);
            itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (entity.isImbuing) {
            entity.renderImbuingSpeed = entity.renderImbuingSpeed + 0.01f;
            //entity.renderRotationAngle += 0.1f;

            matrices.push();
            matrices.translate(0.5f, 0.7f, 0.5f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.renderRotationAngle * entity.renderImbuingSpeed));
            matrices.scale(0.35f, 0.35f, 0.35f);
            itemRenderer.renderItem(slot0, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();

            matrices.push();
            matrices.translate(0.5f, 1.7f, 0.5f);
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.renderRotationAngle * entity.renderImbuingSpeed));
            matrices.scale(0.35f, 0.35f, 0.35f);
            itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();

        } else {

        }

        //matrices.pop();
    }


    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }


}
