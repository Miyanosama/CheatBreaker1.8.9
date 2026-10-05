package net.minecraft.network.play.server;

import io.netty.channel.DefaultChannelPipeline$TailContext;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.item.EntityPainting$EnumArt;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.realms.RealmsAnvilLevelStorageSource;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import recovered.unidentified.UnidentifiedClass4078;

public class S10PacketSpawnPainting implements Packet<INetHandlerPlayClient> {
   public RealmsAnvilLevelStorageSource field_0004;
   public DefaultChannelPipeline$TailContext field_0007;
   public int entityID;
   public EnumFacing facing;
   public DefaultEventExecutorGroup field_0000;
   public UnidentifiedClass4078 field_0001;
   public BlockPos position;
   public String title;
   public BlockWoodSlab field_0002;

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
   public void writePacketData(PacketBuffer var1) {
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
   public void readPacketData(PacketBuffer var1) {
      this.entityID = var1.readVarIntFromBuffer();
      this.title = var1.readStringFromBuffer(EntityPainting$EnumArt.field_180001_A);
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
