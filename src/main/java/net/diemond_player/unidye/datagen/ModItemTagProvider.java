package net.diemond_player.unidye.datagen;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.CANDLES)
                .add(ModBlocks.CUSTOM_CANDLE.asItem());
        getOrCreateTagBuilder(ItemTags.DAMPENS_VIBRATIONS)
                .add(ModBlocks.CUSTOM_CARPET.asItem(),
                        ModBlocks.CUSTOM_WOOL.asItem());
        getOrCreateTagBuilder(ItemTags.TERRACOTTA)
                .add(ModBlocks.CUSTOM_TERRACOTTA.asItem());
        getOrCreateTagBuilder(ItemTags.WOOL)
                .add(ModBlocks.CUSTOM_WOOL.asItem());
        getOrCreateTagBuilder(ItemTags.WOOL_CARPETS)
                .add(ModBlocks.CUSTOM_CARPET.asItem());
    }
}
