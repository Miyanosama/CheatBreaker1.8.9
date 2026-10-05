package net.optifine.expr;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import net.optifine.expr.ExpressionParser;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.IExpressionResolver;

public class TestExpressions {
   public static void method_12378(String[] var0) {
      ExpressionParser var1 = new ExpressionParser((IExpressionResolver)null);

      while (true) {
         try {
            InputStreamReader var2 = new InputStreamReader(System.in);
            BufferedReader var3 = new BufferedReader(var2);
            String var4 = var3.readLine();
            if (var4.length() <= 0) {
               return;
            }

            IExpression var5 = var1.parse(var4);
            if (var5 instanceof IExpressionFloat) {
               IExpressionFloat var6 = (IExpressionFloat)var5;
               float var7 = var6.eval();
               System.out.println("" + var7);
            }

            if (var5 instanceof IExpressionBool) {
               IExpressionBool var9 = (IExpressionBool)var5;
               boolean var10 = var9.eval();
               System.out.println("" + var10);
            }
         } catch (Exception var8) {
            var8.printStackTrace();
         }
      }
   }
}
