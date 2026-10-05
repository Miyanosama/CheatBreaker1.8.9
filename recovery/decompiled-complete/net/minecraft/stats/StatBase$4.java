package net.minecraft.stats;

import io.netty.util.Recycler$DefaultHandle;

public class StatBase$4 implements IStatType {
   public Recycler$DefaultHandle field_0000;

   @Override
   public String format(int var1) {
      return StatBase.access$100().format(var1 * 0.1);
   }
}
