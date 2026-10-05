package io.netty.util.concurrent;

import io.netty.channel.nio.AbstractNioByteChannel;
import net.minecraft.realms.RealmsConnect;
import org.apache.log4j.pattern.ThreadPatternConverter;

public class SingleThreadEventExecutor$4 implements Runnable {
   public AbstractNioByteChannel __junk2065586884197795063;
   public RealmsConnect __junk3251288831796391517;
   public ThreadPatternConverter __junk1153888890831763884;

   @Override
   public void run() {
      SingleThreadEventExecutor.access$600(this.this$0).remove(this.val$task);
   }

   public SingleThreadEventExecutor$4(SingleThreadEventExecutor var1, Runnable var2) {
      this.this$0 = var1;
      this.val$task = var2;
      super();
   }
}
