package net.minecraft.world.biome;

import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMesa extends BiomeGenBase {
   public NoiseGeneratorPerlin recoveredField2856;
   public NoiseGeneratorPerlin recoveredField2857;
   public long field_150622_aD;
   public boolean field_150626_aH;
   public IBlockState[] field_150621_aC;
   public boolean field_150620_aI;
   public NoiseGeneratorPerlin field_150625_aG;

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return this.aA;
   }

   @Override
   public int getGrassColorAtPos(BlockPos var1) {
      return 9470285;
   }

   public IBlockState func_180629_a(int var1, int var2, int var3) {
      int var4 = (int)Math.round(this.field_150625_aG.func_151601_a(var1 * 1.0 / 512.0, var1 * 1.0 / 512.0) * 2.0);
      return this.field_150621_aC[(var2 + var4 + 64) % 64];
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      boolean var2 = this.az == BiomeGenBase.mesa.az;
      BiomeGenMesa var3 = new BiomeGenMesa(var1, var2, this.field_150620_aI);
      if (!var2) {
         var3.a(g);
         var3.a(this.ah + " M");
      } else {
         var3.a(this.ah + " (Bryce)");
      }

      var3.a(this.ai, true);
      return var3;
   }

   @Override
   public int getFoliageColorAtPos(BlockPos var1) {
      return 10387789;
   }

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      super.decorate(var1, var2, var3);
   }

   public BiomeGenMesa(int var1, boolean var2, boolean var3) {
      super(var1);
      this.field_150626_aH = var2;
      this.field_150620_aI = var3;
      this.b();
      this.a(2.0F, 0.0F);
      this.au.clear();
      this.ak = Blocks.sand.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
      this.al = Blocks.stained_hardened_clay.getDefaultState();
      this.as.treesPerChunk = -999;
      this.as.deadBushPerChunk = 20;
      this.as.reedsPerChunk = 3;
      this.as.cactiPerChunk = 5;
      this.as.flowersPerChunk = 0;
      this.au.clear();
      if (var3) {
         this.as.treesPerChunk = 5;
      }
   }

   @Override
   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      if (this.field_150621_aC == null || this.field_150622_aD != var1.J()) {
         this.func_150619_a(var1.J());
      }

      if (this.recoveredField2857 == null || this.recoveredField2856 == null || this.field_150622_aD != var1.J()) {
         Random var8 = new Random(this.field_150622_aD);
         this.recoveredField2857 = new NoiseGeneratorPerlin(var8, 4);
         this.recoveredField2856 = new NoiseGeneratorPerlin(var8, 1);
      }

      this.field_150622_aD = var1.J();
      double var22 = 0.0;
      if (this.field_150626_aH) {
         int var10 = (var4 & -16) + (var5 & 15);
         int var11 = (var5 & -16) + (var4 & 15);
         double var12 = Math.min(Math.abs(var6), this.recoveredField2857.func_151601_a(var10 * 0.25, var11 * 0.25));
         if (var12 > 0.0) {
            double var14 = 0.001953125;
            double var16 = Math.abs(this.recoveredField2856.func_151601_a(var10 * var14, var11 * var14));
            var22 = var12 * var12 * 2.5;
            double var18 = Math.ceil(var16 * 50.0) + 14.0;
            if (var22 > var18) {
               var22 = var18;
            }

            var22 += 64.0;
         }
      }

      int var24 = var4 & 15;
      int var25 = var5 & 15;
      int var26 = var1.F();
      IBlockState var13 = Blocks.stained_hardened_clay.getDefaultState();
      IBlockState var27 = this.al;
      int var15 = (int)(var6 / 3.0 + 3.0 + var2.nextDouble() * 0.25);
      boolean var28 = Math.cos(var6 / 3.0 * Math.PI) > 0.0;
      int var17 = -1;
      boolean var29 = false;

      for (int var19 = 255; var19 >= 0; var19--) {
         if (var3.getBlockState(var25, var19, var24).getBlock().getMaterial() == Material.air && var19 < (int)var22) {
            var3.setBlockState(var25, var19, var24, Blocks.stone.getDefaultState());
         }

         if (var19 <= var2.nextInt(5)) {
            var3.setBlockState(var25, var19, var24, Blocks.bedrock.getDefaultState());
         } else {
            IBlockState var20 = var3.getBlockState(var25, var19, var24);
            if (var20.getBlock().getMaterial() == Material.air) {
               var17 = -1;
            } else if (var20.getBlock() == Blocks.stone) {
               if (var17 == -1) {
                  var29 = false;
                  if (var15 <= 0) {
                     var13 = null;
                     var27 = Blocks.stone.getDefaultState();
                  } else if (var19 >= var26 - 4 && var19 <= var26 + 1) {
                     var13 = Blocks.stained_hardened_clay.getDefaultState();
                     var27 = this.al;
                  }

                  if (var19 < var26 && (var13 == null || var13.getBlock().getMaterial() == Material.air)) {
                     var13 = Blocks.water.getDefaultState();
                  }

                  var17 = var15 + Math.max(0, var19 - var26);
                  if (var19 < var26 - 1) {
                     var3.setBlockState(var25, var19, var24, var27);
                     if (var27.getBlock() == Blocks.stained_hardened_clay) {
                        var3.setBlockState(var25, var19, var24, var27.getBlock().getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE));
                     }
                  } else if (!this.field_150620_aI || var19 <= 86 + var15 * 2) {
                     if (var19 <= var26 + 3 + var15) {
                        var3.setBlockState(var25, var19, var24, this.ak);
                        var29 = true;
                     } else {
                        IBlockState var21;
                        if (var19 < 64 || var19 > 127) {
                           var21 = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
                        } else if (var28) {
                           var21 = Blocks.hardened_clay.getDefaultState();
                        } else {
                           var21 = this.func_180629_a(var4, var19, var5);
                        }

                        var3.setBlockState(var25, var19, var24, var21);
                     }
                  } else if (var28) {
                     var3.setBlockState(var25, var19, var24, Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT));
                  } else {
                     var3.setBlockState(var25, var19, var24, Blocks.grass.getDefaultState());
                  }
               } else if (var17 > 0) {
                  var17--;
                  if (var29) {
                     var3.setBlockState(
                        var25, var19, var24, Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE)
                     );
                  } else {
                     IBlockState var30 = this.func_180629_a(var4, var19, var5);
                     var3.setBlockState(var25, var19, var24, var30);
                  }
               }
            }
         }
      }
   }

   public void func_150619_a(long var1) {
      this.field_150621_aC = new IBlockState[64];
      Arrays.fill(this.field_150621_aC, Blocks.hardened_clay.getDefaultState());
      Random var3 = new Random(var1);
      this.field_150625_aG = new NoiseGeneratorPerlin(var3, 1);

      for (int var12 = 0; var12 < 64; var12++) {
         var12 += var3.nextInt(5) + 1;
         if (var12 < 64) {
            this.field_150621_aC[var12] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.ORANGE);
         }
      }

      int var13 = var3.nextInt(4) + 2;

      for (int var5 = 0; var5 < var13; var5++) {
         int var6 = var3.nextInt(3) + 1;
         int var7 = var3.nextInt(64);

         for (int var8 = 0; var7 + var8 < 64 && var8 < var6; var8++) {
            this.field_150621_aC[var7 + var8] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW);
         }
      }

      int var14 = var3.nextInt(4) + 2;

      for (int var15 = 0; var15 < var14; var15++) {
         int var17 = var3.nextInt(3) + 2;
         int var20 = var3.nextInt(64);

         for (int var9 = 0; var20 + var9 < 64 && var9 < var17; var9++) {
            this.field_150621_aC[var20 + var9] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.BROWN);
         }
      }

      int var16 = var3.nextInt(4) + 2;

      for (int var18 = 0; var18 < var16; var18++) {
         int var21 = var3.nextInt(3) + 1;
         int var23 = var3.nextInt(64);

         for (int var10 = 0; var23 + var10 < 64 && var10 < var21; var10++) {
            this.field_150621_aC[var23 + var10] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.RED);
         }
      }

      int var19 = var3.nextInt(3) + 3;
      int var22 = 0;

      for (int var24 = 0; var24 < var19; var24++) {
         byte var25 = 1;
         var22 += var3.nextInt(16) + 4;

         for (int var11 = 0; var22 + var11 < 64 && var11 < var25; var11++) {
            this.field_150621_aC[var22 + var11] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.WHITE);
            if (var22 + var11 > 1 && var3.nextBoolean()) {
               this.field_150621_aC[var22 + var11 - 1] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
            }

            if (var22 + var11 < 63 && var3.nextBoolean()) {
               this.field_150621_aC[var22 + var11 + 1] = Blocks.stained_hardened_clay.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.SILVER);
            }
         }
      }
   }
}
