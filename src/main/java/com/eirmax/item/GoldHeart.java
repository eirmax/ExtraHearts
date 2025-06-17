package com.eirmax.item;

import com.eirmax.events.TotemParticleSpawn;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class GoldHeart extends Item {

    public GoldHeart(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            stack.decrement(1);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3));
            user.playSound(SoundEvents.ITEM_TOTEM_USE, 1.0F, 1.0F);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.PLAYERS, 1.0F, 1.0F);
            TotemParticleSpawn.startTotemBurstEffect((ServerPlayerEntity) user);
            return TypedActionResult.success(stack, false);
        }
        return TypedActionResult.fail(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.goldheart.tooltipone").formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("item.goldheart.tooltipbn").formatted(Formatting.YELLOW));
        tooltip.add(Text.translatable("item.goldheart.tooltiptwo").formatted(Formatting.RED));

    }

}
