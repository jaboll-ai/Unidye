package net.diemond_player.unidye.util;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.diemond_player.unidye.item.custom.CustomDyeItem.*;
import static net.minecraft.world.item.DyeableLeatherItem.TAG_COLOR;
import static net.minecraft.world.item.DyeableLeatherItem.TAG_DISPLAY;

public class UnidyeUtils {
    public static Map<Item, UnidyeColor> DYES = new HashMap<>() {{
        put(Items.WHITE_DYE, UnidyeColor.WHITE);
        put(Items.ORANGE_DYE, UnidyeColor.ORANGE);
        put(Items.YELLOW_DYE, UnidyeColor.YELLOW);
        put(Items.LIGHT_GRAY_DYE, UnidyeColor.LIGHT_GRAY);
        put(Items.CYAN_DYE, UnidyeColor.CYAN);
        put(Items.BLUE_DYE, UnidyeColor.BLUE);
        put(Items.BROWN_DYE, UnidyeColor.BROWN);
        put(Items.GREEN_DYE, UnidyeColor.GREEN);
        put(Items.RED_DYE, UnidyeColor.RED);
        put(Items.BLACK_DYE, UnidyeColor.BLACK);
        put(Items.LIGHT_BLUE_DYE, UnidyeColor.LIGHT_BLUE);
        put(Items.MAGENTA_DYE, UnidyeColor.MAGENTA);
        put(Items.LIME_DYE, UnidyeColor.LIME);
        put(Items.PINK_DYE, UnidyeColor.PINK);
        put(Items.GRAY_DYE, UnidyeColor.GRAY);
        put(Items.PURPLE_DYE, UnidyeColor.PURPLE);
    }};
    public static Map<Item, String> MATERIAL_TYPES = new HashMap<>() {{
//        put(UnidyeBlocks.CUSTOM_WOOL.asItem(), "wool");
        put(UnidyeBlocks.CUSTOM_CARPET.get().asItem(), "wool");
        put(UnidyeBlocks.CUSTOM_TERRACOTTA.get().asItem(), "terracotta");
        put(UnidyeBlocks.CUSTOM_CONCRETE.get().asItem(), "concrete");
        put(UnidyeBlocks.CUSTOM_CONCRETE_POWDER.get().asItem(), "concrete");
//        put(UnidyeBlocks.CUSTOM_STAINED_GLASS.asItem(), "glass");
//        put(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.asItem(), "glass");
        put(UnidyeItems.CUSTOM_DYE.get(), "dye");
//        put(UnidyeBlocks.CUSTOM_SHULKER_BOX.get().asItem(), "shulker_box");
        put(UnidyeBlocks.CUSTOM_CANDLE.get().asItem(), "candle");
    }};

    public static void setColor(ItemStack stack, int color) {
        stack.getOrCreateTagElement(TAG_DISPLAY).putInt(TAG_COLOR, color);
    }

    public static int getColor(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTagElement(TAG_DISPLAY);
        if (nbtCompound != null && nbtCompound.contains(TAG_COLOR, CompoundTag.TAG_ANY_NUMERIC)) {
            return nbtCompound.getInt(TAG_COLOR);
        }
        return DEFAULT_COLOR;
    }
    public static boolean hasColor(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTagElement(TAG_DISPLAY);
        return nbtCompound != null && nbtCompound.contains(TAG_COLOR, CompoundTag.TAG_ANY_NUMERIC);
    }

    public static ItemStack blendAndSetColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        if (stack.getItem() instanceof CustomDyeItem) {
            stack = blendAndSetCustomDyeColor(stack, colors, customColors);
        }
//        if (stack.is(UnidyeBlocks.CUSTOM_WOOL.asItem()) || stack.is(UnidyeBlocks.CUSTOM_STAINED_GLASS.asItem()) || stack.is(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.asItem())) {
//            stack = blendAndSetLeatherColor(stack, colors, customColors);
//        }
        ItemStack itemStack = ItemStack.EMPTY;
        int n;
        int[] is = new int[3];
        int j = 0;
        DyeableLeatherItem dyeableItem = null;
        Item item = stack.getItem();
        if (item instanceof DyeableLeatherItem) {
            dyeableItem = (DyeableLeatherItem) ((Object) item);
            itemStack = stack.copyWithCount(1);
            if (dyeableItem.hasCustomColor(stack)) {
                int k = dyeableItem.getColor(itemStack);
                float f = (float) (k >> 16 & 0xFF) / 255.0f;
                float g = (float) (k >> 8 & 0xFF) / 255.0f;
                float h = (float) (k & 0xFF) / 255.0f;
                is[0] = is[0] + (int) (f * 255.0f * f * 255.0f);
                is[1] = is[1] + (int) (g * 255.0f * g * 255.0f);
                is[2] = is[2] + (int) (h * 255.0f * h * 255.0f);
                ++j;
            }
            for (DyeItem dyeItem : colors) {
                float[] fs = getColorArray(getMaterialType((Item) dyeableItem), dyeItem);
                int l = (int) (fs[0] * 255.0f * fs[0] * 255.0f);
                int m = (int) (fs[1] * 255.0f * fs[1] * 255.0f);
                n = (int) (fs[2] * 255.0f * fs[2] * 255.0f);
                is[0] = is[0] + l;
                is[1] = is[1] + m;
                is[2] = is[2] + n;
                ++j;
            }
            for (ItemStack customDyeItem : customColors) {
                float[] fs = getCustomColorArray(getMaterialType((Item) dyeableItem), customDyeItem);
                int l = (int) (fs[0] * 255.0f * fs[0] * 255.0f);
                int m = (int) (fs[1] * 255.0f * fs[1] * 255.0f);
                n = (int) (fs[2] * 255.0f * fs[2] * 255.0f);
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
        if(stack.getItem() instanceof CustomDyeItem){
            defineClosestVanillaDye(itemStack);
        }
        return itemStack;
    }


    public static ItemStack blendAndSetLeatherColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        ItemStack itemStack = stack.copyWithCount(1);
        int n;
        int[] is = new int[3];
        int j = 0;
//        if (((DyeableLeatherItem) stack.getItem()).hasCustomColor(stack)) {
//            int k = DyeableLeatheryBlockItem.getLeatherColor(itemStack);
//            float f = (float) (k >> 16 & 0xFF);
//            float g = (float) (k >> 8 & 0xFF);
//            float h = (float) (k & 0xFF);
//            is[0] = is[0] + (int) (f * f);
//            is[1] = is[1] + (int) (g * g);
//            is[2] = is[2] + (int) (h * h);
//            ++j;
//        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray("leather", dyeItem);
            int l = (int) (fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int) (fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int) (fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors) {
            float[] fs = getCustomColorArray("leather", customDye);
            int l = (int) (fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int) (fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int) (fs[2] * 255.0f * fs[2] * 255.0f);
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
//        DyeableLeatheryBlockItem.setLeatherColor(itemStack, n);
        return itemStack;
    }

    public static ItemStack blendAndSetCustomDyeColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        ItemStack itemStack = stack.copyWithCount(1);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "leather");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "wool");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "concrete");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "terracotta");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "glass");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "sign");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "firework");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "shulker_box");
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "candle");
        return itemStack;
    }

    public static void defineClosestVanillaDye(ItemStack itemStack) {
        float[] customColorArray = getCustomColorArray("dye", itemStack);
        double distance;
        double minDistance = -1;
        int id = -1;
        for (Map.Entry<Item, UnidyeColor> entry : DYES.entrySet()) {
            float[] colorArray = entry.getValue().getColorComponents("dye");
            distance = Math.pow(customColorArray[0] - colorArray[0], 2)
                    + Math.pow(customColorArray[1] - colorArray[1], 2) + Math.pow(customColorArray[2] - colorArray[2], 2);
            if (distance < minDistance || minDistance == -1) {
                minDistance = distance;
                id = entry.getValue().getId();
            }
        }
        itemStack.getOrCreateTag().putInt("closest_vanilla_dye_id", id);
    }


    public static void blendAndSetMaterialColor(ItemStack stack, ItemStack itemStack, List<DyeItem> colors,
                                                List<ItemStack> customColors, String materialType) {
        int n;
        int[] is = new int[3];
        int j = 0;
        if (UnidyeUtils.hasColor(stack)) {
            int k = getMaterialColor(itemStack, materialType);
            float f = (float) (k >> 16 & 0xFF);
            float g = (float) (k >> 8 & 0xFF);
            float h = (float) (k & 0xFF);
            is[0] = is[0] + (int) (f * f);
            is[1] = is[1] + (int) (g * g);
            is[2] = is[2] + (int) (h * h);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray(materialType, dyeItem);
            int l = (int) (fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int) (fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int) (fs[2] * 255.0f * fs[2] * 255.0f);
            is[0] = is[0] + l;
            is[1] = is[1] + m;
            is[2] = is[2] + n;
            ++j;
        }
        for (ItemStack customDye : customColors) {
            float[] fs = getCustomColorArray(materialType, customDye);
            int l = (int) (fs[0] * 255.0f * fs[0] * 255.0f);
            int m = (int) (fs[1] * 255.0f * fs[1] * 255.0f);
            n = (int) (fs[2] * 255.0f * fs[2] * 255.0f);
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
        setMaterialColor(itemStack, n, materialType);
    }

    public static float[] getColorArray(String materialType, Item dyeItem) {
        if(DYES.containsKey(dyeItem)) {
            return DYES.get(dyeItem).getColorComponents(materialType);
        }else{
            DyeColor dyeColor = ((DyeItem)dyeItem).getDyeColor();
            if(Objects.equals(materialType, "sign")){
                return getColorArray(dyeColor.getTextColor());
            } else if (Objects.equals(materialType, "firework")) {
                return getColorArray(dyeColor.getFireworkColor());
            } else {
                return dyeColor.getTextureDiffuseColors();
            }
        }
    }

    public static float[] getCustomColorArray(String materialType, ItemStack itemStack) {
        int color;
        if(Objects.equals(materialType, "dye")){
            color = getColor(itemStack);
        }else {
            color = getMaterialColor(itemStack, materialType);
        }
        int j = (color & 0xFF0000) >> 16;
        int k = (color & 0xFF00) >> 8;
        int l = (color & 0xFF) >> 0;
        return new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
    }
    public static String getMaterialType(Item dyeableItem) {
        return MATERIAL_TYPES.getOrDefault(dyeableItem, "leather");
    }

    public static float[] getColorArray(int n) {
        int j = (n & 0xFF0000) >> 16;
        int k = (n & 0xFF00) >> 8;
        int l = (n & 0xFF) >> 0;
        return new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
    }
}
