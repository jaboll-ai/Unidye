package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.SignText;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SignText.class)
public abstract class SignTextMixin implements IEntityAccessor{
    @Unique
    private int customColor = 0xFFFFFF;

    @Override
    public int unidye$getCustomColor() {
        return customColor;
    }

    @Override
    public void unidye$setCustomColor(int customColor) {
        this.customColor = customColor;
    }

}
