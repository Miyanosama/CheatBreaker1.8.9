package io.netty.buffer;

public class ByteBufProcessor$7 implements ByteBufProcessor {
   @Override
   public boolean process(byte var1) {
      return var1 != 13 && var1 != 10;
   }
}
