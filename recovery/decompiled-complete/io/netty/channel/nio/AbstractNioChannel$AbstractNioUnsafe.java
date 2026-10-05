package io.netty.channel.nio;

import io.netty.channel.AbstractChannel$AbstractUnsafe;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.TimeUnit;
import net.minecraft.item.ItemMapBase;

public abstract class AbstractNioChannel$AbstractNioUnsafe extends AbstractChannel$AbstractUnsafe implements AbstractNioChannel$NioUnsafe {
   public DefaultHttpDataFactory __junk7778395951802440076;
   public ItemMapBase __junk8788575638767525685;

   @Override
   public void flush0() {
      if (!this.isFlushPending()) {
         super.flush0();
      }
   }

   @Override
   public SelectableChannel ch() {
      return this.this$0.javaChannel();
   }

   @Override
   public void forceFlush() {
      super.flush0();
   }

   public boolean isFlushPending() {
      SelectionKey var1 = this.this$0.selectionKey();
      return var1.isValid() && (var1.interestOps() & 4) != 0;
   }

   @Override
   public void finishConnect() {
      if (!$assertionsDisabled && !this.this$0.eventLoop().inEventLoop()) {
         throw new AssertionError();
      } else {
         try {
            boolean var8 = this.this$0.isActive();
            this.this$0.doFinishConnect();
            this.fulfillConnectPromise(AbstractNioChannel.access$000(this.this$0), var8);
         } catch (Throwable var6) {
            Object var1 = var6;
            if (var6 instanceof ConnectException) {
               ConnectException var2 = new ConnectException(var6.getMessage() + ": " + AbstractNioChannel.access$100(this.this$0));
               var2.setStackTrace(var6.getStackTrace());
               var1 = var2;
            }

            this.fulfillConnectPromise(AbstractNioChannel.access$000(this.this$0), (Throwable)var1);
         } finally {
            if (AbstractNioChannel.access$200(this.this$0) != null) {
               AbstractNioChannel.access$200(this.this$0).cancel(false);
            }

            AbstractNioChannel.access$002(this.this$0, null);
         }
      }
   }

   @Override
   public void connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      if (var3.setUncancellable() && this.ensureOpen(var3)) {
         try {
            if (AbstractNioChannel.access$000(this.this$0) != null) {
               throw new IllegalStateException("connection attempt already made");
            }

            boolean var7 = this.this$0.isActive();
            if (this.this$0.doConnect(var1, var2)) {
               this.fulfillConnectPromise(var3, var7);
            } else {
               AbstractNioChannel.access$002(this.this$0, var3);
               AbstractNioChannel.access$102(this.this$0, var1);
               int var8 = this.this$0.config().getConnectTimeoutMillis();
               if (var8 > 0) {
                  AbstractNioChannel.access$202(
                     this.this$0, this.this$0.eventLoop().schedule(new AbstractNioChannel$AbstractNioUnsafe$1(this, var1), var8, TimeUnit.MILLISECONDS)
                  );
               }

               var3.addListener(new AbstractNioChannel$AbstractNioUnsafe$2(this));
            }
         } catch (Throwable var6) {
            Object var4 = var6;
            if (var6 instanceof ConnectException) {
               ConnectException var5 = new ConnectException(var6.getMessage() + ": " + var1);
               var5.setStackTrace(var6.getStackTrace());
               var4 = var5;
            }

            var3.tryFailure((Throwable)var4);
            this.closeIfClosed();
         }
      }
   }

   public void fulfillConnectPromise(ChannelPromise var1, Throwable var2) {
      if (var1 != null) {
         var1.tryFailure(var2);
         this.closeIfClosed();
      }
   }

   public AbstractNioChannel$AbstractNioUnsafe(AbstractNioChannel var1) {
      this.this$0 = var1;
      super(var1);
   }

   public void removeReadOp() {
      SelectionKey var1 = this.this$0.selectionKey();
      if (var1.isValid()) {
         int var2 = var1.interestOps();
         if ((var2 & this.this$0.readInterestOp) != 0) {
            var1.interestOps(var2 & ~this.this$0.readInterestOp);
         }
      }
   }

   public void fulfillConnectPromise(ChannelPromise var1, boolean var2) {
      if (var1 != null) {
         boolean var3 = var1.trySuccess();
         if (!var2 && this.this$0.isActive()) {
            this.this$0.pipeline().fireChannelActive();
         }

         if (!var3) {
            this.close(this.voidPromise());
         }
      }
   }
}
