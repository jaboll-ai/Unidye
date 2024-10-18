package net.diemond_player.unidye.recipes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.DyeableWoolBlockItem;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class CustomFireworkStarRecipe extends SpecialCraftingRecipe {
    public CustomFireworkStarRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    private static final Ingredient INPUT_STAR = Ingredient.ofItems(Items.FIREWORK_STAR);

    @Override
    public boolean matches(RecipeInputInventory recipeInputInventory, World world) {
        boolean bl = false;
        boolean bl2 = false;
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack = recipeInputInventory.getStack(i);
            if (itemStack.isEmpty()) continue;
            if (itemStack.getItem() instanceof DyeItem) {
                bl = true;
                continue;
            }
            if (INPUT_STAR.test(itemStack)) {
                if (bl2) {
                    return false;
                }
                bl2 = true;
                continue;
            }
            return false;
        }
        return bl2 && bl;
    }

    @Override
    public ItemStack craft(RecipeInputInventory recipeInputInventory, DynamicRegistryManager dynamicRegistryManager) {
        ArrayList<Integer> list = Lists.newArrayList();
        ItemStack itemStack = null;
        for (int i = 0; i < recipeInputInventory.size(); ++i) {
            ItemStack itemStack2 = recipeInputInventory.getStack(i);
            Item item = itemStack2.getItem();
            if (item instanceof CustomDyeItem) {
                list.add(UnidyeUtils.getMaterialColor(itemStack2, "leather"));
                continue;
            }
            if (item instanceof DyeItem) {
                list.add(((DyeItem)item).getColor().getFireworkColor());
                continue;
            }
            if (!INPUT_STAR.test(itemStack2)) continue;
            itemStack = itemStack2.copyWithCount(1);
        }
        if (itemStack == null || list.isEmpty()) {
            return ItemStack.EMPTY;
        }
        itemStack.getOrCreateSubNbt("Explosion").putIntArray("FadeColors", list);
        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return new SpecialRecipeSerializer<CustomFireworkStarRecipe>(CustomFireworkStarRecipe::new);
    }
}
