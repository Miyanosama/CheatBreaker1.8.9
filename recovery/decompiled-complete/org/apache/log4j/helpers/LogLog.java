package org.apache.log4j.helpers;

import net.minecraft.tileentity.TileEntity;
import net.optifine.RandomTileEntity;
import recovered.unidentified.UnidentifiedClass0477;
import recovered.unidentified.UnidentifiedClass1472;

public class LogLog {
   public static String field_0005;
   public RandomTileEntity field_0008;
   public TileEntity field_0004;
   public UnidentifiedClass0477 field_0007;
   public static String field_0001;
   public static boolean debugEnabled = false;
   public UnidentifiedClass1472 field_0009;
   public static String field_0006;
   public static String field_0003;
   public static String field_0010;
   public static boolean quietMode = false;

   public static void debug(String var0, Throwable var1) {
      if (debugEnabled && !quietMode) {
         System.out.println("log4j: " + var0);
         if (var1 != null) {
            var1.printStackTrace(System.out);
         }
      }
   }

   public static void error(String var0, Throwable var1) {
      if (!quietMode) {
         System.err.println("log4j:ERROR " + var0);
         if (var1 != null) {
            var1.printStackTrace();
         }
      }
   }

   static {
      String var0 = OptionConverter.getSystemProperty("log4j.debug", null);
      if (var0 == null) {
         var0 = OptionConverter.getSystemProperty("log4j.configDebug", null);
      }

      if (var0 != null) {
         debugEnabled = OptionConverter.toBoolean(var0, true);
      }
   }

   public static void error(String var0) {
      if (!quietMode) {
         System.err.println("log4j:ERROR " + var0);
      }
   }

   public static void warn(String var0) {
      if (!quietMode) {
         System.err.println("log4j:WARN " + var0);
      }
   }

   public static void setQuietMode(boolean var0) {
      quietMode = var0;
   }

   public static void warn(String var0, Throwable var1) {
      if (!quietMode) {
         System.err.println("log4j:WARN " + var0);
         if (var1 != null) {
            var1.printStackTrace();
         }
      }
   }

   public static void debug(String var0) {
      if (debugEnabled && !quietMode) {
         System.out.println("log4j: " + var0);
      }
   }

   public static void setInternalDebugging(boolean var0) {
      debugEnabled = var0;
   }
}
