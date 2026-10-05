package net.minecraft.world;

public class ColorizerFoliage {
   public static int[] foliageBuffer = new int[65536];

   public static int getFoliageColor(double var0, double var2) {
      var2 *= var0;
      int var4 = (int)((1.0 - var0) * 255.0);
      int var5 = (int)((1.0 - var2) * 255.0);
      return foliageBuffer[var5 << 8 | var4];
   }

   public static int getFoliageColorBasic() {
      return 4764952;
   }

   public static int getFoliageColorBirch() {
      return 8431445;
   }

   public static int getFoliageColorPine() {
      return 6396257;
   }

   public static void setFoliageBiomeColorizer(int[] var0) {
      foliageBuffer = var0;
   }
}
