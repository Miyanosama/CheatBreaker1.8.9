package junit.runner;

import io.netty.util.concurrent.DefaultPromise$3;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.client.model.ModelCreeper;

public class TestCaseClassLoader extends ClassLoader {
   public Vector field_0003;
   public String[] defaultExclusions = new String[]{"junit.framework.", "junit.extensions.", "junit.runner."};
   public Vector field_0002;
   public DefaultPromise$3 field_0004;
   public ModelCreeper field_0000;
   public static String field_0001;

   public synchronized Class loadClass(String var1, boolean var2) {
      Class var3 = this.findLoadedClass(var1);
      if (var3 != null) {
         return var3;
      } else {
         if (this.isExcluded(var1)) {
            try {
               return this.findSystemClass(var1);
            } catch (ClassNotFoundException var5) {
            }
         }

         if (var3 == null) {
            byte[] var4 = this.lookupClassData(var1);
            if (var4 == null) {
               throw new ClassNotFoundException();
            }

            var3 = this.defineClass(var1, var4, 0, var4.length);
         }

         if (var2) {
            this.resolveClass(var3);
         }

         return var3;
      }
   }

   public boolean method_29276(String var1) {
      return var1.endsWith(".jar") || var1.endsWith(".zip");
   }

   public TestCaseClassLoader(String var1) {
      this.scanPath(var1);
      this.readExcludedPackages();
   }

   public TestCaseClassLoader() {
      this(System.getProperty("java.class.path"));
   }

   public byte[] lookupClassData(String var1) {
      B var2 = null;

      for (int var3 = 0; var3 < this.field_0002.size(); var3++) {
         String var4 = (String)this.field_0002.elementAt(var3);
         String var5 = var1.replace('.', '/') + ".class";
         if (this.method_29276(var4)) {
            var2 = this.loadJarData(var4, var5);
         } else {
            var2 = this.loadFileData(var4, var5);
         }

         if (var2 != null) {
            return (byte[])var2;
         }
      }

      throw new ClassNotFoundException(var1);
   }

   public byte[] getClassData(File var1) {
      FileInputStream var2 = null;

      try {
         var2 = new FileInputStream(var1);
         ByteArrayOutputStream var3 = new ByteArrayOutputStream(1000);
         byte[] var4 = new byte[1000];

         int var5;
         while ((var5 = var2.read(var4)) != -1) {
            var3.write(var4, 0, var5);
         }

         var2.close();
         var3.close();
         return var3.toByteArray();
      } catch (IOException var16) {
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var15) {
            }
         }
      }

      return null;
   }

   public byte[] loadJarData(String var1, String var2) {
      ZipFile var3 = null;
      InputStream var4 = null;
      File var5 = new File(var1);
      if (!var5.exists()) {
         return null;
      } else {
         try {
            var3 = new ZipFile(var5);
         } catch (IOException var21) {
            return null;
         }

         ZipEntry var6 = var3.getEntry(var2);
         if (var6 == null) {
            return null;
         } else {
            int var7 = (int)var6.getSize();

            try {
               var4 = var3.getInputStream(var6);
               byte[] var8 = new byte[var7];
               int var9 = 0;

               while (var9 < var7) {
                  int var10 = var4.read(var8, var9, var8.length - var9);
                  var9 += var10;
               }

               var3.close();
               return var8;
            } catch (IOException var22) {
            } finally {
               try {
                  if (var4 != null) {
                     var4.close();
                  }
               } catch (IOException var20) {
               }
            }

            return null;
         }
      }
   }

   public void scanPath(String var1) {
      String var2 = System.getProperty("path.separator");
      this.field_0002 = new Vector(10);
      StringTokenizer var3 = new StringTokenizer(var1, var2);

      while (var3.hasMoreTokens()) {
         this.field_0002.addElement(var3.nextToken());
      }
   }

   public boolean isExcluded(String var1) {
      for (int var2 = 0; var2 < this.field_0003.size(); var2++) {
         if (var1.startsWith((String)this.field_0003.elementAt(var2))) {
            return true;
         }
      }

      return false;
   }

   public void readExcludedPackages() {
      this.field_0003 = new Vector(10);

      for (int var1 = 0; var1 < this.defaultExclusions.length; var1++) {
         this.field_0003.addElement(this.defaultExclusions[var1]);
      }

      InputStream var15 = this.getClass().getResourceAsStream("excluded.properties");
      if (var15 != null) {
         Properties var2 = new Properties();

         label91: {
            try {
               var2.load(var15);
               break label91;
            } catch (IOException var13) {
            } finally {
               try {
                  var15.close();
               } catch (IOException var12) {
               }
            }

            return;
         }

         Enumeration var3 = var2.propertyNames();

         while (var3.hasMoreElements()) {
            String var4 = (String)var3.nextElement();
            if (var4.startsWith("excluded.")) {
               String var5 = var2.getProperty(var4);
               var5 = var5.trim();
               if (var5.endsWith("*")) {
                  var5 = var5.substring(0, var5.length() - 1);
               }

               if (var5.length() > 0) {
                  this.field_0003.addElement(var5);
               }
            }
         }
      }
   }

   public URL getResource(String var1) {
      return ClassLoader.getSystemResource(var1);
   }

   public byte[] loadFileData(String var1, String var2) {
      File var3 = new File(var1, var2);
      return var3.exists() ? this.getClassData(var3) : null;
   }

   public InputStream getResourceAsStream(String var1) {
      return ClassLoader.getSystemResourceAsStream(var1);
   }
}
