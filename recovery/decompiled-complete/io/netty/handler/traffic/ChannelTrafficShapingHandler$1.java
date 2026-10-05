package io.netty.handler.traffic;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.model.ModelBase;
import org.apache.log4j.pattern.MethodLocationPatternConverter;

public class ChannelTrafficShapingHandler$1 implements Runnable {
   public ModelBase __junk4612124458133510818;
   public MethodLocationPatternConverter __junk5031684108311731244;

   @Override
   public void run() {
      ChannelTrafficShapingHandler.access$100(this.this$0, this.val$ctx);
   }

   public ChannelTrafficShapingHandler$1(ChannelTrafficShapingHandler var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }
}
