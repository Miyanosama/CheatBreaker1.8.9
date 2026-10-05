package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientProcessList extends WSPacket {
   public List<String> recoveredField4;
   public static byte[] recoveredField5 = new byte[]{36, -70, -63, 3, -116, 46, -121, -127, 117, 64, 58, 5, 75, 96, -63, 36};

   public WSPacketClientProcessList(List<String> var1) {
      this.recoveredField4 = var1;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeInt(this.recoveredField4.size());

      for (Object var3 : this.recoveredField4) {
         var1.writeString((String)var3);
      }
   }

   public WSPacketClientProcessList() {
   }

   @Override
   public void read(PacketBuffer var1) {
      int var2 = var1.readInt();
      this.recoveredField4 = new ArrayList<>();

      for (int var3 = 0; var3 < var2; var3++) {
         this.recoveredField4.add(var1.readStringFromBuffer(512));
      }
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public List<String> method_04417() {
      return this.recoveredField4;
   }
}
