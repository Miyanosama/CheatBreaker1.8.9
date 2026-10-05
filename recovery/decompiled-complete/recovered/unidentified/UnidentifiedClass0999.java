package recovered.unidentified;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeGenBase;
import net.optifine.CustomColors;
import net.optifine.CustomColors$IColorizer;
import net.optifine.render.CloudRenderer;

public class UnidentifiedClass0999 implements CustomColors$IColorizer {
   public CloudRenderer field_0000;
   public ThreadLanServerPing field_0001;

   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      BiomeGenBase var4 = CustomColors.getColorBiome(var2, var3);
      return CustomColors.access$100() != null && var4 == BiomeGenBase.swampland
         ? CustomColors.access$100().getColor(var4, var3)
         : var4.getFoliageColorAtPos(var3);
   }

   @Override
   public boolean isColorConstant() {
      return false;
   }
}
