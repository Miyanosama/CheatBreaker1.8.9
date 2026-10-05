package org.apache.log4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.CodeSource;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.spi.ThrowableRenderer;

public class EnhancedThrowableRenderer implements ThrowableRenderer {
   public static Class class$java$lang$Throwable;
   public Method getClassNameMethod;
   public Method getStackTraceMethod;

   public String formatElement(Object var1, Map var2) {
      StringBuffer var3 = new StringBuffer("\tat ");
      var3.append(var1);

      try {
         String var4 = this.getClassNameMethod.invoke(var1, (Object[])null).toString();
         Object var5 = var2.get(var4);
         if (var5 != null) {
            var3.append(var5);
         } else {
            Class var6 = this.findClass(var4);
            int var7 = var3.length();
            var3.append('[');

            try {
               CodeSource var8 = var6.getProtectionDomain().getCodeSource();
               if (var8 != null) {
                  URL var9 = var8.getLocation();
                  if (var9 != null) {
                     if ("file".equals(var9.getProtocol())) {
                        String var10 = var9.getPath();
                        if (var10 != null) {
                           int var11 = var10.lastIndexOf(47);
                           int var12 = var10.lastIndexOf(File.separatorChar);
                           if (var12 > var11) {
                              var11 = var12;
                           }

                           if (var11 > 0 && var11 != var10.length() - 1) {
                              var3.append(var10.substring(var11 + 1));
                           } else {
                              var3.append(var9);
                           }
                        }
                     } else {
                        var3.append(var9);
                     }
                  }
               }
            } catch (SecurityException var13) {
            }

            var3.append(':');
            Package var15 = var6.getPackage();
            if (var15 != null) {
               String var16 = var15.getImplementationVersion();
               if (var16 != null) {
                  var3.append(var16);
               }
            }

            var3.append(']');
            var2.put(var4, var3.substring(var7));
         }
      } catch (Exception var14) {
      }

      return var3.toString();
   }

   public Class findClass(String var1) {
      try {
         return Thread.currentThread().getContextClassLoader().loadClass(var1);
      } catch (ClassNotFoundException var5) {
         try {
            return Class.forName(var1);
         } catch (ClassNotFoundException var4) {
            return this.getClass().getClassLoader().loadClass(var1);
         }
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public EnhancedThrowableRenderer() {
      try {
         Object var1 = null;
         this.getStackTraceMethod = (class$java$lang$Throwable == null
               ? (class$java$lang$Throwable = class$("java.lang.Throwable"))
               : class$java$lang$Throwable)
            .getMethod("getStackTrace", (Class<?>[])var1);
         Class var2 = Class.forName("java.lang.StackTraceElement");
         this.getClassNameMethod = var2.getMethod("getClassName", (Class<?>[])var1);
      } catch (Exception var3) {
      }
   }

   public String[] doRender(Throwable var1) {
      if (this.getStackTraceMethod != null) {
         try {
            Object var2 = null;
            Object[] var3 = (Object[])this.getStackTraceMethod.invoke(var1, (Object[])var2);
            String[] var4 = new String[var3.length + 1];
            var4[0] = var1.toString();
            HashMap var5 = new HashMap();

            for (int var6 = 0; var6 < var3.length; var6++) {
               var4[var6 + 1] = this.formatElement(var3[var6], var5);
            }

            return var4;
         } catch (Exception var7) {
         }
      }

      return DefaultThrowableRenderer.render(var1);
   }
}
