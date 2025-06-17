package com.eirmax.events;

import com.eirmax.item.ModItems;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class KillPlayerUpdateInit {
    public static void init() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(
                (ServerWorld world, Entity killer, LivingEntity victim) -> {
                    if (killer instanceof ServerPlayerEntity killerPlayer && victim instanceof PlayerEntity victimPlayer) {
                        ItemStack heart = new ItemStack(ModItems.HEARTITEM);
                        boolean added = killerPlayer.getInventory().insertStack(heart);
                        if (!added) {
                            killerPlayer.dropStack(heart);
                        }
                    }
                }
        );
    }
}