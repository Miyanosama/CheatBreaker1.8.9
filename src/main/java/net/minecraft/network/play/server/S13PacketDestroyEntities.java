package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S13PacketDestroyEntities implements Packet<INetHandlerPlayClient> {
   public int[] entityIDs;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityIDs.length);

      for (int var2 = 0; var2 < this.entityIDs.length; var2++) {
         var1.writeVarIntToBuffer(this.entityIDs[var2]);
      }
   }

   public int[] getEntityIDs() {
      return this.entityIDs;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityIDs = new int[var1.readVarIntFromBuffer()];

      for (int var2 = 0; var2 < this.entityIDs.length; var2++) {
         this.entityIDs[var2] = var1.readVarIntFromBuffer();
      }
   }

   public S13PacketDestroyEntities(int... var1) {
      this.entityIDs = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleDestroyEntities(this);
   }

   public S13PacketDestroyEntities() {
   }
}
