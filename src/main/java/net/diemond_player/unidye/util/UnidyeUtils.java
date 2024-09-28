package net.diemond_player.unidye.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import static net.diemond_player.unidye.item.custom.CustomDyeItem.*;
import static net.minecraft.item.DyeableItem.COLOR_KEY;
import static net.minecraft.item.DyeableItem.DISPLAY_KEY;

public class UnidyeUtils {
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

    public static Integer getMaterialColor(ItemStack stack, String materialType) {
        NbtCompound nbtCompound;
        switch(materialType){
            case "wool":
                nbtCompound = stack.getSubNbt(WOOL_KEY);
                if (nbtCompound != null && nbtCompound.contains(WOOL_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(WOOL_COLOR_KEY);
                }
                break;
            case "concrete":
                nbtCompound = stack.getSubNbt(CONCRETE_KEY);
                if (nbtCompound != null && nbtCompound.contains(CONCRETE_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(CONCRETE_COLOR_KEY);
                }
                break;
            case "glass":
                nbtCompound = stack.getSubNbt(GLASS_KEY);
                if (nbtCompound != null && nbtCompound.contains(GLASS_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(GLASS_COLOR_KEY);
                }
                break;
            case "terracotta":
                nbtCompound = stack.getSubNbt(TERRACOTTA_KEY);
                if (nbtCompound != null && nbtCompound.contains(TERRACOTTA_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(TERRACOTTA_COLOR_KEY);
                }
                break;
            case "leather":
                nbtCompound = stack.getSubNbt(LEATHER_KEY);
                if (nbtCompound != null && nbtCompound.contains(LEATHER_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(LEATHER_COLOR_KEY);
                }
                break;
            case "dye":
                nbtCompound = stack.getSubNbt(DISPLAY_KEY);
                if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(COLOR_KEY);
                }
                break;
        }
        return DEFAULT_COLOR;
    }
}
