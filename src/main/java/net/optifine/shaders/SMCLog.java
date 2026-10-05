package net.optifine.shaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SMCLog {
   public static final String recoveredField813 = "[Shaders] ";
   public static Logger LOGGER = LogManager.getLogger();

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
