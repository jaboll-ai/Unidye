package net.diemond_player.unidye;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableShulkerBoxBlockEntity;
import net.diemond_player.unidye.block.entity.ModBlockEntities;
import net.diemond_player.unidye.entity.DyeableFallingBlockEntity;
import net.diemond_player.unidye.entity.ModEntities;
import net.diemond_player.unidye.entity.client.model.DyeableShulkerEntityModel;
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
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UnidyeClient implements ClientModInitializer {
    public static final BlockEntityType<DyeableShulkerBoxBlockEntity> DYEABLE_SHULKER = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier("unidye", "custom_shulker"),
            FabricBlockEntityTypeBuilder.create(DyeableShulkerBoxBlockEntity::new, ModBlocks.CUSTOM_SHULKER_BOX).build()
    );
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.DYEABLE_FALLING_BLOCK_ENTITY, DyeableFallingBlockEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DYEABLE_SHULKER_BOX_BE, DyeableShulkerBoxBlockEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CUSTOM_SHULKER, DyeableShulkerEntityModel::getTexturedModelData);
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
        registerBlockColor(ModBlocks.CUSTOM_SHULKER_BOX);
        registerBlockColor(ModBlocks.CUSTOM_BED);
        ModModelPredicateProvider.registerModModels();

    }

    private void registerItemColor(Item item) {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) ((Object) stack.getItem())).getColor(stack), item);
    }

    private void registerBlockColor(Block block) {
        registerItemColor(block.asItem());
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> DyeableBlockEntity.getColor(world,pos),block);
    }
}
