package javax.vecmath;

import java.io.Serializable;

public abstract class Tuple4b implements Serializable, Cloneable {
   public byte z;
   public byte x;
   public static final long recoveredField905 = -8226727741811898211L;
   public byte y;
   public byte w;

   public byte getX() {
      return this.x;
   }

   public Tuple4b(byte[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public Tuple4b(Tuple4b var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public boolean equals(Tuple4b var1) {
      try {
         return this.x == var1.x && this.y == var1.y && this.z == var1.z && this.w == var1.w;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public void set(Tuple4b var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = var1.w;
   }

   public void get(Tuple4b var1) {
      var1.x = this.x;
      var1.y = this.y;
      var1.z = this.z;
      var1.w = this.w;
   }

   public void setY(byte var1) {
      this.y = var1;
   }

   public void get(byte[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
      var1[2] = this.z;
      var1[3] = this.w;
   }

   public Tuple4b() {
      this.x = 0;
      this.y = 0;
      this.z = 0;
      this.w = 0;
   }

   public void setX(byte var1) {
      this.x = var1;
   }

   public Tuple4b(byte var1, byte var2, byte var3, byte var4) {
      this.x = var1;
      this.y = var2;
      this.z = var3;
      this.w = var4;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         Tuple4b var2 = (Tuple4b)var1;
         return this.x == var2.x && this.y == var2.y && this.z == var2.z && this.w == var2.w;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public byte getY() {
      return this.y;
   }

   public void setZ(byte var1) {
      this.z = var1;
   }

   @Override
   public String toString() {
      return "(" + (this.x & 0xFF) + ", " + (this.y & 0xFF) + ", " + (this.z & 0xFF) + ", " + (this.w & 0xFF) + ")";
   }

   public byte getW() {
      return this.w;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public void setW(byte var1) {
      this.w = var1;
   }

   public void set(byte[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.w = var1[3];
   }

   public byte getZ() {
      return this.z;
   }

   @Override
   public int hashCode() {
      return (this.x & 0xFF) << 0 | (this.y & 0xFF) << 8 | (this.z & 0xFF) << 16 | (this.w & 0xFF) << 24;
   }
}
