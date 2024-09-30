package net.diemond_player.unidye.recipes;

import com.google.common.collect.Lists;
import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.UnidyeableItem;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;

public class CustomBedDyeingRecipe extends SpecialCraftingRecipe {
    public CustomBedDyeingRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        boolean bed = false;
        boolean difference = false;
        int count = 0;
        Item item = null;
        ItemStack itemStack = null;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack2 = inventory.getStack(i);
            if (itemStack2.isEmpty()) {
                continue;
            }
            if (itemStack2.getItem() instanceof DyeItem){
                if(item==null){
                    item = itemStack2.getItem();
                }else if(item!=itemStack2.getItem()){
                    difference = true;
                }
                continue;
            }
            if (itemStack2.getItem() instanceof CustomDyeItem){
                count++;
                if(item==null){
                    item = itemStack2.getItem();
                }else if(item!=itemStack2.getItem()){
                    difference = true;
                }
                if(itemStack==null){
                    itemStack = itemStack2;
                }else if(UnidyeUtils.getColor(itemStack)!=UnidyeUtils.getColor(itemStack2)){
                    difference = true;
                }
                continue;
            }
            if(itemStack2.isIn(ItemTags.BEDS) && itemStack2.getItem() != UnidyeBlocks.CUSTOM_BED.asItem()){
                if(bed){
                    return false;
                }
                bed=true;
                continue;
            }
            return false;
        }
        return bed && (difference || count==1);
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ArrayList<DyeItem> dyeList = Lists.newArrayList();
        ArrayList<ItemStack> customDyeList = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack2 = inventory.getStack(i);
            if (itemStack2.isEmpty()) continue;
            Item item = itemStack2.getItem();
            if (item instanceof DyeItem) {
                dyeList.add((DyeItem)item);
            }
            if (item instanceof CustomDyeItem) {
                customDyeList.add(itemStack2);
            }
        }
        if (customDyeList.isEmpty() && dyeList.isEmpty()) {
            return ItemStack.EMPTY;
        }
        return UnidyeableItem.blendAndSetColor(new ItemStack(UnidyeBlocks.CUSTOM_BED), dyeList, customDyeList);
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return new SpecialRecipeSerializer<CustomBedDyeingRecipe>(CustomBedDyeingRecipe::new);
    }
}
