package javax.vecmath;

import java.io.Serializable;

public class Point2f extends Tuple2f implements Serializable {
   public static final long recoveredField2377 = -4801347926528714435L;

   public Point2f(float[] var1) {
      super(var1);
   }

   public Point2f(Point2d var1) {
      super(var1);
   }

   public Point2f() {
   }

   public float distanceSquared(Point2f var1) {
      float var2 = this.x - var1.x;
      float var3 = this.y - var1.y;
      return var2 * var2 + var3 * var3;
   }

   public Point2f(Point2f var1) {
      super(var1);
   }

   public float distanceL1(Point2f var1) {
      return Math.abs(this.x - var1.x) + Math.abs(this.y - var1.y);
   }

   public Point2f(Tuple2d var1) {
      super(var1);
   }

   public float distance(Point2f var1) {
      float var2 = this.x - var1.x;
      float var3 = this.y - var1.y;
      return (float)Math.sqrt(var2 * var2 + var3 * var3);
   }

   public float distanceLinf(Point2f var1) {
      return Math.max(Math.abs(this.x - var1.x), Math.abs(this.y - var1.y));
   }

   public Point2f(Tuple2f var1) {
      super(var1);
   }

   public Point2f(float var1, float var2) {
      super(var1, var2);
   }
}
