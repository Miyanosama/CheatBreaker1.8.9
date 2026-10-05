package javazoom.jl.converter;

import java.io.PrintWriter;
import javazoom.jl.decoder.JavaLayerException;
import net.minecraft.dispenser.IBehaviorDispenseItem$1;

public class jlc {
   public IBehaviorDispenseItem$1 __junk874437470553305419;

   public static void main(String[] var0) {
      long var2 = System.currentTimeMillis();
      int var4 = var0.length + 1;
      String[] var1 = new String[var4];
      var1[0] = "jlc";

      for (int var5 = 0; var5 < var0.length; var5++) {
         var1[var5 + 1] = var0[var5];
      }

      jlc$jlcArgs var11 = new jlc$jlcArgs();
      if (!var11.processArgs(var1)) {
         System.exit(1);
      }

      Converter var6 = new Converter();
      int var7 = var11.verbose_mode ? var11.verbose_level : 0;
      Converter$PrintWriterProgressListener var8 = new Converter$PrintWriterProgressListener(new PrintWriter(System.out, true), var7);

      try {
         var6.convert(var11.filename, var11.output_filename, var8);
      } catch (JavaLayerException var10) {
         System.err.println("Convertion failure: " + var10);
      }

      System.exit(0);
   }
}
