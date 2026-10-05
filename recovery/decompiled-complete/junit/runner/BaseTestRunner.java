package junit.runner;

import io.netty.handler.codec.spdy.SpdyHeaderBlockDecoder;
import io.netty.util.concurrent.GlobalEventExecutor$1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.Properties;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import junit.framework.TestSuite;
import net.minecraft.client.renderer.entity.layers.LayerCape;

public abstract class BaseTestRunner implements TestListener {
   public static Properties fPreferences;
   public SpdyHeaderBlockDecoder field_0006;
   public LayerCape field_0003;
   public static boolean fgFilterStack = true;
   public boolean fLoading = true;
   public static String field_0001;
   public static int fgMaxMessageLength = 500;
   public GlobalEventExecutor$1 field_0002;

   public TestSuiteLoader getLoader() {
      return (TestSuiteLoader)(this.useReloadingTestSuiteLoader() ? new ReloadingTestSuiteLoader() : new StandardTestSuiteLoader());
   }

   public boolean useReloadingTestSuiteLoader() {
      return getPreference("loading").equals("true") && !inVAJava() && this.fLoading;
   }

   public static boolean method_29459() {
      return !getPreference("filterstack").equals("true") || !fgFilterStack;
   }

   public static void savePreferences() {
      FileOutputStream var0 = new FileOutputStream(getPreferencesFile());

      try {
         getPreferences().save(var0, "");
      } finally {
         var0.close();
      }
   }

   public static boolean method_29473() {
      return System.getProperty("mrj.version") != null;
   }

   public static String getPreference(String var0) {
      return getPreferences().getProperty(var0);
   }

   public static void setPreferences(Properties var0) {
      fPreferences = var0;
   }

   public synchronized void startTest(Test var1) {
      this.method_00096(var1.toString());
   }

   public abstract void runFailed(String var1);

   public synchronized void endTest(Test var1) {
      this.testEnded(var1.toString());
   }

   public String extractClassName(String var1) {
      return var1.startsWith("Default package for") ? var1.substring(var1.lastIndexOf(".") + 1) : var1;
   }

   public abstract void method_00096(String var1);

   public String processArguments(String[] var1) {
      String var2 = null;

      for (int var3 = 0; var3 < var1.length; var3++) {
         if (var1[var3].equals("-noloading")) {
            this.setLoading(false);
         } else if (var1[var3].equals("-nofilterstack")) {
            fgFilterStack = false;
         } else if (var1[var3].equals("-c")) {
            if (var1.length > var3 + 1) {
               var2 = this.extractClassName(var1[var3 + 1]);
            } else {
               System.out.println("Missing Test class name");
            }

            var3++;
         } else {
            var2 = var1[var3];
         }
      }

      return var2;
   }

   public synchronized void addError(Test var1, Throwable var2) {
      this.testFailed(1, var1, var2);
   }

   public static boolean inVAJava() {
      try {
         Class.forName("com.ibm.uvm.tools.DebugSupport");
         return true;
      } catch (Exception var1) {
         return false;
      }
   }

   public static Properties getPreferences() {
      if (fPreferences == null) {
         fPreferences = new Properties();
         fPreferences.put("loading", "true");
         fPreferences.put("filterstack", "true");
         readPreferences();
      }

      return fPreferences;
   }

   public void clearStatus() {
   }

   public Class loadSuiteClass(String var1) {
      return this.getLoader().load(var1);
   }

   public Test getTest(String var1) {
      if (var1.length() <= 0) {
         this.clearStatus();
         return null;
      } else {
         Class var2 = null;

         try {
            var2 = this.loadSuiteClass(var1);
         } catch (ClassNotFoundException var7) {
            String var4 = var7.getMessage();
            if (var4 == null) {
               var4 = var1;
            }

            this.runFailed("Class not found \"" + var4 + "\"");
            return null;
         } catch (Exception var8) {
            this.runFailed("Error: " + var8.toString());
            return null;
         }

         Method var3 = null;

         try {
            var3 = var2.getMethod("suite");
         } catch (Exception var6) {
            this.clearStatus();
            return new TestSuite(var2);
         }

         if (!Modifier.isStatic(var3.getModifiers())) {
            this.runFailed("Suite() method must be static");
            return null;
         } else {
            Test var13 = null;

            try {
               var13 = (Test)var3.invoke(null, new Class[0]);
               if (var13 == null) {
                  return var13;
               }
            } catch (InvocationTargetException var9) {
               this.runFailed("Failed to invoke suite():" + var9.getTargetException().toString());
               return null;
            } catch (IllegalAccessException var10) {
               this.runFailed("Failed to invoke suite():" + var10.toString());
               return null;
            }

            this.clearStatus();
            return var13;
         }
      }
   }

   public static int getPreference(String var0, int var1) {
      String var2 = getPreference(var0);
      int var3 = var1;
      if (var2 == null) {
         return var1;
      } else {
         try {
            var3 = Integer.parseInt(var2);
         } catch (NumberFormatException var5) {
         }

         return var3;
      }
   }

   public static String getFilteredTrace(Throwable var0) {
      StringWriter var1 = new StringWriter();
      PrintWriter var2 = new PrintWriter(var1);
      var0.printStackTrace(var2);
      StringBuffer var3 = var1.getBuffer();
      String var4 = var3.toString();
      return getFilteredTrace(var4);
   }

   public synchronized void addFailure(Test var1, AssertionFailedError var2) {
      this.testFailed(2, var1, var2);
   }

   public String elapsedTimeAsString(long var1) {
      return NumberFormat.getInstance().format(var1 / 1000.0);
   }

   public static String getFilteredTrace(String var0) {
      if (method_29459()) {
         return var0;
      } else {
         StringWriter var1 = new StringWriter();
         PrintWriter var2 = new PrintWriter(var1);
         StringReader var3 = new StringReader(var0);
         BufferedReader var4 = new BufferedReader(var3);

         String var5;
         try {
            while ((var5 = var4.readLine()) != null) {
               if (!filterLine(var5)) {
                  var2.println(var5);
               }
            }
         } catch (Exception var7) {
            return var0;
         }

         return var1.toString();
      }
   }

   static {
      fgMaxMessageLength = getPreference("maxmessage", fgMaxMessageLength);
   }

   public void setLoading(boolean var1) {
      this.fLoading = var1;
   }

   public static boolean filterLine(String var0) {
      String[] var1 = new String[]{
         "junit.framework.TestCase",
         "junit.framework.TestResult",
         "junit.framework.TestSuite",
         "junit.framework.Assert.",
         "junit.swingui.TestRunner",
         "junit.awtui.TestRunner",
         "junit.textui.TestRunner",
         "java.lang.reflect.Method.invoke("
      };

      for (int var2 = 0; var2 < var1.length; var2++) {
         if (var0.indexOf(var1[var2]) > 0) {
            return true;
         }
      }

      return false;
   }

   public static File getPreferencesFile() {
      String var0 = System.getProperty("user.home");
      return new File(var0, "junit.properties");
   }

   public static void method_29468(String var0, String var1) {
      getPreferences().put(var0, var1);
   }

   public static void readPreferences() {
      FileInputStream var0 = null;

      try {
         var0 = new FileInputStream(getPreferencesFile());
         setPreferences(new Properties(getPreferences()));
         getPreferences().load(var0);
      } catch (IOException var4) {
         try {
            if (var0 != null) {
               var0.close();
            }
         } catch (IOException var3) {
         }
      }
   }

   public abstract void testFailed(int var1, Test var2, Throwable var3);

   public abstract void testEnded(String var1);

   public static String method_29475(String var0) {
      if (fgMaxMessageLength != -1 && var0.length() > fgMaxMessageLength) {
         var0 = var0.substring(0, fgMaxMessageLength) + "...";
      }

      return var0;
   }
}
