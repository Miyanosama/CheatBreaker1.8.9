package com.cheatbreaker.client.ui.fading;

public class FloatFade extends AbstractFade {
   public FloatFade(long var1, float var3) {
      super(var1, var3);
   }

   @Override
   public float getValue() {
      return (float)(this.recoveredField1961 - this.method_21241()) / (float)this.recoveredField1961;
   }

   public FloatFade(long var1) {
      super(var1, 1.0F);
   }
}
