package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.diemond_player.unidye.block.entity.DyeableBannerBlockEntity;
import net.diemond_player.unidye.entity.client.renderer.DyeableBannerBlockEntityRenderer;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.DyeableBannerItem;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.client.gui.screen.ingame.LoomScreen;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(LoomScreen.class)
public abstract class LoomScreenMixin{

    @Shadow private ItemStack banner;

    @Shadow private @Nullable List<Pair<RegistryEntry<BannerPattern>, ?>> bannerPatterns;

    @Redirect(method = "drawBackground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/BannerBlockEntityRenderer;renderCanvas(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/model/ModelPart;Lnet/minecraft/client/util/SpriteIdentifier;ZLjava/util/List;)V"))
    private void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, ModelPart canvas, SpriteIdentifier baseSprite, boolean isBanner, List<Pair<RegistryEntry<BannerPattern>, DyeColor>> patterns) {
        List<Pair<RegistryEntry<BannerPattern>, ?>> patterns1;
        if(banner.isOf(UnidyeItems.CUSTOM_BANNER)) {
            patterns1 = DyeableBannerBlockEntity.getPatternsFromNbt(((DyeableBannerItem)banner.getItem()).getColor(banner), DyeableBannerBlockEntity.getPatternListNbt(banner));
        }else{
            float[] a = ((BannerItem)banner.getItem()).getColor().getColorComponents();
            int n = (int) (a[0]*255);
            n = (n << 8) + (int) (a[1]*255);
            n = (n << 8) + (int) (a[2]*255);
            patterns1 = DyeableBannerBlockEntity.getPatternsFromNbt(n, DyeableBannerBlockEntity.getPatternListNbt(banner));
        }
        DyeableBannerBlockEntityRenderer.renderCanvas(matrices, vertexConsumers, light, overlay, canvas, baseSprite, isBanner, patterns1);
    }

    @Redirect(method="onInventoryChanged", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screen/ingame/LoomScreen;bannerPatterns:Ljava/util/List;", ordinal = 0))
    private void onInventoryChanged(LoomScreen instance, @Nullable List<Pair<RegistryEntry<BannerPattern>, DyeColor>> value) {
        this.bannerPatterns = null;
    }
}
