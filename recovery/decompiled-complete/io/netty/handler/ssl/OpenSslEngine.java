package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.multipart.HttpPostBodyUtil$SeekAheadOptimize;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.cert.Certificate;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import javax.security.cert.X509Certificate;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import org.apache.tomcat.jni.Buffer;
import org.apache.tomcat.jni.SSL;

public class OpenSslEngine extends SSLEngine {
   public static int MAX_COMPRESSED_LENGTH;
   public ByteBufAllocator alloc;
   public long networkBIO;
   public boolean isInboundDone;
   public static int MAX_ENCRYPTED_PACKET_LENGTH;
   public int accepted;
   public static Certificate[] EMPTY_CERTIFICATES = new Certificate[0];
   public boolean isOutboundDone;
   public static SSLException RENEGOTIATION_UNSUPPORTED = new SSLException("renegotiation unsupported");
   public SSLSession session;
   public static AtomicIntegerFieldUpdater<OpenSslEngine> DESTROYED_UPDATER = AtomicIntegerFieldUpdater.newUpdater(OpenSslEngine.class, "destroyed");
   public static X509Certificate[] EMPTY_X509_CERTIFICATES = new X509Certificate[0];
   public LayerSnowmanHead __junk5914460912873558780;
   public volatile int destroyed;
   public long ssl;
   public static int MAX_ENCRYPTION_OVERHEAD_LENGTH;
   public String fallbackApplicationProtocol;
   public int lastPrimingReadResult;
   public boolean handshakeFinished;
   public static SSLException ENGINE_CLOSED = new SSLException("engine closed");
   public static SSLException ENCRYPTED_PACKET_OVERSIZED = new SSLException("encrypted packet oversized");
   public HttpPostBodyUtil$SeekAheadOptimize __junk3262733833600323975;
   public volatile String applicationProtocol;
   public static int MAX_PLAINTEXT_LENGTH;
   public EntityAIHurtByTarget __junk1993490433020752322;
   public String cipher;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(OpenSslEngine.class);
   public static int MAX_CIPHERTEXT_LENGTH;
   public boolean receivedShutdown;
   public boolean engineClosed;

   @Override
   public synchronized void closeInbound() {
      if (!this.isInboundDone) {
         this.isInboundDone = true;
         this.engineClosed = true;
         if (this.accepted != 0) {
            if (!this.receivedShutdown) {
               this.shutdown();
               throw new SSLException("Inbound closed before receiving peer's close_notify: possible truncation attack?");
            }
         } else {
            this.shutdown();
         }
      }
   }

   @Override
   public SSLSession getSession() {
      Object var1 = this.session;
      if (var1 == null) {
         this.session = (SSLSession)(var1 = new OpenSslEngine$1(this));
      }

      return (SSLSession)var1;
   }

   @Override
   public synchronized HandshakeStatus getHandshakeStatus() {
      if (this.accepted == 0 || this.destroyed != 0) {
         return HandshakeStatus.NOT_HANDSHAKING;
      } else if (!this.handshakeFinished) {
         if (SSL.pendingWrittenBytesInBIO(this.networkBIO) != 0) {
            return HandshakeStatus.NEED_WRAP;
         } else if (SSL.isInInit(this.ssl) == 0) {
            this.handshakeFinished = true;
            this.cipher = SSL.getCipherForSSL(this.ssl);
            String var1 = SSL.getNextProtoNegotiated(this.ssl);
            if (var1 == null) {
               var1 = this.fallbackApplicationProtocol;
            }

            if (var1 != null) {
               this.applicationProtocol = var1.replace(':', '_');
            } else {
               this.applicationProtocol = null;
            }

            return HandshakeStatus.FINISHED;
         } else {
            return HandshakeStatus.NEED_UNWRAP;
         }
      } else if (!this.engineClosed) {
         return HandshakeStatus.NOT_HANDSHAKING;
      } else {
         return SSL.pendingWrittenBytesInBIO(this.networkBIO) != 0 ? HandshakeStatus.NEED_WRAP : HandshakeStatus.NEED_UNWRAP;
      }
   }

   @Override
   public Runnable getDelegatedTask() {
      return null;
   }

   @Override
   public synchronized void beginHandshake() {
      if (this.engineClosed) {
         throw ENGINE_CLOSED;
      } else {
         switch (this.accepted) {
            case 0:
               SSL.doHandshake(this.ssl);
               this.accepted = 2;
               break;
            case 1:
               this.accepted = 2;
               break;
            case 2:
               throw RENEGOTIATION_UNSUPPORTED;
            default:
               throw new Error();
         }
      }
   }

   @Override
   public void setEnabledProtocols(String[] var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public synchronized boolean isOutboundDone() {
      return this.isOutboundDone;
   }

   @Override
   public synchronized void closeOutbound() {
      if (!this.isOutboundDone) {
         this.isOutboundDone = true;
         this.engineClosed = true;
         if (this.accepted != 0 && this.destroyed == 0) {
            int var1 = SSL.getShutdown(this.ssl);
            if ((var1 & 1) != 1) {
               SSL.shutdownSSL(this.ssl);
            }
         } else {
            this.shutdown();
         }
      }
   }

   @Override
   public String[] getEnabledCipherSuites() {
      return EmptyArrays.EMPTY_STRINGS;
   }

   @Override
   public String[] getSupportedCipherSuites() {
      return EmptyArrays.EMPTY_STRINGS;
   }

   @Override
   public boolean getEnableSessionCreation() {
      return false;
   }

   @Override
   public boolean getUseClientMode() {
      return false;
   }

   @Override
   public boolean getNeedClientAuth() {
      return false;
   }

   @Override
   public boolean getWantClientAuth() {
      return false;
   }

   public int readEncryptedData(ByteBuffer var1, int var2) {
      if (var1.isDirect() && var1.remaining() >= var2) {
         int var12 = var1.position();
         long var13 = Buffer.address(var1) + var12;
         int var14 = SSL.readFromBIO(this.networkBIO, var13, var2);
         if (var14 > 0) {
            ((java.nio.Buffer)var1).position(var12 + var14);
            return var14;
         } else {
            return 0;
         }
      } else {
         ByteBuf var3 = this.alloc.directBuffer(var2);

         int var8;
         try {
            long var4;
            if (var3.hasMemoryAddress()) {
               var4 = var3.memoryAddress();
            } else {
               var4 = Buffer.address(var3.nioBuffer());
            }

            int var6 = SSL.readFromBIO(this.networkBIO, var4, var2);
            if (var6 <= 0) {
               return 0;
            }

            int var7 = var1.limit();
            ((java.nio.Buffer)var1).limit(var1.position() + var6);
            var3.getBytes(0, var1);
            ((java.nio.Buffer)var1).limit(var7);
            var8 = var6;
         } finally {
            var3.release();
         }

         return var8;
      }
   }

   public int readPlaintextData(ByteBuffer var1) {
      if (var1.isDirect()) {
         int var13 = var1.position();
         long var14 = Buffer.address(var1) + var13;
         int var15 = var1.limit() - var13;
         int var16 = SSL.readFromSSL(this.ssl, var14, var15);
         if (var16 > 0) {
            ((java.nio.Buffer)var1).position(var13 + var16);
            return var16;
         } else {
            return 0;
         }
      } else {
         int var2 = var1.position();
         int var3 = var1.limit();
         int var4 = Math.min(18713, var3 - var2);
         ByteBuf var5 = this.alloc.directBuffer(var4);

         int var9;
         try {
            long var6;
            if (var5.hasMemoryAddress()) {
               var6 = var5.memoryAddress();
            } else {
               var6 = Buffer.address(var5.nioBuffer());
            }

            int var8 = SSL.readFromSSL(this.ssl, var6, var4);
            if (var8 <= 0) {
               return 0;
            }

            ((java.nio.Buffer)var1).limit(var2 + var8);
            var5.getBytes(0, var1);
            ((java.nio.Buffer)var1).limit(var3);
            var9 = var8;
         } finally {
            var5.release();
         }

         return var9;
      }
   }

   public synchronized void shutdown() {
      if (DESTROYED_UPDATER.compareAndSet(this, 0, 1)) {
         SSL.freeSSL(this.ssl);
         SSL.freeBIO(this.networkBIO);
         this.ssl = this.networkBIO = 5980671043092676672L & 67672067L;
         this.isInboundDone = this.isOutboundDone = this.engineClosed = true;
      }
   }

   @Override
   public synchronized SSLEngineResult wrap(ByteBuffer[] var1, int var2, int var3, ByteBuffer var4) {
      if (this.destroyed != 0) {
         return new SSLEngineResult(Status.CLOSED, HandshakeStatus.NOT_HANDSHAKING, 0, 0);
      } else if (var1 == null) {
         throw new NullPointerException("srcs");
      } else if (var4 == null) {
         throw new NullPointerException("dst");
      } else if (var2 < var1.length && var2 + var3 <= var1.length) {
         if (var4.isReadOnly()) {
            throw new ReadOnlyBufferException();
         } else {
            if (this.accepted == 0) {
               this.beginHandshakeImplicitly();
            }

            HandshakeStatus var5 = this.getHandshakeStatus();
            if ((!this.handshakeFinished || this.engineClosed) && var5 == HandshakeStatus.NEED_UNWRAP) {
               return new SSLEngineResult(this.getEngineStatus(), HandshakeStatus.NEED_UNWRAP, 0, 0);
            } else {
               int var6 = 0;
               int var7 = SSL.pendingWrittenBytesInBIO(this.networkBIO);
               if (var7 > 0) {
                  int var19 = var4.remaining();
                  if (var19 < var7) {
                     return new SSLEngineResult(Status.BUFFER_OVERFLOW, var5, 0, var6);
                  } else {
                     try {
                        var6 += this.readEncryptedData(var4, var7);
                     } catch (Exception var13) {
                        throw new SSLException(var13);
                     }

                     if (this.isOutboundDone) {
                        this.shutdown();
                     }

                     return new SSLEngineResult(this.getEngineStatus(), this.getHandshakeStatus(), 0, var6);
                  }
               } else {
                  int var8 = 0;

                  for (int var9 = var2; var9 < var3; var9++) {
                     ByteBuffer var10 = var1[var9];

                     while (var10.hasRemaining()) {
                        try {
                           var8 += this.writePlaintextData(var10);
                        } catch (Exception var15) {
                           throw new SSLException(var15);
                        }

                        var7 = SSL.pendingWrittenBytesInBIO(this.networkBIO);
                        if (var7 > 0) {
                           int var11 = var4.remaining();
                           if (var11 < var7) {
                              return new SSLEngineResult(Status.BUFFER_OVERFLOW, this.getHandshakeStatus(), var8, var6);
                           }

                           try {
                              var6 += this.readEncryptedData(var4, var7);
                           } catch (Exception var14) {
                              throw new SSLException(var14);
                           }

                           return new SSLEngineResult(this.getEngineStatus(), this.getHandshakeStatus(), var8, var6);
                        }
                     }
                  }

                  return new SSLEngineResult(this.getEngineStatus(), this.getHandshakeStatus(), var8, var6);
               }
            }
         }
      } else {
         throw new IndexOutOfBoundsException(
            "offset: " + var2 + ", length: " + var3 + " (expected: offset <= offset + length <= srcs.length (" + var1.length + "))"
         );
      }
   }

   @Override
   public String[] getEnabledProtocols() {
      return EmptyArrays.EMPTY_STRINGS;
   }

   @Override
   public String[] getSupportedProtocols() {
      return EmptyArrays.EMPTY_STRINGS;
   }

   @Override
   public void setEnabledCipherSuites(String[] var1) {
      throw new UnsupportedOperationException();
   }

   static {
      ENGINE_CLOSED.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      RENEGOTIATION_UNSUPPORTED.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      ENCRYPTED_PACKET_OVERSIZED.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
   }

   @Override
   public void setWantClientAuth(boolean var1) {
      if (var1) {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public synchronized SSLEngineResult unwrap(ByteBuffer var1, ByteBuffer[] var2, int var3, int var4) {
      if (this.destroyed != 0) {
         return new SSLEngineResult(Status.CLOSED, HandshakeStatus.NOT_HANDSHAKING, 0, 0);
      } else if (var1 == null) {
         throw new NullPointerException("src");
      } else if (var2 == null) {
         throw new NullPointerException("dsts");
      } else if (var3 < var2.length && var3 + var4 <= var2.length) {
         int var5 = 0;
         int var6 = var3 + var4;

         for (int var7 = var3; var7 < var6; var7++) {
            ByteBuffer var8 = var2[var7];
            if (var8 == null) {
               throw new IllegalArgumentException();
            }

            if (var8.isReadOnly()) {
               throw new ReadOnlyBufferException();
            }

            var5 += var8.remaining();
         }

         if (this.accepted == 0) {
            this.beginHandshakeImplicitly();
         }

         HandshakeStatus var18 = this.getHandshakeStatus();
         if ((!this.handshakeFinished || this.engineClosed) && var18 == HandshakeStatus.NEED_WRAP) {
            return new SSLEngineResult(this.getEngineStatus(), HandshakeStatus.NEED_WRAP, 0, 0);
         } else if (var1.remaining() > 18713) {
            this.isInboundDone = true;
            this.isOutboundDone = true;
            this.engineClosed = true;
            this.shutdown();
            throw ENCRYPTED_PACKET_OVERSIZED;
         } else {
            int var19 = 0;
            this.lastPrimingReadResult = 0;

            try {
               var19 += this.writeEncryptedData(var1);
            } catch (Exception var17) {
               throw new SSLException(var17);
            }

            String var9 = SSL.getLastError();
            if (var9 != null && !var9.startsWith("error:00000000:")) {
               if (logger.isInfoEnabled()) {
                  logger.info("SSL_read failed: primingReadResult: " + this.lastPrimingReadResult + "; OpenSSL error: '" + var9 + '\'');
               }

               this.shutdown();
               throw new SSLException(var9);
            } else {
               int var10 = SSL.isInInit(this.ssl) == 0 ? SSL.pendingReadableBytesInSSL(this.ssl) : 0;
               if (var5 < var10) {
                  return new SSLEngineResult(Status.BUFFER_OVERFLOW, this.getHandshakeStatus(), var19, 0);
               } else {
                  int var11 = 0;
                  int var12 = var3;

                  while (var12 < var6) {
                     ByteBuffer var13 = var2[var12];
                     if (!var13.hasRemaining()) {
                        var12++;
                     } else {
                        if (var10 <= 0) {
                           break;
                        }

                        int var14;
                        try {
                           var14 = this.readPlaintextData(var13);
                        } catch (Exception var16) {
                           throw new SSLException(var16);
                        }

                        if (var14 == 0) {
                           break;
                        }

                        var11 += var14;
                        var10 -= var14;
                        if (!var13.hasRemaining()) {
                           var12++;
                        }
                     }
                  }

                  if (!this.receivedShutdown && (SSL.getShutdown(this.ssl) & 2) == 2) {
                     this.receivedShutdown = true;
                     this.closeOutbound();
                     this.closeInbound();
                  }

                  return new SSLEngineResult(this.getEngineStatus(), this.getHandshakeStatus(), var19, var11);
               }
            }
         }
      } else {
         throw new IndexOutOfBoundsException(
            "offset: " + var3 + ", length: " + var4 + " (expected: offset <= offset + length <= dsts.length (" + var2.length + "))"
         );
      }
   }

   public int writeEncryptedData(ByteBuffer var1) {
      int var2 = var1.position();
      int var3 = var1.remaining();
      if (var1.isDirect()) {
         long var12 = Buffer.address(var1) + var2;
         int var6 = SSL.writeToBIO(this.networkBIO, var12, var3);
         if (var6 >= 0) {
            ((java.nio.Buffer)var1).position(var2 + var6);
            this.lastPrimingReadResult = SSL.readFromSSL(this.ssl, var12, 0);
            return var6;
         } else {
            return 0;
         }
      } else {
         ByteBuf var4 = this.alloc.directBuffer(var3);

         int var8;
         try {
            long var5;
            if (var4.hasMemoryAddress()) {
               var5 = var4.memoryAddress();
            } else {
               var5 = Buffer.address(var4.nioBuffer());
            }

            var4.setBytes(0, var1);
            int var7 = SSL.writeToBIO(this.networkBIO, var5, var3);
            if (var7 < 0) {
               ((java.nio.Buffer)var1).position(var2);
               return 0;
            }

            ((java.nio.Buffer)var1).position(var2 + var7);
            this.lastPrimingReadResult = SSL.readFromSSL(this.ssl, var5, 0);
            var8 = var7;
         } finally {
            var4.release();
         }

         return var8;
      }
   }

   @Override
   public void setNeedClientAuth(boolean var1) {
      if (var1) {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public synchronized boolean isInboundDone() {
      return this.isInboundDone || this.engineClosed;
   }

   public OpenSslEngine(long var1, ByteBufAllocator var3, String var4) {
      OpenSsl.ensureAvailability();
      if (var1 == (4159908566243100976L & -4159908566278823424L)) {
         throw new NullPointerException("sslContext");
      } else if (var3 == null) {
         throw new NullPointerException("alloc");
      } else {
         this.alloc = var3;
         this.ssl = SSL.newSSL(var1, true);
         this.networkBIO = SSL.makeNetworkBIO(this.ssl);
         this.fallbackApplicationProtocol = var4;
      }
   }

   @Override
   public void setUseClientMode(boolean var1) {
      if (var1) {
         throw new UnsupportedOperationException();
      }
   }

   public Status getEngineStatus() {
      return this.engineClosed ? Status.CLOSED : Status.OK;
   }

   @Override
   public void setEnableSessionCreation(boolean var1) {
      if (var1) {
         throw new UnsupportedOperationException();
      }
   }

   public int writePlaintextData(ByteBuffer var1) {
      int var2 = var1.position();
      int var3 = var1.limit();
      int var4 = Math.min(var3 - var2, 16384);
      if (var1.isDirect()) {
         long var13 = Buffer.address(var1) + var2;
         int var14 = SSL.writeToSSL(this.ssl, var13, var4);
         if (var14 > 0) {
            ((java.nio.Buffer)var1).position(var2 + var14);
            return var14;
         } else {
            throw new IllegalStateException("SSL.writeToSSL() returned a non-positive value: " + var14);
         }
      } else {
         ByteBuf var6 = this.alloc.directBuffer(var4);

         int var9;
         try {
            long var7;
            if (var6.hasMemoryAddress()) {
               var7 = var6.memoryAddress();
            } else {
               var7 = Buffer.address(var6.nioBuffer());
            }

            ((java.nio.Buffer)var1).limit(var2 + var4);
            var6.setBytes(0, var1);
            ((java.nio.Buffer)var1).limit(var3);
            int var5 = SSL.writeToSSL(this.ssl, var7, var4);
            if (var5 <= 0) {
               ((java.nio.Buffer)var1).position(var2);
               throw new IllegalStateException("SSL.writeToSSL() returned a non-positive value: " + var5);
            }

            ((java.nio.Buffer)var1).position(var2 + var5);
            var9 = var5;
         } finally {
            var6.release();
         }

         return var9;
      }
   }

   public synchronized void beginHandshakeImplicitly() {
      if (this.engineClosed) {
         throw ENGINE_CLOSED;
      } else {
         if (this.accepted == 0) {
            SSL.doHandshake(this.ssl);
            this.accepted = 1;
         }
      }
   }
}
