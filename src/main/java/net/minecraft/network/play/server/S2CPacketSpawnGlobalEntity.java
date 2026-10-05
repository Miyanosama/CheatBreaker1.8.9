package net.minecraft.network.play.server;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S2CPacketSpawnGlobalEntity implements Packet<INetHandlerPlayClient> {
   public int type;
   public int x;
   public int y;
   public int entityId;
   public int z;

   public int func_149050_e() {
      return this.y;
   }

   public int func_149051_d() {
      return this.x;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnGlobalEntity(this);
   }

   public S2CPacketSpawnGlobalEntity() {
   }

   public int func_149049_f() {
      return this.z;
   }

   public S2CPacketSpawnGlobalEntity(Entity var1) {
      this.entityId = var1.F();
      this.x = MathHelper.floor_double(var1.s * 32.0);
      this.y = MathHelper.floor_double(var1.t * 32.0);
      this.z = MathHelper.floor_double(var1.u * 32.0);
      if (var1 instanceof EntityLightningBolt) {
         this.type = 1;
      }
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityId = var1.readVarIntFromBuffer();
      this.type = var1.readByte();
      this.x = var1.readInt();
      this.y = var1.readInt();
      this.z = var1.readInt();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.type);
      var1.writeInt(this.x);
      var1.writeInt(this.y);
      var1.writeInt(this.z);
   }

   public int func_149053_g() {
      return this.type;
   }

   public int func_149052_c() {
      return this.entityId;
   }
}
