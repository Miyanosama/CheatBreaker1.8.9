package net.optifine.shaders.config;

import io.netty.channel.epoll.Native;
import io.netty.channel.udt.nio.NioUdtMessageConnectorChannel$1;
import java.util.Map;
import net.minecraft.client.particle.EntityAuraFX$HappyVillagerFactory;
import net.minecraft.src.Config;
import net.optifine.expr.ConstantFloat;
import net.optifine.expr.FunctionBool;
import net.optifine.expr.FunctionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionResolver;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerLogRecordFilter;

public class MacroExpressionResolver implements IExpressionResolver {
   public Map<String, String> mapMacroValues = null;
   public CategoryExplorerLogRecordFilter field_0004;
   public EntityAuraFX$HappyVillagerFactory field_0001;
   public NioUdtMessageConnectorChannel$1 field_0003;
   public Native field_0000;

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
