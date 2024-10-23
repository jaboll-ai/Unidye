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

public class DyeableBedBlockEntity extends BlockEntity {
    public static final int DEFAULT_COLOR = 16777215;
    public int color = DEFAULT_COLOR;

    public DyeableBedBlockEntity(BlockPos pos, BlockState state) {
        super(UnidyeBlockEntities.DYEABLE_BED_BE, pos, state);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        if (color != DEFAULT_COLOR) {
            nbt.putInt("color", color);
            super.writeNbt(nbt);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        if (nbt.getInt("color") == 0) {
            color = DEFAULT_COLOR;
        } else {
            color = nbt.getInt("color");
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
            return DyeableBedBlockEntity.DEFAULT_COLOR;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DyeableBedBlockEntity dyeableBlockEntity) {
            return dyeableBlockEntity.color;
        } else {
            return DyeableBedBlockEntity.DEFAULT_COLOR;
        }
    }
}
