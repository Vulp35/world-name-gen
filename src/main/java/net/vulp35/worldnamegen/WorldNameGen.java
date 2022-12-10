package net.vulp35.worldnamegen;

import dev.isxander.yacl.config.ConfigInstance;
import dev.isxander.yacl.config.GsonConfigInstance;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.vulp35.worldnamegen.config.ConfigData;
import net.vulp35.worldnamegen.utils.NameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldNameGen implements ClientModInitializer {

	public static final String MOD_ID = "worldnamegen";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static ConfigInstance<ConfigData> config;

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Initializing World Name Generator");

		config = new GsonConfigInstance<>(ConfigData.class, FabricLoader.getInstance().getConfigDir().resolve("world-name-gen.json"));
		config.load();

		NameGenerator.registerNameGenerator();
	}

	public static ConfigInstance<ConfigData> getConfig() {
		return config;
	}

}
