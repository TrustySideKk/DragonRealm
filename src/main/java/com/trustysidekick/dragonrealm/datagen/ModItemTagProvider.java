package com.trustysidekick.dragonrealm.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
//        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
//                .add(ModItems.DRAGON_HELMET, ModItems.DRAGON_CHESTPLATE, ModItems.DRAGON_LEGGINGS, ModItems.DRAGON_BOOTS);
    }
}
