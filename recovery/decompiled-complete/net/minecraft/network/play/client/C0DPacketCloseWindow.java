package net.minecraft.network.play.client;

import io.netty.channel.embedded.EmbeddedChannel;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.realms.Tezzelator;

public class C0DPacketCloseWindow implements Packet<INetHandlerPlayServer> {
   public EmbeddedChannel field_0001;
   public int windowId;
   public Tezzelator field_0000;

   public C0DPacketCloseWindow(int var1) {
      this.windowId = var1;
   }

   public C0DPacketCloseWindow() {
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processCloseWindow(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.windowId = var1.readByte();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.windowId);
   }
}
