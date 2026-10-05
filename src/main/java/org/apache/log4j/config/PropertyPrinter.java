package org.apache.log4j.config;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class PropertyPrinter implements PropertyGetter.PropertyCallback {
   public Hashtable layoutNames;
   public PrintWriter out;
   public Hashtable appenderNames;
   public boolean doCapitalize;
   public int numAppenders = 0;

   public void printOptions(PrintWriter var1, Logger var2) {
      this.printOptions(var1, (Category)var2);
   }

   public void printOptions(PrintWriter var1, Object var2, String var3) {
      var1.println(var3 + "=" + var2.getClass().getName());
      PropertyGetter.getProperties(var2, this, var3 + ".");
   }

   public static String capitalize(String var0) {
      if (!Character.isLowerCase(var0.charAt(0)) || var0.length() != 1 && !Character.isLowerCase(var0.charAt(1))) {
         return var0;
      } else {
         StringBuffer var1 = new StringBuffer(var0);
         var1.setCharAt(0, Character.toUpperCase(var0.charAt(0)));
         return var1.toString();
      }
   }

   public static void main(String[] var0) {
      new PropertyPrinter(new PrintWriter(System.out));
   }

   public PropertyPrinter(PrintWriter var1, boolean var2) {
      this.appenderNames = new Hashtable();
      this.layoutNames = new Hashtable();
      this.out = var1;
      this.doCapitalize = var2;
      this.print(var1);
      var1.flush();
   }

   public void foundProperty(Object var1, String var2, String var3, Object var4) {
      if (!(var1 instanceof Appender) || !"name".equals(var3)) {
         if (this.doCapitalize) {
            var3 = capitalize(var3);
         }

         this.out.println(var2 + var3 + "=" + var4.toString());
      }
   }

   public PropertyPrinter(PrintWriter var1) {
      this(var1, false);
   }

   public void printOptions(PrintWriter var1, Category var2) {
      Enumeration var3 = var2.getAllAppenders();
      Level var4 = var2.getLevel();
      String var5 = var4 == null ? "" : var4.toString();

      while (var3.hasMoreElements()) {
         Appender var6 = (Appender)var3.nextElement();
         String var7;
         if ((var7 = (String)this.appenderNames.get(var6)) == null) {
            if ((var7 = var6.getName()) == null || this.isGenAppName(var7)) {
               var7 = this.genAppName();
            }

            this.appenderNames.put(var6, var7);
            this.printOptions(var1, var6, "log4j.appender." + var7);
            if (var6.getLayout() != null) {
               this.printOptions(var1, var6.getLayout(), "log4j.appender." + var7 + ".layout");
            }
         }

         var5 = var5 + ", " + var7;
      }

      String var8 = var2 == Logger.getRootLogger() ? "log4j.rootLogger" : "log4j.logger." + var2.getName();
      if (var5 != "") {
         var1.println(var8 + "=" + var5);
      }

      if (!var2.getAdditivity() && var2 != Logger.getRootLogger()) {
         var1.println("log4j.additivity." + var2.getName() + "=false");
      }
   }

   public String genAppName() {
      return "A" + this.numAppenders++;
   }

   public boolean isGenAppName(String var1) {
      if (var1.length() >= 2 && var1.charAt(0) == 'A') {
         for (int var2 = 0; var2 < var1.length(); var2++) {
            if (var1.charAt(var2) < '0' || var1.charAt(var2) > '9') {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public void print(PrintWriter var1) {
      this.printOptions(var1, Logger.getRootLogger());
      Enumeration var2 = LogManager.getCurrentLoggers();

      while (var2.hasMoreElements()) {
         this.printOptions(var1, (Logger)var2.nextElement());
      }
   }
}
