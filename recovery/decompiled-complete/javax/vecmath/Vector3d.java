package javax.vecmath;

import java.io.Serializable;
import net.minecraft.client.model.ModelArmorStandArmor;

public class Vector3d extends Tuple3d implements Serializable {
   public ModelArmorStandArmor field_0000;
   public static long field_0001;

   public Vector3d(Tuple3f var1) {
      super(var1);
   }

   public Vector3d(double[] var1) {
      super(var1);
   }

   public void normalize(Vector3d var1) {
      double var2 = 1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
   }

   public void cross(Vector3d var1, Vector3d var2) {
      double var3 = var1.y * var2.z - var1.z * var2.y;
      double var5 = var2.x * var1.z - var2.z * var1.x;
      this.z = var1.x * var2.y - var1.y * var2.x;
      this.x = var3;
      this.y = var5;
   }

   public double angle(Vector3d var1) {
      double var2 = this.dot(var1) / (this.length() * var1.length());
      if (var2 < -1.0) {
         var2 = -1.0;
      }

      if (var2 > 1.0) {
         var2 = 1.0;
      }

      return Math.acos(var2);
   }

   public Vector3d(Vector3d var1) {
      super(var1);
   }

   public Vector3d(Vector3f var1) {
      super(var1);
   }

   public Vector3d() {
   }

   public void normalize() {
      double var1 = 1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
   }

   public Vector3d(double var1, double var3, double var5) {
      super(var1, var3, var5);
   }

   public double length() {
      return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public Vector3d(Tuple3d var1) {
      super(var1);
   }

   public double dot(Vector3d var1) {
      return this.x * var1.x + this.y * var1.y + this.z * var1.z;
   }
}
