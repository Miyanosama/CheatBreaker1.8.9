package recovered.unidentified;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.CustomColors;
import net.optifine.CustomColors$IColorizer;
import net.optifine.shaders.IteratorRenderChunks;
import net.optifine.shaders.config.ShaderOptionSwitchConst;

public class UnidentifiedClass1818 implements CustomColors$IColorizer {
   public ShaderOptionSwitchConst field_0000;
   public IteratorRenderChunks field_0001;

   @Override
   public boolean isColorConstant() {
      return false;
   }

   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      BiomeGenBase var4 = CustomColors.getColorBiome(var2, var3);
      return CustomColors.access$000() != null && var4 == BiomeGenBase.swampland
         ? CustomColors.access$000().getColor(var4, var3)
         : var4.getGrassColorAtPos(var3);
   }
}
