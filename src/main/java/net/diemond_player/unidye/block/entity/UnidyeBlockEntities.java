package net.diemond_player.unidye.block.entity;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.diemond_player.unidye.Unidye.MOD_ID;

public class UnidyeBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MOD_ID);
    public static final Supplier<BlockEntityType<DyeableBlockEntity>> DYEABLE_BE = BLOCK_ENTITIES.register("dyeable_block_entity",
            () -> BlockEntityType.Builder.of(DyeableBlockEntity::new,
                UnidyeBlocks.CUSTOM_TERRACOTTA.get(),
                UnidyeBlocks.CUSTOM_CONCRETE.get(),
                UnidyeBlocks.CUSTOM_CONCRETE_POWDER.get(),
                UnidyeBlocks.CUSTOM_CARPET.get(),
                UnidyeBlocks.CUSTOM_CANDLE.get()
//                UnidyeBlocks.CUSTOM_CANDLE_CAKE.get()
            ).build(null));
//    public static final BlockEntityType<DyeableBlockEntity> DYEABLE_BANNER_BE = BLOCK_ENTITIES.register("dyeable_block_entity", () -> new BlockEntityType<>(
//            DyeableBlockEntity::new,
//            UnidyeBlocks.CUSTOM_BANNER.get(),
//            UnidyeBlocks.CUSTOM_WALL_BANNER.get()
//    ).build(null));



    public static void registerBlockEntities(IEventBus modEventBus) {
        Unidye.LOGGER.info("Registering Block Entities for " + Unidye.MOD_ID);
        BLOCK_ENTITIES.register(modEventBus);
    }
}
