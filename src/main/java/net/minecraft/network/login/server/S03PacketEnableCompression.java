package net.minecraft.network.login.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S03PacketEnableCompression implements Packet<INetHandlerLoginClient> {
   public int compressionTreshold;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.compressionTreshold);
   }

   public int getCompressionTreshold() {
      return this.compressionTreshold;
   }

   public S03PacketEnableCompression() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.compressionTreshold = var1.readVarIntFromBuffer();
   }

   public S03PacketEnableCompression(int var1) {
      this.compressionTreshold = var1;
   }

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleEnableCompression(this);
   }
}
