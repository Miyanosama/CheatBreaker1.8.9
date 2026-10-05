package net.minecraft.client.renderer.culling;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;

public class ClippingHelperImpl extends ClippingHelper {
   public FloatBuffer modelviewMatrixBuffer;
   public FloatBuffer field_78564_h;
   public EntityDamageSource field_0004;
   public static ClippingHelperImpl instance = new ClippingHelperImpl();
   public FloatBuffer projectionMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);

   public static ClippingHelper getInstance() {
      instance.init();
      return instance;
   }

   public ClippingHelperImpl() {
      this.modelviewMatrixBuffer = GLAllocation.createDirectFloatBuffer(16);
      this.field_78564_h = GLAllocation.createDirectFloatBuffer(16);
   }

   public void init() {
      ((Buffer)this.projectionMatrixBuffer).clear();
      ((Buffer)this.modelviewMatrixBuffer).clear();
      ((Buffer)this.field_78564_h).clear();
      GlStateManager.getFloat(2983, this.projectionMatrixBuffer);
      GlStateManager.getFloat(2982, this.modelviewMatrixBuffer);
      float[] var1 = this.b;
      float[] var2 = this.c;
      ((Buffer)this.projectionMatrixBuffer).flip().limit(16);
      this.projectionMatrixBuffer.get(var1);
      ((Buffer)this.modelviewMatrixBuffer).flip().limit(16);
      this.modelviewMatrixBuffer.get(var2);
      this.d[0] = var2[0] * var1[0] + var2[1] * var1[4] + var2[2] * var1[8] + var2[3] * var1[12];
      this.d[1] = var2[0] * var1[1] + var2[1] * var1[5] + var2[2] * var1[9] + var2[3] * var1[13];
      this.d[2] = var2[0] * var1[2] + var2[1] * var1[6] + var2[2] * var1[10] + var2[3] * var1[14];
      this.d[3] = var2[0] * var1[3] + var2[1] * var1[7] + var2[2] * var1[11] + var2[3] * var1[15];
      this.d[4] = var2[4] * var1[0] + var2[5] * var1[4] + var2[6] * var1[8] + var2[7] * var1[12];
      this.d[5] = var2[4] * var1[1] + var2[5] * var1[5] + var2[6] * var1[9] + var2[7] * var1[13];
      this.d[6] = var2[4] * var1[2] + var2[5] * var1[6] + var2[6] * var1[10] + var2[7] * var1[14];
      this.d[7] = var2[4] * var1[3] + var2[5] * var1[7] + var2[6] * var1[11] + var2[7] * var1[15];
      this.d[8] = var2[8] * var1[0] + var2[9] * var1[4] + var2[10] * var1[8] + var2[11] * var1[12];
      this.d[9] = var2[8] * var1[1] + var2[9] * var1[5] + var2[10] * var1[9] + var2[11] * var1[13];
      this.d[10] = var2[8] * var1[2] + var2[9] * var1[6] + var2[10] * var1[10] + var2[11] * var1[14];
      this.d[11] = var2[8] * var1[3] + var2[9] * var1[7] + var2[10] * var1[11] + var2[11] * var1[15];
      this.d[12] = var2[12] * var1[0] + var2[13] * var1[4] + var2[14] * var1[8] + var2[15] * var1[12];
      this.d[13] = var2[12] * var1[1] + var2[13] * var1[5] + var2[14] * var1[9] + var2[15] * var1[13];
      this.d[14] = var2[12] * var1[2] + var2[13] * var1[6] + var2[14] * var1[10] + var2[15] * var1[14];
      this.d[15] = var2[12] * var1[3] + var2[13] * var1[7] + var2[14] * var1[11] + var2[15] * var1[15];
      float[] var3 = this.a[0];
      var3[0] = this.d[3] - this.d[0];
      var3[1] = this.d[7] - this.d[4];
      var3[2] = this.d[11] - this.d[8];
      var3[3] = this.d[15] - this.d[12];
      this.normalize(var3);
      float[] var4 = this.a[1];
      var4[0] = this.d[3] + this.d[0];
      var4[1] = this.d[7] + this.d[4];
      var4[2] = this.d[11] + this.d[8];
      var4[3] = this.d[15] + this.d[12];
      this.normalize(var4);
      float[] var5 = this.a[2];
      var5[0] = this.d[3] + this.d[1];
      var5[1] = this.d[7] + this.d[5];
      var5[2] = this.d[11] + this.d[9];
      var5[3] = this.d[15] + this.d[13];
      this.normalize(var5);
      float[] var6 = this.a[3];
      var6[0] = this.d[3] - this.d[1];
      var6[1] = this.d[7] - this.d[5];
      var6[2] = this.d[11] - this.d[9];
      var6[3] = this.d[15] - this.d[13];
      this.normalize(var6);
      float[] var7 = this.a[4];
      var7[0] = this.d[3] - this.d[2];
      var7[1] = this.d[7] - this.d[6];
      var7[2] = this.d[11] - this.d[10];
      var7[3] = this.d[15] - this.d[14];
      this.normalize(var7);
      float[] var8 = this.a[5];
      var8[0] = this.d[3] + this.d[2];
      var8[1] = this.d[7] + this.d[6];
      var8[2] = this.d[11] + this.d[10];
      var8[3] = this.d[15] + this.d[14];
      this.normalize(var8);
   }

   public void normalize(float[] var1) {
      float var2 = MathHelper.sqrt_float(var1[0] * var1[0] + var1[1] * var1[1] + var1[2] * var1[2]);
      var1[0] /= var2;
      var1[1] /= var2;
      var1[2] /= var2;
      var1[3] /= var2;
   }
}
