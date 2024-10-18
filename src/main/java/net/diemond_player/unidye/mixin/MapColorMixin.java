package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.block.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(MapColor.class)
public abstract class MapColorMixin implements IEntityAccessor {
    @Unique
    public int customColor = 0x00FF00;

    protected MapColorMixin(int customColor) {
        this.customColor = customColor;
    }

    @Override
    public int unidye$getCustomColor() {
        return this.customColor;
    }

    @Override
    public void unidye$setCustomColor(int customColor) {
        this.customColor = customColor;
    }
}
