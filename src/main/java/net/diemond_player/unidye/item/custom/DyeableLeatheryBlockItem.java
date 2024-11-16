package net.diemond_player.unidye.item.custom;



import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.block.UnidyeBlocks;
import net.diemond_player.unidye.block.entity.DyeableLeatheryBlockEntity;
import net.diemond_player.unidye.util.UnidyeUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DyeableLeatheryBlockItem extends DyeableBlockItem {

    public DyeableLeatheryBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    public static int getLeatherColor(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTag();
        if (nbtCompound != null && nbtCompound.contains("leather", CompoundTag.TAG_ANY_NUMERIC)) {
            return nbtCompound.getInt("leather");
        }
        return DEFAULT_COLOR;
    }
    public String getLeatherHexColor(ItemStack stack) {
        int color = getLeatherColor(stack);
        return String.format("#%06X", (0xFFFFFF & color));
    }

    public static void setLeatherColor(ItemStack itemStack, int n) {
        itemStack.getOrCreateTag().putInt("leather", n);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        MutableComponent mutableText = Component.literal("■ ");
        if(pStack.is(UnidyeBlocks.CUSTOM_WOOL.get().asItem())) {
            pTooltip.add(mutableText.setStyle(mutableText.getStyle().withColor(getLeatherColor(pStack))).append(Component.translatable("tooltip.unidye.banner_color").append(getLeatherHexColor(pStack)).withStyle(ChatFormatting.GRAY)));
        }else{
            pTooltip.add(mutableText.setStyle(mutableText.getStyle().withColor(getLeatherColor(pStack))).append(Component.translatable("tooltip.unidye.beacon_color").append(getLeatherHexColor(pStack)).withStyle(ChatFormatting.GRAY)));
        }
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        InteractionResult result = super.place(context);
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if (blockEntity instanceof DyeableLeatheryBlockEntity dyeableBlockEntity) {
            dyeableBlockEntity.color = UnidyeUtils.getColor(context.getItemInHand());
            dyeableBlockEntity.leatherColor = getLeatherColor(context.getItemInHand());
        }
        return result;
    }
}
