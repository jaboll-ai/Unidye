package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomDyeItem extends DyeItem implements SignChangingItem, DyeableItem {
    public static final String WOOL_KEY = "wool";
    public static final String WOOL_COLOR_KEY = "wool_color";
    public static final String CONCRETE_KEY = "concrete";
    public static final String CONCRETE_COLOR_KEY = "concrete_color";
    public static final String TERRACOTTA_KEY = "terracotta";
    public static final String TERRACOTTA_COLOR_KEY = "terracotta_color";
    public static final String GLASS_KEY = "glass";
    public static final String GLASS_COLOR_KEY = "glass_color";
    public static final String LEATHER_KEY = "leather";
    public static final String LEATHER_COLOR_KEY = "leather_color";
    public static final String SIGN_KEY = "sign";
    public static final String SIGN_COLOR_KEY = "sign_color";
    public static final String FIREWORK_KEY = "firework";
    public static final String FIREWORK_COLOR_KEY = "firework_color";
    public static final String SHULKER_BOX_KEY = "shulker_box";
    public static final String SHULKER_BOX_COLOR_KEY = "shulker_box_color";
    public static final String CANDLE_KEY = "candle_box";
    public static final String CANDLE_COLOR_KEY = "candle_color";
    public static final String CONTENTS_KEY = "contents";
    public static final String CONTENTS_LIST_KEY = "contents_list";
    public static final String CLOSEST_VANILLA_DYE_KEY = "closest_vanilla_dye";
    public static final String CLOSEST_VANILLA_DYE_ID_KEY = "closest_vanilla_dye_id";
    private static final Identifier SIGN_CUSTOM_COLOR_PACKET_ID = new Identifier("unidye", "sign_custom_color");
    public static final int DEFAULT_COLOR = 16777215;


    public CustomDyeItem(Settings settings) {
        super(DyeColor.CYAN, settings);
    }


    @Override
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(DISPLAY_KEY);
        if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
            return nbtCompound.getInt(COLOR_KEY);
        }
        return DEFAULT_COLOR;
    }

    public static float getClosestVanillaDyeId(ItemStack stack) {
        NbtCompound nbtCompound;
        nbtCompound = stack.getSubNbt(CLOSEST_VANILLA_DYE_KEY);
        float id;
        if (nbtCompound != null && nbtCompound.contains(CLOSEST_VANILLA_DYE_ID_KEY, NbtElement.NUMBER_TYPE)) {
            id = nbtCompound.getInt(CLOSEST_VANILLA_DYE_ID_KEY);
        }else{
            return 0;
        }
        return id/15;
    }

    public Integer getMaterialColor(ItemStack stack, String materialType) {
        NbtCompound nbtCompound;
        switch(materialType){
            case "wool":
                nbtCompound = stack.getSubNbt(WOOL_KEY);
                if (nbtCompound != null && nbtCompound.contains(WOOL_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(WOOL_COLOR_KEY);
                }
                break;
            case "concrete":
                nbtCompound = stack.getSubNbt(CONCRETE_KEY);
                if (nbtCompound != null && nbtCompound.contains(CONCRETE_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(CONCRETE_COLOR_KEY);
                }
                break;
            case "glass":
                nbtCompound = stack.getSubNbt(GLASS_KEY);
                if (nbtCompound != null && nbtCompound.contains(GLASS_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(GLASS_COLOR_KEY);
                }
                break;
            case "terracotta":
                nbtCompound = stack.getSubNbt(TERRACOTTA_KEY);
                if (nbtCompound != null && nbtCompound.contains(TERRACOTTA_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(TERRACOTTA_COLOR_KEY);
                }
                break;
            case "leather":
                nbtCompound = stack.getSubNbt(LEATHER_KEY);
                if (nbtCompound != null && nbtCompound.contains(LEATHER_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(LEATHER_COLOR_KEY);
                }
                break;
            case "dye":
                nbtCompound = stack.getSubNbt(DISPLAY_KEY);
                if (nbtCompound != null && nbtCompound.contains(COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(COLOR_KEY);
                }
                break;
            case "sign":
                nbtCompound = stack.getSubNbt(SIGN_KEY);
                if (nbtCompound != null && nbtCompound.contains(SIGN_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(SIGN_COLOR_KEY);
                }
                break;
            case "firework":
                nbtCompound = stack.getSubNbt(FIREWORK_KEY);
                if (nbtCompound != null && nbtCompound.contains(FIREWORK_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(FIREWORK_COLOR_KEY);
                }
                break;
            case "shulker_box":
                nbtCompound = stack.getSubNbt(SHULKER_BOX_KEY);
                if (nbtCompound != null && nbtCompound.contains(SHULKER_BOX_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(SHULKER_BOX_COLOR_KEY);
                }
                break;
            case "candle":
                nbtCompound = stack.getSubNbt(CANDLE_KEY);
                if (nbtCompound != null && nbtCompound.contains(CANDLE_COLOR_KEY, NbtElement.NUMBER_TYPE)) {
                    return nbtCompound.getInt(CANDLE_COLOR_KEY);
                }
                break;
        }
        return DEFAULT_COLOR;
    }

    public String getMaterialHexColor(ItemStack stack, String materialType) {
        Integer color = getMaterialColor(stack, materialType);
        return "§7#" + Integer.toString(color, 16).toUpperCase();
    }

    public void setMaterialColor(ItemStack itemStack, int n, String materialType) {
        switch (materialType){
            case "wool": itemStack.getOrCreateSubNbt(WOOL_KEY).putInt(WOOL_COLOR_KEY, n); break;
            case "glass": itemStack.getOrCreateSubNbt(GLASS_KEY).putInt(GLASS_COLOR_KEY, n); break;
            case "concrete": itemStack.getOrCreateSubNbt(CONCRETE_KEY).putInt(CONCRETE_COLOR_KEY, n); break;
            case "terracotta": itemStack.getOrCreateSubNbt(TERRACOTTA_KEY).putInt(TERRACOTTA_COLOR_KEY, n); break;
            case "leather": itemStack.getOrCreateSubNbt(LEATHER_KEY).putInt(LEATHER_COLOR_KEY, n); break;
            case "dye": itemStack.getOrCreateSubNbt(DISPLAY_KEY).putInt(COLOR_KEY, n); break;
            case "sign": itemStack.getOrCreateSubNbt(SIGN_KEY).putInt(SIGN_COLOR_KEY, n); break;
            case "firework": itemStack.getOrCreateSubNbt(FIREWORK_KEY).putInt(FIREWORK_COLOR_KEY, n); break;
            case "shulker_box": itemStack.getOrCreateSubNbt(SHULKER_BOX_KEY).putInt(SHULKER_BOX_COLOR_KEY, n); break;
            case "candle": itemStack.getOrCreateSubNbt(CANDLE_KEY).putInt(CANDLE_COLOR_KEY, n); break;
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.unidye.wool_color").append(getMaterialHexColor(stack, "wool")));
            tooltip.add(Text.translatable("tooltip.unidye.sign_color").append(getMaterialHexColor(stack, "sign")));
            tooltip.add(Text.translatable("tooltip.unidye.glass_color").append(getMaterialHexColor(stack, "glass")));
            tooltip.add(Text.translatable("tooltip.unidye.candle_color").append(getMaterialHexColor(stack, "candle")));
            tooltip.add(Text.translatable("tooltip.unidye.firework_color").append(getMaterialHexColor(stack, "firework")));
            tooltip.add(Text.translatable("tooltip.unidye.concrete_color").append(getMaterialHexColor(stack, "concrete")));
            tooltip.add(Text.translatable("tooltip.unidye.terracotta_color").append(getMaterialHexColor(stack, "terracotta")));
            tooltip.add(Text.translatable("tooltip.unidye.shulker_box_color").append(getMaterialHexColor(stack, "shulker_box")));
            tooltip.add(Text.translatable("tooltip.unidye.leather_color").append(getMaterialHexColor(stack, "leather")));
        } else {
            tooltip.add(Text.translatable("tooltip.unidye.press_shift"));
        }
        //tooltip.add(Text.translatable("tooltip.unidye.contents").append(getContents(stack)));
    }

    public String getContents(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt(CONTENTS_KEY);
        if (nbtCompound != null && nbtCompound.contains(CONTENTS_LIST_KEY, NbtElement.STRING_TYPE)) {
            return nbtCompound.getString(CONTENTS_LIST_KEY);
        }
        return "None";
    }

    public void setContents(ItemStack itemStack, String contents) {
        itemStack.getOrCreateSubNbt(CONTENTS_KEY).putString(CONTENTS_LIST_KEY, contents);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        SheepEntity sheepEntity;
        if (entity instanceof SheepEntity
                && (sheepEntity = (SheepEntity)entity).isAlive()
                && !sheepEntity.isSheared()) {
            sheepEntity.getWorld().playSoundFromEntity(user, sheepEntity,
                    SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            if (!user.getWorld().isClient) {
                IEntityAccessor sheep = (IEntityAccessor) sheepEntity;
                sheep.unidye$setCustomColor(getMaterialColor(stack, "leather"));
                sheep.unidye$setSecondaryCustomColor(getMaterialColor(stack, "wool"));
                stack.decrement(1);
            }
            return ActionResult.success(user.getWorld().isClient);
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean useOnSign(World world, SignBlockEntity signBlockEntity, boolean front, PlayerEntity player) {
        IEntityAccessor iEntityAccessor = (IEntityAccessor) signBlockEntity;
        if (iEntityAccessor.unidye$getCustomColor() != getMaterialColor(player.getStackInHand(player.getActiveHand()), "sign")) {
            world.playSound(null, signBlockEntity.getPos(), SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if(front){
                iEntityAccessor.unidye$setCustomColor(getMaterialColor(player.getStackInHand(player.getActiveHand()), "sign"));
            }else{
                iEntityAccessor.unidye$setSecondaryCustomColor(getMaterialColor(player.getStackInHand(player.getActiveHand()), "sign"));
            }
            signBlockEntity.markDirty();
            world.updateListeners(signBlockEntity.getPos(), world.getBlockState(signBlockEntity.getPos()), world.getBlockState(signBlockEntity.getPos()), Block.NOTIFY_LISTENERS);
            return true;
        }
        return false;
    }
}