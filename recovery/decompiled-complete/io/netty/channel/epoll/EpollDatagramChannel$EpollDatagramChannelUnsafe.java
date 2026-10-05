package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator$Handle;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.internal.logging.CommonsLoggerFactory;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import net.minecraft.block.BlockTorch;
import recovered.unidentified.UnidentifiedClass1316;
import recovered.unidentified.UnidentifiedClass4298;
import recovered.unidentified.UnidentifiedClass5123;

public class EpollDatagramChannel$EpollDatagramChannelUnsafe extends AbstractEpollChannel$AbstractEpollUnsafe {
   public CommonsLoggerFactory __junk661631606353423599;
   public BlockTorch __junk4432289754043890208;
   public UnidentifiedClass1316 __junk2651685217326020356;
   public UnidentifiedClass4298 __junk3648029705274113625;
   public UnidentifiedClass5123 __junk7917851345631338069;
   public RecvByteBufAllocator$Handle allocHandle;

   @Override
   public void epollInReady() {
      EpollDatagramChannelConfig var1 = this.this$0.config();
      RecvByteBufAllocator$Handle var2 = this.allocHandle;
      if (var2 == null) {
         this.allocHandle = var2 = var1.getRecvByteBufAllocator().newHandle();
      }

      if (!$assertionsDisabled && !this.this$0.eventLoop().inEventLoop()) {
         throw new AssertionError();
      } else {
         ChannelPipeline var3 = this.this$0.pipeline();

         try {
            while (true) {
               ByteBuf var4 = null;

               try {
                  var4 = var2.allocate(var1.getAllocator());
                  int var5 = var4.writerIndex();
                  EpollDatagramChannel$DatagramSocketAddress var6;
                  if (var4.hasMemoryAddress()) {
                     var6 = Native.recvFromAddress(this.this$0.fd, var4.memoryAddress(), var5, var4.capacity());
                  } else {
                     ByteBuffer var7 = var4.internalNioBuffer(var5, var4.writableBytes());
                     var6 = Native.recvFrom(this.this$0.fd, var7, var7.position(), var7.limit());
                  }

                  if (var6 == null) {
                     return;
                  }

                  int var19 = var6.receivedAmount;
                  var4.writerIndex(var4.writerIndex() + var19);
                  var2.record(var19);
                  this.readPending = false;
                  var3.fireChannelRead(new DatagramPacket(var4, (InetSocketAddress)this.localAddress(), var6));
                  var4 = null;
               } catch (Throwable var16) {
                  var3.fireChannelReadComplete();
                  var3.fireExceptionCaught(var16);
               } finally {
                  if (var4 != null) {
                     var4.release();
                  }
               }
            }
         } finally {
            if (!this.this$0.config().isAutoRead() && !this.readPending) {
               this.this$0.clearEpollIn();
            }
         }
      }
   }

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      boolean var4 = false;

      try {
         try {
            InetSocketAddress var5 = (InetSocketAddress)var1;
            if (var2 != null) {
               InetSocketAddress var6 = (InetSocketAddress)var2;
               this.this$0.doBind(var6);
            }

            AbstractEpollChannel.checkResolvable(var5);
            EpollDatagramChannel.access$002(this.this$0, var5);
            EpollDatagramChannel.access$102(this.this$0, Native.localAddress(this.this$0.fd));
            var4 = true;
         } finally {
            if (!var4) {
               this.this$0.doClose();
            } else {
               var3.setSuccess();
               EpollDatagramChannel.access$202(this.this$0, true);
            }
         }
      } catch (Throwable var11) {
         var3.setFailure(var11);
      }
   }

   public EpollDatagramChannel$EpollDatagramChannelUnsafe(EpollDatagramChannel var1) {
      this.this$0 = var1;
      super(var1);
   }
}
