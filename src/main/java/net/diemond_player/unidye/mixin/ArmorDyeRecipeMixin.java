package net.diemond_player.unidye.mixin;


import com.google.common.collect.Lists;
import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.item.UnidyeItems;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.ArmorDyeRecipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

@Mixin(ArmorDyeRecipe.class)
public abstract class ArmorDyeRecipeMixin {
    /**
     * @author JaBoll
     * @reason allows for Unidye logic
     */
    @Overwrite
    public boolean matches(CraftingContainer pInv, Level pLevel) {
        ItemStack itemStack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();
        ArrayList<ItemStack> customColors = Lists.newArrayList();
        for(int i = 0; i < pInv.getContainerSize(); ++i) {
            ItemStack itemStack2 = pInv.getItem(i);
            if (itemStack2.isEmpty()) continue;
            if (itemStack2.getItem() instanceof DyeableLeatherItem) {
                if (itemStack.isEmpty()) {
                    itemStack = itemStack2;
                    continue;
                }
                if (itemStack.getItem() == UnidyeItems.CUSTOM_DYE.get() && itemStack2.getItem() != UnidyeItems.CUSTOM_DYE.get()) {
                    customColors.add(itemStack);
                    itemStack = itemStack2;
                    continue;
                } else if (itemStack.getItem() != UnidyeItems.CUSTOM_DYE.get() && itemStack2.getItem() != UnidyeItems.CUSTOM_DYE.get()) {
                    return false;
                }
            }
            if (itemStack2.getItem() == UnidyeItems.CUSTOM_DYE.get()) {
                customColors.add(itemStack2);
                continue;
            }
            if (itemStack2.getItem() instanceof DyeItem) {
                list.add(itemStack2);
                continue;
            }
            return false;
        }
        return !itemStack.isEmpty() && (!list.isEmpty() || !customColors.isEmpty());
    }

    /**
     * @author JaBoll
     * @reason allows for Unidye logic
     */
    @Overwrite
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ArrayList<DyeItem> list = Lists.newArrayList();
        ArrayList<ItemStack> customColors = Lists.newArrayList();
        ItemStack itemStack = ItemStack.EMPTY;
        for (int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack itemStack2 = pContainer.getItem(i);
            if (itemStack2.isEmpty()) continue;
            Item item = itemStack2.getItem();
            if (item instanceof DyeableLeatherItem) {
                if (itemStack.isEmpty()) {
                    itemStack = itemStack2;
                    continue;
                }
                if (itemStack.getItem() instanceof CustomDyeItem && !(item instanceof CustomDyeItem)) {
                    customColors.add(itemStack);
                    itemStack = itemStack2;
                    continue;
                } else if (!(itemStack.getItem() instanceof CustomDyeItem) && !(item instanceof CustomDyeItem)) {
                    return ItemStack.EMPTY;
                }
            }
            if (item instanceof CustomDyeItem) {
                customColors.add(itemStack2);
                continue;
            }
            if (item instanceof DyeItem) {
                list.add((DyeItem) item);
                continue;
            }
            return ItemStack.EMPTY;
        }
        ItemStack itemStack1 = UnidyeUtils.blendAndSetColor(itemStack, list, customColors);
        if (itemStack1.is(UnidyeItems.CUSTOM_DYE.get())) {
            itemStack1.setCount(list.size() + customColors.size() + 1);
        }
        return itemStack1;
    }

}
