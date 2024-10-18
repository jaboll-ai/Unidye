package net.diemond_player.unidye.util;


import net.minecraft.block.entity.SignText;
import net.minecraft.item.map.MapState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public interface IEntityAccessor {

    int unidye$getCustomColor();

    void unidye$setCustomColor(int customColor);

    int unidye$getCustomColor(int n);

    void unidye$setCustomColor(int n,int customColor);
    int[] unidye$getCustomColorArray();

    void unidye$setCustomColorArray(int[] customColorArray);

    int unidye$getCustomColorBack();

    void unidye$setCustomColorBack(int customColor);
    boolean unidye$putColor(int x, int z, int customColor);
    void unidye$setColor(int x, int z, int customColor);

    void unidye$setCustomColorsTo(MapState mapState);
}
