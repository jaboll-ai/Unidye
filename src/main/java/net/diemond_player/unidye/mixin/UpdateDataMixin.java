package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.item.map.MapState;
import org.spongepowered.asm.mixin.*;

@Mixin(targets = "net.minecraft.item.map.MapState$UpdateData")
public abstract class UpdateDataMixin implements IEntityAccessor {
    @Unique
    public int[] customColors;
    @Mutable
    @Final
    @Shadow
    public final int startX;
    @Mutable
    @Final
    @Shadow
    public final int startZ;
    @Mutable
    @Final
    @Shadow
    public final int width;
    @Mutable
    @Final
    @Shadow
    public final int height;

    protected UpdateDataMixin(int startX, int startZ, int width, int height) {
        this.startX = startX;
        this.startZ = startZ;
        this.width = width;
        this.height = height;
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
    public void unidye$setCustomColorsTo(MapState mapState) {
        for (int i = 0; i < this.width; ++i) {
            for (int j = 0; j < this.height; ++j) {
                ((IEntityAccessor)mapState).unidye$putColor(this.startX + i, this.startZ + j, this.customColors[i + j * this.width]);
            }
        }
    }
}
