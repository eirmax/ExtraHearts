package com.eirmax.item;

import com.eirmax.component.ExtraHeartsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HeartItem extends Item {

    public HeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            if (ExtraHeartsComponent.addHeart(user)) {
                stack.decrement(1);
                user.setHealth(user.getMaxHealth());
                user.playSound(SoundEvents.ITEM_TOTEM_USE, 1.0F, 1.0F);
                ((ServerPlayerEntity)user).networkHandler.sendPacket(
                        new EntityStatusS2CPacket(user, (byte)35)
                );
                return TypedActionResult.success(stack, false);
            }
        }
        return TypedActionResult.fail(stack);
    }
}
