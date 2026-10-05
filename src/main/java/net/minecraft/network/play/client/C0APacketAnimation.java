package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0APacketAnimation implements Packet<INetHandlerPlayServer> {
   public void processPacket(INetHandlerPlayServer var1) {
      var1.handleAnimation(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
   }
}
