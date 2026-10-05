package javax.vecmath;

import java.io.Serializable;
import net.minecraft.network.play.server.S3CPacketUpdateScore$Action;
import org.apache.log4j.rewrite.RewriteAppender;
import recovered.unidentified.UnidentifiedClass3843;

public class Point2f extends Tuple2f implements Serializable {
   public S3CPacketUpdateScore$Action field_0001;
   public static long field_0003;
   public UnidentifiedClass3843 field_0000;
   public RewriteAppender field_0002;

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
