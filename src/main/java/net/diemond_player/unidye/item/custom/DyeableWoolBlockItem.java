package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableBedBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableShulkerBoxBlockEntity;
import net.diemond_player.unidye.block.entity.DyeableWoolBlockEntity;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.diemond_player.unidye.item.custom.CustomDyeItem.DEFAULT_COLOR;
import static net.minecraft.block.HorizontalFacingBlock.FACING;

public class DyeableWoolBlockItem extends DyeableBlockItem{
    public DyeableWoolBlockItem(Block block, Settings settings) {
        super(block, settings);
    }
    public static Integer getMaterialColor(ItemStack stack, String materialType) {
        NbtCompound nbtCompound;
        switch(materialType) {
            case "leather":
                nbtCompound = stack.getSubNbt("1");
                if (nbtCompound != null && nbtCompound.contains("bannerColor", NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt("bannerColor");
                }
                break;
            case "bed":
                nbtCompound = stack.getSubNbt("2");
                if (nbtCompound != null && nbtCompound.contains("bedColor", NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt("bedColor");
                }
                break;
        }
        return DEFAULT_COLOR;
    }

    public String getMaterialHexColor(ItemStack stack, String materialType) {
        Integer color = getMaterialColor(stack, materialType);
        return "§7#" + Integer.toString(color, 16).toUpperCase();
    }

    public static void setMaterialColor(ItemStack itemStack, int n, String materialType) {
        switch (materialType){
            case "leather": itemStack.getOrCreateSubNbt("1").putInt("bannerColor", n); break;
            case "bed": itemStack.getOrCreateSubNbt("2").putInt("bedColor", n); break;
        }
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.unidye.banner_color").append(getMaterialHexColor(stack, "leather")));
        tooltip.add(Text.translatable("tooltip.unidye.bed_color").append(getMaterialHexColor(stack, "bed")));
    }
    @Override
    public ActionResult place(ItemPlacementContext context) {
        ActionResult result = super.place(context);
        BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
        if(blockEntity instanceof DyeableWoolBlockEntity dyeableBlockEntity){
            dyeableBlockEntity.color = UnidyeUtils.getColor(context.getStack());
            dyeableBlockEntity.bannerColor = getMaterialColor(context.getStack(), "leather");
            dyeableBlockEntity.bedColor = getMaterialColor(context.getStack(), "bed");
        }
        return result;
    }
}
