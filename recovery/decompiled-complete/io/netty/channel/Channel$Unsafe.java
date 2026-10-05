package io.netty.channel;

import java.net.SocketAddress;

public interface Channel$Unsafe {
   void disconnect(ChannelPromise var1);

   void flush();

   SocketAddress remoteAddress();

   void write(Object var1, ChannelPromise var2);

   SocketAddress localAddress();

   void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3);

   void beginRead();

   void close(ChannelPromise var1);

   void register(EventLoop var1, ChannelPromise var2);

   void bind(SocketAddress var1, ChannelPromise var2);

   ChannelOutboundBuffer outboundBuffer();

   void closeForcibly();

   ChannelPromise voidPromise();

   void deregister(ChannelPromise var1);
}
