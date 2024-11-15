package net.diemond_player.unidye.item.custom;


import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.util.UnidyeAccessor;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomDyeItem extends DyeItem implements SignApplicator, DyeableLeatherItem {

    public static final String CLOSEST_VANILLA_DYE_ID_KEY = "closest_vanilla_dye_id";
    public static final int DEFAULT_COLOR = 16777215;

    public CustomDyeItem(Properties properties) {
        super(DyeColor.WHITE, properties);
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTagElement(TAG_DISPLAY);
        if (nbtCompound != null && nbtCompound.contains(TAG_COLOR, CompoundTag.TAG_ANY_NUMERIC)) {
            return nbtCompound.getInt(TAG_COLOR);
        }
        return DEFAULT_COLOR;
    }

    public static float getClosestVanillaDyeId(ItemStack stack) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        float id;
        if (nbtCompound.contains(CLOSEST_VANILLA_DYE_ID_KEY, CompoundTag.TAG_ANY_NUMERIC)) {
            id = nbtCompound.getInt(CLOSEST_VANILLA_DYE_ID_KEY);
        } else {
            return 0;
        }
        return id / 15;
    }

    public static Integer getMaterialColor(ItemStack stack, String materialType) {
        CompoundTag nbtCompound = stack.getTag();
        if (nbtCompound != null && nbtCompound.contains(materialType, CompoundTag.TAG_ANY_NUMERIC)) {
            return nbtCompound.getInt(materialType);
        }
        return DEFAULT_COLOR;
    }

    public static String getMaterialHexColor(ItemStack stack, String materialType) {
        Integer color = getMaterialColor(stack, materialType);
        return String.format("#%06X", (0xFFFFFF & color));
    }

    public static void setMaterialColor(ItemStack itemStack, int n, String materialType) {
        CompoundTag nbtCompound = itemStack.getOrCreateTag();
        nbtCompound.putInt(materialType, n);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            MutableComponent mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "wool"))).append(Component.translatable("tooltip.unidye.wool_color").append(getMaterialHexColor(pStack, "wool")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "sign"))).append(Component.translatable("tooltip.unidye.sign_color").append(getMaterialHexColor(pStack, "sign")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "glass"))).append(Component.translatable("tooltip.unidye.glass_color").append(getMaterialHexColor(pStack, "glass")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "candle"))).append(Component.translatable("tooltip.unidye.candle_color").append(getMaterialHexColor(pStack, "candle")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "firework"))).append(Component.translatable("tooltip.unidye.firework_color").append(getMaterialHexColor(pStack, "firework")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "concrete"))).append(Component.translatable("tooltip.unidye.concrete_color").append(getMaterialHexColor(pStack, "concrete")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "terracotta"))).append(Component.translatable("tooltip.unidye.terracotta_color").append(getMaterialHexColor(pStack, "terracotta")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "shulker_box"))).append(Component.translatable("tooltip.unidye.shulker_box_color").append(getMaterialHexColor(pStack, "shulker_box")).withStyle(ChatFormatting.GRAY)));
            mutableText = Component.literal("■ ");
            pTooltipComponents.add(mutableText.setStyle(mutableText.getStyle().withColor(getMaterialColor(pStack, "leather"))).append(Component.translatable("tooltip.unidye.leather_color").append(getMaterialHexColor(pStack, "leather")).withStyle(ChatFormatting.GRAY)));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack pStack, Player pPlayer, LivingEntity pTarget, InteractionHand pHand) {
        if (pTarget instanceof Sheep sheepEntity
                && sheepEntity.isAlive()
                && !sheepEntity.isSheared()) {
            sheepEntity.level().playSound(pPlayer, sheepEntity,
                    SoundEvents.DYE_USE, SoundSource.PLAYERS, 1.0f, 1.0f);
            if (!pTarget.level().isClientSide()) {
                UnidyeAccessor sheep = (UnidyeAccessor) sheepEntity;
                sheep.unidye$setCustomColor(getMaterialColor(pStack, "leather"));
                sheep.unidye$setSecondaryCustomColor(getMaterialColor(pStack, "wool"));
                pStack.shrink(1);
            }
            return InteractionResult.sidedSuccess(pPlayer.level().isClientSide());
        }
        return InteractionResult.PASS;
    }



    @Override
    public boolean tryApplyToSign(Level world, SignBlockEntity signBlockEntity, boolean front, Player player) {
        UnidyeAccessor unidyeAccessor = (UnidyeAccessor) signBlockEntity;
        if (unidyeAccessor.unidye$getCustomColor() != getMaterialColor(player.getItemInHand(player.getUsedItemHand()), "sign")) {
            world.playSound(null, signBlockEntity.getBlockPos(), SoundEvents.DYE_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
            if (front) {
                unidyeAccessor.unidye$setCustomColor(getMaterialColor(player.getItemInHand(player.getUsedItemHand()), "sign"));
            } else {
                unidyeAccessor.unidye$setSecondaryCustomColor(getMaterialColor(player.getItemInHand(player.getUsedItemHand()), "sign"));
            }
            signBlockEntity.setChanged();
            world.sendBlockUpdated(signBlockEntity.getBlockPos(), world.getBlockState(signBlockEntity.getBlockPos()), world.getBlockState(signBlockEntity.getBlockPos()), Block.UPDATE_CLIENTS);
            return true;
        }
        return false;
    }
}