package io.netty.handler.timeout;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import recovered.unidentified.UnidentifiedClass3368;

public class IdleStateHandler extends ChannelDuplexHandler {
   public static long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(-93501755647490031L & 93501753731580481L);
   public volatile ScheduledFuture<?> writerIdleTimeout;
   public boolean firstReaderIdleEvent = true;
   public long allIdleTimeNanos;
   public boolean firstAllIdleEvent;
   public volatile long lastReadTime;
   public volatile ScheduledFuture<?> readerIdleTimeout;
   public volatile int state;
   public volatile ScheduledFuture<?> allIdleTimeout;
   public long writerIdleTimeNanos;
   public UnidentifiedClass3368 __junk2093613073255377897;
   public volatile long lastWriteTime;
   public boolean firstWriterIdleEvent = true;
   public long readerIdleTimeNanos;

   public void destroy() {
      this.state = 2;
      if (this.readerIdleTimeout != null) {
         this.readerIdleTimeout.cancel(false);
         this.readerIdleTimeout = null;
      }

      if (this.writerIdleTimeout != null) {
         this.writerIdleTimeout.cancel(false);
         this.writerIdleTimeout = null;
      }

      if (this.allIdleTimeout != null) {
         this.allIdleTimeout.cancel(false);
         this.allIdleTimeout = null;
      }
   }

   public void initialize(ChannelHandlerContext var1) {
      switch (this.state) {
         case 1:
         case 2:
            return;
         default:
            this.state = 1;
            EventExecutor var2 = var1.executor();
            this.lastReadTime = this.lastWriteTime = System.nanoTime();
            if (this.readerIdleTimeNanos > (5053513749525718032L & 679907520L)) {
               this.readerIdleTimeout = var2.schedule(new IdleStateHandler$ReaderIdleTimeoutTask(this, var1), this.readerIdleTimeNanos, TimeUnit.NANOSECONDS);
            }

            if (this.writerIdleTimeNanos > (3351071176919091200L & -3351071177190531055L)) {
               this.writerIdleTimeout = var2.schedule(new IdleStateHandler$WriterIdleTimeoutTask(this, var1), this.writerIdleTimeNanos, TimeUnit.NANOSECONDS);
            }

            if (this.allIdleTimeNanos > (2794690184218148866L & -2794690184621283832L)) {
               this.allIdleTimeout = var2.schedule(new IdleStateHandler$AllIdleTimeoutTask(this, var1), this.allIdleTimeNanos, TimeUnit.NANOSECONDS);
            }
      }
   }

   public long getWriterIdleTimeInMillis() {
      return TimeUnit.NANOSECONDS.toMillis(this.writerIdleTimeNanos);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      this.lastReadTime = System.nanoTime();
      this.firstReaderIdleEvent = this.firstAllIdleEvent = true;
      var1.fireChannelRead(var2);
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) {
      this.destroy();
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) {
      if (var1.channel().isActive()) {
         this.initialize(var1);
      }

      super.channelRegistered(var1);
   }

   public long getReaderIdleTimeInMillis() {
      return TimeUnit.NANOSECONDS.toMillis(this.readerIdleTimeNanos);
   }

   public IdleStateHandler(long var1, long var3, long var5, TimeUnit var7) {
      this.firstAllIdleEvent = true;
      if (var7 == null) {
         throw new NullPointerException("unit");
      } else {
         if (var1 <= (5025151239989014656L & 1090652681L)) {
            this.readerIdleTimeNanos = 151192576L & -8796377691297150860L;
         } else {
            this.readerIdleTimeNanos = Math.max(var7.toNanos(var1), MIN_TIMEOUT_NANOS);
         }

         if (var3 <= (201588773L & 1942487450L)) {
            this.writerIdleTimeNanos = 276998152L & 5669076358685282512L;
         } else {
            this.writerIdleTimeNanos = Math.max(var7.toNanos(var3), MIN_TIMEOUT_NANOS);
         }

         if (var5 <= (1620132896L & 134352969L)) {
            this.allIdleTimeNanos = 2324724038305317406L & 87527424L;
         } else {
            this.allIdleTimeNanos = Math.max(var7.toNanos(var5), MIN_TIMEOUT_NANOS);
         }
      }
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) {
      if (var1.channel().isActive() && var1.channel().isRegistered()) {
         this.initialize(var1);
      }
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) {
      this.initialize(var1);
      super.channelActive(var1);
   }

   public long getAllIdleTimeInMillis() {
      return TimeUnit.NANOSECONDS.toMillis(this.allIdleTimeNanos);
   }

   public IdleStateHandler(int var1, int var2, int var3) {
      this(var1, var2, var3, TimeUnit.SECONDS);
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      var3.addListener(new IdleStateHandler$1(this));
      var1.write(var2, var3);
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) {
      this.destroy();
      super.channelInactive(var1);
   }

   public void channelIdle(ChannelHandlerContext var1, IdleStateEvent var2) {
      var1.fireUserEventTriggered(var2);
   }
}
