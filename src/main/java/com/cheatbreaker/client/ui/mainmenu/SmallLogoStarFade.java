package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.ui.mainmenu.SmallAnimatedLogoElement;

import com.cheatbreaker.client.ui.fading.FloatFade;
import com.cheatbreaker.client.ui.fading.MinMaxFade;

public class SmallLogoStarFade extends FloatFade {
   public SmallAnimatedLogoElement recoveredField1043;
   public SmallAnimatedLogoElement recoveredField1044;
   public MinMaxFade recoveredField1045;
   public MinMaxFade recoveredField1046;

   public boolean method_20201() {
      return this.recoveredField1045.method_21210();
   }

   public float method_20202() {
      if (!this.recoveredField1045.method_21217()) {
         this.recoveredField1045.method_20200();
      }

      if (this.recoveredField1045.method_21233()) {
         return Math.max(1.0F * this.recoveredField1045.method_21227(), 0.15F);
      } else if (this.method_21229() <= this.recoveredField1046.method_21240()) {
         if (!this.recoveredField1046.method_21217()) {
            this.recoveredField1046.method_20200();
         }

         return 1.0F - 0.85F * this.recoveredField1046.method_21227();
      } else {
         return 1.0F;
      }
   }

   public SmallLogoStarFade(SmallAnimatedLogoElement var1, SmallAnimatedLogoElement var2, long var3) {
      super(var3);
      this.recoveredField1043 = var1;
      this.recoveredField1044 = var2;
      this.recoveredField1045 = new MinMaxFade(Math.min((long)Math.min((float)var3 * 0.2F, 3000.0F), 1500L));
      this.recoveredField1046 = new MinMaxFade(Math.min((long)((float)var3 * 0.4F), 5000L));
   }

   @Override
   public void method_20200() {
      super.method_20200();
      if (!this.recoveredField1045.method_21217()) {
         this.recoveredField1045.method_20200();
      }
   }
}
