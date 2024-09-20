package net.diemond_player.unidye.datagen;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class UnidyeBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public UnidyeBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.CANDLES)
                .add(UnidyeBlocks.CUSTOM_CANDLE);
        getOrCreateTagBuilder(BlockTags.TERRACOTTA)
                .add(UnidyeBlocks.CUSTOM_TERRACOTTA);
        getOrCreateTagBuilder(BlockTags.WOOL)
                .add(UnidyeBlocks.CUSTOM_WOOL);
        getOrCreateTagBuilder(BlockTags.WOOL_CARPETS)
                .add(UnidyeBlocks.CUSTOM_CARPET);
        getOrCreateTagBuilder(BlockTags.BEDS)
                .add(UnidyeBlocks.CUSTOM_BED);
        getOrCreateTagBuilder(BlockTags.SHULKER_BOXES)
                .add(UnidyeBlocks.CUSTOM_SHULKER_BOX);
    }
}
