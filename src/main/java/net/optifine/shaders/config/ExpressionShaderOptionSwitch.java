package net.optifine.shaders.config;

import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionBool;

public class ExpressionShaderOptionSwitch implements IExpressionBool {
   public ShaderOptionSwitch shaderOption;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }

   @Override
   public boolean eval() {
      return ShaderOptionSwitch.isTrue(this.shaderOption.getValue());
   }

   @Override
   public String toString() {
      return "" + this.shaderOption;
   }

   public ExpressionShaderOptionSwitch(ShaderOptionSwitch var1) {
      this.shaderOption = var1;
   }
}
