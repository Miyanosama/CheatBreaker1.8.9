package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientRequestsStatus extends WSPacket {
   public boolean accepting;

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.accepting = var1.readBoolean();
   }

   public WSPacketClientRequestsStatus() {
   }

   public boolean isAccepting() {
      return this.accepting;
   }

   public WSPacketClientRequestsStatus(boolean var1) {
      this.accepting = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeBoolean(this.accepting);
   }
}
