package net.diemond_player.unidye.block.custom;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.block.entity.UnidyeBlockEntities;
import net.diemond_player.unidye.entity.DyeableFallingBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class DyeableConcretePowderBlock extends ConcretePowderBlock implements IDyeableBlock{

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
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!FallingBlock.canFallThrough(world.getBlockState(pos.down())) || pos.getY() < world.getBottomY()) {
            return;
        }
        int color = DyeableBlockEntity.getColor(world, pos);
        DyeableFallingBlockEntity dyeableFallingBlockEntity = DyeableFallingBlockEntity.spawnFromBlock(world, pos, state);
        this.configureFallingBlockEntity(dyeableFallingBlockEntity, color);
    }

    protected void configureFallingBlockEntity(DyeableFallingBlockEntity entity, int color) {
        entity.setCustomColor(color);
    }
    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        DyeableBlockEntity blockEntity = UnidyeBlockEntities.DYEABLE_BE.get(world,pos);
        return blockEntity.color;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    }
}
