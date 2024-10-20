package net.diemond_player.unidye.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class DyeableGlassBlockEntity extends BlockEntity{
    public DyeableGlassBlockEntity(BlockPos pos, BlockState state) {
        super(UnidyeBlockEntities.DYEABLE_GLASS_BE, pos, state);
    }

    public static final int DEFAULT_COLOR = 16777215;
    public int color = DEFAULT_COLOR;
    public int beaconColor = DEFAULT_COLOR;

    @Override
    public void writeNbt(NbtCompound nbt) {
        if (color != DEFAULT_COLOR) {
            nbt.putInt("color", color);
        }
        if (beaconColor != DEFAULT_COLOR) {
            nbt.putInt("beaconColor", beaconColor);
        }
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if (nbt.getInt("color") == 0) {
            color = DEFAULT_COLOR;
        } else {
            color = nbt.getInt("color");
        }
        if (nbt.getInt("beaconColor") == 0) {
            beaconColor = DEFAULT_COLOR;
        } else {
            beaconColor = nbt.getInt("beaconColor");
        }
    }

    @Override
    public void markDirty() {
        if (this.world != null) {
            markDirty(this.world, this.pos, this.getCachedState());
        }
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    public static int getColor(BlockView world, BlockPos pos) {
        if (world == null) {
            return DyeableGlassBlockEntity.DEFAULT_COLOR;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DyeableGlassBlockEntity dyeableBlockEntity) {
            return dyeableBlockEntity.color;
        } else {
            return DyeableGlassBlockEntity.DEFAULT_COLOR;
        }
    }

    public static int getBeaconColor(BlockView world, BlockPos pos) {
        if (world == null) {
            return DyeableGlassBlockEntity.DEFAULT_COLOR;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DyeableGlassBlockEntity dyeableBlockEntity) {
            return dyeableBlockEntity.beaconColor;
        } else {
            return DyeableGlassBlockEntity.DEFAULT_COLOR;
        }
    }
}
