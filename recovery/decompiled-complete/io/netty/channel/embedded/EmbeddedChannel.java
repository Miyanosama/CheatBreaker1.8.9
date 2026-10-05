package io.netty.channel.embedded;

import io.netty.channel.AbstractChannel;
import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;
import junit.swingui.TestRunner$13;
import net.minecraft.client.stream.ChatController$ChatChannelListener;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Corridor;

public class EmbeddedChannel extends AbstractChannel {
   public ChannelConfig config;
   public EmbeddedEventLoop loop = new EmbeddedEventLoop();
   public SocketAddress localAddress;
   public Throwable lastException;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public TestRunner$13 __junk5367099766824359447;
   public Queue<Object> inboundMessages;
   public StructureMineshaftPieces$Corridor __junk3895751449051421905;
   public SocketAddress remoteAddress;
   public Queue<Object> outboundMessages;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(EmbeddedChannel.class);
   public int state;
   public ChatController$ChatChannelListener __junk4198838682822834026;

   public Queue<Object> lastOutboundBuffer() {
      return this.outboundMessages();
   }

   @Override
   public void doBeginRead() {
   }

   @Override
   public boolean isActive() {
      return this.state == 1;
   }

   @Override
   public SocketAddress remoteAddress0() {
      return this.isActive() ? this.remoteAddress : null;
   }

   public void ensureOpen() {
      if (!this.isOpen()) {
         this.recordException(new ClosedChannelException());
         this.checkException();
      }
   }

   public Queue<Object> inboundMessages() {
      return this.inboundMessages;
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      while (true) {
         Object var2 = var1.current();
         if (var2 == null) {
            return;
         }

         ReferenceCountUtil.retain(var2);
         this.outboundMessages.add(var2);
         var1.remove();
      }
   }

   @Override
   public SocketAddress localAddress0() {
      return this.isActive() ? this.localAddress : null;
   }

   @Override
   public void doRegister() {
      this.state = 1;
   }

   @Override
   public void doBind(SocketAddress var1) {
   }

   public void runPendingTasks() {
      try {
         this.loop.runTasks();
      } catch (Exception var2) {
         this.recordException(var2);
      }
   }

   public boolean writeInbound(Object... var1) {
      this.ensureOpen();
      if (var1.length == 0) {
         return !this.inboundMessages.isEmpty();
      } else {
         ChannelPipeline var2 = this.pipeline();

         for (Object var6 : var1) {
            var2.fireChannelRead(var6);
         }

         var2.fireChannelReadComplete();
         this.runPendingTasks();
         this.checkException();
         return !this.inboundMessages.isEmpty();
      }
   }

   public EmbeddedChannel(ChannelHandler... var1) {
      super(null);
      this.config = new DefaultChannelConfig(this);
      this.localAddress = new EmbeddedSocketAddress();
      this.remoteAddress = new EmbeddedSocketAddress();
      this.inboundMessages = new ArrayDeque<>();
      this.outboundMessages = new ArrayDeque<>();
      if (var1 == null) {
         throw new NullPointerException("handlers");
      } else {
         int var2 = 0;
         ChannelPipeline var3 = this.pipeline();

         for (ChannelHandler var7 : var1) {
            if (var7 == null) {
               break;
            }

            var2++;
            var3.addLast(var7);
         }

         if (var2 == 0) {
            throw new IllegalArgumentException("handlers is empty.");
         } else {
            this.loop.register(this);
            var3.addLast(new EmbeddedChannel$LastInboundHandler(this, null));
         }
      }
   }

   public boolean writeOutbound(Object... var1) {
      this.ensureOpen();
      if (var1.length == 0) {
         return !this.outboundMessages.isEmpty();
      } else {
         RecyclableArrayList var2 = RecyclableArrayList.newInstance(var1.length);

         boolean var12;
         try {
            for (Object var6 : var1) {
               if (var6 == null) {
                  break;
               }

               var2.add(this.write(var6));
            }

            this.flush();
            int var10 = var2.size();

            for (int var11 = 0; var11 < var10; var11++) {
               ChannelFuture var13 = (ChannelFuture)var2.get(var11);
               if (!$assertionsDisabled && !var13.isDone()) {
                  throw new AssertionError();
               }

               if (var13.cause() != null) {
                  this.recordException(var13.cause());
               }
            }

            this.runPendingTasks();
            this.checkException();
            var12 = !this.outboundMessages.isEmpty();
         } finally {
            var2.recycle();
         }

         return var12;
      }
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof EmbeddedEventLoop;
   }

   public void recordException(Throwable var1) {
      if (this.lastException == null) {
         this.lastException = var1;
      } else {
         logger.warn("More than one exception was raised. Will report only the first one and log others.", var1);
      }
   }

   @Override
   public AbstractChannel$AbstractUnsafe newUnsafe() {
      return new EmbeddedChannel$DefaultUnsafe(this, null);
   }

   @Override
   public boolean isOpen() {
      return this.state < 2;
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   public Object readOutbound() {
      return this.outboundMessages.poll();
   }

   public Queue<Object> lastInboundBuffer() {
      return this.inboundMessages();
   }

   public void checkException() {
      Throwable var1 = this.lastException;
      if (var1 != null) {
         this.lastException = null;
         PlatformDependent.throwException(var1);
      }
   }

   @Override
   public ChannelConfig config() {
      return this.config;
   }

   @Override
   public void doClose() {
      this.state = 2;
   }

   public Object readInbound() {
      return this.inboundMessages.poll();
   }

   public boolean finish() {
      this.close();
      this.runPendingTasks();
      this.checkException();
      return !this.inboundMessages.isEmpty() || !this.outboundMessages.isEmpty();
   }

   @Override
   public void doDisconnect() {
      this.doClose();
   }

   public Queue<Object> outboundMessages() {
      return this.outboundMessages;
   }
}
