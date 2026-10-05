package recovered.unidentified;

import java.util.Vector;
import junit.runner.Sorter$Swapper;
import net.minecraft.potion.PotionAttackDamage;
import net.optifine.CustomGuiProperties$EnumContainer;
import net.optifine.expr.TokenParser;

public class UnidentifiedClass3609 {
   public CustomGuiProperties$EnumContainer field_0001;
   public PotionAttackDamage field_0002;
   public TokenParser field_0000;

   public static void method_22132(Vector var0, int var1, int var2, Sorter$Swapper var3) {
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
