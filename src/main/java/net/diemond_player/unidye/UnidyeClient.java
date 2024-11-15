package net.diemond_player.unidye;

import net.diemond_player.unidye.item.UnidyeItems;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.diemond_player.unidye.Unidye.MOD_ID;


@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class UnidyeClient {

    @SubscribeEvent
    public static void registerItemColor(RegisterColorHandlersEvent.Item event) {
        Unidye.LOGGER.info("Registering itemColors for " + MOD_ID);
        event.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableLeatherItem) ((Object) stack.getItem())).getColor(stack), UnidyeItems.CUSTOM_DYE.get());
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
