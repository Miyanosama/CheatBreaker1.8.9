package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S19PacketEntityStatus implements Packet<INetHandlerPlayClient> {
   public byte logicOpcode;
   public int entityId;

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeInt(this.entityId);
      var1.writeByte(this.logicOpcode);
   }

   public byte getOpCode() {
      return this.logicOpcode;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityStatus(this);
   }

   public S19PacketEntityStatus(Entity var1, byte var2) {
      this.entityId = var1.F();
      this.logicOpcode = var2;
   }

   public S19PacketEntityStatus() {
   }

   public Entity getEntity(World var1) {
      return var1.getEntityByID(this.entityId);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityId = var1.readInt();
      this.logicOpcode = var1.readByte();
   }
}
