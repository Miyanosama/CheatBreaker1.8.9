package org.apache.log4j.helpers;

import io.netty.handler.ssl.util.BouncyCastleSelfSignedCertGenerator;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import net.minecraft.world.gen.feature.WorldGenForest;

public class Loader {
   public static final String TSTR = "Caught Exception while in Loader.getResource. This may be innocuous.";
   public static Class class$org$apache$log4j$helpers$Loader;
   public static boolean java1 = true;
   public static boolean ignoreTCL = false;
   public static Class class$java$lang$Thread;

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public static boolean isJava1() {
      return java1;
   }

   public static URL getResource(String var0) {
      ClassLoader var1 = null;
      URL var2 = null;

      try {
         if (!java1 && !ignoreTCL) {
            var1 = getTCL();
            if (var1 != null) {
               LogLog.debug("Trying to find [" + var0 + "] using context classloader " + var1 + ".");
               var2 = var1.getResource(var0);
               if (var2 != null) {
                  return var2;
               }
            }
         }

         var1 = (class$org$apache$log4j$helpers$Loader == null
               ? (class$org$apache$log4j$helpers$Loader = class$("org.apache.log4j.helpers.Loader"))
               : class$org$apache$log4j$helpers$Loader)
            .getClassLoader();
         if (var1 != null) {
            LogLog.debug("Trying to find [" + var0 + "] using " + var1 + " class loader.");
            var2 = var1.getResource(var0);
            if (var2 != null) {
               return var2;
            }
         }
      } catch (IllegalAccessException var4) {
         LogLog.warn("Caught Exception while in Loader.getResource. This may be innocuous.", var4);
      } catch (InvocationTargetException var5) {
         if (var5.getTargetException() instanceof InterruptedException || var5.getTargetException() instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         LogLog.warn("Caught Exception while in Loader.getResource. This may be innocuous.", var5);
      } catch (Throwable var6) {
         LogLog.warn("Caught Exception while in Loader.getResource. This may be innocuous.", var6);
      }

      LogLog.debug("Trying to find [" + var0 + "] using ClassLoader.getSystemResource().");
      return ClassLoader.getSystemResource(var0);
   }

   public static Class loadClass(String var0) throws java.lang.ClassNotFoundException {
      if (!java1 && !ignoreTCL) {
         try {
            return getTCL().loadClass(var0);
         } catch (InvocationTargetException var2) {
            if (var2.getTargetException() instanceof InterruptedException || var2.getTargetException() instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }
         } catch (Throwable var3) {
         }

         return Class.forName(var0);
      } else {
         return Class.forName(var0);
      }
   }

   public static URL getResource(String var0, Class var1) {
      return getResource(var0);
   }

   static {
      String var0 = OptionConverter.getSystemProperty("java.version", null);
      if (var0 != null) {
         int var1 = var0.indexOf(46);
         if (var1 != -1 && var0.charAt(var1 + 1) != '1') {
            java1 = false;
         }
      }

      String var2 = OptionConverter.getSystemProperty("log4j.ignoreTCL", null);
      if (var2 != null) {
         ignoreTCL = OptionConverter.toBoolean(var2, true);
      }
   }

   public static ClassLoader getTCL() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
      java.lang.reflect.Method var0 = null;

      try {
         var0 = (class$java$lang$Thread == null ? (class$java$lang$Thread = class$("java.lang.Thread")) : class$java$lang$Thread)
            .getMethod("getContextClassLoader", null);
      } catch (NoSuchMethodException var2) {
         return null;
      }

      return (ClassLoader)var0.invoke(Thread.currentThread(), null);
   }
}
