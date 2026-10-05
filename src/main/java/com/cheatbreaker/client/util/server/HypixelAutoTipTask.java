package com.cheatbreaker.client.util.server;

import com.cheatbreaker.client.CheatBreaker;
import java.util.TimerTask;
import net.minecraft.client.Minecraft;

public class HypixelAutoTipTask extends TimerTask {
   @Override
   public void run() {
      CheatBreaker var1 = CheatBreaker.getInstance();
      if (var1 != null && (var1.getModuleManager() != null || Minecraft.getMinecraft().thePlayer != null)) {
         if (var1.getModuleManager().recoveredField1719.isEnabled()
            && var1.getModuleManager().recoveredField1719.recoveredField451.method_08908()
            && Minecraft.getMinecraft().getCurrentServerData() != null
            && Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("hypixel")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/tip all");
         }
      }
   }
}
