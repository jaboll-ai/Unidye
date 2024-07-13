package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalBooleanRef;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.recipe.FireworkStarRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(FireworkStarRecipe.class)
public abstract class FireworkStarRecipeMixin {
    @Shadow
    private static final Ingredient TYPE_MODIFIER = Ingredient.ofItems(Items.FIRE_CHARGE, Items.FEATHER, Items.GOLD_NUGGET, Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL, Items.CREEPER_HEAD, Items.PLAYER_HEAD, Items.DRAGON_HEAD, Items.ZOMBIE_HEAD, Items.PIGLIN_HEAD);
    @Shadow
    private static final Ingredient TRAIL_MODIFIER = Ingredient.ofItems(Items.DIAMOND);
    @Shadow
    private static final Ingredient FLICKER_MODIFIER = Ingredient.ofItems(Items.GLOWSTONE_DUST);
    @Shadow
    private static final Ingredient GUNPOWDER = Ingredient.ofItems(Items.GUNPOWDER);
    @Inject(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At(value = "RETURN", ordinal = 4), cancellable = true)
    private void matches(RecipeInputInventory recipeInputInventory, World world, CallbackInfoReturnable<Boolean> cir) {
        if(!cir.getReturnValue()){
            cir.setReturnValue(doesItActuallyMatchThough(recipeInputInventory, world));
        }
    }
    @Inject(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At(value = "RETURN", ordinal = 5), cancellable = true)
    private void matches2(RecipeInputInventory recipeInputInventory, World world, CallbackInfoReturnable<Boolean> cir) {
        if(!cir.getReturnValue()){
            cir.setReturnValue(doesItActuallyMatchThough(recipeInputInventory, world));
        }
    }
    @Inject(method = "craft(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/NbtCompound;putIntArray(Ljava/lang/String;Ljava/util/List;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager, CallbackInfoReturnable<ItemStack> cir, ItemStack itemStack, NbtCompound nbtCompound, FireworkRocketItem.Type type, List list) {
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack2 = recipeInputInventory.getStack(i);
            if (itemStack2.getItem() instanceof CustomDyeItem customDyeItem) {
                int color = customDyeItem.getMaterialColor(itemStack2, "concrete");
                list.add(color);
            }
        }
    }

    @Unique
    private boolean doesItActuallyMatchThough(RecipeInputInventory recipeInputInventory, World world){
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack = recipeInputInventory.getStack(i);
            if (itemStack.isEmpty()) continue;
            if (TYPE_MODIFIER.test(itemStack)) {
                if (bl3) {
                    return false;
                }
                bl3 = true;
                continue;
            }
            if (FLICKER_MODIFIER.test(itemStack)) {
                if (bl5) {
                    return false;
                }
                bl5 = true;
                continue;
            }
            if (TRAIL_MODIFIER.test(itemStack)) {
                if (bl4) {
                    return false;
                }
                bl4 = true;
                continue;
            }
            if (GUNPOWDER.test(itemStack)) {
                if (bl) {
                    return false;
                }
                bl = true;
                continue;
            }
            if (itemStack.getItem() instanceof DyeItem) {
                bl2 = true;
                continue;
            }
            if (itemStack.getItem() instanceof CustomDyeItem) {
                bl2 = true;
                continue;
            }
            return false;
        }
        return bl && bl2;
    }
}
