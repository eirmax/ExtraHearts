package com.eirmax;

import com.eirmax.events.KillPlayerUpdateInit;
import com.eirmax.item.ModItems;
import com.eirmax.tabs.CreativeTab;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraHearts implements ModInitializer {
	public static final String MOD_ID = "extrahearts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		CreativeTab.registerItemGroups();
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
			KillPlayerUpdateInit.init();
		}
	}
}
