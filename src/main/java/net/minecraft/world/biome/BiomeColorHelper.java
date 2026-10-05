package net.minecraft.world.biome;

import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BiomeColorHelper {
   public static BiomeColorHelper.ColorResolver GRASS_COLOR = new BiomeColorHelper.ColorResolver() {
      @Override
      public int getColorAtPos(BiomeGenBase var1, BlockPos var2) {
         return var1.getGrassColorAtPos(var2);
      }
   };
   public static BiomeColorHelper.ColorResolver FOLIAGE_COLOR = new BiomeColorHelper.ColorResolver() {
      @Override
      public int getColorAtPos(BiomeGenBase var1, BlockPos var2) {
         return var1.getFoliageColorAtPos(var2);
      }
   };
   public static BiomeColorHelper.ColorResolver WATER_COLOR_MULTIPLIER = new BiomeColorHelper.ColorResolver() {
      @Override
      public int getColorAtPos(BiomeGenBase var1, BlockPos var2) {
         return var1.ar;
      }
   };

   public static int getGrassColorAtPos(IBlockAccess var0, BlockPos var1) {
      return getColorAtPos(var0, var1, GRASS_COLOR);
   }

   public static int getWaterColorAtPos(IBlockAccess var0, BlockPos var1) {
      return getColorAtPos(var0, var1, WATER_COLOR_MULTIPLIER);
   }

   public static int getColorAtPos(IBlockAccess var0, BlockPos var1, BiomeColorHelper.ColorResolver var2) {
      int var3 = 0;
      int var4 = 0;
      int var5 = 0;

      for (BlockPos.MutableBlockPos var7 : BlockPos.getAllInBoxMutable(var1.add(-1, 0, -1), var1.add(1, 0, 1))) {
         int var8 = var2.getColorAtPos(var0.getBiomeGenForCoords(var7), var7);
         var3 += (var8 & 0xFF0000) >> 16;
         var4 += (var8 & 0xFF00) >> 8;
         var5 += var8 & 0xFF;
      }

      return (var3 / 9 & 0xFF) << 16 | (var4 / 9 & 0xFF) << 8 | var5 / 9 & 0xFF;
   }

   public static int getFoliageColorAtPos(IBlockAccess var0, BlockPos var1) {
      return getColorAtPos(var0, var1, FOLIAGE_COLOR);
   }

   public interface ColorResolver {
      int getColorAtPos(BiomeGenBase var1, BlockPos var2);
   }
}
