package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientKeyResponse extends WSPacket {
   public byte[] recoveredField2026;

   public WSPacketClientKeyResponse() {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField2026 = this.readKey(var1);
   }

   public WSPacketClientKeyResponse(byte[] var1) {
      this.recoveredField2026 = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      this.writeKey(var1, this.recoveredField2026);
   }

   public byte[] method_23630() {
      return this.recoveredField2026;
   }
}
