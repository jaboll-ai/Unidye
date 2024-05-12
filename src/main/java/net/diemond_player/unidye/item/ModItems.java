package net.diemond_player.unidye.item;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CUSTOM_DYE = registerItem("custom_dye",
            new CustomDyeItem(new FabricItemSettings()));


    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Unidye.MOD_ID, name), item);
    }
    public static void registerModItems(){
        Unidye.LOGGER.info("Registering Mod Items for" + Unidye.MOD_ID);
    }

    // 07 05 2024 влад и мета тут были
}
