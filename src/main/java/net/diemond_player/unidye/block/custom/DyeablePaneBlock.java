package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableLeatheryBlockEntity;
import net.diemond_player.unidye.block.entity.UnidyeBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import static net.diemond_player.unidye.block.entity.DyeableLeatheryBlockEntity.DEFAULT_COLOR;

public class DyeablePaneBlock extends IronBarsBlock implements IDyeableBlock, BeaconBeamBlock {

    public DyeablePaneBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        if (DyeableLeatheryBlockEntity.getColor(level, pos) != DyeableLeatheryBlockEntity.DEFAULT_COLOR) {
            return pickBlock(level, pos, new ItemStack(this));
        } else {
            return new ItemStack(this);
        }
    }

    @Override
    public ItemStack pickBlock(BlockGetter level, BlockPos pos, ItemStack stack) {
        DyeableLeatheryBlockEntity blockEntity = UnidyeBlockEntities.DYEABLE_LEATHERY_BE.get().getBlockEntity(level, pos);
        int color = DEFAULT_COLOR;
        int beaconColor = DEFAULT_COLOR;
        if (blockEntity != null) {
            color = blockEntity.color;
            beaconColor = blockEntity.leatherColor;
        }
        CompoundTag subNbt = stack.getOrCreateTagElement("display");
        subNbt.putInt("color", color);
        if(stack.getTag()!=null) {
            stack.getTag().putInt("leather", beaconColor);
        }
        return stack;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DyeableLeatheryBlockEntity(pPos, pState);
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.CYAN;
    }
}
