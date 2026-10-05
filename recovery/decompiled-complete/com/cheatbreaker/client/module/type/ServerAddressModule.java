package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.resources.SkinManager$2;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.util.ResourceLocation;
import recovered.unidentified.UnidentifiedClass3436;

public class ServerAddressModule extends IconTextHudModule {
   public UnidentifiedClass3436 field_0004;
   public S00PacketDisconnect field_0001;
   public Setting field_0000;
   public SkinManager$2 field_0002;
   public ActiveRenderInfo field_0003;

   @Override
   public ResourceLocation method_01868() {
      if (!this.minecraft.isIntegratedServerRunning() && this.minecraft.theWorld != null) {
         return new ResourceLocation("servers/" + this.method_00167() + "/icon");
      } else {
         return !this.field_0000.getValue()
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
         return !this.field_0000.method_08908() ? "Singleplayer" : null;
      }
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.field_0000 = new Setting(this, "Hide in Singleplayer", "Hides the mod in Singleplayer.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003);
   }

   @Override
   public String method_00164() {
      return !this.minecraft.isSingleplayer() ? this.minecraft.currentServerData.serverIP : "minehq.com";
   }
}
