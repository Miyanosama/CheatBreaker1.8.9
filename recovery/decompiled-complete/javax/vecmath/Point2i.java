package javax.vecmath;

import java.io.Serializable;
import net.minecraft.block.BlockQuartz;
import net.optifine.util.IntegratedServerUtils;

public class Point2i extends Tuple2i implements Serializable {
   public BlockQuartz field_0001;
   public static long field_0002;
   public IntegratedServerUtils field_0000;

   public Point2i(Tuple2i var1) {
      super(var1);
   }

   public Point2i(int var1, int var2) {
      super(var1, var2);
   }

   public Point2i(int[] var1) {
      super(var1);
   }

   public Point2i() {
   }
}
