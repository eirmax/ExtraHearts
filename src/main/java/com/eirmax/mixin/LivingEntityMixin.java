package com.eirmax.mixin;

import com.eirmax.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onLivingEntityDeath(DamageSource source, CallbackInfo ci) {
        if ((LivingEntity)(Object)this instanceof ServerPlayerEntity) {
            ServerPlayerEntity deadPlayer = (ServerPlayerEntity)(Object)this;

            deadPlayer.dropStack(new ItemStack(ModItems.HEARTITEM));
        }
    }
}