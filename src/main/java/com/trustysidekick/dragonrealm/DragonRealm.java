package com.trustysidekick.dragonrealm;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonRealm implements ModInitializer {

	public static final String MOD_ID = "dragonrealm";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Item CUSTOM_ITEM = new Item(new FabricItemSettings());

	@Override
	public void onInitialize() {

		Registry.register(Registries.ITEM, new Identifier("tutorial", "custom_item"), CUSTOM_ITEM);

		LOGGER.info("Hello Fabric world!");
	}
}