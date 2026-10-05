package io.netty.handler.timeout;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import javazoom.jl.decoder.LayerIIIDecoder$III_side_info_t;
import net.minecraft.command.CommandEffect;

public class WriteTimeoutHandler$1 implements Runnable {
   public LayerIIIDecoder$III_side_info_t __junk8951292013010633150;
   public CommandEffect __junk3153485701090836636;

   @Override
   public void run() {
      if (!this.val$future.isDone()) {
         try {
            this.this$0.writeTimedOut(this.val$ctx);
         } catch (Throwable var2) {
            this.val$ctx.fireExceptionCaught(var2);
         }
      }
   }

   public WriteTimeoutHandler$1(WriteTimeoutHandler var1, ChannelPromise var2, ChannelHandlerContext var3) {
      this.this$0 = var1;
      this.val$future = var2;
      this.val$ctx = var3;
      super();
   }
}
