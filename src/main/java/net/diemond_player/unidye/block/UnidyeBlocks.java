package net.diemond_player.unidye.block;

import net.diemond_player.unidye.block.custom.*;
import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.item.custom.DyeableBlockItem;
import net.diemond_player.unidye.item.custom.DyeableGlassBlockItem;
import net.diemond_player.unidye.item.custom.DyeableWoolBlockItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UnidyeBlocks {

    //post-1.0
    //TODOO jeb_ dye
    //TODOO dye naming system (?)
    //TODOO dye recipe saving system (?)
    //TODOO add Llama carpets
    //TODOO add Glazed Terracotta
    //FIXMEE villagers do not use beds
    //FIXMEE sheep do not drop colored wool on death
    //FIXMEE when dropped on blocks; wrong particles
    //FIXMEE middlemouse + ctrl works not like intended
    //FIXMEE proper map markers check MapIcon.Type FilledMapItem map_icons MapRenderer
    //FIXMEE map colors
    //TODOO dye blocks second layer! (umhhhhh)



    //all of the to dos
    //TODO CLEAN CODE
    //TODO CLEAN MIXINS
    //TODO Bed Colors
    //TODO Sheep Bed Colors
    //TODO Candle Colors
    //TODO Glass Colors (Panes)
    //TODO Shulker Box Colors

    /*TODO integration with:
    DyeDepot
    Create
    Arts&Crafts
    Supplementaries
    SupplementariesSquared
    ...
     */

    public static final Block CUSTOM_WOOL = registerDyeableWoolBlock("custom_wool",
            new DyeableWoolBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));
    public static final Block CUSTOM_CONCRETE = registerDyeableBlock("custom_concrete",
            new DyeableBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE)));
    public static final Block CUSTOM_TERRACOTTA = registerDyeableBlock("custom_terracotta",
            new DyeableBlock(FabricBlockSettings.copyOf(Blocks.TERRACOTTA)));
    public static final Block CUSTOM_STAINED_GLASS = registerDyeableGlassBlock("custom_stained_glass",
            new DyeableGlassBlock(FabricBlockSettings.copyOf(Blocks.GLASS)));
    public static final Block CUSTOM_CONCRETE_POWDER = registerDyeableBlock("custom_concrete_powder",
            new DyeableConcretePowderBlock(CUSTOM_CONCRETE, FabricBlockSettings.copyOf(Blocks.WHITE_CONCRETE_POWDER)));
    public static final Block CUSTOM_CARPET = registerDyeableBlock("custom_carpet",
            new DyeableCarpetBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CARPET)));
    public static final Block CUSTOM_STAINED_GLASS_PANE = registerDyeableGlassBlock("custom_stained_glass_pane",
            new DyeablePaneBlock(FabricBlockSettings.copyOf(Blocks.WHITE_STAINED_GLASS_PANE)));
    public static final Block CUSTOM_CANDLE = registerDyeableBlock("custom_candle",
            new DyeableCandleBlock(FabricBlockSettings.copyOf(Blocks.WHITE_CANDLE)));
    public static final Block CUSTOM_CANDLE_CAKE = registerBlockWithoutItem("custom_candle_cake",
            new DyeableCandleCakeBlock(CUSTOM_CANDLE, FabricBlockSettings.copyOf(Blocks.WHITE_CANDLE_CAKE)));
    public static final Block CUSTOM_SHULKER_BOX = registerSingletonDyeableBlock("custom_shulker_box",
            new DyeableShulkerBoxBlock(FabricBlockSettings.copyOf(Blocks.WHITE_SHULKER_BOX)));
    public static final Block CUSTOM_BED = registerSingletonDyeableBlock("custom_bed",
            new DyeableBedBlock(FabricBlockSettings.copyOf(Blocks.WHITE_BED)));
    public static final Block CUSTOM_BANNER = registerBlockWithoutItem("custom_banner",
            new DyeableBannerBlock(FabricBlockSettings.copyOf(Blocks.WHITE_BANNER)));
    public static final Block CUSTOM_WALL_BANNER = registerBlockWithoutItem("custom_wall_banner",
            new DyeableWallBannerBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WALL_BANNER).dropsLike(CUSTOM_BANNER)));
//    private static Block registerBlock(String name, Block block){
//        registerBlockItem(name, block);
//        return Registry.register(Registries.BLOCK, new Identifier(Unidye.MOD_ID, name), block);
//    }

    private static Block registerBlockWithoutItem(String name, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(Unidye.MOD_ID, name), block);
    }

//    private static Item registerBlockItem(String name, Block block){
//        return Registry.register(Registries.ITEM, new Identifier(Unidye.MOD_ID, name),
//                new BlockItem(block, new FabricItemSettings()));
//    }

    public static void registerModBlocks(){
        Unidye.LOGGER.info("Registering Mod Blocks for" + Unidye.MOD_ID);
}
    private static Block registerDyeableBlock(String name, Block block) {
        registerDyeableBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Unidye.MOD_ID, name), block);
    }
    private static Block registerDyeableWoolBlock(String name, Block block) {
        registerDyeableWoolBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Unidye.MOD_ID, name), block);
    }
    private static Block registerDyeableGlassBlock(String name, Block block) {
        registerDyeableGlassBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Unidye.MOD_ID, name), block);
    }
    private static Block registerSingletonDyeableBlock(String name, Block block) {
        registerSingletonDyeableBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Unidye.MOD_ID, name), block);
    }
    private static void registerSingletonDyeableBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Unidye.MOD_ID, name), new DyeableBlockItem(block, new FabricItemSettings().maxCount(1)));
    }
    private static void registerDyeableBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Unidye.MOD_ID, name), new DyeableBlockItem(block, new FabricItemSettings()));
    }
    private static void registerDyeableWoolBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Unidye.MOD_ID, name), new DyeableWoolBlockItem(block, new FabricItemSettings()));
    }
    private static void registerDyeableGlassBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Unidye.MOD_ID, name), new DyeableGlassBlockItem(block, new FabricItemSettings()));
    }
}
