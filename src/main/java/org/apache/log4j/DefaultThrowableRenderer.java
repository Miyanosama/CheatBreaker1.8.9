package org.apache.log4j;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import org.apache.log4j.spi.ThrowableRenderer;

public class DefaultThrowableRenderer implements ThrowableRenderer {
   public static String[] render(Throwable var0) {
      StringWriter var1 = new StringWriter();
      PrintWriter var2 = new PrintWriter(var1);

      try {
         var0.printStackTrace(var2);
      } catch (RuntimeException var6) {
      }

      var2.flush();
      LineNumberReader var3 = new LineNumberReader(new StringReader(var1.toString()));
      ArrayList var4 = new ArrayList();

      try {
         for (String var5 = var3.readLine(); var5 != null; var5 = var3.readLine()) {
            var4.add(var5);
         }
      } catch (IOException var7) {
         if (var7 instanceof InterruptedIOException) {
            Thread.currentThread().interrupt();
         }

         var4.add(var7.toString());
      }

      String[] var8 = new String[var4.size()];
      var4.toArray(var8);
      return var8;
   }

   public String[] doRender(Throwable var1) {
      return render(var1);
   }
}
