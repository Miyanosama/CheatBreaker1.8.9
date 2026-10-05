package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerHills extends GenLayer {
   public GenLayer field_151628_d;
   public static Logger logger = LogManager.getLogger();

   public GenLayerHills(long var1, GenLayer var3, GenLayer var4) {
      super(var1);
      this.a = var3;
      this.field_151628_d = var4;
   }

   @Override
   public int[] getInts(int var1, int var2, int var3, int var4) {
      int[] var5 = this.a.getInts(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
      int[] var6 = this.field_151628_d.getInts(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
      int[] var7 = IntCache.getIntCache(var3 * var4);

      for (int var8 = 0; var8 < var4; var8++) {
         for (int var9 = 0; var9 < var3; var9++) {
            this.a(var9 + var1, var8 + var2);
            int var10 = var5[var9 + 1 + (var8 + 1) * (var3 + 2)];
            int var11 = var6[var9 + 1 + (var8 + 1) * (var3 + 2)];
            boolean var12 = (var11 - 2) % 29 == 0;
            if (var10 > 255) {
               logger.debug("old! " + var10);
            }

            if (var10 != 0 && var11 >= 2 && (var11 - 2) % 29 == 1 && var10 < 128) {
               if (BiomeGenBase.getBiome(var10 + 128) != null) {
                  var7[var9 + var8 * var3] = var10 + 128;
               } else {
                  var7[var9 + var8 * var3] = var10;
               }
            } else if (this.a(3) != 0 && !var12) {
               var7[var9 + var8 * var3] = var10;
            } else {
               int var13 = var10;
               if (var10 == BiomeGenBase.desert.az) {
                  var13 = BiomeGenBase.desertHills.az;
               } else if (var10 == BiomeGenBase.forest.az) {
                  var13 = BiomeGenBase.forestHills.az;
               } else if (var10 == BiomeGenBase.birchForest.az) {
                  var13 = BiomeGenBase.recoveredField756.az;
               } else if (var10 == BiomeGenBase.roofedForest.az) {
                  var13 = BiomeGenBase.plains.az;
               } else if (var10 == BiomeGenBase.taiga.az) {
                  var13 = BiomeGenBase.taigaHills.az;
               } else if (var10 == BiomeGenBase.megaTaiga.az) {
                  var13 = BiomeGenBase.recoveredField755.az;
               } else if (var10 == BiomeGenBase.coldTaiga.az) {
                  var13 = BiomeGenBase.recoveredField758.az;
               } else if (var10 == BiomeGenBase.plains.az) {
                  if (this.a(3) == 0) {
                     var13 = BiomeGenBase.forestHills.az;
                  } else {
                     var13 = BiomeGenBase.forest.az;
                  }
               } else if (var10 == BiomeGenBase.icePlains.az) {
                  var13 = BiomeGenBase.recoveredField748.az;
               } else if (var10 == BiomeGenBase.jungle.az) {
                  var13 = BiomeGenBase.jungleHills.az;
               } else if (var10 == BiomeGenBase.ocean.az) {
                  var13 = BiomeGenBase.deepOcean.az;
               } else if (var10 == BiomeGenBase.extremeHills.az) {
                  var13 = BiomeGenBase.recoveredField750.az;
               } else if (var10 == BiomeGenBase.savanna.az) {
                  var13 = BiomeGenBase.recoveredField743.az;
               } else if (biomesEqualOrMesaPlateau(var10, BiomeGenBase.mesaPlateau_F.az)) {
                  var13 = BiomeGenBase.mesa.az;
               } else if (var10 == BiomeGenBase.deepOcean.az && this.a(3) == 0) {
                  int var14 = this.a(2);
                  if (var14 == 0) {
                     var13 = BiomeGenBase.plains.az;
                  } else {
                     var13 = BiomeGenBase.forest.az;
                  }
               }

               if (var12 && var13 != var10) {
                  if (BiomeGenBase.getBiome(var13 + 128) != null) {
                     var13 += 128;
                  } else {
                     var13 = var10;
                  }
               }

               if (var13 == var10) {
                  var7[var9 + var8 * var3] = var10;
               } else {
                  int var19 = var5[var9 + 1 + (var8 + 1 - 1) * (var3 + 2)];
                  int var15 = var5[var9 + 1 + 1 + (var8 + 1) * (var3 + 2)];
                  int var16 = var5[var9 + 1 - 1 + (var8 + 1) * (var3 + 2)];
                  int var17 = var5[var9 + 1 + (var8 + 1 + 1) * (var3 + 2)];
                  int var18 = 0;
                  if (biomesEqualOrMesaPlateau(var19, var10)) {
                     var18++;
                  }

                  if (biomesEqualOrMesaPlateau(var15, var10)) {
                     var18++;
                  }

                  if (biomesEqualOrMesaPlateau(var16, var10)) {
                     var18++;
                  }

                  if (biomesEqualOrMesaPlateau(var17, var10)) {
                     var18++;
                  }

                  if (var18 >= 3) {
                     var7[var9 + var8 * var3] = var13;
                  } else {
                     var7[var9 + var8 * var3] = var10;
                  }
               }
            }
         }
      }

      return var7;
   }
}
