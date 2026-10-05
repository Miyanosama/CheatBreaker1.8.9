package net.minecraft.client.particle;

import com.cheatbreaker.client.websocket.server.WSPacketFriendStatusUpdate;
import java.util.concurrent.Callable;
import javazoom.jl.decoder.OutputChannels;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.optifine.player.PlayerItemModel;
import org.apache.log4j.lf5.viewer.LogTable$LogTableListSelectionListener;

public class EffectRenderer$2 implements Callable<String> {
   public PlayerItemModel field_0005;
   public LogTable$LogTableListSelectionListener field_0004;
   public EntityOtherPlayerMP field_0000;
   public WSPacketFriendStatusUpdate field_0001;
   public OutputChannels field_0006;

   public EffectRenderer$2(EffectRenderer var1, int var2) {
      this.this$0 = var1;
      this.val$i = var2;
      super();
   }

   public String call() {
      return this.val$i == 0
         ? "MISC_TEXTURE"
         : (this.val$i == 1 ? "TERRAIN_TEXTURE" : (this.val$i == 3 ? "ENTITY_PARTICLE_TEXTURE" : "Unknown - " + this.val$i));
   }
}
