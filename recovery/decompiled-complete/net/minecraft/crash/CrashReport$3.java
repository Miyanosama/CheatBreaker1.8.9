package net.minecraft.crash;

import io.netty.channel.AbstractChannelHandlerContext$12;
import io.netty.handler.codec.socks.SocksCmdRequest;
import java.util.concurrent.Callable;

public class CrashReport$3 implements Callable<String> {
   public AbstractChannelHandlerContext$12 field_0001;
   public SocksCmdRequest field_0002;

   public CrashReport$3(CrashReport var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return System.getProperty("java.version") + ", " + System.getProperty("java.vendor");
   }
}
