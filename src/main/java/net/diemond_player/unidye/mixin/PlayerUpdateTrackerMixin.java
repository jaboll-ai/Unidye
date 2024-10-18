package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.item.map.MapState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.item.map.MapState$PlayerUpdateTracker")
public abstract class PlayerUpdateTrackerMixin {
    @ModifyReturnValue(method = "getMapUpdateData", at=@At("TAIL"))
    private MapState.UpdateData getMapUpdateData(MapState.UpdateData original,  @Local(ordinal=0) int i, @Local(ordinal=1) int j, @Local(ordinal=2) int k, @Local(ordinal=3) int l) {
//        int[] bs = new int[k * l];
//        for (int m = 0; m < k; ++m) {
//            for (int n = 0; n < l; ++n) {
//                bs[m + n * k] = ((IEntityAccessor)(MapState.((MapState.PlayerUpdateTracker)((Object)this))).unidye$getCustomColor(i + m + (j + n) * 128);
//            }
//        }
//        ((IEntityAccessor)original).unidye$setCustomColorArray(bs);
        return original;
    }
}
