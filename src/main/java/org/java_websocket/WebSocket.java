package org.java_websocket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Collection;
import javax.net.ssl.SSLSession;
import org.java_websocket.drafts.Draft;
import org.java_websocket.enums.Opcode;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.framing.Framedata;

public interface WebSocket {
   void sendFrame(Collection<Framedata> var1);

   SSLSession getSSLSession() throws java.lang.IllegalArgumentException ;

   boolean hasBufferedData();

   void sendFrame(Framedata var1);

   void sendFragmentedFrame(Opcode var1, ByteBuffer var2, boolean var3);

   <T> T getAttachment();

   <T> void setAttachment(T var1);

   String getResourceDescriptor();

   InetSocketAddress method_09027();

   ReadyState getReadyState();

   boolean isFlushAndClose();

   boolean isClosed();

   boolean isOpen();

   boolean isClosing();

   void sendPing();

   Draft getDraft();

   void send(String var1);

   void closeConnection(int var1, String var2);

   void close(int var1, String var2);

   void send(byte[] var1);

   void send(ByteBuffer var1);

   void close();

   boolean hasSSLSupport();

   InetSocketAddress method_09011();

   void close(int var1);
}
