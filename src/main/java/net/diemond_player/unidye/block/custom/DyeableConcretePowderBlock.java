package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.block.entity.ModBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DyeableConcretePowderBlock extends ConcretePowderBlock implements IDyeableBlock {

    public DyeableConcretePowderBlock(Block hardened, Settings settings) {
        super(hardened, settings);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        if (DyeableBlockEntity.getColor(world, pos) != DyeableBlockEntity.DEFAULT_COLOR) {
            ItemStack stack = super.getPickStack(world, pos, state);
            return pickBlock(world,pos,stack);
        } else {
            return new ItemStack(this);
        }
    }

    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        DyeableBlockEntity blockEntity = ModBlockEntities.DYEABLE_BE.get(world,pos);
        return blockEntity.color;
    }
}
