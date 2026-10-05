package io.netty.channel;

public interface ChannelInboundHandler extends ChannelHandler {
   void channelActive(ChannelHandlerContext var1);

   void channelRegistered(ChannelHandlerContext var1);

   void channelRead(ChannelHandlerContext var1, Object var2);

   @Override
   void exceptionCaught(ChannelHandlerContext var1, Throwable var2);

   void channelUnregistered(ChannelHandlerContext var1);

   void channelInactive(ChannelHandlerContext var1);

   void userEventTriggered(ChannelHandlerContext var1, Object var2);

   void channelWritabilityChanged(ChannelHandlerContext var1);

   void channelReadComplete(ChannelHandlerContext var1);
}
