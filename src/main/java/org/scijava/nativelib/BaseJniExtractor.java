package org.scijava.nativelib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.scijava.nativelib.BaseJniExtractor$1;

public abstract class BaseJniExtractor implements JniExtractor {
   public Class libraryJarClass;
   public static final String recoveredField1810 = "java.io.tmpdir";
   public static Logger recoveredField1811 = Logger.getLogger("org.scijava.nativelib.BaseJniExtractor");
   public String[] nativeResourcePaths;

   public abstract File method_12334();

   public void init(Class var1) {
      this.libraryJarClass = var1;
      String var2 = MxSysInfo.method_21015();
      if (var2 != null) {
         this.nativeResourcePaths = new String[]{"META-INF/lib/" + var2 + "/", "META-INF/lib/"};
      } else {
         this.nativeResourcePaths = new String[]{"META-INF/lib/"};
      }
   }

   public static void copy(InputStream var0, OutputStream var1) throws java.io.IOException {
      byte[] var2 = new byte[8192];
      int var3 = 0;

      while (true) {
         var3 = var0.read(var2);
         if (var3 <= 0) {
            return;
         }

         var1.write(var2, 0, var3);
      }
   }

   @Override
   public File extractJni(String var1, String var2) throws java.io.IOException {
      String var3 = System.mapLibraryName(var2);
      recoveredField1811.log(Level.FINE, "mappedLib is " + var3);
      java.net.URL var4 = null;
      if (null == this.libraryJarClass) {
         this.libraryJarClass = this.getClass();
      }

      var4 = this.libraryJarClass.getClassLoader().getResource(var1 + var3);
      if (null == var4 && var3.endsWith(".jnilib")) {
         var4 = this.getClass().getClassLoader().getResource(var1 + var3.substring(0, var3.length() - 7) + ".dylib");
         if (null != var4) {
            var3 = var3.substring(0, var3.length() - 7) + ".dylib";
         }
      }

      if (null != var4) {
         recoveredField1811.log(Level.FINE, "URL is " + var4.toString());
         recoveredField1811.log(Level.FINE, "URL path is " + var4.getPath());
         return this.extractResource(this.method_12334(), (URL)var4, var3);
      } else {
         recoveredField1811.log(Level.INFO, "Couldn't find resource " + var1 + " " + var3);
         throw new IOException("Couldn't find resource " + var1 + " " + var3);
      }
   }

   public BaseJniExtractor() throws java.io.IOException {
      this.init(null);
   }

   @Override
   public void extractRegistered() throws java.io.IOException, java.io.UnsupportedEncodingException {
      recoveredField1811.log(Level.FINE, "Extracting libraries registered in classloader " + this.getClass().getClassLoader());

      for (int var1 = 0; var1 < this.nativeResourcePaths.length; var1++) {
         Enumeration var2 = this.getClass().getClassLoader().getResources(this.nativeResourcePaths[var1] + "AUTOEXTRACT.LIST");

         while (var2.hasMoreElements()) {
            URL var3 = (URL)var2.nextElement();
            recoveredField1811.log(Level.FINE, "Extracting libraries listed in " + var3);
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.openStream(), "UTF-8"));

            String var5;
            while ((var5 = var4.readLine()) != null) {
               URL var6 = null;

               for (int var7 = 0; var7 < this.nativeResourcePaths.length; var7++) {
                  var6 = this.getClass().getClassLoader().getResource(this.nativeResourcePaths[var7] + var5);
                  if (var6 != null) {
                     break;
                  }
               }

               if (var6 == null) {
                  throw new IOException("Couldn't find native library " + var5 + "on the classpath");
               }

               this.extractResource(this.method_12332(), var6, var5);
            }
         }
      }
   }

   public void method_22309(String var1, String var2) {
      File var3 = new File(System.getProperty("java.io.tmpdir"));
      File[] var4 = var3.listFiles(new BaseJniExtractor$1(this, var1, var2));
      if (var4 != null) {
         for (File var8 : var4) {
            try {
               var8.delete();
            } catch (SecurityException var10) {
            }
         }
      }
   }

   public File extractResource(File var1, URL var2, String var3) throws java.io.FileNotFoundException, java.io.IOException {
      InputStream var4 = var2.openStream();
      String var5 = var3;
      String var6 = null;
      int var7 = var3.lastIndexOf(46);
      if (-1 != var7) {
         var5 = var3.substring(0, var7);
         var6 = var3.substring(var7);
      }

      this.method_22309(var5, var6);
      File var8 = File.createTempFile(var5, var6);
      recoveredField1811.log(Level.FINE, "Extracting '" + var2 + "' to '" + var8.getAbsolutePath() + "'");
      FileOutputStream var9 = new FileOutputStream(var8);
      copy(var4, var9);
      var9.close();
      var4.close();
      var8.deleteOnExit();
      return var8;
   }

   public BaseJniExtractor(Class var1) throws java.io.IOException {
      this.init(var1);
   }

   public abstract File method_12332();
}
