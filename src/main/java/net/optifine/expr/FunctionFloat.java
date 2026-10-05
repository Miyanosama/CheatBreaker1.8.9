package net.optifine.expr;

import net.optifine.shaders.uniform.Smoother;

public class FunctionFloat implements IExpressionFloat {
   public IExpression[] arguments;
   public int smoothId = -1;
   public FunctionType type;

   @Override
   public float eval() {
      IExpression[] var1 = this.arguments;
      switch (this.type) {
         case SMOOTH:
            IExpression var2 = var1[0];
            if (!(var2 instanceof ConstantFloat)) {
               float var3 = evalFloat(var1, 0);
               float var4 = var1.length > 1 ? evalFloat(var1, 1) : 1.0F;
               float var5 = var1.length > 2 ? evalFloat(var1, 2) : var4;
               if (this.smoothId < 0) {
                  this.smoothId = Smoother.getNextId();
               }

               return Smoother.getSmoothValue(this.smoothId, var3, var4, var5);
            }
         default:
            return this.type.evalFloat(this.arguments);
      }
   }

   public static float evalFloat(IExpression[] var0, int var1) {
      IExpressionFloat var2 = (IExpressionFloat)var0[var1];
      return var2.eval();
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public FunctionFloat(FunctionType var1, IExpression[] var2) {
      this.type = var1;
      this.arguments = var2;
   }

   @Override
   public String toString() {
      return "" + this.type + "()";
   }
}
