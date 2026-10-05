package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenShrub;

public class BiomeGenJungle extends BiomeGenBase {
   public boolean field_150614_aC;
   public static IBlockState field_181620_aE = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
   public static IBlockState field_181621_aF = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE)
      .withProperty(BlockLeaves.b, false);
   public static IBlockState field_181622_aG = Blocks.leaves
      .getDefaultState()
      .withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.OAK)
      .withProperty(BlockLeaves.b, false);

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      super.decorate(var1, var2, var3);
      int var4 = var2.nextInt(16) + 8;
      int var5 = var2.nextInt(16) + 8;
      int var6 = var2.nextInt(var1.getHeight(var3.add(var4, 0, var5)).getY() * 2);
      new WorldGenMelon().generate(var1, var2, var3.add(var4, var6, var5));
      WorldGenVines var7 = new WorldGenVines();

      for (int var10 = 0; var10 < 50; var10++) {
         var6 = var2.nextInt(16) + 8;
         short var8 = 128;
         int var9 = var2.nextInt(16) + 8;
         var7.generate(var1, var2, var3.add(var6, 128, var9));
      }
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(10) == 0
         ? this.worldGeneratorBigTree
         : (
            var1.nextInt(2) == 0
               ? new WorldGenShrub(field_181620_aE, field_181622_aG)
               : (
                  !this.field_150614_aC && var1.nextInt(3) == 0
                     ? new WorldGenMegaJungle(false, 10, 20, field_181620_aE, field_181621_aF)
                     : new WorldGenTrees(false, 4 + var1.nextInt(7), field_181620_aE, field_181621_aF, true)
               )
         ));
   }

   public BiomeGenJungle(int var1, boolean var2) {
      super(var1);
      this.field_150614_aC = var2;
      if (var2) {
         this.as.treesPerChunk = 2;
      } else {
         this.as.treesPerChunk = 50;
      }

      this.as.grassPerChunk = 25;
      this.as.flowersPerChunk = 4;
      if (!var2) {
         this.at.add(new BiomeGenBase.SpawnListEntry(EntityOcelot.class, 2, 1, 1));
      }

      this.au.add(new BiomeGenBase.SpawnListEntry(EntityChicken.class, 10, 4, 4));
   }

   @Override
   public WorldGenerator getRandomWorldGenForGrass(Random var1) {
      return var1.nextInt(4) == 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
   }
}
