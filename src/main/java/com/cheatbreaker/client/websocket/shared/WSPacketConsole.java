package com.cheatbreaker.client.websocket.shared;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketConsole extends WSPacket {
   public String recoveredField1735;

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField1735 = var1.readStringFromBuffer(32767);
   }

   public WSPacketConsole(String var1) {
      this.recoveredField1735 = var1;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10128(this);
   }

   public WSPacketConsole() {
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.recoveredField1735);
   }

   public String method_21111() {
      return this.recoveredField1735;
   }
}
