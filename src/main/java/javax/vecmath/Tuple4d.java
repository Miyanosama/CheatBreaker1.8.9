package javax.vecmath;

import java.io.Serializable;
import javax.vecmath.VecMathUtil;

public abstract class Tuple4d implements Serializable, Cloneable {
   public double x;
   public static final long recoveredField2742 = -4748953690425311052L;
   public double z;
   public double w;
   public double y;

   public void scaleAdd(double var1, Tuple4d var3) {
      this.x = var1 * this.x + var3.x;
      this.y = var1 * this.y + var3.y;
      this.z = var1 * this.z + var3.z;
      this.w = var1 * this.w + var3.w;
   }

   public void add(Tuple4d var1, Tuple4d var2) {
      this.x = var1.x + var2.x;
      this.y = var1.y + var2.y;
      this.z = var1.z + var2.z;
      this.w = var1.w + var2.w;
   }

   public void sub(Tuple4d var1) {
      this.x = this.x - var1.x;
      this.y = this.y - var1.y;
      this.z = this.z - var1.z;
      this.w = this.w - var1.w;
   }

   public void setX(double var1) {
      this.x = var1;
   }

   public void set(double var1, double var3, double var5, double var7) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
      this.w = var7;
   }

   public void scaleAdd(double var1, Tuple4d var3, Tuple4d var4) {
      this.x = var1 * var3.x + var4.x;
      this.y = var1 * var3.y + var4.y;
      this.z = var1 * var3.z + var4.z;
      this.w = var1 * var3.w + var4.w;
   }

   public void interpolate(Tuple4d var1, Tuple4d var2, float var3) {
      this.interpolate(var1, var2, (double)var3);
   }

   @Override
   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.x);
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.y);
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.z);
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.w);
      return (int)(var1 ^ var1 >> 32);
   }

   public void get(double[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
      var1[2] = this.z;
      var1[3] = this.w;
   }

   public void add(Tuple4d var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
      this.z = this.z + var1.z;
      this.w = this.w + var1.w;
   }

   public void absolute(Tuple4d var1) {
      this.x = Math.abs(var1.x);
      this.y = Math.abs(var1.y);
      this.z = Math.abs(var1.z);
      this.w = Math.abs(var1.w);
   }

   public void clampMin(double var1) {
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

   public void scale(double var1, Tuple4d var3) {
      this.x = var1 * var3.x;
      this.y = var1 * var3.y;
      this.z = var1 * var3.z;
      this.w = var1 * var3.w;
   }

   public void interpolate(Tuple4d var1, Tuple4d var2, double var3) {
      this.x = (1.0 - var3) * var1.x + var3 * var2.x;
      this.y = (1.0 - var3) * var1.y + var3 * var2.y;
      this.z = (1.0 - var3) * var1.z + var3 * var2.z;
      this.w = (1.0 - var3) * var1.w + var3 * var2.w;
   }

   public void method_23746(float var1) {
      this.clampMin(var1);
   }

   public void setW(double var1) {
      this.w = var1;
   }

   public void method_23765(float var1) {
      this.clampMax(var1);
   }

   public void sub(Tuple4d var1, Tuple4d var2) {
      this.x = var1.x - var2.x;
      this.y = var1.y - var2.y;
      this.z = var1.z - var2.z;
      this.w = var1.w - var2.w;
   }

   public double getW() {
      return this.w;
   }

   public void method_23749(float var1, Tuple4d var2) {
      this.clampMax(var1, var2);
   }

   public boolean epsilonEquals(Tuple4d var1, double var2) {
      double var4 = this.x - var1.x;
      if (Double.isNaN(var4)) {
         return false;
      } else if ((var4 < 0.0 ? -var4 : var4) > var2) {
         return false;
      } else {
         var4 = this.y - var1.y;
         if (Double.isNaN(var4)) {
            return false;
         } else if ((var4 < 0.0 ? -var4 : var4) > var2) {
            return false;
         } else {
            var4 = this.z - var1.z;
            if (Double.isNaN(var4)) {
               return false;
            } else if ((var4 < 0.0 ? -var4 : var4) > var2) {
               return false;
            } else {
               var4 = this.w - var1.w;
               return Double.isNaN(var4) ? false : !((var4 < 0.0 ? -var4 : var4) > var2);
            }
         }
      }
   }

   public Tuple4d(double var1, double var3, double var5, double var7) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
      this.w = var7;
   }

   public void absolute() {
      this.x = Math.abs(this.x);
      this.y = Math.abs(this.y);
      this.z = Math.abs(this.z);
      this.w = Math.abs(this.w);
   }

   public void method_23766(float var1, Tuple4d var2) {
      this.scaleAdd(var1, var2);
   }

   public double getY() {
      return this.y;
   }

   public Tuple4d(Tuple4f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public void interpolate(Tuple4d var1, double var2) {
      this.x = (1.0 - var2) * this.x + var2 * var1.x;
      this.y = (1.0 - var2) * this.y + var2 * var1.y;
      this.z = (1.0 - var2) * this.z + var2 * var1.z;
      this.w = (1.0 - var2) * this.w + var2 * var1.w;
   }

   public double getZ() {
      return this.z;
   }

   public void negate(Tuple4d var1) {
      this.x = -var1.x;
      this.y = -var1.y;
      this.z = -var1.z;
      this.w = -var1.w;
   }

   public void clampMin(double var1, Tuple4d var3) {
      if (var3.x < var1) {
         this.x = var1;
      } else {
         this.x = var3.x;
      }

      if (var3.y < var1) {
         this.y = var1;
      } else {
         this.y = var3.y;
      }

      if (var3.z < var1) {
         this.z = var1;
      } else {
         this.z = var3.z;
      }

      if (var3.w < var1) {
         this.w = var1;
      } else {
         this.w = var3.w;
      }
   }

   public void clamp(double var1, double var3, Tuple4d var5) {
      if (var5.x > var3) {
         this.x = var3;
      } else if (var5.x < var1) {
         this.x = var1;
      } else {
         this.x = var5.x;
      }

      if (var5.y > var3) {
         this.y = var3;
      } else if (var5.y < var1) {
         this.y = var1;
      } else {
         this.y = var5.y;
      }

      if (var5.z > var3) {
         this.z = var3;
      } else if (var5.z < var1) {
         this.z = var1;
      } else {
         this.z = var5.z;
      }

      if (var5.w > var3) {
         this.w = var3;
      } else if (var5.w < var1) {
         this.w = var1;
      } else {
         this.w = var5.w;
      }
   }

   public void setZ(double var1) {
      this.z = var1;
   }

   public void clamp(float var1, float var2) {
      this.clamp((double)var1, (double)var2);
   }

   public void scale(double var1) {
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
      this.w *= var1;
   }

   public void clampMax(double var1) {
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

   @Override
   public boolean equals(Object var1) {
      try {
         Tuple4d var2 = (Tuple4d)var1;
         return this.x == var2.x && this.y == var2.y && this.z == var2.z && this.w == var2.w;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public void clamp(double var1, double var3) {
      if (this.x > var3) {
         this.x = var3;
      } else if (this.x < var1) {
         this.x = var1;
      }

      if (this.y > var3) {
         this.y = var3;
      } else if (this.y < var1) {
         this.y = var1;
      }

      if (this.z > var3) {
         this.z = var3;
      } else if (this.z < var1) {
         this.z = var1;
      }

      if (this.w > var3) {
         this.w = var3;
      } else if (this.w < var1) {
         this.w = var1;
      }
   }

   public void interpolate(Tuple4d var1, float var2) {
      this.interpolate(var1, (double)var2);
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   public void clamp(float var1, float var2, Tuple4d var3) {
      this.clamp((double)var1, (double)var2, var3);
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public void get(Tuple4d var1) {
      var1.x = this.x;
      var1.y = this.y;
      var1.z = this.z;
      var1.w = this.w;
   }

   public Tuple4d(Tuple4d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public void set(Tuple4f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public void method_23735(float var1, Tuple4d var2) {
      this.clampMin(var1, var2);
   }

   public boolean equals(Tuple4d var1) {
      try {
         return this.x == var1.x && this.y == var1.y && this.z == var1.z && this.w == var1.w;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public double getX() {
      return this.x;
   }

   public Tuple4d(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public void set(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public void clampMax(double var1, Tuple4d var3) {
      if (var3.x > var1) {
         this.x = var1;
      } else {
         this.x = var3.x;
      }

      if (var3.y > var1) {
         this.y = var1;
      } else {
         this.y = var3.y;
      }

      if (var3.z > var1) {
         this.z = var1;
      } else {
         this.z = var3.z;
      }

      if (var3.w > var1) {
         this.w = var1;
      } else {
         this.w = var3.z;
      }
   }

   public void set(Tuple4d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public void setY(double var1) {
      this.y = var1;
   }

   public Tuple4d() {
      this.x = 0.0;
      this.y = 0.0;
      this.z = 0.0;
      this.w = 0.0;
   }

   public void negate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
      this.w = -this.w;
   }
}
