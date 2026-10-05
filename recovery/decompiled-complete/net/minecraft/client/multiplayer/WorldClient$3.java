package net.minecraft.client.multiplayer;

import com.cheatbreaker.client.util.voicechat.VoiceUser;
import java.util.concurrent.Callable;
import net.minecraft.client.model.ModelCreeper;

public class WorldClient$3 implements Callable<String> {
   public ModelCreeper field_0002;
   public VoiceUser field_0000;

   public WorldClient$3(WorldClient var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return WorldClient.access$200(this.this$0).thePlayer.getClientBrand();
   }
}
