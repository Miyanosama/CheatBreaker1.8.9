package io.netty.buffer;

import io.netty.channel.socket.nio.NioServerSocketChannel$1;
import org.json.JSONPointer$Builder;

public class ByteBufProcessor$9 implements ByteBufProcessor {
   public NioServerSocketChannel$1 __junk6195386604945658103;
   public JSONPointer$Builder __junk6242951349532326931;

   @Override
   public boolean process(byte var1) {
      return var1 != 32 && var1 != 9;
   }
}
