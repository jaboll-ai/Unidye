package net.diemond_player.unidye;

import net.diemond_player.unidye.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;

public class UnidyeClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        registerItemColor(ModItems.CUSTOM_DYE);
    }

    private void registerItemColor(Item item) {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) ((Object) stack.getItem())).getColor(stack), item);
    }
}
