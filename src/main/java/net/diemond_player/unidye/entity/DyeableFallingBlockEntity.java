package net.diemond_player.unidye.entity;

import net.diemond_player.unidye.UnidyeClient;
import net.diemond_player.unidye.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DyeableFallingBlockEntity extends FallingBlockEntity {

    private BlockState block = ModBlocks.CUSTOM_CONCRETE_POWDER.getDefaultState();

    public DyeableFallingBlockEntity(EntityType<? extends FallingBlockEntity> entityType, World world) {
        super(entityType, world);
    }

    private DyeableFallingBlockEntity(World world, double x, double y, double z, BlockState block) {
        this((EntityType<? extends FallingBlockEntity>)ModEntities.DYEABLE_FALLING_BLOCK_ENTITY, world);
        this.block = block;
        this.intersectionChecked = true;
        this.setPosition(x, y, z);
        this.setVelocity(Vec3d.ZERO);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.setFallingBlockPos(this.getBlockPos());
    }

    public static DyeableFallingBlockEntity spawnFromBlock(World world, BlockPos pos, BlockState state) {
        DyeableFallingBlockEntity dyeableFallingBlockEntity = new DyeableFallingBlockEntity(world, (double)pos.getX() + 0.5, pos.getY(), (double)pos.getZ() + 0.5, state.contains(Properties.WATERLOGGED) ? (BlockState)state.with(Properties.WATERLOGGED, false) : state);
        dyeableFallingBlockEntity.block = ModBlocks.CUSTOM_CONCRETE_POWDER.getDefaultState();
        world.setBlockState(pos, state.getFluidState().getBlockState(), Block.NOTIFY_ALL);
        world.spawnEntity(dyeableFallingBlockEntity);
        return dyeableFallingBlockEntity;
    }

    private static final TrackedData<Integer> CUSTOM_COLOR = DataTracker.registerData(DyeableFallingBlockEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("unidye.custom_color", getCustomColor());
        super.writeCustomDataToNbt(nbt);
    }


    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("unidye.custom_color")) {
            setCustomColor(nbt.getInt("unidye.custom_color"));
        }
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(CUSTOM_COLOR, 0x00FFFF);
        super.initDataTracker();
    }

    public int getCustomColor() {
        return this.getDataTracker().get(CUSTOM_COLOR);
    }

    public void setCustomColor(int color) {
        this.getDataTracker().set(CUSTOM_COLOR, color);
    }
}
