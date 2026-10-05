package javax.vecmath;

import java.io.Serializable;
import javax.vecmath.VecMathUtil;

public abstract class Tuple2d implements Serializable, Cloneable {
   public double x;
   public double y;
   public static final long recoveredField697 = 6205762482756093838L;

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
   }

   public Tuple2d(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
   }

   public void absolute() {
      this.x = Math.abs(this.x);
      this.y = Math.abs(this.y);
   }

   public void scale(double var1) {
      this.x *= var1;
      this.y *= var1;
   }

   public void negate() {
      this.x = -this.x;
      this.y = -this.y;
   }

   public boolean equals(Tuple2d var1) {
      try {
         return this.x == var1.x && this.y == var1.y;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public void negate(Tuple2d var1) {
      this.x = -var1.x;
      this.y = -var1.y;
   }

   public Tuple2d(Tuple2d var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public void add(Tuple2d var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
   }

   public void scaleAdd(double var1, Tuple2d var3, Tuple2d var4) {
      this.x = var1 * var3.x + var4.x;
      this.y = var1 * var3.y + var4.y;
   }

   public void clampMax(double var1, Tuple2d var3) {
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
   }

   public void get(double[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
   }

   public void interpolate(Tuple2d var1, double var2) {
      this.x = (1.0 - var2) * this.x + var2 * var1.x;
      this.y = (1.0 - var2) * this.y + var2 * var1.y;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         Tuple2d var2 = (Tuple2d)var1;
         return this.x == var2.x && this.y == var2.y;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public void absolute(Tuple2d var1) {
      this.x = Math.abs(var1.x);
      this.y = Math.abs(var1.y);
   }

   public void set(double var1, double var3) {
      this.x = var1;
      this.y = var3;
   }

   public void add(Tuple2d var1, Tuple2d var2) {
      this.x = var1.x + var2.x;
      this.y = var1.y + var2.y;
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   public void sub(Tuple2d var1, Tuple2d var2) {
      this.x = var1.x - var2.x;
      this.y = var1.y - var2.y;
   }

   public void set(Tuple2d var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public void clampMin(double var1, Tuple2d var3) {
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
   }

   public Tuple2d(Tuple2f var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public Tuple2d(double var1, double var3) {
      this.x = var1;
      this.y = var3;
   }

   public boolean epsilonEquals(Tuple2d var1, double var2) {
      double var4 = this.x - var1.x;
      if (Double.isNaN(var4)) {
         return false;
      } else if ((var4 < 0.0 ? -var4 : var4) > var2) {
         return false;
      } else {
         var4 = this.y - var1.y;
         return Double.isNaN(var4) ? false : !((var4 < 0.0 ? -var4 : var4) > var2);
      }
   }

   public void clamp(double var1, double var3, Tuple2d var5) {
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
   }

   public void clampMin(double var1) {
      if (this.x < var1) {
         this.x = var1;
      }

      if (this.y < var1) {
         this.y = var1;
      }
   }

   public void setX(double var1) {
      this.x = var1;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public void setY(double var1) {
      this.y = var1;
   }

   @Override
   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.x);
      var1 = 31L * var1 + VecMathUtil.doubleToLongBits(this.y);
      return (int)(var1 ^ var1 >> 32);
   }

   public void clampMax(double var1) {
      if (this.x > var1) {
         this.x = var1;
      }

      if (this.y > var1) {
         this.y = var1;
      }
   }

   public Tuple2d() {
      this.x = 0.0;
      this.y = 0.0;
   }

   public void scale(double var1, Tuple2d var3) {
      this.x = var1 * var3.x;
      this.y = var1 * var3.y;
   }

   public void set(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
   }

   public void sub(Tuple2d var1) {
      this.x = this.x - var1.x;
      this.y = this.y - var1.y;
   }

   public void interpolate(Tuple2d var1, Tuple2d var2, double var3) {
      this.x = (1.0 - var3) * var1.x + var3 * var2.x;
      this.y = (1.0 - var3) * var1.y + var3 * var2.y;
   }

   public double getY() {
      return this.y;
   }

   public double getX() {
      return this.x;
   }

   public void set(Tuple2f var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public void scaleAdd(double var1, Tuple2d var3) {
      this.x = var1 * this.x + var3.x;
      this.y = var1 * this.y + var3.y;
   }
}
