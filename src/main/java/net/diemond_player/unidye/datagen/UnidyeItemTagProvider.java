package net.diemond_player.unidye.datagen;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.util.UnidyeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class UnidyeItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public UnidyeItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ItemTags.CANDLES)
                .add(UnidyeBlocks.CUSTOM_CANDLE.asItem());
        getOrCreateTagBuilder(ItemTags.DAMPENS_VIBRATIONS)
                .add(UnidyeBlocks.CUSTOM_CARPET.asItem(),
                        UnidyeBlocks.CUSTOM_WOOL.asItem());
        getOrCreateTagBuilder(ItemTags.TERRACOTTA)
                .add(UnidyeBlocks.CUSTOM_TERRACOTTA.asItem());
        getOrCreateTagBuilder(ItemTags.WOOL)
                .add(UnidyeBlocks.CUSTOM_WOOL.asItem());
        getOrCreateTagBuilder(ItemTags.WOOL_CARPETS)
                .add(UnidyeBlocks.CUSTOM_CARPET.asItem());
        getOrCreateTagBuilder(UnidyeTags.Items.GLASS)
                .add(UnidyeBlocks.CUSTOM_STAINED_GLASS.asItem(),
                        Items.BLACK_STAINED_GLASS,
                        Items.BLUE_STAINED_GLASS,
                        Items.BROWN_STAINED_GLASS,
                        Items.CYAN_STAINED_GLASS,
                        Items.GRAY_STAINED_GLASS,
                        Items.GREEN_STAINED_GLASS,
                        Items.LIGHT_BLUE_STAINED_GLASS,
                        Items.LIGHT_GRAY_STAINED_GLASS,
                        Items.LIME_STAINED_GLASS,
                        Items.MAGENTA_STAINED_GLASS,
                        Items.ORANGE_STAINED_GLASS,
                        Items.PINK_STAINED_GLASS,
                        Items.PURPLE_STAINED_GLASS,
                        Items.RED_STAINED_GLASS,
                        Items.WHITE_STAINED_GLASS,
                        Items.YELLOW_STAINED_GLASS,
                        Items.GLASS);
        getOrCreateTagBuilder(UnidyeTags.Items.GLASS_PANE)
                .add(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.asItem(),
                        Items.BLACK_STAINED_GLASS_PANE,
                        Items.BLUE_STAINED_GLASS_PANE,
                        Items.BROWN_STAINED_GLASS_PANE,
                        Items.CYAN_STAINED_GLASS_PANE,
                        Items.GRAY_STAINED_GLASS_PANE,
                        Items.GREEN_STAINED_GLASS_PANE,
                        Items.LIGHT_BLUE_STAINED_GLASS_PANE,
                        Items.LIGHT_GRAY_STAINED_GLASS_PANE,
                        Items.LIME_STAINED_GLASS_PANE,
                        Items.MAGENTA_STAINED_GLASS_PANE,
                        Items.ORANGE_STAINED_GLASS_PANE,
                        Items.PINK_STAINED_GLASS_PANE,
                        Items.PURPLE_STAINED_GLASS_PANE,
                        Items.RED_STAINED_GLASS_PANE,
                        Items.WHITE_STAINED_GLASS_PANE,
                        Items.YELLOW_STAINED_GLASS_PANE,
                        Items.GLASS_PANE);
        getOrCreateTagBuilder(UnidyeTags.Items.SHULKER_BOXES)
                .add(UnidyeBlocks.CUSTOM_SHULKER_BOX.asItem(),
                        Items.BLACK_SHULKER_BOX,
                        Items.BLUE_SHULKER_BOX,
                        Items.BROWN_SHULKER_BOX,
                        Items.CYAN_SHULKER_BOX,
                        Items.GRAY_SHULKER_BOX,
                        Items.GREEN_SHULKER_BOX,
                        Items.LIGHT_BLUE_SHULKER_BOX,
                        Items.LIGHT_GRAY_SHULKER_BOX,
                        Items.LIME_SHULKER_BOX,
                        Items.MAGENTA_SHULKER_BOX,
                        Items.ORANGE_SHULKER_BOX,
                        Items.PINK_SHULKER_BOX,
                        Items.PURPLE_SHULKER_BOX,
                        Items.RED_SHULKER_BOX,
                        Items.WHITE_SHULKER_BOX,
                        Items.YELLOW_SHULKER_BOX,
                        Items.SHULKER_BOX);

    }
}
