package io.netty.util.internal;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import net.minecraft.client.gui.GuiButtonRealmsProxy;
import net.minecraft.util.ChatComponentTranslationFormatException;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.config.PropertySetterException;

public class SystemPropertyUtil {
   public static boolean initializedLogger = false;
   public static boolean loggedException;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(SystemPropertyUtil.class);

   static {
      initializedLogger = true;
   }

   public static void log(String var0, Exception var1) {
      if (initializedLogger) {
         logger.warn(var0, (Throwable)var1);
      } else {
         Logger.getLogger(SystemPropertyUtil.class.getName()).log(Level.WARNING, var0, (Throwable)var1);
      }
   }

   public static boolean getBoolean(String var0, boolean var1) {
      String var2 = get(var0);
      if (var2 == null) {
         return var1;
      } else {
         var2 = var2.trim().toLowerCase();
         if (var2.isEmpty()) {
            return true;
         } else if ("true".equals(var2) || "yes".equals(var2) || "1".equals(var2)) {
            return true;
         } else if (!"false".equals(var2) && !"no".equals(var2) && !"0".equals(var2)) {
            log("Unable to parse the boolean system property '" + var0 + "':" + var2 + " - " + "using the default value: " + var1);
            return var1;
         } else {
            return false;
         }
      }
   }

   public static boolean contains(String var0) {
      return get(var0) != null;
   }
   public static Pattern INTEGER_PATTERN = Pattern.compile("-?[0-9]+");

   public static String get(final String var0, String var1) {
      if (var0 == null) {
         throw new NullPointerException("key");
      } else if (var0.isEmpty()) {
         throw new IllegalArgumentException("key must not be empty.");
      } else {
         String var2 = null;

         try {
            if (System.getSecurityManager() == null) {
               var2 = System.getProperty(var0);
            } else {
               var2 = AccessController.doPrivileged(new PrivilegedAction<String>() {

                  public String run() {
                     return System.getProperty(var0);
                  }
               });
            }
         } catch (Exception var4) {
            if (!loggedException) {
               log("Unable to retrieve a system property '" + var0 + "'; default values will be used.", var4);
               loggedException = true;
            }
         }

         return var2 == null ? var1 : var2;
      }
   }

   public static int getInt(String var0, int var1) {
      String var2 = get(var0);
      if (var2 == null) {
         return var1;
      } else {
         var2 = var2.trim().toLowerCase();
         if (INTEGER_PATTERN.matcher(var2).matches()) {
            try {
               return Integer.parseInt(var2);
            } catch (Exception var4) {
            }
         }

         log("Unable to parse the integer system property '" + var0 + "':" + var2 + " - " + "using the default value: " + var1);
         return var1;
      }
   }

   public static String get(String var0) {
      return get(var0, null);
   }

   public static long getLong(String var0, long var1) {
      String var3 = get(var0);
      if (var3 == null) {
         return var1;
      } else {
         var3 = var3.trim().toLowerCase();
         if (INTEGER_PATTERN.matcher(var3).matches()) {
            try {
               return Long.parseLong(var3);
            } catch (Exception var5) {
            }
         }

         log("Unable to parse the long integer system property '" + var0 + "':" + var3 + " - " + "using the default value: " + var1);
         return var1;
      }
   }

   public static void log(String var0) {
      if (initializedLogger) {
         logger.warn(var0);
      } else {
         Logger.getLogger(SystemPropertyUtil.class.getName()).log(Level.WARNING, var0);
      }
   }
}
