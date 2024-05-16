package com.trustysidekick.dragonrealm;

import com.google.common.collect.ImmutableMultimap;
import com.trustysidekick.dragonrealm.block.ModBlocks;
import com.trustysidekick.dragonrealm.block.entity.ModBlockEntities;
import com.trustysidekick.dragonrealm.enchantments.ModEnchantments;
import com.trustysidekick.dragonrealm.entity.ModEntities;
import com.trustysidekick.dragonrealm.entity.custom.DragonWhelpEntity;
import com.trustysidekick.dragonrealm.entity.custom.HighlandDragonEntity;
import com.trustysidekick.dragonrealm.entity.custom.PorcupineEntity;
import com.trustysidekick.dragonrealm.item.ModItemGroups;
import com.trustysidekick.dragonrealm.item.ModItems;
import com.trustysidekick.dragonrealm.item.custom.DragonSwordItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DragonRealm implements ModInitializer {

	public static final String MOD_ID = "dragonrealm";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	Registry<Item> itemRegistry = Registries.ITEM;
	Iterable<Identifier> itemIds = itemRegistry.getIds();



	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModEntities.registerModEntities();
		ModEnchantments.registerEnchantments();
		CustomEntitySpawns.RegisterEntitySpawns();


		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.DRAGONWHELP, DragonWhelpEntity.createDragonWhelpAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.HIGHLAND_DRAGON, HighlandDragonEntity.createHighlandDragonAttributes());


		//// EVENTS ////
		// Right-click dragon using a bottle
		UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
			if (entity instanceof DragonWhelpEntity) {
				if (player != null && player.getStackInHand(hand).getItem() == Items.GLASS_BOTTLE) {
					if (((DragonWhelpEntity) entity).getHealth() < ((DragonWhelpEntity) entity).getMaxHealth()) {
						int bloodDraw = ((DragonWhelpEntity)entity).getBloodDraw();
						if (bloodDraw > 0) {
							((DragonWhelpEntity) entity).setBloodDraw(bloodDraw - 1);
							player.getInventory().getMainHandStack().decrement(1);
							player.getInventory().offerOrDrop(new ItemStack(ModItems.DRAGON_BLOOD));
						}
					}
					return ActionResult.SUCCESS;
				}
			}
			return ActionResult.PASS;
		});
		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
			if (killedEntity instanceof CowEntity) {
				if (entity instanceof PlayerEntity) {
					ItemStack stackInHand = ((PlayerEntity) entity).getStackInHand(((PlayerEntity) entity).getActiveHand());
					if (stackInHand.getItem() == ModItems.DRAGON_SWORD) {
						//((DragonSwordItem)stackInHand.getItem()).addCowKillModifier(stackInHand);
						NbtCompound nbt = stackInHand.getOrCreateNbt();
						int kills = nbt.getInt("kills");
						kills = kills + 1;
						nbt.putInt("kills", kills);
					}
				}
			}
		});
	}
}
