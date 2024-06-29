package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.IEntityAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;

@Mixin(DyeItem.class)
public abstract class DyeItemMixin {
    @Inject(method = "useOnEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/SheepEntity;setColor(Lnet/minecraft/util/DyeColor;)V"))
    private void useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        IEntityAccessor sheep = (IEntityAccessor) entity;
        sheep.unidye$setCustomColor(0xFFFFFF);
    }

    @Inject(method = "useOnEntity", at = @At("HEAD"), cancellable = true)
    private void useOnEntityCheck(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) throws NoSuchFieldException {
        SheepEntity sheepEntity;
        if (entity instanceof SheepEntity && (sheepEntity = (SheepEntity)entity).isAlive() && !sheepEntity.isSheared()) {
            sheepEntity.getWorld().playSoundFromEntity(user, sheepEntity, SoundEvents.ITEM_DYE_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            if (!user.getWorld().isClient) {
                sheepEntity.setColor(((DyeItem) (Object) this).getColor());
                IEntityAccessor sheep = (IEntityAccessor) entity;
                sheep.unidye$setCustomColor(0xFFFFFF);
                stack.decrement(1);
            }
            cir.setReturnValue(ActionResult.success(user.getWorld().isClient));
        }
    }
}
