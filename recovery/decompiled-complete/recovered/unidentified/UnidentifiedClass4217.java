package recovered.unidentified;

import io.netty.buffer.ByteBufOutputStream;
import java.io.File;
import java.io.FilenameFilter;
import net.minecraft.client.renderer.block.model.BreakingFour$1;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$2;
import org.scijava.nativelib.BaseJniExtractor;

public class UnidentifiedClass4217 implements FilenameFilter {
   public CategoryNodeEditor$2 field_0002;
   public ByteBufOutputStream field_0004;
   public BreakingFour$1 field_0000;

   public UnidentifiedClass4217(BaseJniExtractor var1, String var2, String var3) {
      this.field_0001 = var1;
      this.field_0005 = var2;
      this.field_0003 = var3;
      super();
   }

   @Override
   public boolean accept(File var1, String var2) {
      return var2.startsWith(this.field_0005) && var2.endsWith(this.field_0003);
   }
}
