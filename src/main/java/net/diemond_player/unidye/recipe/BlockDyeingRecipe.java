package net.diemond_player.unidye.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.diemond_player.unidye.item.custom.DyeableBlockItem;
import net.diemond_player.unidye.item.custom.UnidyeableItem;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.ArrayList;

public class BlockDyeingRecipe implements Recipe<SimpleInventory> {

    @Override
    public boolean matches(SimpleInventory simpleInventory, World world) {
        ItemStack itemStack = ItemStack.EMPTY;
        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < simpleInventory.size(); ++i) {
            ItemStack itemStack2 = simpleInventory.getStack(i);
            if (itemStack2.isEmpty()) continue;
            if (itemStack2.getItem() instanceof UnidyeableItem) {
                if (!itemStack.isEmpty()) {
                    return false;
                }
                itemStack = itemStack2;
                continue;
            }
            if (itemStack2.getItem() instanceof DyeItem) {
                list.add(itemStack2);
                continue;
            }
            return false;
        }
        return !itemStack.isEmpty() && !list.isEmpty();
    }

    ItemStack output;

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        ArrayList<DyeItem> list = Lists.newArrayList();
        ItemStack itemStack = ItemStack.EMPTY;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack itemStack2 = inventory.getStack(i);
            if (itemStack2.isEmpty()) continue;
            Item item = itemStack2.getItem();
            if (item instanceof UnidyeableItem) {
                if (!itemStack.isEmpty()) {
                    output = ItemStack.EMPTY;
                    return output;
                }
                itemStack = itemStack2.copy();
                continue;
            }
            if (item instanceof DyeItem) {
                list.add((DyeItem)item);
                continue;
            }
            output = ItemStack.EMPTY;
            return output;
        }
        if (itemStack.isEmpty() || list.isEmpty()) {
            output = ItemStack.EMPTY;
            return output;
        }
        output = UnidyeableItem.blendAndSetColor(itemStack, list);
        return output;
    }


    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public Identifier getId() {
        return null;
    }


    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<BlockDyeingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "block_dyeing";
    }

    public static class Serializer implements RecipeSerializer<BlockDyeingRecipe> {
        public static final Serializer INSTANCE = new Serializer();;
        public static final String ID = "block_dyeing";

        @Override
        public BlockDyeingRecipe read(Identifier id, JsonObject json) {
            return null;
        }

        @Override
        public BlockDyeingRecipe read(Identifier id, PacketByteBuf buf) {
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, BlockDyeingRecipe recipe) {

        }
    }
}
