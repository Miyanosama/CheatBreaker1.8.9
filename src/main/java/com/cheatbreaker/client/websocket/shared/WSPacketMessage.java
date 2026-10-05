package com.cheatbreaker.client.websocket.shared;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import net.minecraft.network.PacketBuffer;

public class WSPacketMessage extends WSPacket {
   public String recoveredField3788;
   public String recoveredField3789;

   public String method_12690() {
      return this.recoveredField3789;
   }

   @Override
   public void read(PacketBuffer var1) {
      this.recoveredField3788 = var1.readStringFromBuffer(52);
      this.recoveredField3789 = var1.readStringFromBuffer(1024);
   }

   public WSPacketMessage() {
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.method_10123(this);
   }

   public String method_12691() {
      return this.recoveredField3788;
   }

   public WSPacketMessage(String var1, String var2) {
      this.recoveredField3788 = var1;
      this.recoveredField3789 = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.recoveredField3788);
      var1.writeString(this.recoveredField3789);
   }
}
