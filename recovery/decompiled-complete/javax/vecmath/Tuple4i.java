package javax.vecmath;

import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import java.io.Serializable;
import recovered.unidentified.UnidentifiedClass1276;

public abstract class Tuple4i implements Serializable, Cloneable {
   public int y;
   public int z;
   public FixedLengthFrameDecoder field_0000;
   public DefaultFullHttpResponse field_0001;
   public static long field_0006;
   public UnidentifiedClass1276 field_0004;
   public int w;
   public int x;

   public void get(int[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
      var1[2] = this.z;
      var1[3] = this.w;
   }

   @Override
   public int hashCode() {
      long var1 = 5579354458028249629L & -5579354459524626173L;
      var1 = (1078804543L & 679480607L) * var1 + this.x;
      var1 = (1182946847L & -6169069329837883233L) * var1 + this.y;
      var1 = (1612759071L & 7987507571551896799L) * var1 + this.z;
      var1 = (6291080557656998495L & 17335487L) * var1 + this.w;
      return (int)(var1 ^ var1 >> 32);
   }

   public Tuple4i(int var1, int var2, int var3, int var4) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      this.w = var4;
   }

   public void get(Tuple4i var1) {
      var1.x = this.x;
      var1.y = this.y;
      var1.z = this.z;
      var1.w = this.w;
   }

   public void add(Tuple4i var1) {
      this.x = this.x + var1.x;
      this.y = this.y + var1.y;
      this.z = this.z + var1.z;
      this.w = this.w + var1.w;
   }

   public int getW() {
      return this.w;
   }

   public void setX(int var1) {
      this.x = var1;
   }

   public int getZ() {
      return this.z;
   }

   public void clamp(int var1, int var2, Tuple4i var3) {
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

   public void clampMax(int var1, Tuple4i var2) {
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

   public void setY(int var1) {
      this.y = var1;
   }

   public void set(int var1, int var2, int var3, int var4) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      this.w = var4;
   }

   public void sub(Tuple4i var1) {
      this.x = this.x - var1.x;
      this.y = this.y - var1.y;
      this.z = this.z - var1.z;
      this.w = this.w - var1.w;
   }

   public void scaleAdd(int var1, Tuple4i var2) {
      this.x = var1 * this.x + var2.x;
      this.y = var1 * this.y + var2.y;
      this.z = var1 * this.z + var2.z;
      this.w = var1 * this.w + var2.w;
   }

   public int getX() {
      return this.x;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
   }

   public void clampMin(int var1) {
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

   public void absolute(Tuple4i var1) {
      this.x = Math.abs(var1.x);
      this.y = Math.abs(var1.y);
      this.z = Math.abs(var1.z);
      this.w = Math.abs(var1.w);
   }

   public void scaleAdd(int var1, Tuple4i var2, Tuple4i var3) {
      this.x = var1 * var2.x + var3.x;
      this.y = var1 * var2.y + var3.y;
      this.z = var1 * var2.z + var3.z;
      this.w = var1 * var2.w + var3.w;
   }

   public void set(Tuple4i var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public Tuple4i() {
      this.x = 0;
      this.y = 0;
      this.z = 0;
      this.w = 0;
   }

   public Tuple4i(Tuple4i var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
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

   public void scale(int var1) {
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
      this.w *= var1;
   }

   public void setZ(int var1) {
      this.z = var1;
   }

   public void negate(Tuple4i var1) {
      this.x = -var1.x;
      this.y = -var1.y;
      this.z = -var1.z;
      this.w = -var1.w;
   }

   public void set(int[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public void clampMin(int var1, Tuple4i var2) {
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

   public int getY() {
      return this.y;
   }

   public void sub(Tuple4i var1, Tuple4i var2) {
      this.x = var1.x - var2.x;
      this.y = var1.y - var2.y;
      this.z = var1.z - var2.z;
      this.w = var1.w - var2.w;
   }

   public void scale(int var1, Tuple4i var2) {
      this.x = var1 * var2.x;
      this.y = var1 * var2.y;
      this.z = var1 * var2.z;
      this.w = var1 * var2.w;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         Tuple4i var2 = (Tuple4i)var1;
         return this.x == var2.x && this.y == var2.y && this.z == var2.z && this.w == var2.w;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public void negate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
      this.w = -this.w;
   }

   public void clampMax(int var1) {
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

   public void absolute() {
      this.x = Math.abs(this.x);
      this.y = Math.abs(this.y);
      this.z = Math.abs(this.z);
      this.w = Math.abs(this.w);
   }

   public void setW(int var1) {
      this.w = var1;
   }

   public Tuple4i(int[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public void add(Tuple4i var1, Tuple4i var2) {
      this.x = var1.x + var2.x;
      this.y = var1.y + var2.y;
      this.z = var1.z + var2.z;
      this.w = var1.w + var2.w;
   }
}
