package net.diemond_player.unidye.util;

import net.diemond_player.unidye.Unidye;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class UnidyeTags {
    public static class Blocks{

        private static TagKey<Block> createBlockTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Unidye.MOD_ID, name));
        }

        private static TagKey<Block> createCommonBlockTag(String name){
            return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
        }
    }

    public static class Items{

        public static final TagKey<Item> GLASS =
                createItemTag("glass");
        public static final TagKey<Item> GLASS_PANE =
                createItemTag("glass_pane");
        public static final TagKey<Item> SHULKER_BOXES =
                createItemTag("shulker_boxes");

        private static TagKey<Item> createItemTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Unidye.MOD_ID, name));
        }

        private static TagKey<Item> createCommonItemTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }
}
