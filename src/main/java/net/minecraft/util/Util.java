package net.minecraft.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import org.apache.logging.log4j.Logger;

public class Util {
   public static Util.EnumOS getOSType() {
      String var0 = System.getProperty("os.name").toLowerCase();
      return var0.contains("win")
         ? Util.EnumOS.WINDOWS
         : (
            var0.contains("mac")
               ? Util.EnumOS.OSX
               : (
                  var0.contains("solaris")
                     ? Util.EnumOS.SOLARIS
                     : (
                        var0.contains("sunos")
                           ? Util.EnumOS.SOLARIS
                           : (var0.contains("linux") ? Util.EnumOS.LINUX : (var0.contains("unix") ? Util.EnumOS.LINUX : Util.EnumOS.UNKNOWN))
                     )
               )
         );
   }

   public static <V> V runTask(FutureTask<V> var0, Logger var1) {
      try {
         var0.run();
         return (V)var0.get();
      } catch (ExecutionException var4) {
         var1.fatal("Error executing task", var4);
         if (var4.getCause() instanceof OutOfMemoryError) {
            OutOfMemoryError var3 = (OutOfMemoryError)var4.getCause();
            throw var3;
         }
      } catch (InterruptedException var5) {
         var1.fatal("Error executing task", var5);
      }

      return null;
   }

   public static enum EnumOS {
      LINUX,
      SOLARIS,
      WINDOWS,
      OSX,
      UNKNOWN;
      // $VF: synthetic field
      public static Util.EnumOS[] $VALUES = new Util.EnumOS[]{Util.EnumOS.LINUX, Util.EnumOS.SOLARIS, WINDOWS, OSX, Util.EnumOS.UNKNOWN};
   }
}
