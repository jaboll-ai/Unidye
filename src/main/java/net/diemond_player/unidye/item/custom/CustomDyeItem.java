package net.diemond_player.unidye.item.custom;

import net.minecraft.item.DyeItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.DyeColor;

public class CustomDyeItem extends Item implements DyeableItem {
    public static final String WOOL_KEY = "wool";
    public static final String WOOL_COLOR_KEY = "wool_color";
    public static final String CONCRETE_KEY = "concrete";
    public static final String CONCRETE_COLOR_KEY = "concrete_color";
    public static final String TERRACOTTA_KEY = "terracotta";
    public static final String TERRACOTTA_COLOR_KEY = "terracotta_color";
    public static final String GLASS_KEY = "glass";
    public static final String GLASS_COLOR_KEY = "glass_color";
    public static final String LEATHER_KEY = "leather";
    public static final String LEATHER_COLOR_KEY = "leather_color";
    public static final int DEFAULT_COLOR = 16777215;


    public CustomDyeItem(Settings settings) {
        super(settings);
    }


    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(COLOR_KEY);
        }
        return DEFAULT_COLOR;
    }

    public int getMaterialColor(ItemStack stack, String materialType) {
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

    public void setMaterialColor(ItemStack itemStack, int n, String materialType) {
        switch (materialType){
            case "wool": itemStack.getOrCreateSubNbt(WOOL_KEY).putInt(WOOL_COLOR_KEY, n); break;
            case "glass": itemStack.getOrCreateSubNbt(GLASS_KEY).putInt(GLASS_COLOR_KEY, n); break;
            case "concrete": itemStack.getOrCreateSubNbt(CONCRETE_KEY).putInt(CONCRETE_COLOR_KEY, n); break;
            case "terracotta": itemStack.getOrCreateSubNbt(TERRACOTTA_KEY).putInt(TERRACOTTA_COLOR_KEY, n); break;
            case "leather": itemStack.getOrCreateSubNbt(LEATHER_KEY).putInt(LEATHER_COLOR_KEY, n); break;
            case "dye": itemStack.getOrCreateSubNbt(DISPLAY_KEY).putInt(COLOR_KEY, n); break;
        }
    }
}