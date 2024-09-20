package net.diemond_player.unidye.recipes;

import net.diemond_player.unidye.Unidye;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class UnidyeSpecialRecipes {
    public static void registerSpecialRecipes(){
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_carpet"),
                new SpecialRecipeSerializer<CustomCarpetRecipe>(CustomCarpetRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_stained_glass_pane"),
                new SpecialRecipeSerializer<CustomStainedGlassPaneRecipe>(CustomStainedGlassPaneRecipe::new));
    }
}
