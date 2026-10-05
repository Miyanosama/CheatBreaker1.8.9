package io.netty.channel;

import net.minecraft.client.stream.MetadataCombat;
import net.minecraft.entity.ai.EntityAISit;
import org.apache.log4j.lf5.viewer.LogTableModel;

public class DefaultChannelPipeline$3 implements Runnable {
   public EntityAISit __junk5433521417710642272;
   public MetadataCombat __junk730595233238296889;
   public LogTableModel __junk5853885130994748461;

   @Override
   public void run() {
      DefaultChannelPipeline.access$100(this.this$0, this.val$ctx);
   }

   public DefaultChannelPipeline$3(DefaultChannelPipeline var1, ChannelHandlerContext var2) {
      this.this$0 = var1;
      this.val$ctx = var2;
      super();
   }
}
