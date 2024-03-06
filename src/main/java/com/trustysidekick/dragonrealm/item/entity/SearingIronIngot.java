package com.trustysidekick.dragonrealm.item.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SearingIronIngot extends Item {

    public SearingIronIngot(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;

            player.setOnFire(true);
            player.damage(player.getDamageSources().onFire(), 1.0f);
        }
    }
}
