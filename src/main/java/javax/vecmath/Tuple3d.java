package javax.vecmath;

import java.io.Serializable;
import javax.vecmath.VecMathUtil;

public abstract class Tuple3d implements Serializable, Cloneable {
   public double z;
   public static final long recoveredField147 = 5542096614926168415L;
   public double x;
   public double y;

   public void setZ(double var1) {
      this.z = var1;
   }

   public void clamp(double var1, double var3, Tuple3d var5) {
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
   }

   public void method_23517(float var1) {
      this.clampMin(var1);
   }

   public void get(double[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
      var1[2] = this.z;
   }

   public void clamp(float var1, float var2, Tuple3d var3) {
      this.clamp((double)var1, (double)var2, var3);
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
   }

   public Tuple3d(double var1, double var3, double var5) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
   }

   public Tuple3d(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
   }

   public void get(Tuple3d var1) {
      var1.x = this.x;
      var1.y = this.y;
      var1.z = this.z;
   }

   public void scale(double var1, Tuple3d var3) {
      this.x = var1 * var3.x;
      this.y = var1 * var3.y;
      this.z = var1 * var3.z;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         Tuple3d var2 = (Tuple3d)var1;
         return this.x == var2.x && this.y == var2.y && this.z == var2.z;
      } catch (ClassCastException var3) {
         return false;
      } catch (NullPointerException var4) {
         return false;
      }
   }

   public void setY(double var1) {
      this.y = var1;
   }

   public void scaleAdd(double var1, Tuple3f var3) {
      this.scaleAdd(var1, new Point3d(var3));
   }

   public void method_23536(float var1) {
      this.clampMax(var1);
   }

   public void set(double var1, double var3, double var5) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
   }

   public void absolute(Tuple3d var1) {
      this.x = Math.abs(var1.x);
      this.y = Math.abs(var1.y);
      this.z = Math.abs(var1.z);
   }

   public void set(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
   }

   public void setX(double var1) {
      this.x = var1;
   }

   public void sub(Tuple3d var1, Tuple3d var2) {
      this.x = var1.x - var2.x;
      this.y = var1.y - var2.y;
      this.z = var1.z - var2.z;
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
   }

   public void add(Tuple3d var1, Tuple3d var2) {
      this.x = var1.x + var2.x;
      this.y = var1.y + var2.y;
      this.z = var1.z + var2.z;
   }

   public void interpolate(Tuple3d var1, float var2) {
      this.interpolate(var1, (double)var2);
   }

   public Tuple3d(Tuple3f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public void add(Tuple3d var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
      this.z = this.z + var1.z;
   }

   public void scaleAdd(double var1, Tuple3d var3, Tuple3d var4) {
      this.x = var1 * var3.x + var4.x;
      this.y = var1 * var3.y + var4.y;
      this.z = var1 * var3.z + var4.z;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
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
   }

   public void negate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
   }

   public boolean equals(Tuple3d var1) {
      try {
         return this.x == var1.x && this.y == var1.y && this.z == var1.z;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public void set(Tuple3f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public void interpolate(Tuple3d var1, Tuple3d var2, double var3) {
      this.x = (1.0 - var3) * var1.x + var3 * var2.x;
      this.y = (1.0 - var3) * var1.y + var3 * var2.y;
      this.z = (1.0 - var3) * var1.z + var3 * var2.z;
   }

   public void clampMax(double var1, Tuple3d var3) {
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
   }

   public boolean epsilonEquals(Tuple3d var1, double var2) {
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
            return Double.isNaN(var4) ? false : !((var4 < 0.0 ? -var4 : var4) > var2);
         }
      }
   }

   public void negate(Tuple3d var1) {
      this.x = -var1.x;
      this.y = -var1.y;
      this.z = -var1.z;
   }

   public double getX() {
      return this.x;
   }

   public void sub(Tuple3d var1) {
      this.x = this.x - var1.x;
      this.y = this.y - var1.y;
      this.z = this.z - var1.z;
   }

   public void clampMin(double var1, Tuple3d var3) {
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
   }

   public Tuple3d() {
      this.x = 0.0;
      this.y = 0.0;
      this.z = 0.0;
   }

   @Override
   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.x);
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.y);
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.z);
      return (int)(var1 ^ var1 >> 32);
   }

   public void scaleAdd(double var1, Tuple3d var3) {
      this.x = var1 * this.x + var3.x;
      this.y = var1 * this.y + var3.y;
      this.z = var1 * this.z + var3.z;
   }

   public void interpolate(Tuple3d var1, Tuple3d var2, float var3) {
      this.interpolate(var1, var2, (double)var3);
   }

   public double getY() {
      return this.y;
   }

   public void absolute() {
      this.x = Math.abs(this.x);
      this.y = Math.abs(this.y);
      this.z = Math.abs(this.z);
   }

   public void method_23520(float var1, Tuple3d var2) {
      this.clampMin(var1, var2);
   }

   public Tuple3d(Tuple3d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public double getZ() {
      return this.z;
   }

   public void interpolate(Tuple3d var1, double var2) {
      this.x = (1.0 - var2) * this.x + var2 * var1.x;
      this.y = (1.0 - var2) * this.y + var2 * var1.y;
      this.z = (1.0 - var2) * this.z + var2 * var1.z;
   }

   public void set(Tuple3d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public void method_23537(float var1, Tuple3d var2) {
      this.clampMax(var1, var2);
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ")";
   }

   public void clamp(float var1, float var2) {
      this.clamp((double)var1, (double)var2);
   }

   public void scale(double var1) {
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
   }
}
