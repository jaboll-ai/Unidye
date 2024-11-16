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
    public static final RegistryObject<RecipeSerializer<CustomConcretePowderRecipe>> CUSTOM_CONCRETE_POWDER_SERIALIZER = RECIPES.register("custom_concrete_powder",
            () -> new SimpleCraftingRecipeSerializer<>(CustomConcretePowderRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomTerracottaDyeingRecipe>> CUSTOM_TERRACOTTA_DYEING_SERIALIZER = RECIPES.register("custom_terracotta_dyeing",
            () -> new SimpleCraftingRecipeSerializer<>(CustomTerracottaDyeingRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomCarpetRecipe>> CUSTOM_CARPET_SERIALIZER = RECIPES.register("custom_carpet",
            () -> new SimpleCraftingRecipeSerializer<>(CustomCarpetRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomCarpetDyeingRecipe>> CUSTOM_CARPET_DYEING_SERIALIZER = RECIPES.register("custom_carpet_dyeing",
            () -> new SimpleCraftingRecipeSerializer<>(CustomCarpetDyeingRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomWoolDyeingRecipe>> CUSTOM_WOOL_DYEING_SERIALIZER = RECIPES.register("custom_wool_dyeing",
            () -> new SimpleCraftingRecipeSerializer<>(CustomWoolDyeingRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomCandleDyeingRecipe>> CUSTOM_CANDLE_DYEING_SERIALIZER = RECIPES.register("custom_candle_dyeing",
                () -> new SimpleCraftingRecipeSerializer<>(CustomCandleDyeingRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomStainedGlassDyeingRecipe>> CUSTOM_STAINED_GLASS_DYEING_SERIALIZER = RECIPES.register("custom_stained_glass_dyeing",
                () -> new SimpleCraftingRecipeSerializer<>(CustomStainedGlassDyeingRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomStainedGlassPaneRecipe>> CUSTOM_STAINED_GLASS_PANE = RECIPES.register("custom_stained_glass_pane",
            () -> new SimpleCraftingRecipeSerializer<>(CustomStainedGlassPaneRecipe::new));
    public static final RegistryObject<RecipeSerializer<CustomStainedGlassDyeingRecipe>> CUSTOM_STAINED_GLASS_PANE_DYEING_SERIALIZER = RECIPES.register("custom_stained_glass_pane_dyeing",
            () -> new SimpleCraftingRecipeSerializer<>(CustomStainedGlassDyeingRecipe::new));


    public static void registerSpecialRecipes(IEventBus modEventBus) {
        Unidye.LOGGER.info("Registering Recipes for " + Unidye.MOD_ID);
        RECIPES.register(modEventBus);
    }

}
