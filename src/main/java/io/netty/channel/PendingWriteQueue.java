package io.netty.channel;

import io.netty.handler.codec.marshalling.ChannelBufferByteOutput;
import io.netty.util.Recycler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.command.CommandResultStats;
import net.optifine.entity.model.ModelAdapterSign;
import recovered.unidentified.UnidentifiedClass0672;

public class PendingWriteQueue {
   public PendingWriteQueue.PendingWrite tail;
   public MessageSizeEstimator.Handle estimatorHandle;
   public int size;
   public static final boolean $assertionsDisabled = !PendingWriteQueue.class.desiredAssertionStatus();
   public ChannelHandlerContext ctx;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(PendingWriteQueue.class);
   public PendingWriteQueue.PendingWrite head;
   public ChannelOutboundBuffer buffer;

   public ChannelPromise remove() {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else {
         PendingWriteQueue.PendingWrite var1 = this.head;
         if (var1 == null) {
            return null;
         } else {
            ChannelPromise var2 = var1.promise;
            ReferenceCountUtil.safeRelease(var1.msg);
            this.recycle(var1);
            return var2;
         }
      }
   }

   public int size() {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else {
         return this.size;
      }
   }

   public Object current() {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else {
         PendingWriteQueue.PendingWrite var1 = this.head;
         return var1 == null ? null : var1.msg;
      }
   }

   public ChannelFuture removeAndWrite() {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else {
         PendingWriteQueue.PendingWrite var1 = this.head;
         if (var1 == null) {
            return null;
         } else {
            Object var2 = var1.msg;
            ChannelPromise var3 = var1.promise;
            this.recycle(var1);
            return this.ctx.write(var2, var3);
         }
      }
   }

   public void removeAndFail(Throwable var1) {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else if (var1 == null) {
         throw new NullPointerException("cause");
      } else {
         PendingWriteQueue.PendingWrite var2 = this.head;
         if (var2 != null) {
            ReferenceCountUtil.safeRelease(var2.msg);
            ChannelPromise var3 = var2.promise;
            safeFail(var3, var1);
            this.recycle(var2);
         }
      }
   }

   public static void safeFail(ChannelPromise var0, Throwable var1) {
      if (!(var0 instanceof VoidChannelPromise) && !var0.tryFailure(var1)) {
         logger.warn("Failed to mark a promise as failure because it's done already: {}", var0, var1);
      }
   }

   public ChannelFuture removeAndWriteAll() {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else {
         PendingWriteQueue.PendingWrite var1 = this.head;
         if (var1 == null) {
            return null;
         } else if (this.size == 1) {
            return this.removeAndWrite();
         } else {
            ChannelPromise var2 = this.ctx.newPromise();
            ChannelPromiseAggregator var3 = new ChannelPromiseAggregator(var2);

            while (var1 != null) {
               PendingWriteQueue.PendingWrite var4 = var1.next;
               Object var5 = var1.msg;
               ChannelPromise var6 = var1.promise;
               this.recycle(var1);
               this.ctx.write(var5, var6);
               var3.add(var6);
               var1 = var4;
            }

            this.assertEmpty();
            return var2;
         }
      }
   }

   public PendingWriteQueue(ChannelHandlerContext var1) {
      if (var1 == null) {
         throw new NullPointerException("ctx");
      } else {
         this.ctx = var1;
         this.buffer = var1.channel().unsafe().outboundBuffer();
         this.estimatorHandle = var1.channel().config().getMessageSizeEstimator().newHandle();
      }
   }

   public void add(Object var1, ChannelPromise var2) {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else if (var1 == null) {
         throw new NullPointerException("msg");
      } else if (var2 == null) {
         throw new NullPointerException("promise");
      } else {
         int var3 = this.estimatorHandle.size(var1);
         if (var3 < 0) {
            var3 = 0;
         }

         PendingWriteQueue.PendingWrite var4 = PendingWriteQueue.PendingWrite.newInstance(var1, var3, var2);
         PendingWriteQueue.PendingWrite var5 = this.tail;
         if (var5 == null) {
            this.tail = this.head = var4;
         } else {
            var5.next = var4;
            this.tail = var4;
         }

         this.size++;
         this.buffer.incrementPendingOutboundBytes(var4.size);
      }
   }

   public void removeAndFailAll(Throwable var1) {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else if (var1 == null) {
         throw new NullPointerException("cause");
      } else {
         PendingWriteQueue.PendingWrite var2 = this.head;

         while (var2 != null) {
            PendingWriteQueue.PendingWrite var3 = var2.next;
            ReferenceCountUtil.safeRelease(var2.msg);
            ChannelPromise var4 = var2.promise;
            this.recycle(var2);
            safeFail(var4, var1);
            var2 = var3;
         }

         this.assertEmpty();
      }
   }

   public void assertEmpty() {
      if (!$assertionsDisabled && (this.tail != null || this.head != null || this.size != 0)) {
         throw new AssertionError();
      }
   }

   public boolean isEmpty() {
      if (!$assertionsDisabled && !this.ctx.executor().inEventLoop()) {
         throw new AssertionError();
      } else {
         return this.head == null;
      }
   }

   public void recycle(PendingWriteQueue.PendingWrite var1) {
      PendingWriteQueue.PendingWrite var2 = var1.next;
      this.buffer.decrementPendingOutboundBytes(var1.size);
      var1.recycle();
      this.size--;
      if (var2 == null) {
         this.head = this.tail = null;
         if (!$assertionsDisabled && this.size != 0) {
            throw new AssertionError();
         }
      } else {
         this.head = var2;
         if (!$assertionsDisabled && this.size <= 0) {
            throw new AssertionError();
         }
      }
   }

   public static final class PendingWrite {
      public ChannelPromise promise;
      public Recycler.Handle handle;
      public static Recycler<PendingWriteQueue.PendingWrite> RECYCLER = new Recycler<PendingWriteQueue.PendingWrite>() {

         public PendingWriteQueue.PendingWrite newObject(Recycler.Handle var1) {
            return new PendingWriteQueue.PendingWrite(var1);
         }
      };
      public Object msg;
      public long size;
      public PendingWriteQueue.PendingWrite next;

      public static PendingWriteQueue.PendingWrite newInstance(Object var0, int var1, ChannelPromise var2) {
         PendingWriteQueue.PendingWrite var3 = RECYCLER.get();
         var3.size = var1;
         var3.msg = var0;
         var3.promise = var2;
         return var3;
      }

      public void recycle() {
         this.size = 0L;
         this.next = null;
         this.msg = null;
         this.promise = null;
         RECYCLER.recycle(this, this.handle);
      }

      public PendingWrite(Recycler.Handle var1) {
         this.handle = var1;
      }
   }
}
