package io.netty.channel.local;

import io.netty.channel.ChannelPipeline;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityCommandBlock;
import org.apache.log4j.jmx.Agent;

public class LocalChannel$1 implements Runnable {
   public TileEntityChest __junk7998116921367453507;
   public Agent __junk5974388569792783063;
   public TileEntityCommandBlock __junk7490517670819359485;

   @Override
   public void run() {
      ChannelPipeline var1 = this.this$0.pipeline();

      while (true) {
         Object var2 = LocalChannel.access$000(this.this$0).poll();
         if (var2 == null) {
            var1.fireChannelReadComplete();
            return;
         }

         var1.fireChannelRead(var2);
      }
   }

   public LocalChannel$1(LocalChannel var1) {
      this.this$0 = var1;
      super();
   }
}
