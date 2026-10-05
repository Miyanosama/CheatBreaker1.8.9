package com.cheatbreaker.client.util.discord;

import com.cheatbreaker.client.CheatBreaker;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import net.minecraft.client.Minecraft;

public class DiscordReadyListener implements IPCListener {
   public CheatBreaker recoveredField89;

   public DiscordReadyListener(CheatBreaker var1) {
      this.recoveredField89 = var1;
   }

   @Override
   public void method_13333(IPCClient var1) {
      CheatBreaker.getInstance()
         .method_19780(
            Minecraft.getMinecraft().getSession().getUsername(),
            CheatBreaker.method_19781(CheatBreaker.getInstance()).method_10921(CheatBreaker.getInstance().method_19773())
         );
   }
}
