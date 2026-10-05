package io.netty.channel.socket.nio;

import io.netty.channel.socket.DefaultSocketChannelConfig;
import java.net.Socket;
import net.minecraft.client.network.NetHandlerPlayClient$4;
import net.minecraft.client.stream.BroadcastController$1;

public class NioSocketChannel$NioSocketChannelConfig extends DefaultSocketChannelConfig {
   public BroadcastController$1 __junk9188949621685622685;
   public NetHandlerPlayClient$4 __junk4727717406674404040;

   public NioSocketChannel$NioSocketChannelConfig(NioSocketChannel var1, NioSocketChannel var2, Socket var3) {
      this.this$0 = var1;
      super(var2, var3);
   }

   @Override
   public void autoReadCleared() {
      NioSocketChannel.access$100(this.this$0, false);
   }
}
