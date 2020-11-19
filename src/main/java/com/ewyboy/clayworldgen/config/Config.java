package com.ewyboy.clayworldgen.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static final ForgeConfigSpec settingSpec;
    public static final Config.Settings SETTINGS;

    static {
        Pair<Settings, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(Config.Settings::new);
        settingSpec = specPair.getRight();
        SETTINGS = specPair.getLeft();
    }

    public static class Settings {

        public final ForgeConfigSpec.ConfigValue<Integer> botOffset;
        public final ForgeConfigSpec.ConfigValue<Integer> topOffset;
        public final ForgeConfigSpec.ConfigValue<Integer> maxSpawnLevel;

        public final ForgeConfigSpec.ConfigValue<Integer> veinSize;
        public final ForgeConfigSpec.ConfigValue<Integer> spawnRate;

        Settings(ForgeConfigSpec.Builder builder) {
            builder.comment("Config file for Clay WorldGen").push("SETTINGS");
            builder.comment("Maximum / Minimum Y-level to spawn Clay").push("Spawn Level");
            this.maxSpawnLevel = builder.comment("Sets the maximum Y-level Clay can spawn below").translation("clayworldgen.config.maxSpawnLevel").defineInRange("maxSpawnLevel", 256, 1, 256);
            this.botOffset = builder.comment("Bottom offset of the Y-level Clay can spawn at").translation("clayworldgen.config.botOffset").defineInRange("botOffset", 8, 1, 256);
            this.topOffset = builder.comment("Top offset of the Y-level Clay can spawn at").translation("clayworldgen.config.topOffset").defineInRange("topOffset", 8, 1, 256);
            builder.pop();
            builder.comment("Maximum vein size for Clay").push("Vein Size");
            this.veinSize = builder.comment("The vein size is randomized by default. This value only affect the max size a vein can potentially reach").translation("clayworldgen.config.veinSize").defineInRange("veinSize", 33, 1, 256);
            builder.pop();
            builder.comment("Spawn rate for Clay").push("Spawn Rate");
            this.spawnRate = builder.comment("Diamond = 1 | Gold = 2 | | Redstone = 8 | Granite/Diorite/Andesite = 10 | Iron/Coal = 20").translation("clayworldgen.config.veinSize").defineInRange("spawnRate", 10, 1, 256);
        }
    }
}