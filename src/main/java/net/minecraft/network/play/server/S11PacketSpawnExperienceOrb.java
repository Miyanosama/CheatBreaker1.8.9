package net.minecraft.network.play.server;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S11PacketSpawnExperienceOrb implements Packet<INetHandlerPlayClient> {
   public int posZ;
   public int entityID;
   public int xpValue;
   public int posY;
   public int posX;

   public int getXPValue() {
      return this.xpValue;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnExperienceOrb(this);
   }

   public int getZ() {
      return this.posZ;
   }

   public int getX() {
      return this.posX;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityID = var1.readVarIntFromBuffer();
      this.posX = var1.readInt();
      this.posY = var1.readInt();
      this.posZ = var1.readInt();
      this.xpValue = var1.readShort();
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityID);
      var1.writeInt(this.posX);
      var1.writeInt(this.posY);
      var1.writeInt(this.posZ);
      var1.writeShort(this.xpValue);
   }

   public S11PacketSpawnExperienceOrb() {
   }

   public int getEntityID() {
      return this.entityID;
   }

   public int getY() {
      return this.posY;
   }

   public S11PacketSpawnExperienceOrb(EntityXPOrb var1) {
      this.entityID = var1.F();
      this.posX = MathHelper.floor_double(var1.s * 32.0);
      this.posY = MathHelper.floor_double(var1.t * 32.0);
      this.posZ = MathHelper.floor_double(var1.u * 32.0);
      this.xpValue = var1.getXpValue();
   }
}
