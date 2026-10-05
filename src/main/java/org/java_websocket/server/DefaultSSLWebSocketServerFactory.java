package org.java_websocket.server;

import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import org.java_websocket.SSLSocketChannel2;
import org.java_websocket.WebSocketAdapter;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.WebSocketServerFactory;
import org.java_websocket.drafts.Draft;

public class DefaultSSLWebSocketServerFactory implements WebSocketServerFactory {
   public SSLContext sslcontext;
   public ExecutorService exec;

   public DefaultSSLWebSocketServerFactory(SSLContext var1) {
      this(var1, Executors.newSingleThreadScheduledExecutor());
   }

   public DefaultSSLWebSocketServerFactory(SSLContext var1, ExecutorService var2) {
      if (var1 != null && var2 != null) {
         this.sslcontext = var1;
         this.exec = var2;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public WebSocketImpl createWebSocket(WebSocketAdapter var1, List<Draft> var2) {
      return new WebSocketImpl(var1, var2);
   }

   @Override
   public void close() {
      this.exec.shutdown();
   }

   @Override
   public ByteChannel wrapChannel(SocketChannel var1, SelectionKey var2) throws java.io.IOException {
      SSLEngine var3 = this.sslcontext.createSSLEngine();
      ArrayList var4 = new ArrayList<>(Arrays.asList(var3.getEnabledCipherSuites()));
      var4.remove("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
      var3.setEnabledCipherSuites((java.lang.String[])var4.toArray(new String[var4.size()]));
      var3.setUseClientMode(false);
      return new SSLSocketChannel2(var1, var3, this.exec, var2);
   }

   @Override
   public WebSocketImpl createWebSocket(WebSocketAdapter var1, Draft var2) {
      return new WebSocketImpl(var1, var2);
   }
}
