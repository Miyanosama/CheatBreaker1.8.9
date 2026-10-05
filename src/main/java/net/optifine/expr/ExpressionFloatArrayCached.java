package net.optifine.expr;

public class ExpressionFloatArrayCached implements IExpressionFloatArray, IExpressionCached {
   public boolean cached;
   public float[] value;
   public IExpressionFloatArray expression;

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   @Override
   public String toString() {
      return "cached(" + this.expression + ")";
   }

   @Override
   public float[] eval() {
      if (!this.cached) {
         this.value = this.expression.eval();
         this.cached = true;
      }

      return this.value;
   }

   @Override
   public void reset() {
      this.cached = false;
   }

   public ExpressionFloatArrayCached(IExpressionFloatArray var1) {
      this.expression = var1;
   }
}
