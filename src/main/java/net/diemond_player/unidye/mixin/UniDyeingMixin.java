package net.diemond_player.unidye.mixin;

import com.google.common.collect.Lists;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.diemond_player.unidye.item.ModItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.UnidyeableItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.*;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;

@Mixin(ArmorDyeRecipe.class)
public class UniDyeingMixin {
	/**
	 * @author
	 * @reason
	 */
	@Overwrite
	public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
		ItemStack itemStack = ItemStack.EMPTY;
		ArrayList<ItemStack> list = Lists.newArrayList();
		ArrayList<ItemStack> customColors = Lists.newArrayList();
		ArrayList<Item> items = Lists.newArrayList();
		items.add(Items.GLASS);
		items.add(Items.TERRACOTTA);
		items.add(Items.WHITE_WOOL);
		boolean test = true;
		if(items.contains(recipeInputInventory.getStack(0).getItem())){
			for (int i = 1; i < recipeInputInventory.size(); ++i){
				if(!(recipeInputInventory.getStack(0).getItem() == recipeInputInventory.getStack(i).getItem()
						|| (recipeInputInventory.getStack(i).getItem() == ModItems.CUSTOM_DYE && i==4))){
					test = false;
					break;
				}
			}
			if(test){
				return true;
			}
		}
		for (int i = 0; i < recipeInputInventory.size(); ++i) {
			ItemStack itemStack2 = recipeInputInventory.getStack(i);
			if (itemStack2.isEmpty()) continue;
			if (itemStack2.getItem() instanceof DyeableItem) {
				if (itemStack.isEmpty()){
					itemStack = itemStack2;
					continue;
				}
				if (itemStack.getItem() == ModItems.CUSTOM_DYE && itemStack2.getItem() != ModItems.CUSTOM_DYE) {
					customColors.add(itemStack);
					itemStack = itemStack2;
					continue;
				} else if(itemStack.getItem() != ModItems.CUSTOM_DYE && itemStack2.getItem() != ModItems.CUSTOM_DYE) {
					return false;
				}
			}
			if (itemStack2.getItem() instanceof DyeItem) {
				list.add(itemStack2);
				continue;
			}
			if (itemStack2.getItem() == ModItems.CUSTOM_DYE) {
				customColors.add(itemStack2);
				continue;
			}
			return false;
		}
		return !itemStack.isEmpty() && (!list.isEmpty() || !customColors.isEmpty());
	}
	/**
	 * @author
	 * @reason
	 */
	@Overwrite
	public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
		ArrayList<DyeItem> list = Lists.newArrayList();
		ArrayList<ItemStack> customColors = Lists.newArrayList();
		ItemStack itemStack = ItemStack.EMPTY;
		ArrayList<Item> items = Lists.newArrayList();
		items.add(Items.GLASS);
		items.add(Items.TERRACOTTA);
		items.add(Items.WHITE_WOOL);
		boolean test = true;
		if(items.contains(recipeInputInventory.getStack(0).getItem())){
			for (int i = 1; i < recipeInputInventory.size(); ++i){
				if(recipeInputInventory.getStack(0).getItem() == recipeInputInventory.getStack(i).getItem()){
					itemStack = recipeInputInventory.getStack(0);
				} else if (recipeInputInventory.getStack(i).getItem() == ModItems.CUSTOM_DYE && i==4){
					customColors.add(recipeInputInventory.getStack(4));
				} else {
					test = false;
					itemStack = ItemStack.EMPTY;
					customColors = Lists.newArrayList();
					break;
				}
			}
			if(test){
				return UnidyeableItem.dyeVanillaItems(itemStack, customColors);
			}
		}
		for (int i = 0; i < recipeInputInventory.size(); ++i) {
			ItemStack itemStack2 = recipeInputInventory.getStack(i);
			if (itemStack2.isEmpty()) continue;
			Item item = itemStack2.getItem();
			if (item instanceof DyeableItem) {
				if (itemStack.isEmpty()){
					itemStack = itemStack2;
					continue;
				}
				if (itemStack.getItem() == ModItems.CUSTOM_DYE && itemStack2.getItem() != ModItems.CUSTOM_DYE) {
					customColors.add(itemStack);
					itemStack = itemStack2;
					continue;
				} else if(itemStack.getItem() != ModItems.CUSTOM_DYE && itemStack2.getItem() != ModItems.CUSTOM_DYE){
					return ItemStack.EMPTY;
				}
			}
			if (item instanceof DyeItem) {
				list.add((DyeItem)item);
				continue;
			}
			if (item == ModItems.CUSTOM_DYE){
				customColors.add(itemStack2);
				continue;
			}
			return ItemStack.EMPTY;
		}
		if (itemStack.isEmpty() || (list.isEmpty() && customColors.isEmpty())) {
			return ItemStack.EMPTY;
		}
		return UnidyeableItem.blendAndSetColor(itemStack, list, customColors);
	}
}