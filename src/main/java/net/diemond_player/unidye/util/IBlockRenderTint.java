package net.diemond_player.unidye.util;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

public interface IBlockRenderTint {
    void unidye$renderBlockAsEntityWithTint(BlockState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, float red, float green, float blue);
}
