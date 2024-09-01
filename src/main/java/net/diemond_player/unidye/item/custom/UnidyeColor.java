package net.diemond_player.unidye.item.custom;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;
import org.jetbrains.annotations.Contract;

import java.util.Objects;
import java.util.function.IntFunction;

public enum UnidyeColor implements StringIdentifiable
{
    WHITE(0, "white", 0xF5F4FF, 0xF9FFFE, 0xe9ecec, 0xd1b2a1, 0xcfd5d6, 0xffffff),
    ORANGE(1, "orange", 0xEA8800, 16351261, 0xf07613, 0xa15325, 0xe06100, 0xd77f32),
    MAGENTA(2, "magenta", 0xCC28C3,13061821, 0xbd44b3, 0x95586c, 0xa9309f, 0xb24bd7),
    LIGHT_BLUE(3, "light_blue", 0x3287FF,3847130, 0x3aafd9, 0x716c89,0x2389c6, 0x6699d7),
    YELLOW(4, "yellow", 0xF7DA00,16701501, 0xf8c527, 0xba8523, 0xf0af15, 0xe5e532),
    LIME(5, "lime", 0x73D800,8439583, 0x70b919, 0x677534, 0x5ea818, 0x7fcc19),
    PINK(6, "pink", 0xEA4DA1,15961002, 0xF995B7, 0xa14e4e, 0xd5658e, 0xf27fa4),
    GRAY(7, "gray", 0x898989,4673362, 0x3e4447, 0x392a23, 0x36393d, 0x4b4b4b),
    LIGHT_GRAY(8, "light_gray", 0xD4D2DB,0x9D9D97, 0x8e8e86, 0x876a61, 0x7d7d73, 0x999999),
    CYAN(9, "cyan", 0x1183AF,1481884, 0x158991, 0x565b5b, 0x157788, 0x4b7f99),
    PURPLE(10, "purple", 0x851CCC,8991416, 0x792aac, 0x764656, 0x641f9c, 0x7f3fb2),
    BLUE(11, "blue", 0x0055FF,3949738, 0x35399d, 0x4a3b5b, 0x2c2e8f, 0x324bb2),
    BROWN(12, "brown", 0x773100,8606770, 0x724728, 0x4d3323, 0x603b1f, 0x664b32),
    GREEN(13, "green", 0x6FBA00,6192150, 0x546d1b, 0x4c532a, 0x495b24, 0x667f32),
    RED(14, "red", 0xFF0000,11546150, 0xa02722, 0x8f3d2e, 0x8e2020, 0x993232),
    BLACK(15, "black", 0x3E3759,0x1D1D21, 0x141519, 0x251610, 0x080a0f, 0x191919);

    private static final IntFunction<UnidyeColor> BY_ID;
    public static final StringIdentifiable.Codec<net.minecraft.util.DyeColor> CODEC;
    private final int id;
    private final String name;
    private final int dyeColor;
    private final int woolColor;
    private final int leatherColor;
    private final int terracottaColor;
    private final int concreteColor;
    private final int glassColor;
    private float[] colorComponents;

    private UnidyeColor(int id, String name, int dyeColor, int leatherColor, int woolColor, int terracottaColor, int concreteColor, int glassColor) {
        this.id = id;
        this.name = name;
        this.dyeColor = dyeColor;
        this.leatherColor = leatherColor;
        this.woolColor = woolColor;
        this.terracottaColor = terracottaColor;
        this.concreteColor = concreteColor;
        this.glassColor = glassColor;
        int j = (leatherColor & 0xFF0000) >> 16;
        int k = (leatherColor & 0xFF00) >> 8;
        int l = (leatherColor & 0xFF) >> 0;
        this.colorComponents = new float[]{(float)j, (float)k, (float)l};
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public float[] getColorComponents(String materialType) {
        if(Objects.equals(materialType, "wool")){
            int j = (woolColor & 0xFF0000) >> 16;
            int k = (woolColor & 0xFF00) >> 8;
            int l = (woolColor & 0xFF) >> 0;
            colorComponents = new float[]{(float)j / 255.0f, (float)k / 255.0f, (float)l / 255.0f};
        } else if(Objects.equals(materialType, "terracotta")){
            int j = (terracottaColor & 0xFF0000) >> 16;
            int k = (terracottaColor & 0xFF00) >> 8;
            int l = (terracottaColor & 0xFF) >> 0;
            colorComponents = new float[]{(float)j / 255.0f, (float)k / 255.0f, (float)l / 255.0f};
        } else if(Objects.equals(materialType, "concrete")){
            int j = (concreteColor & 0xFF0000) >> 16;
            int k = (concreteColor & 0xFF00) >> 8;
            int l = (concreteColor & 0xFF) >> 0;
            colorComponents = new float[]{(float)j / 255.0f, (float)k / 255.0f, (float)l / 255.0f};
        } else if(Objects.equals(materialType, "glass")){
            int j = (glassColor & 0xFF0000) >> 16;
            int k = (glassColor & 0xFF00) >> 8;
            int l = (glassColor & 0xFF) >> 0;
            colorComponents = new float[]{(float)j / 255.0f, (float)k / 255.0f, (float)l / 255.0f};
        } else if(Objects.equals(materialType, "dye")){
            int j = (dyeColor & 0xFF0000) >> 16;
            int k = (dyeColor & 0xFF00) >> 8;
            int l = (dyeColor & 0xFF) >> 0;
            colorComponents = new float[]{(float)j / 255.0f, (float)k / 255.0f, (float)l / 255.0f};
        } else {
            int j = (leatherColor & 0xFF0000) >> 16;
            int k = (leatherColor & 0xFF00) >> 8;
            int l = (leatherColor & 0xFF) >> 0;
            colorComponents = new float[]{(float)j / 255.0f, (float)k / 255.0f, (float)l / 255.0f};
        }
        return colorComponents;
    }

    public static UnidyeColor byId(int id) {
        return BY_ID.apply(id);
    }


    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

    static {
        BY_ID = ValueLists.createIdToValueFunction(UnidyeColor::getId, UnidyeColor.values(), ValueLists.OutOfBoundsHandling.ZERO);
        CODEC = StringIdentifiable.createCodec(net.minecraft.util.DyeColor::values);
    }
}
