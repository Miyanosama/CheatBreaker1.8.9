package javax.vecmath;

import java.io.Serializable;

public class Point2d extends Tuple2d implements Serializable {
   public static final long recoveredField419 = 1133748791492571954L;

   public Point2d(Tuple2f var1) {
      super(var1);
   }

   public Point2d(double var1, double var3) {
      super(var1, var3);
   }

   public Point2d(Point2f var1) {
      super(var1);
   }

   public Point2d(Point2d var1) {
      super(var1);
   }

   public double distance(Point2d var1) {
      double var2 = this.x - var1.x;
      double var4 = this.y - var1.y;
      return Math.sqrt(var2 * var2 + var4 * var4);
   }

   public Point2d() {
   }

   public double distanceLinf(Point2d var1) {
      return Math.max(Math.abs(this.x - var1.x), Math.abs(this.y - var1.y));
   }

   public Point2d(Tuple2d var1) {
      super(var1);
   }

   public double distanceL1(Point2d var1) {
      return Math.abs(this.x - var1.x) + Math.abs(this.y - var1.y);
   }

   public Point2d(double[] var1) {
      super(var1);
   }

   public double distanceSquared(Point2d var1) {
      double var2 = this.x - var1.x;
      double var4 = this.y - var1.y;
      return var2 * var2 + var4 * var4;
   }
}
