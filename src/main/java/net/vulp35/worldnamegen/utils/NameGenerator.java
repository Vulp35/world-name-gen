package net.vulp35.worldnamegen.utils;


import net.vulp35.worldnamegen.WorldNameGen;

import java.util.Random;

public class NameGenerator {
// TODO: Max length of 32.
// TODO: Put generated names into an array to check against so they are not shown again.
// TODO: Print to log how many possible combinations there can be.


// TODO: MORE WORDS!!
    static String[]prefixes = {"The",""};
    static String[]adjectives = {"Dirty", "Vast", "Open", "Wide", "Rolling", "Sprawling", "Misty", "Empty", "Dry", "Wet",
                                    "Far-off", "Ruthless", "Old", "New", "Cruel", "Familiar","Unlucky", "Dusty", "Dark",
                                    "Bright", "Ancient", "Modern", "Mythical", "Chaotic", "Medieval", "Familiar", "Unfamiliar",
                                    "Spacious", "Endless", "Lifeless", "Barren", "Thriving", "Great", "Big", "Great Big"};
    static String[]nouns = {"Hills", "Caves", "Meadow", "Mountains", "Ravine", "Rivers", "River", "Hill", "Expanse",
                            "Abyss", "Archipelago", "Backwaters", "Bay", "Canyon", "Bog", "Cliff", "Confluence", "Crater",
                            "Desert", "Glacier", "Island", "Swamp", "Cave", "Peak", "Stream", "Reef", "Fjord", "Butte",
                            "Dune", "Dunes", "Tundra", "Wetland", "Wetlands"};


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
