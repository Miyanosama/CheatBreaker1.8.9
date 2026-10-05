package io.netty.handler.codec.http;

import io.netty.buffer.ByteBufHolder;

public interface HttpContent extends ByteBufHolder, HttpObject {
   HttpContent retain(int var1);

   HttpContent retain();

   HttpContent copy();

   HttpContent duplicate();
}
