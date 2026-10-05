package com.cheatbreaker.client.ui.fading;

import io.netty.util.HashedWheelTimer$HashedWheelTimeout$1;
import net.minecraft.block.state.BlockState$1;

public class ExponentialFade extends FloatFade {
   public HashedWheelTimer$HashedWheelTimeout$1 field_0001;
   public BlockState$1 field_0000;

   public ExponentialFade(long var1) {
      super(var1);
   }

   @Override
   public float getValue() {
      float var1 = super.getValue();
      return (float)Math.pow(var1 * (2.0F - var1), 1.0);
   }
}
