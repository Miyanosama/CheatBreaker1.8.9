package net.minecraft.client.main;

import net.minecraft.client.Minecraft;

public class ClientShutdownHook extends Thread {
   @Override
   public void run() {
      Minecraft.stopIntegratedServer();
   }

   public ClientShutdownHook(String var1) {
      super(var1);
   }
}
