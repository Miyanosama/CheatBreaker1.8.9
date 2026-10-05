package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import net.minecraft.util.ResourceLocation;

public class ServerAddressModule extends IconTextHudModule {
   public Setting recoveredField3523;

   @Override
   public ResourceLocation method_01868() {
      if (!this.minecraft.isIntegratedServerRunning() && this.minecraft.theWorld != null) {
         return new ResourceLocation("servers/" + this.method_00167() + "/icon");
      } else {
         return !(Boolean)this.recoveredField3523.getValue()
            ? new ResourceLocation("textures/misc/unknown_server.png")
            : new ResourceLocation("client/icons/servers/" + this.method_00164() + ".png");
      }
   }

   public ServerAddressModule() {
      super("Server Address", "[minehq.com]");
      this.method_28821("Displays the address of the current connected server.");
   }

   @Override
   public String method_00166() {
      return "IP";
   }

   @Override
   public String method_00167() {
      if (!this.minecraft.isIntegratedServerRunning() && this.minecraft.theWorld != null) {
         return this.minecraft.currentServerData.serverIP;
      } else {
         return !this.recoveredField3523.method_08908() ? "Singleplayer" : null;
      }
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.recoveredField3523 = new Setting(this, "Hide in Singleplayer", "Hides the mod in Singleplayer.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM);
   }

   @Override
   public String method_00164() {
      return !this.minecraft.isSingleplayer() ? this.minecraft.currentServerData.serverIP : "minehq.com";
   }
}
