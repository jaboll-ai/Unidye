package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableWoolBlockEntity;
import net.diemond_player.unidye.block.entity.UnidyeBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

import static net.diemond_player.unidye.block.entity.DyeableWoolBlockEntity.DEFAULT_COLOR;
import static net.minecraft.item.DyeableItem.COLOR_KEY;
import static net.minecraft.item.DyeableItem.DISPLAY_KEY;

public class DyeableWoolBlock extends DyeableBlock{
    public DyeableWoolBlock(Settings settings) {
        super(settings);
    }
    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        if (DyeableWoolBlockEntity.getColor(world, pos) != DEFAULT_COLOR) {
            return pickBlock(world,pos,new ItemStack(this));
        } else {
            return new ItemStack(this);
        }
    }
    @Override
    public ItemStack pickBlock(BlockView world, BlockPos pos, ItemStack stack) {
        DyeableWoolBlockEntity blockEntity = UnidyeBlockEntities.DYEABLE_WOOL_BE.get(world,pos);
        int color = DEFAULT_COLOR;
        int bannerColor = DEFAULT_COLOR;
        if(blockEntity != null){
            color = blockEntity.color;
            bannerColor = blockEntity.bannerColor;
        }
        NbtCompound subNbt = stack.getOrCreateSubNbt("display");
        subNbt.putInt("color", color);
        NbtCompound subNbt2 = stack.getOrCreateSubNbt("1");
        subNbt2.putInt("bannerColor", bannerColor);
        return stack;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DyeableWoolBlockEntity(pos, state);
    }
}
