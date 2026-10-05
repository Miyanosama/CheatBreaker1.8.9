package io.netty.channel;

import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.PlatformDependent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.RejectedExecutionException;
import net.minecraft.client.renderer.BlockModelRenderer$Orientation;
import net.minecraft.client.renderer.ChestRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.command.CommandGameRule;
import net.minecraft.util.Cartesian$1;
import org.apache.log4j.helpers.DateLayout;
import recovered.unidentified.UnidentifiedClass4298;

public abstract class AbstractChannel$AbstractUnsafe implements Channel$Unsafe {
   public BlockModelRenderer$Orientation __junk4312703401643211804;
   public ChestRenderer __junk3638790896170726732;
   public UnidentifiedClass4298 __junk4745887537953766432;
   public ItemRenderer __junk5070810985059862336;
   public Cartesian$1 __junk5904814107745525033;
   public boolean inFlush0;
   public CommandGameRule __junk6060419193489440099;
   public ChannelOutboundBuffer outboundBuffer;
   public DateLayout __junk4821214190615407769;

   @Override
   public void register(EventLoop var1, ChannelPromise var2) {
      if (var1 == null) {
         throw new NullPointerException("eventLoop");
      } else if (this.this$0.isRegistered()) {
         var2.setFailure(new IllegalStateException("registered to an event loop already"));
      } else if (!this.this$0.isCompatible(var1)) {
         var2.setFailure(new IllegalStateException("incompatible event loop type: " + var1.getClass().getName()));
      } else {
         AbstractChannel.access$002(this.this$0, var1);
         if (var1.inEventLoop()) {
            this.register0(var2);
         } else {
            try {
               var1.execute(new AbstractChannel$AbstractUnsafe$1(this, var2));
            } catch (Throwable var4) {
               AbstractChannel.access$200().warn("Force-closing a channel whose registration task was not accepted by an event loop: {}", this.this$0, var4);
               this.closeForcibly();
               AbstractChannel.access$300(this.this$0).setClosed();
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
         boolean var2 = this.this$0.isActive();

         try {
            this.this$0.doDisconnect();
         } catch (Throwable var4) {
            this.safeSetFailure(var1, var4);
            this.closeIfClosed();
            return;
         }

         if (var2 && !this.this$0.isActive()) {
            this.invokeLater(new AbstractChannel$AbstractUnsafe$3(this));
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

         this.this$0.doRegister();
         AbstractChannel.access$402(this.this$0, true);
         this.safeSetSuccess(var1);
         AbstractChannel.access$500(this.this$0).fireChannelRegistered();
         if (this.this$0.isActive()) {
            AbstractChannel.access$500(this.this$0).fireChannelActive();
         }
      } catch (Throwable var3) {
         this.closeForcibly();
         AbstractChannel.access$300(this.this$0).setClosed();
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
            var1 = this.this$0.filterOutboundMessage(var1);
            var4 = this.this$0.estimatorHandle().size(var1);
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
   public void close(ChannelPromise var1) {
      if (var1.setUncancellable()) {
         if (this.inFlush0) {
            this.invokeLater(new AbstractChannel$AbstractUnsafe$4(this, var1));
         } else if (AbstractChannel.access$300(this.this$0).isDone()) {
            this.safeSetSuccess(var1);
         } else {
            boolean var2 = this.this$0.isActive();
            ChannelOutboundBuffer var3 = this.outboundBuffer;
            this.outboundBuffer = null;

            try {
               this.this$0.doClose();
               AbstractChannel.access$300(this.this$0).setClosed();
               this.safeSetSuccess(var1);
            } catch (Throwable var8) {
               AbstractChannel.access$300(this.this$0).setClosed();
               this.safeSetFailure(var1, var8);
            }

            try {
               var3.failFlushed(AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
               var3.close(AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
            } finally {
               if (var2 && !this.this$0.isActive()) {
                  this.invokeLater(new AbstractChannel$AbstractUnsafe$5(this));
               }

               this.deregister(this.voidPromise());
            }
         }
      }
   }

   public void safeSetFailure(ChannelPromise var1, Throwable var2) {
      if (!(var1 instanceof VoidChannelPromise) && !var1.tryFailure(var2)) {
         AbstractChannel.access$200().warn("Failed to mark a promise as failure because it's done already: {}", var1, var2);
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
      return this.this$0.remoteAddress0();
   }

   public AbstractChannel$AbstractUnsafe(AbstractChannel var1) {
      this.this$0 = var1;
      super();
      this.outboundBuffer = new ChannelOutboundBuffer(this.this$0);
   }

   @Override
   public SocketAddress localAddress() {
      return this.this$0.localAddress0();
   }

   @Override
   public ChannelPromise voidPromise() {
      return AbstractChannel.access$600(this.this$0);
   }

   public boolean ensureOpen(ChannelPromise var1) {
      if (this.this$0.isOpen()) {
         return true;
      } else {
         this.safeSetFailure(var1, AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
         return false;
      }
   }

   @Override
   public void deregister(ChannelPromise var1) {
      if (var1.setUncancellable()) {
         if (!AbstractChannel.access$400(this.this$0)) {
            this.safeSetSuccess(var1);
         } else {
            try {
               this.this$0.doDeregister();
            } catch (Throwable var6) {
               AbstractChannel.access$200().warn("Unexpected exception occurred while deregistering a channel.", var6);
            } finally {
               if (AbstractChannel.access$400(this.this$0)) {
                  AbstractChannel.access$402(this.this$0, false);
                  this.invokeLater(new AbstractChannel$AbstractUnsafe$6(this));
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
            && Boolean.TRUE.equals(this.this$0.config().getOption(ChannelOption.SO_BROADCAST))
            && var1 instanceof InetSocketAddress
            && !((InetSocketAddress)var1).getAddress().isAnyLocalAddress()) {
            AbstractChannel.access$200()
               .warn(
                  "A non-root user can't receive a broadcast packet if the socket is not bound to a wildcard address; binding to a non-wildcard address ("
                     + var1
                     + ") anyway as requested."
               );
         }

         boolean var3 = this.this$0.isActive();

         try {
            this.this$0.doBind(var1);
         } catch (Throwable var5) {
            this.safeSetFailure(var2, var5);
            this.closeIfClosed();
            return;
         }

         if (!var3 && this.this$0.isActive()) {
            this.invokeLater(new AbstractChannel$AbstractUnsafe$2(this));
         }

         this.safeSetSuccess(var2);
      }
   }

   public void safeSetSuccess(ChannelPromise var1) {
      if (!(var1 instanceof VoidChannelPromise) && !var1.trySuccess()) {
         AbstractChannel.access$200().warn("Failed to mark a promise as success because it is done already: {}", var1);
      }
   }

   @Override
   public void beginRead() {
      if (this.this$0.isActive()) {
         try {
            this.this$0.doBeginRead();
         } catch (Exception var2) {
            this.invokeLater(new AbstractChannel$AbstractUnsafe$7(this, var2));
            this.close(this.voidPromise());
         }
      }
   }

   public void invokeLater(Runnable var1) {
      try {
         this.this$0.eventLoop().execute(var1);
      } catch (RejectedExecutionException var3) {
         AbstractChannel.access$200().warn("Can't invoke task later as EventLoop rejected it", (Throwable)var3);
      }
   }

   public void flush0() {
      if (!this.inFlush0) {
         ChannelOutboundBuffer var1 = this.outboundBuffer;
         if (var1 != null && !var1.isEmpty()) {
            this.inFlush0 = true;
            if (!this.this$0.isActive()) {
               try {
                  if (this.this$0.isOpen()) {
                     var1.failFlushed(AbstractChannel.NOT_YET_CONNECTED_EXCEPTION);
                  } else {
                     var1.failFlushed(AbstractChannel.CLOSED_CHANNEL_EXCEPTION);
                  }
               } finally {
                  this.inFlush0 = false;
               }
            } else {
               try {
                  this.this$0.doWrite(var1);
               } catch (Throwable var11) {
                  var1.failFlushed(var11);
                  if (var11 instanceof IOException && this.this$0.config().isAutoClose()) {
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
         this.this$0.doClose();
      } catch (Exception var2) {
         AbstractChannel.access$200().warn("Failed to close a channel.", (Throwable)var2);
      }
   }

   public void closeIfClosed() {
      if (!this.this$0.isOpen()) {
         this.close(this.voidPromise());
      }
   }
}
