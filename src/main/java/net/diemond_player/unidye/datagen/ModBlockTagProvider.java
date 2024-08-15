package net.diemond_player.unidye.datagen;

import net.diemond_player.unidye.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(BlockTags.CANDLES)
                .add(ModBlocks.CUSTOM_CANDLE);
        getOrCreateTagBuilder(BlockTags.TERRACOTTA)
                .add(ModBlocks.CUSTOM_TERRACOTTA);
        getOrCreateTagBuilder(BlockTags.WOOL)
                .add(ModBlocks.CUSTOM_WOOL);
        getOrCreateTagBuilder(BlockTags.WOOL_CARPETS)
                .add(ModBlocks.CUSTOM_CARPET);
        getOrCreateTagBuilder(BlockTags.BEDS)
                .add(ModBlocks.CUSTOM_BED);
        getOrCreateTagBuilder(BlockTags.SHULKER_BOXES)
                .add(ModBlocks.CUSTOM_SHULKER_BOX);
    }
}
