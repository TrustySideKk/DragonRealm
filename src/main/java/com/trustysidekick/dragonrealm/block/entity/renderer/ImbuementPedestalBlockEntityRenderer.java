package com.trustysidekick.dragonrealm.block.entity.renderer;

import com.trustysidekick.dragonrealm.block.entity.ImbuementPedestalBlockEntity;
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
public class ImbuementPedestalBlockEntityRenderer implements BlockEntityRenderer<ImbuementPedestalBlockEntity> {
    private static final float Y_OFFSET = 0.1f; // Maximum vertical offset
    private static float Y_SPEED = 0.002f; // Vertical movement speed
    private float yOffset = 0.0f; // Current vertical offset
    private static final float ROTATION_SPEED = 1.0f; // Rotation speed
    private float rotationAngle = 0.0f; // Current rotation angle

    public ImbuementPedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(ImbuementPedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack slot0 = entity.getRenderStack(0);

        if (!slot0.isEmpty()) {
            // Update vertical offset and rotation angle based on time
            yOffset += Y_SPEED;
            rotationAngle += ROTATION_SPEED;
            if (yOffset > Y_OFFSET || yOffset < -Y_OFFSET) {
                // Reverse direction when reaching maximum offset
                Y_SPEED *= -1;
            }

            matrices.push();
            // Apply vertical oscillation effect
            matrices.translate(0.5f, 1.0f + yOffset, 0.5f);
            // Apply rotation
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotationAngle));
            matrices.scale(0.35f, 0.35f, 0.35f);
                itemRenderer.renderItem(slot0, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }
    }


    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }


}
