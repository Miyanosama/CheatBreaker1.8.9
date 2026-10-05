package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ReadOnlyByteBufferBuf;
import io.netty.handler.codec.http.websocketx.WebSocket08FrameDecoder;
import io.netty.handler.codec.spdy.DefaultSpdyHeaders;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.OneTimeTask;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThreadLocalRandom;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.util.concurrent.RejectedExecutionException;
import javazoom.jl.converter.jlc;
import net.minecraft.block.BlockSlime;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.ChestRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.VboRenderList;
import net.minecraft.command.CommandGameRule;
import net.minecraft.command.server.CommandSaveOn;
import net.minecraft.item.crafting.RecipesDyes;
import net.minecraft.util.Cartesian_1;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import net.optifine.player.CapeUtils;
import org.apache.log4j.helpers.DateLayout;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$3;
import org.apache.log4j.pattern.FullLocationPatternConverter;
import org.java_websocket.server.WebSocketServer;
import org.newsclub.net.unix.AFUNIXSocketImpl;
import org.slf4j.helpers.NamedLoggerBase;
import com.cheatbreaker.client.nethandler.server.PacketDeleteVoiceChannel;
import com.cheatbreaker.client.nethandler.server.PacketServerUpdate;
import net.minecraft.world.gen.feature.WorldGenShrub;

public abstract class AbstractChannel extends DefaultAttributeMap implements Channel {
   public MessageSizeEstimator.Handle estimatorHandle;
   public long hashCode = ThreadLocalRandom.current().nextLong();
   public static InternalLogger logger = InternalLoggerFactory.getInstance(AbstractChannel.class);
   public VoidChannelPromise unsafeVoidPromise;
   public AbstractChannel.CloseFuture closeFuture;
   public String strVal;
   public static ClosedChannelException CLOSED_CHANNEL_EXCEPTION = new ClosedChannelException();
   public ChannelFuture succeededFuture = new SucceededChannelFuture(this, null);
   public volatile EventLoop eventLoop;
   public boolean strValActive;
   public Channel parent;
   public volatile boolean registered;
   public Channel.Unsafe unsafe;
   public volatile SocketAddress localAddress;
   public DefaultChannelPipeline pipeline;
   public volatile SocketAddress remoteAddress;
   public static NotYetConnectedException NOT_YET_CONNECTED_EXCEPTION = new NotYetConnectedException();
   public VoidChannelPromise voidPromise = new VoidChannelPromise(this, true);

   @Override
   public ChannelFuture write(Object var1) {
      return this.pipeline.write(var1);
   }

   public void doDeregister() throws java.lang.Exception {
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1, ChannelPromise var2) {
      return this.pipeline.writeAndFlush(var1, var2);
   }

   @Override
   public Channel read() {
      this.pipeline.read();
      return this;
   }

   public abstract void doBind(SocketAddress var1) throws java.lang.Exception ;

   public abstract void doDisconnect() throws java.lang.Exception ;

   @Override
   public ChannelFuture newSucceededFuture() {
      return this.succeededFuture;
   }

   @Override
   public Channel.Unsafe unsafe() {
      return this.unsafe;
   }

   public void doRegister() throws java.lang.Exception {
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      return this.pipeline.connect(var1, var2, var3);
   }

   @Override
   public boolean isWritable() {
      ChannelOutboundBuffer var1 = this.unsafe.outboundBuffer();
      return var1 != null && var1.isWritable();
   }

   @Override
   public Channel parent() {
      return this.parent;
   }

   @Override
   public SocketAddress remoteAddress() {
      SocketAddress var1 = this.remoteAddress;
      if (var1 == null) {
         try {
            this.remoteAddress = var1 = this.unsafe().remoteAddress();
         } catch (Throwable var3) {
            return null;
         }
      }

      return var1;
   }

   @Override
   public SocketAddress localAddress() {
      SocketAddress var1 = this.localAddress;
      if (var1 == null) {
         try {
            this.localAddress = var1 = this.unsafe().localAddress();
         } catch (Throwable var3) {
            return null;
         }
      }

      return var1;
   }

   @Override
   public Channel flush() {
      this.pipeline.flush();
      return this;
   }

   public abstract boolean isCompatible(EventLoop var1);

   @Override
   public ChannelFuture newFailedFuture(Throwable var1) {
      return new FailedChannelFuture(this, null, var1);
   }

   public abstract void doBeginRead() throws java.lang.Exception ;

   @Override
   public ChannelFuture closeFuture() {
      return this.closeFuture;
   }

   @Override
   public ChannelFuture write(Object var1, ChannelPromise var2) {
      return this.pipeline.write(var1, var2);
   }

   @Override
   public ChannelFuture disconnect() {
      return this.pipeline.disconnect();
   }

   @Override
   public ChannelFuture deregister(ChannelPromise var1) {
      return this.pipeline.deregister(var1);
   }

   @Override
   public ChannelFuture close() {
      return this.pipeline.close();
   }

   @Override
   public ChannelFuture close(ChannelPromise var1) {
      return this.pipeline.close(var1);
   }

   @Override
   public ChannelProgressivePromise newProgressivePromise() {
      return new DefaultChannelProgressivePromise(this);
   }

   @Override
   public String toString() {
      boolean var1 = this.isActive();
      if (this.strValActive == var1 && this.strVal != null) {
         return this.strVal;
      } else {
         SocketAddress var2 = this.remoteAddress();
         SocketAddress var3 = this.localAddress();
         if (var2 != null) {
            SocketAddress var4;
            SocketAddress var5;
            if (this.parent == null) {
               var4 = var3;
               var5 = var2;
            } else {
               var4 = var2;
               var5 = var3;
            }

            this.strVal = String.format("[id: 0x%08x, %s %s %s]", (int)this.hashCode, var4, var1 ? "=>" : ":>", var5);
         } else if (var3 != null) {
            this.strVal = String.format("[id: 0x%08x, %s]", (int)this.hashCode, var3);
         } else {
            this.strVal = String.format("[id: 0x%08x]", (int)this.hashCode);
         }

         this.strValActive = var1;
         return this.strVal;
      }
   }

   @Override
   public ChannelPromise voidPromise() {
      return this.voidPromise;
   }

   public void invalidateRemoteAddress() {
      this.remoteAddress = null;
   }

   public AbstractChannel(Channel var1) {
      this.unsafeVoidPromise = new VoidChannelPromise(this, false);
      this.closeFuture = new AbstractChannel.CloseFuture(this);
      this.parent = var1;
      this.unsafe = this.newUnsafe();
      this.pipeline = new DefaultChannelPipeline(this);
   }

   public abstract void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception ;

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2) {
      return this.pipeline.connect(var1, var2);
   }

   public int compareTo(Channel var1) {
      if (this == var1) {
         return 0;
      } else {
         long var2 = this.hashCode - var1.hashCode();
         if (var2 > 0L) {
            return 1;
         } else if (var2 < 0L) {
            return -1;
         } else {
            var2 = System.identityHashCode(this) - System.identityHashCode(var1);
            if (var2 != 0L) {
               return (int)var2;
            } else {
               throw new Error();
            }
         }
      }
   }

   @Override
   public ChannelFuture bind(SocketAddress var1) {
      return this.pipeline.bind(var1);
   }

   public abstract SocketAddress remoteAddress0();

   @Override
   public int hashCode() {
      return (int)this.hashCode;
   }

   static {
      CLOSED_CHANNEL_EXCEPTION.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      NOT_YET_CONNECTED_EXCEPTION.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1) {
      return this.pipeline.writeAndFlush(var1);
   }

   public MessageSizeEstimator.Handle estimatorHandle() {
      if (this.estimatorHandle == null) {
         this.estimatorHandle = this.config().getMessageSizeEstimator().newHandle();
      }

      return this.estimatorHandle;
   }

   public Object filterOutboundMessage(Object var1) throws java.lang.Exception {
      return var1;
   }

   @Override
   public ChannelFuture bind(SocketAddress var1, ChannelPromise var2) {
      return this.pipeline.bind(var1, var2);
   }

   @Override
   public boolean isRegistered() {
      return this.registered;
   }

   @Override
   public ChannelPipeline pipeline() {
      return this.pipeline;
   }

   public abstract AbstractChannel.AbstractUnsafe newUnsafe();

   @Override
   public ChannelFuture connect(SocketAddress var1, ChannelPromise var2) {
      return this.pipeline.connect(var1, var2);
   }

   public abstract SocketAddress localAddress0();

   @Override
   public ChannelFuture connect(SocketAddress var1) {
      return this.pipeline.connect(var1);
   }

   public void invalidateLocalAddress() {
      this.localAddress = null;
   }

   @Override
   public ChannelFuture disconnect(ChannelPromise var1) {
      return this.pipeline.disconnect(var1);
   }

   @Override
   public ChannelPromise newPromise() {
      return new DefaultChannelPromise(this);
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.config().getAllocator();
   }

   public abstract void doClose() throws java.lang.Exception ;

   @Override
   public EventLoop eventLoop() {
      EventLoop var1 = this.eventLoop;
      if (var1 == null) {
         throw new IllegalStateException("channel not registered to an event loop");
      } else {
         return var1;
      }
   }

   @Override
   public ChannelFuture deregister() {
      return this.pipeline.deregister();
   }

   public abstract class AbstractUnsafe implements Channel.Unsafe {
      public boolean inFlush0;
      public ChannelOutboundBuffer outboundBuffer = new ChannelOutboundBuffer(AbstractChannel.this);

      @Override
      public void register(EventLoop var1, final ChannelPromise var2) {
         if (var1 == null) {
            throw new NullPointerException("eventLoop");
         } else if (AbstractChannel.this.isRegistered()) {
            var2.setFailure(new IllegalStateException("registered to an event loop already"));
         } else if (!AbstractChannel.this.isCompatible(var1)) {
            var2.setFailure(new IllegalStateException("incompatible event loop type: " + var1.getClass().getName()));
         } else {
            AbstractChannel.this.eventLoop = var1;
            if (var1.inEventLoop()) {
               this.register0(var2);
            } else {
               try {
                  var1.execute(new OneTimeTask() {

                     @Override
                     public void run() {
                        AbstractUnsafe.this.register0(var2);
                     }
                  });
               } catch (Throwable var4) {
                  AbstractChannel.logger
                     .warn("Force-closing a channel whose registration task was not accepted by an event loop: {}", AbstractChannel.this, var4);
                  this.closeForcibly();
                  AbstractChannel.this.closeFuture.setClosed();
                  this.safeSetFailure(var2, var4);
               }
            }
         }
      }

      @Override
      public ChannelOutboundBuffer outboundBuffer() {
         return this.outboundBuffer;
      }

      @Override
      public void disconnect(ChannelPromise var1) {
         if (var1.setUncancellable()) {
            boolean var2 = AbstractChannel.this.isActive();

            try {
               AbstractChannel.this.doDisconnect();
            } catch (Throwable var4) {
               this.safeSetFailure(var1, var4);
               this.closeIfClosed();
               return;
            }

            if (var2 && !AbstractChannel.this.isActive()) {
               this.invokeLater(new OneTimeTask() {

                  @Override
                  public void run() {
                     AbstractChannel.this.pipeline.fireChannelInactive();
                  }
               });
            }

            this.safeSetSuccess(var1);
            this.closeIfClosed();
         }
      }

      public void register0(ChannelPromise var1) {
         try {
            if (!var1.setUncancellable() || !this.ensureOpen(var1)) {
               return;
            }

            AbstractChannel.this.doRegister();
            AbstractChannel.this.registered = true;
            this.safeSetSuccess(var1);
            AbstractChannel.this.pipeline.fireChannelRegistered();
            if (AbstractChannel.this.isActive()) {
               AbstractChannel.this.pipeline.fireChannelActive();
            }
         } catch (Throwable var3) {
            this.closeForcibly();
            AbstractChannel.this.closeFuture.setClosed();
            this.safeSetFailure(var1, var3);
         }
      }

      @Override
      public void write(Object var1, ChannelPromise var2) {
         ChannelOutboundBuffer var3 = this.outboundBuffer;
         if (var3 == null) {
            this.safeSetFailure(var2, AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
            ReferenceCountUtil.release(var1);
         } else {
            int var4;
            try {
               var1 = AbstractChannel.this.filterOutboundMessage(var1);
               var4 = AbstractChannel.this.estimatorHandle().size(var1);
               if (var4 < 0) {
                  var4 = 0;
               }
            } catch (Throwable var6) {
               this.safeSetFailure(var2, var6);
               ReferenceCountUtil.release(var1);
               return;
            }

            var3.addMessage(var1, var4, var2);
         }
      }

      @Override
      public void close(final ChannelPromise var1) {
         if (var1.setUncancellable()) {
            if (this.inFlush0) {
               this.invokeLater(new OneTimeTask() {

                  @Override
                  public void run() {
                     AbstractUnsafe.this.close(var1);
                  }
               });
            } else if (AbstractChannel.this.closeFuture.isDone()) {
               this.safeSetSuccess(var1);
            } else {
               boolean var2 = AbstractChannel.this.isActive();
               ChannelOutboundBuffer var3 = this.outboundBuffer;
               this.outboundBuffer = null;

               try {
                  AbstractChannel.this.doClose();
                  AbstractChannel.this.closeFuture.setClosed();
                  this.safeSetSuccess(var1);
               } catch (Throwable var8) {
                  AbstractChannel.this.closeFuture.setClosed();
                  this.safeSetFailure(var1, var8);
               }

               try {
                  var3.failFlushed(AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
                  var3.close(AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
               } finally {
                  if (var2 && !AbstractChannel.this.isActive()) {
                     this.invokeLater(new OneTimeTask() {

                        @Override
                        public void run() {
                           AbstractChannel.this.pipeline.fireChannelInactive();
                        }
                     });
                  }

                  this.deregister(this.voidPromise());
               }
            }
         }
      }

      public void safeSetFailure(ChannelPromise var1, Throwable var2) {
         if (!(var1 instanceof VoidChannelPromise) && !var1.tryFailure(var2)) {
            AbstractChannel.logger.warn("Failed to mark a promise as failure because it's done already: {}", var1, var2);
         }
      }

      @Override
      public void flush() {
         ChannelOutboundBuffer var1 = this.outboundBuffer;
         if (var1 != null) {
            var1.addFlush();
            this.flush0();
         }
      }

      @Override
      public SocketAddress remoteAddress() {
         return AbstractChannel.this.remoteAddress0();
      }

      @Override
      public SocketAddress localAddress() {
         return AbstractChannel.this.localAddress0();
      }

      @Override
      public ChannelPromise voidPromise() {
         return AbstractChannel.this.unsafeVoidPromise;
      }

      public boolean ensureOpen(ChannelPromise var1) {
         if (AbstractChannel.this.isOpen()) {
            return true;
         } else {
            this.safeSetFailure(var1, AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
            return false;
         }
      }

      @Override
      public void deregister(ChannelPromise var1) {
         if (var1.setUncancellable()) {
            if (!AbstractChannel.this.registered) {
               this.safeSetSuccess(var1);
            } else {
               try {
                  AbstractChannel.this.doDeregister();
               } catch (Throwable var6) {
                  AbstractChannel.logger.warn("Unexpected exception occurred while deregistering a channel.", var6);
               } finally {
                  if (AbstractChannel.this.registered) {
                     AbstractChannel.this.registered = false;
                     this.invokeLater(new OneTimeTask() {

                        @Override
                        public void run() {
                           AbstractChannel.this.pipeline.fireChannelUnregistered();
                        }
                     });
                     this.safeSetSuccess(var1);
                  } else {
                     this.safeSetSuccess(var1);
                  }
               }
            }
         }
      }

      @Override
      public void bind(SocketAddress var1, ChannelPromise var2) {
         if (var2.setUncancellable() && this.ensureOpen(var2)) {
            if (!PlatformDependent.isWindows()
               && !PlatformDependent.isRoot()
               && Boolean.TRUE.equals(AbstractChannel.this.config().getOption(ChannelOption.SO_BROADCAST))
               && var1 instanceof InetSocketAddress
               && !((InetSocketAddress)var1).getAddress().isAnyLocalAddress()) {
               AbstractChannel.logger
                  .warn(
                     "A non-root user can't receive a broadcast packet if the socket is not bound to a wildcard address; binding to a non-wildcard address ("
                        + var1
                        + ") anyway as requested."
                  );
            }

            boolean var3 = AbstractChannel.this.isActive();

            try {
               AbstractChannel.this.doBind(var1);
            } catch (Throwable var5) {
               this.safeSetFailure(var2, var5);
               this.closeIfClosed();
               return;
            }

            if (!var3 && AbstractChannel.this.isActive()) {
               this.invokeLater(new OneTimeTask() {

                  @Override
                  public void run() {
                     AbstractChannel.this.pipeline.fireChannelActive();
                  }
               });
            }

            this.safeSetSuccess(var2);
         }
      }

      public void safeSetSuccess(ChannelPromise var1) {
         if (!(var1 instanceof VoidChannelPromise) && !var1.trySuccess()) {
            AbstractChannel.logger.warn("Failed to mark a promise as success because it is done already: {}", var1);
         }
      }

      @Override
      public void beginRead() {
         if (AbstractChannel.this.isActive()) {
            try {
               AbstractChannel.this.doBeginRead();
            } catch (final Exception var2) {
               this.invokeLater(new OneTimeTask() {

                  @Override
                  public void run() {
                     AbstractChannel.this.pipeline.fireExceptionCaught(var2);
                  }
               });
               this.close(this.voidPromise());
            }
         }
      }

      public void invokeLater(Runnable var1) {
         try {
            AbstractChannel.this.eventLoop().execute(var1);
         } catch (RejectedExecutionException var3) {
            AbstractChannel.logger.warn("Can't invoke task later as EventLoop rejected it", (Throwable)var3);
         }
      }

      public void flush0() {
         if (!this.inFlush0) {
            ChannelOutboundBuffer var1 = this.outboundBuffer;
            if (var1 != null && !var1.isEmpty()) {
               this.inFlush0 = true;
               if (!AbstractChannel.this.isActive()) {
                  try {
                     if (AbstractChannel.this.isOpen()) {
                        var1.failFlushed(AbstractChannel.NOT_YET_CONNECTED_EXCEPTION);
                     } else {
                        var1.failFlushed(AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
                     }
                  } finally {
                     this.inFlush0 = false;
                  }
               } else {
                  try {
                     AbstractChannel.this.doWrite(var1);
                  } catch (Throwable var11) {
                     var1.failFlushed(var11);
                     if (var11 instanceof IOException && AbstractChannel.this.config().isAutoClose()) {
                        this.close(this.voidPromise());
                     }
                  } finally {
                     this.inFlush0 = false;
                  }
               }
            }
         }
      }

      @Override
      public void closeForcibly() {
         try {
            AbstractChannel.this.doClose();
         } catch (Exception var2) {
            AbstractChannel.logger.warn("Failed to close a channel.", (Throwable)var2);
         }
      }

      public void closeIfClosed() {
         if (!AbstractChannel.this.isOpen()) {
            this.close(this.voidPromise());
         }
      }
   }

   public static final class CloseFuture extends DefaultChannelPromise {

      @Override
      public ChannelPromise setSuccess() {
         throw new IllegalStateException();
      }

      @Override
      public ChannelPromise setFailure(Throwable var1) {
         throw new IllegalStateException();
      }

      public CloseFuture(AbstractChannel var1) {
         super(var1);
      }

      @Override
      public boolean tryFailure(Throwable var1) {
         throw new IllegalStateException();
      }

      public boolean setClosed() {
         return super.trySuccess();
      }

      @Override
      public boolean trySuccess() {
         throw new IllegalStateException();
      }
   }
}
