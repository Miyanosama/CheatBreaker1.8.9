package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientFriendRemove extends WSPacket {
   public String playerId;

   public WSPacketClientFriendRemove() {
   }

   public String getPlayerId() {
      return this.playerId;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.playerId);
   }

   public WSPacketClientFriendRemove(String var1) {
      this.playerId = var1;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.playerId = var1.readStringFromBuffer(52);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleFriendRemove(this);
   }
}
