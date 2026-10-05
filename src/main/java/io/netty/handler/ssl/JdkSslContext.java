package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AbstractChannelHandlerContext;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSessionContext;
import net.minecraft.item.ItemEgg;

public abstract class JdkSslContext extends SslContext {
   public static List<String> DEFAULT_CIPHERS;
   public static final String PROTOCOL = "TLS";
   public static String[] PROTOCOLS;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(JdkSslContext.class);
   public String[] cipherSuites;
   public List<String> unmodifiableCipherSuites;

   @Override
   public SSLEngine newEngine(ByteBufAllocator var1) {
      SSLEngine var2 = this.context().createSSLEngine();
      var2.setEnabledCipherSuites(this.cipherSuites);
      var2.setEnabledProtocols(PROTOCOLS);
      var2.setUseClientMode(this.isClient());
      return this.wrapEngine(var2);
   }

   public SSLSessionContext sessionContext() {
      return this.isServer() ? this.context().getServerSessionContext() : this.context().getClientSessionContext();
   }

   public JdkSslContext(Iterable<String> var1) {
      this.cipherSuites = toCipherSuiteArray(var1);
      this.unmodifiableCipherSuites = Collections.unmodifiableList(Arrays.asList(this.cipherSuites));
   }

   @Override
   public List<String> cipherSuites() {
      return this.unmodifiableCipherSuites;
   }

   public static String[] toCipherSuiteArray(Iterable<String> var0) {
      if (var0 == null) {
         return (String[])DEFAULT_CIPHERS.toArray(new String[DEFAULT_CIPHERS.size()]);
      } else {
         ArrayList var1 = new ArrayList();

         for (String var3 : var0) {
            if (var3 == null) {
               break;
            }

            var1.add(var3);
         }

         return (java.lang.String[])var1.toArray(new String[var1.size()]);
      }
   }

   public abstract SSLContext context();

   @Override
   public SSLEngine newEngine(ByteBufAllocator var1, String var2, int var3) {
      SSLEngine var4 = this.context().createSSLEngine(var2, var3);
      var4.setEnabledCipherSuites(this.cipherSuites);
      var4.setEnabledProtocols(PROTOCOLS);
      var4.setUseClientMode(this.isClient());
      return this.wrapEngine(var4);
   }

   @Override
   public long sessionCacheSize() {
      return this.sessionContext().getSessionCacheSize();
   }

   @Override
   public long sessionTimeout() {
      return this.sessionContext().getSessionTimeout();
   }

   static {
      SSLContext var0;
      try {
         var0 = SSLContext.getInstance("TLS");
         var0.init(null, null, null);
      } catch (Exception var6) {
         throw new Error("failed to initialize the default SSL context", var6);
      }

      SSLEngine var1 = var0.createSSLEngine();
      String[] var2 = var1.getSupportedProtocols();
      ArrayList var3 = new ArrayList();
      addIfSupported(var2, var3, "TLSv1.2", "TLSv1.1", "TLSv1", "SSLv3");
      if (!var3.isEmpty()) {
         PROTOCOLS = (java.lang.String[])var3.toArray(new String[var3.size()]);
      } else {
         PROTOCOLS = var1.getEnabledProtocols();
      }

      String[] var4 = var1.getSupportedCipherSuites();
      ArrayList var5 = new ArrayList();
      addIfSupported(
         var4,
         var5,
         "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256",
         "TLS_ECDHE_RSA_WITH_RC4_128_SHA",
         "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA",
         "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA",
         "TLS_RSA_WITH_AES_128_GCM_SHA256",
         "SSL_RSA_WITH_RC4_128_SHA",
         "SSL_RSA_WITH_RC4_128_MD5",
         "TLS_RSA_WITH_AES_128_CBC_SHA",
         "TLS_RSA_WITH_AES_256_CBC_SHA",
         "SSL_RSA_WITH_DES_CBC_SHA"
      );
      if (!var5.isEmpty()) {
         DEFAULT_CIPHERS = Collections.unmodifiableList(var5);
      } else {
         DEFAULT_CIPHERS = Collections.unmodifiableList(Arrays.asList(var1.getEnabledCipherSuites()));
      }

      if (logger.isDebugEnabled()) {
         logger.debug("Default protocols (JDK): {} ", Arrays.asList(PROTOCOLS));
         logger.debug("Default cipher suites (JDK): {}", DEFAULT_CIPHERS);
      }
   }

   public SSLEngine wrapEngine(SSLEngine var1) {
      return (SSLEngine)(this.nextProtocols().isEmpty() ? var1 : new JettyNpnSslEngine(var1, this.nextProtocols(), this.isServer()));
   }

   public static void addIfSupported(String[] var0, List<String> var1, String... var2) {
      for (String var6 : var2) {
         for (String var10 : var0) {
            if (var6.equals(var10)) {
               var1.add(var10);
               break;
            }
         }
      }
   }
}
