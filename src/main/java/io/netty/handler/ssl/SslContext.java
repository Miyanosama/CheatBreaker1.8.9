package io.netty.handler.ssl;

import com.cheatbreaker.client.config.ConfigManager;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelPromiseAggregator;
import java.io.File;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManagerFactory;

public abstract class SslContext {

   public static SslContext newServerContext(File var0, File var1) throws javax.net.ssl.SSLException {
      return newServerContext(null, var0, var1, null, null, null, 0L, 0L);
   }

   public static SslContext newClientContext(File var0, TrustManagerFactory var1, Iterable<String> var2, Iterable<String> var3, long var4, long var6) throws javax.net.ssl.SSLException {
      return newClientContext(null, var0, var1, var2, var3, var4, var6);
   }

   public abstract long sessionCacheSize();

   public static SslProvider defaultServerProvider() {
      return OpenSsl.isAvailable() ? SslProvider.OPENSSL : SslProvider.JDK;
   }

   public abstract long sessionTimeout();

   public boolean isServer() {
      return !this.isClient();
   }

   public static SslContext newClientContext(SslProvider var0, File var1) throws javax.net.ssl.SSLException {
      return newClientContext(var0, var1, null, null, null, 0L, 0L);
   }

   public static SslContext newServerContext(SslProvider var0, File var1, File var2) throws javax.net.ssl.SSLException {
      return newServerContext(var0, var1, var2, null, null, null, 0L, 0L);
   }

   public SslHandler newHandler(ByteBufAllocator var1, String var2, int var3) {
      return newHandler(this.newEngine(var1, var2, var3));
   }

   public abstract SSLEngine newEngine(ByteBufAllocator var1);

   public abstract boolean isClient();

   public static SslContext newServerContext(File var0, File var1, String var2, Iterable<String> var3, Iterable<String> var4, long var5, long var7) throws javax.net.ssl.SSLException {
      return newServerContext(null, var0, var1, var2, var3, var4, var5, var7);
   }

   public static SslContext newClientContext(SslProvider var0, File var1, TrustManagerFactory var2) throws javax.net.ssl.SSLException {
      return newClientContext(var0, var1, var2, null, null, 0L, 0L);
   }

   public static SslContext newClientContext(SslProvider var0) throws javax.net.ssl.SSLException {
      return newClientContext(var0, null, null, null, null, 0L, 0L);
   }

   public static SslContext newClientContext(
      SslProvider var0, File var1, TrustManagerFactory var2, Iterable<String> var3, Iterable<String> var4, long var5, long var7
   ) throws javax.net.ssl.SSLException{
      if (var0 != null && var0 != SslProvider.JDK) {
         throw new SSLException("client context unsupported for: " + var0);
      } else {
         return new JdkSslClientContext(var1, var2, var3, var4, var5, var7);
      }
   }

   public static SslContext newServerContext(
      SslProvider var0, File var1, File var2, String var3, Iterable<String> var4, Iterable<String> var5, long var6, long var8
   ) throws javax.net.ssl.SSLException{
      if (var0 == null) {
         var0 = OpenSsl.isAvailable() ? SslProvider.OPENSSL : SslProvider.JDK;
      }

      switch (var0) {
         case JDK:
            return new JdkSslServerContext(var1, var2, var3, var4, var5, var6, var8);
         case OPENSSL:
            return new OpenSslServerContext(var1, var2, var3, var4, var5, var6, var8);
         default:
            throw new Error(var0.toString());
      }
   }

   public static SslContext newClientContext(File var0, TrustManagerFactory var1) throws javax.net.ssl.SSLException {
      return newClientContext(null, var0, var1, null, null, 0L, 0L);
   }

   public SslHandler newHandler(ByteBufAllocator var1) {
      return newHandler(this.newEngine(var1));
   }

   public static SslHandler newHandler(SSLEngine var0) {
      return new SslHandler(var0);
   }

   public static SslContext newClientContext(SslProvider var0, TrustManagerFactory var1) throws javax.net.ssl.SSLException {
      return newClientContext(var0, null, var1, null, null, 0L, 0L);
   }

   public static SslProvider defaultClientProvider() {
      return SslProvider.JDK;
   }

   public static SslContext newClientContext(TrustManagerFactory var0) throws javax.net.ssl.SSLException {
      return newClientContext(null, null, var0, null, null, 0L, 0L);
   }

   public abstract List<String> nextProtocols();

   public static SslContext newClientContext() throws javax.net.ssl.SSLException {
      return newClientContext(null, null, null, null, null, 0L, 0L);
   }

   public static SslContext newServerContext(File var0, File var1, String var2) throws javax.net.ssl.SSLException {
      return newServerContext(null, var0, var1, var2, null, null, 0L, 0L);
   }

   public static SslContext newServerContext(SslProvider var0, File var1, File var2, String var3) throws javax.net.ssl.SSLException {
      return newServerContext(var0, var1, var2, var3, null, null, 0L, 0L);
   }

   public static SslContext newClientContext(File var0) throws javax.net.ssl.SSLException {
      return newClientContext(null, var0, null, null, null, 0L, 0L);
   }

   public abstract SSLEngine newEngine(ByteBufAllocator var1, String var2, int var3);

   public abstract List<String> cipherSuites();
}
