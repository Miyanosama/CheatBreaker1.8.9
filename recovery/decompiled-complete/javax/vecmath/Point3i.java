package javax.vecmath;

import java.io.Serializable;
import net.optifine.model.BlockModelCustomizer;

public class Point3i extends Tuple3i implements Serializable {
   public BlockModelCustomizer field_0001;
   public static long field_0002;
   public Tuple3b field_0000;

   public Point3i(int[] var1) {
      super(var1);
   }

   public Point3i() {
   }

   public Point3i(int var1, int var2, int var3) {
      super(var1, var2, var3);
   }

   public Point3i(Tuple3i var1) {
      super(var1);
   }
}
