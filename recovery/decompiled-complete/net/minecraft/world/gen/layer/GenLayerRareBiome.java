package net.minecraft.world.gen.layer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.world.biome.BiomeGenBase;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$5;

public class GenLayerRareBiome extends GenLayer {
   public ModelBase field_0000;
   public ModelMagmaCube field_0002;
   public CategoryNodeEditor$5 field_0001;

   @Override
   public int[] getInts(int var1, int var2, int var3, int var4) {
      int[] var5 = this.a.getInts(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
      int[] var6 = IntCache.getIntCache(var3 * var4);

      for (int var7 = 0; var7 < var4; var7++) {
         for (int var8 = 0; var8 < var3; var8++) {
            this.a(var8 + var1, var7 + var2);
            int var9 = var5[var8 + 1 + (var7 + 1) * (var3 + 2)];
            if (this.a(57) == 0) {
               if (var9 == BiomeGenBase.plains.az) {
                  var6[var8 + var7 * var3] = BiomeGenBase.plains.az + 128;
               } else {
                  var6[var8 + var7 * var3] = var9;
               }
            } else {
               var6[var8 + var7 * var3] = var9;
            }
         }
      }

      return var6;
   }

   public GenLayerRareBiome(long var1, GenLayer var3) {
      super(var1);
      this.a = var3;
   }
}
