package javax.vecmath;

import java.io.Serializable;
import javax.vecmath.VecMathUtil;

public abstract class Tuple4f implements Serializable, Cloneable {
   public float z;
   public float x;
   public static final long recoveredField3668 = 7068460319248845763L;
   public float w;
   public float y;

   public float getZ() {
      return this.z;
   }

   public Tuple4f(float[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public void set(float var1, float var2, float var3, float var4) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      this.w = var4;
   }

   public void set(float[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public void scale(float var1) {
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
      this.w *= var1;
   }

   public void scaleAdd(float var1, Tuple4f var2) {
      this.x = var1 * this.x + var2.x;
      this.y = var1 * this.y + var2.y;
      this.z = var1 * this.z + var2.z;
      this.w = var1 * this.w + var2.w;
   }

   public void setZ(float var1) {
      this.z = var1;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         Tuple4f var2 = (Tuple4f)var1;
         return this.x == var2.x && this.y == var2.y && this.z == var2.z && this.w == var2.w;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   @Override
   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.x);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.y);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.z);
      var1 = 31L * var1 + VecMathUtil.floatToIntBits(this.w);
      return (int)(var1 ^ var1 >> 32);
   }

   public float getY() {
      return this.y;
   }

   public void setX(float var1) {
      this.x = var1;
   }

   public void interpolate(Tuple4f var1, Tuple4f var2, float var3) {
      this.x = (1.0F - var3) * var1.x + var3 * var2.x;
      this.y = (1.0F - var3) * var1.y + var3 * var2.y;
      this.z = (1.0F - var3) * var1.z + var3 * var2.z;
      this.w = (1.0F - var3) * var1.w + var3 * var2.w;
   }

   public void absolute() {
      this.x = Math.abs(this.x);
      this.y = Math.abs(this.y);
      this.z = Math.abs(this.z);
      this.w = Math.abs(this.w);
   }

   public void negate(Tuple4f var1) {
      this.x = -var1.x;
      this.y = -var1.y;
      this.z = -var1.z;
      this.w = -var1.w;
   }

   public void scaleAdd(float var1, Tuple4f var2, Tuple4f var3) {
      this.x = var1 * var2.x + var3.x;
      this.y = var1 * var2.y + var3.y;
      this.z = var1 * var2.z + var3.z;
      this.w = var1 * var2.w + var3.w;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public void clamp(float var1, float var2, Tuple4f var3) {
      if (var3.x > var2) {
         this.x = var2;
      } else if (var3.x < var1) {
         this.x = var1;
      } else {
         this.x = var3.x;
      }

      if (var3.y > var2) {
         this.y = var2;
      } else if (var3.y < var1) {
         this.y = var1;
      } else {
         this.y = var3.y;
      }

      if (var3.z > var2) {
         this.z = var2;
      } else if (var3.z < var1) {
         this.z = var1;
      } else {
         this.z = var3.z;
      }

      if (var3.w > var2) {
         this.w = var2;
      } else if (var3.w < var1) {
         this.w = var1;
      } else {
         this.w = var3.w;
      }
   }

   public void get(float[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
      var1[2] = this.z;
      var1[3] = this.w;
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   public void sub(Tuple4f var1) {
      this.x = this.x - var1.x;
      this.y = this.y - var1.y;
      this.z = this.z - var1.z;
      this.w = this.w - var1.w;
   }

   public void absolute(Tuple4f var1) {
      this.x = Math.abs(var1.x);
      this.y = Math.abs(var1.y);
      this.z = Math.abs(var1.z);
      this.w = Math.abs(var1.w);
   }

   public boolean epsilonEquals(Tuple4f var1, float var2) {
      float var3 = this.x - var1.x;
      if (Float.isNaN(var3)) {
         return false;
      } else if ((var3 < 0.0F ? -var3 : var3) > var2) {
         return false;
      } else {
         var3 = this.y - var1.y;
         if (Float.isNaN(var3)) {
            return false;
         } else if ((var3 < 0.0F ? -var3 : var3) > var2) {
            return false;
         } else {
            var3 = this.z - var1.z;
            if (Float.isNaN(var3)) {
               return false;
            } else if ((var3 < 0.0F ? -var3 : var3) > var2) {
               return false;
            } else {
               var3 = this.w - var1.w;
               return Float.isNaN(var3) ? false : !((var3 < 0.0F ? -var3 : var3) > var2);
            }
         }
      }
   }

   public void scale(float var1, Tuple4f var2) {
      this.x = var1 * var2.x;
      this.y = var1 * var2.y;
      this.z = var1 * var2.z;
      this.w = var1 * var2.w;
   }

   public void setY(float var1) {
      this.y = var1;
   }

   public void set(Tuple4d var1) {
      this.x = (float)var1.x;
      this.y = (float)var1.y;
      this.z = (float)var1.z;
      this.w = (float)var1.w;
   }

   public float getX() {
      return this.x;
   }

   public void sub(Tuple4f var1, Tuple4f var2) {
      this.x = var1.x - var2.x;
      this.y = var1.y - var2.y;
      this.z = var1.z - var2.z;
      this.w = var1.w - var2.w;
   }

   public void clampMax(float var1, Tuple4f var2) {
      if (var2.x > var1) {
         this.x = var1;
      } else {
         this.x = var2.x;
      }

      if (var2.y > var1) {
         this.y = var1;
      } else {
         this.y = var2.y;
      }

      if (var2.z > var1) {
         this.z = var1;
      } else {
         this.z = var2.z;
      }

      if (var2.w > var1) {
         this.w = var1;
      } else {
         this.w = var2.z;
      }
   }

   public Tuple4f(float var1, float var2, float var3, float var4) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      this.w = var4;
   }

   public float getW() {
      return this.w;
   }

   public void interpolate(Tuple4f var1, float var2) {
      this.x = (1.0F - var2) * this.x + var2 * var1.x;
      this.y = (1.0F - var2) * this.y + var2 * var1.y;
      this.z = (1.0F - var2) * this.z + var2 * var1.z;
      this.w = (1.0F - var2) * this.w + var2 * var1.w;
   }

   public Tuple4f(Tuple4d var1) {
      this.x = (float)var1.x;
      this.y = (float)var1.y;
      this.z = (float)var1.z;
      this.w = (float)var1.w;
   }

   public void clampMin(float var1, Tuple4f var2) {
      if (var2.x < var1) {
         this.x = var1;
      } else {
         this.x = var2.x;
      }

      if (var2.y < var1) {
         this.y = var1;
      } else {
         this.y = var2.y;
      }

      if (var2.z < var1) {
         this.z = var1;
      } else {
         this.z = var2.z;
      }

      if (var2.w < var1) {
         this.w = var1;
      } else {
         this.w = var2.w;
      }
   }

   public void get(Tuple4f var1) {
      var1.x = this.x;
      var1.y = this.y;
      var1.z = this.z;
      var1.w = this.w;
   }

   public Tuple4f() {
      this.x = 0.0F;
      this.y = 0.0F;
      this.z = 0.0F;
      this.w = 0.0F;
   }

   public void add(Tuple4f var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
      this.z = this.z + var1.z;
      this.w = this.w + var1.w;
   }

   public void add(Tuple4f var1, Tuple4f var2) {
      this.x = var1.x + var2.x;
      this.y = var1.y + var2.y;
      this.z = var1.z + var2.z;
      this.w = var1.w + var2.w;
   }

   public boolean equals(Tuple4f var1) {
      try {
         return this.x == var1.x && this.y == var1.y && this.z == var1.z && this.w == var1.w;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public void clamp(float var1, float var2) {
      if (this.x > var2) {
         this.x = var2;
      } else if (this.x < var1) {
         this.x = var1;
      }

      if (this.y > var2) {
         this.y = var2;
      } else if (this.y < var1) {
         this.y = var1;
      }

      if (this.z > var2) {
         this.z = var2;
      } else if (this.z < var1) {
         this.z = var1;
      }

      if (this.w > var2) {
         this.w = var2;
      } else if (this.w < var1) {
         this.w = var1;
      }
   }

   public void set(Tuple4f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public Tuple4f(Tuple4f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public void clampMin(float var1) {
      if (this.x < var1) {
         this.x = var1;
      }

      if (this.y < var1) {
         this.y = var1;
      }

      if (this.z < var1) {
         this.z = var1;
      }

      if (this.w < var1) {
         this.w = var1;
      }
   }

   public void setW(float var1) {
      this.w = var1;
   }

   public void clampMax(float var1) {
      if (this.x > var1) {
         this.x = var1;
      }

      if (this.y > var1) {
         this.y = var1;
      }

      if (this.z > var1) {
         this.z = var1;
      }

      if (this.w > var1) {
         this.w = var1;
      }
   }

   public void negate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
      this.w = -this.w;
   }
}
