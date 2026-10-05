package io.netty.handler.codec.spdy;

public interface SpdySynStreamFrame extends SpdyHeadersFrame {
   int associatedStreamId();

   SpdySynStreamFrame setUnidirectional(boolean var1);

   SpdySynStreamFrame setLast(boolean var1);

   SpdySynStreamFrame setAssociatedStreamId(int var1);

   SpdySynStreamFrame setPriority(byte var1);

   SpdySynStreamFrame setStreamId(int var1);

   boolean isUnidirectional();

   SpdySynStreamFrame setInvalid();

   byte priority();
}
