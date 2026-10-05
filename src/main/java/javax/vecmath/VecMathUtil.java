package javax.vecmath;

public class VecMathUtil {
   public static long doubleToLongBits(double var0) {
      return var0 == 0.0 ? 0L : Double.doubleToLongBits(var0);
   }

   public static int floatToIntBits(float var0) {
      return var0 == 0.0F ? 0 : Float.floatToIntBits(var0);
   }
}
