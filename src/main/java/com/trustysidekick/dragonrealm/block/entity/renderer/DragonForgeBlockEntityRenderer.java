package com.trustysidekick.dragonrealm.block.entity.renderer;

import com.trustysidekick.dragonrealm.block.entity.DragonForgeBlockEntity;
import com.trustysidekick.dragonrealm.block.entity.ImplementedInventory;
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
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class DragonForgeBlockEntityRenderer implements BlockEntityRenderer<DragonForgeBlockEntity> {
    public DragonForgeBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }

    @Override
    public void render(DragonForgeBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        ItemStack slot0 = entity.getRenderStack(0);
        ItemStack slot1 = entity.getRenderStack(1);



        if (!slot0.isEmpty()) {
            matrices.push();
            // Render the iron ingot at the center
            matrices.translate(0.5f, 0.8f, 0.5f);
            matrices.scale(0.35f, 0.35f, 0.35f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
            itemRenderer.renderItem(slot0, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!slot1.isEmpty()) {
            if (slot1.getCount() == 1) {
                matrices.push();
                matrices.translate(0.5f, 0.75f, 0.7f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
            }
            if (slot1.getCount() == 2) {
                matrices.push();
                matrices.translate(0.5f, 0.75f, 0.7f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.7f, 0.75f, 0.55f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
            }
            if (slot1.getCount() == 3) {
                matrices.push();
                matrices.translate(0.5f, 0.75f, 0.7f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.7f, 0.75f, 0.55f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.6f, 0.75f, 0.3f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
            }
            if (slot1.getCount() == 4) {
                matrices.push();
                matrices.translate(0.5f, 0.75f, 0.7f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.7f, 0.75f, 0.55f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.6f, 0.75f, 0.3f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.4f, 0.75f, 0.3f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
            }
            if (slot1.getCount() == 5) {
                matrices.push();
                matrices.translate(0.5f, 0.75f, 0.7f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.7f, 0.75f, 0.55f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.6f, 0.75f, 0.3f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.4f, 0.75f, 0.3f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
                matrices.push();
                matrices.translate(0.3f, 0.75f, 0.55f);
                matrices.scale(0.35f, 0.35f, 0.35f);
                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
                itemRenderer.renderItem(slot1, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
                matrices.pop();
            }
        }
    }


    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }


}
