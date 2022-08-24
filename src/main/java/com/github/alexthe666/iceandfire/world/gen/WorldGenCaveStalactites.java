package com.github.alexthe666.iceandfire.world.gen;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

import java.util.Random;

public class WorldGenCaveStalactites {
    private final Block block;
    private int maxHeight = 3;

    public WorldGenCaveStalactites(Block block, int maxHeight) {
        this.block = block;
        this.maxHeight = maxHeight;
    }

    public boolean generate(IWorld worldIn, Random rand, BlockPos position) {
        int height = maxHeight + rand.nextInt(3);
        for (int i = 0; i < height; i++) {
            if (i < height / 2) {
                worldIn.setBlock(position.below(i).north(), block.defaultBlockState(), 2);
                worldIn.setBlock(position.below(i).east(), block.defaultBlockState(), 2);
                worldIn.setBlock(position.below(i).south(), block.defaultBlockState(), 2);
                worldIn.setBlock(position.below(i).west(), block.defaultBlockState(), 2);
            }
            worldIn.setBlock(position.below(i), block.defaultBlockState(), 2);
        }
        return true;
    }
}
