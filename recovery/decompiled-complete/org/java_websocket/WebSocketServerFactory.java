package org.java_websocket;

import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.java_websocket.drafts.Draft;

public interface WebSocketServerFactory extends WebSocketFactory {
   WebSocketImpl createWebSocket(WebSocketAdapter var1, Draft var2);

   WebSocketImpl createWebSocket(WebSocketAdapter var1, List<Draft> var2);

   ByteChannel wrapChannel(SocketChannel var1, SelectionKey var2);

   void close();
}
