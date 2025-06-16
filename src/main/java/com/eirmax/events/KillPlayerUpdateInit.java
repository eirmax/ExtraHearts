package com.eirmax.events;

import com.eirmax.component.ExtraHeartsComponent;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;


public class KillPlayerUpdateInit  {
    public static void init() {
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