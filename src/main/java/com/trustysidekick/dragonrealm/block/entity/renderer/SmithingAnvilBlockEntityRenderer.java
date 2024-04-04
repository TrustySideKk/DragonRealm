package com.trustysidekick.dragonrealm.block.entity.renderer;

import com.trustysidekick.dragonrealm.block.entity.SmithingAnvilBlockEntity;
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
public class SmithingAnvilBlockEntityRenderer implements BlockEntityRenderer<SmithingAnvilBlockEntity> {
    public SmithingAnvilBlockEntityRenderer(BlockEntityRendererFactory.Context context) {
        //super();
    }
    private int tick = 0;


    @Override
    public void render(SmithingAnvilBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        if (!entity.getStack(0).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.275f, 0.8f, 0.28f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(0), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(1).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.49f, 0.8f, 0.28f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(1), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(2).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.71f, 0.8f, 0.28f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(2), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(3).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.275f, 0.8f, 0.5f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(3), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(4).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.49f, 0.8f, 0.5f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            //matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(4), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(5).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.71f, 0.8f, 0.5f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(5), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(6).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.275f, 0.8f, 0.72f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(6), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(7).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.49f, 0.8f, 0.72f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(7), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }

        if (!entity.getStack(8).isEmpty()) {
            matrices.push();
            //matrices.translate(0.5f, 0.8f, 0.5f); // Adjust the translation as needed for render height
            matrices.translate(0.71f, 0.8f, 0.72f);
            matrices.scale(0.18f, 0.18f, 0.18f);
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270));
            itemRenderer.renderItem(entity.getStack(8), ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }


    }




    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }



}