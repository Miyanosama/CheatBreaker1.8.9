package io.netty.channel.nio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ServerChannel;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.command.CommandClone;

public abstract class AbstractNioMessageChannel extends AbstractNioChannel {

   public AbstractNioMessageChannel(Channel var1, SelectableChannel var2, int var3) {
      super(var1, var2, var3);
   }

   public boolean continueOnWriteError() {
      return false;
   }

   public AbstractNioChannel.AbstractNioUnsafe newUnsafe() {
      return new AbstractNioMessageChannel.NioMessageUnsafe();
   }

   public abstract int doReadMessages(List<Object> var1) throws java.lang.Exception ;

   @Override
   public void doWrite(ChannelOutboundBuffer var1) throws java.lang.Exception {
      SelectionKey var2 = this.selectionKey();
      int var3 = var2.interestOps();

      while (true) {
         Object var4 = var1.current();
         if (var4 == null) {
            if ((var3 & 4) != 0) {
               var2.interestOps(var3 & -5);
            }
            break;
         }

         try {
            boolean var5 = false;

            for (int var6 = this.config().getWriteSpinCount() - 1; var6 >= 0; var6--) {
               if (this.doWriteMessage(var4, var1)) {
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               if ((var3 & 4) == 0) {
                  var2.interestOps(var3 | 4);
               }
               break;
            }

            var1.remove();
         } catch (IOException var7) {
            if (!this.continueOnWriteError()) {
               throw var7;
            }

            var1.remove(var7);
         }
      }
   }

   public abstract boolean doWriteMessage(Object var1, ChannelOutboundBuffer var2) throws java.lang.Exception ;

   public final class NioMessageUnsafe extends AbstractNioChannel.AbstractNioUnsafe {
      public List<Object> readBuf = new ArrayList<>();
      // $VF: synthetic field
      public final boolean $assertionsDisabled = !AbstractNioMessageChannel.class.desiredAssertionStatus();

      public NioMessageUnsafe() {
      }

      @Override
      public void read() {
         if (!$assertionsDisabled && !AbstractNioMessageChannel.this.eventLoop().inEventLoop()) {
            throw new AssertionError();
         } else {
            ChannelConfig var1 = AbstractNioMessageChannel.this.config();
            if (!var1.isAutoRead() && !AbstractNioMessageChannel.this.isReadPending()) {
               this.removeReadOp();
            } else {
               int var2 = var1.getMaxMessagesPerRead();
               ChannelPipeline var3 = AbstractNioMessageChannel.this.pipeline();
               boolean var4 = false;
               Throwable var5 = null;

               try {
                  try {
                     do {
                        int var6 = AbstractNioMessageChannel.this.doReadMessages(this.readBuf);
                        if (var6 == 0) {
                           break;
                        }

                        if (var6 < 0) {
                           var4 = true;
                           break;
                        }
                     } while (var1.isAutoRead() && this.readBuf.size() < var2);
                  } catch (Throwable var11) {
                     var5 = var11;
                  }

                  AbstractNioMessageChannel.this.setReadPending(false);
                  int var13 = this.readBuf.size();

                  for (int var7 = 0; var7 < var13; var7++) {
                     var3.fireChannelRead(this.readBuf.get(var7));
                  }

                  this.readBuf.clear();
                  var3.fireChannelReadComplete();
                  if (var5 != null) {
                     if (var5 instanceof IOException) {
                        var4 = !(AbstractNioMessageChannel.this instanceof ServerChannel);
                     }

                     var3.fireExceptionCaught(var5);
                  }

                  if (var4 && AbstractNioMessageChannel.this.isOpen()) {
                     this.close(this.voidPromise());
                  }
               } finally {
                  if (!var1.isAutoRead() && !AbstractNioMessageChannel.this.isReadPending()) {
                     this.removeReadOp();
                  }
               }
            }
         }
      }
   }
}
