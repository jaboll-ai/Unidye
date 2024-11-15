package net.diemond_player.unidye.recipes;

import net.diemond_player.unidye.Unidye;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.diemond_player.unidye.Unidye.MOD_ID;

public class UnidyeSpecialRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_SERIALIZER, MOD_ID);
    public static final RegistryObject<RecipeSerializer<CustomDyeRecipe>> CUSTOM_DYE_SERIALIZER = RECIPES.register("custom_dye",
            () -> new SimpleCraftingRecipeSerializer<>(CustomDyeRecipe::new));

    public static void registerSpecialRecipes(IEventBus modEventBus) {
        Unidye.LOGGER.info("Registering Recipes for " + Unidye.MOD_ID);
        RECIPES.register(modEventBus);
    }

}
