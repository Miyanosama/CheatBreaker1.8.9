package javax.vecmath;

import java.io.Serializable;

public class Vector2f extends Tuple2f implements Serializable {
   public static final long recoveredField2813 = -2168194326883512320L;

   public Vector2f(Vector2f var1) {
      super(var1);
   }

   public Vector2f() {
   }

   public Vector2f(Tuple2d var1) {
      super(var1);
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public Vector2f(Vector2d var1) {
      super(var1);
   }

   public Vector2f(float var1, float var2) {
      super(var1, var2);
   }

   public float dot(Vector2f var1) {
      return this.x * var1.x + this.y * var1.y;
   }

   public void normalize() {
      float var1 = (float)(1.0 / Math.sqrt(this.x * this.x + this.y * this.y));
      this.x *= var1;
      this.y *= var1;
   }

   public float angle(Vector2f var1) {
      double var2 = this.dot(var1) / (this.length() * var1.length());
      if (var2 < -1.0) {
         var2 = -1.0;
      }

      if (var2 > 1.0) {
         var2 = 1.0;
      }

      return (float)Math.acos(var2);
   }

   public float length() {
      return (float)Math.sqrt(this.x * this.x + this.y * this.y);
   }

   public void normalize(Vector2f var1) {
      float var2 = (float)(1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y));
      this.x = var1.x * var2;
      this.y = var1.y * var2;
   }

   public Vector2f(float[] var1) {
      super(var1);
   }

   public Vector2f(Tuple2f var1) {
      super(var1);
   }
}
