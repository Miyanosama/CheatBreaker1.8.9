package io.netty.handler.timeout;

import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.compression.JdkZlibDecoder;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.command.CommandKill;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.item.ItemArmorStand;
import net.minecraft.world.biome.BiomeGenJungle;
import com.cheatbreaker.client.ui.overlay.element.RadioVolumeSlider$EnumSwitch;
import net.optifine.CustomColors$3;

public class IdleStateHandler extends ChannelDuplexHandler {
   public static long MIN_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(1L);
   public volatile ScheduledFuture<?> writerIdleTimeout;
   public boolean firstReaderIdleEvent = true;
   public long allIdleTimeNanos;
   public boolean firstAllIdleEvent;
   public volatile long lastReadTime;
   public volatile ScheduledFuture<?> readerIdleTimeout;
   public volatile int state;
   public volatile ScheduledFuture<?> allIdleTimeout;
   public long writerIdleTimeNanos;
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
            if (this.readerIdleTimeNanos > 0L) {
               this.readerIdleTimeout = var2.schedule(new IdleStateHandler.ReaderIdleTimeoutTask(var1), this.readerIdleTimeNanos, TimeUnit.NANOSECONDS);
            }

            if (this.writerIdleTimeNanos > 0L) {
               this.writerIdleTimeout = var2.schedule(new IdleStateHandler.WriterIdleTimeoutTask(var1), this.writerIdleTimeNanos, TimeUnit.NANOSECONDS);
            }

            if (this.allIdleTimeNanos > 0L) {
               this.allIdleTimeout = var2.schedule(new IdleStateHandler.AllIdleTimeoutTask(var1), this.allIdleTimeNanos, TimeUnit.NANOSECONDS);
            }
      }
   }

   public long getWriterIdleTimeInMillis() {
      return TimeUnit.NANOSECONDS.toMillis(this.writerIdleTimeNanos);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      this.lastReadTime = System.nanoTime();
      this.firstReaderIdleEvent = this.firstAllIdleEvent = true;
      var1.fireChannelRead(var2);
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception {
      this.destroy();
   }

   @Override
   public void channelRegistered(ChannelHandlerContext var1) throws java.lang.Exception {
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
         if (var1 <= 0L) {
            this.readerIdleTimeNanos = 0L;
         } else {
            this.readerIdleTimeNanos = Math.max(var7.toNanos(var1), MIN_TIMEOUT_NANOS);
         }

         if (var3 <= 0L) {
            this.writerIdleTimeNanos = 0L;
         } else {
            this.writerIdleTimeNanos = Math.max(var7.toNanos(var3), MIN_TIMEOUT_NANOS);
         }

         if (var5 <= 0L) {
            this.allIdleTimeNanos = 0L;
         } else {
            this.allIdleTimeNanos = Math.max(var7.toNanos(var5), MIN_TIMEOUT_NANOS);
         }
      }
   }

   @Override
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      if (var1.channel().isActive() && var1.channel().isRegistered()) {
         this.initialize(var1);
      }
   }

   @Override
   public void channelActive(ChannelHandlerContext var1) throws java.lang.Exception {
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
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      var3.addListener(new ChannelFutureListener() {

         public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
            IdleStateHandler.this.lastWriteTime = System.nanoTime();
            IdleStateHandler.this.firstWriterIdleEvent = IdleStateHandler.this.firstAllIdleEvent = true;
         }
      });
      var1.write(var2, var3);
   }

   @Override
   public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      this.destroy();
      super.channelInactive(var1);
   }

   public void channelIdle(ChannelHandlerContext var1, IdleStateEvent var2) throws java.lang.Exception {
      var1.fireUserEventTriggered(var2);
   }

   public final class AllIdleTimeoutTask implements Runnable {
      public ChannelHandlerContext ctx;

      @Override
      public void run() {
         if (this.ctx.channel().isOpen()) {
            long var1 = System.nanoTime();
            long var3 = Math.max(IdleStateHandler.this.lastReadTime, IdleStateHandler.this.lastWriteTime);
            long var5 = IdleStateHandler.this.allIdleTimeNanos - (var1 - var3);
            if (var5 <= 0L) {
               IdleStateHandler.this.allIdleTimeout = this.ctx.executor().schedule(this, IdleStateHandler.this.allIdleTimeNanos, TimeUnit.NANOSECONDS);

               try {
                  IdleStateEvent var7;
                  if (IdleStateHandler.this.firstAllIdleEvent) {
                     IdleStateHandler.this.firstAllIdleEvent = false;
                     var7 = IdleStateEvent.FIRST_ALL_IDLE_STATE_EVENT;
                  } else {
                     var7 = IdleStateEvent.ALL_IDLE_STATE_EVENT;
                  }

                  IdleStateHandler.this.channelIdle(this.ctx, var7);
               } catch (Throwable var8) {
                  this.ctx.fireExceptionCaught(var8);
               }
            } else {
               IdleStateHandler.this.allIdleTimeout = this.ctx.executor().schedule(this, var5, TimeUnit.NANOSECONDS);
            }
         }
      }

      public AllIdleTimeoutTask(ChannelHandlerContext var2) {
         this.ctx = var2;
      }
   }

   public final class ReaderIdleTimeoutTask implements Runnable {
      public ChannelHandlerContext ctx;

      public ReaderIdleTimeoutTask(ChannelHandlerContext var2) {
         this.ctx = var2;
      }

      @Override
      public void run() {
         if (this.ctx.channel().isOpen()) {
            long var1 = System.nanoTime();
            long var3 = IdleStateHandler.this.lastReadTime;
            long var5 = IdleStateHandler.this.readerIdleTimeNanos - (var1 - var3);
            if (var5 <= 0L) {
               IdleStateHandler.this.readerIdleTimeout = this.ctx.executor().schedule(this, IdleStateHandler.this.readerIdleTimeNanos, TimeUnit.NANOSECONDS);

               try {
                  IdleStateEvent var7;
                  if (IdleStateHandler.this.firstReaderIdleEvent) {
                     IdleStateHandler.this.firstReaderIdleEvent = false;
                     var7 = IdleStateEvent.FIRST_READER_IDLE_STATE_EVENT;
                  } else {
                     var7 = IdleStateEvent.READER_IDLE_STATE_EVENT;
                  }

                  IdleStateHandler.this.channelIdle(this.ctx, var7);
               } catch (Throwable var8) {
                  this.ctx.fireExceptionCaught(var8);
               }
            } else {
               IdleStateHandler.this.readerIdleTimeout = this.ctx.executor().schedule(this, var5, TimeUnit.NANOSECONDS);
            }
         }
      }
   }

   public final class WriterIdleTimeoutTask implements Runnable {
      public ChannelHandlerContext ctx;

      @Override
      public void run() {
         if (this.ctx.channel().isOpen()) {
            long var1 = System.nanoTime();
            long var3 = IdleStateHandler.this.lastWriteTime;
            long var5 = IdleStateHandler.this.writerIdleTimeNanos - (var1 - var3);
            if (var5 <= 0L) {
               IdleStateHandler.this.writerIdleTimeout = this.ctx.executor().schedule(this, IdleStateHandler.this.writerIdleTimeNanos, TimeUnit.NANOSECONDS);

               try {
                  IdleStateEvent var7;
                  if (IdleStateHandler.this.firstWriterIdleEvent) {
                     IdleStateHandler.this.firstWriterIdleEvent = false;
                     var7 = IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT;
                  } else {
                     var7 = IdleStateEvent.WRITER_IDLE_STATE_EVENT;
                  }

                  IdleStateHandler.this.channelIdle(this.ctx, var7);
               } catch (Throwable var8) {
                  this.ctx.fireExceptionCaught(var8);
               }
            } else {
               IdleStateHandler.this.writerIdleTimeout = this.ctx.executor().schedule(this, var5, TimeUnit.NANOSECONDS);
            }
         }
      }

      public WriterIdleTimeoutTask(ChannelHandlerContext var2) {
         this.ctx = var2;
      }
   }
}
