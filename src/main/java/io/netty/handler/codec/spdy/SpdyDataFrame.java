package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;

public interface SpdyDataFrame extends ByteBufHolder, SpdyStreamFrame {
   SpdyDataFrame duplicate();

   SpdyDataFrame retain();

   SpdyDataFrame setLast(boolean var1);

   SpdyDataFrame copy();

   SpdyDataFrame setStreamId(int var1);

   SpdyDataFrame retain(int var1);

   @Override
   ByteBuf content();
}
