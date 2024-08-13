package net.diemond_player.unidye.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IBannerCustomPatterns {
    List<Pair<RegistryEntry<BannerPattern>, Integer>> unidye$getCustomPatterns();
    List<Pair<RegistryEntry<BannerPattern>, Integer>> unidye$getCustomPatternsFromNbt(@Nullable NbtList patternListNbt);
    NbtList unidye$getCustomPatternListNbt(ItemStack stack);
}
