package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.util.AttributeMap;
import io.netty.util.concurrent.EventExecutor;
import java.net.SocketAddress;

public interface ChannelHandlerContext extends AttributeMap {
   ChannelFuture deregister();

   ChannelFuture newFailedFuture(Throwable var1);

   ChannelFuture disconnect(ChannelPromise var1);

   String name();

   ChannelFuture write(Object var1);

   ChannelFuture newSucceededFuture();

   ChannelHandlerContext flush();

   ChannelFuture disconnect();

   ChannelPromise voidPromise();

   ChannelProgressivePromise newProgressivePromise();

   ChannelHandlerContext fireUserEventTriggered(Object var1);

   ChannelHandler handler();

   boolean isRemoved();

   ChannelPipeline pipeline();

   Channel channel();

   ChannelFuture close();

   EventExecutor executor();

   ByteBufAllocator alloc();

   ChannelFuture bind(SocketAddress var1, ChannelPromise var2);

   ChannelHandlerContext fireChannelRead(Object var1);

   ChannelFuture connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3);

   ChannelFuture write(Object var1, ChannelPromise var2);

   ChannelFuture connect(SocketAddress var1);

   ChannelHandlerContext fireChannelInactive();

   ChannelHandlerContext fireChannelActive();

   ChannelHandlerContext fireChannelRegistered();

   ChannelPromise newPromise();

   ChannelFuture deregister(ChannelPromise var1);

   ChannelFuture writeAndFlush(Object var1);

   ChannelFuture writeAndFlush(Object var1, ChannelPromise var2);

   ChannelFuture close(ChannelPromise var1);

   ChannelHandlerContext read();

   ChannelHandlerContext fireChannelReadComplete();

   ChannelHandlerContext fireChannelWritabilityChanged();

   ChannelFuture connect(SocketAddress var1, ChannelPromise var2);

   ChannelFuture bind(SocketAddress var1);

   ChannelHandlerContext fireChannelUnregistered();

   ChannelFuture connect(SocketAddress var1, SocketAddress var2);

   ChannelHandlerContext fireExceptionCaught(Throwable var1);
}
