package net.diemond_player.unidye.util;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public interface IModel {
    void unidye$specialRender(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha);
}
