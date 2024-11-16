package net.diemond_player.unidye.recipes;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class CustomTerracottaDyeingRecipe extends CustomRecipe {
    public CustomTerracottaDyeingRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemStack2 = pContainer.getItem(i);
            if ((itemStack2.is(ItemTags.TERRACOTTA) && i != 4)
                    || (itemStack2.getItem() == UnidyeItems.CUSTOM_DYE.get() && i == 4)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack itemStack1 = new ItemStack(UnidyeBlocks.CUSTOM_TERRACOTTA.get().asItem());
        UnidyeUtils.setColor(itemStack1, CustomDyeItem.getMaterialColor(pContainer.getItem(4), "terracotta"));
        itemStack1.setCount(8);
        return itemStack1;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= 3 && pHeight >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UnidyeSpecialRecipes.CUSTOM_TERRACOTTA_DYEING_SERIALIZER.get();
    }
}
