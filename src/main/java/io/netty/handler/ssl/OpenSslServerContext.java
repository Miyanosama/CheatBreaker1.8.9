package io.netty.handler.ssl;

import com.cheatbreaker.client.util.cbagent.CBAgentResources;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import org.apache.tomcat.jni.Pool;
import org.apache.tomcat.jni.SSL;
import org.apache.tomcat.jni.SSLContext;

public class OpenSslServerContext extends SslContext {
   public long ctx;
   public List<String> nextProtocols;
   public long sessionCacheSize;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OpenSslServerContext.class);
   public List<String> ciphers = new ArrayList<>();
   public OpenSslSessionStats stats;
   public long aprPool;
   public long sessionTimeout;
   public static List<String> DEFAULT_CIPHERS;
   public List<String> unmodifiableCiphers = Collections.unmodifiableList(this.ciphers);

   @Override
   public long sessionTimeout() {
      return this.sessionTimeout;
   }

   public OpenSslSessionStats stats() {
      return this.stats;
   }

   @Override
   public boolean isClient() {
      return false;
   }

   @Override
   public long sessionCacheSize() {
      return this.sessionCacheSize;
   }

   static {
      ArrayList var0 = new ArrayList();
      Collections.addAll(
         var0,
         "ECDHE-RSA-AES128-GCM-SHA256",
         "ECDHE-RSA-RC4-SHA",
         "ECDHE-RSA-AES128-SHA",
         "ECDHE-RSA-AES256-SHA",
         "AES128-GCM-SHA256",
         "RC4-SHA",
         "RC4-MD5",
         "AES128-SHA",
         "AES256-SHA",
         "DES-CBC3-SHA"
      );
      DEFAULT_CIPHERS = Collections.unmodifiableList(var0);
      if (logger.isDebugEnabled()) {
         logger.debug("Default cipher suite (OpenSSL): " + var0);
      }
   }

   public void setTicketKeys(byte[] var1) {
      if (var1 == null) {
         throw new NullPointerException("keys");
      } else {
         SSLContext.setSessionTicketKeys(this.ctx, var1);
      }
   }

   @Override
   public void finalize() throws java.lang.Throwable {
      super.finalize();
      synchronized (OpenSslServerContext.class) {
         if (this.ctx != 0L) {
            SSLContext.free(this.ctx);
         }
      }

      this.destroyPools();
   }

   public OpenSslServerContext(File var1, File var2, String var3, Iterable<String> var4, Iterable<String> var5, long var6, long var8) throws javax.net.ssl.SSLException {
      OpenSsl.ensureAvailability();
      if (var1 == null) {
         throw new NullPointerException("certChainFile");
      } else if (!var1.isFile()) {
         throw new IllegalArgumentException("certChainFile is not a file: " + var1);
      } else if (var2 == null) {
         throw new NullPointerException("keyPath");
      } else if (!var2.isFile()) {
         throw new IllegalArgumentException("keyPath is not a file: " + var2);
      } else {
         if (var4 == null) {
            var4 = DEFAULT_CIPHERS;
         }

         if (var3 == null) {
            var3 = "";
         }

         if (var5 == null) {
            var5 = Collections.emptyList();
         }

         for (String var11 : var4) {
            if (var11 == null) {
               break;
            }

            this.ciphers.add(var11);
         }

         ArrayList var34 = new ArrayList();

         for (String var12 : var5) {
            if (var12 == null) {
               break;
            }

            var34.add(var12);
         }

         this.nextProtocols = Collections.unmodifiableList(var34);
         this.aprPool = Pool.create(0L);
         boolean var36 = false;

         try {
            synchronized (OpenSslServerContext.class) {
               try {
                  this.ctx = SSLContext.make(this.aprPool, 6, 1);
               } catch (Exception var25) {
                  throw new SSLException("failed to create an SSL_CTX", var25);
               }

               SSLContext.setOptions(this.ctx, 4095);
               SSLContext.setOptions(this.ctx, 16777216);
               SSLContext.setOptions(this.ctx, 4194304);
               SSLContext.setOptions(this.ctx, 524288);
               SSLContext.setOptions(this.ctx, 1048576);
               SSLContext.setOptions(this.ctx, 65536);

               try {
                  StringBuilder var13 = new StringBuilder();

                  for (String var15 : this.ciphers) {
                     var13.append(var15);
                     var13.append(':');
                  }

                  var13.setLength(var13.length() - 1);
                  SSLContext.setCipherSuite(this.ctx, var13.toString());
               } catch (SSLException var26) {
                  throw var26;
               } catch (Exception var27) {
                  throw new SSLException("failed to set cipher suite: " + this.ciphers, var27);
               }

               SSLContext.setVerify(this.ctx, 0, 10);

               try {
                  if (!SSLContext.setCertificate(this.ctx, var1.getPath(), var2.getPath(), var3, 0)) {
                     throw new SSLException("failed to set certificate: " + var1 + " and " + var2 + " (" + SSL.getLastError() + ')');
                  }
               } catch (SSLException var28) {
                  throw var28;
               } catch (Exception var29) {
                  throw new SSLException("failed to set certificate: " + var1 + " and " + var2, var29);
               }

               if (!SSLContext.setCertificateChainFile(this.ctx, var1.getPath(), true)) {
                  String var38 = SSL.getLastError();
                  if (!var38.startsWith("error:00000000:")) {
                     throw new SSLException("failed to set certificate chain: " + var1 + " (" + SSL.getLastError() + ')');
                  }
               }

               if (!var34.isEmpty()) {
                  StringBuilder var39 = new StringBuilder();

                  for (String var41 : (Iterable<String>)(Iterable<?>)(var34)) {
                     var39.append(var41);
                     var39.append(',');
                  }

                  var39.setLength(var39.length() - 1);
                  SSLContext.setNextProtos(this.ctx, var39.toString());
               }

               if (var6 > 0L) {
                  this.sessionCacheSize = var6;
                  SSLContext.setSessionCacheSize(this.ctx, var6);
               } else {
                  this.sessionCacheSize = var6 = SSLContext.setSessionCacheSize(this.ctx, 20480L);
                  SSLContext.setSessionCacheSize(this.ctx, var6);
               }

               if (var8 > 0L) {
                  this.sessionTimeout = var8;
                  SSLContext.setSessionCacheTimeout(this.ctx, var8);
               } else {
                  this.sessionTimeout = var8 = SSLContext.setSessionCacheTimeout(this.ctx, 300L);
                  SSLContext.setSessionCacheTimeout(this.ctx, var8);
               }
            }

            var36 = true;
         } finally {
            if (!var36) {
               this.destroyPools();
            }
         }

         this.stats = new OpenSslSessionStats(this.ctx);
      }
   }

   @Override
   public SSLEngine newEngine(ByteBufAllocator var1) {
      return this.nextProtocols.isEmpty()
         ? new OpenSslEngine(this.ctx, var1, null)
         : new OpenSslEngine(this.ctx, var1, this.nextProtocols.get(this.nextProtocols.size() - 1));
   }

   @Override
   public List<String> cipherSuites() {
      return this.unmodifiableCiphers;
   }

   public long context() {
      return this.ctx;
   }

   public OpenSslServerContext(File var1, File var2, String var3) throws javax.net.ssl.SSLException {
      this(var1, var2, var3, null, null, 0L, 0L);
   }

   @Override
   public List<String> nextProtocols() {
      return this.nextProtocols;
   }

   public void destroyPools() {
      if (this.aprPool != 0L) {
         Pool.destroy(this.aprPool);
      }
   }

   @Override
   public SSLEngine newEngine(ByteBufAllocator var1, String var2, int var3) {
      throw new UnsupportedOperationException();
   }

   public OpenSslServerContext(File var1, File var2) throws javax.net.ssl.SSLException {
      this(var1, var2, null);
   }
}
