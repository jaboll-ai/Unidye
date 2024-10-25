package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.UnidyeAccessor;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.client.render.entity.feature.CatCollarFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CatCollarFeatureRenderer.class)
public abstract class CatCollarFeatureRendererMixin {

    @ModifyArgs(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/CatEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/CatCollarFeatureRenderer;render(Lnet/minecraft/client/render/entity/model/EntityModel;Lnet/minecraft/client/render/entity/model/EntityModel;Lnet/minecraft/util/Identifier;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFFFFF)V"))
    private void unidye$render(Args args) {
        UnidyeAccessor cat = (UnidyeAccessor) args.get(6);
        int customColor = cat.unidye$getCustomColor();
        if (customColor != 0xFFFFFF) {
            float[] fs = UnidyeUtils.getColorArray(customColor);
            args.set(13, fs[0]);
            args.set(14, fs[1]);
            args.set(15, fs[2]);
        }
    }
}