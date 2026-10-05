package com.cheatbreaker.client.websocket.shared;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketServerUpdate extends WSPacket {
   public String server;
   public String playerId;

   public String getServer() {
      return this.server;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleServerUpdate(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.server = var1.readStringFromBuffer(52);
      this.playerId = var1.readStringFromBuffer(100);
   }

   public String getPlayerId() {
      return this.playerId;
   }

   public WSPacketServerUpdate() {
   }

   public WSPacketServerUpdate(String var1, String var2) {
      this.playerId = var1;
      this.server = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.server);
      var1.writeString(this.playerId);
   }
}
