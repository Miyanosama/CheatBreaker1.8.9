package com.cheatbreaker.client.websocket.shared;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;
import org.apache.log4j.lf5.viewer.LogTable;
import recovered.unidentified.UnidentifiedClass0609;
import recovered.unidentified.UnidentifiedClass3153;

public class WSPacketFriendRequest extends WSPacket {
   public String playerId;
   public UnidentifiedClass3153 field_0002;
   public LogTable field_0003;
   public UnidentifiedClass0609 field_0000;
   public String name;

   public String getPlayerId() {
      return this.playerId;
   }

   public WSPacketFriendRequest(String var1, String var2) {
      this.playerId = var1;
      this.name = var2;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleFriendRequest(this, false);
   }

   public String getName() {
      return this.name;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.playerId = var1.readStringFromBuffer(52);
      this.name = var1.readStringFromBuffer(32);
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.playerId);
      var1.writeString(this.name);
   }

   public WSPacketFriendRequest() {
   }
}
