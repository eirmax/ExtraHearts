package com.eirmax.item;


import com.eirmax.ExtraHearts;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {


    public static final Item HEARTITEM = registerItem("extraheart", new HeartItem(new Item.Settings().maxCount(1)));
    public static final Item HEARTITEMTAB = registerItem("extrahearttab", new HeartItem(new Item.Settings().maxCount(1)));


    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(ExtraHearts.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ExtraHearts.LOGGER.info("Registering Mod Items for " + ExtraHearts.MOD_ID);
    }
}

