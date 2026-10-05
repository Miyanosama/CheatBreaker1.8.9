package javazoom.jl.decoder;

import net.minecraft.block.BlockPumpkin$1;
import net.minecraft.client.renderer.BlockModelShapes$3;
import net.minecraft.init.Bootstrap$8;

public class BitstreamException extends JavaLayerException implements BitstreamErrors {
   public BlockModelShapes$3 __junk2943341399349854999;
   public int errorcode = 256;
   public Bootstrap$8 __junk1444916745018928873;
   public BlockPumpkin$1 __junk1777445326039288650;

   public int getErrorCode() {
      return this.errorcode;
   }

   public static String getErrorString(int var0) {
      return "Bitstream errorcode " + Integer.toHexString(var0);
   }

   public BitstreamException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public BitstreamException(int var1, Throwable var2) {
      this(getErrorString(var1), var2);
      this.errorcode = var1;
   }
}
