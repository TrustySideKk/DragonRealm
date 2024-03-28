package com.trustysidekick.dragonrealm.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import static net.minecraft.entity.damage.DamageTypes.ON_FIRE;

public class SearingIronIngot extends Item {
    public SearingIronIngot(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        LivingEntity mob = (LivingEntity) entity;
        mob.setOnFire(true);
        mob.damage(mob.getDamageSources().onFire(), 1.0f);
    }
}
