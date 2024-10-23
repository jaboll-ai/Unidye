package net.diemond_player.unidye.util;


import net.minecraft.block.BlockState;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.map.MapState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;

public interface UnidyeAccessor {

    int unidye$getCustomColor();

    void unidye$setCustomColor(int customColor);

    int unidye$getCustomColor(int n);

    void unidye$setCustomColor(int n, int customColor);

    int[] unidye$getCustomColorArray();

    void unidye$setCustomColorArray(int[] customColorArray);

    int unidye$getSecondaryCustomColor();

    void unidye$setSecondaryCustomColor(int customColor);

    boolean unidye$putColor(int x, int z, int customColor);

    void unidye$setColor(int x, int z, int customColor);

    void unidye$setCustomColorsTo(MapState mapState);

    void unidye$render(BlockRenderView world, BakedModel model, BlockState state, BlockPos pos, MatrixStack matrices, VertexConsumer vertexConsumer, boolean cull, Random random, long seed, int overlay, int color);

    void unidye$specialRender(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha);

}