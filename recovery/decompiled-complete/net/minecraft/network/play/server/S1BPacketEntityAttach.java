package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1BPacketEntityAttach implements Packet<INetHandlerPlayClient> {
   public int leash;
   public int vehicleEntityId;
   public int entityId;

   public S1BPacketEntityAttach() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.entityId);
      var1.writeInt(this.vehicleEntityId);
      var1.writeByte(this.leash);
   }

   public int getVehicleEntityId() {
      return this.vehicleEntityId;
   }

   public int getEntityId() {
      return this.entityId;
   }

   public int getLeash() {
      return this.leash;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityAttach(this);
   }

   public S1BPacketEntityAttach(int var1, Entity var2, Entity var3) {
      this.leash = var1;
      this.entityId = var2.F();
      this.vehicleEntityId = var3 != null ? var3.F() : -1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readInt();
      this.vehicleEntityId = var1.readInt();
      this.leash = var1.readUnsignedByte();
   }
}
