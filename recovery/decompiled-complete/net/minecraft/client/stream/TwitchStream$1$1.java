package net.minecraft.client.stream;

import javax.vecmath.Color3f;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.world.EnumSkyBlock;

public class TwitchStream$1$1 extends Thread {
   public S30PacketWindowItems field_0004;
   public EntityAIFollowOwner field_0001;
   public EnumSkyBlock field_0003;
   public Color3f field_0000;

   @Override
   public void run() {
      this.field_153082_a.field_153084_b.shutdownStream();
   }

   public TwitchStream$1$1(TwitchStream$1 var1, String var2) {
      this.field_153082_a = var1;
      super(var2);
   }
}
