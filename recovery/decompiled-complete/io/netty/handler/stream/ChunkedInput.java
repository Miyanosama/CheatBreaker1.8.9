package io.netty.handler.stream;

import io.netty.channel.ChannelHandlerContext;

public interface ChunkedInput<B> {
   B readChunk(ChannelHandlerContext var1);

   void close();

   boolean isEndOfInput();
}
