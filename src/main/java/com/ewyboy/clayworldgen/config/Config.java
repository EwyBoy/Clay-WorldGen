package com.ewyboy.clayworldgen.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class Config {

    public static final ForgeConfigSpec settingSpec;
    public static final Config.Settings SETTINGS;

    static {
        Pair<Settings, ForgeConfigSpec> specPair = (new ForgeConfigSpec.Builder()).configure(Config.Settings :: new);
        settingSpec = specPair.getRight();
        SETTINGS = specPair.getLeft();
    }

    public static class Settings {

        public final ForgeConfigSpec.ConfigValue<Integer> botOffset;
        public final ForgeConfigSpec.ConfigValue<Integer> topOffset;

        public final ForgeConfigSpec.ConfigValue<Integer> veinSize;
        public final ForgeConfigSpec.ConfigValue<Integer> spawnRate;

        Settings(ForgeConfigSpec.Builder builder) {
            builder.comment("Config file for Clay WorldGen").push("SETTINGS");

                builder.comment("Maximum / Minimum Y-level to spawn Clay").push("Spawn Level");
                    this.botOffset = builder.comment("Lowest Y-level the clay can spawn at").translation("clayworldgen.config.botOffset").defineInRange("botOffset", 32, -1024, 1024);
                    this.topOffset = builder.comment("Highest Y-Level the clay can spawn at").translation("clayworldgen.config.topOffset").defineInRange("topOffset", 128, -1024, 1024);
                builder.pop();

                builder.comment("Maximum vein size for Clay").push("Vein Size");
                    this.veinSize = builder.comment("The vein size is randomized by default. This value only affect the max size a vein can potentially reach").translation("clayworldgen.config.veinSize").defineInRange("veinSize", 32, 1, 256);
                builder.pop();

                builder.comment("Spawn rate for Clay").push("Spawn Rate");
                    this.spawnRate = builder.comment("Diamond = 1 | Gold = 2 | | Redstone = 8 | Granite/Diorite/Andesite = 10 | Iron/Coal = 20").translation("clayworldgen.config.veinSize").defineInRange("spawnRate", 2, 1, 256);
                builder.pop();

            builder.pop();
        }
    }
}