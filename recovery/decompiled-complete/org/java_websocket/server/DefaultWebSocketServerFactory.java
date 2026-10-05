package org.java_websocket.server;

import io.netty.channel.AbstractChannelHandlerContext$15;
import io.netty.util.internal.chmv8.ForkJoinTask$RunnableExecuteAction;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.drafts.Draft;

public class DefaultWebSocketServerFactory implements WebSocketServerFactory {
   public ForkJoinTask$RunnableExecuteAction field_0000;
   public AbstractChannelHandlerContext$15 field_0001;

   public SocketChannel wrapChannel(SocketChannel var1, SelectionKey var2) {
      return var1;
   }

   @Override
   public void close() {
   }

   @Override
   public WebSocketImpl createWebSocket(WebSocketAdapter var1, List<Draft> var2) {
      return new WebSocketImpl(var1, var2);
   }

   @Override
   public WebSocketImpl createWebSocket(WebSocketAdapter var1, Draft var2) {
      return new WebSocketImpl(var1, var2);
   }
}
