package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.client.render.entity.feature.CatCollarFeatureRenderer;
import net.minecraft.client.render.entity.feature.WolfCollarFeatureRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(CatCollarFeatureRenderer.class)
public abstract class CatCollarFeatureRendererMixin {

	@ModifyArgs(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/CatEntity;FFFFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/feature/CatCollarFeatureRenderer;render(Lnet/minecraft/client/render/entity/model/EntityModel;Lnet/minecraft/client/render/entity/model/EntityModel;Lnet/minecraft/util/Identifier;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFFFFF)V"))
	private void injected(Args args) {
		IEntityAccessor cat = (IEntityAccessor) args.get(6);
		int customColor = cat.unidye$getCustomColor();
		if(customColor != 0xFFFFFF) {
			float red = (float)(customColor >> 16 & 0xFF) / 255.0f;
			float green = (float)(customColor >> 8 & 0xFF) / 255.0f;
			float blue = (float)(customColor & 0xFF) / 255.0f;
			args.set(13, red);
			args.set(14, green);
			args.set(15, blue);
		}
	}
}