package junit.runner;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class ClassPathTestCollector implements TestCollector {
   public static int SUFFIX_LENGTH = ".class".length();

   public Hashtable collectFilesInRoots(Vector var1) {
      Hashtable var2 = new Hashtable(100);
      Enumeration var3 = var1.elements();

      while (var3.hasMoreElements()) {
         this.gatherFiles(new File((String)var3.nextElement()), "", var2);
      }

      return var2;
   }

   public Hashtable collectFilesInPath(String var1) {
      return this.collectFilesInRoots(this.splitClassPath(var1));
   }

   public Enumeration collectTests() {
      String var1 = System.getProperty("java.class.path");
      Hashtable var2 = this.collectFilesInPath(var1);
      return var2.elements();
   }

   public String classNameFromFile(String var1) {
      String var2 = var1.substring(0, var1.length() - SUFFIX_LENGTH);
      String var3 = var2.replace(File.separatorChar, '.');
      return var3.startsWith(".") ? var3.substring(1) : var3;
   }

   public void gatherFiles(File var1, String var2, Hashtable var3) {
      File var4 = new File(var1, var2);
      if (var4.isFile()) {
         if (this.isTestClass(var2)) {
            String var7 = this.classNameFromFile(var2);
            var3.put(var7, var7);
         }
      } else {
         String[] var5 = var4.list();
         if (var5 != null) {
            for (int var6 = 0; var6 < var5.length; var6++) {
               this.gatherFiles(var1, var2 + File.separatorChar + var5[var6], var3);
            }
         }
      }
   }

   public Vector splitClassPath(String var1) {
      Vector var2 = new Vector();
      String var3 = System.getProperty("path.separator");
      StringTokenizer var4 = new StringTokenizer(var1, var3);

      while (var4.hasMoreTokens()) {
         var2.addElement(var4.nextToken());
      }

      return var2;
   }

   public boolean isTestClass(String var1) {
      return var1.endsWith(".class") && var1.indexOf(36) < 0 && var1.indexOf("Test") > 0;
   }
}
