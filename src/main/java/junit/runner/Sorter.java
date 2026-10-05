package junit.runner;

import java.util.Vector;
import junit.runner.Sorter_Swapper;

public class Sorter {
   public static void method_22132(Vector var0, int var1, int var2, Sorter_Swapper var3) {
      int var4 = var1;
      int var5 = var2;
      String var6 = (String)var0.elementAt((var1 + var2) / 2);

      while (true) {
         while (((String)var0.elementAt(var1)).compareTo(var6) >= 0) {
            while (var6.compareTo((String)var0.elementAt(var2)) < 0) {
               var2--;
            }

            if (var1 <= var2) {
               var3.swap(var0, var1, var2);
               var1++;
               var2--;
            }

            if (var1 > var2) {
               if (var4 < var2) {
                  method_22132(var0, var4, var2, var3);
               }

               if (var1 < var5) {
                  method_22132(var0, var1, var5, var3);
               }

               return;
            }
         }

         var1++;
      }
   }
}
