package net.diemond_player.unidye;

import net.diemond_player.unidye.datagen.UnidyeRecipeGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.diemond_player.unidye.Unidye.LOGGER;
import static net.diemond_player.unidye.Unidye.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class UnidyeDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        LOGGER.debug("qwertz");
        generator.addProvider(event.includeServer(), new UnidyeRecipeGenerator(output));
    }

}
