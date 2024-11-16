package net.diemond_player.unidye.recipes;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class CustomConcretePowderRecipe extends CustomRecipe {
    public CustomConcretePowderRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {super(pId, pCategory);}

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        int gravelCount = 0;
        int sandCount = 0;
        boolean dye = false;
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            Item item = pContainer.getItem(i).getItem();
            if (item == Items.SAND) {
                sandCount++;
                continue;
            } else if (item == Items.GRAVEL) {
                gravelCount++;
                continue;
            } else if (item == UnidyeItems.CUSTOM_DYE.get()) {
                dye = true;
                continue;
            }
            return false;
        }
        Unidye.LOGGER.debug("crafting is matching: "+ String.valueOf(sandCount == 4 && gravelCount == 4 && dye));
        return sandCount == 4 && gravelCount == 4 && dye;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack itemStack = ItemStack.EMPTY;
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            if (pContainer.getItem(i).getItem() == UnidyeItems.CUSTOM_DYE.get()) {
                itemStack = pContainer.getItem(i);
            }
        }
        ItemStack itemStack1 = new ItemStack(UnidyeBlocks.CUSTOM_CONCRETE_POWDER.get().asItem());
        UnidyeUtils.setColor(itemStack1, CustomDyeItem.getMaterialColor(itemStack, "concrete"));
        itemStack1.setCount(8);
        return itemStack1;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= 3 && pHeight >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return UnidyeSpecialRecipes.CUSTOM_CONCRETE_POWDER_SERIALIZER.get();
    }
}
