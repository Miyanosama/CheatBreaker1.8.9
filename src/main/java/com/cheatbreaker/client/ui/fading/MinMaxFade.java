package com.cheatbreaker.client.ui.fading;

public class MinMaxFade extends FloatFade {
   @Override
   public float getValue() {
      float var1 = super.getValue();
      return var1 < 0.5 ? 2.0F * var1 * var1 : -1.0F + (4.0F - 2.0F * var1) * var1;
   }

   public MinMaxFade(long var1) {
      super(var1);
   }
}
