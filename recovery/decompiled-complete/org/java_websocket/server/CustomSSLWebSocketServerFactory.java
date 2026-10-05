package org.java_websocket.server;

import io.netty.channel.AbstractChannelHandlerContext$AbstractWriteTask;
import io.netty.handler.ssl.JdkSslServerContext;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import net.minecraft.item.crafting.RecipeRepairItem;
import net.minecraft.tileentity.TileEntityDaylightDetector;
import org.java_websocket.SSLSocketChannel2;
import recovered.unidentified.UnidentifiedClass5059;

public class CustomSSLWebSocketServerFactory extends DefaultSSLWebSocketServerFactory {
   public String[] enabledCiphersuites;
   public JdkSslServerContext field_0005;
   public AbstractChannelHandlerContext$AbstractWriteTask field_0002;
   public TileEntityDaylightDetector field_0004;
   public RecipeRepairItem field_0000;
   public UnidentifiedClass5059 field_0001;
   public String[] enabledProtocols;

   @Override
   public ByteChannel wrapChannel(SocketChannel var1, SelectionKey var2) {
      SSLEngine var3 = this.sslcontext.createSSLEngine();
      if (this.enabledProtocols != null) {
         var3.setEnabledProtocols(this.enabledProtocols);
      }

      if (this.enabledCiphersuites != null) {
         var3.setEnabledCipherSuites(this.enabledCiphersuites);
      }

      var3.setUseClientMode(false);
      return new SSLSocketChannel2(var1, var3, this.exec, var2);
   }

   public CustomSSLWebSocketServerFactory(SSLContext var1, String[] var2, String[] var3) {
      this(var1, Executors.newSingleThreadScheduledExecutor(), var2, var3);
   }

   public CustomSSLWebSocketServerFactory(SSLContext var1, ExecutorService var2, String[] var3, String[] var4) {
      super(var1, var2);
      this.enabledProtocols = var3;
      this.enabledCiphersuites = var4;
   }
}
