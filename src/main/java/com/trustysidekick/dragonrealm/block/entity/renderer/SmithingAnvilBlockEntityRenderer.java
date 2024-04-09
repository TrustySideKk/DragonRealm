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
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class SmithingAnvilBlockEntityRenderer implements BlockEntityRenderer<SmithingAnvilBlockEntity> {
    public SmithingAnvilBlockEntityRenderer(BlockEntityRendererFactory.Context context) {

    }


    @Override
    public void render(SmithingAnvilBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();

        for (int i = 0; i < entity.size(); i++) {
            ItemStack stack = entity.getRenderStack(i);
            matrices.push();
            if (i == 0) {
                matrices.translate(0.275f, 0.8f, 0.28f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 1) {
                matrices.translate(0.49f, 0.8f, 0.28f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 2) {
                matrices.translate(0.71f, 0.8f, 0.28f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 3) {
                matrices.translate(0.275f, 0.8f, 0.5f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 4) {
                matrices.translate(0.49f, 0.8f, 0.5f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 5) {
                matrices.translate(0.71f, 0.8f, 0.5f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 6) {
                matrices.translate(0.275f, 0.8f, 0.72f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 7) {
                matrices.translate(0.49f, 0.8f, 0.72f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 8) {
                matrices.translate(0.71f, 0.8f, 0.72f);
                matrices.scale(0.18f, 0.18f, 0.18f);
            }
            if (i == 9) {
                matrices.translate(0.5f, 0.8f, 0.5f);
                matrices.scale(0.5f, 0.5f, 0.5f);
            }

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90));
            itemRenderer.renderItem(stack, ModelTransformationMode.FIXED, getLightLevel(entity.getWorld(), entity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), 1);
            matrices.pop();
        }
    }




    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }



}