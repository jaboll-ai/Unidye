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

import java.util.Objects;

public class CustomStainedGlassPaneRecipe extends CustomRecipe {
    public CustomStainedGlassPaneRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        ItemStack itemStack = ItemStack.EMPTY;
        int count = 0;
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack2 = container.getItem(i);
            if (itemStack2.isEmpty()) continue;
            if (itemStack2.getItem() == UnidyeBlocks.CUSTOM_STAINED_GLASS.get().asItem()) {
                if (count == 6) return false;
                count++;
                if (!itemStack.isEmpty()) {
                    if (UnidyeUtils.getColor(itemStack2) == UnidyeUtils.getColor(itemStack)
                            && Objects.equals(DyeableLeatheryBlockItem.getLeatherColor(itemStack2), DyeableLeatheryBlockItem.getLeatherColor(itemStack))) {
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
        return count == 6 && (container.getItem(0).isEmpty() && container.getItem(1).isEmpty() && container.getItem(2).isEmpty()
                || container.getItem(6).isEmpty() && container.getItem(7).isEmpty() && container.getItem(8).isEmpty());
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack1 = new ItemStack(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE.get().asItem());
        for (int i = 0; i < container.getContainerSize(); ++i) {
            ItemStack itemStack2 = container.getItem(i);
            if (itemStack2.getItem() == UnidyeBlocks.CUSTOM_STAINED_GLASS.get().asItem()) {
                itemStack = itemStack2;
            }
        }
        UnidyeUtils.setColor(itemStack1, UnidyeUtils.getColor(itemStack));
        DyeableLeatheryBlockItem.setLeatherColor(itemStack1, DyeableLeatheryBlockItem.getLeatherColor(itemStack));
        itemStack1.setCount(16);
        return itemStack1;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width == 3 && height == 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UnidyeSpecialRecipes.CUSTOM_STAINED_GLASS_PANE.get();
    }
}
