package net.optifine.util;

public class SmoothFloat {
   public float valueLast;
   public float timeFadeDownSec;
   public float timeFadeUpSec;
   public long timeLastMs;

   public float getValueLast() {
      return this.valueLast;
   }

   public float getTimeFadeUpSec() {
      return this.timeFadeUpSec;
   }

   public float getSmoothValue(float var1, float var2, float var3) {
      this.timeFadeUpSec = var2;
      this.timeFadeDownSec = var3;
      return this.getSmoothValue(var1);
   }

   public static float getSmoothValue(float var0, float var1, float var2, float var3) {
      if (var2 <= 0.0F) {
         return var0;
      } else {
         float var4 = var1 - var0;
         float var5;
         if (var3 > 0.0F && var2 < var3 && Math.abs(var4) > 1.0E-6F) {
            float var6 = var3 / var2;
            float var7 = 4.61F;
            float var8 = 0.13F;
            float var9 = 10.0F;
            float var10 = var7 - 1.0F / (var8 + var6 / var9);
            float var11 = var2 / var3 * var10;
            var11 = NumUtils.limit(var11, 0.0F, 1.0F);
            var5 = var0 + var4 * var11;
         } else {
            var5 = var1;
         }

         return var5;
      }
   }

   public float getSmoothValue(float var1) {
      long var2 = System.currentTimeMillis();
      float var4 = this.valueLast;
      long var5 = this.timeLastMs;
      float var7 = (float)(var2 - var5) / 1000.0F;
      float var8 = var1 >= var4 ? this.timeFadeUpSec : this.timeFadeDownSec;
      float var9 = getSmoothValue(var4, var1, var7, var8);
      this.valueLast = var9;
      this.timeLastMs = var2;
      return var9;
   }

   public SmoothFloat(float var1, float var2) {
      this(var1, var2, var2);
   }

   public long getTimeLastMs() {
      return this.timeLastMs;
   }

   public SmoothFloat(float var1, float var2, float var3) {
      this.valueLast = var1;
      this.timeFadeUpSec = var2;
      this.timeFadeDownSec = var3;
      this.timeLastMs = System.currentTimeMillis();
   }

   public float getTimeFadeDownSec() {
      return this.timeFadeDownSec;
   }
}
