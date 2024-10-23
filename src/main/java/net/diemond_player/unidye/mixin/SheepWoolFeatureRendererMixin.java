package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.UnidyeAccessor;
import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SheepWoolFeatureRenderer.class)
public abstract class SheepWoolFeatureRendererMixin {

    @ModifyArgs(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/SheepEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/SheepWoolFeatureRenderer;render(Lnet/minecraft/client/render/entity/model/EntityModel;Lnet/minecraft/client/render/entity/model/EntityModel;Lnet/minecraft/util/Identifier;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFFFFF)V"))
    private void injected(Args args) {
        UnidyeAccessor sheep = (UnidyeAccessor) args.get(6);
        int customColor = sheep.unidye$getCustomColor();
        if (customColor != 0xFFFFFF) {
            float red = (float) (customColor >> 16 & 0xFF) / 255.0f;
            float green = (float) (customColor >> 8 & 0xFF) / 255.0f;
            float blue = (float) (customColor & 0xFF) / 255.0f;
            args.set(13, red);
            args.set(14, green);
            args.set(15, blue);
        }
    }
}