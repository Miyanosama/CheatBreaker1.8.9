package net.minecraft.network.play.server;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class S10PacketSpawnPainting implements Packet<INetHandlerPlayClient> {
   public int entityID;
   public EnumFacing facing;
   public BlockPos position;
   public String title;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnPainting(this);
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityID);
      var1.writeString(this.title);
      var1.writeBlockPos(this.position);
      var1.writeByte(this.facing.getHorizontalIndex());
   }

   public String getTitle() {
      return this.title;
   }

   public int getEntityID() {
      return this.entityID;
   }

   public S10PacketSpawnPainting() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityID = var1.readVarIntFromBuffer();
      this.title = var1.readStringFromBuffer(EntityPainting.EnumArt.field_180001_A);
      this.position = var1.readBlockPos();
      this.facing = EnumFacing.getHorizontal(var1.readUnsignedByte());
   }

   public S10PacketSpawnPainting(EntityPainting var1) {
      this.entityID = var1.F();
      this.position = var1.n();
      this.facing = var1.b;
      this.title = var1.art.title;
   }
}
