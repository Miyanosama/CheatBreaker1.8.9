package net.minecraft.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import net.minecraft.block.BlockWorkbench$InterfaceCraftingTable;
import net.minecraft.network.play.client.C0BPacketEntityAction$Action;
import net.optifine.gui.GuiQualitySettingsOF;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.helpers.LogLog;
import org.apache.logging.log4j.Logger;

public class Util {
   public C0BPacketEntityAction$Action field_0002;
   public GuiQualitySettingsOF field_0004;
   public BasicConfigurator field_0001;
   public BlockWorkbench$InterfaceCraftingTable field_0003;
   public LogLog field_0000;

   public static Util$EnumOS getOSType() {
      String var0 = System.getProperty("os.name").toLowerCase();
      return var0.contains("win")
         ? Util$EnumOS.WINDOWS
         : (
            var0.contains("mac")
               ? Util$EnumOS.OSX
               : (
                  var0.contains("solaris")
                     ? Util$EnumOS.SOLARIS
                     : (
                        var0.contains("sunos")
                           ? Util$EnumOS.SOLARIS
                           : (var0.contains("linux") ? Util$EnumOS.LINUX : (var0.contains("unix") ? Util$EnumOS.LINUX : Util$EnumOS.UNKNOWN))
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
}
