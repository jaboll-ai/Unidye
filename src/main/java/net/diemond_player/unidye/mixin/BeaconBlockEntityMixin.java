package net.diemond_player.unidye.mixin;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalFloatRef;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.custom.DyeableGlassBlock;
import net.diemond_player.unidye.block.entity.ModBlockEntities;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Stainable;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.management.openmbean.ArrayType;
import java.util.Arrays;
import java.util.List;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin{
    @ModifyVariable(method = "tick", at = @At(value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/util/DyeColor;getColorComponents()[F"), ordinal = 0)
    private static float[] tick(float[] fs, World world, BlockPos BlockPos, BlockState state, BeaconBlockEntity blockEntity, @Local(ordinal = 1) BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        float[] fsI = new float[3];
        if(block instanceof DyeableGlassBlock){
            if(ModBlockEntities.DYEABLE_BE.get(world,pos) != null) {
                int color = ModBlockEntities.DYEABLE_BE.get(world, pos).color;
                fsI[0] = ((color & 0xFF0000) >> 16) / 255.0f;
                fsI[1] = ((color & 0xFF00) >> 8) / 255.0f;
                fsI[2] = (color & 0xFF) / 255.0f;
                return fsI;
            }
        }
        return ((Stainable)((Object)block)).getColor().getColorComponents();
    }
}
