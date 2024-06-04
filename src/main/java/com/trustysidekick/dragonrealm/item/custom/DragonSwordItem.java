package com.trustysidekick.dragonrealm.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.trustysidekick.dragonrealm.enchantments.ModEnchantments;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

public class DragonSwordItem extends SwordItem {

    public DragonSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        NbtCompound tag = stack.getOrCreateNbt();
        int cowKills = tag.getInt("kills");
        float totalAttack = this.getAttackDamage() + (cowKills * 0.5f);

        if (tag != null) {
            tooltip.add(Text.of("Cow kills: " + tag.getInt("kills")));
            tooltip.add(Text.of(Formatting.DARK_RED + "Socket 1: " + tag.getString("socket1")));
            tooltip.add(Text.of("Socket 2: " + tag.getString("socket2")));
            tooltip.add(Text.of("Socket 3: " + tag.getString("socket3")));
            tooltip.add(Text.of("Attack Damage: " + this.getAttackDamage()));
            tooltip.add(Text.of(Formatting.YELLOW + "Total Attack Damage: " + totalAttack));
        }
    }



    private float getEnchantmentDamage(ItemStack stack) {
        float enchantmentDamage = 0.0F;
        if (stack.hasEnchantments()) {
            for (NbtElement enchantment : stack.getEnchantments()) {
                if (enchantment == ModEnchantments.DRAGON_STRIKE) {
                    System.out.println("HERE");
                    //return ModEnchantments.DRAGON_STRIKE.
                }
            }
        }
//        for (EntityAttributeModifier modifier : stack.getAttributeModifiers(EquipmentSlot.CHEST)) {
//            if (modifier.getName().equals("generic.attackDamage")) {
//                enchantmentDamage += (float) modifier.getValue();
//            }
//        }
        return enchantmentDamage;
    }


}
