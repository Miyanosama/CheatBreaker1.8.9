package net.optifine.expr;

public class ConstantFloat implements IExpressionFloat {
   public float value;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public ConstantFloat(float var1) {
      this.value = var1;
   }

   @Override
   public float eval() {
      return this.value;
   }

   @Override
   public String toString() {
      return "" + this.value;
   }
}
