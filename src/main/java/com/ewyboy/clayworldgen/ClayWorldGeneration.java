package com.ewyboy.clayworldgen;

import com.ewyboy.clayworldgen.config.Config;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ClayWorldGeneration {

    protected static ConfiguredFeature<?, ?> configuredClayFeature;
    protected static PlacedFeature placedClayFeature;

    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifiers) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifiers, BiomeFilter.biome());
    }

    private static List<PlacementModifier> rareOrePlacement(int rarity, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(rarity), placementModifier);
    }

    public static void register() {
        Registry<ConfiguredFeature<?, ?>> configuredFeatureRegistry = BuiltinRegistries.CONFIGURED_FEATURE;
        Registry<PlacedFeature> placedFeatureRegistry = BuiltinRegistries.PLACED_FEATURE;

        configuredClayFeature = Feature.ORE.configured(
                new OreConfiguration(
                        OreFeatures.NATURAL_STONE,
                        Blocks.CLAY.defaultBlockState(),
                        Config.SETTINGS.veinSize.get()
                )
        );

        placedClayFeature = configuredClayFeature.placed(
                rareOrePlacement(Config.SETTINGS.spawnRate.get(), HeightRangePlacement.uniform(VerticalAnchor.absolute(Config.SETTINGS.botOffset.get()), VerticalAnchor.absolute(Config.SETTINGS.topOffset.get())))
        );

        Registry.register(configuredFeatureRegistry, "ore_clay_feature", configuredClayFeature);
        Registry.register(placedFeatureRegistry, "ore_clay_placed", placedClayFeature);
    }

    public static void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ClayWorldGeneration.placedClayFeature);
        }
    }
}
