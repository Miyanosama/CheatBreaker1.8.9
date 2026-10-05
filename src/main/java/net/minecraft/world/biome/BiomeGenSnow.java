package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenSnow extends BiomeGenBase {
   public WorldGenIceSpike field_150616_aD = new WorldGenIceSpike();
   public boolean field_150615_aC;
   public WorldGenIcePath field_150617_aE = new WorldGenIcePath(4);

   public BiomeGenSnow(int var1, boolean var2) {
      super(var1);
      this.field_150615_aC = var2;
      if (var2) {
         this.ak = Blocks.snow.getDefaultState();
      }

      this.au.clear();
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return new WorldGenTaiga2(false);
   }

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      if (this.field_150615_aC) {
         for (int var4 = 0; var4 < 3; var4++) {
            int var5 = var2.nextInt(16) + 8;
            int var6 = var2.nextInt(16) + 8;
            this.field_150616_aD.generate(var1, var2, var1.getHeight(var3.add(var5, 0, var6)));
         }

         for (int var7 = 0; var7 < 2; var7++) {
            int var8 = var2.nextInt(16) + 8;
            int var9 = var2.nextInt(16) + 8;
            this.field_150617_aE.generate(var1, var2, var1.getHeight(var3.add(var8, 0, var9)));
         }
      }

      super.decorate(var1, var2, var3);
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      BiomeGenBase var2 = new BiomeGenSnow(var1, true)
         .a(13828095, true)
         .a(this.ah + " Spikes")
         .setEnableSnow()
         .a(0.0F, 0.5F)
         .a(new BiomeGenBase.Height(this.an + 0.1F, this.ao + 0.1F));
      var2.an = this.an + 0.3F;
      var2.ao = this.ao + 0.4F;
      return var2;
   }
}
