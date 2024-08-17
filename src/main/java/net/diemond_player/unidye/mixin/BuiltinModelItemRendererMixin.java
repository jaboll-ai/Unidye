package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.custom.DyeableBedBlock;
import net.diemond_player.unidye.block.entity.DyeableBedBlockEntity;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.DyeableBlockItem;
import net.minecraft.block.AbstractBannerBlock;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BuiltinModelItemRenderer.class)
public abstract class BuiltinModelItemRendererMixin {
    @Mutable
    @Final
    @Shadow
    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    @Unique
    private final DyeableBedBlockEntity renderDyeableBed = new DyeableBedBlockEntity(BlockPos.ORIGIN, ModBlocks.CUSTOM_BED.getDefaultState());

    protected BuiltinModelItemRendererMixin(BlockEntityRenderDispatcher blockEntityRenderDispatcher) {
        this.blockEntityRenderDispatcher = blockEntityRenderDispatcher;
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/BlockEntityRenderDispatcher;renderEntity(Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)Z"), cancellable = true)
    private void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, CallbackInfo ci) {
        Item item = stack.getItem();
        Block block = ((BlockItem)item).getBlock();
        if (block instanceof DyeableBedBlock) {
            this.renderDyeableBed.color = ((DyeableBlockItem)item).getColor(stack);
            this.blockEntityRenderDispatcher.renderEntity(this.renderDyeableBed, matrices, vertexConsumers, light, overlay);
            ci.cancel();
        }
    }
}
