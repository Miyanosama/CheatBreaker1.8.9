package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0DPacketCloseWindow implements Packet<INetHandlerPlayServer> {
   public int windowId;

   public C0DPacketCloseWindow(int var1) {
      this.windowId = var1;
   }

   public C0DPacketCloseWindow() {
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processCloseWindow(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.windowId = var1.readByte();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.windowId);
   }
}
