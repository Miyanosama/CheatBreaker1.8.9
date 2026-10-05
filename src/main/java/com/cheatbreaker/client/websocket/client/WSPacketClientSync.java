package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientSync extends WSPacket {
   public double recoveredField52;
   public int recoveredField53;

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField53 = var1.readInt();
      this.recoveredField52 = var1.readDouble();
   }

   public WSPacketClientSync(int var1, double var2) {
      this.recoveredField53 = var1;
      this.recoveredField52 = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeInt(this.recoveredField53);
      var1.writeDouble(this.recoveredField52);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public WSPacketClientSync() {
   }

   public int method_23438() {
      return this.recoveredField53;
   }

   public double method_23439() {
      return this.recoveredField52;
   }
}
