package net.optifine.expr;

public class FunctionFloatArray implements IExpressionFloatArray {
   public IExpression[] arguments;
   public FunctionType type;

   public FunctionFloatArray(FunctionType var1, IExpression[] var2) {
      this.type = var1;
      this.arguments = var2;
   }

   @Override
   public float[] eval() {
      return this.type.evalFloatArray(this.arguments);
   }

   @Override
   public String toString() {
      return "" + this.type + "()";
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT_ARRAY;
   }
}
