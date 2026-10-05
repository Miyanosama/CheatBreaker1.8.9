package recovered.unidentified;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.optifine.CustomColors;
import net.optifine.CustomColors$IColorizer;
import net.optifine.shaders.uniform.ShaderExpressionResolver;

public class UnidentifiedClass4788 implements CustomColors$IColorizer {
   public UnidentifiedClass3613 field_0001;
   public MapGenStructureIO field_0002;
   public ShaderExpressionResolver field_0000;

   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return CustomColors.access$200() != null ? CustomColors.access$200().getColor(var2, var3) : ColorizerFoliage.getFoliageColorPine();
   }

   @Override
   public boolean isColorConstant() {
      return CustomColors.access$200() == null;
   }
}
