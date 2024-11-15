package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

public class DyeableCarpetBlock extends CarpetBlock implements IDyeableBlock {
    public DyeableCarpetBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        if (DyeableBlockEntity.getColor(level, pos) != DyeableBlockEntity.DEFAULT_COLOR) {
            ItemStack stack = super.getCloneItemStack(state, target, level, pos, player);
            return pickBlock(level, pos, stack);
        } else {
            return new ItemStack(this);
        }
    }

}
