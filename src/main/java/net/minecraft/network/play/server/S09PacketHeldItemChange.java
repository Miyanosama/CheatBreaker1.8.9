package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S09PacketHeldItemChange implements Packet<INetHandlerPlayClient> {
   public int heldItemHotbarIndex;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleHeldItemChange(this);
   }

   public S09PacketHeldItemChange() {
   }

   public S09PacketHeldItemChange(int var1) {
      this.heldItemHotbarIndex = var1;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.heldItemHotbarIndex);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.heldItemHotbarIndex = var1.readByte();
   }

   public int getHeldItemHotbarIndex() {
      return this.heldItemHotbarIndex;
   }
}
