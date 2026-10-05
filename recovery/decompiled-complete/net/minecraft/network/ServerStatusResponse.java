package net.minecraft.network;

import io.netty.handler.codec.ByteToMessageCodec$Encoder;
import net.minecraft.util.IChatComponent;

public class ServerStatusResponse {
   public IChatComponent serverMotd;
   public ByteToMessageCodec$Encoder field_0005;
   public ServerStatusResponse$MinecraftProtocolVersionIdentifier protocolVersion;
   public ServerStatusResponse$PlayerCountData playerCount;
   public String favicon;
   public String field_0001;

   public String getFavicon() {
      return this.favicon;
   }

   public void setFavicon(String var1) {
      this.favicon = var1;
   }

   public IChatComponent getServerDescription() {
      return this.serverMotd;
   }

   public void setPlayerCountData(ServerStatusResponse$PlayerCountData var1) {
      this.playerCount = var1;
   }

   public void setProtocolVersionInfo(ServerStatusResponse$MinecraftProtocolVersionIdentifier var1) {
      this.protocolVersion = var1;
   }

   public ServerStatusResponse$PlayerCountData getPlayerCountData() {
      return this.playerCount;
   }

   public ServerStatusResponse$MinecraftProtocolVersionIdentifier getProtocolVersionInfo() {
      return this.protocolVersion;
   }

   public void setServerDescription(IChatComponent var1) {
      this.serverMotd = var1;
   }
}
