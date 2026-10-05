package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.ScheduledFuture;
import net.minecraft.client.audio.SoundManager$SoundSystemStarterThread;

public abstract class AbstractNioChannel extends AbstractChannel {
   public volatile boolean inputShutdown;
   public SocketAddress requestedRemoteAddress;
   public volatile boolean readPending;
   public volatile SelectionKey selectionKey;
   public SoundManager$SoundSystemStarterThread __junk4973745625666192154;
   public int readInterestOp;
   public ChannelPromise connectPromise;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(AbstractNioChannel.class);
   public ScheduledFuture<?> connectTimeoutFuture;
   public SelectableChannel ch;

   public void setReadPending(boolean var1) {
      this.readPending = var1;
   }

   public boolean isReadPending() {
      return this.readPending;
   }

   @Override
   public void doBeginRead() {
      if (!this.inputShutdown) {
         SelectionKey var1 = this.selectionKey;
         if (var1.isValid()) {
            this.readPending = true;
            int var2 = var1.interestOps();
            if ((var2 & this.readInterestOp) == 0) {
               var1.interestOps(var2 | this.readInterestOp);
            }
         }
      }
   }

   public SelectableChannel javaChannel() {
      return this.ch;
   }

   public AbstractNioChannel$NioUnsafe unsafe() {
      return (AbstractNioChannel$NioUnsafe)super.unsafe();
   }

   public boolean isInputShutdown() {
      return this.inputShutdown;
   }

   public AbstractNioChannel(Channel var1, SelectableChannel var2, int var3) {
      super(var1);
      this.ch = var2;
      this.readInterestOp = var3;

      try {
         var2.configureBlocking(false);
      } catch (IOException var7) {
         try {
            var2.close();
         } catch (IOException var6) {
            if (logger.isWarnEnabled()) {
               logger.warn("Failed to close a partially initialized socket.", (Throwable)var6);
            }
         }

         throw new ChannelException("Failed to enter non-blocking mode.", var7);
      }
   }

   public SelectionKey selectionKey() {
      if (!$assertionsDisabled && this.selectionKey == null) {
         throw new AssertionError();
      } else {
         return this.selectionKey;
      }
   }

   @Override
   public boolean isOpen() {
      return this.ch.isOpen();
   }

   public NioEventLoop eventLoop() {
      return (NioEventLoop)super.eventLoop();
   }

   public void setInputShutdown() {
      this.inputShutdown = true;
   }

   public abstract void doFinishConnect();

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof NioEventLoop;
   }

   public abstract boolean doConnect(SocketAddress var1, SocketAddress var2);

   public ByteBuf newDirectBuffer(ReferenceCounted var1, ByteBuf var2) {
      int var3 = var2.readableBytes();
      if (var3 == 0) {
         ReferenceCountUtil.safeRelease(var1);
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBufAllocator var4 = this.alloc();
         if (var4.isDirectBufferPooled()) {
            ByteBuf var6 = var4.directBuffer(var3);
            var6.writeBytes(var2, var2.readerIndex(), var3);
            ReferenceCountUtil.safeRelease(var1);
            return var6;
         } else {
            ByteBuf var5 = ByteBufUtil.threadLocalDirectBuffer();
            if (var5 != null) {
               var5.writeBytes(var2, var2.readerIndex(), var3);
               ReferenceCountUtil.safeRelease(var1);
               return var5;
            } else {
               if (var1 != var2) {
                  var2.retain();
                  ReferenceCountUtil.safeRelease(var1);
               }

               return var2;
            }
         }
      }
   }

   @Override
   public void doRegister() {
      boolean var1 = false;

      while (true) {
         try {
            this.selectionKey = this.javaChannel().register(this.eventLoop().selector, 0, this);
            return;
         } catch (CancelledKeyException var3) {
            if (var1) {
               throw var3;
            }

            this.eventLoop().selectNow();
            var1 = true;
         }
      }
   }

   @Override
   public void doDeregister() {
      this.eventLoop().cancel(this.selectionKey());
   }

   public ByteBuf newDirectBuffer(ByteBuf var1) {
      int var2 = var1.readableBytes();
      if (var2 == 0) {
         ReferenceCountUtil.safeRelease(var1);
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBufAllocator var3 = this.alloc();
         if (var3.isDirectBufferPooled()) {
            ByteBuf var5 = var3.directBuffer(var2);
            var5.writeBytes(var1, var1.readerIndex(), var2);
            ReferenceCountUtil.safeRelease(var1);
            return var5;
         } else {
            ByteBuf var4 = ByteBufUtil.threadLocalDirectBuffer();
            if (var4 != null) {
               var4.writeBytes(var1, var1.readerIndex(), var2);
               ReferenceCountUtil.safeRelease(var1);
               return var4;
            } else {
               return var1;
            }
         }
      }
   }
}
