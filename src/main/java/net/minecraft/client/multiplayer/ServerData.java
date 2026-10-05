package net.minecraft.client.multiplayer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class ServerData {
   public String populationInfo;
   public String serverName;
   public boolean recoveredField3385;
   public String serverIP;
   public ServerData.ServerResourceMode resourceMode;
   public String serverIcon;
   public long pingToServer;
   public int recoveredField3386 = 47;
   public String recoveredField3387;
   public boolean recoveredField3388;
   public boolean recoveredField3389;
   public boolean lanServer;
   public String serverMOTD;
   public boolean recoveredField3390;
   public String recoveredField3391 = "1.8.9";
   public int recoveredField3392;
   public String recoveredField3393;

   public void setResourceMode(ServerData.ServerResourceMode var1) {
      this.resourceMode = var1;
   }

   public ServerData(boolean var1, String var2, String var3, boolean var4) {
      this.resourceMode = ServerData.ServerResourceMode.PROMPT;
      this.recoveredField3389 = false;
      this.recoveredField3390 = false;
      this.recoveredField3388 = false;
      this.serverName = var2;
      this.serverIP = var3;
      this.lanServer = var4;
      this.recoveredField3389 = var1;
   }

   public NBTTagCompound getNBTCompound() {
      NBTTagCompound var1 = new NBTTagCompound();
      var1.setString("name", this.serverName);
      var1.setString("ip", this.serverIP);
      if (this.serverIcon != null) {
         var1.setString("icon", this.serverIcon);
      }

      if (this.resourceMode == ServerData.ServerResourceMode.ENABLED) {
         var1.setBoolean("acceptTextures", true);
      } else if (this.resourceMode == ServerData.ServerResourceMode.DISABLED) {
         var1.setBoolean("acceptTextures", false);
      }

      return var1;
   }

   public void copyFrom(ServerData var1) {
      this.serverIP = var1.serverIP;
      this.serverName = var1.serverName;
      this.setResourceMode(var1.getResourceMode());
      this.serverIcon = var1.serverIcon;
      this.lanServer = var1.lanServer;
   }

   public void setBase64EncodedIconData(String var1) {
      this.serverIcon = var1;
   }

   public boolean isOnLAN() {
      return this.lanServer;
   }

   public ServerData(String var1, String var2, boolean var3) {
      this.resourceMode = ServerData.ServerResourceMode.PROMPT;
      this.recoveredField3389 = false;
      this.recoveredField3390 = false;
      this.recoveredField3388 = false;
      this.serverName = var1;
      this.serverIP = var2;
      this.lanServer = var3;
   }

   public ServerData.ServerResourceMode getResourceMode() {
      return this.resourceMode;
   }

   public String getBase64EncodedIconData() {
      return this.serverIcon;
   }

   public boolean method_04647() {
      return this.recoveredField3386 == -1332;
   }

   public static ServerData getServerDataFromNBTCompound(NBTTagCompound var0) {
      ServerData var1 = new ServerData(var0.getString("name"), var0.getString("ip"), false);
      if (var0.hasKey("icon", 8)) {
         var1.setBase64EncodedIconData(var0.getString("icon"));
      }

      if (var0.hasKey("acceptTextures", 1)) {
         if (var0.getBoolean("acceptTextures")) {
            var1.setResourceMode(ServerData.ServerResourceMode.ENABLED);
         } else {
            var1.setResourceMode(ServerData.ServerResourceMode.DISABLED);
         }
      } else {
         var1.setResourceMode(ServerData.ServerResourceMode.PROMPT);
      }

      return var1;
   }

   public static enum ServerResourceMode {
      ENABLED("enabled"),
      DISABLED("disabled"),
      PROMPT("prompt");
      public IChatComponent motd;

      ServerResourceMode(String var3) {
         this.motd = new ChatComponentTranslation("addServer.resourcePack." + var3);
      }

      public IChatComponent getMotd() {
         return this.motd;
      }
   }
}
