package com.ewyboy.clayworldgen.Config;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class Config {

    public static int spawnRate;
    public static int veinSize;
    public static int veinRandomness;
    public static int spawnLevel;

    public static void init (File file) {

        Configuration config = new Configuration(file);

        config.load();
            spawnRate = config.getInt("SpawnRate", "Clay Generation", 6, 0, 256, "Sets how manny veins it is going to spawn per chunk(16x*16z*256y)");
            veinSize = config.getInt("VeinSize", "Clay Generation", 12, 1, 256, "Sets how big the veins are going to be (how manny blocks per vein) +/- " + veinRandomness + " blocks");
            veinRandomness = config.getInt("VeinRandomness", "Clay Generation", 6, 0, 256, "Creates a random number between the positive and negative value of this value(VeinRandomness) and adds it to the VeinSize to create some randomness to the vein size");
            spawnLevel = config.getInt("SpawnLevel", "Clay Generation", 60, 12, 256, "Sets the allowed Y-level for the vein to spawn below");
        config.save();
    }
}
