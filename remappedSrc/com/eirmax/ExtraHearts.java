package com.eirmax;
import com.eirmax.events.KillPlayerUpdateInit;
import com.eirmax.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtraHearts implements ModInitializer {
	public static final String MOD_ID = "extrahearts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
        KillPlayerUpdateInit.init();
	}
}
