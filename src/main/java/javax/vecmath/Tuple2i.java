package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple2i implements Serializable, Cloneable {
   public int x;
   public int y;
   public static final long recoveredField733 = -3555701650170169638L;

   public void scale(int var1) {
      this.x *= var1;
      this.y *= var1;
   }

   public Tuple2i(int[] var1) {
      this.x = var1[0];
      this.y = var1[1];
   }

   public void clamp(int var1, int var2, Tuple2i var3) {
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
   }

   public int getY() {
      return this.y;
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.y + ")";
   }

   public void setY(int var1) {
      this.y = var1;
   }

   public void clamp(int var1, int var2) {
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
   }

   public void clampMax(int var1) {
      if (this.x > var1) {
         this.x = var1;
      }

      if (this.y > var1) {
         this.y = var1;
      }
   }

   public void scale(int var1, Tuple2i var2) {
      this.x = var1 * var2.x;
      this.y = var1 * var2.y;
   }

   public Tuple2i(Tuple2i var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public void clampMin(int var1) {
      if (this.x < var1) {
         this.x = var1;
      }

      if (this.y < var1) {
         this.y = var1;
      }
   }

   public void add(Tuple2i var1, Tuple2i var2) {
      this.x = var1.x + var2.x;
      this.y = var1.y + var2.y;
   }

   @Override
   public int hashCode() {
      long var1 = 1L;
      var1 = 31L * var1 + this.x;
      var1 = 31L * var1 + this.y;
      return (int)(var1 ^ var1 >> 32);
   }

   public void scaleAdd(int var1, Tuple2i var2, Tuple2i var3) {
      this.x = var1 * var2.x + var3.x;
      this.y = var1 * var2.y + var3.y;
   }

   public void set(int[] var1) {
      this.x = var1[0];
      this.y = var1[1];
   }

   public Tuple2i(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }

   public void setX(int var1) {
      this.x = var1;
   }

   public void set(int var1, int var2) {
      this.x = var1;
      this.y = var2;
   }

   public Tuple2i() {
      this.x = 0;
      this.y = 0;
   }

   public void negate(Tuple2i var1) {
      this.x = -var1.x;
      this.y = -var1.y;
   }

   public void clampMin(int var1, Tuple2i var2) {
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
   }

   public void absolute(Tuple2i var1) {
      this.x = Math.abs(var1.x);
      this.y = Math.abs(var1.y);
   }

   public void absolute() {
      this.x = Math.abs(this.x);
      this.y = Math.abs(this.y);
   }

   public void add(Tuple2i var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
   }

   public void sub(Tuple2i var1, Tuple2i var2) {
      this.x = var1.x - var2.x;
      this.y = var1.y - var2.y;
   }

   public void get(int[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
   }

   public void get(Tuple2i var1) {
      var1.x = this.x;
      var1.y = this.y;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         Tuple2i var2 = (Tuple2i)var1;
         return this.x == var2.x && this.y == var2.y;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public void clampMax(int var1, Tuple2i var2) {
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
   }

   public void scaleAdd(int var1, Tuple2i var2) {
      this.x = var1 * this.x + var2.x;
      this.y = var1 * this.y + var2.y;
   }

   public void set(Tuple2i var1) {
      this.x = var1.x;
      this.y = var1.y;
   }

   public void sub(Tuple2i var1) {
      this.x = this.x - var1.x;
      this.y = this.y - var1.y;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public int getX() {
      return this.x;
   }

   public void negate() {
      this.x = -this.x;
      this.y = -this.y;
   }
}
