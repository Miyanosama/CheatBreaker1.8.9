package net.optifine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.CustomColors;
import net.optifine.reflect.Reflector;

public class CustomColors$5 implements CustomColors.IColorizer {
   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      BiomeGenBase var4 = CustomColors.getColorBiome(var2, var3);
      return CustomColors.method_29853() != null
         ? CustomColors.method_29853().getColor(var4, var3)
         : (Reflector.ForgeBiome_getWaterColorMultiplier.exists() ? Reflector.callInt(var4, Reflector.ForgeBiome_getWaterColorMultiplier) : var4.ar);
   }

   @Override
   public boolean isColorConstant() {
      return false;
   }
}
