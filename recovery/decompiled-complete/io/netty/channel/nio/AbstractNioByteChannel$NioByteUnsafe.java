package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.RecvByteBufAllocator$Handle;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesTask;
import java.io.IOException;
import java.nio.channels.SelectionKey;

public class AbstractNioByteChannel$NioByteUnsafe extends AbstractNioChannel$AbstractNioUnsafe {
   public RecvByteBufAllocator$Handle allocHandle;
   public ConcurrentHashMapV8$MapReduceEntriesTask __junk6429195538289919770;

   public AbstractNioByteChannel$NioByteUnsafe(AbstractNioByteChannel var1) {
      this.this$0 = var1;
      super(var1);
   }

   @Override
   public void read() {
      ChannelConfig var1 = this.this$0.config();
      if (!var1.isAutoRead() && !this.this$0.isReadPending()) {
         this.removeReadOp();
      } else {
         ChannelPipeline var2 = this.this$0.pipeline();
         ByteBufAllocator var3 = var1.getAllocator();
         int var4 = var1.getMaxMessagesPerRead();
         RecvByteBufAllocator$Handle var5 = this.allocHandle;
         if (var5 == null) {
            this.allocHandle = var5 = var1.getRecvByteBufAllocator().newHandle();
         }

         ByteBuf var6 = null;
         int var7 = 0;
         boolean var8 = false;

         try {
            int var9 = 0;
            boolean var10 = false;

            int var11;
            int var12;
            do {
               var6 = var5.allocate(var3);
               var11 = var6.writableBytes();
               var12 = this.this$0.doReadBytes(var6);
               if (var12 <= 0) {
                  var6.release();
                  var8 = var12 < 0;
                  break;
               }

               if (!var10) {
                  var10 = true;
                  this.this$0.setReadPending(false);
               }

               var2.fireChannelRead(var6);
               var6 = null;
               if (var9 >= Integer.MAX_VALUE - var12) {
                  var9 = Integer.MAX_VALUE;
                  break;
               }

               var9 += var12;
            } while (var1.isAutoRead() && var12 >= var11 && ++var7 < var4);

            var2.fireChannelReadComplete();
            var5.record(var9);
            if (var8) {
               this.closeOnRead(var2);
               var8 = false;
            }
         } catch (Throwable var16) {
            this.handleReadException(var2, var6, var16, var8);
         } finally {
            if (!var1.isAutoRead() && !this.this$0.isReadPending()) {
               this.removeReadOp();
            }
         }
      }
   }

   public void handleReadException(ChannelPipeline var1, ByteBuf var2, Throwable var3, boolean var4) {
      if (var2 != null) {
         if (var2.isReadable()) {
            this.this$0.setReadPending(false);
            var1.fireChannelRead(var2);
         } else {
            var2.release();
         }
      }

      var1.fireChannelReadComplete();
      var1.fireExceptionCaught(var3);
      if (var4 || var3 instanceof IOException) {
         this.closeOnRead(var1);
      }
   }

   public void closeOnRead(ChannelPipeline var1) {
      SelectionKey var2 = this.this$0.selectionKey();
      this.this$0.setInputShutdown();
      if (this.this$0.isOpen()) {
         if (Boolean.TRUE.equals(this.this$0.config().getOption(ChannelOption.ALLOW_HALF_CLOSURE))) {
            var2.interestOps(var2.interestOps() & ~this.this$0.readInterestOp);
            var1.fireUserEventTriggered(ChannelInputShutdownEvent.INSTANCE);
         } else {
            this.close(this.voidPromise());
         }
      }
   }
}
