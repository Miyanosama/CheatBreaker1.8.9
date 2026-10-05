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
   public ChannelPromiseAggregator __junk3667308286379079201;
   public ConfigManager __junk371290788815412592;

   public static SslContext newServerContext(File var0, File var1) {
      return newServerContext(null, var0, var1, null, null, null, 940085632L & 1099121268L, 1094746498L & -3626004250008591828L);
   }

   public static SslContext newClientContext(File var0, TrustManagerFactory var1, Iterable<String> var2, Iterable<String> var3, long var4, long var6) {
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

   public static SslContext newClientContext(SslProvider var0, File var1) {
      return newClientContext(var0, var1, null, null, null, 308843776L & -405982577149476713L, 286785536L & 536873912L);
   }

   public static SslContext newServerContext(SslProvider var0, File var1, File var2) {
      return newServerContext(var0, var1, var2, null, null, null, 549602866L & 603593538183102852L, 6495056685384743209L & -6495056685704150336L);
   }

   public SslHandler newHandler(ByteBufAllocator var1, String var2, int var3) {
      return newHandler(this.newEngine(var1, var2, var3));
   }

   public abstract SSLEngine newEngine(ByteBufAllocator var1);

   public abstract boolean isClient();

   public static SslContext newServerContext(File var0, File var1, String var2, Iterable<String> var3, Iterable<String> var4, long var5, long var7) {
      return newServerContext(null, var0, var1, var2, var3, var4, var5, var7);
   }

   public static SslContext newClientContext(SslProvider var0, File var1, TrustManagerFactory var2) {
      return newClientContext(var0, var1, var2, null, null, 1644512464L & 93982721L, 8199323249686086656L & 637585408L);
   }

   public static SslContext newClientContext(SslProvider var0) {
      return newClientContext(var0, null, null, null, null, 296394756L & -3969142014409945032L, 218106976L & 7334180813581660288L);
   }

   public static SslContext newClientContext(
      SslProvider var0, File var1, TrustManagerFactory var2, Iterable<String> var3, Iterable<String> var4, long var5, long var7
   ) {
      if (var0 != null && var0 != SslProvider.JDK) {
         throw new SSLException("client context unsupported for: " + var0);
      } else {
         return new JdkSslClientContext(var1, var2, var3, var4, var5, var7);
      }
   }

   public static SslContext newServerContext(
      SslProvider var0, File var1, File var2, String var3, Iterable<String> var4, Iterable<String> var5, long var6, long var8
   ) {
      if (var0 == null) {
         var0 = OpenSsl.isAvailable() ? SslProvider.OPENSSL : SslProvider.JDK;
      }

      switch (SslContext$1.$SwitchMap$io$netty$handler$ssl$SslProvider[var0.ordinal()]) {
         case 1:
            return new JdkSslServerContext(var1, var2, var3, var4, var5, var6, var8);
         case 2:
            return new OpenSslServerContext(var1, var2, var3, var4, var5, var6, var8);
         default:
            throw new Error(var0.toString());
      }
   }

   public static SslContext newClientContext(File var0, TrustManagerFactory var1) {
      return newClientContext(null, var0, var1, null, null, 94913264L & 1078985728L, 1620345238L & 503320640L);
   }

   public SslHandler newHandler(ByteBufAllocator var1) {
      return newHandler(this.newEngine(var1));
   }

   public static SslHandler newHandler(SSLEngine var0) {
      return new SslHandler(var0);
   }

   public static SslContext newClientContext(SslProvider var0, TrustManagerFactory var1) {
      return newClientContext(var0, null, var1, null, null, 1099706466L & 202490132L, 8463792044653052992L & -8463792046423924060L);
   }

   public static SslProvider defaultClientProvider() {
      return SslProvider.JDK;
   }

   public static SslContext newClientContext(TrustManagerFactory var0) {
      return newClientContext(null, null, var0, null, null, 67666000L & 90625L, -1614644904973450876L & 1614644903105926154L);
   }

   public abstract List<String> nextProtocols();

   public static SslContext newClientContext() {
      return newClientContext(null, null, null, null, null, 67272972L & -8168469595611383806L, 8327074315241603456L & 75805738L);
   }

   public static SslContext newServerContext(File var0, File var1, String var2) {
      return newServerContext(null, var0, var1, var2, null, null, 440607244L & 25740352L, -1731217438377033646L & 442531976L);
   }

   public static SslContext newServerContext(SslProvider var0, File var1, File var2, String var3) {
      return newServerContext(var0, var1, var2, var3, null, null, 176198176L & 1613761680L, 1795188819L & 6232222347300569088L);
   }

   public static SslContext newClientContext(File var0) {
      return newClientContext(null, var0, null, null, null, 3667194958979138625L & -3667194959122900992L, 1287657538L & -7563382067566697984L);
   }

   public abstract SSLEngine newEngine(ByteBufAllocator var1, String var2, int var3);

   public abstract List<String> cipherSuites();
}
