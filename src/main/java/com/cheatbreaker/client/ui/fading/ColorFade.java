package com.cheatbreaker.client.ui.fading;

import java.awt.Color;

public class ColorFade extends ExponentialFade {
   public Color recoveredField1319;
   public boolean recoveredField1320;
   public int recoveredField1321;
   public Color recoveredField1322;
   public int recoveredField1323;

   public ColorFade(long var1, int var3, int var4) {
      super(var1);
      this.recoveredField1323 = var3;
      this.recoveredField1321 = var4;
   }

   public void method_25067(int var1) {
      this.recoveredField1323 = var1;
   }

   public ColorFade(int var1, int var2) {
      this(175L, var1, var2);
   }

   public void method_25068(int var1) {
      this.recoveredField1321 = var1;
   }

   public Color method_25066(boolean var1) {
      Color var2 = new Color(var1 ? this.recoveredField1321 : this.recoveredField1323, true);
      if (var1 && !this.recoveredField1320) {
         this.recoveredField1320 = true;
         this.recoveredField1322 = new Color(this.recoveredField1323, true);
         this.recoveredField1319 = new Color(this.recoveredField1321, true);
         this.method_20200();
      } else if (this.recoveredField1320 && !var1) {
         this.recoveredField1320 = false;
         this.recoveredField1322 = new Color(this.recoveredField1321, true);
         this.recoveredField1319 = new Color(this.recoveredField1323, true);
         this.method_20200();
      }

      if (this.method_21233()) {
         float var3 = super.method_21227();
         int var4 = (int)Math.abs(var3 * this.recoveredField1319.getRed() + (1.0F - var3) * this.recoveredField1322.getRed());
         int var5 = (int)Math.abs(var3 * this.recoveredField1319.getGreen() + (1.0F - var3) * this.recoveredField1322.getGreen());
         int var6 = (int)Math.abs(var3 * this.recoveredField1319.getBlue() + (1.0F - var3) * this.recoveredField1322.getBlue());
         int var7 = (int)Math.abs(var3 * this.recoveredField1319.getAlpha() + (1.0F - var3) * this.recoveredField1322.getAlpha());
         var2 = new Color(var4, var5, var6, var7);
      }

      return var2;
   }
}
