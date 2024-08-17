package net.diemond_player.unidye.mixin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.custom.DyeableBedBlock;
import net.diemond_player.unidye.item.ModItemGroups;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BedPart;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(PointOfInterestTypes.class)
public abstract class PointOfInterestTypesMixin {
//    @Unique
//    private static final Set<BlockState> CUSTOM_BED_HEADS = ModBlocks.CUSTOM_BED.getStateManager().getStates().stream().filter(blockState -> blockState.get(DyeableBedBlock.PART) == BedPart.HEAD).collect(Collectors.toSet());
//
//    @ModifyArg(method = "registerAndGetDefault", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/poi/PointOfInterestTypes;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/registry/RegistryKey;Ljava/util/Set;II)Lnet/minecraft/world/poi/PointOfInterestType;", ordinal = 13), index = 2)
//    private static Set<BlockState> reg(Set<BlockState> states) {
//        states = Stream.concat(states.stream(), CUSTOM_BED_HEADS.stream())
//                .collect(ImmutableSet.toImmutableSet());
//        return states;
//    }
}
