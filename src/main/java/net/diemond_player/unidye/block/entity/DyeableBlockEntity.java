package net.diemond_player.unidye.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


public class DyeableBlockEntity extends BlockEntity {
    public static final int DEFAULT_COLOR = 16777215;
    public int color = DEFAULT_COLOR;

    public DyeableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(UnidyeBlockEntities.DYEABLE_BE.get(), pPos, pBlockState);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        if (color != DEFAULT_COLOR) {
            pTag.putInt("color", color);
        }
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        if (pTag.getInt("color") == 0) {
            color = DEFAULT_COLOR;
        } else {
            color = pTag.getInt("color");
        }
    }

//    @Override
//    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
//        return ClientboundBlockEntityDataPacket.create(this);
//    }

    public static int getColor(BlockGetter world, BlockPos pos) {
        if (world == null) {
            return DyeableBlockEntity.DEFAULT_COLOR;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DyeableBlockEntity dyeableBlockEntity) {
            return dyeableBlockEntity.color;
        } else {
            return DyeableBlockEntity.DEFAULT_COLOR;
        }
    }


}
