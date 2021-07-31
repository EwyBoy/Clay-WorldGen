package com.ewyboy.clayworldgen;

import com.ewyboy.clayworldgen.config.Config;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.feature.configurations.RangeDecoratorConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class ClayWorldGeneration {

    protected static ConfiguredFeature<?, ?> CLAY_GEN;

    public static void register() {
        Registry<ConfiguredFeature<?, ?>> registry = BuiltinRegistries.CONFIGURED_FEATURE;

        CLAY_GEN = Feature.ORE.configured(
                new OreConfiguration(
                        OreConfiguration.Predicates.NATURAL_STONE,
                        Blocks.CLAY.defaultBlockState(),
                        Config.SETTINGS.veinSize.get()
                )
        ).rangeUniform(
                VerticalAnchor.absolute(Config.SETTINGS.botOffset.get()),
                VerticalAnchor.absolute(Config.SETTINGS.topOffset.get()))
                .squared()
                .count(Config.SETTINGS.spawnRate.get()
        );

        Registry.register(registry, "ore_clay", CLAY_GEN);
    }

    public static void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ClayWorldGeneration.CLAY_GEN);
        }
    }
}
