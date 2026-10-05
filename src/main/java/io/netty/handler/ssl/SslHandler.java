package io.netty.handler.ssl;

import com.cheatbreaker.client.module.type.cooldowns.CooldownRenderer;
import com.cheatbreaker.client.network.CheatBreakerPingHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.PendingWriteQueue;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.sctp.SctpMessageToMessageDecoder;
import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ImmediateExecutor;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import javazoom.jl.decoder.LayerIDecoder;
import junit.swingui.TestRunner$12;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.main.GameConfiguration$FolderInformation;
import net.minecraft.client.renderer.block.model.BlockPart;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.tileentity.TileEntityEndPortalRenderer;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.optifine.gui.GuiPerformanceSettingsOF;
import net.optifine.shaders.config.ShaderOptionSwitch;
import net.optifine.shaders.config.ShaderProfile;
import net.optifine.shaders.uniform.ShaderExpressionResolver;
import org.apache.log4j.NDC;
import org.java_websocket.SocketChannelIOHelper;
import net.minecraft.world.gen.layer.GenLayerRiver;

public class SslHandler extends ByteToMessageDecoder implements ChannelOutboundHandler {
   public Executor delegatedTaskExecutor;
   public int maxPacketBufferSize;
   public SslHandler.LazyChannelPromise handshakePromise = new SslHandler.LazyChannelPromise();
   public boolean wantsDirectBuffer;
   public volatile ChannelHandlerContext ctx;
   public static final boolean $assertionsDisabled = !SslHandler.class.desiredAssertionStatus();
   public PendingWriteQueue pendingUnencryptedWrites;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(SslHandler.class);
   public boolean flushedBeforeHandshakeDone;
   public static Pattern IGNORABLE_CLASS_IN_STACK = Pattern.compile("^.*(?:Socket|Datagram|Sctp|Udt)Channel.*$");
   public boolean wantsInboundHeapBuffer;
   public static Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile("^.*(?:connection.*(?:reset|closed|abort|broken)|broken.*pipe).*$", 2);
   public volatile long handshakeTimeoutMillis;
   public SslHandler.LazyChannelPromise sslCloseFuture = new SslHandler.LazyChannelPromise();
   public boolean wantsLargeOutboundNetworkBuffer;
   public volatile long closeNotifyTimeoutMillis;
   public static SSLException SSLENGINE_CLOSED = new SSLException("SSLEngine closed already");
   public boolean startTls;
   public SSLEngine engine;
   public static SSLException HANDSHAKE_TIMED_OUT = new SSLException("handshake timed out");
   public boolean needsFlush;
   public boolean sentFirstMessage;
   public int packetLength;
   public static ClosedChannelException CHANNEL_CLOSED = new ClosedChannelException();

   public void finishWrap(ChannelHandlerContext var1, ByteBuf var2, ChannelPromise var3, boolean var4) {
      if (var2 == null) {
         var2 = Unpooled.EMPTY_BUFFER;
      } else if (!var2.isReadable()) {
         var2.release();
         var2 = Unpooled.EMPTY_BUFFER;
      }

      if (var3 != null) {
         var1.write(var2, var3);
      } else {
         var1.write(var2);
      }

      if (var4) {
         this.needsFlush = true;
      }
   }

   public ChannelFuture close(final ChannelPromise var1) {
      final ChannelHandlerContext var2 = this.ctx;
      var2.executor().execute(new Runnable() {

         @Override
         public void run() {
            SslHandler.this.engine.closeOutbound();

            try {
               SslHandler.this.write(var2, Unpooled.EMPTY_BUFFER, var1);
               SslHandler.this.flush(var2);
            } catch (Exception var2x) {
               if (!var1.tryFailure(var2x)) {
                  SslHandler.logger.warn("flush() raised a masked exception.", (Throwable)var2x);
               }
            }
         }
      });
      return var1;
   }

   public void wrap(ChannelHandlerContext var1, boolean var2) throws javax.net.ssl.SSLException {
      ByteBuf var3 = null;
      ChannelPromise var4 = null;

      try {
         while (true) {
            ByteBuf var6;
            while (true) {
               Object var5 = this.pendingUnencryptedWrites.current();
               if (var5 == null) {
                  return;
               }

               if (var5 instanceof ByteBuf) {
                  var6 = (ByteBuf)var5;
                  if (var3 == null) {
                     var3 = this.allocateOutNetBuf(var1, var6.readableBytes());
                  }
                  break;
               }

               this.pendingUnencryptedWrites.removeAndWrite();
            }

            SSLEngineResult var7 = this.wrap(this.engine, var6, var3);
            if (!var6.isReadable()) {
               var4 = this.pendingUnencryptedWrites.remove();
            } else {
               var4 = null;
            }

            if (var7.getStatus() == Status.CLOSED) {
               this.pendingUnencryptedWrites.removeAndFailAll(SSLENGINE_CLOSED);
               return;
            }

            switch (var7.getHandshakeStatus()) {
               case NEED_TASK:
                  this.runDelegatedTasks();
                  break;
               case FINISHED:
                  this.setHandshakeSuccess();
               case NOT_HANDSHAKING:
                  this.setHandshakeSuccessIfStillHandshaking();
               case NEED_WRAP:
                  this.finishWrap(var1, var3, var4, var2);
                  var4 = null;
                  var3 = null;
                  break;
               case NEED_UNWRAP:
                  return;
               default:
                  throw new IllegalStateException("Unknown handshake status: " + var7.getHandshakeStatus());
            }
         }
      } catch (SSLException var11) {
         this.setHandshakeFailure(var11);
         throw var11;
      } finally {
         this.finishWrap(var1, var3, var4, var2);
      }
   }

   public boolean ignoreException(Throwable var1) {
      if (!(var1 instanceof SSLException) && var1 instanceof IOException && this.sslCloseFuture.isDone()) {
         String var2 = String.valueOf(var1.getMessage()).toLowerCase();
         if (IGNORABLE_ERROR_MESSAGE.matcher(var2).matches()) {
            return true;
         }

         StackTraceElement[] var3 = var1.getStackTrace();

         for (StackTraceElement var7 : var3) {
            String var8 = var7.getClassName();
            String var9 = var7.getMethodName();
            if (!var8.startsWith("io.netty.") && "read".equals(var9)) {
               if (IGNORABLE_CLASS_IN_STACK.matcher(var8).matches()) {
                  return true;
               }

               try {
                  Class var10 = PlatformDependent.getClassLoader(this.getClass()).loadClass(var8);
                  if (SocketChannel.class.isAssignableFrom(var10) || DatagramChannel.class.isAssignableFrom(var10)) {
                     return true;
                  }

                  if (PlatformDependent.javaVersion() >= 7 && "com.sun.nio.sctp.SctpChannel".equals(var10.getSuperclass().getName())) {
                     return true;
                  }
               } catch (ClassNotFoundException var11) {
               }
            }
         }
      }

      return false;
   }

   @Override
   public void deregister(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
      var1.deregister(var2);
   }

   public void closeOutboundAndChannel(ChannelHandlerContext var1, ChannelPromise var2, boolean var3) throws java.lang.Exception {
      if (!var1.channel().isActive()) {
         if (var3) {
            var1.disconnect(var2);
         } else {
            var1.close(var2);
         }
      } else {
         this.engine.closeOutbound();
         ChannelPromise var4 = var1.newPromise();
         this.write(var1, Unpooled.EMPTY_BUFFER, var4);
         this.flush(var1);
         this.safeClose(var1, var4, var2);
      }
   }

   public ByteBuf allocateOutNetBuf(ChannelHandlerContext var1, int var2) {
      return this.wantsLargeOutboundNetworkBuffer
         ? this.allocate(var1, this.maxPacketBufferSize)
         : this.allocate(var1, Math.min(var2 + 2329, this.maxPacketBufferSize));
   }

   @Override
   public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
      this.closeOutboundAndChannel(var1, var2, true);
   }

   public void setHandshakeTimeout(long var1, TimeUnit var3) {
      if (var3 == null) {
         throw new NullPointerException("unit");
      } else {
         this.setHandshakeTimeoutMillis(var3.toMillis(var1));
      }
   }

   public SslHandler(SSLEngine var1) {
      this(var1, false);
   }

   public void setCloseNotifyTimeout(long var1, TimeUnit var3) {
      if (var3 == null) {
         throw new NullPointerException("unit");
      } else {
         this.setCloseNotifyTimeoutMillis(var3.toMillis(var1));
      }
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      this.setHandshakeFailure(CHANNEL_CLOSED);
      super.channelInactive(var1);
   }

   public SslHandler(SSLEngine var1, Executor var2) {
      this(var1, false, var2);
   }

   public void wrapNonAppData(ChannelHandlerContext var1, boolean var2) throws javax.net.ssl.SSLException {
      ByteBuf var3 = null;

      try {
         SSLEngineResult var4;
         try {
            do {
               if (var3 == null) {
                  var3 = this.allocateOutNetBuf(var1, 0);
               }

               var4 = this.wrap(this.engine, Unpooled.EMPTY_BUFFER, var3);
               if (var4.bytesProduced() > 0) {
                  var1.write(var3);
                  if (var2) {
                     this.needsFlush = true;
                  }

                  var3 = null;
               }

               switch (var4.getHandshakeStatus()) {
                  case NEED_TASK:
                     this.runDelegatedTasks();
                     break;
                  case FINISHED:
                     this.setHandshakeSuccess();
                     break;
                  case NOT_HANDSHAKING:
                     this.setHandshakeSuccessIfStillHandshaking();
                     if (!var2) {
                        this.unwrapNonAppData(var1);
                     }
                  case NEED_WRAP:
                     break;
                  case NEED_UNWRAP:
                     if (!var2) {
                        this.unwrapNonAppData(var1);
                     }
                     break;
                  default:
                     throw new IllegalStateException("Unknown handshake status: " + var4.getHandshakeStatus());
               }
            } while (var4.bytesProduced() != 0);
         } catch (SSLException var8) {
            this.setHandshakeFailure(var8);
            throw var8;
         }
      } finally {
         if (var3 != null) {
            var3.release();
         }
      }
   }

   public void setHandshakeFailure(Throwable var1) {
      this.engine.closeOutbound();

      try {
         this.engine.closeInbound();
      } catch (SSLException var4) {
         String var3 = var4.getMessage();
         if (var3 == null || !var3.contains("possible truncation attack")) {
            logger.debug("SSLEngine.closeInbound() raised an exception.", (Throwable)var4);
         }
      }

      this.notifyHandshakeFailure(var1);
      this.pendingUnencryptedWrites.removeAndFailAll(var1);
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      this.pendingUnencryptedWrites.add(var2, var3);
   }

   public long getCloseNotifyTimeoutMillis() {
      return this.closeNotifyTimeoutMillis;
   }

   public void runDelegatedTasks() {
      if (this.delegatedTaskExecutor == ImmediateExecutor.INSTANCE) {
         while (true) {
            Runnable var6 = this.engine.getDelegatedTask();
            if (var6 == null) {
               break;
            }

            var6.run();
         }
      } else {
         final ArrayList var1 = new ArrayList(2);

         while (true) {
            Runnable var2 = this.engine.getDelegatedTask();
            if (var2 == null) {
               if (var1.isEmpty()) {
                  return;
               }

               final CountDownLatch var7 = new CountDownLatch(1);
               this.delegatedTaskExecutor.execute(new Runnable() {

                  @Override
                  public void run() {
                     try {
                        for (Runnable var2x : (Iterable<Runnable>)(Iterable<?>)(var1)) {
                           var2x.run();
                        }
                     } catch (Exception var6) {
                        SslHandler.this.ctx.fireExceptionCaught(var6);
                     } finally {
                        var7.countDown();
                     }
                  }
               });
               boolean var3 = false;

               while (var7.getCount() != 0L) {
                  try {
                     var7.await();
                  } catch (InterruptedException var5) {
                     var3 = true;
                  }
               }

               if (var3) {
                  Thread.currentThread().interrupt();
               }
               break;
            }

            var1.add(var2);
         }
      }
   }

   @Override
   public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) throws java.lang.Exception {
      var1.connect(var2, var3, var4);
   }

   public SSLEngine engine() {
      return this.engine;
   }

   @Override
   public void handlerRemoved0(ChannelHandlerContext var1) throws java.lang.Exception {
      if (!this.pendingUnencryptedWrites.isEmpty()) {
         this.pendingUnencryptedWrites.removeAndFailAll(new ChannelException("Pending write on removal of SslHandler"));
      }
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      this.ctx = var1;
      this.pendingUnencryptedWrites = new PendingWriteQueue(var1);
      if (var1.channel().isActive() && this.engine.getUseClientMode()) {
         this.handshake();
      }
   }

   public Future<Channel> sslCloseFuture() {
      return this.sslCloseFuture;
   }

   @Override
   public void channelActive(final ChannelHandlerContext var1) throws java.lang.Exception {
      if (!this.startTls && this.engine.getUseClientMode()) {
         this.handshake().addListener(new GenericFutureListener<Future<Channel>>() {
            @Override
            public void operationComplete(Future<Channel> var1x) throws java.lang.Exception {
               if (!var1x.isSuccess()) {
                  SslHandler.logger.debug("Failed to complete handshake", var1x.cause());
                  var1.close();
               }
            }
         });
      }

      var1.fireChannelActive();
   }

   @Override
   public void flush(ChannelHandlerContext var1) throws java.lang.Exception {
      if (this.startTls && !this.sentFirstMessage) {
         this.sentFirstMessage = true;
         this.pendingUnencryptedWrites.removeAndWriteAll();
         var1.flush();
      } else {
         if (this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.add(Unpooled.EMPTY_BUFFER, var1.voidPromise());
         }

         if (!this.handshakePromise.isDone()) {
            this.flushedBeforeHandshakeDone = true;
         }

         this.wrap(var1, false);
         var1.flush();
      }
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
      if (this.ignoreException(var2)) {
         if (logger.isDebugEnabled()) {
            logger.debug(
               "Swallowing a harmless 'connection reset by peer / broken pipe' error that occurred while writing close_notify in response to the peer's close_notify",
               var2
            );
         }

         if (var1.channel().isActive()) {
            var1.close();
         }
      } else {
         var1.fireExceptionCaught(var2);
      }
   }

   @Override
   public void read(ChannelHandlerContext var1) {
      var1.read();
   }

   public ChannelFuture close() {
      return this.close(this.ctx.newPromise());
   }

   public void safeClose(final ChannelHandlerContext var1, ChannelFuture var2, final ChannelPromise var3) {
      if (!var1.channel().isActive()) {
         var1.close(var3);
      } else {
         final ScheduledFuture var4;
         if (this.closeNotifyTimeoutMillis > 0L) {
            var4 = var1.executor().schedule(new Runnable() {

               @Override
               public void run() {
                  SslHandler.logger.warn(var1.channel() + " last write attempt timed out." + " Force-closing the connection.");
                  var1.close(var3);
               }
            }, this.closeNotifyTimeoutMillis, TimeUnit.MILLISECONDS);
         } else {
            var4 = null;
         }

         var2.addListener(new ChannelFutureListener() {

            public void operationComplete(ChannelFuture var1x) throws java.lang.Exception {
               if (var4 != null) {
                  var4.cancel(false);
               }

               var1.close(var3);
            }
         });
      }
   }

   public Future<Channel> handshake() {
      final ScheduledFuture var1;
      if (this.handshakeTimeoutMillis > 0L) {
         var1 = this.ctx.executor().schedule(new Runnable() {

            @Override
            public void run() {
               if (!SslHandler.this.handshakePromise.isDone()) {
                  SslHandler.this.notifyHandshakeFailure(SslHandler.HANDSHAKE_TIMED_OUT);
               }
            }
         }, this.handshakeTimeoutMillis, TimeUnit.MILLISECONDS);
      } else {
         var1 = null;
      }

      this.handshakePromise.addListener(new GenericFutureListener<Future<Channel>>() {

         @Override
         public void operationComplete(Future<Channel> var1x) throws java.lang.Exception {
            if (var1 != null) {
               var1.cancel(false);
            }
         }
      });

      try {
         this.engine.beginHandshake();
         this.wrapNonAppData(this.ctx, false);
         this.ctx.flush();
      } catch (Exception var3) {
         this.notifyHandshakeFailure(var3);
      }

      return this.handshakePromise;
   }

   @Override
   public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) throws java.lang.Exception {
      var1.bind(var2, var3);
   }

   public Future<Channel> handshakeFuture() {
      return this.handshakePromise;
   }

   public void setHandshakeSuccess() {
      String var1 = String.valueOf(this.engine.getSession().getCipherSuite());
      if (!this.wantsDirectBuffer && (var1.contains("_GCM_") || var1.contains("-GCM-"))) {
         this.wantsInboundHeapBuffer = true;
      }

      if (this.handshakePromise.trySuccess(this.ctx.channel())) {
         if (logger.isDebugEnabled()) {
            logger.debug(this.ctx.channel() + " HANDSHAKEN: " + this.engine.getSession().getCipherSuite());
         }

         this.ctx.fireUserEventTriggered(SslHandshakeCompletionEvent.SUCCESS);
      }
   }

   public void setHandshakeTimeoutMillis(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("handshakeTimeoutMillis: " + var1 + " (expected: >= 0)");
      } else {
         this.handshakeTimeoutMillis = var1;
      }
   }

   public boolean setHandshakeSuccessIfStillHandshaking() {
      if (!this.handshakePromise.isDone()) {
         this.setHandshakeSuccess();
         return true;
      } else {
         return false;
      }
   }

   public SSLEngineResult wrap(SSLEngine var1, ByteBuf var2, ByteBuf var3) throws javax.net.ssl.SSLException {
      ByteBuffer var4 = var2.nioBuffer();
      if (!var4.isDirect()) {
         ByteBuffer var5 = ByteBuffer.allocateDirect(var4.remaining());
         ((Buffer)var5.put(var4)).flip();
         var4 = var5;
      }

      while (true) {
         ByteBuffer var7 = var3.nioBuffer(var3.writerIndex(), var3.writableBytes());
         SSLEngineResult var6 = var1.wrap(var4, var7);
         var2.skipBytes(var6.bytesConsumed());
         var3.writerIndex(var3.writerIndex() + var6.bytesProduced());
         switch (var6.getStatus()) {
            case BUFFER_OVERFLOW:
               var3.ensureWritable(this.maxPacketBufferSize);
               break;
            default:
               return var6;
         }
      }
   }

   static {
      SSLENGINE_CLOSED.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      HANDSHAKE_TIMED_OUT.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      CHANNEL_CLOSED.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
   }

   public SslHandler(SSLEngine var1, boolean var2, Executor var3) {
      this.handshakeTimeoutMillis = 10000L;
      this.closeNotifyTimeoutMillis = 3000L;
      if (var1 == null) {
         throw new NullPointerException("engine");
      } else if (var3 == null) {
         throw new NullPointerException("delegatedTaskExecutor");
      } else {
         this.engine = var1;
         this.delegatedTaskExecutor = var3;
         this.startTls = var2;
         this.maxPacketBufferSize = var1.getSession().getPacketBufferSize();
         this.wantsDirectBuffer = var1 instanceof OpenSslEngine;
         this.wantsLargeOutboundNetworkBuffer = !(var1 instanceof OpenSslEngine);
      }
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws javax.net.ssl.SSLException {
      int var4 = var2.readerIndex();
      int var5 = var2.writerIndex();
      int var6 = var4;
      int var7 = 0;
      if (this.packetLength > 0) {
         if (var5 - var4 < this.packetLength) {
            return;
         }

         var6 = var4 + this.packetLength;
         var7 = this.packetLength;
         this.packetLength = 0;
      }

      boolean var8 = false;

      while (var7 < 18713) {
         int var9 = var5 - var6;
         if (var9 < 5) {
            break;
         }

         int var10 = getEncryptedPacketLength(var2, var6);
         if (var10 == -1) {
            var8 = true;
            break;
         }

         if (!$assertionsDisabled && var10 <= 0) {
            throw new AssertionError();
         }

         if (var10 > var9) {
            this.packetLength = var10;
            break;
         }

         int var11 = var7 + var10;
         if (var11 > 18713) {
            break;
         }

         var6 += var10;
         var7 = var11;
      }

      if (var7 > 0) {
         var2.skipBytes(var7);
         ByteBuffer var12 = var2.nioBuffer(var4, var7);
         this.unwrap(var1, var12, var7);
         if (!$assertionsDisabled && var12.hasRemaining() && !this.engine.isInboundDone()) {
            throw new AssertionError();
         }
      }

      if (var8) {
         NotSslRecordException var13 = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(var2));
         var2.skipBytes(var2.readableBytes());
         var1.fireExceptionCaught(var13);
         this.setHandshakeFailure(var13);
      }
   }

   public static int getEncryptedPacketLength(ByteBuf var0, int var1) {
      int var2 = 0;
      boolean var3;
      switch (var0.getUnsignedByte(var1)) {
         case 20:
         case 21:
         case 22:
         case 23:
            var3 = true;
            break;
         default:
            var3 = false;
      }

      if (var3) {
         short var4 = var0.getUnsignedByte(var1 + 1);
         if (var4 == 3) {
            var2 = var0.getUnsignedShort(var1 + 3) + 5;
            if (var2 <= 5) {
               var3 = false;
            }
         } else {
            var3 = false;
         }
      }

      if (!var3) {
         boolean var7 = true;
         int var5 = (var0.getUnsignedByte(var1) & 128) != 0 ? 2 : 3;
         short var6 = var0.getUnsignedByte(var1 + var5 + 1);
         if (var6 != 2 && var6 != 3) {
            var7 = false;
         } else {
            if (var5 == 2) {
               var2 = (var0.getShort(var1) & 32767) + 2;
            } else {
               var2 = (var0.getShort(var1) & 16383) + 3;
            }

            if (var2 <= var5) {
               var7 = false;
            }
         }

         if (!var7) {
            return -1;
         }
      }

      return var2;
   }

   public void unwrap(ChannelHandlerContext var1, ByteBuffer var2, int var3) throws javax.net.ssl.SSLException {
      int var6 = var2.position();
      ByteBuffer var4;
      ByteBuf var5;
      if (this.wantsInboundHeapBuffer && var2.isDirect()) {
         var5 = var1.alloc().heapBuffer(var2.limit() - var6);
         var5.writeBytes(var2);
         var4 = var2;
         var2 = var5.nioBuffer();
      } else {
         var4 = null;
         var5 = null;
      }

      boolean var7 = false;
      ByteBuf var8 = this.allocate(var1, var3);

      try {
         while (true) {
            SSLEngineResult var9 = unwrap(this.engine, var2, var8);
            Status var10 = var9.getStatus();
            HandshakeStatus var11 = var9.getHandshakeStatus();
            int var12 = var9.bytesProduced();
            int var13 = var9.bytesConsumed();
            if (var10 == Status.CLOSED) {
               this.sslCloseFuture.trySuccess(var1.channel());
            } else {
               switch (var11) {
                  case NEED_TASK:
                     this.runDelegatedTasks();
                     break;
                  case FINISHED:
                     this.setHandshakeSuccess();
                     var7 = true;
                     continue;
                  case NOT_HANDSHAKING:
                     if (this.setHandshakeSuccessIfStillHandshaking()) {
                        var7 = true;
                        continue;
                     }

                     if (this.flushedBeforeHandshakeDone) {
                        this.flushedBeforeHandshakeDone = false;
                        var7 = true;
                     }
                     break;
                  case NEED_WRAP:
                     this.wrapNonAppData(var1, true);
                  case NEED_UNWRAP:
                     break;
                  default:
                     throw new IllegalStateException("Unknown handshake status: " + var11);
               }

               if (var10 != Status.BUFFER_UNDERFLOW && (var13 != 0 || var12 != 0)) {
                  continue;
               }
            }

            if (var7) {
               this.wrap(var1, true);
            }
            break;
         }
      } catch (SSLException var17) {
         this.setHandshakeFailure(var17);
         throw var17;
      } finally {
         if (var5 != null) {
            ((Buffer)var4).position(var6 + var2.position());
            var5.release();
         }

         if (var8.isReadable()) {
            var1.fireChannelRead(var8);
         } else {
            var8.release();
         }
      }
   }

   @Override
   public void close(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
      this.closeOutboundAndChannel(var1, var2, false);
   }

   public void notifyHandshakeFailure(Throwable var1) {
      if (this.handshakePromise.tryFailure(var1)) {
         this.ctx.fireUserEventTriggered(new SslHandshakeCompletionEvent(var1));
         this.ctx.close();
      }
   }

   public ByteBuf allocate(ChannelHandlerContext var1, int var2) {
      ByteBufAllocator var3 = var1.alloc();
      return this.wantsDirectBuffer ? var3.directBuffer(var2) : var3.buffer(var2);
   }

   public SslHandler(SSLEngine var1, boolean var2) {
      this(var1, var2, ImmediateExecutor.INSTANCE);
   }

   public long getHandshakeTimeoutMillis() {
      return this.handshakeTimeoutMillis;
   }

   public void unwrapNonAppData(ChannelHandlerContext var1) throws javax.net.ssl.SSLException {
      this.unwrap(var1, Unpooled.EMPTY_BUFFER.nioBuffer(), 0);
   }

   @Override
   public void channelReadComplete(ChannelHandlerContext var1) throws java.lang.Exception {
      if (this.needsFlush) {
         this.needsFlush = false;
         var1.flush();
      }

      super.channelReadComplete(var1);
   }

   public void setCloseNotifyTimeoutMillis(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("closeNotifyTimeoutMillis: " + var1 + " (expected: >= 0)");
      } else {
         this.closeNotifyTimeoutMillis = var1;
      }
   }

   public static SSLEngineResult unwrap(SSLEngine var0, ByteBuffer var1, ByteBuf var2) throws javax.net.ssl.SSLException {
      int var3 = 0;

      while (true) {
         ByteBuffer var4 = var2.nioBuffer(var2.writerIndex(), var2.writableBytes());
         SSLEngineResult var5 = var0.unwrap(var1, var4);
         var2.writerIndex(var2.writerIndex() + var5.bytesProduced());
         switch (var5.getStatus()) {
            case BUFFER_OVERFLOW:
               int var6 = var0.getSession().getApplicationBufferSize();
               switch (var3++) {
                  case 0:
                     var2.ensureWritable(Math.min(var6, var1.remaining()));
                     continue;
                  default:
                     var2.ensureWritable(var6);
                     continue;
               }
            default:
               return var5;
         }
      }
   }

   public static boolean isEncrypted(ByteBuf var0) {
      if (var0.readableBytes() < 5) {
         throw new IllegalArgumentException("buffer must have at least 5 readable bytes");
      } else {
         return getEncryptedPacketLength(var0, var0.readerIndex()) != -1;
      }
   }

   public final class LazyChannelPromise extends DefaultPromise<Channel> {

      public LazyChannelPromise() {
      }

      @Override
      public EventExecutor executor() {
         if (SslHandler.this.ctx == null) {
            throw new IllegalStateException();
         } else {
            return SslHandler.this.ctx.executor();
         }
      }
   }
}
