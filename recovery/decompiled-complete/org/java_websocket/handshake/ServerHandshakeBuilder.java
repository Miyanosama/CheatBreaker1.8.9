package org.java_websocket.handshake;

public interface ServerHandshakeBuilder extends HandshakeBuilder, ServerHandshake {
   void setHttpStatusMessage(String var1);

   void setHttpStatus(short var1);
}
