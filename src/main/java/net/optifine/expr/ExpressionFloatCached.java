package net.optifine.expr;

public class ExpressionFloatCached implements IExpressionCached, IExpressionFloat {
   public boolean cached;
   public float value;
   public IExpressionFloat expression;

   @Override
   public float eval() {
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

   @Override
   public String toString() {
      return "cached(" + this.expression + ")";
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public ExpressionFloatCached(IExpressionFloat var1) {
      this.expression = var1;
   }
}
