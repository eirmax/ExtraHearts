package com.eirmax.component;


import net.minecraft.entity.player.PlayerEntity;

public class ExtraHeartsComponent {
        public static int getHearts(PlayerEntity player) {
            return ((ExtraHeartsAccess) player).extrahearts$getExtraHearts();
        }

        public static void setHearts(PlayerEntity player, int value) {
            ((ExtraHeartsAccess) player).extrahearts$setExtraHearts(value);
            updateMaxHealth(player);
        }

    public static boolean addHeart(PlayerEntity player) {
        int current = getHearts(player);
        if (current < 10) {
            setHearts(player, current + 1);
            return true;
        }
        return false;
    }

    public static void removeHeart(PlayerEntity player) {
        int current = getHearts(player);
        if (current > 0) {
            setHearts(player, current - 1);
        }
    }

    public static void updateMaxHealth(PlayerEntity player) {
        int extra = getHearts(player);
        float base = 20.0F;
        player.getAttributeInstance(net.minecraft.entity.attribute.EntityAttributes.GENERIC_MAX_HEALTH)
                .setBaseValue(base + extra * 2.0F);
    }
}