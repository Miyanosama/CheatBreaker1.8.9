package io.netty.handler.ssl;

import java.nio.ByteBuffer;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import net.minecraft.client.model.ModelChest;
import net.minecraft.network.ServerStatusResponse;
import org.eclipse.jetty.npn.NextProtoNego;
import org.eclipse.jetty.npn.NextProtoNego.ClientProvider;
import org.eclipse.jetty.npn.NextProtoNego.ServerProvider;

public class JettyNpnSslEngine extends SSLEngine {
   public JettyNpnSslSession session;
   public static final boolean $assertionsDisabled = !JettyNpnSslEngine.class.desiredAssertionStatus();
   public SSLEngine engine;
   public static boolean available;

   @Override
   public SSLEngineResult unwrap(ByteBuffer var1, ByteBuffer var2) throws javax.net.ssl.SSLException {
      return this.engine.unwrap(var1, var2);
   }

   @Override
   public void beginHandshake() throws javax.net.ssl.SSLException {
      this.engine.beginHandshake();
   }

   @Override
   public SSLEngineResult wrap(ByteBuffer[] var1, ByteBuffer var2) throws javax.net.ssl.SSLException {
      return this.engine.wrap(var1, var2);
   }

   @Override
   public String[] getEnabledCipherSuites() {
      return this.engine.getEnabledCipherSuites();
   }

   @Override
   public SSLEngineResult wrap(ByteBuffer var1, ByteBuffer var2) throws javax.net.ssl.SSLException {
      return this.engine.wrap(var1, var2);
   }

   @Override
   public String[] getSupportedCipherSuites() {
      return this.engine.getSupportedCipherSuites();
   }

   @Override
   public SSLEngineResult unwrap(ByteBuffer var1, ByteBuffer[] var2, int var3, int var4) throws javax.net.ssl.SSLException {
      return this.engine.unwrap(var1, var2, var3, var4);
   }

   @Override
   public SSLSession getHandshakeSession() {
      return this.engine.getHandshakeSession();
   }

   @Override
   public Runnable getDelegatedTask() {
      return this.engine.getDelegatedTask();
   }

   @Override
   public void setUseClientMode(boolean var1) {
      this.engine.setUseClientMode(var1);
   }

   @Override
   public void setNeedClientAuth(boolean var1) {
      this.engine.setNeedClientAuth(var1);
   }

   @Override
   public String getPeerHost() {
      return this.engine.getPeerHost();
   }

   @Override
   public void setEnableSessionCreation(boolean var1) {
      this.engine.setEnableSessionCreation(var1);
   }

   @Override
   public SSLEngineResult unwrap(ByteBuffer var1, ByteBuffer[] var2) throws javax.net.ssl.SSLException {
      return this.engine.unwrap(var1, var2);
   }

   @Override
   public void closeInbound() throws javax.net.ssl.SSLException {
      NextProtoNego.remove(this.engine);
      this.engine.closeInbound();
   }

   @Override
   public String[] getSupportedProtocols() {
      return this.engine.getSupportedProtocols();
   }

   @Override
   public String[] getEnabledProtocols() {
      return this.engine.getEnabledProtocols();
   }

   @Override
   public boolean getEnableSessionCreation() {
      return this.engine.getEnableSessionCreation();
   }

   @Override
   public boolean isInboundDone() {
      return this.engine.isInboundDone();
   }

   public JettyNpnSslEngine(SSLEngine var1, final List<String> var2, boolean var3) {
      if (!$assertionsDisabled && var2.isEmpty()) {
         throw new AssertionError();
      } else {
         this.engine = var1;
         this.session = new JettyNpnSslSession(var1);
         if (var3) {
            NextProtoNego.put(var1, new ServerProvider() {

               @Override
               public void unsupported() {
                  JettyNpnSslEngine.this.getSession().setApplicationProtocol((String)var2.get(var2.size() - 1));
               }

               @Override
               public List<String> protocols() {
                  return var2;
               }

               @Override
               public void protocolSelected(String var1) {
                  JettyNpnSslEngine.this.getSession().setApplicationProtocol(var1);
               }
            });
         } else {
            final String[] var4 = (String[])var2.toArray(new String[var2.size()]);
            final String var5 = var4[var4.length - 1];
            NextProtoNego.put(var1, new ClientProvider() {
               @Override
               public String selectProtocol(List<String> var1) {
                  for (String var5x : var4) {
                     if (var1.contains(var5x)) {
                        return var5x;
                     }
                  }

                  return var5;
               }

               @Override
               public void unsupported() {
                  JettyNpnSslEngine.this.session.setApplicationProtocol(null);
               }

               @Override
               public boolean supports() {
                  return true;
               }
            });
         }
      }
   }

   @Override
   public SSLEngineResult wrap(ByteBuffer[] var1, int var2, int var3, ByteBuffer var4) throws javax.net.ssl.SSLException {
      return this.engine.wrap(var1, var2, var3, var4);
   }

   @Override
   public void setSSLParameters(SSLParameters var1) {
      this.engine.setSSLParameters(var1);
   }

   @Override
   public void setWantClientAuth(boolean var1) {
      this.engine.setWantClientAuth(var1);
   }

   @Override
   public void setEnabledProtocols(String[] var1) {
      this.engine.setEnabledProtocols(var1);
   }

   public JettyNpnSslSession getSession() {
      return this.session;
   }

   public static boolean isAvailable() {
      updateAvailability();
      return available;
   }

   @Override
   public boolean isOutboundDone() {
      return this.engine.isOutboundDone();
   }

   @Override
   public SSLParameters getSSLParameters() {
      return this.engine.getSSLParameters();
   }

   @Override
   public HandshakeStatus getHandshakeStatus() {
      return this.engine.getHandshakeStatus();
   }

   @Override
   public void setEnabledCipherSuites(String[] var1) {
      this.engine.setEnabledCipherSuites(var1);
   }

   @Override
   public boolean getWantClientAuth() {
      return this.engine.getWantClientAuth();
   }

   @Override
   public void closeOutbound() {
      NextProtoNego.remove(this.engine);
      this.engine.closeOutbound();
   }

   @Override
   public boolean getNeedClientAuth() {
      return this.engine.getNeedClientAuth();
   }

   @Override
   public boolean getUseClientMode() {
      return this.engine.getUseClientMode();
   }

   @Override
   public int getPeerPort() {
      return this.engine.getPeerPort();
   }

   public static void updateAvailability() {
      if (!available) {
         try {
            ClassLoader var0 = ClassLoader.getSystemClassLoader().getParent();
            if (var0 == null) {
               var0 = ClassLoader.getSystemClassLoader();
            }

            Class.forName("sun.security.ssl.NextProtoNegoExtension", true, var0);
            available = true;
         } catch (Exception var1) {
         }
      }
   }
}
