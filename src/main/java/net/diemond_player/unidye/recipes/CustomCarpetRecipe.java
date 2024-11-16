package net.diemond_player.unidye.recipes;

import net.diemond_player.unidye.block.UnidyeBlocks;
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

public class CustomCarpetRecipe extends CustomRecipe {
    public CustomCarpetRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        ItemStack itemStack = ItemStack.EMPTY;
        int count = 0;
        int[] positions = new int[2];
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemStack2 = pContainer.getItem(i);
            if (itemStack2.isEmpty()) continue;
            if (itemStack2.getItem() == UnidyeBlocks.CUSTOM_WOOL.get().asItem()) {
                if (count == 2) return false;
                positions[count] = i;
                count++;
                if (!itemStack.isEmpty()) {
                    if (UnidyeUtils.getColor(itemStack2) == UnidyeUtils.getColor(itemStack)
                            && DyeableLeatheryBlockItem.getLeatherColor(itemStack2) == DyeableLeatheryBlockItem.getLeatherColor(itemStack)) {
                        continue;
                    } else {
                        return false;
                    }
                }
                itemStack = itemStack2;
                continue;
            }
            return false;
        }
        if (pContainer.getContainerSize() == 9) {
            if (positions[0] == 2 || positions[0] == 5) {
                return false;
            }
        }
        if (pContainer.getContainerSize() == 4) {
            if (positions[0] == 1) {
                return false;
            }
        }
        return !itemStack.isEmpty() && positions[0] + 1 == positions[1];
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack1 = new ItemStack(UnidyeBlocks.CUSTOM_CARPET.get().asItem());
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemStack2 = pContainer.getItem(i);
            if (itemStack2.getItem() == UnidyeBlocks.CUSTOM_WOOL.get().asItem()) {
                itemStack = itemStack2;
            }
        }
        UnidyeUtils.setColor(itemStack1, UnidyeUtils.getColor(itemStack));
        itemStack1.setCount(3);
        return itemStack1;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= 2 && pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UnidyeSpecialRecipes.CUSTOM_CARPET_SERIALIZER.get();
    }
}
