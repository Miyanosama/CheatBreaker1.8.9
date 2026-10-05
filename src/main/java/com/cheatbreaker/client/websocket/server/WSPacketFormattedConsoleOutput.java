package com.cheatbreaker.client.websocket.server;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketFormattedConsoleOutput extends WSPacket {
   public String recoveredField2608;
   public String recoveredField2609;

   public WSPacketFormattedConsoleOutput(String var1, String var2) {
      this.recoveredField2608 = var1;
      this.recoveredField2609 = var2;
   }

   public String method_05710() {
      return this.recoveredField2608;
   }

   public String method_05711() {
      return this.recoveredField2609;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField2608 = var1.readStringFromBuffer(128);
      this.recoveredField2609 = var1.readStringFromBuffer(512);
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.recoveredField2608);
      var1.writeString(this.recoveredField2609);
   }

   public WSPacketFormattedConsoleOutput() {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10117(this);
   }
}
