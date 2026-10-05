package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator$Handle;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import junit.textui.TestRunner;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;

public class EpollSocketChannel$EpollSocketUnsafe extends AbstractEpollChannel$AbstractEpollUnsafe {
   public EpollSocketChannel$EpollSocketUnsafe$1 __junk2073451576375409741;
   public TestRunner __junk6342179089864741157;
   public RecvByteBufAllocator$Handle allocHandle;
   public CategoryPath __junk9125275453936685661;

   public void fulfillConnectPromise(ChannelPromise var1, boolean var2) {
      if (var1 != null) {
         this.this$0.active = true;
         boolean var3 = var1.trySuccess();
         if (!var2 && this.this$0.isActive()) {
            this.this$0.pipeline().fireChannelActive();
         }

         if (!var3) {
            this.close(this.voidPromise());
         }
      }
   }

   public void finishConnect() {
      if (!$assertionsDisabled && !this.this$0.eventLoop().inEventLoop()) {
         throw new AssertionError();
      } else {
         boolean var1 = false;

         try {
            boolean var9 = this.this$0.isActive();
            if (this.doFinishConnect()) {
               this.fulfillConnectPromise(EpollSocketChannel.access$100(this.this$0), var9);
               return;
            }

            var1 = true;
         } catch (Throwable var7) {
            Object var2 = var7;
            if (var7 instanceof ConnectException) {
               ConnectException var3 = new ConnectException(var7.getMessage() + ": " + EpollSocketChannel.access$200(this.this$0));
               var3.setStackTrace(var7.getStackTrace());
               var2 = var3;
            }

            this.fulfillConnectPromise(EpollSocketChannel.access$100(this.this$0), (Throwable)var2);
            return;
         } finally {
            if (!var1) {
               if (EpollSocketChannel.access$300(this.this$0) != null) {
                  EpollSocketChannel.access$300(this.this$0).cancel(false);
               }

               EpollSocketChannel.access$102(this.this$0, null);
            }
         }
      }
   }

   public boolean doConnect(InetSocketAddress var1, InetSocketAddress var2) {
      if (var2 != null) {
         AbstractEpollChannel.checkResolvable(var2);
         Native.bind(this.this$0.fd, var2.getAddress(), var2.getPort());
      }

      boolean var3 = false;

      boolean var5;
      try {
         AbstractEpollChannel.checkResolvable(var1);
         boolean var4 = Native.connect(this.this$0.fd, var1.getAddress(), var1.getPort());
         EpollSocketChannel.access$402(this.this$0, var1);
         EpollSocketChannel.access$502(this.this$0, Native.localAddress(this.this$0.fd));
         if (!var4) {
            this.this$0.setEpollOut();
         }

         var3 = true;
         var5 = var4;
      } finally {
         if (!var3) {
            this.this$0.doClose();
         }
      }

      return var5;
   }

   public EpollSocketChannel$EpollSocketUnsafe(EpollSocketChannel var1) {
      this.this$0 = var1;
      super(var1);
   }

   public boolean doFinishConnect() {
      if (Native.finishConnect(this.this$0.fd)) {
         this.this$0.clearEpollOut();
         return true;
      } else {
         this.this$0.setEpollOut();
         return false;
      }
   }

   public void fulfillConnectPromise(ChannelPromise var1, Throwable var2) {
      if (var1 != null) {
         var1.tryFailure(var2);
         this.closeIfClosed();
      }
   }

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      if (var3.setUncancellable() && this.ensureOpen(var3)) {
         try {
            if (EpollSocketChannel.access$100(this.this$0) != null) {
               throw new IllegalStateException("connection attempt already made");
            }

            boolean var7 = this.this$0.isActive();
            if (this.doConnect((InetSocketAddress)var1, (InetSocketAddress)var2)) {
               this.fulfillConnectPromise(var3, var7);
            } else {
               EpollSocketChannel.access$102(this.this$0, var3);
               EpollSocketChannel.access$202(this.this$0, var1);
               int var8 = this.this$0.config().getConnectTimeoutMillis();
               if (var8 > 0) {
                  EpollSocketChannel.access$302(
                     this.this$0, this.this$0.eventLoop().schedule(new EpollSocketChannel$EpollSocketUnsafe$1(this, var1), var8, TimeUnit.MILLISECONDS)
                  );
               }

               var3.addListener(new EpollSocketChannel$EpollSocketUnsafe$2(this));
            }
         } catch (Throwable var6) {
            Object var4 = var6;
            if (var6 instanceof ConnectException) {
               ConnectException var5 = new ConnectException(var6.getMessage() + ": " + var1);
               var5.setStackTrace(var6.getStackTrace());
               var4 = var5;
            }

            this.closeIfClosed();
            var3.tryFailure((Throwable)var4);
         }
      }
   }

   @Override
   public void epollRdHupReady() {
      if (this.this$0.isActive()) {
         this.epollInReady();
      } else {
         this.closeOnRead(this.this$0.pipeline());
      }
   }

   @Override
   public void epollInReady() {
      EpollSocketChannelConfig var1 = this.this$0.config();
      ChannelPipeline var2 = this.this$0.pipeline();
      ByteBufAllocator var3 = var1.getAllocator();
      RecvByteBufAllocator$Handle var4 = this.allocHandle;
      if (var4 == null) {
         this.allocHandle = var4 = var1.getRecvByteBufAllocator().newHandle();
      }

      ByteBuf var5 = null;
      boolean var6 = false;

      try {
         int var7 = 0;

         int var9;
         int var15;
         do {
            var5 = var4.allocate(var3);
            var15 = var5.writableBytes();
            var9 = this.doReadBytes(var5);
            if (var9 <= 0) {
               var5.release();
               var6 = var9 < 0;
               break;
            }

            this.readPending = false;
            var2.fireChannelRead(var5);
            var5 = null;
            if (var7 >= Integer.MAX_VALUE - var9) {
               var4.record(var7);
               var7 = var9;
            } else {
               var7 += var9;
            }
         } while (var9 >= var15);

         var2.fireChannelReadComplete();
         var4.record(var7);
         if (var6) {
            this.closeOnRead(var2);
            var6 = false;
         }
      } catch (Throwable var13) {
         boolean var8 = this.handleReadException(var2, var5, var13, var6);
         if (!var8) {
            this.this$0.eventLoop().execute(new EpollSocketChannel$EpollSocketUnsafe$3(this));
         }
      } finally {
         if (!var1.isAutoRead() && !this.readPending) {
            this.clearEpollIn0();
         }
      }
   }

   @Override
   public void epollOutReady() {
      if (EpollSocketChannel.access$100(this.this$0) != null) {
         this.finishConnect();
      } else {
         super.epollOutReady();
      }
   }

   public boolean handleReadException(ChannelPipeline var1, ByteBuf var2, Throwable var3, boolean var4) {
      if (var2 != null) {
         if (var2.isReadable()) {
            this.readPending = false;
            var1.fireChannelRead(var2);
         } else {
            var2.release();
         }
      }

      var1.fireChannelReadComplete();
      var1.fireExceptionCaught(var3);
      if (!var4 && !(var3 instanceof IOException)) {
         return false;
      } else {
         this.closeOnRead(var1);
         return true;
      }
   }

   public void closeOnRead(ChannelPipeline var1) {
      EpollSocketChannel.access$002(this.this$0, true);
      if (this.this$0.isOpen()) {
         if (Boolean.TRUE.equals(this.this$0.config().getOption(ChannelOption.ALLOW_HALF_CLOSURE))) {
            this.clearEpollIn0();
            var1.fireUserEventTriggered(ChannelInputShutdownEvent.INSTANCE);
         } else {
            this.close(this.voidPromise());
         }
      }
   }

   public int doReadBytes(ByteBuf var1) {
      int var2 = var1.writerIndex();
      int var3;
      if (var1.hasMemoryAddress()) {
         var3 = Native.readAddress(this.this$0.fd, var1.memoryAddress(), var2, var1.capacity());
      } else {
         ByteBuffer var4 = var1.internalNioBuffer(var2, var1.writableBytes());
         var3 = Native.read(this.this$0.fd, var4, var4.position(), var4.limit());
      }

      if (var3 > 0) {
         var1.writerIndex(var2 + var3);
      }

      return var3;
   }
}
