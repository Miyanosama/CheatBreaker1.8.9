package io.netty.channel;

import com.cheatbreaker.client.ui.util.font.CBFont;
import io.netty.util.Recycler$Handle;
import io.netty.util.internal.RecyclableMpscLinkedQueueNode;

public abstract class AbstractChannelHandlerContext$AbstractWriteTask extends RecyclableMpscLinkedQueueNode<Runnable> implements Runnable {
   public int size;
   public AbstractChannelHandlerContext ctx;
   public ChannelPromise promise;
   public Object msg;
   public CBFont __junk1288232409924023662;

   @Override
   public void run() {
      try {
         if (this.size > 0) {
            ChannelOutboundBuffer var1 = AbstractChannelHandlerContext.access$1900(this.ctx).unsafe().outboundBuffer();
            if (var1 != null) {
               var1.decrementPendingOutboundBytes(this.size);
            }
         }

         this.write(this.ctx, this.msg, this.promise);
      } finally {
         this.ctx = null;
         this.msg = null;
         this.promise = null;
      }
   }

   public Runnable value() {
      return this;
   }

   public AbstractChannelHandlerContext$AbstractWriteTask(Recycler$Handle var1) {
      super(var1);
   }

   public static void init(AbstractChannelHandlerContext$AbstractWriteTask var0, AbstractChannelHandlerContext var1, Object var2, int var3, ChannelPromise var4) {
      var0.ctx = var1;
      var0.msg = var2;
      var0.promise = var4;
      var0.size = var3;
   }

   public void write(AbstractChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      AbstractChannelHandlerContext.access$2000(var1, var2, var3);
   }
}
