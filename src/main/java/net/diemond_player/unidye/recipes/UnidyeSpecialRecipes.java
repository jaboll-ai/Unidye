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
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_terracotta_dyeing"),
                new SpecialRecipeSerializer<CustomTerracottaDyeingRecipe>(CustomTerracottaDyeingRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_stained_glass_dyeing"),
                new SpecialRecipeSerializer<CustomStainedGlassDyeingRecipe>(CustomStainedGlassDyeingRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_stained_glass_pane_dyeing"),
                new SpecialRecipeSerializer<CustomStainedGlassPaneDyeingRecipe>(CustomStainedGlassPaneDyeingRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_candle_dyeing"),
                new SpecialRecipeSerializer<CustomCandleDyeingRecipe>(CustomCandleDyeingRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_carpet_dyeing"),
                new SpecialRecipeSerializer<CustomCarpetDyeingRecipe>(CustomCarpetDyeingRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_wool_dyeing"),
                new SpecialRecipeSerializer<CustomWoolDyeingRecipe>(CustomWoolDyeingRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_concrete_powder"),
                new SpecialRecipeSerializer<CustomConcretePowderRecipe>(CustomConcretePowderRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_dye"),
                new SpecialRecipeSerializer<CustomDyeRecipe>(CustomDyeRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_banner"),
                new SpecialRecipeSerializer<CustomBannerRecipe>(CustomBannerRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_bed"),
                new SpecialRecipeSerializer<CustomBedRecipe>(CustomBedRecipe::new));
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, "custom_bed_dyeing"),
                new SpecialRecipeSerializer<CustomBedDyeingRecipe>(CustomBedDyeingRecipe::new));
    }
}
