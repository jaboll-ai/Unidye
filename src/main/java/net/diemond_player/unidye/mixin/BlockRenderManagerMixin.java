package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.IBlockRenderTint;
import net.diemond_player.unidye.util.IDoSomething;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.feature.CatCollarFeatureRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockRenderManager.class)
public abstract class BlockRenderManagerMixin implements IBlockRenderTint {
    @Override
    public void unidye$renderBlockAsEntityWithTint(BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, float red, float green, float blue) {
        BlockRenderType blockRenderType = state.getRenderType();
        if (blockRenderType == BlockRenderType.INVISIBLE) {
            return;
        }
        BlockRenderManager blockRenderManager = ((BlockRenderManager) (Object) this);
        BakedModel bakedModel = blockRenderManager.getModel(state);
        IDoSomething blockModelRenderer = (IDoSomething) blockRenderManager.getModelRenderer();
        blockModelRenderer.unidye$render(matrices.peek(), vertexConsumers.getBuffer(RenderLayers.getEntityBlockLayer(state, false)), state, bakedModel, red, green, blue, light, overlay);
    }
}
