package net.minecraft.world.gen.layer;

import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpRequest;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockNewLog$2;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$TempCategory;

public class GenLayerBiomeEdge extends GenLayer {
   public EnumDyeColor field_0000;
   public BlockNewLog$2 field_0003;
   public BlockCommandBlock field_0002;
   public HttpObjectAggregator$AggregatedFullHttpRequest field_0001;

   public boolean method_21954(int[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      if (!biomesEqualOrMesaPlateau(var6, var7)) {
         return false;
      } else {
         int var9 = var1[var3 + 1 + (var4 + 1 - 1) * (var5 + 2)];
         int var10 = var1[var3 + 1 + 1 + (var4 + 1) * (var5 + 2)];
         int var11 = var1[var3 + 1 - 1 + (var4 + 1) * (var5 + 2)];
         int var12 = var1[var3 + 1 + (var4 + 1 + 1) * (var5 + 2)];
         if (this.canBiomesBeNeighbors(var9, var7)
            && this.canBiomesBeNeighbors(var10, var7)
            && this.canBiomesBeNeighbors(var11, var7)
            && this.canBiomesBeNeighbors(var12, var7)) {
            var2[var3 + var4 * var5] = var6;
         } else {
            var2[var3 + var4 * var5] = var8;
         }

         return true;
      }
   }

   public boolean canBiomesBeNeighbors(int var1, int var2) {
      if (biomesEqualOrMesaPlateau(var1, var2)) {
         return true;
      } else {
         BiomeGenBase var3 = BiomeGenBase.getBiome(var1);
         BiomeGenBase var4 = BiomeGenBase.getBiome(var2);
         if (var3 != null && var4 != null) {
            BiomeGenBase$TempCategory var5 = var3.getTempCategory();
            BiomeGenBase$TempCategory var6 = var4.getTempCategory();
            return var5 == var6 || var5 == BiomeGenBase$TempCategory.MEDIUM || var6 == BiomeGenBase$TempCategory.MEDIUM;
         } else {
            return false;
         }
      }
   }

   public GenLayerBiomeEdge(long var1, GenLayer var3) {
      super(var1);
      this.a = var3;
   }

   public boolean method_21956(int[] var1, int[] var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      if (var6 != var7) {
         return false;
      } else {
         int var9 = var1[var3 + 1 + (var4 + 1 - 1) * (var5 + 2)];
         int var10 = var1[var3 + 1 + 1 + (var4 + 1) * (var5 + 2)];
         int var11 = var1[var3 + 1 - 1 + (var4 + 1) * (var5 + 2)];
         int var12 = var1[var3 + 1 + (var4 + 1 + 1) * (var5 + 2)];
         if (biomesEqualOrMesaPlateau(var9, var7)
            && biomesEqualOrMesaPlateau(var10, var7)
            && biomesEqualOrMesaPlateau(var11, var7)
            && biomesEqualOrMesaPlateau(var12, var7)) {
            var2[var3 + var4 * var5] = var6;
         } else {
            var2[var3 + var4 * var5] = var8;
         }

         return true;
      }
   }

   @Override
   public int[] getInts(int var1, int var2, int var3, int var4) {
      int[] var5 = this.a.getInts(var1 - 1, var2 - 1, var3 + 2, var4 + 2);
      int[] var6 = IntCache.getIntCache(var3 * var4);

      for (int var7 = 0; var7 < var4; var7++) {
         for (int var8 = 0; var8 < var3; var8++) {
            this.a(var8 + var1, var7 + var2);
            int var9 = var5[var8 + 1 + (var7 + 1) * (var3 + 2)];
            if (!this.method_21954(var5, var6, var8, var7, var3, var9, BiomeGenBase.extremeHills.az, BiomeGenBase.field_0052.az)
               && !this.method_21956(var5, var6, var8, var7, var3, var9, BiomeGenBase.mesaPlateau_F.az, BiomeGenBase.mesa.az)
               && !this.method_21956(var5, var6, var8, var7, var3, var9, BiomeGenBase.mesaPlateau.az, BiomeGenBase.mesa.az)
               && !this.method_21956(var5, var6, var8, var7, var3, var9, BiomeGenBase.megaTaiga.az, BiomeGenBase.taiga.az)) {
               if (var9 == BiomeGenBase.desert.az) {
                  int var10 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
                  int var11 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
                  int var12 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
                  int var13 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
                  if (var10 != BiomeGenBase.icePlains.az
                     && var11 != BiomeGenBase.icePlains.az
                     && var12 != BiomeGenBase.icePlains.az
                     && var13 != BiomeGenBase.icePlains.az) {
                     var6[var8 + var7 * var3] = var9;
                  } else {
                     var6[var8 + var7 * var3] = BiomeGenBase.field_0048.az;
                  }
               } else if (var9 == BiomeGenBase.swampland.az) {
                  int var14 = var5[var8 + 1 + (var7 + 1 - 1) * (var3 + 2)];
                  int var15 = var5[var8 + 1 + 1 + (var7 + 1) * (var3 + 2)];
                  int var16 = var5[var8 + 1 - 1 + (var7 + 1) * (var3 + 2)];
                  int var17 = var5[var8 + 1 + (var7 + 1 + 1) * (var3 + 2)];
                  if (var14 == BiomeGenBase.desert.az
                     || var15 == BiomeGenBase.desert.az
                     || var16 == BiomeGenBase.desert.az
                     || var17 == BiomeGenBase.desert.az
                     || var14 == BiomeGenBase.coldTaiga.az
                     || var15 == BiomeGenBase.coldTaiga.az
                     || var16 == BiomeGenBase.coldTaiga.az
                     || var17 == BiomeGenBase.coldTaiga.az
                     || var14 == BiomeGenBase.icePlains.az
                     || var15 == BiomeGenBase.icePlains.az
                     || var16 == BiomeGenBase.icePlains.az
                     || var17 == BiomeGenBase.icePlains.az) {
                     var6[var8 + var7 * var3] = BiomeGenBase.plains.az;
                  } else if (var14 != BiomeGenBase.jungle.az
                     && var17 != BiomeGenBase.jungle.az
                     && var15 != BiomeGenBase.jungle.az
                     && var16 != BiomeGenBase.jungle.az) {
                     var6[var8 + var7 * var3] = var9;
                  } else {
                     var6[var8 + var7 * var3] = BiomeGenBase.field_0059.az;
                  }
               } else {
                  var6[var8 + var7 * var3] = var9;
               }
            }
         }
      }

      return var6;
   }
}
