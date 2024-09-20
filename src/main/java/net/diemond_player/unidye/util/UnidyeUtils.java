package net.diemond_player.unidye.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import static net.diemond_player.unidye.item.custom.CustomDyeItem.DEFAULT_COLOR;
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
}
