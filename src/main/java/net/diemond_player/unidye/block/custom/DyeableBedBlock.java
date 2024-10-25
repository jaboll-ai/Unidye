package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableBedBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class DyeableBedBlock extends BedBlock
        implements IDyeableBlock {
    public DyeableBedBlock(AbstractBlock.Settings settings) {
        super(DyeColor.CYAN, settings);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableBedBlockEntity(pos, state);
    }
}
