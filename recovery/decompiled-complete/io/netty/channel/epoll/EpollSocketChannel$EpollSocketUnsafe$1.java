package io.netty.channel.epoll;

import io.netty.channel.ChannelPromise;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.local.LocalAddress;
import io.netty.handler.codec.serialization.CachingClassResolver;
import java.net.SocketAddress;
import net.minecraft.client.gui.GuiKeyBindingList$CategoryEntry;

public class EpollSocketChannel$EpollSocketUnsafe$1 implements Runnable {
   public LocalAddress __junk3099575905468146304;
   public CachingClassResolver __junk5180369660578403832;
   public GuiKeyBindingList$CategoryEntry __junk2741949593817578129;

   public EpollSocketChannel$EpollSocketUnsafe$1(EpollSocketChannel$EpollSocketUnsafe var1, SocketAddress var2) {
      this.this$1 = var1;
      this.val$remoteAddress = var2;
      super();
   }

   @Override
   public void run() {
      ChannelPromise var1 = EpollSocketChannel.access$100(this.this$1.this$0);
      ConnectTimeoutException var2 = new ConnectTimeoutException("connection timed out: " + this.val$remoteAddress);
      if (var1 != null && var1.tryFailure(var2)) {
         this.this$1.close(this.this$1.voidPromise());
      }
   }
}
