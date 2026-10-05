package net.optifine;

import com.cheatbreaker.client.nethandler.ByteBufWrapper;
import net.minecraft.command.CommandEntityData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
   public static boolean logDetail = System.getProperty("log.detail", "false").equals("true");
   public CommandEntityData field_0003;
   public ByteBufWrapper field_0000;
   public static Logger LOGGER = LogManager.getLogger();

   public static void error(String var0, Throwable var1) {
      LOGGER.error("[OptiFine] " + var0, var1);
   }

   public static void detail(String var0) {
      if (logDetail) {
         LOGGER.info("[OptiFine] " + var0);
      }
   }

   public static void error(String var0) {
      LOGGER.error("[OptiFine] " + var0);
   }

   public static void warn(String var0, Throwable var1) {
      LOGGER.warn("[OptiFine] " + var0, var1);
   }

   public static void dbg(String var0) {
      LOGGER.info("[OptiFine] " + var0);
   }

   public static void log(String var0) {
      dbg(var0);
   }

   public static void warn(String var0) {
      LOGGER.warn("[OptiFine] " + var0);
   }
}
