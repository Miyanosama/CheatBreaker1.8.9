package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketForceCrash extends WSPacket {
   @Override
   public void write(PacketBuffer var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10127(this);
   }
}
