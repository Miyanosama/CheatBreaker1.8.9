package io.netty.channel;

import java.net.SocketAddress;

public interface ChannelOutboundHandler extends ChannelHandler {
   void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3);

   void close(ChannelHandlerContext var1, ChannelPromise var2);

   void read(ChannelHandlerContext var1);

   void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4);

   void deregister(ChannelHandlerContext var1, ChannelPromise var2);

   void flush(ChannelHandlerContext var1);

   void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3);

   void disconnect(ChannelHandlerContext var1, ChannelPromise var2);
}
