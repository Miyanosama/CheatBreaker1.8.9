package net.minecraft.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class Timer {
   public float recoveredField2493;
   public float recoveredField2494;
   public int recoveredField2495;
   public float recoveredField2496;
   public double recoveredField2497;
   public double recoveredField2498;
   public float recoveredField2499 = 1.0F;
   public long recoveredField2500;
   public long recoveredField2501;
   public long recoveredField2502;

   public void method_03202() {
      long var1 = Minecraft.getSystemTime();
      long var3 = var1 - this.recoveredField2501;
      long var5 = System.nanoTime() / 1000000L;
      double var7 = var5 / 1000.0;
      if (var3 <= 1000L && var3 >= 0L) {
         this.recoveredField2500 += var3;
         if (this.recoveredField2500 > 1000L) {
            long var9 = var5 - this.recoveredField2502;
            double var11 = (double)this.recoveredField2500 / var9;
            this.recoveredField2498 = this.recoveredField2498 + (var11 - this.recoveredField2498) * 0.2F;
            this.recoveredField2502 = var5;
            this.recoveredField2500 = 0L;
         }

         if (this.recoveredField2500 < 0L) {
            this.recoveredField2502 = var5;
         }
      } else {
         this.recoveredField2497 = var7;
      }

      this.recoveredField2501 = var1;
      double var13 = (var7 - this.recoveredField2497) * this.recoveredField2498;
      this.recoveredField2497 = var7;
      var13 = MathHelper.clamp_double(var13, 0.0, 1.0);
      this.recoveredField2494 = (float)(this.recoveredField2494 + var13 * this.recoveredField2499 * this.recoveredField2496);
      this.recoveredField2495 = (int)this.recoveredField2494;
      this.recoveredField2494 = this.recoveredField2494 - this.recoveredField2495;
      if (this.recoveredField2495 > 10) {
         this.recoveredField2495 = 10;
      }

      this.recoveredField2493 = this.recoveredField2494;
   }

   public Timer(float var1) {
      this.recoveredField2498 = 1.0;
      this.recoveredField2496 = var1;
      this.recoveredField2501 = Minecraft.getSystemTime();
      this.recoveredField2502 = System.nanoTime() / 1000000L;
   }
}
