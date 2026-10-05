package io.netty.channel;

import io.netty.util.concurrent.EventExecutorGroup;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public interface ChannelPipeline extends Iterable<Entry<String, ChannelHandler>> {
   <T extends ChannelHandler> T remove(Class<T> var1);

   ChannelFuture write(Object var1, ChannelPromise var2);

   ChannelHandler removeLast();

   ChannelPipeline addLast(EventExecutorGroup var1, String var2, ChannelHandler var3);

   ChannelHandler remove(String var1);

   ChannelPipeline replace(ChannelHandler var1, String var2, ChannelHandler var3);

   ChannelHandlerContext lastContext();

   ChannelPipeline addAfter(String var1, String var2, ChannelHandler var3);

   ChannelPipeline fireChannelInactive();

   ChannelFuture deregister(ChannelPromise var1);

   ChannelFuture close(ChannelPromise var1);

   ChannelPipeline fireChannelUnregistered();

   <T extends ChannelHandler> T replace(Class<T> var1, String var2, ChannelHandler var3);

   ChannelFuture bind(SocketAddress var1);

   List<String> names();

   ChannelPipeline fireChannelReadComplete();

   Map<String, ChannelHandler> toMap();

   ChannelHandlerContext context(Class<? extends ChannelHandler> var1);

   ChannelFuture writeAndFlush(Object var1, ChannelPromise var2);

   ChannelPipeline fireChannelWritabilityChanged();

   ChannelFuture connect(SocketAddress var1);

   ChannelPipeline fireExceptionCaught(Throwable var1);

   ChannelPipeline addFirst(String var1, ChannelHandler var2);

   ChannelHandlerContext context(ChannelHandler var1);

   ChannelHandler replace(String var1, String var2, ChannelHandler var3);

   ChannelPipeline read();

   ChannelFuture connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3);

   ChannelHandlerContext firstContext();

   ChannelPipeline addAfter(EventExecutorGroup var1, String var2, String var3, ChannelHandler var4);

   ChannelFuture writeAndFlush(Object var1);

   Channel channel();

   ChannelPipeline addBefore(String var1, String var2, ChannelHandler var3);

   ChannelFuture write(Object var1);

   ChannelFuture disconnect(ChannelPromise var1);

   ChannelHandlerContext context(String var1);

   ChannelPipeline addLast(ChannelHandler... var1);

   ChannelHandler first();

   ChannelFuture deregister();

   ChannelFuture disconnect();

   ChannelFuture connect(SocketAddress var1, SocketAddress var2);

   ChannelFuture bind(SocketAddress var1, ChannelPromise var2);

   ChannelFuture close();

   ChannelHandler get(String var1);

   ChannelHandler removeFirst();

   ChannelPipeline addFirst(EventExecutorGroup var1, String var2, ChannelHandler var3);

   ChannelPipeline addFirst(ChannelHandler... var1);

   ChannelPipeline addLast(String var1, ChannelHandler var2);

   ChannelHandler last();

   ChannelPipeline addLast(EventExecutorGroup var1, ChannelHandler... var2);

   ChannelPipeline fireChannelRead(Object var1);

   ChannelPipeline flush();

   ChannelFuture connect(SocketAddress var1, ChannelPromise var2);

   <T extends ChannelHandler> T get(Class<T> var1);

   ChannelPipeline fireUserEventTriggered(Object var1);

   ChannelPipeline fireChannelRegistered();

   ChannelPipeline addFirst(EventExecutorGroup var1, ChannelHandler... var2);

   ChannelPipeline addBefore(EventExecutorGroup var1, String var2, String var3, ChannelHandler var4);

   ChannelPipeline fireChannelActive();

   ChannelPipeline remove(ChannelHandler var1);
}
