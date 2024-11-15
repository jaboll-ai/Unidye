package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static net.minecraft.world.level.block.HorizontalDirectionalBlock.FACING;


public class DyeableBlockItem extends BlockItem implements DyeableLeatherItem {

    public DyeableBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    public static final int DEFAULT_COLOR = 16777215;

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        InteractionResult result = super.place(context);
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if (blockEntity instanceof DyeableBlockEntity dyeableBlockEntity
                && !blockstate.is(UnidyeBlocks.CUSTOM_CARPET.get())
                && !blockstate.is(UnidyeBlocks.CUSTOM_CANDLE.get())
//                && !blockstate.is(UnidyeBlocks.CUSTOM_CANDLE_CAKE.get())
            ) {
            dyeableBlockEntity.color = getColor(context.getItemInHand());
        }
//        if (blockEntity instanceof DyeableShulkerBoxBlockEntity dyeableShulkerBoxBlockEntity
//                && !blockstate.is(UnidyeBlocks.CUSTOM_SHULKER_BOX.get())) {
//            dyeableShulkerBoxBlockEntity.color = getColor(context.getItemInHand());
//        }
//        if (blockEntity instanceof DyeableBedBlockEntity dyeableBedBlockEntity
//                && !blockstate.is(UnidyeBlocks.CUSTOM_BED.get())) {
//            dyeableBedBlockEntity.color = getColor(context.getItemInHand());
//            DyeableBedBlockEntity dyeableBedBlockEntity1 = (DyeableBedBlockEntity) context.getLevel().getBlockEntity(context.getClickedPos().offset(context.getLevel().getBlockState(context.getClickedPos()).getValue(FACING).getNormal()));
//            if (dyeableBedBlockEntity1 != null) {
//                dyeableBedBlockEntity1.color = getColor(context.getStack());
//            }
//        }
        return result;
    }

    @Override
    public int getColor(ItemStack pStack) {
        CompoundTag nbtCompound = pStack.getTagElement(TAG_DISPLAY);
        if (nbtCompound != null && nbtCompound.contains(TAG_COLOR, CompoundTag.TAG_ANY_NUMERIC)) {
            return nbtCompound.getInt(TAG_COLOR);
        }
        return DEFAULT_COLOR;
    }
}
