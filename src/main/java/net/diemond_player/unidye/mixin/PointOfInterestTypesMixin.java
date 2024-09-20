package net.diemond_player.unidye.mixin;

import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PointOfInterestTypes.class)
public abstract class PointOfInterestTypesMixin {
//    @Unique
//    private static final Set<BlockState> CUSTOM_BED_HEADS = UnidyeBlocks.CUSTOM_BED.getStateManager().getStates().stream().filter(blockState -> blockState.get(DyeableBedBlock.PART) == BedPart.HEAD).collect(Collectors.toSet());
//
//    @ModifyArg(method = "registerAndGetDefault", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/poi/PointOfInterestTypes;register(Lnet/minecraft/registry/Registry;Lnet/minecraft/registry/RegistryKey;Ljava/util/Set;II)Lnet/minecraft/world/poi/PointOfInterestType;", ordinal = 13), index = 2)
//    private static Set<BlockState> reg(Set<BlockState> states) {
//        states = Stream.concat(states.stream(), CUSTOM_BED_HEADS.stream())
//                .collect(ImmutableSet.toImmutableSet());
//        return states;
//    }
}
