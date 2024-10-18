package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.block.MapColor;
import net.minecraft.item.map.MapState;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapState.class)
public abstract class MapStateMixin implements IEntityAccessor {
    @Unique
    private int[] customColors = new int[16384];
    @Shadow
    private void markDirty(int x, int z){

    }
    @Override
    public int unidye$getCustomColor(int n) {
        return this.customColors[n];
    }

    @Override
    public void unidye$setCustomColor(int n, int customColor) {
        this.customColors[n] = customColor;
    }

    @Override
    public int[] unidye$getCustomColorArray() {
        return this.customColors;
    }

    @Override
    public void unidye$setCustomColorArray(int[] customColorArray) {
        this.customColors = customColorArray;
    }
    @Override
    public boolean unidye$putColor(int x, int z, int customColor) {
        int b = this.customColors[x + z * 128];
        if (b != customColor) {
            this.unidye$setColor(x, z, customColor);
            return true;
        }
        return false;
    }
    @Override
    public void unidye$setColor(int x, int z, int customColor) {
        this.customColors[x + z * 128] = customColor;
        this.markDirty(x, z);
    }

    @Inject(method = "writeNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putByte(Ljava/lang/String;B)V"))
    public void updateColors1(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        nbt.putIntArray("customColors", this.customColors);
    }
    @Inject(method = "fromNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;getList(Ljava/lang/String;I)Lnet/minecraft/nbt/NbtList;"))
    private static void updateColors(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir, @Local MapState mapState) {
        int[] ints = nbt.getIntArray("customColors");
        if(ints.length == 16384){
            ((IEntityAccessor)mapState).unidye$setCustomColorArray(ints);
        }
    }
    @Inject(method = "copy", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/map/MapState;markDirty()V"))
    public void updateColors1(CallbackInfoReturnable<MapState> cir, @Local(ordinal = 1) MapState mapState) {
        System.arraycopy(this.customColors, 0, ((IEntityAccessor)mapState).unidye$getCustomColorArray(), 0, this.customColors.length);
    }

}
