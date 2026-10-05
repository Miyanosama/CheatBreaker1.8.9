package net.optifine.expr;

public class Parameters implements IParameters {
   public ExpressionType[] parameterTypes;

   @Override
   public ExpressionType[] getParameterTypes(IExpression[] var1) {
      return this.parameterTypes;
   }

   public Parameters(ExpressionType[] var1) {
      this.parameterTypes = var1;
   }
}
