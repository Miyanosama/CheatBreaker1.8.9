package net.optifine.util;

import net.minecraft.util.MathHelper;

public class MathUtilsTest {
   public static void test(MathUtilsTest.OPER var0, boolean var1) {
      MathHelper.fastMath = var1;
      double var2;
      double var4;
      switch (var0) {
         case SIN:
         case COS:
            var2 = -MathHelper.PI;
            var4 = MathHelper.PI;
            break;
         case ASIN:
         case ACOS:
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
         switch (var0) {
            case SIN:
               var10 = (float)Math.sin(var8);
               var11 = MathHelper.sin((float)var8);
               break;
            case COS:
               var10 = (float)Math.cos(var8);
               var11 = MathHelper.cos((float)var8);
               break;
            case ASIN:
               var10 = (float)Math.asin(var8);
               var11 = MathUtils.asin((float)var8);
               break;
            case ACOS:
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

   public static void main(String[] var0) throws java.lang.Exception {
      MathUtilsTest.OPER[] var1 = MathUtilsTest.OPER.values();

      for (int var2 = 0; var2 < var1.length; var2++) {
         MathUtilsTest.OPER var3 = var1[var2];
         dbg("******** " + var3 + " ***********");
         test(var3, false);
      }
   }

   public static enum OPER {
      SIN,
      COS,
      ASIN,
      ACOS;
   }
}
