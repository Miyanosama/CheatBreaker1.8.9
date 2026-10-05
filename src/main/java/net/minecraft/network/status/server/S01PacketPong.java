package net.minecraft.network.status.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusClient;

public class S01PacketPong implements Packet<INetHandlerStatusClient> {
   public long clientTime;

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.clientTime = var1.readLong();
   }

   public S01PacketPong(long var1) {
      this.clientTime = var1;
   }

   public S01PacketPong() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeLong(this.clientTime);
   }

   public void processPacket(INetHandlerStatusClient var1) {
      var1.handlePong(this);
   }
}
