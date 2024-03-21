package com.trustysidekick.dragonrealm;

import com.trustysidekick.dragonrealm.block.ModBlocks;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import com.trustysidekick.dragonrealm.entity.custom.PorcupineEntity;
import com.trustysidekick.dragonrealm.fluid.DragonBlood;
import com.trustysidekick.dragonrealm.item.ModItemGroups;
import com.trustysidekick.dragonrealm.item.ModItems;
import com.trustysidekick.dragonrealm.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonRealm implements ModInitializer {

	public static final String MOD_ID = "dragonrealm";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
		ModEntities.registerModEntities();

		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DRAGONWHELP, DragonWhelpEntity.createDragonWhelpAttributes());
	}

}
