package net.diemond_player.unidye.mixin;

import com.mojang.datafixers.util.Pair;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.custom.DyeableBannerBlock;
import net.diemond_player.unidye.block.custom.DyeableBedBlock;
import net.diemond_player.unidye.block.custom.DyeableShulkerBoxBlock;
import net.diemond_player.unidye.block.custom.DyeableWallBannerBlock;
import net.diemond_player.unidye.block.entity.DyeableBannerBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableBedBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableShulkerBoxBlockEntity;
import net.diemond_player.unidye.entity.client.renderer.DyeableBannerBlockEntityRenderer;
import net.diemond_player.unidye.item.custom.DyeableBannerItem;
import net.diemond_player.unidye.item.custom.DyeableBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShieldItem.class)
public abstract class ShieldItemMixin{
    @Inject(method = "getTranslationKey", at = @At(value = "RETURN", ordinal = 0), cancellable = true)
    private void render(ItemStack stack, CallbackInfoReturnable<String> cir) {
        if(BlockItem.getBlockEntityNbt(stack).contains("CustomColored")){
            cir.setReturnValue("item.unidye.shield_custom_color");
        }
    }
}
