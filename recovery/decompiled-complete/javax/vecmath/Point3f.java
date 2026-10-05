package javax.vecmath;

import java.io.Serializable;
import recovered.unidentified.UnidentifiedClass0347;

public class Point3f extends Tuple3f implements Serializable {
   public UnidentifiedClass0347 field_0000;
   public static long field_0001;

   public Point3f(float[] var1) {
      super(var1);
   }

   public Point3f(Point3d var1) {
      super(var1);
   }

   public Point3f() {
   }

   public Point3f(float var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   public float distanceL1(Point3f var1) {
      return Math.abs(this.x - var1.x) + Math.abs(this.y - var1.y) + Math.abs(this.z - var1.z);
   }

   public float distanceSquared(Point3f var1) {
      float var2 = this.x - var1.x;
      float var3 = this.y - var1.y;
      float var4 = this.z - var1.z;
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   public void project(Point4f var1) {
      float var2 = 1.0F / var1.w;
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
   }

   public float distanceLinf(Point3f var1) {
      float var2 = Math.max(Math.abs(this.x - var1.x), Math.abs(this.y - var1.y));
      return Math.max(var2, Math.abs(this.z - var1.z));
   }

   public Point3f(Tuple3f var1) {
      super(var1);
   }

   public Point3f(Tuple3d var1) {
      super(var1);
   }

   public Point3f(Point3f var1) {
      super(var1);
   }

   public float distance(Point3f var1) {
      float var2 = this.x - var1.x;
      float var3 = this.y - var1.y;
      float var4 = this.z - var1.z;
      return (float)Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4);
   }
}
