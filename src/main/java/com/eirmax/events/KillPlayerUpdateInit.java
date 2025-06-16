package com.eirmax.events;

import com.eirmax.component.ExtraHeartsComponent;
import com.eirmax.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public class KillPlayerUpdateInit  {
        public static void init() {
            AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
                if (!world.isClient && entity instanceof PlayerEntity) {
                    PlayerEntity victim = (PlayerEntity) entity;
                    if (victim.getHealth() - player.getAttackCooldownProgress(0) <= 0.0F) {
                        ItemStack heart = new ItemStack(ModItems.HEARTITEM);
                        victim.dropStack(heart);
                    }
                }
                return ActionResult.PASS;
            });


            ServerPlayerEvents.ALLOW_DEATH.register((player, damageSource, damageAmount) -> {
                if (ExtraHeartsComponent.getHearts(player) > 0) {
                    ExtraHeartsComponent.removeHeart(player);
                }
                return true;
            });

            ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
                ExtraHeartsComponent.updateMaxHealth(newPlayer);
            });
        }
}
