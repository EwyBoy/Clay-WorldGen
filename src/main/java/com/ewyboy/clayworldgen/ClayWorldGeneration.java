package com.ewyboy.clayworldgen;

import com.ewyboy.clayworldgen.config.Config;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ClayWorldGeneration {

    protected static ConfiguredFeature<?, ?> CLAY_GEN;

    public static void register() {
        Registry<ConfiguredFeature<?, ?>> registry = WorldGenRegistries.CONFIGURED_FEATURE;

        CLAY_GEN = Feature.ORE.withConfiguration(
                new OreFeatureConfig(
                        OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                        Blocks.CLAY.getDefaultState(),
                        Config.SETTINGS.veinSize.get()
                )
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(
                        Config.SETTINGS.botOffset.get(),
                        Config.SETTINGS.topOffset.get(),
                        Config.SETTINGS.maxSpawnLevel.get()
                )).square().func_242731_b(Config.SETTINGS.spawnRate.get())
        );

        Registry.register(registry, "ore_clay", CLAY_GEN);
    }

    public static void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.Category.THEEND && event.getCategory() != Biome.Category.NETHER) {
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ClayWorldGeneration.CLAY_GEN);
        }
    }
}
