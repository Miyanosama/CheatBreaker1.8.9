package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.util.AttributeMap;
import java.net.SocketAddress;

public interface Channel extends AttributeMap, Comparable<Channel> {
   ChannelFuture disconnect(ChannelPromise var1);

   ChannelFuture closeFuture();

   ChannelConfig config();

   ChannelFuture writeAndFlush(Object var1);

   ChannelFuture connect(SocketAddress var1, ChannelPromise var2);

   ChannelFuture deregister();

   ChannelProgressivePromise newProgressivePromise();

   ChannelFuture connect(SocketAddress var1, SocketAddress var2);

   Channel flush();

   EventLoop eventLoop();

   SocketAddress remoteAddress();

   ChannelFuture deregister(ChannelPromise var1);

   ChannelFuture writeAndFlush(Object var1, ChannelPromise var2);

   ChannelFuture write(Object var1);

   ChannelFuture connect(SocketAddress var1);

   boolean isWritable();

   ChannelFuture close(ChannelPromise var1);

   ChannelMetadata metadata();

   ChannelFuture connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3);

   ChannelPromise newPromise();

   ChannelFuture write(Object var1, ChannelPromise var2);

   ChannelPipeline pipeline();

   Channel read();

   boolean isActive();

   Channel.Unsafe unsafe();

   ChannelFuture newSucceededFuture();

   ChannelFuture bind(SocketAddress var1);

   boolean isRegistered();

   ByteBufAllocator alloc();

   ChannelFuture newFailedFuture(Throwable var1);

   ChannelPromise voidPromise();

   ChannelFuture disconnect();

   ChannelFuture close();

   Channel parent();

   SocketAddress localAddress();

   boolean isOpen();

   ChannelFuture bind(SocketAddress var1, ChannelPromise var2);

   public interface Unsafe {
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
}
