package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.entity.ModBlockEntities;
import net.diemond_player.unidye.item.ModItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.DyeableBannerItem;
import net.minecraft.block.entity.BannerPattern;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.LoomScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(LoomScreenHandler.class)
public abstract class LoomScreenHandlerMixin extends ScreenHandler{
    protected LoomScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, Inventory input) {
        super(type, syncId);
        this.input = input;
    }

    @Inject(method = "updateOutputSlot", at = @At("HEAD"))
    private void updateOutputSlot(RegistryEntry<BannerPattern> pattern, CallbackInfo ci){

    }
    @Mutable
    @Final
    @Shadow
    Slot dyeSlot;

    @Mutable
    @Final
    @Shadow
    Slot bannerSlot;

    @Final
    @Shadow
    private Slot patternSlot;

    @Final
    @Shadow
    private Slot outputSlot;

    @Mutable
    @Final
    @Shadow
    private final Inventory input;

    @Shadow @Final private ScreenHandlerContext context;

    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 1))
    private void canInsert(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci){
        this.dyeSlot = this.addSlot(new Slot(this.input, 1, 33, 26){

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof CustomDyeItem || stack.getItem() instanceof DyeItem;
            }
        });
    }

    @Inject(method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/LoomScreenHandler;addSlot(Lnet/minecraft/screen/slot/Slot;)Lnet/minecraft/screen/slot/Slot;", ordinal = 0))
    private void canInsert1(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context, CallbackInfo ci){
        this.bannerSlot = this.addSlot(new Slot(this.input, 0, 13, 26){

            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() instanceof BannerItem || stack.getItem() instanceof DyeableBannerItem;
            }
        });
    }

//    @Inject(method = "quickMove", at = @At(value = "RETURN", ordinal = 1), cancellable = true, locals = LocalCapture.CAPTURE_FAILHARD)
//    private void quickMove(PlayerEntity player, int slot, CallbackInfoReturnable<ItemStack> cir, @Local(ordinal = 1) ItemStack itemStack2){
//        if (slot == this.dyeSlot.id || slot == this.bannerSlot.id || slot == this.patternSlot.id ? !this.insertItem(itemStack2, 4, 40, false) : (itemStack2.getItem() instanceof BannerItem ? !this.insertItem(itemStack2, this.bannerSlot.id, this.bannerSlot.id + 1, false) : (itemStack2.getItem() instanceof CustomDyeItem ? !this.insertItem(itemStack2, this.dyeSlot.id, this.dyeSlot.id + 1, false) : (itemStack2.getItem() instanceof BannerPatternItem ? !this.insertItem(itemStack2, this.patternSlot.id, this.patternSlot.id + 1, false) : (slot >= 4 && slot < 31 ? !this.insertItem(itemStack2, 31, 40, false) : slot >= 31 && slot < 40 && !this.insertItem(itemStack2, 4, 31, false)))))) {
//            cir.setReturnValue(Items.ACACIA_LOG.getDefaultStack());
//        } else {
//            cir.cancel();
//        }
//    }

//    @Inject(method = "updateOutputSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/DyeItem;getColor()Lnet/minecraft/util/DyeColor;"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
//    private void updateOutputSlott(RegistryEntry<BannerPattern> pattern, CallbackInfo ci, @Local(ordinal = 2) ItemStack itemStack3, @Local(ordinal = 1) ItemStack itemStack2){
//        if(itemStack2.getItem() instanceof CustomDyeItem) {
//            if (!ItemStack.areEqual(itemStack3, this.outputSlot.getStack())) {
//                this.outputSlot.setStackNoCallbacks(itemStack3);
//            }
//            ci.cancel();
//        }
//    }

    @Inject(method = "updateOutputSlot", at = @At(value = "HEAD"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void updateOutputSlottt(RegistryEntry<BannerPattern> pattern, CallbackInfo ci){
        ItemStack itemStack = this.bannerSlot.getStack();
        if(itemStack.isOf(ModItems.CUSTOM_BANNER)){
            ItemStack itemStack2 = this.dyeSlot.getStack();
            ItemStack itemStack3 = ItemStack.EMPTY;
            if (!itemStack.isEmpty() && !itemStack2.isEmpty()) {
                NbtList nbtList;
                itemStack3 = itemStack.copyWithCount(1);
                DyeColor dyeColor = ((DyeItem)itemStack2.getItem()).getColor();
                NbtCompound nbtCompound = BlockItem.getBlockEntityNbt(itemStack3);
                if (nbtCompound != null && nbtCompound.contains("Patterns", NbtElement.LIST_TYPE)) {
                    nbtList = nbtCompound.getList("Patterns", NbtElement.COMPOUND_TYPE);
                } else {
                    nbtList = new NbtList();
                    if (nbtCompound == null) {
                        nbtCompound = new NbtCompound();
                    }
                    nbtCompound.put("Patterns", nbtList);
                }
                NbtCompound nbtCompound2 = new NbtCompound();
                nbtCompound2.putString("Pattern", pattern.value().getId());
                nbtCompound2.putInt("Color", dyeColor.getId());
                nbtList.add(nbtCompound2);
                BlockItem.setBlockEntityNbt(itemStack3, ModBlockEntities.DYEABLE_BANNER_BE, nbtCompound);
            }
            if (!ItemStack.areEqual(itemStack3, this.outputSlot.getStack())) {
                this.outputSlot.setStackNoCallbacks(itemStack3);
            }
            ci.cancel();
        }
    }
}
