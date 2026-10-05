package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketKeyRequest extends WSPacket {
   public byte[] recoveredField3355;

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10116(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField3355 = this.readKey(var1);
   }

   public WSPacketKeyRequest(byte[] var1) {
      this.recoveredField3355 = var1;
   }

   public WSPacketKeyRequest() {
   }

   public byte[] method_04977() {
      return this.recoveredField3355;
   }

   @Override
   public void write(PacketBuffer var1) {
      this.writeKey(var1, this.recoveredField3355);
   }
}
