package net.minecraft.block;

import io.netty.handler.codec.socks.SocksCmdStatus;
import net.minecraft.profiler.Profiler;

public class BlockHalfStoneSlab extends BlockStoneSlab {
   public Profiler field_0000;
   public SocksCmdStatus field_0001;

   @Override
   public boolean isDouble() {
      return false;
   }
}
