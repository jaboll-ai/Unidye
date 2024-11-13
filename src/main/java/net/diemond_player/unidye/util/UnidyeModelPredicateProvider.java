package net.diemond_player.unidye.util;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;


public class UnidyeModelPredicateProvider {
    public static void registerModModels() {
        ItemProperties.register(UnidyeItems.CUSTOM_DYE.get(), new ResourceLocation(Unidye.MOD_ID, "dye_id"),
                (stack, world, entity, seed) -> CustomDyeItem.getClosestVanillaDyeId(stack));
    }
}
