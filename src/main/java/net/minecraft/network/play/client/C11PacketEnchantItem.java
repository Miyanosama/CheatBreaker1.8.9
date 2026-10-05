package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C11PacketEnchantItem implements Packet<INetHandlerPlayServer> {
   public int button;
   public int windowId;

   public C11PacketEnchantItem(int var1, int var2) {
      this.windowId = var1;
      this.button = var2;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.windowId = var1.readByte();
      this.button = var1.readByte();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.windowId);
      var1.writeByte(this.button);
   }

   public int getButton() {
      return this.button;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processEnchantItem(this);
   }

   public C11PacketEnchantItem() {
   }

   public int getWindowId() {
      return this.windowId;
   }
}
