package net.diemond_player.unidye;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.entity.*;
import net.diemond_player.unidye.entity.DyeableFallingBlockEntity;
import net.diemond_player.unidye.entity.ModEntities;
import net.diemond_player.unidye.entity.client.model.DyeableShulkerEntityModel;
import net.diemond_player.unidye.entity.client.renderer.DyeableBannerBlockEntityRenderer;
import net.diemond_player.unidye.entity.client.renderer.DyeableBedBlockEntityRenderer;
import net.diemond_player.unidye.entity.client.renderer.DyeableShulkerBoxBlockEntityRenderer;
import net.diemond_player.unidye.entity.layer.ModModelLayers;
import net.diemond_player.unidye.item.ModItems;
import net.diemond_player.unidye.entity.client.renderer.DyeableFallingBlockEntityRenderer;
import net.diemond_player.unidye.util.ModModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.data.client.Models;
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UnidyeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.DYEABLE_FALLING_BLOCK_ENTITY, DyeableFallingBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DYEABLE_SHULKER_BOX_BE, DyeableShulkerBoxBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DYEABLE_BED_BE, DyeableBedBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DYEABLE_BANNER_BE, DyeableBannerBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CUSTOM_SHULKER, DyeableShulkerEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CUSTOM_BED_HEAD, DyeableBedBlockEntityRenderer::getHeadTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CUSTOM_BED_FOOT, DyeableBedBlockEntityRenderer::getFootTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CUSTOM_BANNER, DyeableBannerBlockEntityRenderer::getTexturedModelData);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CUSTOM_STAINED_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CUSTOM_STAINED_GLASS_PANE, RenderLayer.getTranslucent());
        registerItemColor(ModItems.CUSTOM_DYE);
        registerBlockColor(ModBlocks.CUSTOM_WOOL);
        registerBlockColor(ModBlocks.CUSTOM_CONCRETE);
        registerBlockColor(ModBlocks.CUSTOM_TERRACOTTA);
        registerBlockColor(ModBlocks.CUSTOM_STAINED_GLASS);
        registerBlockColor(ModBlocks.CUSTOM_CONCRETE_POWDER);
        registerBlockColor(ModBlocks.CUSTOM_CARPET);
        registerBlockColor(ModBlocks.CUSTOM_STAINED_GLASS_PANE);
        registerBlockColor(ModBlocks.CUSTOM_CANDLE);
        registerBlockColor1(ModBlocks.CUSTOM_SHULKER_BOX);
        registerItemColor(ModBlocks.CUSTOM_BANNER.asItem());
        ModModelPredicateProvider.registerModModels();

    }

    private void registerItemColor(Item item) {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) ((Object) stack.getItem())).getColor(stack), item);
    }

    private void registerBlockColor(Block block) {
        registerItemColor(block.asItem());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> DyeableBlockEntity.getColor(world,pos),block);
    }

    private void registerBlockColor1(Block block) {
        registerItemColor(block.asItem());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> DyeableShulkerBoxBlockEntity.getColor(world,pos),block);
    }
}
