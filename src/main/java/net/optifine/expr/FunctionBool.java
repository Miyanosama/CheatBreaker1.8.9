package net.optifine.expr;

public class FunctionBool implements IExpressionBool {
   public IExpression[] arguments;
   public FunctionType type;

   @Override
   public boolean eval() {
      return this.type.evalBool(this.arguments);
   }

   public FunctionBool(FunctionType var1, IExpression[] var2) {
      this.type = var1;
      this.arguments = var2;
   }

   @Override
   public String toString() {
      return "" + this.type + "()";
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.BOOL;
   }
}
