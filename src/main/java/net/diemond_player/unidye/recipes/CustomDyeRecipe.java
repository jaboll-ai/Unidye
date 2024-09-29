package net.diemond_player.unidye.recipes;

import com.google.common.collect.Lists;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.UnidyeableItem;
import net.diemond_player.unidye.util.UnidyeTags;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.*;
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
import java.util.List;

public class CustomDyeRecipe extends SpecialCraftingRecipe {
    public CustomDyeRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        boolean stick = false;
        boolean difference = false;
        boolean polymorph = FabricLoader.getInstance().isModLoaded("polymorph");
        Item item = null;
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
            if(itemStack2.getItem() == Items.STICK){
                if(stick){
                    return false;
                }
                stick=true;
                continue;
            }
            return false;
        }
        return (stick || polymorph) && difference;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ArrayList<DyeItem> list = Lists.newArrayList();
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack2 = inventory.getStack(i);
            if (itemStack2.isEmpty()) continue;
            Item item = itemStack2.getItem();
            if (item instanceof DyeItem) {
                list.add((DyeItem)item);
            }
        }
        if (list.isEmpty()) {
            return ItemStack.EMPTY;
        }
        ItemStack itemStack = UnidyeableItem.blendAndSetColor(new ItemStack(UnidyeItems.CUSTOM_DYE), list, Lists.newArrayList());
        itemStack.decrement(1);
        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 2 && height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return new SpecialRecipeSerializer<CustomDyeRecipe>(CustomDyeRecipe::new);
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(RecipeInputInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            Item item = inventory.getStack(i).getItem();
            if (item == Items.STICK){
                defaultedList.set(i, new ItemStack(Items.STICK));
            }
        }
        return defaultedList;
    }
}
