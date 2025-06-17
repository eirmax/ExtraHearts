package com.eirmax.tabs;

import com.eirmax.ExtraHearts;
import com.eirmax.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CreativeTab {
    public static final ItemGroup EXTRA_HEARTS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ExtraHearts.MOD_ID, "extra_hearts_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.extra_hearts_group"))
                    .icon(() -> new ItemStack(ModItems.HEARTITEM)).entries((displayContext, entries) -> {
                        entries.add(ModItems.HEARTITEM);
                        entries.add(ModItems.GOLDHEARTITEM);
                    }).build());




    public static void registerItemGroups(){}

}

