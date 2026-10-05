package io.netty.channel.nio;

import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ServerChannel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockSilverfish$EnumType$2;
import net.minecraft.block.BlockStoneBrick$EnumType;
import net.minecraft.command.CommandClone$StaticCloneData;

public class AbstractNioMessageChannel$NioMessageUnsafe extends AbstractNioChannel$AbstractNioUnsafe {
   public List<Object> readBuf;
   public BlockFlowerPot __junk6210716882621666640;
   public BlockStoneBrick$EnumType __junk3531769732386994306;
   public CommandClone$StaticCloneData __junk2266793200227371581;
   public BlockSilverfish$EnumType$2 __junk4348740354875238689;

   public AbstractNioMessageChannel$NioMessageUnsafe(AbstractNioMessageChannel var1) {
      this.this$0 = var1;
      super(var1);
      this.readBuf = new ArrayList<>();
   }

   @Override
   public void read() {
      if (!$assertionsDisabled && !this.this$0.eventLoop().inEventLoop()) {
         throw new AssertionError();
      } else {
         ChannelConfig var1 = this.this$0.config();
         if (!var1.isAutoRead() && !this.this$0.isReadPending()) {
            this.removeReadOp();
         } else {
            int var2 = var1.getMaxMessagesPerRead();
            ChannelPipeline var3 = this.this$0.pipeline();
            boolean var4 = false;
            Throwable var5 = null;

            try {
               try {
                  do {
                     int var6 = this.this$0.doReadMessages(this.readBuf);
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

               this.this$0.setReadPending(false);
               int var13 = this.readBuf.size();

               for (int var7 = 0; var7 < var13; var7++) {
                  var3.fireChannelRead(this.readBuf.get(var7));
               }

               this.readBuf.clear();
               var3.fireChannelReadComplete();
               if (var5 != null) {
                  if (var5 instanceof IOException) {
                     var4 = !(this.this$0 instanceof ServerChannel);
                  }

                  var3.fireExceptionCaught(var5);
               }

               if (var4 && this.this$0.isOpen()) {
                  this.close(this.voidPromise());
               }
            } finally {
               if (!var1.isAutoRead() && !this.this$0.isReadPending()) {
                  this.removeReadOp();
               }
            }
         }
      }
   }
}
