package net.minecraft.network.status.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;

public class C01PacketPing implements Packet<INetHandlerStatusServer> {
   public long clientTime;

   public C01PacketPing() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.clientTime = var1.readLong();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeLong(this.clientTime);
   }

   public long getClientTime() {
      return this.clientTime;
   }

   public void processPacket(INetHandlerStatusServer var1) {
      var1.processPing(this);
   }

   public C01PacketPing(long var1) {
      this.clientTime = var1;
   }
}
