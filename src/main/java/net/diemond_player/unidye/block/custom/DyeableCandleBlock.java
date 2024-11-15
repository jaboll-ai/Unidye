package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.HitResult;

import static net.diemond_player.unidye.item.custom.DyeableBlockItem.DEFAULT_COLOR;
import static net.minecraft.world.item.DyeableLeatherItem.TAG_COLOR;
import static net.minecraft.world.item.DyeableLeatherItem.TAG_DISPLAY;

public class DyeableCandleBlock extends CandleBlock implements IDyeableBlock {
    public DyeableCandleBlock(Properties properties) {
        super(properties);
    }


    public int getColor(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTagElement(TAG_DISPLAY);
        if (nbtCompound != null && nbtCompound.contains(TAG_COLOR, CompoundTag.TAG_ANY_NUMERIC)) {
            return nbtCompound.getInt(TAG_COLOR);
        }
        return DEFAULT_COLOR;
    }


    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        if (getColor(pUseContext.getItemInHand())
                != getColor(getCloneItemStack(pUseContext.getLevel(), pUseContext.getClickedPos(), pState))) {
            return false;
        }
        return super.canBeReplaced(pState, pUseContext);
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
