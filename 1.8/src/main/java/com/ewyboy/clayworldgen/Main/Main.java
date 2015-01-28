package com.ewyboy.clayworldgen.Main;

import com.ewyboy.clayworldgen.Config.Config;
import com.ewyboy.clayworldgen.WorldGen.WorldGenRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "clayworldgen", name = "ClayWorldGen", version = "v1.0.0")

public class Main {

    @Mod.Instance("clayworldgen")
    public static Main instance;

    @Mod.EventHandler
    public void PreInit (FMLPreInitializationEvent event) {
        WorldGenRegister.init();
        Config.init(event.getSuggestedConfigurationFile());
    }
}
