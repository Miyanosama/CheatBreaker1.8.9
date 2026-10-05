package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientPlayerJoin extends WSPacket {
   public String recoveredField40;

   public WSPacketClientPlayerJoin(String var1) {
      this.recoveredField40 = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.recoveredField40);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public String method_19694() {
      return this.recoveredField40;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField40 = var1.readStringFromBuffer(52);
   }
}
