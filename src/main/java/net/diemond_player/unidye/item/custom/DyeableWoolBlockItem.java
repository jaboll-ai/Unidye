package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.block.entity.DyeableWoolBlockEntity;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
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

public class DyeableWoolBlockItem extends DyeableBlockItem {
    public DyeableWoolBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public static Integer getMaterialColor(ItemStack stack, String materialType) {
        NbtCompound nbtCompound;
        switch (materialType) {
            case "leather":
                nbtCompound = stack.getSubNbt("1");
                if (nbtCompound != null && nbtCompound.contains("bannerColor", NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt("bannerColor");
                }
                break;
            case "wool":
                nbtCompound = stack.getSubNbt("display");
                if (nbtCompound != null && nbtCompound.contains("color", NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt("color");
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
        switch (materialType) {
            case "leather":
                itemStack.getOrCreateSubNbt("1").putInt("bannerColor", n);
                break;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.unidye.banner_color").append(getMaterialHexColor(stack, "leather")));
    }

    @Override
    public ActionResult place(ItemPlacementContext context) {
        ActionResult result = super.place(context);
        BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
        if (blockEntity instanceof DyeableWoolBlockEntity dyeableBlockEntity) {
            dyeableBlockEntity.color = UnidyeUtils.getColor(context.getStack());
            dyeableBlockEntity.bannerColor = getMaterialColor(context.getStack(), "leather");
        }
        return result;
    }
}
