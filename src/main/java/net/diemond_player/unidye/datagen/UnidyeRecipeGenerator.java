package net.diemond_player.unidye.datagen;

import net.diemond_player.unidye.Unidye;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

import static net.diemond_player.unidye.Unidye.MOD_ID;

public class UnidyeRecipeGenerator extends RecipeProvider {

    public UnidyeRecipeGenerator(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.RED_WOOL, 8)
                .pattern("WWW")
                .pattern("WDW")
                .pattern("WWW")
                .define('W', ItemTags.WOOL)
                .define('D', Items.RED_DYE)
                .unlockedBy("has_needed_dye", has(Items.RED_DYE))
                .group("wool_circle_dyeing")
                .showNotification(true)
                .save(pWriter, new ResourceLocation(MOD_ID,getSimpleRecipeName(Items.RED_WOOL) + "_from_circle_dyeing"));
    }
}
