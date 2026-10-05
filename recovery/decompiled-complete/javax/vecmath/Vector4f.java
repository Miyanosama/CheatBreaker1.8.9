package javax.vecmath;

import java.io.Serializable;

public class Vector4f extends Tuple4f implements Serializable {
   public static long field_0000;

   public Vector4f(Vector4f var1) {
      super(var1);
   }

   public void set(Tuple3f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = 0.0F;
   }

   public float dot(Vector4f var1) {
      return this.x * var1.x + this.y * var1.y + this.z * var1.z + this.w * var1.w;
   }

   public void normalize(Vector4f var1) {
      float var2 = (float)(1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z + var1.w * var1.w));
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
      this.w = var1.w * var2;
   }

   public Vector4f(float[] var1) {
      super(var1);
   }

   public float angle(Vector4f var1) {
      double var2 = this.dot(var1) / (this.length() * var1.length());
      if (var2 < -1.0) {
         var2 = -1.0;
      }

      if (var2 > 1.0) {
         var2 = 1.0;
      }

      return (float)Math.acos(var2);
   }

   public Vector4f(Vector4d var1) {
      super(var1);
   }

   public Vector4f(Tuple3f var1) {
      super(var1.x, var1.y, var1.z, 0.0F);
   }

   public float length() {
      return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
   }

   public Vector4f() {
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
   }

   public Vector4f(Tuple4f var1) {
      super(var1);
   }

   public Vector4f(Tuple4d var1) {
      super(var1);
   }

   public Vector4f(float var1, float var2, float var3, float var4) {
      super(var1, var2, var3, var4);
   }

   public void normalize() {
      float var1 = (float)(1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w));
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
      this.w *= var1;
   }
}
