//package net.diemond_player.unidye.mixin;
//
//import com.google.common.collect.Lists;
//import com.mojang.datafixers.util.Pair;
//import net.diemond_player.unidye.util.IBannerCustomPatterns;
//import net.minecraft.block.entity.BannerBlockEntity;
//import net.minecraft.block.entity.BannerPattern;
//import net.minecraft.block.entity.BannerPatterns;
//import net.minecraft.entity.passive.WolfEntity;
//import net.minecraft.item.BlockItem;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.nbt.NbtElement;
//import net.minecraft.nbt.NbtList;
//import net.minecraft.registry.Registries;
//import net.minecraft.registry.entry.RegistryEntry;
//import net.minecraft.util.DyeColor;
//import org.jetbrains.annotations.Nullable;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Unique;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Mixin(BannerBlockEntity.class)
//public abstract class BannerBlockEntityMixin implements IBannerCustomPatterns {
//    @Unique
//    private List<Pair<RegistryEntry<BannerPattern>, Integer>> customPatterns;
//
//    @Override
//    public List<Pair<RegistryEntry<BannerPattern>, Integer>> unidye$getCustomPatternsFromNbt(@Nullable NbtList patternListNbt) {
//        ArrayList<Pair<RegistryEntry<BannerPattern>, Integer>> list = Lists.newArrayList();
//        if (patternListNbt != null) {
//            for (int i = 0; i < patternListNbt.size(); ++i) {
//                NbtCompound nbtCompound = patternListNbt.getCompound(i);
//                RegistryEntry<BannerPattern> registryEntry = BannerPattern.byId(nbtCompound.getString("Pattern"));
//                if (registryEntry == null) continue;
//                int j = nbtCompound.getInt("Color");
//                list.add(Pair.of(registryEntry, j));
//            }
//        }
//        return list;
//    }
//    @Override
//    public NbtList unidye$getCustomPatternListNbt(ItemStack stack) {
//        NbtList nbtList = null;
//        NbtCompound nbtCompound = BlockItem.getBlockEntityNbt(stack);
//        if (nbtCompound != null && nbtCompound.contains("Custom Patterns", NbtElement.LIST_TYPE)) {
//            nbtList = nbtCompound.getList("Custom Patterns", NbtElement.COMPOUND_TYPE).copy();
//        }
//        return nbtList;
//    }
//    @Override
//    public List<Pair<RegistryEntry<BannerPattern>, Integer>> unidye$getCustomPatterns() {
//        if (this.customPatterns == null) {
//            this.customPatterns = BannerBlockEntity.getPatternsFromNbt(this.baseColor, this.patternListNbt);
//        }
//        return this.customPatterns;
//    }
//}
