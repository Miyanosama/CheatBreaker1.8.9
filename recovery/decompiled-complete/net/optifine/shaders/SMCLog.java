package net.optifine.shaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import recovered.unidentified.UnidentifiedClass4298;
import recovered.unidentified.UnidentifiedClass4327;

public abstract class SMCLog {
   public static Logger LOGGER = LogManager.getLogger();
   public UnidentifiedClass4327 field_0003;
   public static String field_0000;
   public UnidentifiedClass4298 field_0002;

   public static void info(String var0, Object... var1) {
      String var2 = String.format(var0, var1);
      LOGGER.info("[Shaders] " + var2);
   }

   public static void severe(String var0) {
      LOGGER.error("[Shaders] " + var0);
   }

   public static void info(String var0) {
      LOGGER.info("[Shaders] " + var0);
   }

   public static void fine(String var0, Object... var1) {
      String var2 = String.format(var0, var1);
      LOGGER.debug("[Shaders] " + var2);
   }

   public static void warning(String var0, Object... var1) {
      String var2 = String.format(var0, var1);
      LOGGER.warn("[Shaders] " + var2);
   }

   public static void fine(String var0) {
      LOGGER.debug("[Shaders] " + var0);
   }

   public static void warning(String var0) {
      LOGGER.warn("[Shaders] " + var0);
   }

   public static void severe(String var0, Object... var1) {
      String var2 = String.format(var0, var1);
      LOGGER.error("[Shaders] " + var2);
   }
}
