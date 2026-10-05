package net.minecraft.network.status.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;

public class C00PacketServerQuery implements Packet<INetHandlerStatusServer> {
   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
   }

   public void processPacket(INetHandlerStatusServer var1) {
      var1.processServerQuery(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
   }
}
