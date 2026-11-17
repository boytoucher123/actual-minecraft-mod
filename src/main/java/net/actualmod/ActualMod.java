package net.actualmod;

import java.util.Set;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActualMod implements ModInitializer {
	public static final String MOD_ID = "actualmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	/** Mobs this mod removes from the game. */
	public static final Set<EntityType<?>> REMOVED_ENTITIES = Set.of(
			EntityTypes.SNIFFER,
			EntityTypes.ALLAY
	);

	/** Items that only exist to produce a removed mob; hidden from the creative inventory. */
	public static final Set<Item> REMOVED_ITEMS = Set.of(
			Items.SNIFFER_SPAWN_EGG,
			Items.SNIFFER_EGG,
			Items.ALLAY_SPAWN_EGG
	);

	@Override
	public void onInitialize() {
		// Runs before any entity is added to a server world: natural/structure spawns,
		// spawn eggs, egg hatching, /summon, and entities loaded from existing saves.
		// Returning false drops the entity, so old worlds get cleaned up as chunks load.
		ServerEntityEvents.ALLOW_LOAD.register((entity, level, spawnReason, worldGenSpawned) ->
				!REMOVED_ENTITIES.contains(entity.getType()));

		CreativeModeTabEvents.MODIFY_OUTPUT_ALL.register((tab, output) -> {
			output.getDisplayStacks().removeIf(ActualMod::isRemovedItem);
			output.getSearchTabStacks().removeIf(ActualMod::isRemovedItem);
		});

		LOGGER.info("Sniffers and Allays have been removed.");
	}

	private static boolean isRemovedItem(ItemStack stack) {
		return REMOVED_ITEMS.contains(stack.getItem());
	}
}
