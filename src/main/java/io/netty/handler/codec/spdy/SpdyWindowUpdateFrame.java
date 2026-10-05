package io.netty.handler.codec.spdy;

public interface SpdyWindowUpdateFrame extends SpdyFrame {
   SpdyWindowUpdateFrame setStreamId(int var1);

   SpdyWindowUpdateFrame setDeltaWindowSize(int var1);

   int streamId();

   int deltaWindowSize();
}
