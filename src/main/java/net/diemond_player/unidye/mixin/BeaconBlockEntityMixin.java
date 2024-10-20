package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.block.custom.DyeableGlassBlock;
import net.diemond_player.unidye.block.custom.DyeablePaneBlock;
import net.diemond_player.unidye.block.entity.UnidyeBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin{
    @ModifyVariable(method = "tick", at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/util/DyeColor;getColorComponents()[F"), ordinal = 0)
    private static float[] tick(float[] fs, World world, BlockPos BlockPos, BlockState state, BeaconBlockEntity blockEntity, @Local(ordinal = 1) BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        float[] fsI = new float[3];
        if(block instanceof DyeableGlassBlock || block instanceof DyeablePaneBlock){
            if(UnidyeBlockEntities.DYEABLE_GLASS_BE.get(world,pos) != null) {
                int color = UnidyeBlockEntities.DYEABLE_GLASS_BE.get(world, pos).beaconColor;
                fsI[0] = ((color & 0xFF0000) >> 16) / 255.0f;
                fsI[1] = ((color & 0xFF00) >> 8) / 255.0f;
                fsI[2] = (color & 0xFF) / 255.0f;
                return fsI;
            }
        }
        return ((Stainable)((Object)block)).getColor().getColorComponents();
    }
}
