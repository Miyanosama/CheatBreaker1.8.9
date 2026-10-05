package org.apache.log4j.helpers;

import com.cheatbreaker.client.websocket.server.WSPacketBulkFriends;
import io.netty.channel.group.ChannelMatchers$InstanceMatcher;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;
import net.minecraft.client.stream.BroadcastController;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.spi.Configurator;
import org.apache.log4j.spi.LoggerRepository;
import recovered.unidentified.UnidentifiedClass4671;

public class OptionConverter {
   public static int DELIM_STOP_LEN = 1;
   public static Class class$org$apache$log4j$spi$Configurator;
   public static String DELIM_START = "${";
   public static Class class$java$lang$String;
   public static Class class$org$apache$log4j$Level;
   public BroadcastController field_0002;
   public static char DELIM_STOP = '}';
   public WSPacketBulkFriends field_0006;
   public UnidentifiedClass4671 field_0003;
   public ChannelMatchers$InstanceMatcher field_0010;
   public static int DELIM_START_LEN = 2;

   public static boolean toBoolean(String var0, boolean var1) {
      if (var0 == null) {
         return var1;
      } else {
         String var2 = var0.trim();
         if ("true".equalsIgnoreCase(var2)) {
            return true;
         } else {
            return "false".equalsIgnoreCase(var2) ? false : var1;
         }
      }
   }

   public static void selectAndConfigure(InputStream var0, String var1, LoggerRepository var2) {
      Object var3 = null;
      if (var1 != null) {
         LogLog.debug("Preferred configurator class: " + var1);
         var3 = (Configurator)instantiateByClassName(
            var1,
            class$org$apache$log4j$spi$Configurator == null
               ? (class$org$apache$log4j$spi$Configurator = class$("org.apache.log4j.spi.Configurator"))
               : class$org$apache$log4j$spi$Configurator,
            null
         );
         if (var3 == null) {
            LogLog.error("Could not instantiate configurator [" + var1 + "].");
            return;
         }
      } else {
         var3 = new PropertyConfigurator();
      }

      ((Configurator)var3).doConfigure(var0, var2);
   }

   public static Level toLevel(String var0, Level var1) {
      if (var0 == null) {
         return var1;
      } else {
         var0 = var0.trim();
         int var2 = var0.indexOf(35);
         if (var2 == -1) {
            return "NULL".equalsIgnoreCase(var0) ? null : Level.toLevel(var0, var1);
         } else {
            Level var3 = var1;
            String var4 = var0.substring(var2 + 1);
            String var5 = var0.substring(0, var2);
            if ("NULL".equalsIgnoreCase(var5)) {
               return null;
            } else {
               LogLog.debug("toLevel:class=[" + var4 + "]" + ":pri=[" + var5 + "]");

               try {
                  Class var6 = Loader.loadClass(var4);
                  Class[] var7 = new Class[]{
                     class$java$lang$String == null ? (class$java$lang$String = class$("java.lang.String")) : class$java$lang$String,
                     class$org$apache$log4j$Level == null ? (class$org$apache$log4j$Level = class$("org.apache.log4j.Level")) : class$org$apache$log4j$Level
                  };
                  Method var8 = var6.getMethod("toLevel", var7);
                  Object[] var9 = new Object[]{var5, var1};
                  Object var10 = var8.invoke(null, var9);
                  var3 = (Level)var10;
               } catch (ClassNotFoundException var11) {
                  LogLog.warn("custom level class [" + var4 + "] not found.");
               } catch (NoSuchMethodException var12) {
                  LogLog.warn("custom level class [" + var4 + "]" + " does not have a class function toLevel(String, Level)", var12);
               } catch (InvocationTargetException var13) {
                  if (var13.getTargetException() instanceof InterruptedException || var13.getTargetException() instanceof InterruptedIOException) {
                     Thread.currentThread().interrupt();
                  }

                  LogLog.warn("custom level class [" + var4 + "]" + " could not be instantiated", var13);
               } catch (ClassCastException var14) {
                  LogLog.warn("class [" + var4 + "] is not a subclass of org.apache.log4j.Level", var14);
               } catch (IllegalAccessException var15) {
                  LogLog.warn("class [" + var4 + "] cannot be instantiated due to access restrictions", var15);
               } catch (RuntimeException var16) {
                  LogLog.warn("class [" + var4 + "], level [" + var5 + "] conversion failed.", var16);
               }

               return var3;
            }
         }
      }
   }

   public static String findAndSubst(String var0, Properties var1) {
      String var2 = var1.getProperty(var0);
      if (var2 == null) {
         return null;
      } else {
         try {
            return substVars(var2, var1);
         } catch (IllegalArgumentException var4) {
            LogLog.error("Bad option value [" + var2 + "].", var4);
            return var2;
         }
      }
   }

   public static void selectAndConfigure(URL var0, String var1, LoggerRepository var2) {
      Object var3 = null;
      String var4 = var0.getFile();
      if (var1 == null && var4 != null && var4.endsWith(".xml")) {
         var1 = "org.apache.log4j.xml.DOMConfigurator";
      }

      if (var1 != null) {
         LogLog.debug("Preferred configurator class: " + var1);
         var3 = (Configurator)instantiateByClassName(
            var1,
            class$org$apache$log4j$spi$Configurator == null
               ? (class$org$apache$log4j$spi$Configurator = class$("org.apache.log4j.spi.Configurator"))
               : class$org$apache$log4j$spi$Configurator,
            null
         );
         if (var3 == null) {
            LogLog.error("Could not instantiate configurator [" + var1 + "].");
            return;
         }
      } else {
         var3 = new PropertyConfigurator();
      }

      ((Configurator)var3).doConfigure(var0, var2);
   }

   public static Object instantiateByClassName(String var0, Class var1, Object var2) {
      if (var0 != null) {
         try {
            Class var3 = Loader.loadClass(var0);
            if (!var1.isAssignableFrom(var3)) {
               LogLog.error("A \"" + var0 + "\" object is not assignable to a \"" + var1.getName() + "\" variable.");
               LogLog.error("The class \"" + var1.getName() + "\" was loaded by ");
               LogLog.error("[" + var1.getClassLoader() + "] whereas object of type ");
               LogLog.error("\"" + var3.getName() + "\" was loaded by [" + var3.getClassLoader() + "].");
               return var2;
            }

            return var3.newInstance();
         } catch (ClassNotFoundException var4) {
            LogLog.error("Could not instantiate class [" + var0 + "].", var4);
         } catch (IllegalAccessException var5) {
            LogLog.error("Could not instantiate class [" + var0 + "].", var5);
         } catch (InstantiationException var6) {
            LogLog.error("Could not instantiate class [" + var0 + "].", var6);
         } catch (RuntimeException var7) {
            LogLog.error("Could not instantiate class [" + var0 + "].", var7);
         }
      }

      return var2;
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public static long toFileSize(String var0, long var1) {
      if (var0 == null) {
         return var1;
      } else {
         String var3 = var0.trim().toUpperCase();
         long var4 = 807404677L & -5296158167262295453L;
         int var6;
         if ((var6 = var3.indexOf("KB")) != -1) {
            var4 = 1611597312L & -2488712569757348799L;
            var3 = var3.substring(0, var6);
         } else if ((var6 = var3.indexOf("MB")) != -1) {
            var4 = 3667696381592649894L & -3667696382349146560L;
            var3 = var3.substring(0, var6);
         } else if ((var6 = var3.indexOf("GB")) != -1) {
            var4 = 1239467916L & 1880166464L;
            var3 = var3.substring(0, var6);
         }

         if (var3 != null) {
            try {
               return Long.valueOf(var3) * var4;
            } catch (NumberFormatException var8) {
               LogLog.error("[" + var3 + "] is not in proper int form.");
               LogLog.error("[" + var0 + "] not in expected format.", var8);
            }
         }

         return var1;
      }
   }

   public static Object instantiateByKey(Properties var0, String var1, Class var2, Object var3) {
      String var4 = findAndSubst(var1, var0);
      if (var4 == null) {
         LogLog.error("Could not find value for key " + var1);
         return var3;
      } else {
         return instantiateByClassName(var4.trim(), var2, var3);
      }
   }

   public static String[] concatanateArrays(String[] var0, String[] var1) {
      int var2 = var0.length + var1.length;
      String[] var3 = new String[var2];
      System.arraycopy(var0, 0, var3, 0, var0.length);
      System.arraycopy(var1, 0, var3, var0.length, var1.length);
      return var3;
   }

   public static String getSystemProperty(String var0, String var1) {
      try {
         return System.getProperty(var0, var1);
      } catch (Throwable var3) {
         LogLog.debug("Was not allowed to read system property \"" + var0 + "\".");
         return var1;
      }
   }

   public static int toInt(String var0, int var1) {
      if (var0 != null) {
         String var2 = var0.trim();

         try {
            return Integer.valueOf(var2);
         } catch (NumberFormatException var4) {
            LogLog.error("[" + var2 + "] is not in proper int form.");
            var4.printStackTrace();
         }
      }

      return var1;
   }

   public static String substVars(String var0, Properties var1) {
      StringBuffer var2 = new StringBuffer();
      int var3 = 0;

      while (true) {
         int var4 = var0.indexOf(DELIM_START, var3);
         if (var4 == -1) {
            if (var3 == 0) {
               return var0;
            } else {
               var2.append(var0.substring(var3, var0.length()));
               return var2.toString();
            }
         }

         var2.append(var0.substring(var3, var4));
         int var5 = var0.indexOf(DELIM_STOP, var4);
         if (var5 == -1) {
            throw new IllegalArgumentException('"' + var0 + "\" has no closing brace. Opening brace at position " + var4 + '.');
         }

         var4 += DELIM_START_LEN;
         String var6 = var0.substring(var4, var5);
         String var7 = getSystemProperty(var6, null);
         if (var7 == null && var1 != null) {
            var7 = var1.getProperty(var6);
         }

         if (var7 != null) {
            String var8 = substVars(var7, var1);
            var2.append(var8);
         }

         var3 = var5 + DELIM_STOP_LEN;
      }
   }

   public static String convertSpecialChars(String var0) {
      int var2 = var0.length();
      StringBuffer var3 = new StringBuffer(var2);
      int var4 = 0;

      while (var4 < var2) {
         char var1 = var0.charAt(var4++);
         if (var1 == '\\') {
            var1 = var0.charAt(var4++);
            if (var1 == 'n') {
               var1 = '\n';
            } else if (var1 == 'r') {
               var1 = '\r';
            } else if (var1 == 't') {
               var1 = '\t';
            } else if (var1 == 'f') {
               var1 = '\f';
            } else if (var1 == '\b') {
               var1 = '\b';
            } else if (var1 == '"') {
               var1 = '"';
            } else if (var1 == '\'') {
               var1 = '\'';
            } else if (var1 == '\\') {
               var1 = '\\';
            }
         }

         var3.append(var1);
      }

      return var3.toString();
   }
}
