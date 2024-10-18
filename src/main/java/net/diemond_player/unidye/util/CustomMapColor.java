package net.diemond_player.unidye.util;

import net.minecraft.block.MapColor;

public class CustomMapColor{
    public final int color;
    public final int id;

    public CustomMapColor(int id, int color) {
        this.id = id;
        this.color = color;
    }
    public static byte getRenderColor(MapColor.Brightness brightness, int color) {
        int i = brightness.brightness;
        int j = (color >> 16 & 0xFF) * i/255;
        int k = (color >> 8 & 0xFF) * i/255;
        int l = (color & 0xFF)* i/255;
        return (byte) (0xFF000000 | l << 16 | k << 8 | j);
    }
}
