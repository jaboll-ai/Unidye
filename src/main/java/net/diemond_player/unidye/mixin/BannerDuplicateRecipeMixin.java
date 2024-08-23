package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.block.entity.DyeableBannerBlockEntity;
import net.diemond_player.unidye.item.custom.DyeableBannerItem;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.BannerDuplicateRecipe;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BannerDuplicateRecipe.class)
public abstract class BannerDuplicateRecipeMixin {
    @ModifyReturnValue(method = "matches(Lnet/minecraft/inventory/RecipeInputInventory;Lnet/minecraft/world/World;)Z", at = @At(value = "RETURN"))
    private boolean matches(boolean original, @Local(argsOnly = true) RecipeInputInventory recipeInputInventory, @Local(argsOnly = true) World world) {
        if(!original){
            return doesItActuallyMatchThough(recipeInputInventory, world);
        }
        return true;
    }
    @Unique
    private boolean doesItActuallyMatchThough(RecipeInputInventory recipeInputInventory, World world){
        int color = -1;
        ItemStack itemStack = null;
        ItemStack itemStack2 = null;
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack3 = recipeInputInventory.getStack(i);
            if (itemStack3.isEmpty()) continue;
            Item item = itemStack3.getItem();
            if (!(item instanceof DyeableBannerItem)) {
                return false;
            }
            DyeableBannerItem bannerItem = (DyeableBannerItem)item;
            if (color == -1) {
                color = bannerItem.getColor(itemStack3);
            } else if (color != bannerItem.getColor(itemStack3)) {
                return false;
            }
            int j = DyeableBannerBlockEntity.getPatternCount(itemStack3);
            if (j > 6) {
                return false;
            }
            if (j > 0) {
                if (itemStack == null) {
                    itemStack = itemStack3;
                    continue;
                }
                return false;
            }
            if (itemStack2 == null) {
                itemStack2 = itemStack3;
                continue;
            }
            return false;
        }
        return itemStack != null && itemStack2 != null;
    }
}
