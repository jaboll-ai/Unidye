package net.diemond_player.unidye.item.custom;

import net.diemond_player.unidye.Unidye;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.SignBlock;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomDyeItem extends Item implements SignChangingItem, DyeableItem {
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
    public static final String CONTENTS_KEY = "contents";
    public static final String CONTENTS_LIST_KEY = "contents_list";
    public static final String CLOSEST_VANILLA_DYE_KEY = "closest_vanilla_dye";
    public static final String CLOSEST_VANILLA_DYE_ID_KEY = "closest_vanilla_dye_id";
    private static final Identifier SIGN_CUSTOM_COLOR_PACKET_ID = new Identifier("unidye", "sign_custom_color");
    public static final int DEFAULT_COLOR = 16777215;


    public CustomDyeItem(Settings settings) {
        super(settings);
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
        int id = -1;
        if (nbtCompound != null && nbtCompound.contains(CLOSEST_VANILLA_DYE_ID_KEY, NbtElement.NUMBER_TYPE)) {
            id =  nbtCompound.getInt(CLOSEST_VANILLA_DYE_ID_KEY);
        }
        return switch (id) {
            case 0 -> 0f;
            case 1 -> 1f / 11;
            case 3 -> 2f / 11;
            case 4 -> 3f / 11;
            case 8 -> 4f / 11;
            case 9 -> 5f / 11;
            case 11 -> 6f / 11;
            case 12 -> 7f / 11;
            case 13 -> 8f / 11;
            case 14 -> 9f / 11;
            case 15 -> 10f / 11;
            case 2, 6, 5, 7, 10 -> 1f;
            default -> -1;
        };
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
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.unidye.wool_color").append(getMaterialHexColor(stack, "wool")));
            tooltip.add(Text.translatable("tooltip.unidye.glass_color").append(getMaterialHexColor(stack, "glass")));
            tooltip.add(Text.translatable("tooltip.unidye.leather_color").append(getMaterialHexColor(stack, "leather")));
            tooltip.add(Text.translatable("tooltip.unidye.concrete_color").append(getMaterialHexColor(stack, "concrete")));
            tooltip.add(Text.translatable("tooltip.unidye.terracotta_color").append(getMaterialHexColor(stack, "terracotta")));
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
                sheep.unidye$setCustomColor(getMaterialColor(stack, "wool"));
                stack.decrement(1);
            }
            return ActionResult.success(user.getWorld().isClient);
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean useOnSign(World world, SignBlockEntity signBlockEntity, boolean front, PlayerEntity player) {
        IEntityAccessor iEntityAccessor = (IEntityAccessor) signBlockEntity;
        if (iEntityAccessor.unidye$getCustomColor() != getMaterialColor(player.getStackInHand(player.getActiveHand()), "concrete")) {
            world.playSound(null, signBlockEntity.getPos(), SoundEvents.ITEM_DYE_USE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if(front){
                iEntityAccessor.unidye$setCustomColor(getMaterialColor(player.getStackInHand(player.getActiveHand()), "concrete"));
            }else{
                iEntityAccessor.unidye$setCustomColorBack(getMaterialColor(player.getStackInHand(player.getActiveHand()), "concrete"));
            }
            signBlockEntity.markDirty();
            world.updateListeners(signBlockEntity.getPos(), world.getBlockState(signBlockEntity.getPos()), world.getBlockState(signBlockEntity.getPos()), Block.NOTIFY_LISTENERS);
            return true;
        }
        return false;
    }
}