package com.cheatbreaker.client.websocket.shared;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientFriendRequestUpdate extends WSPacket {
   public boolean recoveredField165;
   public String recoveredField166;

   public WSPacketClientFriendRequestUpdate(boolean var1, String var2) {
      this.recoveredField165 = var1;
      this.recoveredField166 = var2;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10120(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField165 = var1.readBoolean();
      this.recoveredField166 = var1.readStringFromBuffer(52);
   }

   public boolean method_11226() {
      return this.recoveredField165;
   }

   public WSPacketClientFriendRequestUpdate() {
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeBoolean(this.recoveredField165);
      var1.writeString(this.recoveredField166);
   }

   public String method_11227() {
      return this.recoveredField166;
   }
}
