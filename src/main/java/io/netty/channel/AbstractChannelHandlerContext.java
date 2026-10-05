package io.netty.channel;

import com.cheatbreaker.client.nethandler.obj.ServerRule;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.util.font.CBFont;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.sctp.SctpChannelOption;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.handler.codec.http.HttpContentEncoder;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.internal.OneTimeTask;
import io.netty.util.internal.RecyclableMpscLinkedQueueNode;
import io.netty.util.internal.StringUtil;
import java.net.SocketAddress;
import javax.vecmath.Point2i;
import javax.vecmath.TexCoord2f;
import junit.swingui.TestSelector;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.network.LanServerDetector;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.particle.EntitySuspendFX;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.data.FontMetadataSection;
import net.minecraft.client.util.JsonException;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.network.NettyEncryptingEncoder;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.village.Village;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.optifine.RandomEntityProperties;
import net.optifine.entity.model.ModelAdapterBook;
import net.optifine.model.QuadBounds;
import net.optifine.util.EntityUtils;
import net.optifine.util.IteratorCache;
import org.apache.log4j.chainsaw.ControlPanel$1;
import org.apache.log4j.config.PropertyPrinter;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$8;
import org.apache.log4j.spi.NOPLoggerRepository;
import org.java_websocket.enums.Opcode;
import org.java_websocket.framing.ControlFrame;
import org.newsclub.net.unix.AFUNIXServerSocket;
import org.newsclub.net.unix.AFUNIXSocketImpl;
import org.slf4j.LoggerFactory;
import com.cheatbreaker.client.event.type.ChatMessageEvent;
import com.cheatbreaker.client.module.type.TeammatesModule$EnumSwitch;
import org.json.UnicodeCodePointIterator;

public abstract class AbstractChannelHandlerContext extends DefaultAttributeMap implements ChannelHandlerContext {
   public volatile Runnable invokeFlushTask;
   public volatile Runnable invokeChannelReadCompleteTask;
   public boolean inbound;
   public volatile AbstractChannelHandlerContext prev;
   public ChannelFuture succeededFuture;
   public DefaultChannelPipeline pipeline;
   public AbstractChannel channel;
   public boolean removed;
   public volatile Runnable invokeReadTask;
   public EventExecutor executor;
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
      final AbstractChannelHandlerContext var1 = this.findContextOutbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeFlush();
      } else {
         Runnable var3 = var1.invokeFlushTask;
         if (var3 == null) {
            var1.invokeFlushTask = var3 = new Runnable() {

               @Override
               public void run() {
                  var1.invokeFlush();
               }
            };
         }

         safeExecute(var2, var3, this.channel.voidPromise(), null);
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
      final AbstractChannelHandlerContext var1 = this.findContextOutbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeRead();
      } else {
         Runnable var3 = var1.invokeReadTask;
         if (var3 == null) {
            var1.invokeReadTask = var3 = new Runnable() {

               @Override
               public void run() {
                  var1.invokeRead();
               }
            };
         }

         var2.execute(var3);
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
            var8 = AbstractChannelHandlerContext.WriteAndFlushTask.newInstance(var4, var1, var6, var3);
         } else {
            var8 = AbstractChannelHandlerContext.WriteTask.newInstance(var4, var1, var6, var3);
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
      } else if (var1 instanceof AbstractChannel.CloseFuture) {
         throw new IllegalArgumentException(StringUtil.simpleClassName(AbstractChannel.CloseFuture.class) + " not allowed in a pipeline");
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
   public ChannelFuture bind(final SocketAddress var1, final ChannelPromise var2) {
      if (var1 == null) {
         throw new NullPointerException("localAddress");
      } else if (!this.validatePromise(var2, false)) {
         return var2;
      } else {
         final AbstractChannelHandlerContext var3 = this.findContextOutbound();
         EventExecutor var4 = var3.executor();
         if (var4.inEventLoop()) {
            var3.invokeBind(var1, var2);
         } else {
            safeExecute(var4, new OneTimeTask() {

               @Override
               public void run() {
                  var3.invokeBind(var1, var2);
               }
            }, var2, null);
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
      final AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelUnregistered();
      } else {
         var2.execute(new OneTimeTask() {

            @Override
            public void run() {
               var1.invokeChannelUnregistered();
            }
         });
      }

      return this;
   }

   @Override
   public ChannelHandlerContext fireChannelRead(final Object var1) {
      if (var1 == null) {
         throw new NullPointerException("msg");
      } else {
         final AbstractChannelHandlerContext var2 = this.findContextInbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeChannelRead(var1);
         } else {
            var3.execute(new OneTimeTask() {

               @Override
               public void run() {
                  var2.invokeChannelRead(var1);
               }
            });
         }

         return this;
      }
   }

   @Override
   public ChannelFuture deregister(final ChannelPromise var1) {
      if (!this.validatePromise(var1, false)) {
         return var1;
      } else {
         final AbstractChannelHandlerContext var2 = this.findContextOutbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeDeregister(var1);
         } else {
            safeExecute(var3, new OneTimeTask() {

               @Override
               public void run() {
                  var2.invokeDeregister(var1);
               }
            }, var1, null);
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
      final AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelWritabilityChanged();
      } else {
         Runnable var3 = var1.invokeChannelWritableStateChangedTask;
         if (var3 == null) {
            var1.invokeChannelWritableStateChangedTask = var3 = new Runnable() {

               @Override
               public void run() {
                  var1.invokeChannelWritabilityChanged();
               }
            };
         }

         var2.execute(var3);
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
      final AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelInactive();
      } else {
         var2.execute(new OneTimeTask() {

            @Override
            public void run() {
               var1.invokeChannelInactive();
            }
         });
      }

      return this;
   }

   @Override
   public ChannelHandlerContext fireChannelRegistered() {
      final AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelRegistered();
      } else {
         var2.execute(new OneTimeTask() {

            @Override
            public void run() {
               var1.invokeChannelRegistered();
            }
         });
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
   public ChannelHandlerContext fireUserEventTriggered(final Object var1) {
      if (var1 == null) {
         throw new NullPointerException("event");
      } else {
         final AbstractChannelHandlerContext var2 = this.findContextInbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeUserEventTriggered(var1);
         } else {
            var3.execute(new OneTimeTask() {

               @Override
               public void run() {
                  var2.invokeUserEventTriggered(var1);
               }
            });
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
   public ChannelHandlerContext fireExceptionCaught(final Throwable var1) {
      if (var1 == null) {
         throw new NullPointerException("cause");
      } else {
         final AbstractChannelHandlerContext var2 = this.next;
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeExceptionCaught(var1);
         } else {
            try {
               var3.execute(new OneTimeTask() {

                  @Override
                  public void run() {
                     var2.invokeExceptionCaught(var1);
                  }
               });
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
   public ChannelFuture disconnect(final ChannelPromise var1) {
      if (!this.validatePromise(var1, false)) {
         return var1;
      } else {
         final AbstractChannelHandlerContext var2 = this.findContextOutbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            if (!this.channel().metadata().hasDisconnect()) {
               var2.invokeClose(var1);
            } else {
               var2.invokeDisconnect(var1);
            }
         } else {
            safeExecute(var3, new OneTimeTask() {

               @Override
               public void run() {
                  if (!AbstractChannelHandlerContext.this.channel().metadata().hasDisconnect()) {
                     var2.invokeClose(var1);
                  } else {
                     var2.invokeDisconnect(var1);
                  }
               }
            }, var1, null);
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
      final AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelReadComplete();
      } else {
         Runnable var3 = var1.invokeChannelReadCompleteTask;
         if (var3 == null) {
            var1.invokeChannelReadCompleteTask = var3 = new Runnable() {

               @Override
               public void run() {
                  var1.invokeChannelReadComplete();
               }
            };
         }

         var2.execute(var3);
      }

      return this;
   }

   public void teardown() {
      EventExecutor var1 = this.executor();
      if (var1.inEventLoop()) {
         this.teardown0();
      } else {
         var1.execute(new Runnable() {

            @Override
            public void run() {
               AbstractChannelHandlerContext.this.teardown0();
            }
         });
      }
   }

   @Override
   public ChannelFuture close(final ChannelPromise var1) {
      if (!this.validatePromise(var1, false)) {
         return var1;
      } else {
         final AbstractChannelHandlerContext var2 = this.findContextOutbound();
         EventExecutor var3 = var2.executor();
         if (var3.inEventLoop()) {
            var2.invokeClose(var1);
         } else {
            safeExecute(var3, new OneTimeTask() {

               @Override
               public void run() {
                  var2.invokeClose(var1);
               }
            }, var1, null);
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
      io.netty.channel.ChannelFuture var1 = this.succeededFuture;
      if (var1 == null) {
         this.succeededFuture = (ChannelFuture)(var1 = new SucceededChannelFuture(this.channel(), this.executor()));
      }

      return (ChannelFuture)var1;
   }

   @Override
   public ChannelFuture connect(final SocketAddress var1, final SocketAddress var2, final ChannelPromise var3) {
      if (var1 == null) {
         throw new NullPointerException("remoteAddress");
      } else if (!this.validatePromise(var3, false)) {
         return var3;
      } else {
         final AbstractChannelHandlerContext var4 = this.findContextOutbound();
         EventExecutor var5 = var4.executor();
         if (var5.inEventLoop()) {
            var4.invokeConnect(var1, var2, var3);
         } else {
            safeExecute(var5, new OneTimeTask() {

               @Override
               public void run() {
                  var4.invokeConnect(var1, var2, var3);
               }
            }, var3, null);
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
      final AbstractChannelHandlerContext var1 = this.findContextInbound();
      EventExecutor var2 = var1.executor();
      if (var2.inEventLoop()) {
         var1.invokeChannelActive();
      } else {
         var2.execute(new OneTimeTask() {

            @Override
            public void run() {
               var1.invokeChannelActive();
            }
         });
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

   public abstract static class AbstractWriteTask extends RecyclableMpscLinkedQueueNode<Runnable> implements Runnable {
      public int size;
      public AbstractChannelHandlerContext ctx;
      public ChannelPromise promise;
      public Object msg;

      @Override
      public void run() {
         try {
            if (this.size > 0) {
               ChannelOutboundBuffer var1 = this.ctx.channel.unsafe().outboundBuffer();
               if (var1 != null) {
                  var1.decrementPendingOutboundBytes(this.size);
               }
            }

            this.write(this.ctx, this.msg, this.promise);
         } finally {
            this.ctx = null;
            this.msg = null;
            this.promise = null;
         }
      }

      public Runnable value() {
         return this;
      }

      public AbstractWriteTask(Recycler.Handle var1) {
         super(var1);
      }

      public static void init(
         AbstractChannelHandlerContext.AbstractWriteTask var0, AbstractChannelHandlerContext var1, Object var2, int var3, ChannelPromise var4
      ) {
         var0.ctx = var1;
         var0.msg = var2;
         var0.promise = var4;
         var0.size = var3;
      }

      public void write(AbstractChannelHandlerContext var1, Object var2, ChannelPromise var3) {
         var1.invokeWrite(var2, var3);
      }
   }

   public static final class WriteAndFlushTask extends AbstractChannelHandlerContext.AbstractWriteTask {
      public static Recycler<AbstractChannelHandlerContext.WriteAndFlushTask> RECYCLER = new Recycler<AbstractChannelHandlerContext.WriteAndFlushTask>() {

         public AbstractChannelHandlerContext.WriteAndFlushTask newObject(Recycler.Handle var1) {
            return new AbstractChannelHandlerContext.WriteAndFlushTask(var1);
         }
      };

      public WriteAndFlushTask(Recycler.Handle var1) {
         super(var1);
      }

      @Override
      public void recycle(Recycler.Handle var1) {
         RECYCLER.recycle(this, var1);
      }

      @Override
      public void write(AbstractChannelHandlerContext var1, Object var2, ChannelPromise var3) {
         super.write(var1, var2, var3);
         var1.invokeFlush();
      }

      public static AbstractChannelHandlerContext.WriteAndFlushTask newInstance(AbstractChannelHandlerContext var0, Object var1, int var2, ChannelPromise var3) {
         AbstractChannelHandlerContext.WriteAndFlushTask var4 = RECYCLER.get();
         init(var4, var0, var1, var2, var3);
         return var4;
      }
   }

   public static final class WriteTask extends AbstractChannelHandlerContext.AbstractWriteTask implements SingleThreadEventLoop.NonWakeupRunnable {
      public static Recycler<AbstractChannelHandlerContext.WriteTask> RECYCLER = new Recycler<AbstractChannelHandlerContext.WriteTask>() {

         public AbstractChannelHandlerContext.WriteTask newObject(Recycler.Handle var1) {
            return new AbstractChannelHandlerContext.WriteTask(var1);
         }
      };

      public WriteTask(Recycler.Handle var1) {
         super(var1);
      }

      @Override
      public void recycle(Recycler.Handle var1) {
         RECYCLER.recycle(this, var1);
      }

      public static AbstractChannelHandlerContext.WriteTask newInstance(AbstractChannelHandlerContext var0, Object var1, int var2, ChannelPromise var3) {
         AbstractChannelHandlerContext.WriteTask var4 = RECYCLER.get();
         init(var4, var0, var1, var2, var3);
         return var4;
      }
   }
}
