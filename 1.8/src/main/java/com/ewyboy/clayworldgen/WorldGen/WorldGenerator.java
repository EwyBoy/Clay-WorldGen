package com.ewyboy.clayworldgen.WorldGen;

import com.ewyboy.clayworldgen.Config.Config;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenerator implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimensionId()) {
            case 0 : generateSurface(world, random, chunkX*16, chunkZ*16);
        }
    }

    private int maxPos = Config.veinRandomness;
    private int minPos = (Config.veinRandomness) * -1;

    private void generateSurface(World world, Random random, int x, int z) {
        for (int i = 0; i < Config.spawnRate; i++) {

            int Xcoord = x + random.nextInt(16);
            int Zcoord = z + random.nextInt(16);
            int Ycoord = random.nextInt(Config.spawnLevel);

            new WorldGenMinable(Blocks.clay.getDefaultState(), Config.veinSize + (int)Math.random() * (maxPos - minPos) + minPos).generate(world,random, new BlockPos(Xcoord,Ycoord,Zcoord));
        }
    }
}
