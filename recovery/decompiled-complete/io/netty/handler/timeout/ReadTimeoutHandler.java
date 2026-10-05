package io.netty.handler.timeout;

import com.cheatbreaker.client.module.type.TabListModule;
import com.cheatbreaker.client.ui.serverlist.PinnedServerEntry;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.command.CommandSetPlayerTimeout;
import recovered.unidentified.UnidentifiedClass1161;
import recovered.unidentified.UnidentifiedClass3620;

public class ReadTimeoutHandler extends ChannelInboundHandlerAdapter {
   public UnidentifiedClass1161 __junk3669549681728249157;
   public volatile int state;
   public UnidentifiedClass3620 __junk7029914987569702903;
   public volatile ScheduledFuture<?> timeout;
   public TabListModule __junk7088746067171790640;
   public ViewFrustum __junk5560118158884478946;
   public volatile long lastReadTime;
   public PinnedServerEntry __junk5471537154250646981;
   public boolean closed;
   public CommandSetPlayerTimeout __junk331526343850018047;
   public long timeoutNanos;
   public static long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(268968201L & 1216645239L);

   @Override
   public void channelActive(ChannelHandlerContext var1) {
      this.initialize(var1);
      super.channelActive(var1);
   }

   public ReadTimeoutHandler(int var1) {
      this(var1, TimeUnit.SECONDS);
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      if (var1.channel().isActive() && var1.channel().isRegistered()) {
         this.initialize(var1);
      }
   }

   public void destroy() {
      this.state = 2;
      if (this.timeout != null) {
         this.timeout.cancel(false);
         this.timeout = null;
      }
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) {
      if (var1.channel().isActive()) {
         this.initialize(var1);
      }

      super.channelRegistered(var1);
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      this.destroy();
      super.channelInactive(var1);
   }

   public void initialize(ChannelHandlerContext var1) {
      switch (this.state) {
         case 1:
         case 2:
            return;
         default:
            this.state = 1;
            this.lastReadTime = System.nanoTime();
            if (this.timeoutNanos > (19155088L & 4848617980848807944L)) {
               this.timeout = var1.executor().schedule(new ReadTimeoutHandler$ReadTimeoutTask(this, var1), this.timeoutNanos, TimeUnit.NANOSECONDS);
            }
      }
   }

   public void readTimedOut(ChannelHandlerContext var1) {
      if (!this.closed) {
         var1.fireExceptionCaught(ReadTimeoutException.INSTANCE);
         var1.close();
         this.closed = true;
      }
   }

   public ReadTimeoutHandler(long var1, TimeUnit var3) {
      if (var3 == null) {
         throw new NullPointerException("unit");
      } else {
         if (var1 <= (4232L & -3500349522921249760L)) {
            this.timeoutNanos = -6701862871223262078L & 8392981L;
         } else {
            this.timeoutNanos = Math.max(var3.toNanos(var1), MIN_TIMEOUT_NANOS);
         }
      }
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      this.lastReadTime = System.nanoTime();
      var1.fireChannelRead(var2);
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
      this.destroy();
   }
}
