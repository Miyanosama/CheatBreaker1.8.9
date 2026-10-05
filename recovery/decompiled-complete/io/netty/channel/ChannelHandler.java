package io.netty.channel;

public interface ChannelHandler {
   void exceptionCaught(ChannelHandlerContext var1, Throwable var2);

   void handlerRemoved(ChannelHandlerContext var1);

   void handlerAdded(ChannelHandlerContext var1);
}
