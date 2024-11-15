package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.block.entity.UnidyeBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public interface IDyeableBlock extends EntityBlock {

    @Override
    default BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {return new DyeableBlockEntity(pPos, pState);}

    default ItemStack pickBlock(BlockGetter level, BlockPos pos, ItemStack stack) {
        DyeableBlockEntity blockEntity = UnidyeBlockEntities.DYEABLE_BE.get().getBlockEntity(level, pos);
        int color = DyeableBlockEntity.DEFAULT_COLOR;
        if (blockEntity != null) {
            color = blockEntity.color;
        }
        CompoundTag subNbt = stack.getOrCreateTagElement(DyeableLeatherItem.TAG_DISPLAY);
        subNbt.putInt(DyeableLeatherItem.TAG_COLOR, color);
        return stack;
    }

}
