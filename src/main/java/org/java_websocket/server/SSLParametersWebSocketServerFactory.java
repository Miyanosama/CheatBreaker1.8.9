package org.java_websocket.server;

import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import org.java_websocket.SSLSocketChannel2;

public class SSLParametersWebSocketServerFactory extends DefaultSSLWebSocketServerFactory {
   public SSLParameters sslParameters;

   public SSLParametersWebSocketServerFactory(SSLContext var1, ExecutorService var2, SSLParameters var3) {
      super(var1, var2);
      if (var3 == null) {
         throw new IllegalArgumentException();
      } else {
         this.sslParameters = var3;
      }
   }

   @Override
   public ByteChannel wrapChannel(SocketChannel var1, SelectionKey var2) throws java.io.IOException {
      SSLEngine var3 = this.sslcontext.createSSLEngine();
      var3.setUseClientMode(false);
      var3.setSSLParameters(this.sslParameters);
      return new SSLSocketChannel2(var1, var3, this.exec, var2);
   }

   public SSLParametersWebSocketServerFactory(SSLContext var1, SSLParameters var2) {
      this(var1, Executors.newSingleThreadScheduledExecutor(), var2);
   }
}
