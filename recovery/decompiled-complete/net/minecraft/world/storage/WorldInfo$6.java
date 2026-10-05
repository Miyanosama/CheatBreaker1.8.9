package net.minecraft.world.storage;

import io.netty.channel.ChannelOption;
import java.util.concurrent.Callable;
import net.minecraft.entity.ai.EntityAIWander;
import org.apache.log4j.lf5.util.StreamUtils;

public class WorldInfo$6 implements Callable<String> {
   public StreamUtils field_0003;
   public ChannelOption field_0000;
   public EntityAIWander field_0002;

   public WorldInfo$6(WorldInfo var1) {
      this.field_85115_a = var1;
      super();
   }

   public String call() {
      return String.valueOf(WorldInfo.access$800(this.field_85115_a));
   }
}
