package net.optifine.shaders.uniform;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform3f extends ShaderUniformBase {
   public float[][] programValues;
   public static final float recoveredField3196 = -3.4028235e+38F;

   public float[] getValue() {
      int var1 = this.getProgram();
      return this.programValues[var1];
   }

   @Override
   public void resetValue() {
      this.programValues = new float[][]{{-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE}};
   }

   public ShaderUniform3f(String var1) {
      super(var1);
      this.resetValue();
   }

   @Override
   public void onProgramSet(int var1) {
      if (var1 >= this.programValues.length) {
         float[][] var2 = this.programValues;
         float[][] var3 = new float[var1 + 10][];
         System.arraycopy(var2, 0, var3, 0, var2.length);
         this.programValues = var3;
      }

      if (this.programValues[var1] == null) {
         this.programValues[var1] = new float[]{-Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE};
      }
   }

   public void setValue(float var1, float var2, float var3) {
      int var4 = this.getProgram();
      float[] var5 = this.programValues[var4];
      if (var5[0] != var1 || var5[1] != var2 || var5[2] != var3) {
         var5[0] = var1;
         var5[1] = var2;
         var5[2] = var3;
         int var6 = this.getLocation();
         if (var6 >= 0) {
            ARBShaderObjects.glUniform3fARB(var6, var1, var2, var3);
            this.checkGLError();
         }
      }
   }
}
