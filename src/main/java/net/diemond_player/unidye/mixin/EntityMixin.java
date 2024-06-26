package net.diemond_player.unidye.mixin;

import net.diemond_player.unidye.util.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin implements IEntityDataSaver {
	private NbtCompound persistentData;

	@Override
	public NbtCompound unidye$getPersistentData() {
		if(this.persistentData == null){
			this.persistentData = new NbtCompound();
		}
		return persistentData;
	}

	@Inject(method = "writeNbt", at = @At("HEAD"))
	protected void injectWriteMethod(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> info){
		if(this.persistentData != null){
			nbt.put("unidye.custom_data", persistentData);
		}
	}

	@Inject(method = "readNbt", at = @At("HEAD"))
	protected void injectReadMethod(NbtCompound nbt, CallbackInfo info){
		if(nbt.contains("unidye.custom_data", 10)){
			this.persistentData = nbt.getCompound("diemants_test.custom_data");
		}
	}
}