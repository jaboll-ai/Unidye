package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.item.ModItems;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.*;

public interface UnidyeableItem {
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

    static ItemStack blendAndSetColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        if (stack.getItem() instanceof CustomDyeItem){
            return blendAndSetCustomDyeColor(stack, colors, customColors);
        }
        ItemStack itemStack = ItemStack.EMPTY;
        int n;
        int[] is = new int[3];
        int i = 0;
        int j = 0;
        DyeableItem dyeableItem = null;
        Item item = stack.getItem();
        if (item instanceof DyeableItem) {
            dyeableItem = (DyeableItem)((Object)item);
            itemStack = stack.copyWithCount(1);
            if (dyeableItem.hasColor(stack)) {
                int k = dyeableItem.getColor(itemStack);
                float f = (float)(k >> 16 & 0xFF) / 255.0f;
                float g = (float)(k >> 8 & 0xFF) / 255.0f;
                float h = (float)(k & 0xFF) / 255.0f;
                is[0] = is[0] + (int)(f * 255.0f * f * 255.0f);
                is[1] = is[1] + (int)(g * 255.0f * g * 255.0f);
                is[2] = is[2] + (int)(h * 255.0f * h * 255.0f);
                ++j;
            }
            for (DyeItem dyeItem : colors) {
                float[] fs = getColorArray(getMaterialType(dyeableItem), getDyeType(dyeItem));
                int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
                int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
                n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
                is[0] = is[0] + l;
                is[1] = is[1] + m;
                is[2] = is[2] + n;
                ++j;
            }
            for (ItemStack customDyeItem : customColors){
                float[] fs = getCustomColorArray(getMaterialType(dyeableItem), customDyeItem);
                int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
                int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
                n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
                is[0] = is[0] + l;
                is[1] = is[1] + m;
                is[2] = is[2] + n;
                ++j;
            }
        }
        if (dyeableItem == null) {
            return ItemStack.EMPTY;
        }
        int k = (int) Math.sqrt((double) is[0] / j);
        int o = (int) Math.sqrt((double) is[1] / j);
        int p = (int) Math.sqrt((double) is[2] / j);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        dyeableItem.setColor(itemStack, n);
        return itemStack;
    }

    static ItemStack blendAndSetCustomDyeColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        int n;
        int[] is = new int[3];
        int j = 0;
        Item item = stack.getItem();
        CustomDyeItem customDyeItem = (CustomDyeItem)((Object)item);
        ItemStack itemStack = stack.copyWithCount(1);
        if (customDyeItem.hasColor(stack)) {
            int k = customDyeItem.getColor(itemStack);
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float g = (float)(k >> 8 & 0xFF) / 255.0f;
            float h = (float)(k & 0xFF) / 255.0f;
            is[0] = is[0] + (int)(f * 255.0f * f * 255.0f);
            is[1] = is[1] + (int)(g * 255.0f * g * 255.0f);
            is[2] = is[2] + (int)(h * 255.0f * h * 255.0f);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray("leather", getDyeType(dyeItem));
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors){
            float[] fs = getCustomColorArray("leather", customDye);
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        int k = (int) Math.sqrt((double) is[0] / j);
        int o = (int) Math.sqrt((double) is[1] / j);
        int p = (int) Math.sqrt((double) is[2] / j);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        customDyeItem.setMaterialColor(itemStack, n, "leather");
        is = new int[3];
        j = 0;
        if (customDyeItem.hasColor(stack)) {
            k = customDyeItem.getColor(itemStack);
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float g = (float)(k >> 8 & 0xFF) / 255.0f;
            float h = (float)(k & 0xFF) / 255.0f;
            is[0] = is[0] + (int)(f * 255.0f * f * 255.0f);
            is[1] = is[1] + (int)(g * 255.0f * g * 255.0f);
            is[2] = is[2] + (int)(h * 255.0f * h * 255.0f);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray("wool", getDyeType(dyeItem));
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors){
            float[] fs = getCustomColorArray("wool", customDye);
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        k = (int) Math.sqrt((double) is[0] / j);
        o = (int) Math.sqrt((double) is[1] / j);
        p = (int) Math.sqrt((double) is[2] / j);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        customDyeItem.setMaterialColor(itemStack, n, "wool");
        is = new int[3];
        j = 0;
        if (customDyeItem.hasColor(stack)) {
            k = customDyeItem.getColor(itemStack);
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float g = (float)(k >> 8 & 0xFF) / 255.0f;
            float h = (float)(k & 0xFF) / 255.0f;
            is[0] = is[0] + (int)(f * 255.0f * f * 255.0f);
            is[1] = is[1] + (int)(g * 255.0f * g * 255.0f);
            is[2] = is[2] + (int)(h * 255.0f * h * 255.0f);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray("glass", getDyeType(dyeItem));
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors){
            float[] fs = getCustomColorArray("glass", customDye);
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        k = (int) Math.sqrt((double) is[0] / j);
        o = (int) Math.sqrt((double) is[1] / j);
        p = (int) Math.sqrt((double) is[2] / j);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        customDyeItem.setMaterialColor(itemStack, n, "glass");
        is = new int[3];
        j = 0;
        if (customDyeItem.hasColor(stack)) {
            k = customDyeItem.getColor(itemStack);
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float g = (float)(k >> 8 & 0xFF) / 255.0f;
            float h = (float)(k & 0xFF) / 255.0f;
            is[0] = is[0] + (int)(f * 255.0f * f * 255.0f);
            is[1] = is[1] + (int)(g * 255.0f * g * 255.0f);
            is[2] = is[2] + (int)(h * 255.0f * h * 255.0f);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray("concrete", getDyeType(dyeItem));
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors){
            float[] fs = getCustomColorArray("concrete", customDye);
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        k = (int) Math.sqrt((double) is[0] / j);
        o = (int) Math.sqrt((double) is[1] / j);
        p = (int) Math.sqrt((double) is[2] / j);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        customDyeItem.setMaterialColor(itemStack, n, "concrete");
        is = new int[3];
        j = 0;
        if (customDyeItem.hasColor(stack)) {
            k = customDyeItem.getColor(itemStack);
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float g = (float)(k >> 8 & 0xFF) / 255.0f;
            float h = (float)(k & 0xFF) / 255.0f;
            is[0] = is[0] + (int)(f * 255.0f * f * 255.0f);
            is[1] = is[1] + (int)(g * 255.0f * g * 255.0f);
            is[2] = is[2] + (int)(h * 255.0f * h * 255.0f);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray("terracotta", getDyeType(dyeItem));
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors){
            float[] fs = getCustomColorArray("terracotta", customDye);
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        k = (int) Math.sqrt((double) is[0] / j);
        o = (int) Math.sqrt((double) is[1] / j);
        p = (int) Math.sqrt((double) is[2] / j);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        customDyeItem.setMaterialColor(itemStack, n, "terracotta");
        is = new int[3];
        j = 0;
        if (customDyeItem.hasColor(stack)) {
            k = customDyeItem.getColor(itemStack);
            float f = (float)(k >> 16 & 0xFF) / 255.0f;
            float g = (float)(k >> 8 & 0xFF) / 255.0f;
            float h = (float)(k & 0xFF) / 255.0f;
            is[0] = is[0] + (int)(f * 255.0f * f * 255.0f);
            is[1] = is[1] + (int)(g * 255.0f * g * 255.0f);
            is[2] = is[2] + (int)(h * 255.0f * h * 255.0f);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray("dye", getDyeType(dyeItem));
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors){
            float[] fs = getCustomColorArray("dye", customDye);
            int l = (int)(fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int)(fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int)(fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        k = (int) Math.sqrt((double) is[0] / j);
        o = (int) Math.sqrt((double) is[1] / j);
        p = (int) Math.sqrt((double) is[2] / j);
        n = k;
        n = (n << 8) + o;
        n = (n << 8) + p;
        customDyeItem.setMaterialColor(itemStack, n, "dye");
        itemStack.setCount(j);
        return itemStack;
    }

    static String getDyeType(DyeItem dyeItem) {
        if (dyeItem == Items.WHITE_DYE) {
            return "white";
        } else if (dyeItem == Items.ORANGE_DYE) {
            return "orange";
        } else if (dyeItem == Items.MAGENTA_DYE) {
            return "magenta";
        } else if (dyeItem == Items.LIGHT_BLUE_DYE) {
            return "light_blue";
        } else if (dyeItem == Items.YELLOW_DYE) {
            return "yellow";
        } else if (dyeItem == Items.LIME_DYE) {
            return "lime";
        } else if (dyeItem == Items.PINK_DYE) {
            return "pink";
        } else if (dyeItem == Items.GRAY_DYE) {
            return "gray";
        } else if (dyeItem == Items.LIGHT_GRAY_DYE) {
            return "light_gray";
        } else if (dyeItem == Items.CYAN_DYE) {
            return "cyan";
        } else if (dyeItem == Items.PURPLE_DYE) {
            return "purple";
        } else if (dyeItem == Items.BLUE_DYE) {
            return "blue";
        } else if (dyeItem == Items.BROWN_DYE) {
            return "brown";
        } else if (dyeItem == Items.GREEN_DYE) {
            return "green";
        } else if (dyeItem == Items.RED_DYE) {
            return "red";
        } else if (dyeItem == Items.BLACK_DYE) {
            return "black";
        } else { return "unknown"; }
    }

    static String getMaterialType(DyeableItem dyeableItem) {
        if (dyeableItem == ModBlocks.CUSTOM_CONCRETE.asItem() || dyeableItem == ModBlocks.CUSTOM_CONCRETE_POWDER.asItem()) {
            return "concrete";
        } else if(dyeableItem == ModBlocks.CUSTOM_WOOL.asItem()){
            return "wool";
        } else if(dyeableItem == ModBlocks.CUSTOM_TERRACOTTA.asItem()){
            return "terracotta";
        } else if(dyeableItem == ModBlocks.CUSTOM_STAINED_GLASS.asItem()){
            return "glass";
        } else if(dyeableItem == ModItems.CUSTOM_DYE){
            return "dye";
        } else {
            return "leather";
        }
    }

    static String getMaterialType(Item item) {
        if (item == Items.TERRACOTTA) {
            return "terracotta";
        } else if(item == Items.WHITE_WOOL){
            return "wool";
        } else if(item == Items.GLASS){
            return "glass";
        } else {
            return "unknown";
        }
    }

    static float[] getColorArray(String materialType, String dyeType){
        return DYES.get(dyeType).getColorComponents(materialType);
    }

    static float[] getCustomColorArray(String materialType, ItemStack itemStack) {
        CustomDyeItem customDyeItem = (CustomDyeItem)itemStack.getItem();
        int color = customDyeItem.getMaterialColor(itemStack, materialType);
        int j = (color & 0xFF0000) >> 16;
        int k = (color & 0xFF00) >> 8;
        int l = (color & 0xFF) >> 0;
        return new float[]{(float)j / 255.0f, (float)k / 255.0f, (float)l / 255.0f};
    }

    static ItemStack dyeVanillaItems(ItemStack itemStack, ArrayList<ItemStack> customColors) {
        Item item = itemStack.getItem();
        ItemStack customDyeStack = customColors.get(0);
        CustomDyeItem customDyeItem = (CustomDyeItem)customDyeStack.getItem();
        int color = customDyeItem.getMaterialColor(customDyeStack, getMaterialType(item));
        DyeableItem dyeableItem = null;
        if (item == Items.GLASS){
            dyeableItem = (DyeableItem) ModBlocks.CUSTOM_STAINED_GLASS.asItem();
        } else if (item == Items.WHITE_WOOL){
            dyeableItem = (DyeableItem) ModBlocks.CUSTOM_WOOL.asItem();
        } else if (item == Items.TERRACOTTA){
            dyeableItem = (DyeableItem) ModBlocks.CUSTOM_TERRACOTTA.asItem();
        }
        ItemStack dyeableItemStack = ((Item) dyeableItem).getDefaultStack();
        dyeableItem.setColor(dyeableItemStack, color);
        dyeableItemStack.setCount(8);
        return dyeableItemStack;
    }
}
