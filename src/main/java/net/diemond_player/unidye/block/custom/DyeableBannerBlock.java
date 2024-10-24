package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableBannerBlockEntity;
import net.diemond_player.unidye.block.entity.UnidyeBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DyeableBannerBlock extends BannerBlock {

    public DyeableBannerBlock(AbstractBlock.Settings settings) {
        super(DyeColor.CYAN, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableBannerBlockEntity(pos, state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.isClient) {
            world.getBlockEntity(pos, UnidyeBlockEntities.DYEABLE_BANNER_BE).ifPresent(blockEntity -> blockEntity.readFrom(itemStack));
        } else if (itemStack.hasCustomName()) {
            world.getBlockEntity(pos, UnidyeBlockEntities.DYEABLE_BANNER_BE).ifPresent(blockEntity -> blockEntity.setCustomName(itemStack.getName()));
        }
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DyeableBannerBlockEntity) {
            return ((DyeableBannerBlockEntity) blockEntity).getPickStack();
        }
        return super.getPickStack(world, pos, state);
    }
}
