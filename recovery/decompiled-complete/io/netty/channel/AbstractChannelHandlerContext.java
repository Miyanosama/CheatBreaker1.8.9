package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http.HttpObjectAggregator$AggregatedFullHttpResponse;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.internal.StringUtil;
import java.net.SocketAddress;
import net.minecraft.network.NettyEncryptingEncoder;
import net.minecraft.village.Village$VillageAggressor;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.optifine.util.IteratorCache;

public abstract class AbstractChannelHandlerContext extends DefaultAttributeMap implements ChannelHandlerContext {
   public volatile Runnable invokeFlushTask;
   public volatile Runnable invokeChannelReadCompleteTask;
   public boolean inbound;
   public volatile AbstractChannelHandlerContext prev;
   public MapGenVillage __junk256427953513960597;
   public ChannelFuture succeededFuture;
   public DefaultChannelPipeline pipeline;
   public NettyEncryptingEncoder __junk283758376914216501;
   public AbstractChannel channel;
   public boolean removed;
   public Village$VillageAggressor __junk4562755708183504988;
   public IteratorCache __junk4223691239078150948;
   public volatile Runnable invokeReadTask;
   public EventExecutor executor;
   public HttpObjectAggregator$AggregatedFullHttpResponse __junk6463988565662759738;
   public volatile Runnable invokeChannelWritableStateChangedTask;
   public volatile AbstractChannelHandlerContext next;
   public boolean outbound;
   public String name;

   public void invokeChannelWritabilityChanged() {
      try {
         ((ChannelInboundHandler)this.handler()).channelWritabilityChanged(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   @Override
   public ChannelFuture close() {
      return this.close(this.newPromise());
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1, ChannelPromise var2) {
      if (var1 == null) {
         throw new NullPointerException("msg");
      } else if (!this.validatePromise(var2, true)) {
         ReferenceCountUtil.release(var1);
         return var2;
      } else {
         this.write(var1, true, var2);
         return var2;
      }
   }

   public void invokeChannelReadComplete() {
      try {
         ((ChannelInboundHandler)this.handler()).channelReadComplete(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   public AbstractChannelHandlerContext findContextOutbound() {
      AbstractChannelHandlerContext var1 = this;

      do {
         var1 = var1.prev;
      } while (!var1.outbound);

      return var1;
   }

   @Override
   public ChannelHandlerContext flush() {
      AbstractChannelHandlerContext var1 = this.findContextOutbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeFlush();
      } else {
         Object var3 = var1.invokeFlushTask;
         if (var3 == null) {
            var1.invokeFlushTask = (Runnable)(var3 = new AbstractChannelHandlerContext$17(this, var1));
         }

         safeExecute(var2, (Runnable)var3, this.channel.voidPromise(), null);
      }

      return this;
   }

   public void teardown0() {
      AbstractChannelHandlerContext var1 = this.prev;
      if (var1 != null) {
         synchronized (this.pipeline) {
            this.pipeline.remove0(this);
         }

         var1.teardown();
      }
   }

   public static void notifyOutboundHandlerException(Throwable var0, ChannelPromise var1) {
      if (!(var1 instanceof VoidChannelPromise)) {
         if (!var1.tryFailure(var0) && DefaultChannelPipeline.logger.isWarnEnabled()) {
            DefaultChannelPipeline.logger.warn("Failed to fail the promise because it's done already: {}", var1, var0);
         }
      }
   }

   @Override
   public ChannelPromise newPromise() {
      return new DefaultChannelPromise(this.channel(), this.executor());
   }

   @Override
   public ChannelHandlerContext read() {
      AbstractChannelHandlerContext var1 = this.findContextOutbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeRead();
      } else {
         Object var3 = var1.invokeReadTask;
         if (var3 == null) {
            var1.invokeReadTask = (Runnable)(var3 = new AbstractChannelHandlerContext$16(this, var1));
         }

         var2.execute((Runnable)var3);
      }

      return this;
   }

   public AbstractChannelHandlerContext findContextInbound() {
      AbstractChannelHandlerContext var1 = this;

      do {
         var1 = var1.next;
      } while (!var1.inbound);

      return var1;
   }

   @Override
   public String name() {
      return this.name;
   }

   public void invokeChannelRegistered() {
      try {
         ((ChannelInboundHandler)this.handler()).channelRegistered(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   public void write(Object var1, boolean var2, ChannelPromise var3) {
      AbstractChannelHandlerContext var4 = this.findContextOutbound();
      EventExecutor var5 = var4.executor();
      if (var5.inEventLoop()) {
         var4.invokeWrite(var1, var3);
         if (var2) {
            var4.invokeFlush();
         }
      } else {
         int var6 = this.channel.estimatorHandle().size(var1);
         if (var6 > 0) {
            ChannelOutboundBuffer var7 = this.channel.unsafe().outboundBuffer();
            if (var7 != null) {
               var7.incrementPendingOutboundBytes(var6);
            }
         }

         Object var8;
         if (var2) {
            var8 = AbstractChannelHandlerContext$WriteAndFlushTask.access$1700(var4, var1, var6, var3);
         } else {
            var8 = AbstractChannelHandlerContext$WriteTask.access$1800(var4, var1, var6, var3);
         }

         safeExecute(var5, (Runnable)var8, var3, var1);
      }
   }

   public static void safeExecute(EventExecutor var0, Runnable var1, ChannelPromise var2, Object var3) {
      try {
         var0.execute(var1);
      } catch (Throwable var9) {
         Throwable var4 = var9;

         try {
            var2.setFailure(var4);
         } finally {
            if (var3 != null) {
               ReferenceCountUtil.release(var3);
            }
         }
      }
   }

   public boolean validatePromise(ChannelPromise var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("promise");
      } else if (var1.isDone()) {
         if (var1.isCancelled()) {
            return false;
         } else {
            throw new IllegalArgumentException("promise already done: " + var1);
         }
      } else if (var1.channel() != this.channel()) {
         throw new IllegalArgumentException(String.format("promise.channel does not match: %s (expected: %s)", var1.channel(), this.channel()));
      } else if (var1.getClass() == DefaultChannelPromise.class) {
         return true;
      } else if (!var2 && var1 instanceof VoidChannelPromise) {
         throw new IllegalArgumentException(StringUtil.simpleClassName(VoidChannelPromise.class) + " not allowed for this operation");
      } else if (var1 instanceof AbstractChannel$CloseFuture) {
         throw new IllegalArgumentException(StringUtil.simpleClassName(AbstractChannel$CloseFuture.class) + " not allowed in a pipeline");
      } else {
         return true;
      }
   }

   @Override
   public ChannelFuture write(Object var1, ChannelPromise var2) {
      if (var1 == null) {
         throw new NullPointerException("msg");
      } else if (!this.validatePromise(var2, true)) {
         ReferenceCountUtil.release(var1);
         return var2;
      } else {
         this.write(var1, false, var2);
         return var2;
      }
   }

   public void invokeUserEventTriggered(Object var1) {
      try {
         ((ChannelInboundHandler)this.handler()).userEventTriggered(this, var1);
      } catch (Throwable var3) {
         this.notifyHandlerException(var3);
      }
   }

   @Override
   public ChannelFuture deregister() {
      return this.deregister(this.newPromise());
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2) {
      return this.connect(var1, var2, this.newPromise());
   }

   public void setRemoved() {
      this.removed = true;
   }

   @Override
   public ChannelFuture bind(SocketAddress var1, ChannelPromise var2) {
      if (var1 == null) {
         throw new NullPointerException("localAddress");
      } else if (!this.validatePromise(var2, false)) {
         return var2;
      } else {
         AbstractChannelHandlerContext var3 = this.findContextOutbound();
         EventExecutor var4 = var3.executor();
         if (var4.inEventLoop()) {
            var3.invokeBind(var1, var2);
         } else {
            safeExecute(var4, new AbstractChannelHandlerContext$11(this, var3, var1, var2), var2, null);
         }

         return var2;
      }
   }

   public void invokeDisconnect(ChannelPromise var1) {
      try {
         ((ChannelOutboundHandler)this.handler()).disconnect(this, var1);
      } catch (Throwable var3) {
         notifyOutboundHandlerException(var3, var1);
      }
   }

   @Override
   public ChannelHandlerContext fireChannelUnregistered() {
      AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelUnregistered();
      } else {
         var2.execute(new AbstractChannelHandlerContext$3(this, var1));
      }

      return this;
   }

   @Override
   public ChannelHandlerContext fireChannelRead(Object var1) {
      if (var1 == null) {
         throw new NullPointerException("msg");
      } else {
         AbstractChannelHandlerContext var2 = this.findContextInbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeChannelRead(var1);
         } else {
            var3.execute(new AbstractChannelHandlerContext$8(this, var2, var1));
         }

         return this;
      }
   }

   @Override
   public ChannelFuture deregister(ChannelPromise var1) {
      if (!this.validatePromise(var1, false)) {
         return var1;
      } else {
         AbstractChannelHandlerContext var2 = this.findContextOutbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeDeregister(var1);
         } else {
            safeExecute(var3, new AbstractChannelHandlerContext$15(this, var2, var1), var1, null);
         }

         return var1;
      }
   }

   public void invokeConnect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      try {
         ((ChannelOutboundHandler)this.handler()).connect(this, var1, var2, var3);
      } catch (Throwable var5) {
         notifyOutboundHandlerException(var5, var3);
      }
   }

   @Override
   public ChannelFuture connect(SocketAddress var1) {
      return this.connect(var1, this.newPromise());
   }

   @Override
   public ChannelHandlerContext fireChannelWritabilityChanged() {
      AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelWritabilityChanged();
      } else {
         Object var3 = var1.invokeChannelWritableStateChangedTask;
         if (var3 == null) {
            var1.invokeChannelWritableStateChangedTask = (Runnable)(var3 = new AbstractChannelHandlerContext$10(this, var1));
         }

         var2.execute((Runnable)var3);
      }

      return this;
   }

   public void invokeWrite(Object var1, ChannelPromise var2) {
      try {
         ((ChannelOutboundHandler)this.handler()).write(this, var1, var2);
      } catch (Throwable var4) {
         notifyOutboundHandlerException(var4, var2);
      }
   }

   @Override
   public ChannelHandlerContext fireChannelInactive() {
      AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelInactive();
      } else {
         var2.execute(new AbstractChannelHandlerContext$5(this, var1));
      }

      return this;
   }

   @Override
   public ChannelHandlerContext fireChannelRegistered() {
      AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelRegistered();
      } else {
         var2.execute(new AbstractChannelHandlerContext$2(this, var1));
      }

      return this;
   }

   public void invokeChannelUnregistered() {
      try {
         ((ChannelInboundHandler)this.handler()).channelUnregistered(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   @Override
   public ChannelHandlerContext fireUserEventTriggered(Object var1) {
      if (var1 == null) {
         throw new NullPointerException("event");
      } else {
         AbstractChannelHandlerContext var2 = this.findContextInbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeUserEventTriggered(var1);
         } else {
            var3.execute(new AbstractChannelHandlerContext$7(this, var2, var1));
         }

         return this;
      }
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1) {
      return this.writeAndFlush(var1, this.newPromise());
   }

   public static boolean inExceptionCaught(Throwable var0) {
      do {
         StackTraceElement[] var1 = var0.getStackTrace();
         if (var1 != null) {
            for (StackTraceElement var5 : var1) {
               if (var5 == null) {
                  break;
               }

               if ("exceptionCaught".equals(var5.getMethodName())) {
                  return true;
               }
            }
         }

         var0 = var0.getCause();
      } while (var0 != null);

      return false;
   }

   @Override
   public ChannelProgressivePromise newProgressivePromise() {
      return new DefaultChannelProgressivePromise(this.channel(), this.executor());
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.channel().config().getAllocator();
   }

   @Override
   public ChannelHandlerContext fireExceptionCaught(Throwable var1) {
      if (var1 == null) {
         throw new NullPointerException("cause");
      } else {
         AbstractChannelHandlerContext var2 = this.next;
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeExceptionCaught(var1);
         } else {
            try {
               var3.execute(new AbstractChannelHandlerContext$6(this, var2, var1));
            } catch (Throwable var5) {
               if (DefaultChannelPipeline.logger.isWarnEnabled()) {
                  DefaultChannelPipeline.logger.warn("Failed to submit an exceptionCaught() event.", var5);
                  DefaultChannelPipeline.logger.warn("The exceptionCaught() event that was failed to submit was:", var1);
               }
            }
         }

         return this;
      }
   }

   public void invokeClose(ChannelPromise var1) {
      try {
         ((ChannelOutboundHandler)this.handler()).close(this, var1);
      } catch (Throwable var3) {
         notifyOutboundHandlerException(var3, var1);
      }
   }

   @Override
   public ChannelFuture disconnect(ChannelPromise var1) {
      if (!this.validatePromise(var1, false)) {
         return var1;
      } else {
         AbstractChannelHandlerContext var2 = this.findContextOutbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            if (!this.channel().metadata().hasDisconnect()) {
               var2.invokeClose(var1);
            } else {
               var2.invokeDisconnect(var1);
            }
         } else {
            safeExecute(var3, new AbstractChannelHandlerContext$13(this, var2, var1), var1, null);
         }

         return var1;
      }
   }

   @Override
   public ChannelFuture write(Object var1) {
      return this.write(var1, this.newPromise());
   }

   @Override
   public ChannelPromise voidPromise() {
      return this.channel.voidPromise();
   }

   @Override
   public ChannelHandlerContext fireChannelReadComplete() {
      AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelReadComplete();
      } else {
         Object var3 = var1.invokeChannelReadCompleteTask;
         if (var3 == null) {
            var1.invokeChannelReadCompleteTask = (Runnable)(var3 = new AbstractChannelHandlerContext$9(this, var1));
         }

         var2.execute((Runnable)var3);
      }

      return this;
   }

   public void teardown() {
      EventExecutor var1 = this.executor();
      if (var1.inEventLoop()) {
         this.teardown0();
      } else {
         var1.execute(new AbstractChannelHandlerContext$1(this));
      }
   }

   @Override
   public ChannelFuture close(ChannelPromise var1) {
      if (!this.validatePromise(var1, false)) {
         return var1;
      } else {
         AbstractChannelHandlerContext var2 = this.findContextOutbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeClose(var1);
         } else {
            safeExecute(var3, new AbstractChannelHandlerContext$14(this, var2, var1), var1, null);
         }

         return var1;
      }
   }

   public void invokeBind(SocketAddress var1, ChannelPromise var2) {
      try {
         ((ChannelOutboundHandler)this.handler()).bind(this, var1, var2);
      } catch (Throwable var4) {
         notifyOutboundHandlerException(var4, var2);
      }
   }

   public void notifyHandlerException(Throwable var1) {
      if (inExceptionCaught(var1)) {
         if (DefaultChannelPipeline.logger.isWarnEnabled()) {
            DefaultChannelPipeline.logger.warn("An exception was thrown by a user handler while handling an exceptionCaught event", var1);
         }
      } else {
         this.invokeExceptionCaught(var1);
      }
   }

   public AbstractChannelHandlerContext(DefaultChannelPipeline var1, EventExecutorGroup var2, String var3, boolean var4, boolean var5) {
      if (var3 == null) {
         throw new NullPointerException("name");
      } else {
         this.channel = var1.channel;
         this.pipeline = var1;
         this.name = var3;
         if (var2 != null) {
            EventExecutor var6 = var1.childExecutors.get(var2);
            if (var6 == null) {
               var6 = var2.next();
               var1.childExecutors.put(var2, var6);
            }

            this.executor = var6;
         } else {
            this.executor = null;
         }

         this.inbound = var4;
         this.outbound = var5;
      }
   }

   public void invokeDeregister(ChannelPromise var1) {
      try {
         ((ChannelOutboundHandler)this.handler()).deregister(this, var1);
      } catch (Throwable var3) {
         notifyOutboundHandlerException(var3, var1);
      }
   }

   public void invokeChannelRead(Object var1) {
      try {
         ((ChannelInboundHandler)this.handler()).channelRead(this, var1);
      } catch (Throwable var3) {
         this.notifyHandlerException(var3);
      }
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, ChannelPromise var2) {
      return this.connect(var1, null, var2);
   }

   public void invokeExceptionCaught(Throwable var1) {
      try {
         this.handler().exceptionCaught(this, var1);
      } catch (Throwable var3) {
         if (DefaultChannelPipeline.logger.isWarnEnabled()) {
            DefaultChannelPipeline.logger
               .warn("An exception was thrown by a user handler's exceptionCaught() method while handling the following exception:", var1);
         }
      }
   }

   @Override
   public ChannelFuture disconnect() {
      return this.disconnect(this.newPromise());
   }

   public void invokeChannelInactive() {
      try {
         ((ChannelInboundHandler)this.handler()).channelInactive(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   @Override
   public boolean isRemoved() {
      return this.removed;
   }

   @Override
   public EventExecutor executor() {
      return (EventExecutor)(this.executor == null ? this.channel().eventLoop() : this.executor);
   }

   public void invokeFlush() {
      try {
         ((ChannelOutboundHandler)this.handler()).flush(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   @Override
   public ChannelFuture newSucceededFuture() {
      Object var1 = this.succeededFuture;
      if (var1 == null) {
         this.succeededFuture = (ChannelFuture)(var1 = new SucceededChannelFuture(this.channel(), this.executor()));
      }

      return (ChannelFuture)var1;
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      if (var1 == null) {
         throw new NullPointerException("remoteAddress");
      } else if (!this.validatePromise(var3, false)) {
         return var3;
      } else {
         AbstractChannelHandlerContext var4 = this.findContextOutbound();
         EventExecutor var5 = var4.executor();
         if (var5.inEventLoop()) {
            var4.invokeConnect(var1, var2, var3);
         } else {
            safeExecute(var5, new AbstractChannelHandlerContext$12(this, var4, var1, var2, var3), var3, null);
         }

         return var3;
      }
   }

   @Override
   public ChannelFuture bind(SocketAddress var1) {
      return this.bind(var1, this.newPromise());
   }

   @Override
   public ChannelHandlerContext fireChannelActive() {
      AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelActive();
      } else {
         var2.execute(new AbstractChannelHandlerContext$4(this, var1));
      }

      return this;
   }

   @Override
   public Channel channel() {
      return this.channel;
   }

   public void invokeChannelActive() {
      try {
         ((ChannelInboundHandler)this.handler()).channelActive(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   public void invokeRead() {
      try {
         ((ChannelOutboundHandler)this.handler()).read(this);
      } catch (Throwable var2) {
         this.notifyHandlerException(var2);
      }
   }

   @Override
   public ChannelPipeline pipeline() {
      return this.pipeline;
   }

   @Override
   public ChannelFuture newFailedFuture(Throwable var1) {
      return new FailedChannelFuture(this.channel(), this.executor(), var1);
   }
}
