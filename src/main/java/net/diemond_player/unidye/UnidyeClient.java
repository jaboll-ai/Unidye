package net.diemond_player.unidye;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableLeatheryBlockEntity;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.util.UnidyeModelPredicateProvider;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static net.diemond_player.unidye.Unidye.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnidyeClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        UnidyeModelPredicateProvider.registerModModels();
    }

    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        Unidye.LOGGER.info("Registering itemColors for " + MOD_ID);
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeItems.CUSTOM_DYE.get());

        // BlockItems
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : adjust(((DyeableLeatherItem) stack.getItem()).getColor(stack), 15), UnidyeBlocks.CUSTOM_CONCRETE_POWDER.get().asItem());
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeBlocks.CUSTOM_CONCRETE.get().asItem());
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeBlocks.CUSTOM_WOOL.get().asItem());
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeBlocks.CUSTOM_CARPET.get().asItem());
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeBlocks.CUSTOM_TERRACOTTA.get().asItem());
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeBlocks.CUSTOM_CANDLE.get().asItem());
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeBlocks.CUSTOM_STAINED_GLASS.get().asItem());
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) stack.getItem()).getColor(stack), UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.get().asItem());
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event){
        event.register((state, level, pos, tintIndex) -> DyeableBlockEntity.getColor(level, pos), UnidyeBlocks.CUSTOM_CONCRETE.get());
        event.register((state, level, pos, tintIndex) -> adjust(DyeableBlockEntity.getColor(level, pos), 15), UnidyeBlocks.CUSTOM_CONCRETE_POWDER.get());
        event.register((state, level, pos, tintIndex) -> DyeableLeatheryBlockEntity.getColor(level, pos), UnidyeBlocks.CUSTOM_WOOL.get());
        event.register((state, level, pos, tintIndex) -> DyeableBlockEntity.getColor(level, pos), UnidyeBlocks.CUSTOM_CARPET.get());
        event.register((state, level, pos, tintIndex) -> DyeableBlockEntity.getColor(level, pos), UnidyeBlocks.CUSTOM_TERRACOTTA.get());
        event.register((state, level, pos, tintIndex) -> DyeableBlockEntity.getColor(level, pos), UnidyeBlocks.CUSTOM_CANDLE.get());
        event.register((state, level, pos, tintIndex) -> DyeableLeatheryBlockEntity.getColor(level, pos), UnidyeBlocks.CUSTOM_STAINED_GLASS.get());
        event.register((state, level, pos, tintIndex) -> DyeableLeatheryBlockEntity.getColor(level, pos), UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.get());
    }

    public static int adjust(int color, int i) {
        int j = Math.min(((color & 0xFF0000) >> 16) + i, 255);
        int k = Math.min(((color & 0xFF00) >> 8) + i, 255);
        int l = Math.min(((color & 0xFF) >> 0) + i, 255);
        int res = j;
        res = (res << 8) + k;
        res = (res << 8) + l;
        return res;
    }
}
