package recovered.unidentified;

import io.netty.handler.timeout.WriteTimeoutHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.client.renderer.tileentity.RenderWitherSkull;
import net.minecraft.client.stream.IngestServerTester$1;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.optifine.CustomColors;
import net.optifine.CustomColors$IColorizer;

public class UnidentifiedClass3854 implements CustomColors$IColorizer {
   public MovingSound field_0001;
   public WriteTimeoutHandler field_0003;
   public RenderWitherSkull field_0000;
   public IngestServerTester$1 field_0002;

   @Override
   public int getColor(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return CustomColors.access$300() != null ? CustomColors.access$300().getColor(var2, var3) : ColorizerFoliage.getFoliageColorBirch();
   }

   @Override
   public boolean isColorConstant() {
      return CustomColors.access$300() == null;
   }
}
