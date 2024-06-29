package net.diemond_player.unidye.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.diemond_player.unidye.block.ModBlocks;
import net.diemond_player.unidye.block.custom.DyeableBlock;
import net.diemond_player.unidye.item.custom.CustomDyeItem;
import net.diemond_player.unidye.item.custom.DyeableBlockItem;
import net.diemond_player.unidye.item.custom.UnidyeColor;
import net.diemond_player.unidye.item.custom.UnidyeableItem;
import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin implements IEntityAccessor {
    @Unique
    private static final TrackedData<Integer> CUSTOM_COLOR = DataTracker.registerData(SheepEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("unidye.custom_color", unidye$getCustomColor());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("unidye.custom_color")) {
            unidye$setCustomColor(nbt.getInt("unidye.custom_color"));
        }
    }

    @Inject(method = "initDataTracker", at = @At("HEAD"))
    private void initDataTracker(CallbackInfo ci){
        ((SheepEntity) (Object) this).getDataTracker().startTracking(CUSTOM_COLOR, 0xFFFFFF);
    }

    @Inject(method = "getLootTableId", at = @At("HEAD"), cancellable = true)
    private void getLootTableId(CallbackInfoReturnable<Identifier> cir){
        IEntityAccessor sheep = (IEntityAccessor) ((SheepEntity) (Object) this);
        if(sheep.unidye$getCustomColor() != 0xFFFFFF){
            cir.setReturnValue(((SheepEntity) (Object) this).getType().getLootTableId());
        }
    }

    @Inject(method = "createChild(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/PassiveEntity;)Lnet/minecraft/entity/passive/PassiveEntity;", at = @At("HEAD"), cancellable = true)
    private void createChild(ServerWorld world, PassiveEntity entity, CallbackInfoReturnable<PassiveEntity> cir){
        SheepEntity sheepEntity = (SheepEntity)EntityType.SHEEP.create(world);
        if (sheepEntity != null) {
            IEntityAccessor firstSheep = (IEntityAccessor) ((SheepEntity) (Object) this);
            IEntityAccessor secondSheep = (IEntityAccessor) entity;
            IEntityAccessor sheep = (IEntityAccessor) sheepEntity;
            if(firstSheep.unidye$getCustomColor() != 0xFFFFFF && secondSheep.unidye$getCustomColor() != 0xFFFFFF){
                int customColor1 = firstSheep.unidye$getCustomColor();
                int red1 =  (customColor1 >> 16 & 0xFF);
                int green1 = (customColor1 >> 8 & 0xFF);
                int blue1 = (customColor1 & 0xFF);
                int customColor2 = secondSheep.unidye$getCustomColor();
                int red2 = (customColor2 >> 16 & 0xFF);
                int green2 = (customColor2 >> 8 & 0xFF);
                int blue2 = (customColor2 & 0xFF);
                int customColor3;
                int red3 = (int) Math.sqrt((double) (red1 * red1 + red2 * red2) /2);
                int green3 = (int) Math.sqrt((double) (green1 * green1 + green2 * green2) /2);
                int blue3 = (int) Math.sqrt((double) (blue1 * blue1 + blue2 * blue2) /2);
                customColor3 = red3;
                customColor3 = (customColor3 << 8) + green3;
                customColor3 = (customColor3 << 8) + blue3;
                sheep.unidye$setCustomColor(customColor3);
                cir.setReturnValue(sheepEntity);
            } else if(firstSheep.unidye$getCustomColor() != 0xFFFFFF && secondSheep.unidye$getCustomColor() == 0xFFFFFF){
                int customColor1 = firstSheep.unidye$getCustomColor();
                int red1 = (customColor1 >> 16 & 0xFF);
                int green1 = (customColor1 >> 8 & 0xFF);
                int blue1 = (customColor1 & 0xFF);
                SheepEntity second = (SheepEntity) entity;
                float[] customColor2 = UnidyeColor.byId(second.getColor().getId()).getColorComponents("wool");
                customColor2[0] = customColor2[0]*255.0f;
                customColor2[1] = customColor2[1]*255.0f;
                customColor2[2] = customColor2[2]*255.0f;
                int customColor3;
                int red3 = (int) Math.sqrt((double) (red1 * red1 + customColor2[0] * customColor2[0]) /2);
                int green3 = (int) Math.sqrt((double) (green1 * green1 + customColor2[1] * customColor2[1]) /2);
                int blue3 = (int) Math.sqrt((double) (blue1 * blue1 + customColor2[2] * customColor2[2]) /2);
                customColor3 = red3;
                customColor3 = (customColor3 << 8) + green3;
                customColor3 = (customColor3 << 8) + blue3;
                sheep.unidye$setCustomColor(customColor3);
                cir.setReturnValue(sheepEntity);
            } else if(secondSheep.unidye$getCustomColor() != 0xFFFFFF && firstSheep.unidye$getCustomColor() == 0xFFFFFF){
                int customColor1 = secondSheep.unidye$getCustomColor();
                int red1 = (customColor1 >> 16 & 0xFF);
                int green1 = (customColor1 >> 8 & 0xFF);
                int blue1 = (customColor1 & 0xFF);
                SheepEntity first = (SheepEntity) ((SheepEntity) (Object) this);
                float[] customColor2 = UnidyeColor.byId(first.getColor().getId()).getColorComponents("wool");
                customColor2[0] = customColor2[0]*255.0f;
                customColor2[1] = customColor2[1]*255.0f;
                customColor2[2] = customColor2[2]*255.0f;
                int customColor3;
                int red3 = (int) Math.sqrt((double) (red1 * red1 + customColor2[0] * customColor2[0]) /2);
                int green3 = (int) Math.sqrt((double) (green1 * green1 + customColor2[1] * customColor2[1]) /2);
                int blue3 = (int) Math.sqrt((double) (blue1 * blue1 + customColor2[2] * customColor2[2]) /2);
                customColor3 = red3;
                customColor3 = (customColor3 << 8) + green3;
                customColor3 = (customColor3 << 8) + blue3;
                sheep.unidye$setCustomColor(customColor3);
                cir.setReturnValue(sheepEntity);
            }
        }
    }

    @Inject(method = "sheared", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;setSheared(Z)V", shift = At.Shift.AFTER), cancellable = true)
    private void sheared(SoundCategory shearedSoundCategory, CallbackInfo ci){
        IEntityAccessor sheep = (IEntityAccessor) ((SheepEntity) (Object) this);
        if(sheep.unidye$getCustomColor() != 0xFFFFFF){
            int i = 1 + ((SheepEntity) (Object) this).getRandom().nextInt(3);
            for(int j = 0; j < i; ++j) {
                DyeableItem item = (DyeableItem) ModBlocks.CUSTOM_WOOL.asItem();
                ItemStack itemStack = ModBlocks.CUSTOM_WOOL.asItem().getDefaultStack();
                item.setColor(itemStack, sheep.unidye$getCustomColor());
                ItemEntity itemEntity = ((SheepEntity) (Object) this).dropStack(itemStack, 1);
                if (itemEntity != null) {
                    itemEntity.setVelocity(itemEntity.getVelocity().add((double)((((SheepEntity) (Object) this).getRandom().nextFloat() - ((SheepEntity) (Object) this).getRandom().nextFloat()) * 0.1F), (double)(((SheepEntity) (Object) this).getRandom().nextFloat() * 0.05F), (double)((((SheepEntity) (Object) this).getRandom().nextFloat() - ((SheepEntity) (Object) this).getRandom().nextFloat()) * 0.1F)));
                }
            }
            ci.cancel();
        }
    }

    @Override
    public int unidye$getCustomColor() {
        return ((SheepEntity) (Object) this).getDataTracker().get(CUSTOM_COLOR);
    }

    @Override
    public void unidye$setCustomColor(int color) {
        ((SheepEntity) (Object) this).getDataTracker().set(CUSTOM_COLOR, color);
    }
}