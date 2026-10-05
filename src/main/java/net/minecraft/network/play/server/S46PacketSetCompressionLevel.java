package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S46PacketSetCompressionLevel implements Packet<INetHandlerPlayClient> {
   public int threshold;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.threshold);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.threshold = var1.readVarIntFromBuffer();
   }

   public int getThreshold() {
      return this.threshold;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSetCompressionLevel(this);
   }
}
