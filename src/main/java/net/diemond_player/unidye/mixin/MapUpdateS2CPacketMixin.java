package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.item.map.MapState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.MapUpdateS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapUpdateS2CPacket.class)
public abstract class MapUpdateS2CPacketMixin {
    @Mutable
    @Final
    @Shadow
    private final MapState.UpdateData updateData;

    protected MapUpdateS2CPacketMixin(MapState.UpdateData updateData) {
        this.updateData = updateData;
    }

    @Inject(method = "write", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/PacketByteBuf;writeByteArray([B)Lnet/minecraft/network/PacketByteBuf;"))
    public void updateColors1(PacketByteBuf buf, CallbackInfo ci) {
        buf.writeIntArray(((IEntityAccessor)this.updateData).unidye$getCustomColorArray());
    }
    @Inject(method = "apply(Lnet/minecraft/item/map/MapState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/map/MapState$UpdateData;setColorsTo(Lnet/minecraft/item/map/MapState;)V"))
    public void updateColors1(MapState mapState, CallbackInfo ci) {
        ((IEntityAccessor)this.updateData).unidye$setCustomColorsTo(mapState);
    }
}
