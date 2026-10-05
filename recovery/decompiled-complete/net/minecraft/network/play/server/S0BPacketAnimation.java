package net.minecraft.network.play.server;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0BPacketAnimation implements Packet<INetHandlerPlayClient> {
   public int type;
   public BlockPistonBase field_0002;
   public int entityId;

   public S0BPacketAnimation(Entity var1, int var2) {
      this.entityId = var1.F();
      this.type = var2;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.type);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.type = var1.readUnsignedByte();
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleAnimation(this);
   }

   public int getEntityID() {
      return this.entityId;
   }

   public int getAnimationType() {
      return this.type;
   }

   public S0BPacketAnimation() {
   }
}
