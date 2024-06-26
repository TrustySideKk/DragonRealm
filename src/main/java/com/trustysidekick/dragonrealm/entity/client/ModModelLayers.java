package com.trustysidekick.dragonrealm.entity.client;

import com.trustysidekick.dragonrealm.DragonRealm;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PORCUPINE =
            new EntityModelLayer(new Identifier(DragonRealm.MOD_ID, "porcupine"), "main");

    public static final EntityModelLayer DRAGONWHELP =
            new EntityModelLayer(new Identifier(DragonRealm.MOD_ID, "dragonwhelp"), "root");

    public static final EntityModelLayer HIGHLAND_DRAGON =
            new EntityModelLayer(new Identifier(DragonRealm.MOD_ID, "highlanddragon"), "highlandDragon");
}
