package net.optifine.shaders.uniform;

import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.IExpressionFloatArray;

public enum UniformType {
   FLOAT,
   INT,
   VEC3,
   VEC4,
   BOOL,
   VEC2;
   // $VF: synthetic field
   public static UniformType[] $VALUES = new UniformType[]{UniformType.BOOL, INT, FLOAT, UniformType.VEC2, VEC3, VEC4};

   public boolean matchesExpressionType(ExpressionType var1) {
      switch (UniformType$1.$SwitchMap$net$optifine$shaders$uniform$UniformType[this.ordinal()]) {
         case 1:
            return var1 == ExpressionType.BOOL;
         case 2:
            return var1 == ExpressionType.FLOAT;
         case 3:
            return var1 == ExpressionType.FLOAT;
         case 4:
         case 5:
         case 6:
            return var1 == ExpressionType.FLOAT_ARRAY;
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   public void updateUniformFloat4(IExpressionFloatArray var1, ShaderUniform4f var2) {
      float[] var3 = var1.eval();
      if (var3.length != 4) {
         throw new RuntimeException("Value length is not 4, length: " + var3.length);
      } else {
         var2.setValue(var3[0], var3[1], var3[2], var3[3]);
      }
   }

   public void updateUniformFloat2(IExpressionFloatArray var1, ShaderUniform2f var2) {
      float[] var3 = var1.eval();
      if (var3.length != 2) {
         throw new RuntimeException("Value length is not 2, length: " + var3.length);
      } else {
         var2.setValue(var3[0], var3[1]);
      }
   }

   public ShaderUniformBase makeShaderUniform(String var1) {
      switch (UniformType$1.$SwitchMap$net$optifine$shaders$uniform$UniformType[this.ordinal()]) {
         case 1:
            return new ShaderUniform1i(var1);
         case 2:
            return new ShaderUniform1i(var1);
         case 3:
            return new ShaderUniform1f(var1);
         case 4:
            return new ShaderUniform2f(var1);
         case 5:
            return new ShaderUniform3f(var1);
         case 6:
            return new ShaderUniform4f(var1);
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   public void updateUniformInt(IExpressionFloat var1, ShaderUniform1i var2) {
      int var3 = (int)var1.eval();
      var2.setValue(var3);
   }

   public static UniformType parse(String var0) {
      UniformType[] var1 = values();

      for (int var2 = 0; var2 < var1.length; var2++) {
         UniformType var3 = var1[var2];
         if (var3.name().toLowerCase().equals(var0)) {
            return var3;
         }
      }

      return null;
   }

   public void updateUniform(IExpression var1, ShaderUniformBase var2) {
      switch (UniformType$1.$SwitchMap$net$optifine$shaders$uniform$UniformType[this.ordinal()]) {
         case 1:
            this.updateUniformBool((IExpressionBool)var1, (ShaderUniform1i)var2);
            return;
         case 2:
            this.updateUniformInt((IExpressionFloat)var1, (ShaderUniform1i)var2);
            return;
         case 3:
            this.updateUniformFloat((IExpressionFloat)var1, (ShaderUniform1f)var2);
            return;
         case 4:
            this.updateUniformFloat2((IExpressionFloatArray)var1, (ShaderUniform2f)var2);
            return;
         case 5:
            this.updateUniformFloat3((IExpressionFloatArray)var1, (ShaderUniform3f)var2);
            return;
         case 6:
            this.updateUniformFloat4((IExpressionFloatArray)var1, (ShaderUniform4f)var2);
            return;
         default:
            throw new RuntimeException("Unknown uniform type: " + this);
      }
   }

   public void updateUniformFloat(IExpressionFloat var1, ShaderUniform1f var2) {
      float var3 = var1.eval();
      var2.setValue(var3);
   }

   public void updateUniformFloat3(IExpressionFloatArray var1, ShaderUniform3f var2) {
      float[] var3 = var1.eval();
      if (var3.length != 3) {
         throw new RuntimeException("Value length is not 3, length: " + var3.length);
      } else {
         var2.setValue(var3[0], var3[1], var3[2]);
      }
   }

   public void updateUniformBool(IExpressionBool var1, ShaderUniform1i var2) {
      boolean var3 = var1.eval();
      int var4 = var3 ? 1 : 0;
      var2.setValue(var4);
   }
}
