package io.netty.handler.stream;

import com.cheatbreaker.client.module.type.NumberHudModule;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.world.gen.structure.StructureComponent$1;
import org.apache.log4j.SimpleLayout;

public class ChunkedWriteHandler$1 implements Runnable {
   public NumberHudModule __junk3350856802434230113;
   public StructureComponent$1 __junk9106811427054999130;
   public SimpleLayout __junk8130078898393483756;

   @Override
   public void run() {
      try {
         ChunkedWriteHandler.access$000(this.this$0, this.val$ctx);
      } catch (Exception var2) {
         if (ChunkedWriteHandler.access$100().isWarnEnabled()) {
            ChunkedWriteHandler.access$100().warn("Unexpected exception while sending chunks.", (Throwable)var2);
         }
      }
   }

   public ChunkedWriteHandler$1(ChunkedWriteHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }
}
