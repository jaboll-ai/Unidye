package net.diemond_player.unidye.util;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.DyeableGlassBlockItem;
import net.diemond_player.unidye.item.custom.DyeableWoolBlockItem;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.diemond_player.unidye.item.custom.CustomDyeItem.*;
import static net.minecraft.item.DyeableItem.COLOR_KEY;
import static net.minecraft.item.DyeableItem.DISPLAY_KEY;

public class UnidyeUtils {
    public static final Map<String, UnidyeColor> DYES = new HashMap<String, UnidyeColor>() {{
        put("white", UnidyeColor.WHITE);
        put("orange", UnidyeColor.ORANGE);
        put("yellow", UnidyeColor.YELLOW);
        put("light_gray", UnidyeColor.LIGHT_GRAY);
        put("cyan", UnidyeColor.CYAN);
        put("blue", UnidyeColor.BLUE);
        put("brown", UnidyeColor.BROWN);
        put("green", UnidyeColor.GREEN);
        put("red", UnidyeColor.RED);
        put("black", UnidyeColor.BLACK);
        put("light_blue", UnidyeColor.LIGHT_BLUE);
        put("magenta", UnidyeColor.MAGENTA);
        put("lime", UnidyeColor.LIME);
        put("pink", UnidyeColor.PINK);
        put("gray", UnidyeColor.GRAY);
        put("purple", UnidyeColor.PURPLE);
    }};
    public static void setColor(ItemStack stack, int color) {
        stack.getOrCreateSubNbt(DISPLAY_KEY).putInt(COLOR_KEY, color);
    }

    public static int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(COLOR_KEY);
        }
        return DEFAULT_COLOR;
    }

    public static ItemStack blendAndSetColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        if (stack.getItem() instanceof CustomDyeItem) {
            return blendAndSetCustomDyeColor(stack, colors, customColors);
        }
        if (stack.isOf(UnidyeBlocks.CUSTOM_WOOL.asItem())) {
            return blendAndSetCustomWoolColor(stack, colors, customColors);
        }
        if (stack.isOf(UnidyeBlocks.CUSTOM_STAINED_GLASS.asItem()) || stack.isOf(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.asItem())) {
            return blendAndSetCustomGlassColor(stack, colors, customColors);
        }
        ItemStack itemStack = ItemStack.EMPTY;
        int n;
        int[] is = new int[3];
        int j = 0;
        DyeableItem dyeableItem = null;
        Item item = stack.getItem();
        if (item instanceof DyeableItem) {
            dyeableItem = (DyeableItem) ((Object) item);
            itemStack = stack.copyWithCount(1);
            if (dyeableItem.hasColor(stack)) {
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
                float[] fs = getColorArray(getMaterialType(dyeableItem), getDyeType(dyeItem));
                int l = (int) (fs[0] * 255.0f * fs[0] * 255.0f);
                int m = (int) (fs[1] * 255.0f * fs[1] * 255.0f);
                n = (int) (fs[2] * 255.0f * fs[2] * 255.0f);
                is[0] = is[0] + l;
                is[1] = is[1] + m;
                is[2] = is[2] + n;
                ++j;
            }
            for (ItemStack customDyeItem : customColors) {
                float[] fs = getCustomColorArray(getMaterialType(dyeableItem), customDyeItem);
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
        return itemStack;
    }

    public static ItemStack blendAndSetCustomGlassColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        ItemStack itemStack = stack.copyWithCount(1);
        blendAndSetMaterialColor2(stack, itemStack, colors, customColors, "leather");
        blendAndSetMaterialColor2(stack, itemStack, colors, customColors, "glass");
        return itemStack;
    }

    public static ItemStack blendAndSetCustomWoolColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        ItemStack itemStack = stack.copyWithCount(1);
        blendAndSetMaterialColor1(stack, itemStack, colors, customColors, "leather");
        blendAndSetMaterialColor1(stack, itemStack, colors, customColors, "wool");
        return itemStack;
    }

    public static void blendAndSetMaterialColor1(ItemStack stack, ItemStack itemStack, List<DyeItem> colors, List<ItemStack> customColors, String materialType) {
        int n;
        int[] is = new int[3];
        int j = 0;
        if (((DyeableItem) stack.getItem()).hasColor(stack)) {
            int k = DyeableWoolBlockItem.getMaterialColor(itemStack, materialType);
            float f = (float) (k >> 16 & 0xFF);
            float g = (float) (k >> 8 & 0xFF);
            float h = (float) (k & 0xFF);
            is[0] = is[0] + (int) (f * f);
            is[1] = is[1] + (int) (g * g);
            is[2] = is[2] + (int) (h * h);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray(materialType, getDyeType(dyeItem));
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
        if (Objects.equals(materialType, "wool")) {
            ((DyeableItem) stack.getItem()).setColor(itemStack, n);
        } else {
            DyeableWoolBlockItem.setMaterialColor(itemStack, n, materialType);
        }
    }

    public static void blendAndSetMaterialColor2(ItemStack stack, ItemStack itemStack, List<DyeItem> colors, List<ItemStack> customColors, String materialType) {
        int n;
        int[] is = new int[3];
        int j = 0;
        if (((DyeableItem) stack.getItem()).hasColor(stack)) {
            int k = DyeableGlassBlockItem.getMaterialColor(itemStack, materialType);
            float f = (float) (k >> 16 & 0xFF);
            float g = (float) (k >> 8 & 0xFF);
            float h = (float) (k & 0xFF);
            is[0] = is[0] + (int) (f * f);
            is[1] = is[1] + (int) (g * g);
            is[2] = is[2] + (int) (h * h);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray(materialType, getDyeType(dyeItem));
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
        if (Objects.equals(materialType, "glass")) {
            ((DyeableItem) stack.getItem()).setColor(itemStack, n);
        } else {
            DyeableGlassBlockItem.setBeaconColor(itemStack, n);
        }
    }

    public static ItemStack blendAndSetCustomDyeColor(ItemStack stack, List<DyeItem> colors, List<ItemStack> customColors) {
        Item item = stack.getItem();
        CustomDyeItem customDyeItem = (CustomDyeItem) ((Object) item);
        ItemStack itemStack = stack.copyWithCount(1);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "leather", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "wool", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "dye", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "concrete", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "terracotta", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "glass", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "sign", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "firework", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "shulker_box", customDyeItem);
        blendAndSetMaterialColor(stack, itemStack, colors, customColors, "candle", customDyeItem);
        defineClosestVanillaDye(itemStack);
        return itemStack;
    }

    public static void defineClosestVanillaDye(ItemStack itemStack) {
        float[] customColorArray = getCustomColorArray("dye", itemStack);
        double distance;
        double minDistance = -1;
        int id = -1;
        for (Map.Entry<String, UnidyeColor> entry : DYES.entrySet()) {
            float[] colorArray = entry.getValue().getColorComponents("dye");
            distance = Math.pow(customColorArray[0] - colorArray[0], 2)
                    + Math.pow(customColorArray[1] - colorArray[1], 2) + Math.pow(customColorArray[2] - colorArray[2], 2);
            if (distance < minDistance || minDistance == -1) {
                minDistance = distance;
                id = entry.getValue().getId();
            }
        }
        itemStack.getOrCreateSubNbt("closest_vanilla_dye").putInt("closest_vanilla_dye_id", id);
    }


    public static void blendAndSetMaterialColor(ItemStack stack, ItemStack itemStack, List<DyeItem> colors,
                                         List<ItemStack> customColors, String materialType, CustomDyeItem customDyeItem) {
        int n;
        int[] is = new int[3];
        int j = 0;
        if (customDyeItem.hasColor(stack)) {
            int k = customDyeItem.getMaterialColor(itemStack, materialType);
            float f = (float) (k >> 16 & 0xFF);
            float g = (float) (k >> 8 & 0xFF);
            float h = (float) (k & 0xFF);
            is[0] = is[0] + (int) (f * f);
            is[1] = is[1] + (int) (g * g);
            is[2] = is[2] + (int) (h * h);
            ++j;
        }
        for (DyeItem dyeItem : colors) {
            float[] fs = getColorArray(materialType, getDyeType(dyeItem));
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
        customDyeItem.setMaterialColor(itemStack, n, materialType);
    }

    public static String getDyeType(DyeItem dyeItem) {
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
        } else {
            return "unknown";
        }
    }

    public static String getMaterialType(DyeableItem dyeableItem) {
        if (dyeableItem == UnidyeBlocks.CUSTOM_CONCRETE.asItem()
                || dyeableItem == UnidyeBlocks.CUSTOM_CONCRETE_POWDER.asItem()) {
            return "concrete";
        } else if (dyeableItem == UnidyeBlocks.CUSTOM_WOOL.asItem()
                || dyeableItem == UnidyeBlocks.CUSTOM_CARPET.asItem()) {
            return "wool";
        } else if (dyeableItem == UnidyeBlocks.CUSTOM_TERRACOTTA.asItem()) {
            return "terracotta";
        } else if (dyeableItem == UnidyeBlocks.CUSTOM_STAINED_GLASS.asItem()
                || dyeableItem == UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.asItem()) {
            return "glass";
        } else if (dyeableItem == UnidyeItems.CUSTOM_DYE) {
            return "dye";
        } else if (dyeableItem == UnidyeBlocks.CUSTOM_SHULKER_BOX.asItem()) {
            return "shulker_box";
        } else if (dyeableItem == UnidyeBlocks.CUSTOM_CANDLE.asItem()) {
            return "candle";
        } else {
            return "leather";
        }
    }

    public static float[] getColorArray(String materialType, String dyeType) {
        return DYES.get(dyeType).getColorComponents(materialType);
    }

    public static float[] getCustomColorArray(String materialType, ItemStack itemStack) {
        CustomDyeItem customDyeItem = (CustomDyeItem) itemStack.getItem();
        int color = customDyeItem.getMaterialColor(itemStack, materialType);
        int j = (color & 0xFF0000) >> 16;
        int k = (color & 0xFF00) >> 8;
        int l = (color & 0xFF) >> 0;
        return new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
    }
}
