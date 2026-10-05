package net.optifine.shaders.uniform;

import javazoom.jl.decoder.Header;
import net.minecraft.client.audio.SoundHandler$2;
import net.minecraft.scoreboard.ScoreDummyCriteria;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public class ShaderParameterIndexed implements IExpressionFloat {
   public int index1;
   public ScoreDummyCriteria field_0005;
   public ShaderParameterFloat type;
   public SoundHandler$2 field_0004;
   public int index2;
   public Header field_0001;

   public ShaderParameterIndexed(ShaderParameterFloat var1, int var2) {
      this(var1, var2, 0);
   }

   @Override
   public float eval() {
      return this.type.eval(this.index1, this.index2);
   }

   @Override
   public String toString() {
      return this.type.getIndexNames1() == null
         ? "" + this.type
         : (
            this.type.getIndexNames2() == null
               ? "" + this.type + "." + this.type.getIndexNames1()[this.index1]
               : "" + this.type + "." + this.type.getIndexNames1()[this.index1] + "." + this.type.getIndexNames2()[this.index2]
         );
   }

   public ShaderParameterIndexed(ShaderParameterFloat var1) {
      this(var1, 0, 0);
   }

   @Override
   public ExpressionType getExpressionType() {
      return ExpressionType.FLOAT;
   }

   public ShaderParameterIndexed(ShaderParameterFloat var1, int var2, int var3) {
      this.type = var1;
      this.index1 = var2;
      this.index2 = var3;
   }
}
