package io.netty.channel;

public interface ChannelHandler {
   void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception ;

   void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception ;

   void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception ;

   public @interface Sharable {
   }
}
