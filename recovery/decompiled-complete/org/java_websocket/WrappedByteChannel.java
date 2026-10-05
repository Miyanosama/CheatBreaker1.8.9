package org.java_websocket;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public interface WrappedByteChannel extends ByteChannel {
   boolean isNeedWrite();

   int readMore(ByteBuffer var1);

   boolean isBlocking();

   boolean isNeedRead();

   void writeMore();
}
