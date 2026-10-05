package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.mainmenu.LargeAnimatedLogoElement;

import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;

public class LargeLogoStarFade extends FloatFade {
   public MinMaxFade recoveredField3130;
   public MinMaxFade recoveredField3131;
   public LargeAnimatedLogoElement recoveredField3132;

   @Override
   public void method_20200() {
      super.method_20200();
      if (!this.recoveredField3131.method_21217()) {
         this.recoveredField3131.method_20200();
      }
   }

   public float method_24497() {
      if (!this.recoveredField3131.method_21217()) {
         this.recoveredField3131.method_20200();
      }

      if (this.recoveredField3131.method_21233()) {
         return Math.max(this.recoveredField3131.method_21227(), 0.15F);
      } else if (this.method_21229() <= this.recoveredField3130.method_21240()) {
         if (!this.recoveredField3130.method_21217()) {
            this.recoveredField3130.method_20200();
         }

         return 1.0F - 0.85F * this.recoveredField3130.method_21227();
      } else {
         return 1.0F;
      }
   }

   public LargeLogoStarFade(LargeAnimatedLogoElement var1, long var2) {
      super((long)((float)var2));
      this.recoveredField3132 = var1;
      this.recoveredField3131 = new MinMaxFade(Math.min((long)Math.min((float)var2 * 0.2F, 3000.0F), 1500L));
      this.recoveredField3130 = new MinMaxFade(Math.min((long)((float)var2 * 0.4F), 5000L));
   }

   public boolean method_24498() {
      return this.recoveredField3131.method_21210();
   }
}
