package net.vulp35.worldnamegen;

import net.fabricmc.api.ClientModInitializer;
import net.vulp35.worldnamegen.utils.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldNameGen implements ClientModInitializer {

	public static final String MOD_ID = "worldnamegen";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		NameGenerator.registerNameGenerator();
	}
}
