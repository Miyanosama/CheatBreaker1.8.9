package net.optifine.shaders.uniform;

import net.minecraft.client.renderer.tileentity.TileEntityBeaconRenderer;
import org.lwjgl.opengl.ARBShaderObjects;

public class ShaderUniform2f extends ShaderUniformBase {
   public TileEntityBeaconRenderer field_0001;
   public static float field_0002;
   public float[][] programValues;

   @Override
   public void resetValue() {
      this.programValues = new float[][]{{-Float.MAX_VALUE, -Float.MAX_VALUE}};
   }

   public ShaderUniform2f(String var1) {
      super(var1);
      this.resetValue();
   }

   public void setValue(float var1, float var2) {
      int var3 = this.getProgram();
      float[] var4 = this.programValues[var3];
      if (var4[0] != var1 || var4[1] != var2) {
         var4[0] = var1;
         var4[1] = var2;
         int var5 = this.getLocation();
         if (var5 >= 0) {
            ARBShaderObjects.glUniform2fARB(var5, var1, var2);
            this.checkGLError();
         }
      }
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
         this.programValues[var1] = new float[]{-Float.MAX_VALUE, -Float.MAX_VALUE};
      }
   }

   public float[] getValue() {
      int var1 = this.getProgram();
      return this.programValues[var1];
   }
}
