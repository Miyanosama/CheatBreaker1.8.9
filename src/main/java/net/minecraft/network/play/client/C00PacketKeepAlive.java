package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C00PacketKeepAlive implements Packet<INetHandlerPlayServer> {
   public int key;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.key);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.key = var1.readVarIntFromBuffer();
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processKeepAlive(this);
   }

   public int getKey() {
      return this.key;
   }

   public C00PacketKeepAlive() {
   }

   public C00PacketKeepAlive(int var1) {
      this.key = var1;
   }
}
