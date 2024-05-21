package net.diemond_player.unidye;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.item.ModItems;
import net.diemond_player.unidye.item.custom.UnidyeableItem;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;

public class UnidyeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CUSTOM_STAINED_GLASS, RenderLayer.getTranslucent());
        registerItemColorD(ModItems.CUSTOM_DYE);
        registerBlockColor(ModBlocks.CUSTOM_WOOL);
        registerBlockColor(ModBlocks.CUSTOM_CONCRETE);
        registerBlockColor(ModBlocks.CUSTOM_TERRACOTTA);
        registerBlockColor(ModBlocks.CUSTOM_STAINED_GLASS);

    }

    private void registerItemColorD(Item item) {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) ((Object) stack.getItem())).getColor(stack), item);
    }

    private void registerItemColorU(Item item) {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((UnidyeableItem) ((Object) stack.getItem())).getColor(stack), item);
    }

    private void registerBlockColor(Block block) {
        registerItemColorU(block.asItem());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> DyeableBlockEntity.getColor(world,pos),block);
    }
}
