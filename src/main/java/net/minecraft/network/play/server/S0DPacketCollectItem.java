package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0DPacketCollectItem implements Packet<INetHandlerPlayClient> {
   public int collectedItemEntityId;
   public int entityId;

   public int getEntityID() {
      return this.entityId;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.collectedItemEntityId);
      var1.writeVarIntToBuffer(this.entityId);
   }

   public int getCollectedItemEntityID() {
      return this.collectedItemEntityId;
   }

   public S0DPacketCollectItem(int var1, int var2) {
      this.collectedItemEntityId = var1;
      this.entityId = var2;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.collectedItemEntityId = var1.readVarIntFromBuffer();
      this.entityId = var1.readVarIntFromBuffer();
   }

   public S0DPacketCollectItem() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCollectItem(this);
   }
}
