package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHills extends BiomeGenBase {
   public WorldGenTaiga2 field_150634_aD;
   public int field_150637_aG;
   public int field_150636_aF;
   public WorldGenerator theWorldGenerator = new WorldGenMinable(
      Blocks.monster_egg.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE), 9
   );
   public int field_150635_aE;
   public int field_150638_aH;

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(3) > 0 ? this.field_150634_aD : super.genBigTreeChance(var1));
   }

   public BiomeGenHills mutateHills(BiomeGenBase var1) {
      this.field_150638_aH = this.field_150637_aG;
      this.a(var1.ai, true);
      this.a(var1.ah + " M");
      this.a(new BiomeGenBase.Height(var1.an, var1.ao));
      this.a(var1.ap, var1.aq);
      return this;
   }

   public BiomeGenHills(int var1, boolean var2) {
      super(var1);
      this.field_150634_aD = new WorldGenTaiga2(false);
      this.field_150635_aE = 0;
      this.field_150636_aF = 1;
      this.field_150637_aG = 2;
      this.field_150638_aH = this.field_150635_aE;
      if (var2) {
         this.as.treesPerChunk = 3;
         this.field_150638_aH = this.field_150636_aF;
      }
   }

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      super.decorate(var1, var2, var3);
      int var4 = 3 + var2.nextInt(6);

      for (int var5 = 0; var5 < var4; var5++) {
         int var6 = var2.nextInt(16);
         int var7 = var2.nextInt(28) + 4;
         int var8 = var2.nextInt(16);
         BlockPos var9 = var3.add(var6, var7, var8);
         if (var1.getBlockState(var9).getBlock() == Blocks.stone) {
            var1.a(var9, Blocks.emerald_ore.getDefaultState(), 2);
         }
      }

      for (int var10 = 0; var10 < 7; var10++) {
         int var11 = var2.nextInt(16);
         int var12 = var2.nextInt(64);
         int var13 = var2.nextInt(16);
         this.theWorldGenerator.generate(var1, var2, var3.add(var11, var12, var13));
      }
   }

   @Override
   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      this.ak = Blocks.grass.getDefaultState();
      this.al = Blocks.dirt.getDefaultState();
      if ((var6 < -1.0 || var6 > 2.0) && this.field_150638_aH == this.field_150637_aG) {
         this.ak = Blocks.gravel.getDefaultState();
         this.al = Blocks.gravel.getDefaultState();
      } else if (var6 > 1.0 && this.field_150638_aH != this.field_150636_aF) {
         this.ak = Blocks.stone.getDefaultState();
         this.al = Blocks.stone.getDefaultState();
      }

      this.b(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      return new BiomeGenHills(var1, false).mutateHills(this);
   }
}
