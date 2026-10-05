package com.cheatbreaker.client.ui.fading;

public class CosineFade extends FloatFade {
   public CosineFade(long var1) {
      super(var1, 0.0F);
   }

   @Override
   public float getValue() {
      float var1 = super.getValue();
      float var2 = var1 * 2.0F - 1.0F;
      return (float)(Math.cos(var2 * Math.PI) + 1.0) / 2.0F;
   }
}
