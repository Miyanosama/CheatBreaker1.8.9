package com.cheatbreaker.client.ui.fading;

import javax.vecmath.Tuple4b;

public class FloatFade extends AbstractFade {
   public Tuple4b field_0000;

   public FloatFade(long var1, float var3) {
      super(var1, var3);
   }

   @Override
   public float getValue() {
      return (float)(this.field_0004 - this.method_21241()) / (float)this.field_0004;
   }

   public FloatFade(long var1) {
      super(var1, 1.0F);
   }
}
