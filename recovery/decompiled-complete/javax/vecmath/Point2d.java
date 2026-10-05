package javax.vecmath;

import io.netty.util.internal.chmv8.ForkJoinPool$2;
import java.io.Serializable;
import net.minecraft.block.BlockOldLog$1;
import net.optifine.BlockPosM$1;
import org.apache.log4j.helpers.MDCKeySetExtractor;
import org.json.JSONWriter;

public class Point2d extends Tuple2d implements Serializable {
   public static long field_0003;
   public ForkJoinPool$2 field_0005;
   public BlockPosM$1 field_0002;
   public MDCKeySetExtractor field_0004;
   public JSONWriter field_0000;
   public BlockOldLog$1 field_0001;

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
