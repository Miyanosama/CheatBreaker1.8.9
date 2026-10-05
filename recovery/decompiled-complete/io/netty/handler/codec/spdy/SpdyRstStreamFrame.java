package io.netty.handler.codec.spdy;

public interface SpdyRstStreamFrame extends SpdyStreamFrame {
   SpdyRstStreamFrame setStreamId(int var1);

   SpdyRstStreamFrame setLast(boolean var1);

   SpdyStreamStatus status();

   SpdyRstStreamFrame setStatus(SpdyStreamStatus var1);
}
