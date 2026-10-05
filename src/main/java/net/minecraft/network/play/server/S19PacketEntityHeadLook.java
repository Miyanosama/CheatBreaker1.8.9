package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S19PacketEntityHeadLook implements Packet<INetHandlerPlayClient> {
   public byte yaw;
   public int entityId;

   public S19PacketEntityHeadLook() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.yaw);
   }

   public S19PacketEntityHeadLook(Entity var1, byte var2) {
      this.entityId = var1.F();
      this.yaw = var2;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityHeadLook(this);
   }

   public byte getYaw() {
      return this.yaw;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityId = var1.readVarIntFromBuffer();
      this.yaw = var1.readByte();
   }

   public Entity getEntity(World var1) {
      return var1.getEntityByID(this.entityId);
   }
}
