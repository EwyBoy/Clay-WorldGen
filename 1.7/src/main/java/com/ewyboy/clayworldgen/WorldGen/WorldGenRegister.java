package com.ewyboy.clayworldgen.WorldGen;

import cpw.mods.fml.common.registry.GameRegistry;

public class WorldGenRegister {

    public static void init()
    {
        registerWorldGen();
    }

    public static void registerWorldGen() {
        GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
    }

}
