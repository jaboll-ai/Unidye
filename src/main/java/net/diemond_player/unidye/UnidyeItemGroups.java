package net.diemond_player.unidye;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DirtPathBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.diemond_player.unidye.Unidye.MOD_ID;

public class UnidyeItemGroups {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("unidye", () -> CreativeModeTab.builder()
        .withTabsBefore(CreativeModeTabs.COMBAT)
        .icon(() -> UnidyeItems.CUSTOM_DYE.get().getDefaultInstance())
        .displayItems((parameters, output) -> {
            output.accept(UnidyeItems.CUSTOM_DYE.get());
            output.accept(UnidyeBlocks.CUSTOM_WOOL.get());
            output.accept(UnidyeBlocks.CUSTOM_CARPET.get());
            output.accept(UnidyeBlocks.CUSTOM_TERRACOTTA.get());
            output.accept(UnidyeBlocks.CUSTOM_CONCRETE.get());
            output.accept(UnidyeBlocks.CUSTOM_CONCRETE_POWDER.get());
            output.accept(UnidyeBlocks.CUSTOM_STAINED_GLASS.get());
            output.accept(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.get());
//            output.accept(UnidyeBlocks.CUSTOM_SHULKER_BOX.get());
//            output.accept(UnidyeBlocks.CUSTOM_BED.get());
            output.accept(UnidyeBlocks.CUSTOM_CANDLE.get());
//            output.accept(UnidyeBlocks.CUSTOM_BANNER.get());

            //add diffrent once here
        }).build());


    public static void registerItemGroups(IEventBus modEventBus) {
        Unidye.LOGGER.info("Registering Item Groups for" + MOD_ID);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}