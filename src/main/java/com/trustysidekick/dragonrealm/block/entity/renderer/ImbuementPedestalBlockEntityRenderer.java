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

    public ImbuementPedestalBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(ImbuementPedestalBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack slot0 = entity.getRenderStack(0);

        if (!slot0.isEmpty()) {
            // Update vertical offset and rotation angle based on time
            entity.renderYCurOffset += entity.renderYMaxSpeed;
            entity.renderRotationAngle += entity.renderRotationSpeed;
            if (entity.renderYCurOffset > entity.renderYMaxOffset || entity.renderYCurOffset < -entity.renderYMaxOffset) {
                // Reverse direction when reaching maximum offset
                entity.renderYMaxSpeed *= -1;
            }

            matrices.push();
            // Apply vertical oscillation effect
            matrices.translate(0.5f, 1.0f + entity.renderYCurOffset, 0.5f);
            // Apply rotation
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.renderRotationAngle));
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
