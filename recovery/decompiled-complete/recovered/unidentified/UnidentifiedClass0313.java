package recovered.unidentified;

import net.minecraft.util.MathHelper;

public class UnidentifiedClass0313 {
   public static double[] field_0000 = new double[65536];
   public static double[] field_0001 = new double[360];

   public static double method_02411(int var0) {
      var0 += 90;
      var0 %= 360;
      return field_0001[var0];
   }

   static {
      for (int var0 = 0; var0 < 65536; var0++) {
         field_0000[var0] = Math.sin(var0 * Math.PI * 2.0 / 65536.0);
      }

      for (int var1 = 0; var1 < 360; var1++) {
         field_0001[var1] = Math.sin(Math.toRadians(var1));
      }
   }

   public static float method_02409(float var0, float var1) {
      if (var1 > 0.0F) {
         var0 = var1 * Math.round(var0 / var1);
      }

      return var0;
   }

   public static float method_02410(float var0, float var1, float var2, float var3) {
      var0 = method_02409(var0, var3);
      return MathHelper.clamp_float(var0, var1, var2);
   }

   public static double method_02413(int var0) {
      var0 %= 360;
      return field_0001[var0];
   }

   public static float method_02412(float var0, float var1, float var2, float var3) {
      return method_02410(var1 + (var2 - var1) * MathHelper.clamp_float(var0, 0.0F, 1.0F), var1, var2, var3);
   }

   public static float method_02408(float var0, float var1, float var2, float var3) {
      return MathHelper.clamp_float((method_02410(var0, var1, var2, var3) - var1) / (var2 - var1), 0.0F, 1.0F);
   }
}
