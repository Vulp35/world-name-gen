package net.vulp35.worldnamegen.utils;

import net.vulp35.worldnamegen.WorldNameGen;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RandomHashGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomHash(int length) {
        return new BigInteger(length * 5, random).toString(32);
    }

    public static void  registerHashGenerator() {
        WorldNameGen.LOGGER.info("Registering Hash Generator.");
    }
}
