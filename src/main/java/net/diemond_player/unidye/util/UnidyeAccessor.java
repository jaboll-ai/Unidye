package net.diemond_player.unidye.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public interface UnidyeAccessor {

    int unidye$getCustomColor();

    void unidye$setCustomColor(int customColor);

    int unidye$getSecondaryCustomColor();

    void unidye$setSecondaryCustomColor(int customColor);

    void unidye$render(LevelAccessor world, BakedModel model, BlockState state, BlockPos pos, PoseStack matrices, VertexConsumer vertexConsumer, boolean cull, Random random, long seed, int overlay, int color);

    void unidye$renderCustomBed(PoseStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha);

}