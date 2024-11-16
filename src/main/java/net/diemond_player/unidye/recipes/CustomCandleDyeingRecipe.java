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

public class CustomCandleDyeingRecipe extends CustomRecipe {
    public CustomCandleDyeingRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if ((itemStack.is(ItemTags.CANDLES) && i != 4)
                    || (itemStack.getItem() == UnidyeItems.CUSTOM_DYE.get() && i == 4)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack itemStack = new ItemStack(UnidyeBlocks.CUSTOM_CANDLE.get().asItem());
        UnidyeUtils.setColor(itemStack, CustomDyeItem.getMaterialColor(container.getItem(4), "concrete"));
        itemStack.setCount(8);
        return itemStack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UnidyeSpecialRecipes.CUSTOM_CANDLE_DYEING_SERIALIZER.get();
    }
}
