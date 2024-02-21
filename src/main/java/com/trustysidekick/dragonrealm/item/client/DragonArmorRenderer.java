package com.trustysidekick.dragonrealm.item.client;

import com.trustysidekick.dragonrealm.item.custom.DragonArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class DragonArmorRenderer extends GeoArmorRenderer<DragonArmorItem> {
    public DragonArmorRenderer() {
        super(new DragonArmorModel());
    }
}
