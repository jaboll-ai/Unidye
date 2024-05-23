package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.ActionResult;

public class DyeableBlockItem extends BlockItem implements DyeableItem {
    public DyeableBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        ActionResult result = super.place(context);
        BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
        if(blockEntity instanceof DyeableBlockEntity dyeableBlockEntity){
            dyeableBlockEntity.color = getColor(context.getStack());
        }
        return result;
    }
}
