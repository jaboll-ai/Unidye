package net.diemond_player.unidye.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DyeableLeatheryBlockEntity extends BlockEntity {
    public DyeableLeatheryBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(UnidyeBlockEntities.DYEABLE_LEATHERY_BE.get(), pPos, pBlockState);
    }

    public static final int DEFAULT_COLOR = 16777215;
    public int color = DEFAULT_COLOR;
    public int leatherColor = DEFAULT_COLOR;

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        if (color != DEFAULT_COLOR) {
            pTag.putInt("color", color);
        }
        if (leatherColor != DEFAULT_COLOR) {
            pTag.putInt("leather", leatherColor);
        }
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        if (pTag.getInt("leather") == 0) {
            leatherColor = DEFAULT_COLOR;
        } else {
            leatherColor = pTag.getInt("leather");
        }
        if (pTag.getInt("color") == 0) {
            color = DEFAULT_COLOR;
        } else {
            color = pTag.getInt("color");
        }
    }

//    @Override
//    public Packet<ClientGamePacketListener> getUpdatePacket() {
//        return ClientboundBlockEntityDataPacket.create(this);
//    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    public static int getColor(BlockGetter level, BlockPos pos) {
        if (level == null) {
            return DyeableBlockEntity.DEFAULT_COLOR;
        }
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof DyeableLeatheryBlockEntity dyeableBlockEntity) {
            return dyeableBlockEntity.color;
        } else {
            return DyeableBlockEntity.DEFAULT_COLOR;
        }
    }
}
