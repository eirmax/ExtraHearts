package com.eirmax.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class TotemParticleSpawn {

    private static final Map<UUID, Integer> activeBursts = new HashMap<>();
    static {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            Iterator<Map.Entry<UUID, Integer>> it = activeBursts.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<UUID, Integer> entry = it.next();
                UUID playerId = entry.getKey();
                int ticksLeft = entry.getValue();

                ServerPlayerEntity player = server.getPlayerManager().getPlayer(playerId);

                if (player == null || !player.isAlive() || ticksLeft <= 0) {
                    it.remove();
                    continue;
                }

                spawnTotemBurst(player);

                entry.setValue(ticksLeft - 1);
            }
        });
    }

    public static void startTotemBurstEffect(ServerPlayerEntity player) {
        activeBursts.put(player.getUuid(), 60);
    }

    public static void spawnTotemBurst(PlayerEntity player) {
        if (!(player.getWorld() instanceof ServerWorld world)) return;

        Random random = world.random;
        double px = player.getX();
        double py = player.getY() + 1.0;
        double pz = player.getZ();

        int count = 24;

        for (int i = 0; i < count; i++) {
            double theta = random.nextDouble() * 2 * Math.PI;
            double phi = random.nextDouble() * Math.PI;
            double r = 0.6 + random.nextDouble() * 0.6;

            double ox = r * Math.sin(phi) * Math.cos(theta);
            double oy = r * Math.cos(phi);
            double oz = r * Math.sin(phi) * Math.sin(theta);

            double mx = ox * 0.15;
            double my = oy * 0.15 + 0.08;
            double mz = oz * 0.15;

            world.spawnParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    px + ox, py + oy, pz + oz,
                    1,
                    mx, my, mz, 0
            );
        }
    }
}