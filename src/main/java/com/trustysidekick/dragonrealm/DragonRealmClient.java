package com.trustysidekick.dragonrealm;

import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.renderer.DragonForgeBlockEntityRenderer;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class DragonRealmClient implements ClientModInitializer {

    @Override
    public void onInitializeClient(){
        BlockEntityRendererFactories.register(ModBlockEntities.DRAGON_FORGE_BLOCK_ENTITY, DragonForgeBlockEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.PORCUPINE, PorcupineRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE, PorcupineModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.DRAGONWHELP, DragonWhelpRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.DRAGONWHELP, DragonWhelpModel::getTexturedModelData);

    }
}
