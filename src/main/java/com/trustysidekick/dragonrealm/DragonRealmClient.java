package com.trustysidekick.dragonrealm;

import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.block.entity.renderer.DragonForgeBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class DragonRealmClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        BlockEntityRendererFactories.register(ModBlockEntities.DRAGON_FORGE_BLOCK_ENTITY, DragonForgeBlockEntityRenderer::new);

    }
}
