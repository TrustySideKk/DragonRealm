package com.trustysidekick.dragonrealm.item;

import com.trustysidekick.dragonrealm.DragonIngotToolMaterial;
import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.NullToolMaterial;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.item.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // ITEMS
    public static final Item DRAGON_HORN_TORCH = registerItem("dragon_horn_torch", new Item(new FabricItemSettings()));
    public static final Item DRAGON_BACKPACK = registerItem("dragon_backpack", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_INGOT = registerItem("dragon_ingot", new DragonIngotItem(new FabricItemSettings()));
    public static final Item SEARING_IRON_INGOT = registerItem("searing_iron_ingot", new SearingIronIngot(new FabricItemSettings()));
    public static final Item DRAGON_HEAD = registerItem("dragon_head", new Item(new FabricItemSettings()));
    public static final Item DRAGON_TALON = registerItem("dragon_talon", new Item(new FabricItemSettings()));
    public static final Item DRAGON_MEAT = registerItem("dragon_meat", new Item(new FabricItemSettings()));
    public static final Item DRAGON_HIDE = registerItem("dragon_hide", new Item(new FabricItemSettings()));
    public static final Item DRAGON_SCALE = registerItem("dragon_scale", new Item(new FabricItemSettings()));
    public static final Item DRAGON_BONE = registerItem("dragon_bone", new Item(new FabricItemSettings()));
    public static final Item DRAGON_HORN = registerItem("dragon_horn", new Item(new FabricItemSettings()));
    public static final Item DRAGON_TOOTH = registerItem("dragon_tooth", new Item(new FabricItemSettings()));
    public static final Item DRAGON_EYE = registerItem("dragon_eye", new Item(new FabricItemSettings()));
    public static final Item DRAGON_INGOT_BLADE = registerItem("dragon_ingot_blade", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_INGOT_SPEARHEAD = registerItem("dragon_ingot_spearhead", new Item(new FabricItemSettings()));
    public static final Item DRAGON_BONE_SHAFT = registerItem("dragon_bone_shaft", new Item(new FabricItemSettings()));
    public static final Item DRAGON_BONE_HILT = registerItem("dragon_bone_hilt", new Item(new FabricItemSettings()));
    public static final Item DRAGON_STEAK = registerItem("dragon_steak", new Item(new FabricItemSettings()));
    public static final Item NULL_TOOL_MATERIAL = registerItem("null_tool_material", new Item(new FabricItemSettings()));
    public static final Item DRAGON_BLOOD = registerItem("dragon_blood", new DragonBloodItem(new FabricItemSettings().maxCount(1)));
    public static final Item ORE_CHUNK_GOLD = registerItem("ore_chunk_gold", new Item(new FabricItemSettings()));
    public static final Item ORE_CHUNK_IRON = registerItem("ore_chunk_iron", new Item(new FabricItemSettings()));
    public static final Item ORE_CHUNK_COPPER = registerItem("ore_chunk_copper", new Item(new FabricItemSettings()));


    // TOOLS
    public static final Item DRAGON_HORN_PICKAXE = registerItem("dragon_horn_pickaxe", new DragonHornPickaxeItem(NullToolMaterial.INSTANCE, 1, -2.8f, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_JAWBONE_AXE = registerItem("dragon_jawbone_axe", new DragonJawboneAxeItem(NullToolMaterial.INSTANCE, 5, -3.0f, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_SCALE_SHOVEL = registerItem("dragon_scale_shovel", new DragonScaleShovelItem(NullToolMaterial.INSTANCE, 1.5f, -3.0f, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_PICKAXE = registerItem("dragon_pickaxe", new DragonPickaxeItem(DragonIngotToolMaterial.INSTANCE, 1, -2.8f, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_SHOVEL = registerItem("dragon_shovel", new DragonShovelItem(DragonIngotToolMaterial.INSTANCE, 1.5f, -3.0f, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_AXE = registerItem("dragon_axe", new DragonAxeItem(DragonIngotToolMaterial.INSTANCE, 5, -3.0f, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_HOE = registerItem("dragon_hoe", new DragonHoeItem(DragonIngotToolMaterial.INSTANCE, -4, 0.0f, new FabricItemSettings().maxCount(1)));
    public static final Item SMITHING_HAMMER = registerItem("smithing_hammer", new SmithingHammerItem(ModToolMaterial.DRAGON_INGOT, new FabricItemSettings()));


    // WEAPONS
    public static final Item DRAGON_CLUB = registerItem("dragon_club", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_SPEAR = registerItem("dragon_spear", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_BOW = registerItem("dragon_bow", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_CROSSBOW = registerItem("dragon_crossbow", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_SWORD = registerItem("dragon_sword", new DragonSwordItem(ModToolMaterial.DRAGON_INGOT, 1, 10, new FabricItemSettings()));
    //public static final Item DRAGON_SWORD = registerItem("dragon_sword", new DragonSwordItem(ModToolMaterial.DRAGON_INGOT, 10, 10, new FabricItemSettings()));
    public static final Item DRAGON_SHIELD = registerItem("dragon_shield", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_BATTLEAXE = registerItem("dragon_battleaxe", new Item(new FabricItemSettings().maxCount(1)));

    // ARMORS
    public static final Item DRAGON_HIDE_TUNIC = registerItem("dragon_hide_tunic", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_HIDE_CAP = registerItem("dragon_hide_cap", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_HIDE_PANTS = registerItem("dragon_hide_pants", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_HIDE_BOOTS = registerItem("dragon_hide_boots", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_HIDE_CAPE = registerItem("dragon_hide_cape", new Item(new FabricItemSettings().maxCount(1)));

    public static final Item PORCUPINE_SPAWN_EGG = registerItem("porcupine_spawn_egg",
            new SpawnEggItem(ModEntities.PORCUPINE, 0xa86518, 0x3b260f, new FabricItemSettings()));



    public static final Item DRAGON_HELMET = registerItem("dragon_helmet",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.HELMET, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_CHESTPLATE = registerItem("dragon_chestplate",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_LEGGINGS = registerItem("dragon_leggings",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.LEGGINGS, new FabricItemSettings().maxCount(1)));
    public static final Item DRAGON_BOOTS = registerItem("dragon_boots",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.BOOTS, new FabricItemSettings().maxCount(1)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        // USE THIS TO ADD ITEMS TO CREATIVE TABS
        //entries.add(DRAGON_PICKAXE);
        //entries.add(DRAGON_SHOVEL);
        //entries.add(DRAGON_AXE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DragonRealm.MOD_ID, name), item);
    }
    public static void registerModItems() {

        DragonRealm.LOGGER.info("Registering Mod Items for " + DragonRealm.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}
