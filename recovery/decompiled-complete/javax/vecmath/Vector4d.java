package javax.vecmath;

import java.io.Serializable;
import net.minecraft.network.NetworkSystem$4;
import org.apache.log4j.ConsoleAppender$SystemOutStream;

public class Vector4d extends Tuple4d implements Serializable {
   public NetworkSystem$4 field_0001;
   public ConsoleAppender$SystemOutStream field_0002;
   public static long field_0000;

   public Vector4d() {
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
   }

   public void set(Tuple3d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = 0.0;
   }

   public Vector4d(Vector4f var1) {
      super(var1);
   }

   public double dot(Vector4d var1) {
      return this.x * var1.x + this.y * var1.y + this.z * var1.z + this.w * var1.w;
   }

   public Vector4d(Tuple4d var1) {
      super(var1);
   }

   public Vector4d(Tuple4f var1) {
      super(var1);
   }

   public Vector4d(Vector4d var1) {
      super(var1);
   }

   public void normalize() {
      double var1 = 1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
      this.w *= var1;
   }

   public double length() {
      return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
   }

   public Vector4d(double[] var1) {
      super(var1);
   }

   public void normalize(Vector4d var1) {
      double var2 = 1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z + var1.w * var1.w);
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
      this.w = var1.w * var2;
   }

   public double angle(Vector4d var1) {
      double var2 = this.dot(var1) / (this.length() * var1.length());
      if (var2 < -1.0) {
         var2 = -1.0;
      }

      if (var2 > 1.0) {
         var2 = 1.0;
      }

      return Math.acos(var2);
   }

   public Vector4d(double var1, double var3, double var5, double var7) {
      super(var1, var3, var5, var7);
   }

   public Vector4d(Tuple3d var1) {
      super(var1.x, var1.y, var1.z, 0.0);
   }
}
