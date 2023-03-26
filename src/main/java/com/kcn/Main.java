package com.kcn;

import com.kcn.util.PlayerTickHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("kcn");

	@Override
	public void onInitialize() {
		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
	}
}
