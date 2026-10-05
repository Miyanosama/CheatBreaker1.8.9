package io.netty.handler.traffic;

import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import javazoom.jl.decoder.Bitstream;
import net.minecraft.command.CommandNotFoundException;

public class GlobalTrafficShapingHandler$1 implements Runnable {
   public Bitstream __junk5175324957759421606;
   public CommandNotFoundException __junk8561534406315578252;

   @Override
   public void run() {
      GlobalTrafficShapingHandler.access$100(this.this$0, this.val$ctx, this.val$mqfinal);
   }

   public GlobalTrafficShapingHandler$1(GlobalTrafficShapingHandler var1, ChannelHandlerContext var2, List var3) {
      this.this$0 = var1;
      this.val$ctx = var2;
      this.val$mqfinal = var3;
      super();
   }
}
