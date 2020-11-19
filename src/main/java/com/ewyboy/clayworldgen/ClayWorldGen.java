package com.ewyboy.clayworldgen;

import com.ewyboy.clayworldgen.config.Config;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("clayworldgen")
public class ClayWorldGen {

    public ClayWorldGen() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.settingSpec);
    }

    @SubscribeEvent(priority= EventPriority.HIGH)
    public static void onBiomeLoading(BiomeLoadingEvent evt) {
        ClayWorldGeneration.checkAndInitBiome(evt);
        ClayWorldGeneration.register();
        ClayWorldGeneration.generateOverworldOres(evt);
    }

}
