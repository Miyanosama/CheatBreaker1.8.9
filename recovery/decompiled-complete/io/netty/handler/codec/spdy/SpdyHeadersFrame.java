package io.netty.handler.codec.spdy;

public interface SpdyHeadersFrame extends SpdyStreamFrame {
   SpdyHeadersFrame setInvalid();

   SpdyHeadersFrame setTruncated();

   boolean isTruncated();

   boolean isInvalid();

   SpdyHeadersFrame setStreamId(int var1);

   SpdyHeaders headers();

   SpdyHeadersFrame setLast(boolean var1);
}
