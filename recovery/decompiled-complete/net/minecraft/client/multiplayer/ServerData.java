package net.minecraft.client.multiplayer;

import io.netty.channel.sctp.oio.OioSctpChannel$OioSctpChannelConfig;
import net.minecraft.nbt.NBTTagCompound;

public class ServerData {
   public String populationInfo;
   public String serverName;
   public boolean field_0007;
   public String serverIP;
   public ServerData$ServerResourceMode resourceMode;
   public String serverIcon;
   public long pingToServer;
   public int field_0011 = 47;
   public String field_0004;
   public boolean field_0017;
   public boolean field_0001;
   public boolean lanServer;
   public String serverMOTD;
   public boolean field_0006;
   public OioSctpChannel$OioSctpChannelConfig field_0012;
   public String field_0014 = "1.8.9";
   public int field_0000;
   public String field_0005;

   public void setResourceMode(ServerData$ServerResourceMode var1) {
      this.resourceMode = var1;
   }

   public ServerData(boolean var1, String var2, String var3, boolean var4) {
      this.resourceMode = ServerData$ServerResourceMode.PROMPT;
      this.field_0001 = false;
      this.field_0006 = false;
      this.field_0017 = false;
      this.serverName = var2;
      this.serverIP = var3;
      this.lanServer = var4;
      this.field_0001 = var1;
   }

   public NBTTagCompound getNBTCompound() {
      NBTTagCompound var1 = new NBTTagCompound();
      var1.setString("name", this.serverName);
      var1.setString("ip", this.serverIP);
      if (this.serverIcon != null) {
         var1.setString("icon", this.serverIcon);
      }

      if (this.resourceMode == ServerData$ServerResourceMode.ENABLED) {
         var1.setBoolean("acceptTextures", true);
      } else if (this.resourceMode == ServerData$ServerResourceMode.DISABLED) {
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
      this.resourceMode = ServerData$ServerResourceMode.PROMPT;
      this.field_0001 = false;
      this.field_0006 = false;
      this.field_0017 = false;
      this.serverName = var1;
      this.serverIP = var2;
      this.lanServer = var3;
   }

   public ServerData$ServerResourceMode getResourceMode() {
      return this.resourceMode;
   }

   public String getBase64EncodedIconData() {
      return this.serverIcon;
   }

   public boolean method_04647() {
      return this.field_0011 == -1332;
   }

   public static ServerData getServerDataFromNBTCompound(NBTTagCompound var0) {
      ServerData var1 = new ServerData(var0.getString("name"), var0.getString("ip"), false);
      if (var0.hasKey("icon", 8)) {
         var1.setBase64EncodedIconData(var0.getString("icon"));
      }

      if (var0.hasKey("acceptTextures", 1)) {
         if (var0.getBoolean("acceptTextures")) {
            var1.setResourceMode(ServerData$ServerResourceMode.ENABLED);
         } else {
            var1.setResourceMode(ServerData$ServerResourceMode.DISABLED);
         }
      } else {
         var1.setResourceMode(ServerData$ServerResourceMode.PROMPT);
      }

      return var1;
   }
}
