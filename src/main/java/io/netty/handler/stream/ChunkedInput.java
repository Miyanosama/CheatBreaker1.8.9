package io.netty.handler.stream;

import io.netty.channel.ChannelHandlerContext;

public interface ChunkedInput<B> {
   B readChunk(ChannelHandlerContext var1) throws java.lang.Exception ;

   void close() throws java.lang.Exception ;

   boolean isEndOfInput() throws java.lang.Exception ;
}
