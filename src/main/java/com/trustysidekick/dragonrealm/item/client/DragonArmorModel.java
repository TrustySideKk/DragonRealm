package com.trustysidekick.dragonrealm.item.client;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.item.custom.DragonArmorItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DragonArmorModel extends GeoModel<DragonArmorItem> {
    @Override
    public Identifier getModelResource(DragonArmorItem animatable) {
        return new Identifier(DragonRealm.MOD_ID,"geo/test_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(DragonArmorItem animatable) {
        return new Identifier(DragonRealm.MOD_ID,"textures/models/armor/test_armor.png");
    }

    @Override
    public Identifier getAnimationResource(DragonArmorItem animatable) {
        return new Identifier(DragonRealm.MOD_ID, "animations/test_armor.animation.json");
    }
}
