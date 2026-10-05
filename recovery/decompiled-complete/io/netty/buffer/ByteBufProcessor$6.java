package io.netty.buffer;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.entity.EntityTrackerEntry;

public class ByteBufProcessor$6 implements ByteBufProcessor {
   public EntityTrackerEntry __junk7370769295302107685;
   public ConcurrentHashMapV8 __junk6555476751912981045;

   @Override
   public boolean process(byte var1) {
      return var1 == 10;
   }
}
