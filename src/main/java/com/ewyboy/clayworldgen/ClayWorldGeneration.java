package com.ewyboy.clayworldgen;

import com.ewyboy.clayworldgen.config.Config;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ClayWorldGeneration {

    public static Holder<PlacedFeature> CLAY_ORE_PLACED;
    public static Holder<ConfiguredFeature<OreConfiguration, ?>> CLAY_ORE_CONFIGURED;

    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifiers) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifiers, BiomeFilter.biome());
    }

    private static List<PlacementModifier> rareOrePlacement(int rarity, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(rarity), placementModifier);
    }

    public static void register() {
        CLAY_ORE_CONFIGURED = FeatureUtils.register(
                "ore_clay_feature", Feature.ORE, new OreConfiguration(
                        List.of(
                                OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, Blocks.CLAY.defaultBlockState()),
                                OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, Blocks.CLAY.defaultBlockState())
                        ),
                        Config.SETTINGS.veinSize.get()
                )
        );
        CLAY_ORE_PLACED = PlacementUtils.register(
                "ore_clay_placed", CLAY_ORE_CONFIGURED,
                rareOrePlacement(
                        Config.SETTINGS.spawnRate.get(),
                        HeightRangePlacement.uniform(
                                VerticalAnchor.absolute(
                                        Config.SETTINGS.botOffset.get()
                                ),
                                VerticalAnchor.absolute(
                                        Config.SETTINGS.topOffset.get()
                                )
                        )
                )
        );
    }

    public static void onBiomeLoading(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.NETHER) {
            event.getGeneration().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Holder.direct(ClayWorldGeneration.CLAY_ORE_PLACED.value()));
        }
    }
}
