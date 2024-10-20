package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableGlassBlockEntity;
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

public class DyeableGlassBlockItem extends DyeableBlockItem{
    public DyeableGlassBlockItem(Block block, Settings settings) {
        super(block, settings);
    }
    public static Integer getBeaconColor(ItemStack stack) {
        NbtCompound nbtCompound;
        nbtCompound = stack.getSubNbt("1");
        if (nbtCompound != null && nbtCompound.contains("beaconColor", NbtElement.NUMBER_TYPE)) {
                return nbtCompound.getInt("beaconColor");
        }
        return DEFAULT_COLOR;
    }

    public String getMaterialHexColor(ItemStack stack) {
        Integer color = getBeaconColor(stack);
        return "§7#" + Integer.toString(color, 16).toUpperCase();
    }

    public static void setBeaconColor(ItemStack itemStack, int n) {
        itemStack.getOrCreateSubNbt("1").putInt("beaconColor", n);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.unidye.beacon_color").append(getMaterialHexColor(stack)));
    }
    @Override
    public ActionResult place(ItemPlacementContext context) {
        BlockState blockstate = context.getWorld().getBlockState(context.getBlockPos());
        ActionResult result = super.place(context);
        BlockEntity blockEntity = context.getWorld().getBlockEntity(context.getBlockPos());
        if(blockEntity instanceof DyeableGlassBlockEntity dyeableBlockEntity
                && !blockstate.isOf(UnidyeBlocks.CUSTOM_STAINED_GLASS_PANE)){
            dyeableBlockEntity.color = UnidyeUtils.getColor(context.getStack());
            dyeableBlockEntity.beaconColor = getBeaconColor(context.getStack());
        }
        return result;
    }
}
