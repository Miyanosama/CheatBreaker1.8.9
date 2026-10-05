package io.netty.channel.sctp.nio;

import io.netty.channel.ChannelPromise;
import java.net.InetAddress;
import net.minecraft.client.renderer.block.statemap.StateMap;

public class NioSctpServerChannel$1 implements Runnable {
   public StateMap __junk779351666376215484;

   public NioSctpServerChannel$1(NioSctpServerChannel var1, InetAddress var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$localAddress = var2;
      this.val$promise = var3;
      super();
   }

   @Override
   public void run() {
      this.this$0.bindAddress(this.val$localAddress, this.val$promise);
   }
}
