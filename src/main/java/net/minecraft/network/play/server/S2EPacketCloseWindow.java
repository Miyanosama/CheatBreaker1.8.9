package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2EPacketCloseWindow implements Packet<INetHandlerPlayClient> {
   public int windowId;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCloseWindow(this);
   }

   public S2EPacketCloseWindow() {
   }

   public S2EPacketCloseWindow(int var1) {
      this.windowId = var1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.windowId = var1.readUnsignedByte();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.windowId);
   }
}
