package com.ewyboy.clayworldgen.worldgen;

import com.ewyboy.clayworldgen.config.Config;
import net.minecraft.block.Blocks;
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

    public ClayWorldGeneration() {}

    public static void checkAndInitBiome(BiomeLoadingEvent evt)
    {
        if (evt.getCategory() != Biome.Category.THEEND && evt.getCategory() != Biome.Category.NETHER) {
            if (CLAY_GEN == null) {
                CLAY_GEN = Feature.ORE.withConfiguration(
                        new OreFeatureConfig(
                                OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
                                Blocks.CLAY.getDefaultState(),
                                Config.SETTINGS.veinSize.get()
                        )
                    ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(
                            Config.SETTINGS.minSpawnLevel.get(),
                        5,
                            Config.SETTINGS.maxSpawnLevel.get()
                        )
                    ).square().func_242731_b(Config.SETTINGS.spawnRate.get())
                );
            }
        }
    }

    public static void generateOverworldOres(BiomeLoadingEvent evt)
    {
        evt.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ClayWorldGeneration.CLAY_GEN);
    }
}
