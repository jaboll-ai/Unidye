package net.diemond_player.unidye.recipes;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.DyeableLeatheryBlockItem;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

public class CustomStainedGlassDyeingRecipe extends CustomRecipe {
    public CustomStainedGlassDyeingRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack = container.getItem(i);
            if ((itemStack.is(Tags.Items.GLASS) && i != 4) // Replace with the correct tag for glass blocks
                    || (itemStack.getItem() == UnidyeItems.CUSTOM_DYE.get() && i == 4)) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack result = new ItemStack(UnidyeBlocks.CUSTOM_STAINED_GLASS.get().asItem());
        UnidyeUtils.setColor(result, CustomDyeItem.getMaterialColor(container.getItem(4), "glass"));
        DyeableLeatheryBlockItem.setLeatherColor(result, CustomDyeItem.getMaterialColor(container.getItem(4), "leather"));
        result.setCount(8);
        return result;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UnidyeSpecialRecipes.CUSTOM_STAINED_GLASS_DYEING_SERIALIZER.get();
    }
}

