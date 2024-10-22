package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(SignBlockEntityRenderer.class)
public abstract class SignBlockEntityRendererMixin{

    @ModifyArg(method = "renderText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/OrderedText;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)I"), index = 3)
    private int renderText1(int color, @Local(argsOnly = true) SignText signText){
        IEntityAccessor iEntityAccessor = (IEntityAccessor) signText;
        if(iEntityAccessor.unidye$getCustomColor() != 0xFFFFFF){
            int color2 = iEntityAccessor.unidye$getCustomColor();
            int j = (int)((double) ColorHelper.Argb.getRed(color2) * 0.4);
            int k = (int)((double)ColorHelper.Argb.getGreen(color2) * 0.4);
            int l = (int)((double)ColorHelper.Argb.getBlue(color2) * 0.4);
            return ColorHelper.Argb.getArgb(0, j, k, l);
        }
        return color;
    }

    @ModifyArgs(method = "renderText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawWithOutline(Lnet/minecraft/text/OrderedText;FFIILorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void renderText(Args args, @Local(argsOnly = true) SignText signText){
        IEntityAccessor iEntityAccessor = (IEntityAccessor) signText;
        if(iEntityAccessor.unidye$getCustomColor() != 0xFFFFFF){
            int color2 = iEntityAccessor.unidye$getCustomColor();
            int j = (int)((double) ColorHelper.Argb.getRed(color2) * 0.4);
            int k = (int)((double)ColorHelper.Argb.getGreen(color2) * 0.4);
            int l = (int)((double)ColorHelper.Argb.getBlue(color2) * 0.4);
            args.set(3, color2);
            args.set(4, ColorHelper.Argb.getArgb(0, j, k, l));
        }
    }

    @ModifyArg(method = "render(Lnet/minecraft/block/entity/SignBlockEntity;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/block/BlockState;Lnet/minecraft/block/AbstractSignBlock;Lnet/minecraft/block/WoodType;Lnet/minecraft/client/model/Model;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/SignBlockEntityRenderer;renderText(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/SignText;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IIIZ)V", ordinal = 0), index = 1)
    private SignText render(SignText signText, @Local(argsOnly = true) SignBlockEntity signBlockEntity){
        IEntityAccessor iEntityAccessor = (IEntityAccessor) signText;
        IEntityAccessor iEntityAccessor2 = (IEntityAccessor) signBlockEntity;
        iEntityAccessor.unidye$setCustomColor(iEntityAccessor2.unidye$getCustomColor());
        return signText;
    }

    @ModifyArg(method = "render(Lnet/minecraft/block/entity/SignBlockEntity;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/block/BlockState;Lnet/minecraft/block/AbstractSignBlock;Lnet/minecraft/block/WoodType;Lnet/minecraft/client/model/Model;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/block/entity/SignBlockEntityRenderer;renderText(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/SignText;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IIIZ)V", ordinal = 1), index = 1)
    private SignText render1(SignText signText, @Local(argsOnly = true) SignBlockEntity signBlockEntity){
        IEntityAccessor iEntityAccessor = (IEntityAccessor) signText;
        IEntityAccessor iEntityAccessor2 = (IEntityAccessor) signBlockEntity;
        iEntityAccessor.unidye$setCustomColor(iEntityAccessor2.unidye$getSecondaryCustomColor());
        return signText;
    }
}
