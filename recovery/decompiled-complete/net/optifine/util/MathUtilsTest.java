package net.optifine.util;

import io.netty.util.concurrent.ImmediateExecutor;
import io.netty.util.internal.MpscLinkedQueue;
import net.minecraft.util.MathHelper;
import recovered.unidentified.UnidentifiedClass4985;

public class MathUtilsTest {
   public UnidentifiedClass4985 field_0001;
   public ImmediateExecutor field_0002;
   public MpscLinkedQueue field_0000;

   public static void test(MathUtilsTest$OPER var0, boolean var1) {
      MathHelper.fastMath = var1;
      double var2;
      double var4;
      switch (MathUtilsTest$1.$SwitchMap$net$optifine$util$MathUtilsTest$OPER[var0.ordinal()]) {
         case 1:
         case 2:
            var2 = -MathHelper.PI;
            var4 = MathHelper.PI;
            break;
         case 3:
         case 4:
            var2 = -1.0;
            var4 = 1.0;
            break;
         default:
            return;
      }

      byte var6 = 10;

      for (int var7 = 0; var7 <= var6; var7++) {
         double var8 = var2 + var7 * (var4 - var2) / var6;
         float var10;
         float var11;
         switch (MathUtilsTest$1.$SwitchMap$net$optifine$util$MathUtilsTest$OPER[var0.ordinal()]) {
            case 1:
               var10 = (float)Math.sin(var8);
               var11 = MathHelper.sin((float)var8);
               break;
            case 2:
               var10 = (float)Math.cos(var8);
               var11 = MathHelper.cos((float)var8);
               break;
            case 3:
               var10 = (float)Math.asin(var8);
               var11 = MathUtils.asin((float)var8);
               break;
            case 4:
               var10 = (float)Math.acos(var8);
               var11 = MathUtils.acos((float)var8);
               break;
            default:
               return;
         }

         dbg(String.format("%.2f, Math: %f, Helper: %f, diff: %f", var8, var10, var11, Math.abs(var10 - var11)));
      }
   }

   public static void dbg(String var0) {
      System.out.println(var0);
   }

   public static void main(String[] var0) {
      MathUtilsTest$OPER[] var1 = MathUtilsTest$OPER.values();

      for (int var2 = 0; var2 < var1.length; var2++) {
         MathUtilsTest$OPER var3 = var1[var2];
         dbg("******** " + var3 + " ***********");
         test(var3, false);
      }
   }
}
