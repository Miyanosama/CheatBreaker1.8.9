package javax.vecmath;

import java.io.Serializable;
import javax.vecmath.VecMathUtil;

public class Matrix4f implements Serializable, Cloneable {
   public float m11;
   public static final long recoveredField474 = -8405036035410109353L;
   public float m32;
   public float m20;
   public float m13;
   public float m01;
   public float m03;
   public float m30;
   public float m00;
   public float m12;
   public float m33;
   public float m31;
   public float m23;
   public float m21;
   public static final double recoveredField475 = 1e-08;
   public float m02;
   public float m10;
   public float m22;

   public void setM21(float var1) {
      this.m21 = var1;
   }

   public void mul(Matrix4f var1) {
      float var2 = this.m00 * var1.m00 + this.m01 * var1.m10 + this.m02 * var1.m20 + this.m03 * var1.m30;
      float var3 = this.m00 * var1.m01 + this.m01 * var1.m11 + this.m02 * var1.m21 + this.m03 * var1.m31;
      float var4 = this.m00 * var1.m02 + this.m01 * var1.m12 + this.m02 * var1.m22 + this.m03 * var1.m32;
      float var5 = this.m00 * var1.m03 + this.m01 * var1.m13 + this.m02 * var1.m23 + this.m03 * var1.m33;
      float var6 = this.m10 * var1.m00 + this.m11 * var1.m10 + this.m12 * var1.m20 + this.m13 * var1.m30;
      float var7 = this.m10 * var1.m01 + this.m11 * var1.m11 + this.m12 * var1.m21 + this.m13 * var1.m31;
      float var8 = this.m10 * var1.m02 + this.m11 * var1.m12 + this.m12 * var1.m22 + this.m13 * var1.m32;
      float var9 = this.m10 * var1.m03 + this.m11 * var1.m13 + this.m12 * var1.m23 + this.m13 * var1.m33;
      float var10 = this.m20 * var1.m00 + this.m21 * var1.m10 + this.m22 * var1.m20 + this.m23 * var1.m30;
      float var11 = this.m20 * var1.m01 + this.m21 * var1.m11 + this.m22 * var1.m21 + this.m23 * var1.m31;
      float var12 = this.m20 * var1.m02 + this.m21 * var1.m12 + this.m22 * var1.m22 + this.m23 * var1.m32;
      float var13 = this.m20 * var1.m03 + this.m21 * var1.m13 + this.m22 * var1.m23 + this.m23 * var1.m33;
      float var14 = this.m30 * var1.m00 + this.m31 * var1.m10 + this.m32 * var1.m20 + this.m33 * var1.m30;
      float var15 = this.m30 * var1.m01 + this.m31 * var1.m11 + this.m32 * var1.m21 + this.m33 * var1.m31;
      float var16 = this.m30 * var1.m02 + this.m31 * var1.m12 + this.m32 * var1.m22 + this.m33 * var1.m32;
      float var17 = this.m30 * var1.m03 + this.m31 * var1.m13 + this.m32 * var1.m23 + this.m33 * var1.m33;
      this.m00 = var2;
      this.m01 = var3;
      this.m02 = var4;
      this.m03 = var5;
      this.m10 = var6;
      this.m11 = var7;
      this.m12 = var8;
      this.m13 = var9;
      this.m20 = var10;
      this.m21 = var11;
      this.m22 = var12;
      this.m23 = var13;
      this.m30 = var14;
      this.m31 = var15;
      this.m32 = var16;
      this.m33 = var17;
   }

   @Override
   public String toString() {
      return this.m00
         + ", "
         + this.m01
         + ", "
         + this.m02
         + ", "
         + this.m03
         + "\n"
         + this.m10
         + ", "
         + this.m11
         + ", "
         + this.m12
         + ", "
         + this.m13
         + "\n"
         + this.m20
         + ", "
         + this.m21
         + ", "
         + this.m22
         + ", "
         + this.m23
         + "\n"
         + this.m30
         + ", "
         + this.m31
         + ", "
         + this.m32
         + ", "
         + this.m33
         + "\n";
   }

   public Matrix4f(Matrix4d var1) {
      this.m00 = (float)var1.m00;
      this.m01 = (float)var1.m01;
      this.m02 = (float)var1.m02;
      this.m03 = (float)var1.m03;
      this.m10 = (float)var1.m10;
      this.m11 = (float)var1.m11;
      this.m12 = (float)var1.m12;
      this.m13 = (float)var1.m13;
      this.m20 = (float)var1.m20;
      this.m21 = (float)var1.m21;
      this.m22 = (float)var1.m22;
      this.m23 = (float)var1.m23;
      this.m30 = (float)var1.m30;
      this.m31 = (float)var1.m31;
      this.m32 = (float)var1.m32;
      this.m33 = (float)var1.m33;
   }

   public void set(Matrix3d var1, Vector3d var2, double var3) {
      this.m00 = (float)(var1.m00 * var3);
      this.m01 = (float)(var1.m01 * var3);
      this.m02 = (float)(var1.m02 * var3);
      this.m03 = (float)var2.x;
      this.m10 = (float)(var1.m10 * var3);
      this.m11 = (float)(var1.m11 * var3);
      this.m12 = (float)(var1.m12 * var3);
      this.m13 = (float)var2.y;
      this.m20 = (float)(var1.m20 * var3);
      this.m21 = (float)(var1.m21 * var3);
      this.m22 = (float)(var1.m22 * var3);
      this.m23 = (float)var2.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void setRotation(Matrix3f var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      this.m00 = (float)(var1.m00 * var3[0]);
      this.m01 = (float)(var1.m01 * var3[1]);
      this.m02 = (float)(var1.m02 * var3[2]);
      this.m10 = (float)(var1.m10 * var3[0]);
      this.m11 = (float)(var1.m11 * var3[1]);
      this.m12 = (float)(var1.m12 * var3[2]);
      this.m20 = (float)(var1.m20 * var3[0]);
      this.m21 = (float)(var1.m21 * var3[1]);
      this.m22 = (float)(var1.m22 * var3[2]);
   }

   public void setRotation(Quat4f var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      this.m00 = (float)((1.0F - 2.0F * var1.y * var1.y - 2.0F * var1.z * var1.z) * var3[0]);
      this.m10 = (float)(2.0F * (var1.x * var1.y + var1.w * var1.z) * var3[0]);
      this.m20 = (float)(2.0F * (var1.x * var1.z - var1.w * var1.y) * var3[0]);
      this.m01 = (float)(2.0F * (var1.x * var1.y - var1.w * var1.z) * var3[1]);
      this.m11 = (float)((1.0F - 2.0F * var1.x * var1.x - 2.0F * var1.z * var1.z) * var3[1]);
      this.m21 = (float)(2.0F * (var1.y * var1.z + var1.w * var1.x) * var3[1]);
      this.m02 = (float)(2.0F * (var1.x * var1.z + var1.w * var1.y) * var3[2]);
      this.m12 = (float)(2.0F * (var1.y * var1.z - var1.w * var1.x) * var3[2]);
      this.m22 = (float)((1.0F - 2.0F * var1.x * var1.x - 2.0F * var1.y * var1.y) * var3[2]);
   }

   public void setZero() {
      this.m00 = 0.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 0.0F;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 0.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 0.0F;
   }

   public void rotY(float var1) {
      float var2 = (float)Math.sin(var1);
      float var3 = (float)Math.cos(var1);
      this.m00 = var3;
      this.m01 = 0.0F;
      this.m02 = var2;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = -var2;
      this.m21 = 0.0F;
      this.m22 = var3;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void set(Quat4d var1) {
      this.m00 = (float)(1.0 - 2.0 * var1.y * var1.y - 2.0 * var1.z * var1.z);
      this.m10 = (float)(2.0 * (var1.x * var1.y + var1.w * var1.z));
      this.m20 = (float)(2.0 * (var1.x * var1.z - var1.w * var1.y));
      this.m01 = (float)(2.0 * (var1.x * var1.y - var1.w * var1.z));
      this.m11 = (float)(1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.z * var1.z);
      this.m21 = (float)(2.0 * (var1.y * var1.z + var1.w * var1.x));
      this.m02 = (float)(2.0 * (var1.x * var1.z + var1.w * var1.y));
      this.m12 = (float)(2.0 * (var1.y * var1.z - var1.w * var1.x));
      this.m22 = (float)(1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.y * var1.y);
      this.m03 = 0.0F;
      this.m13 = 0.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void getColumn(int var1, Vector4f var2) {
      if (var1 == 0) {
         var2.x = this.m00;
         var2.y = this.m10;
         var2.z = this.m20;
         var2.w = this.m30;
      } else if (var1 == 1) {
         var2.x = this.m01;
         var2.y = this.m11;
         var2.z = this.m21;
         var2.w = this.m31;
      } else if (var1 == 2) {
         var2.x = this.m02;
         var2.y = this.m12;
         var2.z = this.m22;
         var2.w = this.m32;
      } else {
         if (var1 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f4"));
         }

         var2.x = this.m03;
         var2.y = this.m13;
         var2.z = this.m23;
         var2.w = this.m33;
      }
   }

   public void set(Vector3f var1, float var2) {
      this.m00 = var2;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = var2 * var1.x;
      this.m10 = 0.0F;
      this.m11 = var2;
      this.m12 = 0.0F;
      this.m13 = var2 * var1.y;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = var2;
      this.m23 = var2 * var1.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public Matrix4f(
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16
   ) {
      this.m00 = var1;
      this.m01 = var2;
      this.m02 = var3;
      this.m03 = var4;
      this.m10 = var5;
      this.m11 = var6;
      this.m12 = var7;
      this.m13 = var8;
      this.m20 = var9;
      this.m21 = var10;
      this.m22 = var11;
      this.m23 = var12;
      this.m30 = var13;
      this.m31 = var14;
      this.m32 = var15;
      this.m33 = var16;
   }

   public Matrix4f(Matrix4f var1) {
      this.m00 = var1.m00;
      this.m01 = var1.m01;
      this.m02 = var1.m02;
      this.m03 = var1.m03;
      this.m10 = var1.m10;
      this.m11 = var1.m11;
      this.m12 = var1.m12;
      this.m13 = var1.m13;
      this.m20 = var1.m20;
      this.m21 = var1.m21;
      this.m22 = var1.m22;
      this.m23 = var1.m23;
      this.m30 = var1.m30;
      this.m31 = var1.m31;
      this.m32 = var1.m32;
      this.m33 = var1.m33;
   }

   public void set(float var1) {
      this.m00 = var1;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = var1;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = var1;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public float getM11() {
      return this.m11;
   }

   public void setColumn(int var1, float var2, float var3, float var4, float var5) {
      switch (var1) {
         case 0:
            this.m00 = var2;
            this.m10 = var3;
            this.m20 = var4;
            this.m30 = var5;
            break;
         case 1:
            this.m01 = var2;
            this.m11 = var3;
            this.m21 = var4;
            this.m31 = var5;
            break;
         case 2:
            this.m02 = var2;
            this.m12 = var3;
            this.m22 = var4;
            this.m32 = var5;
            break;
         case 3:
            this.m03 = var2;
            this.m13 = var3;
            this.m23 = var4;
            this.m33 = var5;
            break;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f9"));
      }
   }

   public float getM21() {
      return this.m21;
   }

   public void set(Matrix4f var1) {
      this.m00 = var1.m00;
      this.m01 = var1.m01;
      this.m02 = var1.m02;
      this.m03 = var1.m03;
      this.m10 = var1.m10;
      this.m11 = var1.m11;
      this.m12 = var1.m12;
      this.m13 = var1.m13;
      this.m20 = var1.m20;
      this.m21 = var1.m21;
      this.m22 = var1.m22;
      this.m23 = var1.m23;
      this.m30 = var1.m30;
      this.m31 = var1.m31;
      this.m32 = var1.m32;
      this.m33 = var1.m33;
   }

   public void sub(Matrix4f var1, Matrix4f var2) {
      this.m00 = var1.m00 - var2.m00;
      this.m01 = var1.m01 - var2.m01;
      this.m02 = var1.m02 - var2.m02;
      this.m03 = var1.m03 - var2.m03;
      this.m10 = var1.m10 - var2.m10;
      this.m11 = var1.m11 - var2.m11;
      this.m12 = var1.m12 - var2.m12;
      this.m13 = var1.m13 - var2.m13;
      this.m20 = var1.m20 - var2.m20;
      this.m21 = var1.m21 - var2.m21;
      this.m22 = var1.m22 - var2.m22;
      this.m23 = var1.m23 - var2.m23;
      this.m30 = var1.m30 - var2.m30;
      this.m31 = var1.m31 - var2.m31;
      this.m32 = var1.m32 - var2.m32;
      this.m33 = var1.m33 - var2.m33;
   }

   public void setM03(float var1) {
      this.m03 = var1;
   }

   public void transform(Point3f var1, Point3f var2) {
      float var3 = this.m00 * var1.x + this.m01 * var1.y + this.m02 * var1.z + this.m03;
      float var4 = this.m10 * var1.x + this.m11 * var1.y + this.m12 * var1.z + this.m13;
      var2.z = this.m20 * var1.x + this.m21 * var1.y + this.m22 * var1.z + this.m23;
      var2.x = var3;
      var2.y = var4;
   }

   public void sub(Matrix4f var1) {
      this.m00 = this.m00 - var1.m00;
      this.m01 = this.m01 - var1.m01;
      this.m02 = this.m02 - var1.m02;
      this.m03 = this.m03 - var1.m03;
      this.m10 = this.m10 - var1.m10;
      this.m11 = this.m11 - var1.m11;
      this.m12 = this.m12 - var1.m12;
      this.m13 = this.m13 - var1.m13;
      this.m20 = this.m20 - var1.m20;
      this.m21 = this.m21 - var1.m21;
      this.m22 = this.m22 - var1.m22;
      this.m23 = this.m23 - var1.m23;
      this.m30 = this.m30 - var1.m30;
      this.m31 = this.m31 - var1.m31;
      this.m32 = this.m32 - var1.m32;
      this.m33 = this.m33 - var1.m33;
   }

   public void add(Matrix4f var1, Matrix4f var2) {
      this.m00 = var1.m00 + var2.m00;
      this.m01 = var1.m01 + var2.m01;
      this.m02 = var1.m02 + var2.m02;
      this.m03 = var1.m03 + var2.m03;
      this.m10 = var1.m10 + var2.m10;
      this.m11 = var1.m11 + var2.m11;
      this.m12 = var1.m12 + var2.m12;
      this.m13 = var1.m13 + var2.m13;
      this.m20 = var1.m20 + var2.m20;
      this.m21 = var1.m21 + var2.m21;
      this.m22 = var1.m22 + var2.m22;
      this.m23 = var1.m23 + var2.m23;
      this.m30 = var1.m30 + var2.m30;
      this.m31 = var1.m31 + var2.m31;
      this.m32 = var1.m32 + var2.m32;
      this.m33 = var1.m33 + var2.m33;
   }

   public void invert() {
      this.invertGeneral(this);
   }

   public void setM12(float var1) {
      this.m12 = var1;
   }

   public float getM33() {
      return this.m33;
   }

   public Matrix4f(Quat4f var1, Vector3f var2, float var3) {
      this.m00 = (float)(var3 * (1.0 - 2.0 * var1.y * var1.y - 2.0 * var1.z * var1.z));
      this.m10 = (float)(var3 * (2.0 * (var1.x * var1.y + var1.w * var1.z)));
      this.m20 = (float)(var3 * (2.0 * (var1.x * var1.z - var1.w * var1.y)));
      this.m01 = (float)(var3 * (2.0 * (var1.x * var1.y - var1.w * var1.z)));
      this.m11 = (float)(var3 * (1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.z * var1.z));
      this.m21 = (float)(var3 * (2.0 * (var1.y * var1.z + var1.w * var1.x)));
      this.m02 = (float)(var3 * (2.0 * (var1.x * var1.z + var1.w * var1.y)));
      this.m12 = (float)(var3 * (2.0 * (var1.y * var1.z - var1.w * var1.x)));
      this.m22 = (float)(var3 * (1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.y * var1.y));
      this.m03 = var2.x;
      this.m13 = var2.y;
      this.m23 = var2.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public float determinant() {
      float var1 = this.m00
         * (
            this.m11 * this.m22 * this.m33
               + this.m12 * this.m23 * this.m31
               + this.m13 * this.m21 * this.m32
               - this.m13 * this.m22 * this.m31
               - this.m11 * this.m23 * this.m32
               - this.m12 * this.m21 * this.m33
         );
      var1 -= this.m01
         * (
            this.m10 * this.m22 * this.m33
               + this.m12 * this.m23 * this.m30
               + this.m13 * this.m20 * this.m32
               - this.m13 * this.m22 * this.m30
               - this.m10 * this.m23 * this.m32
               - this.m12 * this.m20 * this.m33
         );
      var1 += this.m02
         * (
            this.m10 * this.m21 * this.m33
               + this.m11 * this.m23 * this.m30
               + this.m13 * this.m20 * this.m31
               - this.m13 * this.m21 * this.m30
               - this.m10 * this.m23 * this.m31
               - this.m11 * this.m20 * this.m33
         );
      return var1
         - this.m03
            * (
               this.m10 * this.m21 * this.m32
                  + this.m11 * this.m22 * this.m30
                  + this.m12 * this.m20 * this.m31
                  - this.m12 * this.m21 * this.m30
                  - this.m10 * this.m22 * this.m31
                  - this.m11 * this.m20 * this.m32
            );
   }

   public void invert(Matrix4f var1) {
      this.invertGeneral(var1);
   }

   public float getM20() {
      return this.m20;
   }

   public void mulTransposeRight(Matrix4f var1, Matrix4f var2) {
      if (this != var1 && this != var2) {
         this.m00 = var1.m00 * var2.m00 + var1.m01 * var2.m01 + var1.m02 * var2.m02 + var1.m03 * var2.m03;
         this.m01 = var1.m00 * var2.m10 + var1.m01 * var2.m11 + var1.m02 * var2.m12 + var1.m03 * var2.m13;
         this.m02 = var1.m00 * var2.m20 + var1.m01 * var2.m21 + var1.m02 * var2.m22 + var1.m03 * var2.m23;
         this.m03 = var1.m00 * var2.m30 + var1.m01 * var2.m31 + var1.m02 * var2.m32 + var1.m03 * var2.m33;
         this.m10 = var1.m10 * var2.m00 + var1.m11 * var2.m01 + var1.m12 * var2.m02 + var1.m13 * var2.m03;
         this.m11 = var1.m10 * var2.m10 + var1.m11 * var2.m11 + var1.m12 * var2.m12 + var1.m13 * var2.m13;
         this.m12 = var1.m10 * var2.m20 + var1.m11 * var2.m21 + var1.m12 * var2.m22 + var1.m13 * var2.m23;
         this.m13 = var1.m10 * var2.m30 + var1.m11 * var2.m31 + var1.m12 * var2.m32 + var1.m13 * var2.m33;
         this.m20 = var1.m20 * var2.m00 + var1.m21 * var2.m01 + var1.m22 * var2.m02 + var1.m23 * var2.m03;
         this.m21 = var1.m20 * var2.m10 + var1.m21 * var2.m11 + var1.m22 * var2.m12 + var1.m23 * var2.m13;
         this.m22 = var1.m20 * var2.m20 + var1.m21 * var2.m21 + var1.m22 * var2.m22 + var1.m23 * var2.m23;
         this.m23 = var1.m20 * var2.m30 + var1.m21 * var2.m31 + var1.m22 * var2.m32 + var1.m23 * var2.m33;
         this.m30 = var1.m30 * var2.m00 + var1.m31 * var2.m01 + var1.m32 * var2.m02 + var1.m33 * var2.m03;
         this.m31 = var1.m30 * var2.m10 + var1.m31 * var2.m11 + var1.m32 * var2.m12 + var1.m33 * var2.m13;
         this.m32 = var1.m30 * var2.m20 + var1.m31 * var2.m21 + var1.m32 * var2.m22 + var1.m33 * var2.m23;
         this.m33 = var1.m30 * var2.m30 + var1.m31 * var2.m31 + var1.m32 * var2.m32 + var1.m33 * var2.m33;
      } else {
         float var3 = var1.m00 * var2.m00 + var1.m01 * var2.m01 + var1.m02 * var2.m02 + var1.m03 * var2.m03;
         float var4 = var1.m00 * var2.m10 + var1.m01 * var2.m11 + var1.m02 * var2.m12 + var1.m03 * var2.m13;
         float var5 = var1.m00 * var2.m20 + var1.m01 * var2.m21 + var1.m02 * var2.m22 + var1.m03 * var2.m23;
         float var6 = var1.m00 * var2.m30 + var1.m01 * var2.m31 + var1.m02 * var2.m32 + var1.m03 * var2.m33;
         float var7 = var1.m10 * var2.m00 + var1.m11 * var2.m01 + var1.m12 * var2.m02 + var1.m13 * var2.m03;
         float var8 = var1.m10 * var2.m10 + var1.m11 * var2.m11 + var1.m12 * var2.m12 + var1.m13 * var2.m13;
         float var9 = var1.m10 * var2.m20 + var1.m11 * var2.m21 + var1.m12 * var2.m22 + var1.m13 * var2.m23;
         float var10 = var1.m10 * var2.m30 + var1.m11 * var2.m31 + var1.m12 * var2.m32 + var1.m13 * var2.m33;
         float var11 = var1.m20 * var2.m00 + var1.m21 * var2.m01 + var1.m22 * var2.m02 + var1.m23 * var2.m03;
         float var12 = var1.m20 * var2.m10 + var1.m21 * var2.m11 + var1.m22 * var2.m12 + var1.m23 * var2.m13;
         float var13 = var1.m20 * var2.m20 + var1.m21 * var2.m21 + var1.m22 * var2.m22 + var1.m23 * var2.m23;
         float var14 = var1.m20 * var2.m30 + var1.m21 * var2.m31 + var1.m22 * var2.m32 + var1.m23 * var2.m33;
         float var15 = var1.m30 * var2.m00 + var1.m31 * var2.m01 + var1.m32 * var2.m02 + var1.m33 * var2.m03;
         float var16 = var1.m30 * var2.m10 + var1.m31 * var2.m11 + var1.m32 * var2.m12 + var1.m33 * var2.m13;
         float var17 = var1.m30 * var2.m20 + var1.m31 * var2.m21 + var1.m32 * var2.m22 + var1.m33 * var2.m23;
         float var18 = var1.m30 * var2.m30 + var1.m31 * var2.m31 + var1.m32 * var2.m32 + var1.m33 * var2.m33;
         this.m00 = var3;
         this.m01 = var4;
         this.m02 = var5;
         this.m03 = var6;
         this.m10 = var7;
         this.m11 = var8;
         this.m12 = var9;
         this.m13 = var10;
         this.m20 = var11;
         this.m21 = var12;
         this.m22 = var13;
         this.m23 = var14;
         this.m30 = var15;
         this.m31 = var16;
         this.m32 = var17;
         this.m33 = var18;
      }
   }

   public void mul(float var1) {
      this.m00 *= var1;
      this.m01 *= var1;
      this.m02 *= var1;
      this.m03 *= var1;
      this.m10 *= var1;
      this.m11 *= var1;
      this.m12 *= var1;
      this.m13 *= var1;
      this.m20 *= var1;
      this.m21 *= var1;
      this.m22 *= var1;
      this.m23 *= var1;
      this.m30 *= var1;
      this.m31 *= var1;
      this.m32 *= var1;
      this.m33 *= var1;
   }

   public void transform(Vector3f var1) {
      float var2 = this.m00 * var1.x + this.m01 * var1.y + this.m02 * var1.z;
      float var3 = this.m10 * var1.x + this.m11 * var1.y + this.m12 * var1.z;
      var1.z = this.m20 * var1.x + this.m21 * var1.y + this.m22 * var1.z;
      var1.x = var2;
      var1.y = var3;
   }

   public void setM23(float var1) {
      this.m23 = var1;
   }

   public void mul(Matrix4f var1, Matrix4f var2) {
      if (this != var1 && this != var2) {
         this.m00 = var1.m00 * var2.m00 + var1.m01 * var2.m10 + var1.m02 * var2.m20 + var1.m03 * var2.m30;
         this.m01 = var1.m00 * var2.m01 + var1.m01 * var2.m11 + var1.m02 * var2.m21 + var1.m03 * var2.m31;
         this.m02 = var1.m00 * var2.m02 + var1.m01 * var2.m12 + var1.m02 * var2.m22 + var1.m03 * var2.m32;
         this.m03 = var1.m00 * var2.m03 + var1.m01 * var2.m13 + var1.m02 * var2.m23 + var1.m03 * var2.m33;
         this.m10 = var1.m10 * var2.m00 + var1.m11 * var2.m10 + var1.m12 * var2.m20 + var1.m13 * var2.m30;
         this.m11 = var1.m10 * var2.m01 + var1.m11 * var2.m11 + var1.m12 * var2.m21 + var1.m13 * var2.m31;
         this.m12 = var1.m10 * var2.m02 + var1.m11 * var2.m12 + var1.m12 * var2.m22 + var1.m13 * var2.m32;
         this.m13 = var1.m10 * var2.m03 + var1.m11 * var2.m13 + var1.m12 * var2.m23 + var1.m13 * var2.m33;
         this.m20 = var1.m20 * var2.m00 + var1.m21 * var2.m10 + var1.m22 * var2.m20 + var1.m23 * var2.m30;
         this.m21 = var1.m20 * var2.m01 + var1.m21 * var2.m11 + var1.m22 * var2.m21 + var1.m23 * var2.m31;
         this.m22 = var1.m20 * var2.m02 + var1.m21 * var2.m12 + var1.m22 * var2.m22 + var1.m23 * var2.m32;
         this.m23 = var1.m20 * var2.m03 + var1.m21 * var2.m13 + var1.m22 * var2.m23 + var1.m23 * var2.m33;
         this.m30 = var1.m30 * var2.m00 + var1.m31 * var2.m10 + var1.m32 * var2.m20 + var1.m33 * var2.m30;
         this.m31 = var1.m30 * var2.m01 + var1.m31 * var2.m11 + var1.m32 * var2.m21 + var1.m33 * var2.m31;
         this.m32 = var1.m30 * var2.m02 + var1.m31 * var2.m12 + var1.m32 * var2.m22 + var1.m33 * var2.m32;
         this.m33 = var1.m30 * var2.m03 + var1.m31 * var2.m13 + var1.m32 * var2.m23 + var1.m33 * var2.m33;
      } else {
         float var3 = var1.m00 * var2.m00 + var1.m01 * var2.m10 + var1.m02 * var2.m20 + var1.m03 * var2.m30;
         float var4 = var1.m00 * var2.m01 + var1.m01 * var2.m11 + var1.m02 * var2.m21 + var1.m03 * var2.m31;
         float var5 = var1.m00 * var2.m02 + var1.m01 * var2.m12 + var1.m02 * var2.m22 + var1.m03 * var2.m32;
         float var6 = var1.m00 * var2.m03 + var1.m01 * var2.m13 + var1.m02 * var2.m23 + var1.m03 * var2.m33;
         float var7 = var1.m10 * var2.m00 + var1.m11 * var2.m10 + var1.m12 * var2.m20 + var1.m13 * var2.m30;
         float var8 = var1.m10 * var2.m01 + var1.m11 * var2.m11 + var1.m12 * var2.m21 + var1.m13 * var2.m31;
         float var9 = var1.m10 * var2.m02 + var1.m11 * var2.m12 + var1.m12 * var2.m22 + var1.m13 * var2.m32;
         float var10 = var1.m10 * var2.m03 + var1.m11 * var2.m13 + var1.m12 * var2.m23 + var1.m13 * var2.m33;
         float var11 = var1.m20 * var2.m00 + var1.m21 * var2.m10 + var1.m22 * var2.m20 + var1.m23 * var2.m30;
         float var12 = var1.m20 * var2.m01 + var1.m21 * var2.m11 + var1.m22 * var2.m21 + var1.m23 * var2.m31;
         float var13 = var1.m20 * var2.m02 + var1.m21 * var2.m12 + var1.m22 * var2.m22 + var1.m23 * var2.m32;
         float var14 = var1.m20 * var2.m03 + var1.m21 * var2.m13 + var1.m22 * var2.m23 + var1.m23 * var2.m33;
         float var15 = var1.m30 * var2.m00 + var1.m31 * var2.m10 + var1.m32 * var2.m20 + var1.m33 * var2.m30;
         float var16 = var1.m30 * var2.m01 + var1.m31 * var2.m11 + var1.m32 * var2.m21 + var1.m33 * var2.m31;
         float var17 = var1.m30 * var2.m02 + var1.m31 * var2.m12 + var1.m32 * var2.m22 + var1.m33 * var2.m32;
         float var18 = var1.m30 * var2.m03 + var1.m31 * var2.m13 + var1.m32 * var2.m23 + var1.m33 * var2.m33;
         this.m00 = var3;
         this.m01 = var4;
         this.m02 = var5;
         this.m03 = var6;
         this.m10 = var7;
         this.m11 = var8;
         this.m12 = var9;
         this.m13 = var10;
         this.m20 = var11;
         this.m21 = var12;
         this.m22 = var13;
         this.m23 = var14;
         this.m30 = var15;
         this.m31 = var16;
         this.m32 = var17;
         this.m33 = var18;
      }
   }

   public float getM13() {
      return this.m13;
   }

   public void setColumn(int var1, float[] var2) {
      switch (var1) {
         case 0:
            this.m00 = var2[0];
            this.m10 = var2[1];
            this.m20 = var2[2];
            this.m30 = var2[3];
            break;
         case 1:
            this.m01 = var2[0];
            this.m11 = var2[1];
            this.m21 = var2[2];
            this.m31 = var2[3];
            break;
         case 2:
            this.m02 = var2[0];
            this.m12 = var2[1];
            this.m22 = var2[2];
            this.m32 = var2[3];
            break;
         case 3:
            this.m03 = var2[0];
            this.m13 = var2[1];
            this.m23 = var2[2];
            this.m33 = var2[3];
            break;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f9"));
      }
   }

   public void setIdentity() {
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void getColumn(int var1, float[] var2) {
      if (var1 == 0) {
         var2[0] = this.m00;
         var2[1] = this.m10;
         var2[2] = this.m20;
         var2[3] = this.m30;
      } else if (var1 == 1) {
         var2[0] = this.m01;
         var2[1] = this.m11;
         var2[2] = this.m21;
         var2[3] = this.m31;
      } else if (var1 == 2) {
         var2[0] = this.m02;
         var2[1] = this.m12;
         var2[2] = this.m22;
         var2[3] = this.m32;
      } else {
         if (var1 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f4"));
         }

         var2[0] = this.m03;
         var2[1] = this.m13;
         var2[2] = this.m23;
         var2[3] = this.m33;
      }
   }

   public void setM30(float var1) {
      this.m30 = var1;
   }

   public void transpose() {
      float var1 = this.m10;
      this.m10 = this.m01;
      this.m01 = var1;
      var1 = this.m20;
      this.m20 = this.m02;
      this.m02 = var1;
      var1 = this.m30;
      this.m30 = this.m03;
      this.m03 = var1;
      var1 = this.m21;
      this.m21 = this.m12;
      this.m12 = var1;
      var1 = this.m31;
      this.m31 = this.m13;
      this.m13 = var1;
      var1 = this.m32;
      this.m32 = this.m23;
      this.m23 = var1;
   }

   public float getM32() {
      return this.m32;
   }

   public void set(AxisAngle4d var1) {
      double var2 = Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      if (var2 < 1.0E-8) {
         this.m00 = 1.0F;
         this.m01 = 0.0F;
         this.m02 = 0.0F;
         this.m10 = 0.0F;
         this.m11 = 1.0F;
         this.m12 = 0.0F;
         this.m20 = 0.0F;
         this.m21 = 0.0F;
         this.m22 = 1.0F;
      } else {
         var2 = 1.0 / var2;
         double var4 = var1.x * var2;
         double var6 = var1.y * var2;
         double var8 = var1.z * var2;
         float var10 = (float)Math.sin(var1.angle);
         float var11 = (float)Math.cos(var1.angle);
         float var12 = 1.0F - var11;
         float var13 = (float)(var4 * var8);
         float var14 = (float)(var4 * var6);
         float var15 = (float)(var6 * var8);
         this.m00 = var12 * (float)(var4 * var4) + var11;
         this.m01 = var12 * var14 - var10 * (float)var8;
         this.m02 = var12 * var13 + var10 * (float)var6;
         this.m10 = var12 * var14 + var10 * (float)var8;
         this.m11 = var12 * (float)(var6 * var6) + var11;
         this.m12 = var12 * var15 - var10 * (float)var4;
         this.m20 = var12 * var13 - var10 * (float)var6;
         this.m21 = var12 * var15 + var10 * (float)var4;
         this.m22 = var12 * (float)(var8 * var8) + var11;
      }

      this.m03 = 0.0F;
      this.m13 = 0.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void setM32(float var1) {
      this.m32 = var1;
   }

   public void setScale(float var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      this.m00 = (float)(var2[0] * var1);
      this.m01 = (float)(var2[1] * var1);
      this.m02 = (float)(var2[2] * var1);
      this.m10 = (float)(var2[3] * var1);
      this.m11 = (float)(var2[4] * var1);
      this.m12 = (float)(var2[5] * var1);
      this.m20 = (float)(var2[6] * var1);
      this.m21 = (float)(var2[7] * var1);
      this.m22 = (float)(var2[8] * var1);
   }

   public void setElement(int var1, int var2, float var3) {
      switch (var1) {
         case 0:
            switch (var2) {
               case 0:
                  this.m00 = var3;
                  return;
               case 1:
                  this.m01 = var3;
                  return;
               case 2:
                  this.m02 = var3;
                  return;
               case 3:
                  this.m03 = var3;
                  return;
               default:
                  throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
            }
         case 1:
            switch (var2) {
               case 0:
                  this.m10 = var3;
                  return;
               case 1:
                  this.m11 = var3;
                  return;
               case 2:
                  this.m12 = var3;
                  return;
               case 3:
                  this.m13 = var3;
                  return;
               default:
                  throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
            }
         case 2:
            switch (var2) {
               case 0:
                  this.m20 = var3;
                  return;
               case 1:
                  this.m21 = var3;
                  return;
               case 2:
                  this.m22 = var3;
                  return;
               case 3:
                  this.m23 = var3;
                  return;
               default:
                  throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
            }
         case 3:
            switch (var2) {
               case 0:
                  this.m30 = var3;
                  return;
               case 1:
                  this.m31 = var3;
                  return;
               case 2:
                  this.m32 = var3;
                  return;
               case 3:
                  this.m33 = var3;
                  return;
               default:
                  throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
            }
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f0"));
      }
   }

   public void add(float var1) {
      this.m00 += var1;
      this.m01 += var1;
      this.m02 += var1;
      this.m03 += var1;
      this.m10 += var1;
      this.m11 += var1;
      this.m12 += var1;
      this.m13 += var1;
      this.m20 += var1;
      this.m21 += var1;
      this.m22 += var1;
      this.m23 += var1;
      this.m30 += var1;
      this.m31 += var1;
      this.m32 += var1;
      this.m33 += var1;
   }

   public void negate() {
      this.m00 = -this.m00;
      this.m01 = -this.m01;
      this.m02 = -this.m02;
      this.m03 = -this.m03;
      this.m10 = -this.m10;
      this.m11 = -this.m11;
      this.m12 = -this.m12;
      this.m13 = -this.m13;
      this.m20 = -this.m20;
      this.m21 = -this.m21;
      this.m22 = -this.m22;
      this.m23 = -this.m23;
      this.m30 = -this.m30;
      this.m31 = -this.m31;
      this.m32 = -this.m32;
      this.m33 = -this.m33;
   }

   public void set(Matrix3f var1) {
      this.m00 = var1.m00;
      this.m01 = var1.m01;
      this.m02 = var1.m02;
      this.m03 = 0.0F;
      this.m10 = var1.m10;
      this.m11 = var1.m11;
      this.m12 = var1.m12;
      this.m13 = 0.0F;
      this.m20 = var1.m20;
      this.m21 = var1.m21;
      this.m22 = var1.m22;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public float getM23() {
      return this.m23;
   }

   public void setRow(int var1, float var2, float var3, float var4, float var5) {
      switch (var1) {
         case 0:
            this.m00 = var2;
            this.m01 = var3;
            this.m02 = var4;
            this.m03 = var5;
            break;
         case 1:
            this.m10 = var2;
            this.m11 = var3;
            this.m12 = var4;
            this.m13 = var5;
            break;
         case 2:
            this.m20 = var2;
            this.m21 = var3;
            this.m22 = var4;
            this.m23 = var5;
            break;
         case 3:
            this.m30 = var2;
            this.m31 = var3;
            this.m32 = var4;
            this.m33 = var5;
            break;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f6"));
      }
   }

   public void mulTransposeLeft(Matrix4f var1, Matrix4f var2) {
      if (this != var1 && this != var2) {
         this.m00 = var1.m00 * var2.m00 + var1.m10 * var2.m10 + var1.m20 * var2.m20 + var1.m30 * var2.m30;
         this.m01 = var1.m00 * var2.m01 + var1.m10 * var2.m11 + var1.m20 * var2.m21 + var1.m30 * var2.m31;
         this.m02 = var1.m00 * var2.m02 + var1.m10 * var2.m12 + var1.m20 * var2.m22 + var1.m30 * var2.m32;
         this.m03 = var1.m00 * var2.m03 + var1.m10 * var2.m13 + var1.m20 * var2.m23 + var1.m30 * var2.m33;
         this.m10 = var1.m01 * var2.m00 + var1.m11 * var2.m10 + var1.m21 * var2.m20 + var1.m31 * var2.m30;
         this.m11 = var1.m01 * var2.m01 + var1.m11 * var2.m11 + var1.m21 * var2.m21 + var1.m31 * var2.m31;
         this.m12 = var1.m01 * var2.m02 + var1.m11 * var2.m12 + var1.m21 * var2.m22 + var1.m31 * var2.m32;
         this.m13 = var1.m01 * var2.m03 + var1.m11 * var2.m13 + var1.m21 * var2.m23 + var1.m31 * var2.m33;
         this.m20 = var1.m02 * var2.m00 + var1.m12 * var2.m10 + var1.m22 * var2.m20 + var1.m32 * var2.m30;
         this.m21 = var1.m02 * var2.m01 + var1.m12 * var2.m11 + var1.m22 * var2.m21 + var1.m32 * var2.m31;
         this.m22 = var1.m02 * var2.m02 + var1.m12 * var2.m12 + var1.m22 * var2.m22 + var1.m32 * var2.m32;
         this.m23 = var1.m02 * var2.m03 + var1.m12 * var2.m13 + var1.m22 * var2.m23 + var1.m32 * var2.m33;
         this.m30 = var1.m03 * var2.m00 + var1.m13 * var2.m10 + var1.m23 * var2.m20 + var1.m33 * var2.m30;
         this.m31 = var1.m03 * var2.m01 + var1.m13 * var2.m11 + var1.m23 * var2.m21 + var1.m33 * var2.m31;
         this.m32 = var1.m03 * var2.m02 + var1.m13 * var2.m12 + var1.m23 * var2.m22 + var1.m33 * var2.m32;
         this.m33 = var1.m03 * var2.m03 + var1.m13 * var2.m13 + var1.m23 * var2.m23 + var1.m33 * var2.m33;
      } else {
         float var3 = var1.m00 * var2.m00 + var1.m10 * var2.m10 + var1.m20 * var2.m20 + var1.m30 * var2.m30;
         float var4 = var1.m00 * var2.m01 + var1.m10 * var2.m11 + var1.m20 * var2.m21 + var1.m30 * var2.m31;
         float var5 = var1.m00 * var2.m02 + var1.m10 * var2.m12 + var1.m20 * var2.m22 + var1.m30 * var2.m32;
         float var6 = var1.m00 * var2.m03 + var1.m10 * var2.m13 + var1.m20 * var2.m23 + var1.m30 * var2.m33;
         float var7 = var1.m01 * var2.m00 + var1.m11 * var2.m10 + var1.m21 * var2.m20 + var1.m31 * var2.m30;
         float var8 = var1.m01 * var2.m01 + var1.m11 * var2.m11 + var1.m21 * var2.m21 + var1.m31 * var2.m31;
         float var9 = var1.m01 * var2.m02 + var1.m11 * var2.m12 + var1.m21 * var2.m22 + var1.m31 * var2.m32;
         float var10 = var1.m01 * var2.m03 + var1.m11 * var2.m13 + var1.m21 * var2.m23 + var1.m31 * var2.m33;
         float var11 = var1.m02 * var2.m00 + var1.m12 * var2.m10 + var1.m22 * var2.m20 + var1.m32 * var2.m30;
         float var12 = var1.m02 * var2.m01 + var1.m12 * var2.m11 + var1.m22 * var2.m21 + var1.m32 * var2.m31;
         float var13 = var1.m02 * var2.m02 + var1.m12 * var2.m12 + var1.m22 * var2.m22 + var1.m32 * var2.m32;
         float var14 = var1.m02 * var2.m03 + var1.m12 * var2.m13 + var1.m22 * var2.m23 + var1.m32 * var2.m33;
         float var15 = var1.m03 * var2.m00 + var1.m13 * var2.m10 + var1.m23 * var2.m20 + var1.m33 * var2.m30;
         float var16 = var1.m03 * var2.m01 + var1.m13 * var2.m11 + var1.m23 * var2.m21 + var1.m33 * var2.m31;
         float var17 = var1.m03 * var2.m02 + var1.m13 * var2.m12 + var1.m23 * var2.m22 + var1.m33 * var2.m32;
         float var18 = var1.m03 * var2.m03 + var1.m13 * var2.m13 + var1.m23 * var2.m23 + var1.m33 * var2.m33;
         this.m00 = var3;
         this.m01 = var4;
         this.m02 = var5;
         this.m03 = var6;
         this.m10 = var7;
         this.m11 = var8;
         this.m12 = var9;
         this.m13 = var10;
         this.m20 = var11;
         this.m21 = var12;
         this.m22 = var13;
         this.m23 = var14;
         this.m30 = var15;
         this.m31 = var16;
         this.m32 = var17;
         this.m33 = var18;
      }
   }

   public void set(Quat4f var1, Vector3f var2, float var3) {
      this.m00 = var3 * (1.0F - 2.0F * var1.y * var1.y - 2.0F * var1.z * var1.z);
      this.m10 = var3 * (2.0F * (var1.x * var1.y + var1.w * var1.z));
      this.m20 = var3 * (2.0F * (var1.x * var1.z - var1.w * var1.y));
      this.m01 = var3 * (2.0F * (var1.x * var1.y - var1.w * var1.z));
      this.m11 = var3 * (1.0F - 2.0F * var1.x * var1.x - 2.0F * var1.z * var1.z);
      this.m21 = var3 * (2.0F * (var1.y * var1.z + var1.w * var1.x));
      this.m02 = var3 * (2.0F * (var1.x * var1.z + var1.w * var1.y));
      this.m12 = var3 * (2.0F * (var1.y * var1.z - var1.w * var1.x));
      this.m22 = var3 * (1.0F - 2.0F * var1.x * var1.x - 2.0F * var1.y * var1.y);
      this.m03 = var2.x;
      this.m13 = var2.y;
      this.m23 = var2.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void setM13(float var1) {
      this.m13 = var1;
   }

   public void rotZ(float var1) {
      float var2 = (float)Math.sin(var1);
      float var3 = (float)Math.cos(var1);
      this.m00 = var3;
      this.m01 = -var2;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = var2;
      this.m11 = var3;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void setM31(float var1) {
      this.m31 = var1;
   }

   public void setM20(float var1) {
      this.m20 = var1;
   }

   public void set(float[] var1) {
      this.m00 = var1[0];
      this.m01 = var1[1];
      this.m02 = var1[2];
      this.m03 = var1[3];
      this.m10 = var1[4];
      this.m11 = var1[5];
      this.m12 = var1[6];
      this.m13 = var1[7];
      this.m20 = var1[8];
      this.m21 = var1[9];
      this.m22 = var1[10];
      this.m23 = var1[11];
      this.m30 = var1[12];
      this.m31 = var1[13];
      this.m32 = var1[14];
      this.m33 = var1[15];
   }

   public void setRotation(AxisAngle4f var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      double var4 = Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      if (var4 < 1.0E-8) {
         this.m00 = 1.0F;
         this.m01 = 0.0F;
         this.m02 = 0.0F;
         this.m10 = 0.0F;
         this.m11 = 1.0F;
         this.m12 = 0.0F;
         this.m20 = 0.0F;
         this.m21 = 0.0F;
         this.m22 = 1.0F;
      } else {
         var4 = 1.0 / var4;
         double var6 = var1.x * var4;
         double var8 = var1.y * var4;
         double var10 = var1.z * var4;
         double var12 = Math.sin(var1.angle);
         double var14 = Math.cos(var1.angle);
         double var16 = 1.0 - var14;
         double var18 = var1.x * var1.z;
         double var20 = var1.x * var1.y;
         double var22 = var1.y * var1.z;
         this.m00 = (float)((var16 * var6 * var6 + var14) * var3[0]);
         this.m01 = (float)((var16 * var20 - var12 * var10) * var3[1]);
         this.m02 = (float)((var16 * var18 + var12 * var8) * var3[2]);
         this.m10 = (float)((var16 * var20 + var12 * var10) * var3[0]);
         this.m11 = (float)((var16 * var8 * var8 + var14) * var3[1]);
         this.m12 = (float)((var16 * var22 - var12 * var6) * var3[2]);
         this.m20 = (float)((var16 * var18 - var12 * var8) * var3[0]);
         this.m21 = (float)((var16 * var22 + var12 * var6) * var3[1]);
         this.m22 = (float)((var16 * var10 * var10 + var14) * var3[2]);
      }
   }

   public void setM33(float var1) {
      this.m33 = var1;
   }

   public void getRow(int var1, float[] var2) {
      if (var1 == 0) {
         var2[0] = this.m00;
         var2[1] = this.m01;
         var2[2] = this.m02;
         var2[3] = this.m03;
      } else if (var1 == 1) {
         var2[0] = this.m10;
         var2[1] = this.m11;
         var2[2] = this.m12;
         var2[3] = this.m13;
      } else if (var1 == 2) {
         var2[0] = this.m20;
         var2[1] = this.m21;
         var2[2] = this.m22;
         var2[3] = this.m23;
      } else {
         if (var1 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f2"));
         }

         var2[0] = this.m30;
         var2[1] = this.m31;
         var2[2] = this.m32;
         var2[3] = this.m33;
      }
   }

   public Matrix4f(float[] var1) {
      this.m00 = var1[0];
      this.m01 = var1[1];
      this.m02 = var1[2];
      this.m03 = var1[3];
      this.m10 = var1[4];
      this.m11 = var1[5];
      this.m12 = var1[6];
      this.m13 = var1[7];
      this.m20 = var1[8];
      this.m21 = var1[9];
      this.m22 = var1[10];
      this.m23 = var1[11];
      this.m30 = var1[12];
      this.m31 = var1[13];
      this.m32 = var1[14];
      this.m33 = var1[15];
   }

   public void get(Quat4f var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      double var4 = 0.25 * (1.0 + var2[0] + var2[4] + var2[8]);
      if (!((var4 < 0.0 ? -var4 : var4) < 1.0E-30)) {
         var1.w = (float)Math.sqrt(var4);
         var4 = 0.25 / var1.w;
         var1.x = (float)((var2[7] - var2[5]) * var4);
         var1.y = (float)((var2[2] - var2[6]) * var4);
         var1.z = (float)((var2[3] - var2[1]) * var4);
      } else {
         var1.w = 0.0F;
         var4 = -0.5 * (var2[4] + var2[8]);
         if (!((var4 < 0.0 ? -var4 : var4) < 1.0E-30)) {
            var1.x = (float)Math.sqrt(var4);
            var4 = 0.5 / var1.x;
            var1.y = (float)(var2[3] * var4);
            var1.z = (float)(var2[6] * var4);
         } else {
            var1.x = 0.0F;
            var4 = 0.5 * (1.0 - var2[8]);
            if (!((var4 < 0.0 ? -var4 : var4) < 1.0E-30)) {
               var1.y = (float)Math.sqrt(var4);
               var1.z = (float)(var2[7] / (2.0 * var1.y));
            } else {
               var1.y = 0.0F;
               var1.z = 1.0F;
            }
         }
      }
   }

   public Matrix4f() {
      this.m00 = 0.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = 0.0F;
      this.m12 = 0.0F;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 0.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 0.0F;
   }

   public void getRotationScale(Matrix3f var1) {
      var1.m00 = this.m00;
      var1.m01 = this.m01;
      var1.m02 = this.m02;
      var1.m10 = this.m10;
      var1.m11 = this.m11;
      var1.m12 = this.m12;
      var1.m20 = this.m20;
      var1.m21 = this.m21;
      var1.m22 = this.m22;
   }

   public void setM11(float var1) {
      this.m11 = var1;
   }

   public void getRow(int var1, Vector4f var2) {
      if (var1 == 0) {
         var2.x = this.m00;
         var2.y = this.m01;
         var2.z = this.m02;
         var2.w = this.m03;
      } else if (var1 == 1) {
         var2.x = this.m10;
         var2.y = this.m11;
         var2.z = this.m12;
         var2.w = this.m13;
      } else if (var1 == 2) {
         var2.x = this.m20;
         var2.y = this.m21;
         var2.z = this.m22;
         var2.w = this.m23;
      } else {
         if (var1 != 3) {
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f2"));
         }

         var2.x = this.m30;
         var2.y = this.m31;
         var2.z = this.m32;
         var2.w = this.m33;
      }
   }

   public void set(Quat4d var1, Vector3d var2, double var3) {
      this.m00 = (float)(var3 * (1.0 - 2.0 * var1.y * var1.y - 2.0 * var1.z * var1.z));
      this.m10 = (float)(var3 * (2.0 * (var1.x * var1.y + var1.w * var1.z)));
      this.m20 = (float)(var3 * (2.0 * (var1.x * var1.z - var1.w * var1.y)));
      this.m01 = (float)(var3 * (2.0 * (var1.x * var1.y - var1.w * var1.z)));
      this.m11 = (float)(var3 * (1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.z * var1.z));
      this.m21 = (float)(var3 * (2.0 * (var1.y * var1.z + var1.w * var1.x)));
      this.m02 = (float)(var3 * (2.0 * (var1.x * var1.z + var1.w * var1.y)));
      this.m12 = (float)(var3 * (2.0 * (var1.y * var1.z - var1.w * var1.x)));
      this.m22 = (float)(var3 * (1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.y * var1.y));
      this.m03 = (float)var2.x;
      this.m13 = (float)var2.y;
      this.m23 = (float)var2.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void setRotation(Matrix3d var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      this.m00 = (float)(var1.m00 * var3[0]);
      this.m01 = (float)(var1.m01 * var3[1]);
      this.m02 = (float)(var1.m02 * var3[2]);
      this.m10 = (float)(var1.m10 * var3[0]);
      this.m11 = (float)(var1.m11 * var3[1]);
      this.m12 = (float)(var1.m12 * var3[2]);
      this.m20 = (float)(var1.m20 * var3[0]);
      this.m21 = (float)(var1.m21 * var3[1]);
      this.m22 = (float)(var1.m22 * var3[2]);
   }

   public void set(Matrix3f var1, Vector3f var2, float var3) {
      this.m00 = var1.m00 * var3;
      this.m01 = var1.m01 * var3;
      this.m02 = var1.m02 * var3;
      this.m03 = var2.x;
      this.m10 = var1.m10 * var3;
      this.m11 = var1.m11 * var3;
      this.m12 = var1.m12 * var3;
      this.m13 = var2.y;
      this.m20 = var1.m20 * var3;
      this.m21 = var1.m21 * var3;
      this.m22 = var1.m22 * var3;
      this.m23 = var2.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void add(Matrix4f var1) {
      this.m00 = this.m00 + var1.m00;
      this.m01 = this.m01 + var1.m01;
      this.m02 = this.m02 + var1.m02;
      this.m03 = this.m03 + var1.m03;
      this.m10 = this.m10 + var1.m10;
      this.m11 = this.m11 + var1.m11;
      this.m12 = this.m12 + var1.m12;
      this.m13 = this.m13 + var1.m13;
      this.m20 = this.m20 + var1.m20;
      this.m21 = this.m21 + var1.m21;
      this.m22 = this.m22 + var1.m22;
      this.m23 = this.m23 + var1.m23;
      this.m30 = this.m30 + var1.m30;
      this.m31 = this.m31 + var1.m31;
      this.m32 = this.m32 + var1.m32;
      this.m33 = this.m33 + var1.m33;
   }

   public void setRow(int var1, Vector4f var2) {
      switch (var1) {
         case 0:
            this.m00 = var2.x;
            this.m01 = var2.y;
            this.m02 = var2.z;
            this.m03 = var2.w;
            break;
         case 1:
            this.m10 = var2.x;
            this.m11 = var2.y;
            this.m12 = var2.z;
            this.m13 = var2.w;
            break;
         case 2:
            this.m20 = var2.x;
            this.m21 = var2.y;
            this.m22 = var2.z;
            this.m23 = var2.w;
            break;
         case 3:
            this.m30 = var2.x;
            this.m31 = var2.y;
            this.m32 = var2.z;
            this.m33 = var2.w;
            break;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f6"));
      }
   }

   public float getM10() {
      return this.m10;
   }

   public float get(Matrix3f var1, Vector3f var2) {
      double[] var3 = new double[9];
      double[] var4 = new double[3];
      this.getScaleRotate(var4, var3);
      var1.m00 = (float)var3[0];
      var1.m01 = (float)var3[1];
      var1.m02 = (float)var3[2];
      var1.m10 = (float)var3[3];
      var1.m11 = (float)var3[4];
      var1.m12 = (float)var3[5];
      var1.m20 = (float)var3[6];
      var1.m21 = (float)var3[7];
      var1.m22 = (float)var3[8];
      var2.x = this.m03;
      var2.y = this.m13;
      var2.z = this.m23;
      return (float)Matrix3d.max3(var4);
   }

   public void setRotationScale(Matrix3f var1) {
      this.m00 = var1.m00;
      this.m01 = var1.m01;
      this.m02 = var1.m02;
      this.m10 = var1.m10;
      this.m11 = var1.m11;
      this.m12 = var1.m12;
      this.m20 = var1.m20;
      this.m21 = var1.m21;
      this.m22 = var1.m22;
   }

   public float getM12() {
      return this.m12;
   }

   public static void luBacksubstitution(double[] var0, int[] var1, double[] var2) {
      byte var8 = 0;

      for (int var7 = 0; var7 < 4; var7++) {
         int var9 = var7;
         int var4 = -1;

         for (int var3 = 0; var3 < 4; var3++) {
            int var5 = var1[var8 + var3];
            double var11 = var2[var9 + 4 * var5];
            var2[var9 + 4 * var5] = var2[var9 + 4 * var3];
            if (var4 >= 0) {
               int var10 = var3 * 4;

               for (int var6 = var4; var6 <= var3 - 1; var6++) {
                  var11 -= var0[var10 + var6] * var2[var9 + 4 * var6];
               }
            } else if (var11 != 0.0) {
               var4 = var3;
            }

            var2[var9 + 4 * var3] = var11;
         }

         int var13 = 12;
         var2[var9 + 12] = var2[var9 + 12] / var0[var13 + 3];
         var13 -= 4;
         var2[var9 + 8] = (var2[var9 + 8] - var0[var13 + 3] * var2[var9 + 12]) / var0[var13 + 2];
         var13 -= 4;
         var2[var9 + 4] = (var2[var9 + 4] - var0[var13 + 2] * var2[var9 + 8] - var0[var13 + 3] * var2[var9 + 12]) / var0[var13 + 1];
         var13 -= 4;
         var2[var9 + 0] = (var2[var9 + 0] - var0[var13 + 1] * var2[var9 + 4] - var0[var13 + 2] * var2[var9 + 8] - var0[var13 + 3] * var2[var9 + 12])
            / var0[var13 + 0];
      }
   }

   public void transform(Tuple4f var1, Tuple4f var2) {
      float var3 = this.m00 * var1.x + this.m01 * var1.y + this.m02 * var1.z + this.m03 * var1.w;
      float var4 = this.m10 * var1.x + this.m11 * var1.y + this.m12 * var1.z + this.m13 * var1.w;
      float var5 = this.m20 * var1.x + this.m21 * var1.y + this.m22 * var1.z + this.m23 * var1.w;
      var2.w = this.m30 * var1.x + this.m31 * var1.y + this.m32 * var1.z + this.m33 * var1.w;
      var2.x = var3;
      var2.y = var4;
      var2.z = var5;
   }

   public void set(Matrix3d var1) {
      this.m00 = (float)var1.m00;
      this.m01 = (float)var1.m01;
      this.m02 = (float)var1.m02;
      this.m03 = 0.0F;
      this.m10 = (float)var1.m10;
      this.m11 = (float)var1.m11;
      this.m12 = (float)var1.m12;
      this.m13 = 0.0F;
      this.m20 = (float)var1.m20;
      this.m21 = (float)var1.m21;
      this.m22 = (float)var1.m22;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void get(Matrix3d var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      var1.m00 = var2[0];
      var1.m01 = var2[1];
      var1.m02 = var2[2];
      var1.m10 = var2[3];
      var1.m11 = var2[4];
      var1.m12 = var2[5];
      var1.m20 = var2[6];
      var1.m21 = var2[7];
      var1.m22 = var2[8];
   }

   public void negate(Matrix4f var1) {
      this.m00 = -var1.m00;
      this.m01 = -var1.m01;
      this.m02 = -var1.m02;
      this.m03 = -var1.m03;
      this.m10 = -var1.m10;
      this.m11 = -var1.m11;
      this.m12 = -var1.m12;
      this.m13 = -var1.m13;
      this.m20 = -var1.m20;
      this.m21 = -var1.m21;
      this.m22 = -var1.m22;
      this.m23 = -var1.m23;
      this.m30 = -var1.m30;
      this.m31 = -var1.m31;
      this.m32 = -var1.m32;
      this.m33 = -var1.m33;
   }

   public void transform(Vector3f var1, Vector3f var2) {
      float var3 = this.m00 * var1.x + this.m01 * var1.y + this.m02 * var1.z;
      float var4 = this.m10 * var1.x + this.m11 * var1.y + this.m12 * var1.z;
      var2.z = this.m20 * var1.x + this.m21 * var1.y + this.m22 * var1.z;
      var2.x = var3;
      var2.y = var4;
   }

   public void set(AxisAngle4f var1) {
      float var2 = (float)Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      if (var2 < 1.0E-8) {
         this.m00 = 1.0F;
         this.m01 = 0.0F;
         this.m02 = 0.0F;
         this.m10 = 0.0F;
         this.m11 = 1.0F;
         this.m12 = 0.0F;
         this.m20 = 0.0F;
         this.m21 = 0.0F;
         this.m22 = 1.0F;
      } else {
         var2 = 1.0F / var2;
         float var3 = var1.x * var2;
         float var4 = var1.y * var2;
         float var5 = var1.z * var2;
         float var6 = (float)Math.sin(var1.angle);
         float var7 = (float)Math.cos(var1.angle);
         float var8 = 1.0F - var7;
         float var9 = var3 * var5;
         float var10 = var3 * var4;
         float var11 = var4 * var5;
         this.m00 = var8 * var3 * var3 + var7;
         this.m01 = var8 * var10 - var6 * var5;
         this.m02 = var8 * var9 + var6 * var4;
         this.m10 = var8 * var10 + var6 * var5;
         this.m11 = var8 * var4 * var4 + var7;
         this.m12 = var8 * var11 - var6 * var3;
         this.m20 = var8 * var9 - var6 * var4;
         this.m21 = var8 * var11 + var6 * var3;
         this.m22 = var8 * var5 * var5 + var7;
      }

      this.m03 = 0.0F;
      this.m13 = 0.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public float getM03() {
      return this.m03;
   }

   public float getM02() {
      return this.m02;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         Matrix4f var2 = (Matrix4f)var1;
         return this.m00 == var2.m00
            && this.m01 == var2.m01
            && this.m02 == var2.m02
            && this.m03 == var2.m03
            && this.m10 == var2.m10
            && this.m11 == var2.m11
            && this.m12 == var2.m12
            && this.m13 == var2.m13
            && this.m20 == var2.m20
            && this.m21 == var2.m21
            && this.m22 == var2.m22
            && this.m23 == var2.m23
            && this.m30 == var2.m30
            && this.m31 == var2.m31
            && this.m32 == var2.m32
            && this.m33 == var2.m33;
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public void setM00(float var1) {
      this.m00 = var1;
   }

   public void add(float var1, Matrix4f var2) {
      this.m00 = var2.m00 + var1;
      this.m01 = var2.m01 + var1;
      this.m02 = var2.m02 + var1;
      this.m03 = var2.m03 + var1;
      this.m10 = var2.m10 + var1;
      this.m11 = var2.m11 + var1;
      this.m12 = var2.m12 + var1;
      this.m13 = var2.m13 + var1;
      this.m20 = var2.m20 + var1;
      this.m21 = var2.m21 + var1;
      this.m22 = var2.m22 + var1;
      this.m23 = var2.m23 + var1;
      this.m30 = var2.m30 + var1;
      this.m31 = var2.m31 + var1;
      this.m32 = var2.m32 + var1;
      this.m33 = var2.m33 + var1;
   }

   public void setM22(float var1) {
      this.m22 = var1;
   }

   @Override
   public Object clone() {
      Object var1 = null;

      try {
         return (Matrix4f)super.clone();
      } catch (CloneNotSupportedException var3) {
         throw new InternalError();
      }
   }

   public float getM31() {
      return this.m31;
   }

   public void setTranslation(Vector3f var1) {
      this.m03 = var1.x;
      this.m13 = var1.y;
      this.m23 = var1.z;
   }

   public void setM10(float var1) {
      this.m10 = var1;
   }

   public void transform(Tuple4f var1) {
      float var2 = this.m00 * var1.x + this.m01 * var1.y + this.m02 * var1.z + this.m03 * var1.w;
      float var3 = this.m10 * var1.x + this.m11 * var1.y + this.m12 * var1.z + this.m13 * var1.w;
      float var4 = this.m20 * var1.x + this.m21 * var1.y + this.m22 * var1.z + this.m23 * var1.w;
      var1.w = this.m30 * var1.x + this.m31 * var1.y + this.m32 * var1.z + this.m33 * var1.w;
      var1.x = var2;
      var1.y = var3;
      var1.z = var4;
   }

   public void setRow(int var1, float[] var2) {
      switch (var1) {
         case 0:
            this.m00 = var2[0];
            this.m01 = var2[1];
            this.m02 = var2[2];
            this.m03 = var2[3];
            break;
         case 1:
            this.m10 = var2[0];
            this.m11 = var2[1];
            this.m12 = var2[2];
            this.m13 = var2[3];
            break;
         case 2:
            this.m20 = var2[0];
            this.m21 = var2[1];
            this.m22 = var2[2];
            this.m23 = var2[3];
            break;
         case 3:
            this.m30 = var2[0];
            this.m31 = var2[1];
            this.m32 = var2[2];
            this.m33 = var2[3];
            break;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f6"));
      }
   }

   public void setColumn(int var1, Vector4f var2) {
      switch (var1) {
         case 0:
            this.m00 = var2.x;
            this.m10 = var2.y;
            this.m20 = var2.z;
            this.m30 = var2.w;
            break;
         case 1:
            this.m01 = var2.x;
            this.m11 = var2.y;
            this.m21 = var2.z;
            this.m31 = var2.w;
            break;
         case 2:
            this.m02 = var2.x;
            this.m12 = var2.y;
            this.m22 = var2.z;
            this.m32 = var2.w;
            break;
         case 3:
            this.m03 = var2.x;
            this.m13 = var2.y;
            this.m23 = var2.z;
            this.m33 = var2.w;
            break;
         default:
            throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f9"));
      }
   }

   public Matrix4f(Matrix3f var1, Vector3f var2, float var3) {
      this.m00 = var1.m00 * var3;
      this.m01 = var1.m01 * var3;
      this.m02 = var1.m02 * var3;
      this.m03 = var2.x;
      this.m10 = var1.m10 * var3;
      this.m11 = var1.m11 * var3;
      this.m12 = var1.m12 * var3;
      this.m13 = var2.y;
      this.m20 = var1.m20 * var3;
      this.m21 = var1.m21 * var3;
      this.m22 = var1.m22 * var3;
      this.m23 = var2.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void setM02(float var1) {
      this.m02 = var1;
   }

   public void setRotation(Quat4d var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      this.m00 = (float)((1.0 - 2.0 * var1.y * var1.y - 2.0 * var1.z * var1.z) * var3[0]);
      this.m10 = (float)(2.0 * (var1.x * var1.y + var1.w * var1.z) * var3[0]);
      this.m20 = (float)(2.0 * (var1.x * var1.z - var1.w * var1.y) * var3[0]);
      this.m01 = (float)(2.0 * (var1.x * var1.y - var1.w * var1.z) * var3[1]);
      this.m11 = (float)((1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.z * var1.z) * var3[1]);
      this.m21 = (float)(2.0 * (var1.y * var1.z + var1.w * var1.x) * var3[1]);
      this.m02 = (float)(2.0 * (var1.x * var1.z + var1.w * var1.y) * var3[2]);
      this.m12 = (float)(2.0 * (var1.y * var1.z - var1.w * var1.x) * var3[2]);
      this.m22 = (float)((1.0 - 2.0 * var1.x * var1.x - 2.0 * var1.y * var1.y) * var3[2]);
   }

   public float getElement(int var1, int var2) {
      switch (var1) {
         case 0:
            switch (var2) {
               case 0:
                  return this.m00;
               case 1:
                  return this.m01;
               case 2:
                  return this.m02;
               case 3:
                  return this.m03;
               default:
                  throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f1"));
            }
         case 1:
            switch (var2) {
               case 0:
                  return this.m10;
               case 1:
                  return this.m11;
               case 2:
                  return this.m12;
               case 3:
                  return this.m13;
               default:
                  throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f1"));
            }
         case 2:
            switch (var2) {
               case 0:
                  return this.m20;
               case 1:
                  return this.m21;
               case 2:
                  return this.m22;
               case 3:
                  return this.m23;
               default:
                  throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f1"));
            }
         case 3:
            switch (var2) {
               case 0:
                  return this.m30;
               case 1:
                  return this.m31;
               case 2:
                  return this.m32;
               case 3:
                  return this.m33;
            }
      }

      throw new ArrayIndexOutOfBoundsException(VecMathI18N.getString("Matrix4f1"));
   }

   @Override
   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m00);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m01);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m02);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m03);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m10);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m11);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m12);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m13);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m20);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m21);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m22);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m23);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m30);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m31);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m32);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.m33);
      return (int)(var1 ^ var1 >> 32);
   }

   public void transpose(Matrix4f var1) {
      if (this != var1) {
         this.m00 = var1.m00;
         this.m01 = var1.m10;
         this.m02 = var1.m20;
         this.m03 = var1.m30;
         this.m10 = var1.m01;
         this.m11 = var1.m11;
         this.m12 = var1.m21;
         this.m13 = var1.m31;
         this.m20 = var1.m02;
         this.m21 = var1.m12;
         this.m22 = var1.m22;
         this.m23 = var1.m32;
         this.m30 = var1.m03;
         this.m31 = var1.m13;
         this.m32 = var1.m23;
         this.m33 = var1.m33;
      } else {
         this.transpose();
      }
   }

   public void invertGeneral(Matrix4f var1) {
      double[] var2 = new double[16];
      double[] var3 = new double[16];
      int[] var4 = new int[4];
      var2[0] = var1.m00;
      var2[1] = var1.m01;
      var2[2] = var1.m02;
      var2[3] = var1.m03;
      var2[4] = var1.m10;
      var2[5] = var1.m11;
      var2[6] = var1.m12;
      var2[7] = var1.m13;
      var2[8] = var1.m20;
      var2[9] = var1.m21;
      var2[10] = var1.m22;
      var2[11] = var1.m23;
      var2[12] = var1.m30;
      var2[13] = var1.m31;
      var2[14] = var1.m32;
      var2[15] = var1.m33;
      if (!luDecomposition(var2, var4)) {
         throw new SingularMatrixException(VecMathI18N.getString("Matrix4f12"));
      } else {
         for (int var5 = 0; var5 < 16; var5++) {
            var3[var5] = 0.0;
         }

         var3[0] = 1.0;
         var3[5] = 1.0;
         var3[10] = 1.0;
         var3[15] = 1.0;
         luBacksubstitution(var2, var4, var3);
         this.m00 = (float)var3[0];
         this.m01 = (float)var3[1];
         this.m02 = (float)var3[2];
         this.m03 = (float)var3[3];
         this.m10 = (float)var3[4];
         this.m11 = (float)var3[5];
         this.m12 = (float)var3[6];
         this.m13 = (float)var3[7];
         this.m20 = (float)var3[8];
         this.m21 = (float)var3[9];
         this.m22 = (float)var3[10];
         this.m23 = (float)var3[11];
         this.m30 = (float)var3[12];
         this.m31 = (float)var3[13];
         this.m32 = (float)var3[14];
         this.m33 = (float)var3[15];
      }
   }

   public void transform(Point3f var1) {
      float var2 = this.m00 * var1.x + this.m01 * var1.y + this.m02 * var1.z + this.m03;
      float var3 = this.m10 * var1.x + this.m11 * var1.y + this.m12 * var1.z + this.m13;
      var1.z = this.m20 * var1.x + this.m21 * var1.y + this.m22 * var1.z + this.m23;
      var1.x = var2;
      var1.y = var3;
   }

   public void mulTransposeBoth(Matrix4f var1, Matrix4f var2) {
      if (this != var1 && this != var2) {
         this.m00 = var1.m00 * var2.m00 + var1.m10 * var2.m01 + var1.m20 * var2.m02 + var1.m30 * var2.m03;
         this.m01 = var1.m00 * var2.m10 + var1.m10 * var2.m11 + var1.m20 * var2.m12 + var1.m30 * var2.m13;
         this.m02 = var1.m00 * var2.m20 + var1.m10 * var2.m21 + var1.m20 * var2.m22 + var1.m30 * var2.m23;
         this.m03 = var1.m00 * var2.m30 + var1.m10 * var2.m31 + var1.m20 * var2.m32 + var1.m30 * var2.m33;
         this.m10 = var1.m01 * var2.m00 + var1.m11 * var2.m01 + var1.m21 * var2.m02 + var1.m31 * var2.m03;
         this.m11 = var1.m01 * var2.m10 + var1.m11 * var2.m11 + var1.m21 * var2.m12 + var1.m31 * var2.m13;
         this.m12 = var1.m01 * var2.m20 + var1.m11 * var2.m21 + var1.m21 * var2.m22 + var1.m31 * var2.m23;
         this.m13 = var1.m01 * var2.m30 + var1.m11 * var2.m31 + var1.m21 * var2.m32 + var1.m31 * var2.m33;
         this.m20 = var1.m02 * var2.m00 + var1.m12 * var2.m01 + var1.m22 * var2.m02 + var1.m32 * var2.m03;
         this.m21 = var1.m02 * var2.m10 + var1.m12 * var2.m11 + var1.m22 * var2.m12 + var1.m32 * var2.m13;
         this.m22 = var1.m02 * var2.m20 + var1.m12 * var2.m21 + var1.m22 * var2.m22 + var1.m32 * var2.m23;
         this.m23 = var1.m02 * var2.m30 + var1.m12 * var2.m31 + var1.m22 * var2.m32 + var1.m32 * var2.m33;
         this.m30 = var1.m03 * var2.m00 + var1.m13 * var2.m01 + var1.m23 * var2.m02 + var1.m33 * var2.m03;
         this.m31 = var1.m03 * var2.m10 + var1.m13 * var2.m11 + var1.m23 * var2.m12 + var1.m33 * var2.m13;
         this.m32 = var1.m03 * var2.m20 + var1.m13 * var2.m21 + var1.m23 * var2.m22 + var1.m33 * var2.m23;
         this.m33 = var1.m03 * var2.m30 + var1.m13 * var2.m31 + var1.m23 * var2.m32 + var1.m33 * var2.m33;
      } else {
         float var3 = var1.m00 * var2.m00 + var1.m10 * var2.m01 + var1.m20 * var2.m02 + var1.m30 * var2.m03;
         float var4 = var1.m00 * var2.m10 + var1.m10 * var2.m11 + var1.m20 * var2.m12 + var1.m30 * var2.m13;
         float var5 = var1.m00 * var2.m20 + var1.m10 * var2.m21 + var1.m20 * var2.m22 + var1.m30 * var2.m23;
         float var6 = var1.m00 * var2.m30 + var1.m10 * var2.m31 + var1.m20 * var2.m32 + var1.m30 * var2.m33;
         float var7 = var1.m01 * var2.m00 + var1.m11 * var2.m01 + var1.m21 * var2.m02 + var1.m31 * var2.m03;
         float var8 = var1.m01 * var2.m10 + var1.m11 * var2.m11 + var1.m21 * var2.m12 + var1.m31 * var2.m13;
         float var9 = var1.m01 * var2.m20 + var1.m11 * var2.m21 + var1.m21 * var2.m22 + var1.m31 * var2.m23;
         float var10 = var1.m01 * var2.m30 + var1.m11 * var2.m31 + var1.m21 * var2.m32 + var1.m31 * var2.m33;
         float var11 = var1.m02 * var2.m00 + var1.m12 * var2.m01 + var1.m22 * var2.m02 + var1.m32 * var2.m03;
         float var12 = var1.m02 * var2.m10 + var1.m12 * var2.m11 + var1.m22 * var2.m12 + var1.m32 * var2.m13;
         float var13 = var1.m02 * var2.m20 + var1.m12 * var2.m21 + var1.m22 * var2.m22 + var1.m32 * var2.m23;
         float var14 = var1.m02 * var2.m30 + var1.m12 * var2.m31 + var1.m22 * var2.m32 + var1.m32 * var2.m33;
         float var15 = var1.m03 * var2.m00 + var1.m13 * var2.m01 + var1.m23 * var2.m02 + var1.m33 * var2.m03;
         float var16 = var1.m03 * var2.m10 + var1.m13 * var2.m11 + var1.m23 * var2.m12 + var1.m33 * var2.m13;
         float var17 = var1.m03 * var2.m20 + var1.m13 * var2.m21 + var1.m23 * var2.m22 + var1.m33 * var2.m23;
         float var18 = var1.m03 * var2.m30 + var1.m13 * var2.m31 + var1.m23 * var2.m32 + var1.m33 * var2.m33;
         this.m00 = var3;
         this.m01 = var4;
         this.m02 = var5;
         this.m03 = var6;
         this.m10 = var7;
         this.m11 = var8;
         this.m12 = var9;
         this.m13 = var10;
         this.m20 = var11;
         this.m21 = var12;
         this.m22 = var13;
         this.m23 = var14;
         this.m30 = var15;
         this.m31 = var16;
         this.m32 = var17;
         this.m33 = var18;
      }
   }

   public static boolean luDecomposition(double[] var0, int[] var1) {
      double[] var2 = new double[4];
      int var5 = 0;
      int var6 = 0;
      int var3 = 4;

      while (var3-- != 0) {
         double var7 = 0.0;
         int var4 = 4;

         while (var4-- != 0) {
            double var9 = var0[var5++];
            var9 = Math.abs(var9);
            if (var9 > var7) {
               var7 = var9;
            }
         }

         if (var7 == 0.0) {
            return false;
         }

         var2[var6++] = 1.0 / var7;
      }

      byte var18 = 0;

      for (int var17 = 0; var17 < 4; var17++) {
         for (int var19 = 0; var19 < var17; var19++) {
            int var8 = var18 + 4 * var19 + var17;
            double var11 = var0[var8];
            int var23 = var19;
            int var29 = var18 + 4 * var19;

            for (int var10 = var18 + var17; var23-- != 0; var10 += 4) {
               var11 -= var0[var29] * var0[var10];
               var29++;
            }

            var0[var8] = var11;
         }

         double var13 = 0.0;
         var6 = -1;

         for (int var20 = var17; var20 < 4; var20++) {
            int var26 = var18 + 4 * var20 + var17;
            double var34 = var0[var26];
            int var24 = var17;
            int var30 = var18 + 4 * var20;

            for (int var32 = var18 + var17; var24-- != 0; var32 += 4) {
               var34 -= var0[var30] * var0[var32];
               var30++;
            }

            var0[var26] = var34;
            double var15;
            if ((var15 = var2[var20] * Math.abs(var34)) >= var13) {
               var13 = var15;
               var6 = var20;
            }
         }

         if (var6 < 0) {
            throw new RuntimeException(VecMathI18N.getString("Matrix4f13"));
         }

         if (var17 != var6) {
            int var25 = 4;
            int var31 = var18 + 4 * var6;
            int var33 = var18 + 4 * var17;

            while (var25-- != 0) {
               double var35 = var0[var31];
               var0[var31++] = var0[var33];
               var0[var33++] = var35;
            }

            var2[var6] = var2[var17];
         }

         var1[var17] = var6;
         if (var0[var18 + 4 * var17 + var17] == 0.0) {
            return false;
         }

         if (var17 != 3) {
            double var36 = 1.0 / var0[var18 + 4 * var17 + var17];
            int var27 = var18 + 4 * (var17 + 1) + var17;

            for (int var21 = 3 - var17; var21-- != 0; var27 += 4) {
               var0[var27] *= var36;
            }
         }
      }

      return true;
   }

   public void getScaleRotate(double[] var1, double[] var2) {
      double[] var3 = new double[]{this.m00, this.m01, this.m02, this.m10, this.m11, this.m12, this.m20, this.m21, this.m22};
      Matrix3d.compute_svd(var3, var1, var2);
   }

   public void mul(float var1, Matrix4f var2) {
      this.m00 = var2.m00 * var1;
      this.m01 = var2.m01 * var1;
      this.m02 = var2.m02 * var1;
      this.m03 = var2.m03 * var1;
      this.m10 = var2.m10 * var1;
      this.m11 = var2.m11 * var1;
      this.m12 = var2.m12 * var1;
      this.m13 = var2.m13 * var1;
      this.m20 = var2.m20 * var1;
      this.m21 = var2.m21 * var1;
      this.m22 = var2.m22 * var1;
      this.m23 = var2.m23 * var1;
      this.m30 = var2.m30 * var1;
      this.m31 = var2.m31 * var1;
      this.m32 = var2.m32 * var1;
      this.m33 = var2.m33 * var1;
   }

   public void get(Matrix3f var1) {
      double[] var2 = new double[9];
      double[] var3 = new double[3];
      this.getScaleRotate(var3, var2);
      var1.m00 = (float)var2[0];
      var1.m01 = (float)var2[1];
      var1.m02 = (float)var2[2];
      var1.m10 = (float)var2[3];
      var1.m11 = (float)var2[4];
      var1.m12 = (float)var2[5];
      var1.m20 = (float)var2[6];
      var1.m21 = (float)var2[7];
      var1.m22 = (float)var2[8];
   }

   public void get(Vector3f var1) {
      var1.x = this.m03;
      var1.y = this.m13;
      var1.z = this.m23;
   }

   public float getM22() {
      return this.m22;
   }

   public float getM30() {
      return this.m30;
   }

   public void rotX(float var1) {
      float var2 = (float)Math.sin(var1);
      float var3 = (float)Math.cos(var1);
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = 0.0F;
      this.m10 = 0.0F;
      this.m11 = var3;
      this.m12 = -var2;
      this.m13 = 0.0F;
      this.m20 = 0.0F;
      this.m21 = var2;
      this.m22 = var3;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public float getM01() {
      return this.m01;
   }

   public boolean epsilonEquals(Matrix4f var1, float var2) {
      boolean var3 = true;
      if (Math.abs(this.m00 - var1.m00) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m01 - var1.m01) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m02 - var1.m02) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m03 - var1.m03) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m10 - var1.m10) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m11 - var1.m11) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m12 - var1.m12) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m13 - var1.m13) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m20 - var1.m20) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m21 - var1.m21) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m22 - var1.m22) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m23 - var1.m23) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m30 - var1.m30) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m31 - var1.m31) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m32 - var1.m32) > var2) {
         var3 = false;
      }

      if (Math.abs(this.m33 - var1.m33) > var2) {
         var3 = false;
      }

      return var3;
   }

   public void set(Quat4f var1) {
      this.m00 = 1.0F - 2.0F * var1.y * var1.y - 2.0F * var1.z * var1.z;
      this.m10 = 2.0F * (var1.x * var1.y + var1.w * var1.z);
      this.m20 = 2.0F * (var1.x * var1.z - var1.w * var1.y);
      this.m01 = 2.0F * (var1.x * var1.y - var1.w * var1.z);
      this.m11 = 1.0F - 2.0F * var1.x * var1.x - 2.0F * var1.z * var1.z;
      this.m21 = 2.0F * (var1.y * var1.z + var1.w * var1.x);
      this.m02 = 2.0F * (var1.x * var1.z + var1.w * var1.y);
      this.m12 = 2.0F * (var1.y * var1.z - var1.w * var1.x);
      this.m22 = 1.0F - 2.0F * var1.x * var1.x - 2.0F * var1.y * var1.y;
      this.m03 = 0.0F;
      this.m13 = 0.0F;
      this.m23 = 0.0F;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void set(float var1, Vector3f var2) {
      this.m00 = var1;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = var2.x;
      this.m10 = 0.0F;
      this.m11 = var1;
      this.m12 = 0.0F;
      this.m13 = var2.y;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = var1;
      this.m23 = var2.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void set(Vector3f var1) {
      this.m00 = 1.0F;
      this.m01 = 0.0F;
      this.m02 = 0.0F;
      this.m03 = var1.x;
      this.m10 = 0.0F;
      this.m11 = 1.0F;
      this.m12 = 0.0F;
      this.m13 = var1.y;
      this.m20 = 0.0F;
      this.m21 = 0.0F;
      this.m22 = 1.0F;
      this.m23 = var1.z;
      this.m30 = 0.0F;
      this.m31 = 0.0F;
      this.m32 = 0.0F;
      this.m33 = 1.0F;
   }

   public void setM01(float var1) {
      this.m01 = var1;
   }

   public void set(Matrix4d var1) {
      this.m00 = (float)var1.m00;
      this.m01 = (float)var1.m01;
      this.m02 = (float)var1.m02;
      this.m03 = (float)var1.m03;
      this.m10 = (float)var1.m10;
      this.m11 = (float)var1.m11;
      this.m12 = (float)var1.m12;
      this.m13 = (float)var1.m13;
      this.m20 = (float)var1.m20;
      this.m21 = (float)var1.m21;
      this.m22 = (float)var1.m22;
      this.m23 = (float)var1.m23;
      this.m30 = (float)var1.m30;
      this.m31 = (float)var1.m31;
      this.m32 = (float)var1.m32;
      this.m33 = (float)var1.m33;
   }

   public float getM00() {
      return this.m00;
   }

   public boolean equals(Matrix4f var1) {
      try {
         return this.m00 == var1.m00
            && this.m01 == var1.m01
            && this.m02 == var1.m02
            && this.m03 == var1.m03
            && this.m10 == var1.m10
            && this.m11 == var1.m11
            && this.m12 == var1.m12
            && this.m13 == var1.m13
            && this.m20 == var1.m20
            && this.m21 == var1.m21
            && this.m22 == var1.m22
            && this.m23 == var1.m23
            && this.m30 == var1.m30
            && this.m31 == var1.m31
            && this.m32 == var1.m32
            && this.m33 == var1.m33;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public float getScale() {
      double[] var1 = new double[9];
      double[] var2 = new double[3];
      this.getScaleRotate(var2, var1);
      return (float)Matrix3d.max3(var2);
   }
}
