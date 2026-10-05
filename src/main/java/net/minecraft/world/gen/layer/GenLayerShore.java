package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShore extends GenLayer {
   @Override
   public int[] getInts(int var1, int var2, int var3, int var4) {
      int[] var5 = this.a.getInts(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
      int[] var6 = IntCache.getIntCache(var3 * var4);

      for (int var7 = 0; var7 < var4; var7++) {
         for (int var8 = 0; var8 < var3; var8++) {
            this.a(var8 + var1, var7 + var2);
            int var9 = var5[var8 + 1 + (var7 + 1) * (var3 + 2)];
            BiomeGenBase var10 = BiomeGenBase.getBiome(var9);
            if (var9 == BiomeGenBase.mushroomIsland.az) {
               int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
               int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
               int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
               int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
               if (var11 != BiomeGenBase.ocean.az && var12 != BiomeGenBase.ocean.az && var13 != BiomeGenBase.ocean.az && var14 != BiomeGenBase.ocean.az) {
                  var6[var8 + var7 * var3] = var9;
               } else {
                  var6[var8 + var7 * var3] = BiomeGenBase.recoveredField745.az;
               }
            } else if (var10 != null && var10.getBiomeClass() == BiomeGenJungle.class) {
               int var17 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
               int var20 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
               int var23 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
               int var26 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
               if (!this.method_30253(var17) || !this.method_30253(var20) || !this.method_30253(var23) || !this.method_30253(var26)) {
                  var6[var8 + var7 * var3] = BiomeGenBase.recoveredField761.az;
               } else if (!isBiomeOceanic(var17) && !isBiomeOceanic(var20) && !isBiomeOceanic(var23) && !isBiomeOceanic(var26)) {
                  var6[var8 + var7 * var3] = var9;
               } else {
                  var6[var8 + var7 * var3] = BiomeGenBase.recoveredField740.az;
               }
            } else if (var9 == BiomeGenBase.extremeHills.az || var9 == BiomeGenBase.recoveredField750.az || var9 == BiomeGenBase.recoveredField751.az) {
               this.method_30254(var5, var6, var8, var7, var3, var9, BiomeGenBase.recoveredField744.az);
            } else if (var10 != null && var10.isSnowyBiome()) {
               this.method_30254(var5, var6, var8, var7, var3, var9, BiomeGenBase.recoveredField749.az);
            } else if (var9 == BiomeGenBase.mesa.az || var9 == BiomeGenBase.mesaPlateau_F.az) {
               int var16 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
               int var19 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
               int var22 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
               int var25 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
               if (isBiomeOceanic(var16) || isBiomeOceanic(var19) || isBiomeOceanic(var22) || isBiomeOceanic(var25)) {
                  var6[var8 + var7 * var3] = var9;
               } else if (this.method_30255(var16) && this.method_30255(var19) && this.method_30255(var22) && this.method_30255(var25)) {
                  var6[var8 + var7 * var3] = var9;
               } else {
                  var6[var8 + var7 * var3] = BiomeGenBase.desert.az;
               }
            } else if (var9 != BiomeGenBase.ocean.az && var9 != BiomeGenBase.deepOcean.az && var9 != BiomeGenBase.river.az && var9 != BiomeGenBase.swampland.az
               )
             {
               int var15 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
               int var18 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
               int var21 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
               int var24 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
               if (!isBiomeOceanic(var15) && !isBiomeOceanic(var18) && !isBiomeOceanic(var21) && !isBiomeOceanic(var24)) {
                  var6[var8 + var7 * var3] = var9;
               } else {
                  var6[var8 + var7 * var3] = BiomeGenBase.recoveredField740.az;
               }
            } else {
               var6[var8 + var7 * var3] = var9;
            }
         }
      }

      return var6;
   }

   public GenLayerShore(long var1, GenLayer var3) {
      super(var1);
      this.a = var3;
   }

   public boolean method_30253(int var1) {
      return BiomeGenBase.getBiome(var1) != null && BiomeGenBase.getBiome(var1).getBiomeClass() == BiomeGenJungle.class
         ? true
         : var1 == BiomeGenBase.recoveredField761.az
            || var1 == BiomeGenBase.jungle.az
            || var1 == BiomeGenBase.jungleHills.az
            || var1 == BiomeGenBase.forest.az
            || var1 == BiomeGenBase.taiga.az
            || isBiomeOceanic(var1);
   }

   public boolean method_30255(int var1) {
      return BiomeGenBase.getBiome(var1) instanceof BiomeGenMesa;
   }

   public void method_30254(int[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7) {
      if (isBiomeOceanic(var6)) {
         var2[var3 + var4 * var5] = var6;
      } else {
         int var8 = var1[var3 + 1 + (var4 + 1 - 1) * (var5 + 2)];
         int var9 = var1[var3 + 1 + 1 + (var4 + 1) * (var5 + 2)];
         int var10 = var1[var3 + 1 - 1 + (var4 + 1) * (var5 + 2)];
         int var11 = var1[var3 + 1 + (var4 + 1 + 1) * (var5 + 2)];
         if (!isBiomeOceanic(var8) && !isBiomeOceanic(var9) && !isBiomeOceanic(var10) && !isBiomeOceanic(var11)) {
            var2[var3 + var4 * var5] = var6;
         } else {
            var2[var3 + var4 * var5] = var7;
         }
      }
   }
}
