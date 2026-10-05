package io.netty.channel;

public interface ChannelInboundHandler extends ChannelHandler {
   void channelActive(ChannelHandlerContext var1) throws java.lang.Exception ;

   void channelRegistered(ChannelHandlerContext var1) throws java.lang.Exception ;

   void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception ;

   @Override
   void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception ;

   void channelUnregistered(ChannelHandlerContext var1) throws java.lang.Exception ;

   void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception ;

   void userEventTriggered(ChannelHandlerContext var1, Object var2) throws java.lang.Exception ;

   void channelWritabilityChanged(ChannelHandlerContext var1) throws java.lang.Exception ;

   void channelReadComplete(ChannelHandlerContext var1) throws java.lang.Exception ;
}
