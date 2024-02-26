package com.trustysidekick.dragonrealm.block.entity;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<DragonForgeBlockEntity> DRAGON_FORGE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DragonRealm.MOD_ID, "dragon_forge_be"), FabricBlockEntityTypeBuilder.create(DragonForgeBlockEntity::new, ModBlocks.DRAGON_FORGE).build());
    public static final BlockEntityType<TestBlockEntity> TEST_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DragonRealm.MOD_ID, "test_be"), FabricBlockEntityTypeBuilder.create(TestBlockEntity::new, ModBlocks.TEST_BLOCK).build());

    public static void registerBlockEntities() {
        DragonRealm.LOGGER.info("Registering Block Entities for " + DragonRealm.MOD_ID);

    }
}
