package com.trustysidekick.dragonrealm.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class DragonSwordItem extends SwordItem {
    //private Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;
    public Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;


    public DragonSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        //ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        //this.attributeModifiers = builder.build();
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        NbtCompound tag = stack.getOrCreateNbt();

        if (tag != null) {
            tooltip.add(Text.of("Cow kills: " + tag.getInt("kills")));
            tooltip.add(Text.of(Formatting.DARK_RED + "Socket 1: " + tag.getString("socket1")));
            tooltip.add(Text.of("Socket 2: " + tag.getString("socket2")));
            tooltip.add(Text.of("Socket 3: " + tag.getString("socket3")));
            tooltip.add(Text.of("Attack Damage: " + this.getAttackDamage()));
        }
    }


}
