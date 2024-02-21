package com.trustysidekick.dragonrealm.item;

import com.trustysidekick.dragonrealm.DragonRealm;
import com.trustysidekick.dragonrealm.item.custom.DragonArmorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item DRAGON_PICKAXE = registerItem("dragon_pickaxe", new Item(new FabricItemSettings()));
    public static final Item DRAGON_SHOVEL = registerItem("dragon_shovel", new Item(new FabricItemSettings()));
    public static final Item DRAGON_AXE = registerItem("dragon_axe", new Item(new FabricItemSettings()));

    public static final Item DRAGON_HELMET = registerItem("dragon_helmet",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item DRAGON_CHESTPLATE = registerItem("dragon_chestplate",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item DRAGON_LEGGINGS = registerItem("dragon_leggings",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item DRAGON_BOOTS = registerItem("dragon_boots",
            new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.BOOTS, new FabricItemSettings()));

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
