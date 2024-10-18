package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.util.CustomMapColor;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.entity.Entity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.map.MapState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FilledMapItem.class)
public abstract class FilledMapItemMixin {
    @Inject(method = "updateColors", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/map/MapState;removeBanner(Lnet/minecraft/world/BlockView;II)V"))
    public void updateColors(World world, Entity entity, MapState state, CallbackInfo ci, @Local(ordinal = 0) BlockState blockState, @Local(ordinal = 0) BlockPos.Mutable mutable, @Local(ordinal = 0) int o, @Local(ordinal = 1) int p) {
        if(blockState.isOf(UnidyeBlocks.CUSTOM_WOOL)){
            ((IEntityAccessor)state).unidye$putColor(o - 1, p - 1, DyeableBlockEntity.getColor(world, mutable));
        }
    }
    @Inject(method = "updateColors", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/map/MapState;putColor(IIB)Z"))
    public void updateColors2(World world, Entity entity, MapState state, CallbackInfo ci, @Local MapColor mapColor, @Local MapColor.Brightness brightness, @Local(ordinal = 0) int o, @Local(ordinal = 1) int p) {
        if(((IEntityAccessor)state).unidye$getCustomColor(128*(o-1) + p - 1) != 0) {
            ((IEntityAccessor)state).unidye$putColor(o,p,CustomMapColor.getRenderColor(brightness, ((IEntityAccessor)state).unidye$getCustomColor(128*o + p)));
        }
    }
}
