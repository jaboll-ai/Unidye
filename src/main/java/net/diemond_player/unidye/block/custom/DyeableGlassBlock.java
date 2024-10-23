package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableGlassBlockEntity;
import net.diemond_player.unidye.block.entity.UnidyeBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.Stainable;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import static net.diemond_player.unidye.block.entity.DyeableWoolBlockEntity.DEFAULT_COLOR;

public class DyeableGlassBlock extends GlassBlock implements IDyeableBlock, Stainable {
    public DyeableGlassBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        if (DyeableGlassBlockEntity.getColor(world, pos) != DyeableGlassBlockEntity.DEFAULT_COLOR) {
            ItemStack stack = super.getPickStack(world, pos, state);
            return pickBlock(world, pos, stack);
        } else {
            return new ItemStack(this);
        }
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.CYAN;
    }

    @Override
    public ItemStack pickBlock(BlockView world, BlockPos pos, ItemStack stack) {
        DyeableGlassBlockEntity blockEntity = UnidyeBlockEntities.DYEABLE_GLASS_BE.get(world, pos);
        int color = DEFAULT_COLOR;
        int beaconColor = DEFAULT_COLOR;
        if (blockEntity != null) {
            color = blockEntity.color;
            beaconColor = blockEntity.beaconColor;
        }
        NbtCompound subNbt = stack.getOrCreateSubNbt("display");
        subNbt.putInt("color", color);
        NbtCompound subNbt2 = stack.getOrCreateSubNbt("1");
        subNbt2.putInt("beaconColor", beaconColor);
        return stack;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableGlassBlockEntity(pos, state);
    }
}
