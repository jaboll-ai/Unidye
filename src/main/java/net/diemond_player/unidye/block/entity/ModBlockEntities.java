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
                        ModBlocks.CUSTOM_STAINED_GLASS
                        ).build(null));

        public static void registerBlockEntities(){
            Unidye.LOGGER.info("Registering Block Entities for " + Unidye.MOD_ID);
        }
}
