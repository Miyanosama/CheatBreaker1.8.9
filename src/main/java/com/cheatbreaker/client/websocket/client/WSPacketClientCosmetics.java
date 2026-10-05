package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.util.List;
import net.minecraft.network.PacketBuffer;

public class WSPacketClientCosmetics extends WSPacket {
   public List<ClientResourceManager> recoveredField278;

   @Override
   public void handle(AssetsWebSocket var1) {
   }

   public WSPacketClientCosmetics() {
   }

   public List<ClientResourceManager> method_05446() {
      return this.recoveredField278;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeInt(this.recoveredField278.size());

      for (ClientResourceManager var3 : this.recoveredField278) {
         var1.writeLong(var3.method_20860());
         var1.writeBoolean(var3.method_20849());
         var1.writeString(var3.method_20858());
         var1.writeString(var3.method_20848().method_00485());
         var1.writeFloat(var3.method_20846());
         var1.writeString(var3.method_20859().toString().replaceFirst("minecraft:", ""));
      }
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   public WSPacketClientCosmetics(List<ClientResourceManager> var1) {
      this.recoveredField278 = var1;
   }
}
