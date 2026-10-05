package io.netty.handler.ssl;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.block.Block$4;

public class SslHandler$1 implements Runnable {
   public Block$4 __junk6731783367471562957;

   public SslHandler$1(SslHandler var1, ChannelHandlerContext var2, ChannelPromise var3) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$future = var3;
      super();
   }

   @Override
   public void run() {
      SslHandler.access$100(this.this$0).closeOutbound();

      try {
         this.this$0.write(this.val$ctx, Unpooled.EMPTY_BUFFER, this.val$future);
         this.this$0.flush(this.val$ctx);
      } catch (Exception var2) {
         if (!this.val$future.tryFailure(var2)) {
            SslHandler.access$200().warn("flush() raised a masked exception.", (Throwable)var2);
         }
      }
   }
}
