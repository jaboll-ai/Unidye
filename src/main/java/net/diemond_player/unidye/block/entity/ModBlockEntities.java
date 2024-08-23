package net.diemond_player.unidye.block.entity;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.Unidye;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
        public static final BlockEntityType<DyeableBlockEntity> DYEABLE_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableBlockEntity::new,
                        ModBlocks.CUSTOM_WOOL,
                        ModBlocks.CUSTOM_TERRACOTTA,
                        ModBlocks.CUSTOM_CONCRETE,
                        ModBlocks.CUSTOM_STAINED_GLASS,
                        ModBlocks.CUSTOM_CONCRETE_POWDER,
                        ModBlocks.CUSTOM_CARPET,
                        ModBlocks.CUSTOM_STAINED_GLASS_PANE,
                        ModBlocks.CUSTOM_CANDLE
                        ).build(null));

        public static final BlockEntityType<DyeableShulkerBoxBlockEntity> DYEABLE_SHULKER_BOX_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_shulker_box_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableShulkerBoxBlockEntity::new,
                        ModBlocks.CUSTOM_SHULKER_BOX
                        ).build(null));
        public static final BlockEntityType<DyeableBedBlockEntity> DYEABLE_BED_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_bed_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableBedBlockEntity::new,
                        ModBlocks.CUSTOM_BED
                        ).build(null));
        public static final BlockEntityType<DyeableBannerBlockEntity> DYEABLE_BANNER_BE = Registry.register(
                Registries.BLOCK_ENTITY_TYPE, new Identifier(Unidye.MOD_ID, "dyeable_banner_block_entity"),
                FabricBlockEntityTypeBuilder.create(DyeableBannerBlockEntity::new,
                        ModBlocks.CUSTOM_BANNER,
                        ModBlocks.CUSTOM_WALL_BANNER
                        ).build(null));


        public static void registerBlockEntities(){
            Unidye.LOGGER.info("Registering Block Entities for " + Unidye.MOD_ID);
        }
}
