package org.java_websocket;

import io.netty.channel.socket.nio.ProtocolFamilyConverter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.world.chunk.storage.ChunkLoader;
import net.optifine.entity.model.ModelAdapterSheep;
import org.apache.log4j.pattern.LiteralPatternConverter;
import org.java_websocket.interfaces.ISSLChannel;
import org.java_websocket.util.ByteBufferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLSocketChannel implements ISSLChannel, ByteChannel, WrappedByteChannel {
   public ByteBuffer peerNetData;
   public EntityLargeFireball field_0010;
   public ByteBuffer myNetData;
   public ByteBuffer peerAppData;
   public ExecutorService executor;
   public SocketChannel socketChannel;
   public ByteBuffer myAppData;
   public SSLEngine engine;
   public Logger log = LoggerFactory.getLogger(SSLSocketChannel.class);
   public ChunkLoader field_0012;
   public ModelAdapterSheep field_0000;
   public LiteralPatternConverter field_0006;
   public ProtocolFamilyConverter field_0007;

   @Override
   public synchronized int write(ByteBuffer var1) {
      int var2 = 0;

      while (var1.hasRemaining()) {
         ((Buffer)this.myNetData).clear();
         SSLEngineResult var3 = this.engine.wrap(var1, this.myNetData);
         switch (SSLSocketChannel$1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[var3.getStatus().ordinal()]) {
            case 1:
               ((Buffer)this.myNetData).flip();

               while (this.myNetData.hasRemaining()) {
                  var2 += this.socketChannel.write(this.myNetData);
               }
               break;
            case 2:
               throw new SSLException("Buffer underflow occured after a wrap. I don't think we should ever get here.");
            case 3:
               this.myNetData = this.enlargePacketBuffer(this.myNetData);
               break;
            case 4:
               this.closeConnection();
               return 0;
            default:
               throw new IllegalStateException("Invalid SSL status: " + var3.getStatus());
         }
      }

      return var2;
   }

   @Override
   public void writeMore() {
   }

   @Override
   public boolean isNeedRead() {
      return this.peerNetData.hasRemaining() || this.peerAppData.hasRemaining();
   }

   @Override
   public boolean isNeedWrite() {
      return false;
   }

   public void handleEndOfStream() {
      try {
         this.engine.closeInbound();
      } catch (Exception var2) {
         this.log
            .error(
               "This engine was forced to close inbound, without having received the proper SSL/TLS close notification message from the peer, due to end of stream."
            );
      }

      this.closeConnection();
   }

   public ByteBuffer enlargePacketBuffer(ByteBuffer var1) {
      return this.enlargeBuffer(var1, this.engine.getSession().getPacketBufferSize());
   }

   @Override
   public synchronized int read(ByteBuffer var1) {
      if (!var1.hasRemaining()) {
         return 0;
      } else if (this.peerAppData.hasRemaining()) {
         ((Buffer)this.peerAppData).flip();
         return ByteBufferUtils.transferByteBuffer(this.peerAppData, var1);
      } else {
         this.peerNetData.compact();
         int var2 = this.socketChannel.read(this.peerNetData);
         if (var2 > 0 || this.peerNetData.hasRemaining()) {
            ((Buffer)this.peerNetData).flip();
            if (this.peerNetData.hasRemaining()) {
               this.peerAppData.compact();

               SSLEngineResult var3;
               try {
                  var3 = this.engine.unwrap(this.peerNetData, this.peerAppData);
               } catch (SSLException var5) {
                  this.log.error("SSLExcpetion during unwrap", (Throwable)var5);
                  throw var5;
               }

               switch (SSLSocketChannel$1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[var3.getStatus().ordinal()]) {
                  case 1:
                     ((Buffer)this.peerAppData).flip();
                     return ByteBufferUtils.transferByteBuffer(this.peerAppData, var1);
                  case 2:
                     ((Buffer)this.peerAppData).flip();
                     return ByteBufferUtils.transferByteBuffer(this.peerAppData, var1);
                  case 3:
                     this.peerAppData = this.enlargeApplicationBuffer(this.peerAppData);
                     return this.read(var1);
                  case 4:
                     this.closeConnection();
                     ((Buffer)var1).clear();
                     return -1;
                  default:
                     throw new IllegalStateException("Invalid SSL status: " + var3.getStatus());
               }
            }
         } else if (var2 < 0) {
            this.handleEndOfStream();
         }

         ByteBufferUtils.transferByteBuffer(this.peerAppData, var1);
         return var2;
      }
   }

   public boolean doHandshake() {
      int var3 = this.engine.getSession().getApplicationBufferSize();
      this.myAppData = ByteBuffer.allocate(var3);
      this.peerAppData = ByteBuffer.allocate(var3);
      ((Buffer)this.myNetData).clear();
      ((Buffer)this.peerNetData).clear();
      HandshakeStatus var2 = this.engine.getHandshakeStatus();
      boolean var4 = false;

      label102:
      while (!var4) {
         switch (SSLSocketChannel$1.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[var2.ordinal()]) {
            case 1:
               var4 = !this.peerNetData.hasRemaining();
               if (var4) {
                  return true;
               }

               this.socketChannel.write(this.peerNetData);
               break;
            case 2:
               if (this.socketChannel.read(this.peerNetData) < 0) {
                  if (this.engine.isInboundDone() && this.engine.isOutboundDone()) {
                     return false;
                  }

                  try {
                     this.engine.closeInbound();
                  } catch (SSLException var6) {
                  }

                  this.engine.closeOutbound();
                  var2 = this.engine.getHandshakeStatus();
                  break;
               } else {
                  ((Buffer)this.peerNetData).flip();

                  SSLEngineResult var10;
                  try {
                     var10 = this.engine.unwrap(this.peerNetData, this.peerAppData);
                     this.peerNetData.compact();
                     var2 = var10.getHandshakeStatus();
                  } catch (SSLException var8) {
                     this.engine.closeOutbound();
                     var2 = this.engine.getHandshakeStatus();
                     break;
                  }

                  switch (SSLSocketChannel$1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[var10.getStatus().ordinal()]) {
                     case 1:
                        continue;
                     case 2:
                        this.peerNetData = this.handleBufferUnderflow(this.peerNetData);
                        continue;
                     case 3:
                        this.peerAppData = this.enlargeApplicationBuffer(this.peerAppData);
                        continue;
                     case 4:
                        if (this.engine.isOutboundDone()) {
                           return false;
                        }

                        this.engine.closeOutbound();
                        var2 = this.engine.getHandshakeStatus();
                        continue;
                     default:
                        throw new IllegalStateException("Invalid SSL status: " + var10.getStatus());
                  }
               }
            case 3:
               ((Buffer)this.myNetData).clear();

               SSLEngineResult var1;
               try {
                  var1 = this.engine.wrap(this.myAppData, this.myNetData);
                  var2 = var1.getHandshakeStatus();
               } catch (SSLException var7) {
                  this.engine.closeOutbound();
                  var2 = this.engine.getHandshakeStatus();
                  break;
               }

               switch (SSLSocketChannel$1.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[var1.getStatus().ordinal()]) {
                  case 1:
                     ((Buffer)this.myNetData).flip();

                     while (true) {
                        if (!this.myNetData.hasRemaining()) {
                           continue label102;
                        }

                        this.socketChannel.write(this.myNetData);
                     }
                  case 2:
                     throw new SSLException("Buffer underflow occured after a wrap. I don't think we should ever get here.");
                  case 3:
                     this.myNetData = this.enlargePacketBuffer(this.myNetData);
                     continue;
                  case 4:
                     try {
                        ((Buffer)this.myNetData).flip();

                        while (this.myNetData.hasRemaining()) {
                           this.socketChannel.write(this.myNetData);
                        }

                        ((Buffer)this.peerNetData).clear();
                     } catch (Exception var9) {
                        var2 = this.engine.getHandshakeStatus();
                     }
                     continue;
                  default:
                     throw new IllegalStateException("Invalid SSL status: " + var1.getStatus());
               }
            case 4:
               Runnable var5;
               while ((var5 = this.engine.getDelegatedTask()) != null) {
                  this.executor.execute(var5);
               }

               var2 = this.engine.getHandshakeStatus();
            case 5:
               break;
            default:
               throw new IllegalStateException("Invalid SSL status: " + var2);
         }
      }

      return true;
   }

   @Override
   public void close() {
      this.closeConnection();
   }

   public ByteBuffer handleBufferUnderflow(ByteBuffer var1) {
      if (this.engine.getSession().getPacketBufferSize() < var1.limit()) {
         return var1;
      } else {
         ByteBuffer var2 = this.enlargePacketBuffer(var1);
         ((Buffer)var1).flip();
         var2.put(var1);
         return var2;
      }
   }

   public SSLSocketChannel(SocketChannel var1, SSLEngine var2, ExecutorService var3, SelectionKey var4) {
      if (var1 != null && var2 != null && this.executor != var3) {
         this.socketChannel = var1;
         this.engine = var2;
         this.executor = var3;
         this.myNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
         this.peerNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
         this.engine.beginHandshake();
         if (this.doHandshake()) {
            if (var4 != null) {
               var4.interestOps(var4.interestOps() | 4);
            }
         } else {
            try {
               this.socketChannel.close();
            } catch (IOException var6) {
               this.log.error("Exception during the closing of the channel", (Throwable)var6);
            }
         }
      } else {
         throw new IllegalArgumentException("parameter must not be null");
      }
   }

   public ByteBuffer enlargeApplicationBuffer(ByteBuffer var1) {
      return this.enlargeBuffer(var1, this.engine.getSession().getApplicationBufferSize());
   }

   @Override
   public boolean isOpen() {
      return this.socketChannel.isOpen();
   }

   public ByteBuffer enlargeBuffer(ByteBuffer var1, int var2) {
      if (var2 > var1.capacity()) {
         var1 = ByteBuffer.allocate(var2);
      } else {
         var1 = ByteBuffer.allocate(var1.capacity() * 2);
      }

      return var1;
   }

   @Override
   public SSLEngine getSSLEngine() {
      return this.engine;
   }

   public void closeConnection() {
      this.engine.closeOutbound();

      try {
         this.doHandshake();
      } catch (IOException var2) {
      }

      this.socketChannel.close();
   }

   @Override
   public int readMore(ByteBuffer var1) {
      return this.read(var1);
   }

   @Override
   public boolean isBlocking() {
      return this.socketChannel.isBlocking();
   }
}
