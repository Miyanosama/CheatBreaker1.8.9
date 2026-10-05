package io.netty.handler.codec.spdy;

public interface SpdyGoAwayFrame extends SpdyFrame {
   SpdySessionStatus status();

   SpdyGoAwayFrame setStatus(SpdySessionStatus var1);

   int lastGoodStreamId();

   SpdyGoAwayFrame setLastGoodStreamId(int var1);
}
