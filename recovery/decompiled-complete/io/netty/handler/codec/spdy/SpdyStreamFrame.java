package io.netty.handler.codec.spdy;

public interface SpdyStreamFrame extends SpdyFrame {
   SpdyStreamFrame setStreamId(int var1);

   boolean isLast();

   SpdyStreamFrame setLast(boolean var1);

   int streamId();
}
