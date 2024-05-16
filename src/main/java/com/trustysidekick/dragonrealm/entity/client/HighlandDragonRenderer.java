package com.trustysidekick.dragonrealm.entity.client;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class HighlandDragonRenderer extends MobEntityRenderer<HighlandDragonEntity, HighlandDragonModel<HighlandDragonEntity>> {
    private static final Identifier TEXTURE = new Identifier(DragonRealm.MOD_ID, "textures/entity/highland_dragon.png");

    public HighlandDragonRenderer(EntityRendererFactory.Context context) {
        super(context, new HighlandDragonModel<>(context.getPart(ModModelLayers.HIGHLAND_DRAGON)), 1.5f);
    }

    @Override
    public Identifier getTexture(HighlandDragonEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(HighlandDragonEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);


    }


}
