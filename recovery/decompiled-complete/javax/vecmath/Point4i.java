package javax.vecmath;

import java.io.Serializable;
import net.minecraft.client.renderer.entity.layers.LayerArrow;

public class Point4i extends Tuple4i implements Serializable {
   public LayerArrow field_0000;
   public static long field_0001;

   public Point4i(Tuple4i var1) {
      super(var1);
   }

   public Point4i(int[] var1) {
      super(var1);
   }

   public Point4i(int var1, int var2, int var3, int var4) {
      super(var1, var2, var3, var4);
   }

   public Point4i() {
   }
}
