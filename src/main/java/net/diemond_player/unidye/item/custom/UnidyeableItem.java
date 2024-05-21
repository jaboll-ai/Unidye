package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.block.ModBlocks;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UnidyeableItem {
    public static final String COLOR_KEY = "color";
    public static final String DISPLAY_KEY = "display";
    public static final int DEFAULT_COLOR = 16777215;
    public static final Map<String, UnidyeColor> DYES = new HashMap<String, UnidyeColor>(){{
        put("white", UnidyeColor.WHITE);
        put("orange", UnidyeColor.ORANGE);
        put("magenta", UnidyeColor.MAGENTA);
        put("light_blue", UnidyeColor.LIGHT_BLUE);
        put("yellow", UnidyeColor.YELLOW);
        put("lime", UnidyeColor.LIME);
        put("pink", UnidyeColor.PINK);
        put("gray", UnidyeColor.GRAY);
        put("light_gray", UnidyeColor.LIGHT_GRAY);
        put("cyan", UnidyeColor.CYAN);
        put("purple", UnidyeColor.PURPLE);
        put("blue", UnidyeColor.BLUE);
        put("brown", UnidyeColor.BROWN);
        put("green", UnidyeColor.GREEN);
        put("red", UnidyeColor.RED);
        put("black", UnidyeColor.BLACK);
    }};


    default public boolean hasColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        return nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE);
    }

    default public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(COLOR_KEY);
        }
        return DEFAULT_COLOR;
    }

    default public void removeColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY)) {
            nbtCompound.remove(COLOR_KEY);
        }
    }

    default public void setColor(ItemStack stack, int color) {
        stack.getOrCreateSubNbt(DISPLAY_KEY).putInt(COLOR_KEY, color);
    }

    public static ItemStack blendAndSetColor(ItemStack stack, List<DyeItem> colors) {
        int n;
        float h;
        ItemStack itemStack = ItemStack.EMPTY;
        int[] is = new int[3];
        int i = 0;
        int j = 0;
        UnidyeableItem unidyeableItem = null;
        Item item = stack.getItem();
        if (item instanceof UnidyeableItem) {
            unidyeableItem = (UnidyeableItem) ((Object)item);
            itemStack = stack.copyWithCount(1);
            if (unidyeableItem.hasColor(stack)) {
                int k = unidyeableItem.getColor(itemStack);
                float f = (float)(k >> 16 & 0xFF) / 255.0f;
                float g = (float)(k >> 8 & 0xFF) / 255.0f;
                h = (float)(k & 0xFF) / 255.0f;
                i += (int)(Math.max(f, Math.max(g, h)) * 255.0f);
                is[0] = is[0] + (int)(f * 255.0f);
                is[1] = is[1] + (int)(g * 255.0f);
                is[2] = is[2] + (int)(h * 255.0f);
                ++j;
            }
            for (DyeItem dyeItem : colors) {
                float[] fs = getColorArray(getMaterialType(item), getDyeType(dyeItem));
                int l = (int)(fs[0] * 255.0f);
                int m = (int)(fs[1] * 255.0f);
                n = (int)(fs[2] * 255.0f);
                i += Math.max(l, Math.max(m, n));
                is[0] = is[0] + l;
                is[1] = is[1] + m;
                is[2] = is[2] + n;
                ++j;
            }
        }
        if (unidyeableItem == null) {
            return ItemStack.EMPTY;
        }
        int k = is[0] / j;
        int o = is[1] / j;
        int p = is[2] / j;
        h = (float)i / (float)j;
        float q = Math.max(k, Math.max(o, p));
        k = (int)((float)k * h / q);
        o = (int)((float)o * h / q);
        p = (int)((float)p * h / q);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        unidyeableItem.setColor(itemStack, n);
        return itemStack;
    }

    static String getDyeType(DyeItem dyeItem) {
        if (dyeItem == Items.WHITE_DYE) {
            return "white";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.ORANGE_DYE)) {
            return "orange";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.MAGENTA_DYE)) {
            return "magenta";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.LIGHT_BLUE_DYE)) {
            return "light_blue";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.YELLOW_DYE)) {
            return "yellow";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.LIME_DYE)) {
            return "lime";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.PINK_DYE)) {
            return "pink";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.GRAY_DYE)) {
            return "gray";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.LIGHT_GRAY_DYE)) {
            return "light_gray";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.CYAN_DYE)) {
            return "cyan";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.PURPLE_DYE)) {
            return "purple";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.BROWN_DYE)) {
            return "brown";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.GREEN_DYE)) {
            return "green";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.RED_DYE)) {
            return "red";
        } else if (Item.getRawId(dyeItem) == Item.getRawId(Items.BLACK_DYE)) {
            return "black";
        } else { return "unknown"; }
    }

    static String getMaterialType(Item item) {
        if (item == ModBlocks.CUSTOM_CONCRETE.asItem()) {
            return "concrete";
        } else if(Item.getRawId(item) == Item.getRawId(ModBlocks.CUSTOM_WOOL.asItem())){
            return "wool";
        } else if(Item.getRawId(item) == Item.getRawId(ModBlocks.CUSTOM_TERRACOTTA.asItem())){
            return "terracotta";
        } else if(Item.getRawId(item) == Item.getRawId(ModBlocks.CUSTOM_STAINED_GLASS.asItem())){
            return "glass";
        } else{ return "leather";}
    }

    public static float[] getColorArray(String materialType, String dyeType){
        return DYES.get(dyeType).getColorComponents(materialType);
    }


}
