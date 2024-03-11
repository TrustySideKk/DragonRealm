package com.trustysidekick.dragonrealm;

import com.trustysidekick.dragonrealm.block.custom.DragonForgeModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.util.ModelIdentifier;

@Environment(EnvType.CLIENT)
public class DragonRealmModelLoadingPlugin implements ModelLoadingPlugin {
    public static final ModelIdentifier DRAGON_FORGE_BLOCK_MODEL = new ModelIdentifier("dragonrealm", "dragon_forge_block", "");



    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        // We want to add our model when the models are loaded
        pluginContext.modifyModelOnLoad().register((original, context) -> {
            // This is called for every model that is loaded, so make sure we only target ours
            if(context.id().equals(DRAGON_FORGE_BLOCK_MODEL)) {
                return new DragonForgeModel();
            } else {
                // If we don't modify the model we just return the original as-is
                return original;
            }
        });
    }
}
