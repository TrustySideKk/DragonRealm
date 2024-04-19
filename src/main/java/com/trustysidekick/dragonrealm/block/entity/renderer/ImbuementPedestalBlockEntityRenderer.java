package com.trustysidekick.dragonrealm.block.entity.renderer;

import com.trustysidekick.dragonrealm.block.entity.ImbuementAltarBlockEntity;
import com.trustysidekick.dragonrealm.block.entity.ImbuementPedestalBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
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


        if (!slot0.isEmpty() && !entity.isImbuing) {
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

        if (entity.isImbuing) {
            // Update vertical offset and rotation angle based on time
            entity.renderYCurOffset += entity.renderYMaxSpeed;
            entity.renderRotationAngle += entity.renderRotationSpeed;
            if (entity.renderYCurOffset > entity.renderYMaxOffset || entity.renderYCurOffset < -entity.renderYMaxOffset) {
                // Reverse direction when reaching maximum offset
                entity.renderYMaxSpeed *= -1;
            }
            // Find the altar block within range
            BlockPos pedestalBoxPos1 = entity.getPos().add(-7, -7, -7);
            BlockPos pedestalBoxPos2 = entity.getPos().add(7, 7, 7);
            Iterable<BlockPos> AltarInRange = BlockPos.iterate(pedestalBoxPos1, pedestalBoxPos2);

            for (BlockPos blockPos : AltarInRange) {
                BlockEntity blockEntity = entity.getWorld().getBlockEntity(blockPos);
                if (blockEntity instanceof ImbuementAltarBlockEntity) {
                    if (!entity.renderImbuingStop) {
                        entity.renderTotX = blockEntity.getPos().getX() - entity.getPos().getX();
                        entity.renderTotY = blockEntity.getPos().getY() - entity.getPos().getY();
                        entity.renderTotZ = blockEntity.getPos().getZ() - entity.getPos().getZ();
                    }

                    if (Math.abs(entity.renderDistX) < Math.abs(entity.renderTotX)) {
                        entity.renderDistX = entity.renderDistX + entity.renderTotX/entity.renderImbuingSpeed;
                    } else {
                        entity.renderDistX = entity.renderTotX;
                    }

                    if (Math.abs(entity.renderDistY) < Math.abs(entity.renderTotY)) {
                            entity.renderDistY = entity.renderDistY + entity.renderTotY/entity.renderImbuingSpeed;
                    } else {
                        entity.renderDistY = entity.renderTotY;
                    }

                    if (Math.abs(entity.renderDistZ) < Math.abs(entity.renderTotZ)) {
                        entity.renderDistZ = entity.renderDistZ + entity.renderTotZ/entity.renderImbuingSpeed;
                    } else {
                        entity.renderDistZ = entity.renderTotZ;
                    }

                    if (entity.renderDistX == entity.renderTotX && entity.renderDistY == entity.renderTotY && entity.renderDistZ == entity.renderTotZ) {
                        entity.renderImbuingStop = true;
                    }

                    matrices.push();
                    matrices.translate(0.5 + entity.renderDistX, 1 + entity.renderDistY + entity.renderYCurOffset, 0.5 + entity.renderDistZ);
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(entity.renderRotationAngle));
                    matrices.scale(0.35f, 0.35f, 0.35f);
                    itemRenderer.renderItem(slot0, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                    matrices.pop();
                }
            }
        } else {
            entity.renderImbuingStop = false;
            entity.renderDistX = 0;
            entity.renderDistY = 0;
            entity.renderDistZ = 0;
        }
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
