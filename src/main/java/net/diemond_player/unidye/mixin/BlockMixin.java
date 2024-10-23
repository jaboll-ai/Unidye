package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableGlassBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "shouldDrawSide", at = @At(value = "HEAD"), cancellable = true)
    private static void render(BlockState state, BlockView world, BlockPos pos, Direction side, BlockPos otherPos, CallbackInfoReturnable<Boolean> cir) {
        BlockState blockState = world.getBlockState(otherPos);
        if (state.isOf(UnidyeBlocks.CUSTOM_STAINED_GLASS) && blockState.isOf(UnidyeBlocks.CUSTOM_STAINED_GLASS)) {
            if (DyeableGlassBlockEntity.getColor(world, pos) != DyeableGlassBlockEntity.getColor(world, otherPos)) {
                cir.setReturnValue(true);
            }
        }
        if (state.isOf(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE) && blockState.isOf(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE)) {
            if (DyeableGlassBlockEntity.getColor(world, pos) != DyeableGlassBlockEntity.getColor(world, otherPos)) {
                cir.setReturnValue(true);
            }
        }
    }
}
