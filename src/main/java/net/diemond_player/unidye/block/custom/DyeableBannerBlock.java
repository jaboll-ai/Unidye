package net.diemond_player.unidye.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.block.Block;

public class DyeableBannerBlock extends BannerBlock {
    private static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);


    public DyeableBannerBlock(DyeColor pColor, Properties pProperties) {
        super(pColor, pProperties);
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return super.rotate(pState, pRotation);
    }

//    @Override
//    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
//        return new DyeableBannerBlockEntity(pPos, pState);
//    }
//
//    @Override
//    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
//        BlockEntity blockEntity = level.getBlockEntity(pos);
//        if (blockEntity instanceof DyeableBannerBlockEntity dyeablebannerbe) {
//            return dyeablebannerbe.getCloneItemStack();
//        }
//        return super.getCloneItemStack(level, pos, state);
//    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }



}
