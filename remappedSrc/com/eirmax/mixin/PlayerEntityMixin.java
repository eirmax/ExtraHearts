package com.eirmax.mixin;

import com.eirmax.component.ExtraHeartsAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements ExtraHeartsAccess {
	@Unique
	private int extrahearts_extraHearts = 0;

	@Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
	private void writeExtraHearts(NbtCompound nbt, CallbackInfo ci) {
		nbt.putInt("ExtraHearts", extrahearts_extraHearts);
	}

	@Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
	private void readExtraHearts(NbtCompound nbt, CallbackInfo ci) {
		if (nbt.contains("ExtraHearts")) {
			extrahearts_extraHearts = nbt.getInt("ExtraHearts");
		}
	}


	@Unique
	@Override
	public int extrahearts$getExtraHearts() {
		return extrahearts_extraHearts;
	}

	@Unique
	@Override
	public void extrahearts$setExtraHearts(int value) {
		extrahearts_extraHearts = Math.max(0, Math.min(10, value));
	}
}