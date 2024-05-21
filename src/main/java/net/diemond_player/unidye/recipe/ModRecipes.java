package net.diemond_player.unidye.recipe;

import net.diemond_player.unidye.Unidye;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Unidye.MOD_ID, BlockDyeingRecipe.Serializer.ID),
                BlockDyeingRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(Unidye.MOD_ID, BlockDyeingRecipe.Type.ID),
                BlockDyeingRecipe.Type.INSTANCE);

    }
}
