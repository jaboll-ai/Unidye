package net.diemond_player.unidye.block.entity;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.Unidye;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UnidyeBlockEntities {
        public static final BlockEntityType<DyeableBlockEntity> DYEABLE_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableBlockEntity::new,
                        UnidyeBlocks.CUSTOM_TERRACOTTA,
                        UnidyeBlocks.CUSTOM_CONCRETE,
                        UnidyeBlocks.CUSTOM_STAINED_GLASS,
                        UnidyeBlocks.CUSTOM_CONCRETE_POWDER,
                        UnidyeBlocks.CUSTOM_CARPET,
                        UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE,
                        UnidyeBlocks.CUSTOM_CANDLE,
                        UnidyeBlocks.CUSTOM_CANDLE_CAKE
                        ).build(null));

        public static final BlockEntityType<DyeableShulkerBoxBlockEntity> DYEABLE_SHULKER_BOX_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_shulker_box_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableShulkerBoxBlockEntity::new,
                        UnidyeBlocks.CUSTOM_SHULKER_BOX
                        ).build(null));
        public static final BlockEntityType<DyeableBedBlockEntity> DYEABLE_BED_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_bed_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableBedBlockEntity::new,
                        UnidyeBlocks.CUSTOM_BED
                        ).build(null));
        public static final BlockEntityType<DyeableBannerBlockEntity> DYEABLE_BANNER_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_banner_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableBannerBlockEntity::new,
                        UnidyeBlocks.CUSTOM_BANNER,
                        UnidyeBlocks.CUSTOM_WALL_BANNER
                        ).build(null));
        public static final BlockEntityType<DyeableWoolBlockEntity> DYEABLE_WOOL_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_wool_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableWoolBlockEntity::new,
                        UnidyeBlocks.CUSTOM_WOOL
                        ).build(null));


        public static void registerBlockEntities(){
            Unidye.LOGGER.info("Registering Block Entities for " + Unidye.MOD_ID);
        }
}
