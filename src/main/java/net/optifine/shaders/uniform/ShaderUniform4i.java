package net.optifine.shaders.uniform;

import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform4i extends ShaderUniformBase {
   public int[][] programValues;
   public static final int recoveredField3849 = -2147483648;

   @Override
   public void resetValue() {
      this.programValues = new int[][]{{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE}};
   }

   @Override
   public void onProgramSet(int var1) {
      if (var1 >= this.programValues.length) {
         int[][] var2 = this.programValues;
         int[][] var3 = new int[var1 + 10][];
         System.arraycopy(var2, 0, var3, 0, var2.length);
         this.programValues = var3;
      }

      if (this.programValues[var1] == null) {
         this.programValues[var1] = new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
      }
   }

   public void setValue(int var1, int var2, int var3, int var4) {
      int var5 = this.getProgram();
      int[] var6 = this.programValues[var5];
      if (var6[0] != var1 || var6[1] != var2 || var6[2] != var3 || var6[3] != var4) {
         var6[0] = var1;
         var6[1] = var2;
         var6[2] = var3;
         var6[3] = var4;
         int var7 = this.getLocation();
         if (var7 >= 0) {
            ARBShaderObjects.glUniform4iARB(var7, var1, var2, var3, var4);
            this.checkGLError();
         }
      }
   }

   public int[] getValue() {
      int var1 = this.getProgram();
      return this.programValues[var1];
   }

   public ShaderUniform4i(String var1) {
      super(var1);
      this.resetValue();
   }
}
