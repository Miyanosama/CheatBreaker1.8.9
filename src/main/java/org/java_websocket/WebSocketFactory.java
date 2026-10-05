package org.java_websocket;

import java.util.List;
import org.java_websocket.drafts.Draft;

public interface WebSocketFactory {
   WebSocket createWebSocket(WebSocketAdapter var1, Draft var2);

   WebSocket createWebSocket(WebSocketAdapter var1, List<Draft> var2);
}
