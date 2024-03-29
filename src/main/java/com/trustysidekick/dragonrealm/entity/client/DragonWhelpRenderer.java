package com.trustysidekick.dragonrealm.entity.client;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class DragonWhelpRenderer extends MobEntityRenderer<DragonWhelpEntity, DragonWhelpModel<DragonWhelpEntity>> {
    private static final Identifier TEXTURE = new Identifier(DragonRealm.MOD_ID, "textures/entity/dragon_whelp.png");

    public DragonWhelpRenderer(EntityRendererFactory.Context context) {
        super(context, new DragonWhelpModel<>(context.getPart(ModModelLayers.DRAGONWHELP)), 0.1f);
    }

    @Override
    public Identifier getTexture(DragonWhelpEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(DragonWhelpEntity mobEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
