package net.minecraft.server.integrated;

import com.google.common.collect.Lists;
import io.netty.channel.embedded.EmbeddedSocketAddress;
import io.netty.channel.group.DefaultChannelGroupFuture;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.entity.player.EntityPlayerMP;

public class IntegratedServer$3 implements Runnable {
   public DefaultChannelGroupFuture field_0003;
   public EmbeddedSocketAddress field_0000;
   public EntityCloudFX field_0002;

   public IntegratedServer$3(IntegratedServer var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void run() {
      for (EntityPlayerMP var2 : Lists.newArrayList(this.this$0.getConfigurationManager().getPlayerList())) {
         this.this$0.getConfigurationManager().playerLoggedOut(var2);
      }
   }
}
