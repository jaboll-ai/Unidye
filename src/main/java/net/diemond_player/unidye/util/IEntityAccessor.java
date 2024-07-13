package net.diemond_player.unidye.util;


import net.minecraft.block.entity.SignText;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public interface IEntityAccessor {

    int unidye$getCustomColor();

    void unidye$setCustomColor(int customColor);

    int unidye$getCustomColorBack();

    void unidye$setCustomColorBack(int customColor);
}
