package net.diemond_player.unidye.item;

import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.Unidye;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup MUFFIN_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Unidye.MOD_ID, "unidye"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.unidye"))
                    .icon(() -> new ItemStack(ModItems.CUSTOM_DYE)).entries((displayContext, entries) -> {

                        entries.add(ModItems.CUSTOM_DYE);

                        entries.add(ModBlocks.CUSTOM_WOOL);
                        entries.add(ModBlocks.CUSTOM_CONCRETE);
                        entries.add(ModBlocks.CUSTOM_TERRACOTTA);
                        entries.add(ModBlocks.CUSTOM_STAINED_GLASS);

                    }).build());
    public static void registerItemGroups(){
        Unidye.LOGGER.info("Registering Item Groups for" + Unidye.MOD_ID);
    }
}
