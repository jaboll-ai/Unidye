package net.diemond_player.unidye.block;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.custom.DyeableBlock;
import net.diemond_player.unidye.block.custom.DyeableCandleBlock;
import net.diemond_player.unidye.block.custom.DyeableCarpetBlock;
import net.diemond_player.unidye.block.custom.DyeableConcretePowderBlock;
import net.diemond_player.unidye.item.custom.DyeableBlockItem;
import net.diemond_player.unidye.item.custom.DyeableLeatheryBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

import static net.diemond_player.unidye.Unidye.MOD_ID;
import static net.diemond_player.unidye.item.UnidyeItems.ITEMS;

public class UnidyeBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static final RegistryObject<Block> CUSTOM_CONCRETE = registerDyeableBlock("custom_concrete",
            () -> new DyeableBlock(Block.Properties.copy(Blocks.WHITE_CONCRETE)));
    public static final RegistryObject<Block> CUSTOM_TERRACOTTA = registerDyeableBlock("custom_terracotta",
            () -> new DyeableBlock(Block.Properties.copy(Blocks.TERRACOTTA)));
    public static final RegistryObject<Block> CUSTOM_CONCRETE_POWDER = registerDyeableBlock("custom_concrete_powder",
            () -> new DyeableConcretePowderBlock(CUSTOM_CONCRETE.get(), Block.Properties.copy(Blocks.WHITE_CONCRETE_POWDER)));
    public static final RegistryObject<Block> CUSTOM_CARPET = registerDyeableBlock("custom_carpet",
            () -> new DyeableCarpetBlock(Block.Properties.copy(Blocks.WHITE_CARPET)));
    public static final RegistryObject<Block> CUSTOM_CANDLE = registerDyeableBlock("custom_candle",
            () -> new DyeableCandleBlock(Block.Properties.copy(Blocks.WHITE_CANDLE)));
//    public static final RegistryObject<Block> CUSTOM_SHULKER_BOX = registerDyeableBlock("custom_shulker_box",
//            new DyeableShulkerBoxBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_SHULKER_BOX)), new FabricItemSettings().maxCount(1));
//    public static final RegistryObject<Block> CUSTOM_BED = registerDyeableBlock("custom_bed",
//            new DyeableBedBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_BED)), new Item.Properties().stacksTo(1));
//    public static final RegistryObject<Block> CUSTOM_CANDLE_CAKE = registerBlockWithoutItem("custom_candle_cake",
//            new DyeableCandleCakeBlock(CUSTOM_CANDLE, FabricBlockSettings.copyOf(Blocks.WHITE_CANDLE_CAKE)));

    private static RegistryObject<Block> registerBlockWithoutItem(String name, Supplier<Block> block) {
        return BLOCKS.register(name, block);
    }
    private static RegistryObject<Block> registerDyeableBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> registeredBlock = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new DyeableBlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }
    private static RegistryObject<Block>  registerDyeableBlock(String name, Supplier<Block> block, Item.Properties properties) {
        RegistryObject<Block> registeredBlock = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new DyeableBlockItem(registeredBlock.get(), properties));
        return registeredBlock;
    }
    private static RegistryObject<Block>  registerDyeableLeatheryBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> registeredBlock = BLOCKS.register(name, block);
        ITEMS.register(name, () -> new DyeableLeatheryBlockItem(registeredBlock.get(), new Item.Properties()));
        return registeredBlock;
    }

    public static void registerModBlocks(IEventBus modEventBus) {
        Unidye.LOGGER.info("Registering Mod Blocks for " + Unidye.MOD_ID);
        BLOCKS.register(modEventBus);
    }

}
