package net.diemond_player.unidye.util;


import net.minecraft.util.ByIdMap;
import net.minecraft.world.item.DyeColor;

import java.util.Objects;
import java.util.function.IntFunction;

public enum UnidyeColor {
    WHITE(0, "white", 0xF5F4FF, 0xF9FFFE, 0xe9ecec, 0xFFDAC6, 0xcfd5d6, 0xffffff, DyeColor.WHITE.getFireworkColor(), DyeColor.WHITE.getTextColor(), 0xEAEDED, 0xFFFFFF),
    ORANGE(1, "orange", 0xEA8800, 16351261, 0xf07613, 0xC66831, 0xe06100, 0xd77f32, DyeColor.ORANGE.getFireworkColor(), DyeColor.ORANGE.getTextColor(), 0xF77111, 0xFF9929),
    MAGENTA(2, "magenta", 0xCC28C3, 13061821, 0xbd44b3, 0xC4758F, 0xa9309f, 0xb24bd7, DyeColor.MAGENTA.getFireworkColor(), DyeColor.MAGENTA.getTextColor(), 0xBA3FAF, 0xC544B4),
    LIGHT_BLUE(3, "light_blue", 0x3287FF, 3847130, 0x3aafd9, 0x918EAF, 0x2389c6, 0x6699d7, DyeColor.LIGHT_BLUE.getFireworkColor(), DyeColor.LIGHT_BLUE.getTextColor(), 0x3BB8DD, 0x31AEDE),
    YELLOW(4, "yellow", 0xF7DA00, 16701501, 0xf8c527, 0xE5A330, 0xf0af15, 0xe5e532, DyeColor.YELLOW.getFireworkColor(), DyeColor.YELLOW.getTextColor(), 0xFFC826, 0xFFDA39),
    LIME(5, "lime", 0x73D800, 8439583, 0x70b919, 0x839144, 0x5ea818, 0x7fcc19, DyeColor.LIME.getFireworkColor(), DyeColor.LIME.getTextColor(), 0x72BC18, 0x8BCE29),
    PINK(6, "pink", 0xEA4DA1, 15961002, 0xF38AAA, 0xC66161, 0xd5658e, 0xf27fa4, DyeColor.PINK.getFireworkColor(), DyeColor.PINK.getTextColor(), 0xF48BAB, 0xF699B4),
    GRAY(7, "gray", 0x898989, 4673362, 0x3e4447, 0x49352D, 0x36393d, 0x4b4b4b, DyeColor.GRAY.getFireworkColor(), DyeColor.GRAY.getTextColor(), 0x3F4447, 0x6A757B),
    LIGHT_GRAY(8, "light_gray", 0xD4D2DB, 0x9D9D97, 0x8e8e86, 0xAF8B7E, 0x7d7d73, 0x999999, DyeColor.LIGHT_GRAY.getFireworkColor(), DyeColor.LIGHT_GRAY.getTextColor(), 0x8C8C83, 0xA3A09A),
    CYAN(9, "cyan", 0x1183AF, 1481884, 0x17949B, 0x707272, 0x157788, 0x4b7f99, DyeColor.CYAN.getFireworkColor(), DyeColor.CYAN.getTextColor(), 0x178791, 0x16ABAA),
    PURPLE(10, "purple", 0x851CCC, 8991416, 0x792aac, 0x96586D, 0x641f9c, 0x7f3fb2, DyeColor.PURPLE.getFireworkColor(), DyeColor.PURPLE.getTextColor(), 0x7226A8, 0x8B30BD),
    BLUE(11, "blue", 0x0055FF, 3949738, 0x3D41AF, 0x614F75, 0x2c2e8f, 0x324bb2, DyeColor.BLUE.getFireworkColor(), DyeColor.BLUE.getTextColor(), 0x33369B, 0x406FC2),
    BROWN(12, "brown", 0x773100, 8606770, 0x7A4E2F, 0x664430, 0x603b1f, 0x664b32, DyeColor.BROWN.getFireworkColor(), DyeColor.BROWN.getTextColor(), 0x754829, 0x8F5B35),
    GREEN(13, "green", 0x6FBA00, 6192150, 0x546d1b, 0x626B38, 0x495b24, 0x667f32, DyeColor.GREEN.getFireworkColor(), DyeColor.GREEN.getTextColor(), 0x556D1D, 0x658718),
    RED(14, "red", 0xFF0000, 11546150, 0xa02722, 0xB24C3C, 0x8e2020, 0x993232, DyeColor.RED.getFireworkColor(), DyeColor.RED.getTextColor(), 0x9B2523, 0xBF3B33),
    BLACK(15, "black", 0x565656, 0x1D1D21, 0x252529, 0x301E16, 0x080a0f, 0x191919, DyeColor.BLACK.getFireworkColor(), DyeColor.BLACK.getTextColor(), 0x1F1F23, 0x31304C);

    private static final IntFunction<UnidyeColor> BY_ID;
    private final int id;
    private final String name;
    private final int dyeColor;
    private final int woolColor;
    private final int leatherColor;
    private final int terracottaColor;
    private final int concreteColor;
    private final int glassColor;
    private final int fireworkColor;
    private final int signColor;
    private final int shulkerBoxColor;
    private final int candleColor;
    private float[] colorComponents;

    private UnidyeColor(int id, String name, int dyeColor, int leatherColor, int woolColor, int terracottaColor, int concreteColor, int glassColor, int fireworkColor, int signColor, int shulkerBoxColor, int candleColor) {
        this.id = id;
        this.name = name;
        this.dyeColor = dyeColor;
        this.leatherColor = leatherColor;
        this.woolColor = woolColor;
        this.terracottaColor = terracottaColor;
        this.concreteColor = concreteColor;
        this.glassColor = glassColor;
        this.fireworkColor = fireworkColor;
        this.signColor = signColor;
        this.shulkerBoxColor = shulkerBoxColor;
        this.candleColor = candleColor;
        int j = (leatherColor & 0xFF0000) >> 16;
        int k = (leatherColor & 0xFF00) >> 8;
        int l = (leatherColor & 0xFF) >> 0;
        this.colorComponents = new float[]{(float) j, (float) k, (float) l};
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float[] getColorComponents(String materialType) {
        if (Objects.equals(materialType, "wool")) {
            int j = (woolColor & 0xFF0000) >> 16;
            int k = (woolColor & 0xFF00) >> 8;
            int l = (woolColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "terracotta")) {
            int j = (terracottaColor & 0xFF0000) >> 16;
            int k = (terracottaColor & 0xFF00) >> 8;
            int l = (terracottaColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "concrete") || Objects.equals(materialType, "bed")) {
            int j = (concreteColor & 0xFF0000) >> 16;
            int k = (concreteColor & 0xFF00) >> 8;
            int l = (concreteColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "glass")) {
            int j = (glassColor & 0xFF0000) >> 16;
            int k = (glassColor & 0xFF00) >> 8;
            int l = (glassColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "dye")) {
            int j = (dyeColor & 0xFF0000) >> 16;
            int k = (dyeColor & 0xFF00) >> 8;
            int l = (dyeColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "sign")) {
            int j = (signColor & 0xFF0000) >> 16;
            int k = (signColor & 0xFF00) >> 8;
            int l = (signColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "firework")) {
            int j = (fireworkColor & 0xFF0000) >> 16;
            int k = (fireworkColor & 0xFF00) >> 8;
            int l = (fireworkColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "shulker_box")) {
            int j = (shulkerBoxColor & 0xFF0000) >> 16;
            int k = (shulkerBoxColor & 0xFF00) >> 8;
            int l = (shulkerBoxColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else if (Objects.equals(materialType, "candle")) {
            int j = (candleColor & 0xFF0000) >> 16;
            int k = (candleColor & 0xFF00) >> 8;
            int l = (candleColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        } else {
            int j = (leatherColor & 0xFF0000) >> 16;
            int k = (leatherColor & 0xFF00) >> 8;
            int l = (leatherColor & 0xFF) >> 0;
            colorComponents = new float[]{(float) j / 255.0f, (float) k / 255.0f, (float) l / 255.0f};
        }
        return colorComponents;
    }

    public static UnidyeColor byId(int id) {
        return BY_ID.apply(id);
    }

    public String toString() {
        return this.name;
    }

    static {
        BY_ID = ByIdMap.continuous(UnidyeColor::getId, UnidyeColor.values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    }
}
