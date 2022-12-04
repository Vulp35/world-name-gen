package net.vulp35.worldnamegen.utils;


import net.vulp35.worldnamegen.WorldNameGen;

import java.util.Random;

public class NameGenerator {
// TODO: Max length of 32.
// TODO: Put generated names into an array to check against so they are not shown again.
// TODO: Print to log how many possible combinations there can be.


// TODO: MORE WORDS!!
    static String[]prefixes = {"The",""};
    static String[]adjectives = {"Dirty", "Vast", "Open", "Wide", "Rolling", "Sprawling", "Misty"};
    static String[]nouns = {"Hills", "Caves", "Meadow", "Mountains", "Ravine", "Rivers", "River", "Hill" };


    public static String getNewName() {
        Random random = new Random();
        String prefix = prefixes[random.nextInt(prefixes.length)];
        String adjective = adjectives[random.nextInt(adjectives.length)];
        String noun = nouns[random.nextInt(nouns.length)];
        String newName;

        if(prefix.length() > 0) {
            newName = prefix + " " + adjective + " " + noun;
        } else {
            newName = adjective + " " + noun;
        }
        WorldNameGen.LOGGER.info("Generated name: " +newName);
        return newName;

    }

    public static void  registerNameGenerator() {
        WorldNameGen.LOGGER.info("Registering Name Generator.");
    }
}
