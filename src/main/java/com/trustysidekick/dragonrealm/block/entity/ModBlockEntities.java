package com.trustysidekick.dragonrealm.block.entity;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<DragonForgeBlockEntity> DRAGON_FORGE_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DragonRealm.MOD_ID, "dragon_forge_be"), FabricBlockEntityTypeBuilder.create(DragonForgeBlockEntity::new, ModBlocks.DRAGON_FORGE_BLOCK).build());
    public static final BlockEntityType<QuenchTankBlockEntity> QUENCH_TANK_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DragonRealm.MOD_ID, "quench_tank_be"), FabricBlockEntityTypeBuilder.create(QuenchTankBlockEntity::new, ModBlocks.QUENCH_TANK_BLOCK).build());
    public static final BlockEntityType<SmithingAnvilBlockEntity> SMITHING_ANVIL_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DragonRealm.MOD_ID, "smithing_anvil_be"), FabricBlockEntityTypeBuilder.create(SmithingAnvilBlockEntity::new, ModBlocks.SMITHING_ANVIL_BLOCK).build());
    public static final BlockEntityType<ImbuementAltarBlockEntity> IMBUEMENT_ALTAR_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DragonRealm.MOD_ID, "imbuement_altar_be"), FabricBlockEntityTypeBuilder.create(ImbuementAltarBlockEntity::new, ModBlocks.IMBUEMENT_ALTAR_BLOCK).build());
    public static final BlockEntityType<ImbuementPedestalBlockEntity> IMBUEMENT_PEDESTAL_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(DragonRealm.MOD_ID, "imbuement_pedestal_be"), FabricBlockEntityTypeBuilder.create(ImbuementPedestalBlockEntity::new, ModBlocks.IMBUEMENT_PEDESTAL_BLOCK).build());

    public static void registerBlockEntities() {
        DragonRealm.LOGGER.info("Registering Block Entities for " + DragonRealm.MOD_ID);

    }
}
