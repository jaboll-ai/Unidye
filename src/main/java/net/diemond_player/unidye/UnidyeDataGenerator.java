package net.diemond_player.unidye;

import net.diemond_player.unidye.datagen.UnidyeBlockTagProvider;
import net.diemond_player.unidye.datagen.UnidyeItemTagProvider;
import net.diemond_player.unidye.datagen.UnidyeModelProvider;
import net.diemond_player.unidye.datagen.UnidyeRecipeGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class UnidyeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(UnidyeModelProvider::new);
		pack.addProvider(UnidyeRecipeGenerator::new);
		pack.addProvider(UnidyeBlockTagProvider::new);
		pack.addProvider(UnidyeItemTagProvider::new);
	}
}
