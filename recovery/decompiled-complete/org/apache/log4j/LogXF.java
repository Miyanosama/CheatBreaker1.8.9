package org.apache.log4j;

import net.minecraft.command.server.CommandBlockLogic$1;
import net.minecraft.entity.ai.EntityMinecartMobSpawner$1;
import net.minecraft.inventory.ContainerRepair;
import org.apache.log4j.spi.LoggingEvent;

public abstract class LogXF {
   public EntityMinecartMobSpawner$1 field_0004;
   public static Class class$org$apache$log4j$LogXF;
   public ContainerRepair field_0005;
   public static String FQCN = (class$org$apache$log4j$LogXF == null
         ? (class$org$apache$log4j$LogXF = class$("org.apache.log4j.LogXF"))
         : class$org$apache$log4j$LogXF)
      .getName();
   public CommandBlockLogic$1 field_0002;
   public static Level TRACE = new Level(5000, "TRACE", 7);

   public static Short valueOf(short var0) {
      return new Short(var0);
   }

   public static Character valueOf(char var0) {
      return new Character(var0);
   }

   public static Object[] toArray(Object var0) {
      return new Object[]{var0};
   }

   public static Double valueOf(double var0) {
      return new Double(var0);
   }

   public static Object[] toArray(Object var0, Object var1, Object var2, Object var3) {
      return new Object[]{var0, var1, var2, var3};
   }

   public static void entering(Logger var0, String var1, String var2, String var3) {
      if (var0.isDebugEnabled()) {
         String var4 = var1 + "." + var2 + " ENTRY " + var3;
         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var4, null));
      }
   }

   public static Boolean valueOf(boolean var0) {
      return var0 ? Boolean.TRUE : Boolean.FALSE;
   }

   public static void exiting(Logger var0, String var1, String var2, Object var3) {
      if (var0.isDebugEnabled()) {
         Object var4 = var1 + "." + var2 + " RETURN ";
         if (var3 == null) {
            var4 = var4 + "null";
         } else {
            try {
               var4 = var4 + var3;
            } catch (Throwable var6) {
               var4 = var4 + "?";
            }
         }

         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var4, null));
      }
   }

   public static void entering(Logger var0, String var1, String var2) {
      if (var0.isDebugEnabled()) {
         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var1 + "." + var2 + " ENTRY", null));
      }
   }

   public static Integer valueOf(int var0) {
      return new Integer(var0);
   }

   public static Float valueOf(float var0) {
      return new Float(var0);
   }

   public static void throwing(Logger var0, String var1, String var2, Throwable var3) {
      if (var0.isDebugEnabled()) {
         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var1 + "." + var2 + " THROW", var3));
      }
   }

   public static Byte valueOf(byte var0) {
      return new Byte(var0);
   }

   public static Object[] toArray(Object var0, Object var1, Object var2) {
      return new Object[]{var0, var1, var2};
   }

   public static void entering(Logger var0, String var1, String var2, Object var3) {
      if (var0.isDebugEnabled()) {
         Object var4 = var1 + "." + var2 + " ENTRY ";
         if (var3 == null) {
            var4 = var4 + "null";
         } else {
            try {
               var4 = var4 + var3;
            } catch (Throwable var6) {
               var4 = var4 + "?";
            }
         }

         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var4, null));
      }
   }

   public static void entering(Logger var0, String var1, String var2, Object[] var3) {
      if (var0.isDebugEnabled()) {
         String var4 = var1 + "." + var2 + " ENTRY ";
         if (var3 != null && var3.length > 0) {
            String var5 = "{";

            for (int var6 = 0; var6 < var3.length; var6++) {
               try {
                  var4 = var4 + var5 + var3[var6];
               } catch (Throwable var8) {
                  var4 = var4 + var5 + "?";
               }

               var5 = ",";
            }

            var4 = var4 + "}";
         } else {
            var4 = var4 + "{}";
         }

         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var4, null));
      }
   }

   public static void exiting(Logger var0, String var1, String var2) {
      if (var0.isDebugEnabled()) {
         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var1 + "." + var2 + " RETURN", null));
      }
   }

   public static Long valueOf(long var0) {
      return new Long(var0);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void exiting(Logger var0, String var1, String var2, String var3) {
      if (var0.isDebugEnabled()) {
         var0.callAppenders(new LoggingEvent(FQCN, var0, Level.DEBUG, var1 + "." + var2 + " RETURN " + var3, null));
      }
   }

   public static Object[] toArray(Object var0, Object var1) {
      return new Object[]{var0, var1};
   }
}
