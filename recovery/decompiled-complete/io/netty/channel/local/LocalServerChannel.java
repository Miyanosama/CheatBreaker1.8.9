package io.netty.channel.local;

import io.netty.channel.AbstractServerChannel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.EventLoop;
import io.netty.channel.SingleThreadEventLoop;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.Queue;
import net.minecraft.block.BlockHalfStoneSlab;

public class LocalServerChannel extends AbstractServerChannel {
   public volatile int state;
   public ChannelConfig config = new DefaultChannelConfig(this);
   public Runnable shutdownHook;
   public volatile boolean acceptInProgress;
   public BlockHalfStoneSlab __junk1392882782217760158;
   public volatile LocalAddress localAddress;
   public Queue<Object> inboundBuffer = new ArrayDeque<>();

   @Override
   public void doRegister() {
      ((SingleThreadEventExecutor)this.eventLoop()).addShutdownHook(this.shutdownHook);
   }

   public LocalAddress localAddress() {
      return (LocalAddress)super.localAddress();
   }

   public void serve0(LocalChannel var1) {
      this.inboundBuffer.add(var1);
      if (this.acceptInProgress) {
         this.acceptInProgress = false;
         ChannelPipeline var2 = this.pipeline();

         while (true) {
            Object var3 = this.inboundBuffer.poll();
            if (var3 == null) {
               var2.fireChannelReadComplete();
               break;
            }

            var2.fireChannelRead(var3);
         }
      }
   }

   @Override
   public boolean isActive() {
      return this.state == 1;
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof SingleThreadEventLoop;
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.localAddress = LocalChannelRegistry.register(this, this.localAddress, var1);
      this.state = 1;
   }

   public LocalServerChannel() {
      this.shutdownHook = new LocalServerChannel$1(this);
   }

   @Override
   public SocketAddress localAddress0() {
      return this.localAddress;
   }

   @Override
   public boolean isOpen() {
      return this.state < 2;
   }

   @Override
   public void doDeregister() {
      ((SingleThreadEventExecutor)this.eventLoop()).removeShutdownHook(this.shutdownHook);
   }

   @Override
   public void doClose() {
      if (this.state <= 1) {
         if (this.localAddress != null) {
            LocalChannelRegistry.unregister(this.localAddress);
            this.localAddress = null;
         }

         this.state = 2;
      }
   }

   @Override
   public ChannelConfig config() {
      return this.config;
   }

   @Override
   public void doBeginRead() {
      if (!this.acceptInProgress) {
         Queue var1 = this.inboundBuffer;
         if (var1.isEmpty()) {
            this.acceptInProgress = true;
         } else {
            ChannelPipeline var2 = this.pipeline();

            while (true) {
               Object var3 = var1.poll();
               if (var3 == null) {
                  var2.fireChannelReadComplete();
                  return;
               }

               var2.fireChannelRead(var3);
            }
         }
      }
   }

   public LocalAddress remoteAddress() {
      return (LocalAddress)super.remoteAddress();
   }

   public LocalChannel serve(LocalChannel var1) {
      LocalChannel var2 = new LocalChannel(this, var1);
      if (this.eventLoop().inEventLoop()) {
         this.serve0(var2);
      } else {
         this.eventLoop().execute(new LocalServerChannel$2(this, var2));
      }

      return var2;
   }
}
