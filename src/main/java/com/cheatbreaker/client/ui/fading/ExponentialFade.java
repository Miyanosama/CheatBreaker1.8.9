package com.cheatbreaker.client.ui.fading;

public class ExponentialFade extends FloatFade {
   public ExponentialFade(long var1) {
      super(var1);
   }

   @Override
   public float getValue() {
      float var1 = super.getValue();
      return (float)Math.pow(var1 * (2.0F - var1), 1.0);
   }
}
