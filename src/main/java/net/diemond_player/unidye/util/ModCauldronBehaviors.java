package net.diemond_player.unidye.util;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.custom.DyeableShulkerBoxBlock;
import net.diemond_player.unidye.block.entity.DyeableBannerBlockEntity;
import net.diemond_player.unidye.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;

import static net.minecraft.block.cauldron.CauldronBehavior.WATER_CAULDRON_BEHAVIOR;

public class ModCauldronBehaviors {
    public static void registerModCauldronBehaviors(){
        WATER_CAULDRON_BEHAVIOR.put(ModItems.CUSTOM_BANNER, CLEAN_CUSTOM_BANNER);
        WATER_CAULDRON_BEHAVIOR.put(ModBlocks.CUSTOM_SHULKER_BOX.asItem(), CLEAN_CUSTOM_SHULKER_BOX);
    }
    public static final CauldronBehavior CLEAN_CUSTOM_BANNER = (state, world, pos, player, hand, stack) -> {
        if (DyeableBannerBlockEntity.getPatternCount(stack) <= 0) {
            return ActionResult.PASS;
        }
        if (!world.isClient) {
            ItemStack itemStack = stack.copyWithCount(1);
            DyeableBannerBlockEntity.loadFromItemStack(itemStack);
            if (!player.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            if (stack.isEmpty()) {
                player.setStackInHand(hand, itemStack);
            } else if (player.getInventory().insertStack(itemStack)) {
                player.playerScreenHandler.syncState();
            } else {
                player.dropItem(itemStack, false);
            }
            player.incrementStat(Stats.CLEAN_BANNER);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ActionResult.success(world.isClient);
    };

    public static final CauldronBehavior CLEAN_CUSTOM_SHULKER_BOX = (state, world, pos, player, hand, stack) -> {
        Block block = Block.getBlockFromItem(stack.getItem());
        if (!(block instanceof DyeableShulkerBoxBlock)) {
            return ActionResult.PASS;
        }
        if (!world.isClient) {
            ItemStack itemStack = new ItemStack(Blocks.SHULKER_BOX);
            if (stack.hasNbt()) {
                stack.removeSubNbt("display");
                itemStack.setNbt(stack.getNbt().copy());
            }
            player.setStackInHand(hand, itemStack);
            player.incrementStat(Stats.CLEAN_SHULKER_BOX);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }
        return ActionResult.success(world.isClient);
    };
}
