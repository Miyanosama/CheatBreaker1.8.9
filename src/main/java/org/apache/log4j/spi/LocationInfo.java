package org.apache.log4j.spi;

import java.io.InterruptedIOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;

public class LocationInfo implements Serializable {
   public static Class class$java$lang$Throwable;
   public transient String lineNumber;
   public String fullInfo;
   public static Method getFileNameMethod;
   public transient String fileName;
   public static Method getMethodNameMethod;
   public static final long recoveredField3777 = -1325822038990805636L;
   public transient String methodName;
   public static Method getStackTraceMethod;
   public static Method getLineNumberMethod;
   public static final String recoveredField3778 = "?";
   public static StringWriter sw = new StringWriter();
   public transient String className;
   public static PrintWriter pw = new PrintWriter(sw);
   public static LocationInfo NA_LOCATION_INFO = new LocationInfo("?", "?", "?", "?");
   public static boolean inVisualAge = false;
   public static Method getClassNameMethod;

   public static void appendFragment(StringBuffer var0, String var1) {
      if (var1 == null) {
         var0.append("?");
      } else {
         var0.append(var1);
      }
   }

   public LocationInfo(Throwable var1, String var2) {
      if (var1 != null && var2 != null) {
         if (getLineNumberMethod != null) {
            try {
               Object var16 = null;
               Object[] var21 = (Object[])getStackTraceMethod.invoke(var1, (Object[])var16);
               String var22 = "?";

               for (int var23 = var21.length - 1; var23 >= 0; var23--) {
                  String var7 = (String)getClassNameMethod.invoke(var21[var23], (Object[])var16);
                  if (var2.equals(var7)) {
                     int var8 = var23 + 1;
                     if (var8 < var21.length) {
                        this.className = var22;
                        this.methodName = (String)getMethodNameMethod.invoke(var21[var8], (Object[])var16);
                        this.fileName = (String)getFileNameMethod.invoke(var21[var8], (Object[])var16);
                        if (this.fileName == null) {
                           this.fileName = "?";
                        }

                        int var9 = (Integer)getLineNumberMethod.invoke(var21[var8], (Object[])var16);
                        if (var9 < 0) {
                           this.lineNumber = "?";
                        } else {
                           this.lineNumber = String.valueOf(var9);
                        }

                        StringBuffer var10 = new StringBuffer();
                        var10.append(this.className);
                        var10.append(".");
                        var10.append(this.methodName);
                        var10.append("(");
                        var10.append(this.fileName);
                        var10.append(":");
                        var10.append(this.lineNumber);
                        var10.append(")");
                        this.fullInfo = var10.toString();
                     }

                     return;
                  }

                  var22 = var7;
               }

               return;
            } catch (IllegalAccessException var13) {
               LogLog.debug("LocationInfo failed using JDK 1.4 methods", var13);
            } catch (InvocationTargetException var14) {
               if (var14.getTargetException() instanceof InterruptedException || var14.getTargetException() instanceof InterruptedIOException) {
                  Thread.currentThread().interrupt();
               }

               LogLog.debug("LocationInfo failed using JDK 1.4 methods", var14);
            } catch (RuntimeException var15) {
               LogLog.debug("LocationInfo failed using JDK 1.4 methods", var15);
            }
         }

         String var3;
         synchronized (sw) {
            var1.printStackTrace(pw);
            var3 = sw.toString();
            sw.getBuffer().setLength(0);
         }

         int var17 = var3.lastIndexOf(var2);
         if (var17 != -1) {
            if (var17 + var2.length() < var3.length() && var3.charAt(var17 + var2.length()) != '.') {
               int var6 = var3.lastIndexOf(var2 + ".");
               if (var6 != -1) {
                  var17 = var6;
               }
            }

            var17 = var3.indexOf(Layout.LINE_SEP, var17);
            if (var17 != -1) {
               var17 += Layout.LINE_SEP_LEN;
               int var5 = var3.indexOf(Layout.LINE_SEP, var17);
               if (var5 != -1) {
                  if (!inVisualAge) {
                     var17 = var3.lastIndexOf("at ", var5);
                     if (var17 == -1) {
                        return;
                     }

                     var17 += 3;
                  }

                  this.fullInfo = var3.substring(var17, var5);
               }
            }
         }
      }
   }

   public String getLineNumber() {
      if (this.fullInfo == null) {
         return "?";
      } else {
         if (this.lineNumber == null) {
            int var1 = this.fullInfo.lastIndexOf(41);
            int var2 = this.fullInfo.lastIndexOf(58, var1 - 1);
            if (var2 == -1) {
               this.lineNumber = "?";
            } else {
               this.lineNumber = this.fullInfo.substring(var2 + 1, var1);
            }
         }

         return this.lineNumber;
      }
   }

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public String getClassName() {
      if (this.fullInfo == null) {
         return "?";
      } else {
         if (this.className == null) {
            int var1 = this.fullInfo.lastIndexOf(40);
            if (var1 == -1) {
               this.className = "?";
            } else {
               var1 = this.fullInfo.lastIndexOf(46, var1);
               int var2 = 0;
               if (inVisualAge) {
                  var2 = this.fullInfo.lastIndexOf(32, var1) + 1;
               }

               if (var1 == -1) {
                  this.className = "?";
               } else {
                  this.className = this.fullInfo.substring(var2, var1);
               }
            }
         }

         return this.className;
      }
   }

   static {
      try {
         inVisualAge = Class.forName("com.ibm.uvm.tools.DebugSupport") != null;
         LogLog.debug("Detected IBM VisualAge environment.");
      } catch (Throwable var4) {
      }

      try {
         Object var0 = null;
         getStackTraceMethod = (class$java$lang$Throwable == null ? (class$java$lang$Throwable = class$("java.lang.Throwable")) : class$java$lang$Throwable)
            .getMethod("getStackTrace", (Class<?>[])var0);
         Class var1 = Class.forName("java.lang.StackTraceElement");
         getClassNameMethod = var1.getMethod("getClassName", (Class<?>[])var0);
         getMethodNameMethod = var1.getMethod("getMethodName", (Class<?>[])var0);
         getFileNameMethod = var1.getMethod("getFileName", (Class<?>[])var0);
         getLineNumberMethod = var1.getMethod("getLineNumber", (Class<?>[])var0);
      } catch (ClassNotFoundException var2) {
         LogLog.debug("LocationInfo will use pre-JDK 1.4 methods to determine location.");
      } catch (NoSuchMethodException var3) {
         LogLog.debug("LocationInfo will use pre-JDK 1.4 methods to determine location.");
      }
   }

   public LocationInfo(String var1, String var2, String var3, String var4) {
      this.fileName = var1;
      this.className = var2;
      this.methodName = var3;
      this.lineNumber = var4;
      StringBuffer var5 = new StringBuffer();
      appendFragment(var5, var2);
      var5.append(".");
      appendFragment(var5, var3);
      var5.append("(");
      appendFragment(var5, var1);
      var5.append(":");
      appendFragment(var5, var4);
      var5.append(")");
      this.fullInfo = var5.toString();
   }

   public String getMethodName() {
      if (this.fullInfo == null) {
         return "?";
      } else {
         if (this.methodName == null) {
            int var1 = this.fullInfo.lastIndexOf(40);
            int var2 = this.fullInfo.lastIndexOf(46, var1);
            if (var2 == -1) {
               this.methodName = "?";
            } else {
               this.methodName = this.fullInfo.substring(var2 + 1, var1);
            }
         }

         return this.methodName;
      }
   }

   public String getFileName() {
      if (this.fullInfo == null) {
         return "?";
      } else {
         if (this.fileName == null) {
            int var1 = this.fullInfo.lastIndexOf(58);
            if (var1 == -1) {
               this.fileName = "?";
            } else {
               int var2 = this.fullInfo.lastIndexOf(40, var1 - 1);
               this.fileName = this.fullInfo.substring(var2 + 1, var1);
            }
         }

         return this.fileName;
      }
   }
}
