package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S48PacketResourcePackSend implements Packet<INetHandlerPlayClient> {
   public String url;
   public String hash;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeString(this.url);
      var1.writeString(this.hash);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.url = var1.readStringFromBuffer(32767);
      this.hash = var1.readStringFromBuffer(40);
   }

   public String getURL() {
      return this.url;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleResourcePack(this);
   }

   public S48PacketResourcePackSend(String var1, String var2) {
      this.url = var1;
      this.hash = var2;
      if (var2.length() > 40) {
         throw new IllegalArgumentException("Hash is too long (max 40, was " + var2.length() + ")");
      }
   }

   public S48PacketResourcePackSend() {
   }

   public String getHash() {
      return this.hash;
   }
}
