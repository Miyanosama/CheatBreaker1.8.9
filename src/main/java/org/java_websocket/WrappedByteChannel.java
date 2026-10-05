package org.java_websocket;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public interface WrappedByteChannel extends ByteChannel {
   boolean isNeedWrite();

   int readMore(ByteBuffer var1) throws java.io.IOException ;

   boolean isBlocking();

   boolean isNeedRead();

   void writeMore() throws java.io.IOException ;
}
