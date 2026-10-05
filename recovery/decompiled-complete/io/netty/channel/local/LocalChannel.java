package io.netty.channel.local;

import com.cheatbreaker.client.websocket.client.WSPacketClientJoinServerResponse;
import io.netty.channel.AbstractChannel;
import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import io.netty.util.internal.InternalThreadLocalMap;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import java.util.ArrayDeque;
import java.util.Queue;
import net.minecraft.world.WorldProviderHell;

public class LocalChannel extends AbstractChannel {
   public Queue<Object> inboundBuffer;
   public volatile boolean readInProgress;
   public ChannelConfig config = new DefaultChannelConfig(this);
   public volatile boolean registerInProgress;
   public static int MAX_READER_STACK_DEPTH;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);
   public volatile ChannelPromise connectPromise;
   public volatile int state;
   public Runnable readTask;
   public volatile LocalAddress remoteAddress;
   public Runnable shutdownHook;
   public volatile LocalChannel peer;
   public WSPacketClientJoinServerResponse __junk3175161623601031728;
   public WorldProviderHell __junk3651052428536404639;
   public volatile LocalAddress localAddress;

   public static void finishPeerRead(LocalChannel var0, ChannelPipeline var1) {
      if (var0.readInProgress) {
         var0.readInProgress = false;

         while (true) {
            Object var2 = var0.inboundBuffer.poll();
            if (var2 == null) {
               var1.fireChannelReadComplete();
               break;
            }

            var1.fireChannelRead(var2);
         }
      }
   }

   @Override
   public void doClose() {
      if (this.state <= 2) {
         if (this.localAddress != null) {
            if (this.parent() == null) {
               LocalChannelRegistry.unregister(this.localAddress);
            }

            this.localAddress = null;
         }

         this.state = 3;
      }

      LocalChannel var1 = this.peer;
      if (var1 != null && var1.isActive()) {
         EventLoop var2 = var1.eventLoop();
         if (var2.inEventLoop() && !this.registerInProgress) {
            var1.unsafe().close(this.unsafe().voidPromise());
         } else {
            var1.eventLoop().execute(new LocalChannel$4(this, var1));
         }

         this.peer = null;
      }
   }

   @Override
   public SocketAddress remoteAddress0() {
      return this.remoteAddress;
   }

   public LocalAddress remoteAddress() {
      return (LocalAddress)super.remoteAddress();
   }

   @Override
   public void doDisconnect() {
      this.doClose();
   }

   public LocalAddress localAddress() {
      return (LocalAddress)super.localAddress();
   }

   @Override
   public void doDeregister() {
      ((SingleThreadEventExecutor)this.eventLoop()).removeShutdownHook(this.shutdownHook);
   }

   public LocalChannel(LocalServerChannel var1, LocalChannel var2) {
      super(var1);
      this.inboundBuffer = new ArrayDeque<>();
      this.readTask = new LocalChannel$1(this);
      this.shutdownHook = new LocalChannel$2(this);
      this.peer = var2;
      this.localAddress = var1.localAddress();
      this.remoteAddress = var2.localAddress();
   }

   public LocalChannel() {
      super(null);
      this.inboundBuffer = new ArrayDeque<>();
      this.readTask = new LocalChannel$1(this);
      this.shutdownHook = new LocalChannel$2(this);
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public void doRegister() {
      if (this.peer != null && this.parent() != null) {
         LocalChannel var1 = this.peer;
         this.registerInProgress = true;
         this.state = 2;
         var1.remoteAddress = this.parent().localAddress();
         var1.state = 2;
         var1.eventLoop().execute(new LocalChannel$3(this, var1));
      }

      ((SingleThreadEventExecutor)this.eventLoop()).addShutdownHook(this.shutdownHook);
   }

   @Override
   public AbstractChannel$AbstractUnsafe newUnsafe() {
      return new LocalChannel$LocalUnsafe(this, null);
   }

   public LocalServerChannel parent() {
      return (LocalServerChannel)super.parent();
   }

   @Override
   public void doBeginRead() {
      if (!this.readInProgress) {
         ChannelPipeline var1 = this.pipeline();
         Queue var2 = this.inboundBuffer;
         if (var2.isEmpty()) {
            this.readInProgress = true;
         } else {
            InternalThreadLocalMap var3 = InternalThreadLocalMap.get();
            Integer var4 = var3.localChannelReaderStackDepth();
            if (var4 < 8) {
               var3.setLocalChannelReaderStackDepth(var4 + 1);

               try {
                  while (true) {
                     Object var5 = var2.poll();
                     if (var5 == null) {
                        var1.fireChannelReadComplete();
                        break;
                     }

                     var1.fireChannelRead(var5);
                  }
               } finally {
                  var3.setLocalChannelReaderStackDepth(var4);
               }
            } else {
               this.eventLoop().execute(this.readTask);
            }
         }
      }
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof SingleThreadEventLoop;
   }

   @Override
   public SocketAddress localAddress0() {
      return this.localAddress;
   }

   @Override
   public ChannelConfig config() {
      return this.config;
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.localAddress = LocalChannelRegistry.register(this, this.localAddress, var1);
      this.state = 1;
   }

   @Override
   public boolean isOpen() {
      return this.state < 3;
   }

   @Override
   public void doWrite(ChannelOutboundBuffer var1) {
      if (this.state < 2) {
         throw new NotYetConnectedException();
      } else if (this.state > 2) {
         throw new ClosedChannelException();
      } else {
         LocalChannel var2 = this.peer;
         ChannelPipeline var3 = var2.pipeline();
         EventLoop var4 = var2.eventLoop();
         if (var4 != this.eventLoop()) {
            Object[] var7 = new Object[var1.size()];

            for (int var6 = 0; var6 < var7.length; var6++) {
               var7[var6] = ReferenceCountUtil.retain(var1.current());
               var1.remove();
            }

            var4.execute(new LocalChannel$5(this, var2, var7, var3));
         } else {
            while (true) {
               Object var5 = var1.current();
               if (var5 == null) {
                  finishPeerRead(var2, var3);
                  break;
               }

               var2.inboundBuffer.add(var5);
               ReferenceCountUtil.retain(var5);
               var1.remove();
            }
         }
      }
   }

   @Override
   public boolean isActive() {
      return this.state == 2;
   }
}
