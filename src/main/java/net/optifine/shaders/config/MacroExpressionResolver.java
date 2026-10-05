package net.optifine.shaders.config;

import java.util.Map;
import net.minecraft.src.Config;
import net.optifine.expr.ConstantFloat;
import net.optifine.expr.FunctionBool;
import net.optifine.expr.FunctionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;

public class MacroExpressionResolver implements IExpressionResolver {
   public Map<String, String> mapMacroValues = null;

   @Override
   public IExpression getExpression(String var1) {
      String var2 = "defined_";
      if (var1.startsWith(var2)) {
         String var5 = var1.substring(var2.length());
         return this.mapMacroValues.containsKey(var5)
            ? new FunctionBool(FunctionType.TRUE, (IExpression[])null)
            : new FunctionBool(FunctionType.FALSE, (IExpression[])null);
      } else {
         while (this.mapMacroValues.containsKey(var1)) {
            String var3 = this.mapMacroValues.get(var1);
            if (var3 == null || var3.equals(var1)) {
               break;
            }

            var1 = var3;
         }

         int var4 = Config.parseInt(var1, Integer.MIN_VALUE);
         if (var4 == Integer.MIN_VALUE) {
            Config.warn("Unknown macro value: " + var1);
            return new ConstantFloat(0.0F);
         } else {
            return new ConstantFloat(var4);
         }
      }
   }

   public MacroExpressionResolver(Map<String, String> var1) {
      this.mapMacroValues = var1;
   }
}
