package net.vulp35.worldnamegen.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import net.vulp35.worldnamegen.WorldNameGen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Config {
    static String fileName = WorldNameGen.MOD_ID + "-config.json";
    static Path configFile = FabricLoader.getInstance().getConfigDir().resolve(fileName);

    int coolValue = 12;
    String someString = "hello";
    List<Identifier> epicList = new ArrayList<>();

    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Config readConfig() {
        try {
            String content = Files.readString(configFile);
            return GSON.fromJson(content, Config.class);
        }
        catch (IOException e) {
            throw new RuntimeException("Couldnt read the config!!!!!!", e);
        }
    }

    public void writeConfig() {
        try {
            Files.writeString(configFile, GSON.toJson(this));
        }
        catch (IOException e) {
            throw new RuntimeException("Couldnt write the config!!!!!!", e);
        }
    }
}
