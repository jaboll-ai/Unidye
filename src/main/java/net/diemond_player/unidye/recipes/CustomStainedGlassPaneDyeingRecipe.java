package net.diemond_player.unidye.recipes;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.util.UnidyeTags;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class CustomStainedGlassPaneDyeingRecipe extends SpecialCraftingRecipe {
    public CustomStainedGlassPaneDyeingRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack2 = inventory.getStack(i);
            if ((itemStack2.isIn(UnidyeTags.Items.GLASS_PANE) && i!=4)
                    ||(itemStack2.getItem() == UnidyeItems.CUSTOM_DYE && i==4)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        ItemStack itemStack1 = new ItemStack(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.asItem());
        UnidyeUtils.setColor(itemStack1, UnidyeUtils.getMaterialColor(inventory.getStack(4), "glass"));
        itemStack1.setCount(8);
        return itemStack1;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return new SpecialRecipeSerializer<CustomStainedGlassPaneDyeingRecipe>(CustomStainedGlassPaneDyeingRecipe::new);
    }
}
