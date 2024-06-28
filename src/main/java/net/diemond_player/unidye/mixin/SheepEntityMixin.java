package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin implements IEntityAccessor {
    @Unique
    private static final TrackedData<Integer> CUSTOM_COLOR = DataTracker.registerData(SheepEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("unidye.custom_color", unidye$getCustomColor());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("unidye.custom_color")) {
            unidye$setCustomColor(nbt.getInt("unidye.custom_color"));
        }
    }

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    private void initDataTracker(CallbackInfo ci){
        ((SheepEntity) (Object) this).getDataTracker().startTracking(CUSTOM_COLOR, 0xFFFFFF);
    }

    @Override
    public int unidye$getCustomColor() {
        return ((SheepEntity) (Object) this).getDataTracker().get(CUSTOM_COLOR);
    }

    @Override
    public void unidye$setCustomColor(int color) {
        ((SheepEntity) (Object) this).getDataTracker().set(CUSTOM_COLOR, color);
    }
}