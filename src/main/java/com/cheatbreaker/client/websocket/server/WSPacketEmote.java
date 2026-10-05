package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.UUID;
import net.minecraft.network.PacketBuffer;

public class WSPacketEmote extends WSPacket {
   public int recoveredField3199;
   public UUID recoveredField3200;

   public WSPacketEmote() {
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField3200 = var1.readUuid();
      this.recoveredField3199 = var1.readInt();
   }

   public UUID method_13269() {
      return this.recoveredField3200;
   }

   public WSPacketEmote(UUID var1, int var2) {
      this.recoveredField3200 = var1;
      this.recoveredField3199 = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeUuid(this.recoveredField3200);
      var1.writeInt(this.recoveredField3199);
   }

   public int method_13270() {
      return this.recoveredField3199;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10124(this);
   }
}
