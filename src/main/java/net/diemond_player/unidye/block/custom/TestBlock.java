package net.diemond_player.unidye.block.custom;

import net.minecraft.block.AirBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TestBlock extends Block {
    public TestBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if(item == Items.EMERALD){
            for(int i=-1; i<2; i++){
                for(int j=-1; j<2; j++){
                    world.setBlockState(new BlockPos(pos.getX() + i, pos.getY() + j, pos.getZ()), Blocks.AIR.getDefaultState());
                }
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
