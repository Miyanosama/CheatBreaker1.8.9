package io.netty.util.concurrent;

import io.netty.channel.socket.nio.NioServerSocketChannel$NioServerSocketChannelConfig;
import io.netty.handler.codec.socks.SocksAuthResponse;
import io.netty.handler.codec.socks.SocksCmdResponseDecoder;
import net.optifine.expr.ParametersVariable;

public class DefaultPromise$4 implements Runnable {
   public ParametersVariable __junk1080122112978642114;
   public SocksAuthResponse __junk655075683555047589;
   public NioServerSocketChannel$NioServerSocketChannelConfig __junk639343679715209654;
   public SocksCmdResponseDecoder __junk1234285656154816130;

   @Override
   public void run() {
      DefaultPromise.access$200(this.val$self, this.val$array, this.val$progress, this.val$total);
   }

   public DefaultPromise$4(DefaultPromise var1, ProgressiveFuture var2, GenericProgressiveFutureListener[] var3, long var4, long var6) {
      this.this$0 = var1;
      this.val$self = var2;
      this.val$array = var3;
      this.val$progress = var4;
      this.val$total = var6;
      super();
   }
}
