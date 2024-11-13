package net.diemond_player.unidye.item;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;



public class UnidyeItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Unidye.MOD_ID);
    public static final RegistryObject<Item> CUSTOM_DYE = ITEMS.register("custom_dye",
            () -> new CustomDyeItem(new Item.Properties()));

    public static void registerModItems(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        Unidye.LOGGER.info("Registering Mod Items for" + Unidye.MOD_ID);
    }

}