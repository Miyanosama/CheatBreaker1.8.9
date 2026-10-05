package net.optifine.expr;

import net.minecraft.client.resources.data.IMetadataSerializer$Registration;
import net.minecraft.pathfinding.PathNavigate;
import org.apache.log4j.pattern.ThrowableInformationPatternConverter;

public class FunctionFloatArray implements IExpressionFloatArray {
   public PathNavigate field_0002;
   public ThrowableInformationPatternConverter field_0004;
   public IExpression[] arguments;
   public FunctionType type;
   public IMetadataSerializer$Registration field_0000;

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
