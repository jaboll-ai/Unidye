package net.diemond_player.unidye.block.custom;

import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

public class DyeableShulkerBoxBlock extends ShulkerBoxBlock implements IDyeableBlock{

    public DyeableShulkerBoxBlock(@Nullable DyeColor color, Settings settings) {
        super(color, settings);
    }
}
