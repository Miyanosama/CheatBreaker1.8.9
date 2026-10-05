package net.minecraft.client;

import com.cheatbreaker.client.nethandler.server.PacketVoiceChannelUpdate;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.OpenGlHelper;

public class Minecraft$17 implements Callable<String> {
   public PacketVoiceChannelUpdate field_0000;

   public String call() {
      return OpenGlHelper.getLogText();
   }

   public Minecraft$17(Minecraft var1) {
      this.field_74500_a = var1;
      super();
   }
}
