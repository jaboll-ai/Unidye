package net.diemond_player.unidye;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.entity.ModBlockEntities;
import net.diemond_player.unidye.item.ModItemGroups;
import net.diemond_player.unidye.item.ModItems;
import net.diemond_player.unidye.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Unidye implements ModInitializer {
	public static final String MOD_ID = "unidye";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModRecipes.registerRecipes();
	}
}