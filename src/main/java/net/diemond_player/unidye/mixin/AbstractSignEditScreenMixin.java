package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.UnidyeAccessor;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {

    @Shadow
    private SignText text;

    @ModifyVariable(method = "renderSignText", at = @At(value = "STORE"), ordinal = 0)
    private int unidye$renderSignText(int value) {
        if(((UnidyeAccessor)this.text).unidye$getCustomColor() != 0xFFFFFF) {
            return ((UnidyeAccessor) this.text).unidye$getCustomColor();
        }
        return value;
    }
}
