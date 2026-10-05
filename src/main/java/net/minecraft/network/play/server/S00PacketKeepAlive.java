package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S00PacketKeepAlive implements Packet<INetHandlerPlayClient> {
   public int id;

   public int func_149134_c() {
      return this.id;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleKeepAlive(this);
   }

   public S00PacketKeepAlive(int var1) {
      this.id = var1;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.id);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.id = var1.readVarIntFromBuffer();
   }

   public S00PacketKeepAlive() {
   }
}
